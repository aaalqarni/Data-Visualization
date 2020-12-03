/**
 * Copyright (c) 2004-2006 Regents of the University of California.
 * See "license-prefuse.txt" for licensing terms.
 */
package prefuse.demos;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.Action;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.ColorAction;
import prefuse.action.assignment.DataColorAction;
import prefuse.action.assignment.DataSizeAction;
import prefuse.action.filter.GraphDistanceFilter;
import prefuse.action.layout.Layout;
import prefuse.action.layout.graph.ForceDirectedLayout;
import prefuse.activity.Activity;
import prefuse.controls.ControlAdapter;
import prefuse.controls.DragControl;
import prefuse.controls.FocusControl;
import prefuse.controls.NeighborHighlightControl;
import prefuse.controls.PanControl;
import prefuse.controls.WheelZoomControl;
import prefuse.controls.ZoomControl;
import prefuse.controls.ZoomToFitControl;
import prefuse.data.Graph;
import prefuse.data.Node;
import prefuse.data.Schema;
import prefuse.data.Table;
import prefuse.data.Tuple;
import prefuse.data.event.TupleSetListener;
import prefuse.data.expression.parser.ExpressionParser;
import prefuse.data.io.DataIOException;
import prefuse.data.io.GraphMLReader;
import prefuse.data.query.SearchQueryBinding;
import prefuse.data.search.PrefixSearchTupleSet;
import prefuse.data.search.SearchTupleSet;
import prefuse.data.tuple.DefaultTupleSet;
import prefuse.data.tuple.TupleSet;
import prefuse.demos.GraphView.GraphMenuAction;
import prefuse.demos.GraphView.OpenGraphAction;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.LabelRenderer;
import prefuse.render.PolygonRenderer;
import prefuse.render.Renderer;
import prefuse.render.ShapeRenderer;
import prefuse.util.ColorLib;
import prefuse.util.FontLib;
import prefuse.util.GraphLib;
import prefuse.util.GraphicsLib;
import prefuse.util.PrefuseLib;
import prefuse.util.force.ForceSimulator;
import prefuse.util.ui.JForcePanel;
import prefuse.util.ui.JSearchPanel;
import prefuse.visual.AggregateItem;
import prefuse.visual.AggregateTable;
import prefuse.visual.DecoratorItem;
import prefuse.visual.EdgeItem;
import prefuse.visual.NodeItem;
import prefuse.visual.VisualGraph;
import prefuse.visual.VisualItem;
import prefuse.visual.expression.HoverPredicate;
import prefuse.visual.expression.InGroupPredicate;
/**
 * Demo application showcasing the use of AggregateItems to
 * visualize groupings of nodes with in a graph visualization.
 * It also shows:
 * - Decorators for edges 
 * - Decorators for nodes 
 * - Decorators for aggregates (only on mouseOver) 
 * - using a label-field which contains the String to be drawn by the decorators 
 * - currently, only one Renderer (a LabelRenderer) ist used for all the 
 *      decorator groups (so all decorators show a text) 
 * - using different text formatting for each decorator group 
 * - using any shape for the nodes (ShapeRenderer on the nodes) together with a 
 *      label (LabelRenderer on the nodes decorators) 
 * - all decorator groups use the same Layout, they are centered on the item 
 *      they decorate
 * 
 * This class uses the AggregateLayout class to compute bounding
 * polygons for each aggregate and the AggregateDragControl to
 * enable drags of both nodes and node aggregates.
 * 
 * @author <a href="http://jheer.org">jeffrey heer</a>
 * @author Bjorn Kruse
 */
public class AggregateDecoratorDemo2 extends Display {
    public static final String GRAPH = "graph";
    public static final String NODES = "graph.nodes";
    public static final String EDGES = "graph.edges";
    public static final String AGGR = "aggregates";
    private static final String SELECTED = "sel";
    public static final String EDGE_DECORATORS = "edgeDeco";
    public static final String NODE_DECORATORS = "nodeDeco";
    public static final String AGGR_DECORATORS = "aggrDeco";
    
    private static final Schema DECORATOR_SCHEMA = PrefuseLib.getVisualItemSchema(); 
    static { 
        DECORATOR_SCHEMA.setDefault(VisualItem.INTERACTIVE, false); 
        DECORATOR_SCHEMA.setDefault(VisualItem.TEXTCOLOR, ColorLib.gray(128)); 
        DECORATOR_SCHEMA.setDefault(VisualItem.FONT, FontLib.getFont("Tahoma",8));
    }
    
    public AggregateDecoratorDemo() {
        // initialize display and data
        super(new Visualization());
        initDataGroups();
        
        // set up the renderers
        // draw the nodes as basic shapes
        LabelRenderer nodeR = new LabelRenderer("name");
        nodeR.setRoundedCorner(8, 8); // round the corners
        
        // draw aggregates as polygons with curved edges
        Renderer polyR = new PolygonRenderer(Constants.POLY_TYPE_CURVE);
        ((PolygonRenderer)polyR).setCurveSlack(0.15f);
        
        DefaultRendererFactory drf = new DefaultRendererFactory();
        drf.setDefaultRenderer(nodeR);
        drf.add("ingroup('aggregates')", polyR);
        drf.add(new InGroupPredicate(EDGE_DECORATORS), new LabelRenderer(VisualItem.LABEL));
        drf.add(new InGroupPredicate(NODE_DECORATORS), new LabelRenderer(VisualItem.LABEL));
        drf.add(new InGroupPredicate(AGGR_DECORATORS), new LabelRenderer("id"));
        m_vis.setRendererFactory(drf);
        
        // adding decorators, one group for the nodes, one for the edges and one
        // for the aggregates
        DECORATOR_SCHEMA.setDefault(VisualItem.TEXTCOLOR, ColorLib.rgb(255, 0, 0));
        m_vis.addDecorators(EDGE_DECORATORS, EDGES, DECORATOR_SCHEMA);
        
        DECORATOR_SCHEMA.setDefault(VisualItem.TEXTCOLOR, ColorLib.gray(0));
        m_vis.addDecorators(NODE_DECORATORS, NODES, DECORATOR_SCHEMA);
        
        // the HoverPredicate makes this group of decorators to appear only on mouseOver
        DECORATOR_SCHEMA.setDefault(VisualItem.TEXTCOLOR, ColorLib.gray(255, 128));
        DECORATOR_SCHEMA.setDefault(VisualItem.FONT, FontLib.getFont("Tahoma", Font.BOLD, 48));
        m_vis.addDecorators(AGGR_DECORATORS, AGGR, new HoverPredicate(), DECORATOR_SCHEMA);
                
        // set up the visual operators
        // first set up all the color actions
        ColorAction nStroke = new ColorAction(NODES,
                VisualItem.STROKECOLOR, ColorLib.gray(0));
        nStroke.add("_hover", ColorLib.gray(50));
        
        // create our nominal color palette
        // red for root set, black otherwise
        int[] palette_root = new int[] {
            ColorLib.rgb(255,255,255), ColorLib.rgb(255,0,0)
        };
        // map nominal data values to colors using our provided palette
        DataColorAction nFill = new DataColorAction(NODES, "root",
                Constants.NOMINAL, VisualItem.FILLCOLOR, palette_root);
        nFill.add("ingroup('_search_')", ColorLib.rgb(255,190,190));
        nFill.add(VisualItem.HIGHLIGHT, ColorLib.rgb(255,200,125));
        nFill.add("_hover", ColorLib.gray(200));
        
         
        ColorAction nEdges = new ColorAction(EDGES,
                VisualItem.STROKECOLOR, ColorLib.gray(0));
        
        ColorAction arrows = new ColorAction("graph.edges",
                VisualItem.FILLCOLOR, ColorLib.gray(0));
        
        ColorAction aStroke = new ColorAction(AGGR, VisualItem.STROKECOLOR);
        aStroke.setDefaultColor(ColorLib.gray(200));
        aStroke.add("_hover", ColorLib.rgb(255,100,100));
        
        int[] palette = new int[] {
            ColorLib.rgba(255,200,200,150),
            ColorLib.rgba(200,255,200,150),
            ColorLib.rgba(200,200,255,150)
        };
        ColorAction aFill = new DataColorAction(AGGR, "id",
                Constants.NOMINAL, VisualItem.FILLCOLOR, palette);
                
        // bundle the color actions
        ActionList colors = new ActionList();
        colors.add(nStroke);
        colors.add(nFill);
        colors.add(nEdges);
        colors.add(aStroke);
        colors.add(arrows);
        colors.add(aFill);
        
        Action dataSizeAction = new DataSizeAction("graph.edges", "score");
        Action NodeSizeAction = new DataSizeAction("graph.nodes", "authority");
        
        // now create the main layout routine
        ActionList layout = new ActionList(Activity.INFINITY); //Change the score in order to stop the action after N milliseconds
                
        //Impossible to drag items when it had stopped !
        layout.add(colors);
        //layout.add(new ForceDirectedLayout(GRAPH, true));
        layout.add(new AggregateLayout2(AGGR));
        layout.add(new LabelLayout2(EDGE_DECORATORS));
        layout.add(new LabelLayout2(NODE_DECORATORS));
        layout.add(new LabelLayout2(AGGR_DECORATORS));
        layout.add(dataSizeAction);
        layout.add(NodeSizeAction);
        layout.add(new RepaintAction());
        
        ActionList layout2 = new ActionList(10000); //Rendre valeur parametrable
        layout2.add(new ForceDirectedLayout(GRAPH));
        
        m_vis.putAction("layout", layout);
        m_vis.putAction("layout2", layout2);
        
        m_vis.runAfter("layout", "layout2");
               
        /*
        // Create the focus group
        TupleSet selectedItems = new DefaultTupleSet();
        m_vis.addFocusGroup(SELECTED, selectedItems);
        // listen for changes
        TupleSet focusGroup = m_vis.getGroup(SELECTED);
        focusGroup.addTupleSetListener(new TupleSetListener() {
            public void tupleSetChanged(TupleSet ts, Tuple[] add, Tuple[] rem) {
                //do whatever you do with newly selected/deselected items
//                      m_vis.cancel("layout");
                for (int i = 0; i < add.length; i++) {
                    VisualItem item = (VisualItem) add[i];
                    item.setHighlighted(true);
                }
                for (int i = 0; i < rem.length; i++) {
                    VisualItem item = (VisualItem) rem[i];
                    item.setHighlighted(false);
                }
//                        m_vis.run("layout");
            }
        });*/
        
        // set up the display
        //Display display = new Display(m_vis);
        setSize(700,700);
        pan(350, 350);
        setHighQuality(false);
        //setHighQuality(true);
        addControlListener(new AggregateDragControl2());
        addControlListener(new ZoomControl());
        addControlListener(new WheelZoomControl());
        addControlListener(new ZoomToFitControl());
        addControlListener(new PanControl());
        addControlListener(new NeighborHighlightControl());
        
                
        //MAD the seach items vocus group
        // create a panel for editing force values
        ForceSimulator fsim = ((ForceDirectedLayout)layout2.get(0)).getForceSimulator();
        JForcePanel fpanel = new JForcePanel(fsim);
        
        SearchTupleSet search = new PrefixSearchTupleSet();
        m_vis.addFocusGroup(Visualization.SEARCH_ITEMS, search);
        
        //MAD - the search query binding and the search panel
        SearchQueryBinding sq = new SearchQueryBinding((Table) m_vis
        .getGroup(NODES), "name", (SearchTupleSet) m_vis
        .getGroup(Visualization.SEARCH_ITEMS));
        JSearchPanel searchPanel = sq.createSearchPanel();
        searchPanel.setShowResultCount(true);
        searchPanel.setMaximumSize(new Dimension(400, 20));
        searchPanel.setFont(FontLib.getFont("Tahoma", Font.PLAIN, 11));
        
        Box df = new Box(BoxLayout.Y_AXIS);
        df.add(searchPanel);
        df.setBorder(BorderFactory.createTitledBorder("Search"));
        fpanel.add(df);
        
        fpanel.add(Box.createVerticalGlue());
        
        // create a new JSplitPane to present the interface
        JSplitPane split = new JSplitPane();
        //split.setLeftComponent(display);
        split.setRightComponent(fpanel);
        split.setOneTouchExpandable(true);
        split.setContinuousLayout(false);
        split.setDividerLocation(700);
        // set things running
        m_vis.run("layout");
        m_vis.run("layout2");
        
        add(split);  
    }
    
    private void initDataGroups() {
        // load the socialnet.xml file. it is assumed that the file can be
        // found at the root of the java classpath
        Graph g = null;
        try {
            g = new GraphMLReader().readGraph("/socialnet.xml");
            //g = new GraphMLReader().readGraph("/home/alu/Lab/Cifre-2010/Perl/prefuse.xml");
        } catch ( DataIOException e ) {
            e.printStackTrace();
            System.err.println("Error loading graph. Exiting...");
            System.exit(1);
        }
                  
        // add labels for nodes and edges
        g.addColumn(VisualItem.LABEL, String.class);
        Iterator graphNodes = g.nodes();
        Iterator graphEdges = g.edges();
        
        int nodeIndex = 0;
        while(graphNodes.hasNext()) {
            g.getNode(nodeIndex).setString(VisualItem.LABEL, g.getNode(nodeIndex).getString("name")); //""+i
            nodeIndex++;
            graphNodes.next();
        } 
        
        int edgeIndex = 0;
        while(graphEdges.hasNext()) {
            g.getEdge(edgeIndex).setString(VisualItem.LABEL, g.getEdge(edgeIndex).getString("score")); //""+i
            edgeIndex++;
            graphEdges.next();
        }
        
        
        // add visual data groups
        VisualGraph vg = m_vis.addGraph(GRAPH, g);
        m_vis.setInteractive(EDGES, null, false);
        m_vis.setValue(NODES, null, VisualItem.SHAPE,
                new Integer(Constants.SHAPE_ELLIPSE));
        
        AggregateTable at = m_vis.addAggregates(AGGR);
        at.addColumn(VisualItem.POLYGON, float[].class);
        at.addColumn("id", int.class);
        
        // add nodes to aggregates
        // create an aggregate for each cluster
        
        for(int i = 1 ; i < 4 ; i++){
            Iterator graphAggregates = vg.nodes();
            AggregateItem aitem = (AggregateItem)at.addItem();
            aitem.setInt("id", i);
            int aggregateIndex = 0;
            while(graphAggregates.hasNext()) {
                if(g.getNode(aggregateIndex).getInt("cluster") == i){
                    aitem.addItem((VisualItem)graphAggregates.next());
                    aggregateIndex++;
                }
                else{
                    graphAggregates.next();
                    aggregateIndex++;
                }
                //graphNodes.next();
            }
        }
        /*
        Iterator nodes = vg.nodes();
        for ( int i=0; i<3; ++i ) {
            AggregateItem aitem = (AggregateItem)at.addItem();
            aitem.setInt("id", i);
            for ( int j=0; j<3; ++j ) {
                aitem.addItem((VisualItem)nodes.next());
            }
        }*/
    }
    
    public static void main(String[] argv) {
        JFrame frame = demo();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
      
    public static JFrame demo() {
        
        //final GraphView view = new GraphView(g, label);
        
        // set up menu
        JMenu dataMenu = new JMenu("Menu");
        /*dataMenu.add(new OpenGraphAction(view));
        dataMenu.add(new GraphMenuAction("Grid","ctrl 1",view) {
            protected Graph getGraph() {
                return GraphLib.getGrid(15,15);
            }
        });
        dataMenu.add(new GraphMenuAction("Clique","ctrl 2",view) {
            protected Graph getGraph() {
                return GraphLib.getClique(10);
            }
        });
        dataMenu.add(new GraphMenuAction("Honeycomb","ctrl 3",view) {
            protected Graph getGraph() {
                return GraphLib.getHoneycomb(5);
            }
        });
        dataMenu.add(new GraphMenuAction("Balanced Tree","ctrl 4",view) {
            protected Graph getGraph() {
                return GraphLib.getBalancedTree(3,5);
            }
        });
        dataMenu.add(new GraphMenuAction("Diamond Tree","ctrl 5",view) {
            protected Graph getGraph() {
                return GraphLib.getDiamondTree(3,3,3);
            }
        });*/
        JMenuBar menubar = new JMenuBar();
        menubar.add(dataMenu);
        
        // launch window
        AggregateDecoratorDemo ad = new AggregateDecoratorDemo();
        JFrame frame = new JFrame("p r e f u s e  |  a g g r e g a t e d   a n d   d e c o r a t e d");
        frame.setJMenuBar(menubar);
        frame.getContentPane().add(ad);
        frame.pack();
        return frame;      
    }
    
} // end of class AggregateDemo
/**
 * Layout algorithm that computes a convex hull surrounding
 * aggregate items and saves it in the "_polygon" field.
 */
class AggregateLayout2 extends Layout {
    
    private int m_margin = 5; // convex hull pixel margin
    private double[] m_pts;   // buffer for computing convex hulls
    
    public AggregateLayout2(String aggrGroup) {
        super(aggrGroup);
    }
    
    /**
     * @see edu.berkeley.guir.prefuse.action.Action#run(edu.berkeley.guir.prefuse.ItemRegistry, double)
     */
    public void run(double frac) {
        
        AggregateTable aggr = (AggregateTable)m_vis.getGroup(m_group);
        // do we have any  to process?
        int num = aggr.getTupleCount();
        if ( num == 0 ) return;
        
        // update buffers
        int maxsz = 0;
        for ( Iterator aggrs = aggr.tuples(); aggrs.hasNext();  )
            maxsz = Math.max(maxsz, 4*2*
                    ((AggregateItem)aggrs.next()).getAggregateSize());
        if ( m_pts == null || maxsz > m_pts.length ) {
            m_pts = new double[maxsz];
        }
        
        // compute and assign convex hull for each aggregate
        Iterator aggrs = m_vis.visibleItems(m_group);
        while ( aggrs.hasNext() ) {
            AggregateItem aitem = (AggregateItem)aggrs.next();
            int idx = 0;
            if ( aitem.getAggregateSize() == 0 ) continue;
            VisualItem item = null;
            Iterator iter = aitem.items();
            while ( iter.hasNext() ) {
                item = (VisualItem)iter.next();
                if ( item.isVisible() ) {
                    addPoint(m_pts, idx, item, m_margin);
                    idx += 2*4;
                }
            }
            // if no aggregates are visible, do nothing
            if ( idx == 0 ) continue;
            // compute convex hull
            double[] nhull = GraphicsLib.convexHull(m_pts, idx);
            
            // prepare viz attribute array
            float[]  fhull = (float[])aitem.get(VisualItem.POLYGON);
            if ( fhull == null || fhull.length < nhull.length )
                fhull = new float[nhull.length];
            else if ( fhull.length > nhull.length )
                fhull[nhull.length] = Float.NaN;
            
            // copy hull values
            for ( int j=0; j<nhull.length; j++ )
                fhull[j] = (float)nhull[j];
            aitem.set(VisualItem.POLYGON, fhull);
            aitem.setValidated(false); // force invalidation
        }
    }
    
    private static void addPoint(double[] pts, int idx, 
                                 VisualItem item, int growth)
    {
        Rectangle2D b = item.getBounds();
        double minX = (b.getMinX())-growth, minY = (b.getMinY())-growth;
        double maxX = (b.getMaxX())+growth, maxY = (b.getMaxY())+growth;
        pts[idx]   = minX; pts[idx+1] = minY;
        pts[idx+2] = minX; pts[idx+3] = maxY;
        pts[idx+4] = maxX; pts[idx+5] = minY;
        pts[idx+6] = maxX; pts[idx+7] = maxY;
    }
    
} // end of class AggregateLayout
/**
 * Interactive drag control that is "aggregate-aware"
 */
class AggregateDragControl2 extends ControlAdapter {
    private VisualItem activeItem;
    protected Point2D down = new Point2D.Double();
    protected Point2D temp = new Point2D.Double();
    protected boolean dragged;
    
    /**
     * Creates a new drag control that issues repaint requests as an item
     * is dragged.
     */
    public AggregateDragControl2() {
    }
        
    /**
     * @see prefuse.controls.Control#itemEntered(prefuse.visual.VisualItem, java.awt.event.MouseEvent)
     */
    public void itemEntered(VisualItem item, MouseEvent e) {
        Display d = (Display)e.getSource();
        d.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        activeItem = item;
        if ( !(item instanceof AggregateItem) )
            setFixed(item, true);
    }
    
    /**
     * @see prefuse.controls.Control#itemExited(prefuse.visual.VisualItem, java.awt.event.MouseEvent)
     */
    public void itemExited(VisualItem item, MouseEvent e) {
        if ( activeItem == item ) {
            activeItem = null;
            setFixed(item, false);
        }
        Display d = (Display)e.getSource();
        d.setCursor(Cursor.getDefaultCursor());
    }
    
    /**
     * @see prefuse.controls.Control#itemPressed(prefuse.visual.VisualItem, java.awt.event.MouseEvent)
     */
    public void itemPressed(VisualItem item, MouseEvent e) {
        if (!SwingUtilities.isLeftMouseButton(e)) return;
        dragged = false;
        Display d = (Display)e.getComponent();
        d.getAbsoluteCoordinate(e.getPoint(), down);
        if ( item instanceof AggregateItem )
            setFixed(item, true);
    }
    
    /**
     * @see prefuse.controls.Control#itemReleased(prefuse.visual.VisualItem, java.awt.event.MouseEvent)
     */
    public void itemReleased(VisualItem item, MouseEvent e) {
        if (!SwingUtilities.isLeftMouseButton(e)) return;
        if ( dragged ) {
            activeItem = null;
            setFixed(item, false);
            dragged = false;
        }            
    }
    
    /**
     * @see prefuse.controls.Control#itemDragged(prefuse.visual.VisualItem, java.awt.event.MouseEvent)
     */
    public void itemDragged(VisualItem item, MouseEvent e) {
        if (!SwingUtilities.isLeftMouseButton(e)) return;
        dragged = true;
        Display d = (Display)e.getComponent();
        d.getAbsoluteCoordinate(e.getPoint(), temp);
        double dx = temp.getX()-down.getX();
        double dy = temp.getY()-down.getY();
        
        move(item, dx, dy);
        
        down.setLocation(temp);
    }
    protected static void setFixed(VisualItem item, boolean fixed) {
        if ( item instanceof AggregateItem ) {
            Iterator items = ((AggregateItem)item).items();
            while ( items.hasNext() ) {
                setFixed((VisualItem)items.next(), fixed);
            }
        } else {
            item.setFixed(fixed);
        }
    }
    
    protected static void move(VisualItem item, double dx, double dy) {
        if ( item instanceof AggregateItem ) {
            Iterator items = ((AggregateItem)item).items();
            while ( items.hasNext() ) {
                move((VisualItem)items.next(), dx, dy);
            }
        } else {
            double x = item.getX();
            double y = item.getY();
            item.setStartX(x);  item.setStartY(y);
            item.setX(x+dx);    item.setY(y+dy);
            item.setEndX(x+dx); item.setEndY(y+dy);
        }
    }
    
} // end of class AggregateDragControl
/**
 * Set label positions. Labels are assumed to be DecoratorItem instances,
 * decorating their respective nodes. The layout simply gets the bounds
 * of the decorated node and assigns the label coordinates to the center
 * of those bounds.
 */
class LabelLayout2 extends Layout {
    public LabelLayout2(String group) {
        super(group);
    }
    public void run(double frac) {
        Iterator iter = m_vis.items(m_group);
        while ( iter.hasNext() ) {
            DecoratorItem decorator = (DecoratorItem)iter.next();
            VisualItem decoratedItem = decorator.getDecoratedItem();
            Rectangle2D bounds = decoratedItem.getBounds();
            
            double x = bounds.getCenterX();
            double y = bounds.getCenterY();
            //modification to move edge labels more to the arrow head
            /*double x2 = 0, y2 = 0;
            if (decoratedItem instanceof EdgeItem){
                VisualItem dest = ((EdgeItem)decoratedItem).getTargetItem(); 
                x2 = dest.getX();
                y2 = dest.getY();
                x = (x + x2) / 2;
                y = (y + y2) / 2;
            }*/
            
            
            setX(decorator, null, x);
            setY(decorator, null, y);
        }
    }
} 
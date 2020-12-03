package prefuse.demos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.*;

import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.GroupAction;
import prefuse.action.ItemAction;
import prefuse.action.RepaintAction;
import prefuse.action.animate.ColorAnimator;
import prefuse.action.animate.PolarLocationAnimator;
import prefuse.action.animate.QualityControlAnimator;
import prefuse.action.animate.VisibilityAnimator;
import prefuse.action.assignment.*;
import prefuse.action.filter.GraphDistanceFilter;
import prefuse.action.filter.VisibilityFilter;
import prefuse.action.layout.CollapsedSubtreeLayout;
import prefuse.action.layout.Layout;
import prefuse.action.layout.graph.RadialTreeLayout;
import prefuse.activity.Activity;
import prefuse.activity.SlowInSlowOutPacer;
import prefuse.controls.*;
import prefuse.data.*;
import prefuse.data.event.TupleSetListener;
import prefuse.data.expression.AbstractPredicate;
import prefuse.data.expression.AndPredicate;
import prefuse.data.expression.Predicate;
import prefuse.data.expression.parser.ExpressionParser;
import prefuse.data.io.GraphMLReader;
import prefuse.data.query.ListQueryBinding;
import prefuse.data.query.RangeQueryBinding;
import prefuse.data.query.SearchQueryBinding;
import prefuse.data.search.PrefixSearchTupleSet;
import prefuse.data.search.SearchTupleSet;
import prefuse.data.tuple.DefaultTupleSet;
import prefuse.data.tuple.TupleSet;
//import prefuse.demos.RadialGraphView_First_Task.LabelLayout2.BiDirectionPredicate;
import prefuse.render.AbstractShapeRenderer;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.EdgeRenderer;
import prefuse.render.LabelRenderer;
import prefuse.util.ColorLib;
import prefuse.util.FontLib;
import prefuse.util.PrefuseLib;
import prefuse.util.UpdateListener;
import prefuse.util.ui.JFastLabel;
import prefuse.util.ui.JSearchPanel;
import prefuse.util.ui.UILib;
import prefuse.util.ui.JCustomTooltip;
import prefuse.visual.DecoratorItem;
import prefuse.visual.EdgeItem;
import prefuse.visual.NodeItem;
import prefuse.visual.VisualItem;
import prefuse.visual.expression.InGroupPredicate;
import prefuse.visual.sort.TreeDepthItemSorter;


/**
 * Demonstration of a node-link tree viewer
 *
 * @version 1.0
 * @author <a href="http://jheer.org">jeffrey heer</a>
 * 
 
 */
public class RadialGraphView_First_Task extends Display {
    //----------------------------------------------------------------
    //public class BiDirectionPredicate extends AbstractPredicate{}
     {
         
     }
    //---------------------------------------------------------------- 

    public static final String DATA_FILE ="/Twitter_Networks_06_filtered.graphml";
    private static final String tree = "tree";
    private static final String treeNodes = "tree.nodes";
    private static final String treeEdges = "tree.edges";
    public static final String EDGE_DECORATORS = "edgeDeco";
    private static final String linear = "linear";
    private static LabelRenderer   m_nodeRenderer;
    private static  String InDegree;
    private static  String OutDegree;
    private Display view;
    final static TupleSet tset = new DefaultTupleSet(); 
    static AndPredicate   pfilter;
    static SearchQueryBinding searchQ;
    
    //***************************************************************************
    private EdgeRenderer m_edgeRenderer;
    private EdgeRenderer bi_direction;
    
    private static final Schema DECORATOR_SCHEMA = PrefuseLib.getVisualItemSchema();
    {
        DECORATOR_SCHEMA.setDefault(VisualItem.INTERACTIVE, false); 
    	DECORATOR_SCHEMA.setDefault(VisualItem.TEXTCOLOR, ColorLib.gray(128)); 
    	DECORATOR_SCHEMA.setDefault(VisualItem.FONT, FontLib.getFont("Tahoma",16));
    }
   //**************************************************************************** 
    private String m_label = "label";
   // private String e_label = "label";
    
    public RadialGraphView_First_Task(Graph g, String label) {
        super(new Visualization());
        
        m_label = label;
        // -- set up visualization --
        m_vis.add(tree, g);
        //m_vis.addDecorators(m_label, treeNodes, labelP);
        m_vis.setInteractive(treeEdges, null,false);
        // -- set up renderers --
        m_nodeRenderer = new LabelRenderer(m_label);
        m_nodeRenderer.setRenderType(AbstractShapeRenderer.RENDER_TYPE_FILL);
        m_nodeRenderer.setRenderType(AbstractShapeRenderer.RENDER_TYPE_DRAW_AND_FILL);
        m_nodeRenderer.setHorizontalAlignment(Constants.CENTER);
        m_nodeRenderer.setRoundedCorner(10,10);
        m_edgeRenderer = new EdgeRenderer(Constants.EDGE_TYPE_LINE, Constants.EDGE_ARROW_FORWARD);
        m_edgeRenderer.setArrowHeadSize(20,30);
        bi_direction=new EdgeRenderer(Constants.EDGE_TYPE_LINE, Constants.EDGE_ARROW_NONE);
       
        
       // m_edgeRenderer.
         //view = new Display(m_vis,);
                      
        DefaultRendererFactory rf = new DefaultRendererFactory(m_nodeRenderer,m_edgeRenderer);
        Predicate biDirectional = new BiDirectionPredicate(g);
        rf.add(biDirectional, bi_direction);
        //Decoration------------------
        rf.add(new InGroupPredicate(EDGE_DECORATORS), new LabelRenderer("id"));
        //---------End_Decoration------
        rf.add(new InGroupPredicate(treeEdges), (m_edgeRenderer));
        rf.add(new InGroupPredicate(EDGE_DECORATORS), new LabelRenderer(VisualItem.LABEL));
        m_vis.setRendererFactory(rf);
         DECORATOR_SCHEMA.setDefault(VisualItem.TEXTCOLOR, ColorLib.gray(0));
        m_vis.addDecorators(EDGE_DECORATORS, treeEdges, DECORATOR_SCHEMA);
               
        // -- set up processing actions --
       //-----------------------------------------------------
        final AndPredicate filter_E = new AndPredicate();
        final Predicate ageGreater24 = ExpressionParser.predicate("LEFT(name,1)= 'n'");
        //----------------------------------------------------------------------
         int maxhops = 1, hops = 1;
        final GraphDistanceFilter gdf = new GraphDistanceFilter(tree, hops);
         ActionList draw = new ActionList();
        draw.add(gdf);
        draw.add(new ColorAction(treeNodes, VisualItem.FILLCOLOR, ColorLib.rgb(200,200,255)));
        draw.add(new ColorAction(treeNodes, VisualItem.STROKECOLOR, 0));
        draw.add(new ColorAction(treeNodes, VisualItem.TEXTCOLOR, ColorLib.rgb(0,0,0)));
        draw.add(new ColorAction(treeEdges, VisualItem.FILLCOLOR, ColorLib.gray(200)));
        draw.add(new ColorAction(treeEdges, VisualItem.STROKECOLOR, ColorLib.gray(200)));
        
        Predicate highlight_and_filter = 
        	new AndPredicate(filter_E, ExpressionParser.predicate("_indegree"));
        
       g.addColumn(label, filter_E);
      // m_vis.putAction(label,ageGreater24);
      //-----------------------------------------------------
        ColorAction nodeColor = new ColorAction(treeNodes,
                VisualItem.FILLCOLOR, ColorLib.rgb(104,200,200));
       // nodeColor.add(VisualItem.HIGHLIGHT, ColorLib.rgb(200,200,200));
        nodeColor.add("ingroup('_focus_')", ColorLib.rgb(198,229,229));
        nodeColor.add("ingroup('DGNHC_TARGETGROUP_NODE')", ColorLib.rgb(255,0,0));
        nodeColor.add("ingroup('DGNHC_BOTHGROUP_NODE')", ColorLib.rgb(0,0,0));
        nodeColor.add("ingroup('DGNHC_SOURCEGROUP_NODE')", ColorLib.rgb(0,0,205));
        nodeColor.add("ingroup('_search_')", ColorLib.rgb(255,0,190));
        nodeColor.add("ingroup('test')", ColorLib.rgb(255,100,190));
        //----------------------------------------------------------------
      
        //---------------------------------------------------------
        TupleSetListener tsl = new TupleSetListener() {

            public void tupleSetChanged(TupleSet tset, Tuple[] added, Tuple[] removed)
            {
                for ( int i = 0; i < added.length; i++)
                {
                // Display all focus node neighbors
                NodeItem n=(NodeItem)added[i];
                Iterator nItr = n.neighbors();
                     while (nItr.hasNext())
                {
                    ((NodeItem)nItr.next()).setVisible(true);
                    
                }
                // Display all focus node edges

                Iterator eItr = n.edges();
                while (eItr.hasNext())
                {
                ((EdgeItem)eItr.next()).setVisible(false);
                }    

               throw new UnsupportedOperationException("Not supported yet.");
                }
            }
            
        };
        //---------------------------------------------------------------------
       
        //---------------------------------------------------------------------
        //-----------------------------------------------------------------
       // VisualItem item1 = treeEdges.
        //VisualItem item2 = treeEdges.getTargetItem();
        
        
      
      
        ItemAction textColor = new TextColorAction(treeNodes);
        m_vis.putAction("textColor", textColor);
        
        
        
         ColorAction edgeColor2 = new ColorAction(treeEdges,
                VisualItem.STROKECOLOR, ColorLib.rgb(200,200,200));
                //edgeColor2.add(VisualItem.HIGHLIGHT, ColorLib.rgb(255,0,0));
                edgeColor2.add("ingroup('DGNHC_BOTHGROUP')", ColorLib.rgb(0,0,0));
                edgeColor2.add("ingroup('DGNHC_SOURCEGROUP')", ColorLib.rgb(0,0,205));
                edgeColor2.add("ingroup('DGNHC_TARGETGROUP') ", ColorLib.rgb(255,0,0)); 
               // edgeColor2.add( biDirectional, ColorLib.rgb(250,0,250));
               
       //-----------------------------------------------------------------------
           Predicate pPreceding = (Predicate) ExpressionParser.parse("ISEDGE()",true);
        ColorAction edgeColor= new ColorAction(treeEdges,VisualItem.FILLCOLOR, ColorLib.rgb(0,200,0));
        //edgeColor.getDefaultColor();
        
       // edgeColor.add(VisualItem.HIGHLIGHT, ColorLib.rgb(255,0,0));
         edgeColor.add("ingroup('DGNHC_SOURCEGROUP')", ColorLib.rgb(0,0,205));
         edgeColor.add("ingroup('DGNHC_TARGETGROUP')", ColorLib.rgb(255,0,0));
        //edgeColor.add("ingroup('SOURCE_EDGE')", ColorLib.rgb(255,0,0));
       // edgeColor.add(pPreceding, ColorLib.rgb(0,100,0));
        
        
        FontAction fonts = new FontAction(treeNodes, 
                FontLib.getFont("Tahoma", 15));
        fonts.add("ingroup('_focus_')", FontLib.getFont("Tahoma", 25));
        
       //-----------------------------------------------------------------------

        // recolor
        ActionList recolor = new ActionList();
        
       //recolor.add(textColor);
      // recolor.add(nodeColor);
       //recolor.add(edgeColor);
       //recolor.add(edgeColor2); 
        m_vis.putAction("recolor", recolor);
        
        //Resize The Edges
        //----------------------------------------------------------------------
            g.addColumn("InDegree2",String.class);
            g.addColumn("OutDegree2",String.class);
        //----------------------------------------------------------------------
         ActionList resize = new ActionList();
        SizeAction defaultNodeSize = new SizeAction(treeEdges,2.3);
        defaultNodeSize.add("ingroup('DGNHC_SOURCEGROUP')",5.5);
        m_vis.putAction("setSizes", defaultNodeSize);
        //----------------------------------------------------------------------
         ActionList Visual = new ActionList();
         VisibilityAnimator visual= new VisibilityAnimator("ingroup('tset')");
         
         m_vis.putAction("Visual", Visual);
         //VisualAction 
        // repaint
        ActionList repaint = new ActionList();
        repaint.add(recolor);
        repaint.add(new RepaintAction());
        m_vis.putAction("repaint", repaint);
        
        // animate paint change
        ActionList animatePaint = new ActionList(400);
        animatePaint.add(new ColorAnimator(treeNodes));
        animatePaint.add(new RepaintAction());
        m_vis.putAction("animatePaint", animatePaint);
        
           //treeNodes.getSourceNode();
        // create the tree layout action
        /***************************************************/
        RadialTreeLayout treeLayout = new RadialTreeLayout(tree);
       // treeLayout.setAngularBounds(-Math.PI/2, Math.PI);
        m_vis.putAction("treeLayout", treeLayout);
        
        CollapsedSubtreeLayout subLayout = new CollapsedSubtreeLayout(tree);
        m_vis.putAction("subLayout", subLayout);
        
        //----------------------------------------------------------------------
        GraphDistanceFilter filter_Graph = new prefuse.action.filter.GraphDistanceFilter(treeEdges, Visualization.SELECTED_ITEMS, 2);
        
        
          m_vis.putAction("filter",filter_Graph);
          int val = 10;
        Predicate p = (Predicate)ExpressionParser.parse("INDEGREE()<"+val+"",true);
     //   VisibilityFilter vf = new VisibilityFilter(treeNodes,p);
      //  VisibilityFilter vf2 = new VisibilityFilter(treeEdges,p);
        
        //m_vis.putAction("VisibilityFilter", vf);
        
        //----------------------------------------------------------------------
        // create the filtering and layout
        ActionList filter = new ActionList();
        filter.add(new TreeRootAction(tree));
        //filter.add(new tsl());
        filter.add(fonts);
        filter.add(treeLayout);
        filter.add(subLayout);
        filter.add(textColor);
        filter.add(nodeColor);
        filter.add(defaultNodeSize);
        filter.add(edgeColor);
        filter.add(edgeColor2);
        m_vis.putAction("filter", filter);
        
        
        // animated transition
        ActionList animate = new ActionList(1250);
        animate.setPacingFunction(new SlowInSlowOutPacer());
        animate.add(new QualityControlAnimator());
        animate.add(new VisibilityAnimator(tree));
        animate.add(new PolarLocationAnimator(treeNodes, linear));
        animate.add(new ColorAnimator(treeNodes));
       animate.add(new VisibilityFilter(treeNodes,p));
        animate.add(new RepaintAction());
        //--------------------------------------------------------------------
        m_vis.putAction("animate", animate);
        m_vis.alwaysRunAfter("filter", "animate");
       DirectedGraphNeighborHighlightControl dgNeighborHighlightControl=
        new DirectedGraphNeighborHighlightControl(m_vis);
        DirectedGraphNeighborHighlightControl_Edges Edge_highlight=
                new DirectedGraphNeighborHighlightControl_Edges(m_vis);
        
        //---------------------------------------------------------------------
        // initialize the display
        setSize(800,800);
        setItemSorter(new TreeDepthItemSorter());
        addControlListener(new DragControl());
        addControlListener(new ZoomToFitControl());
        addControlListener(new ZoomControl());
        addControlListener(new PanControl());
        //addControlListener(new FocusControl());
        addControlListener(new FocusControl(1, "filter"));
        addControlListener(new HoverActionControl("repaint"));
        addControlListener(dgNeighborHighlightControl);
        addControlListener(Edge_highlight);
     //   addControlListner(dgNeighborHighlightControl);
        addControlListener(new NeighborHighlightControl());
        // ------------------------------------------------
      //  m_vis.removeAction("test");
        // filter graph and perform layout
        m_vis.run("filter");
        
        //----------------------------------------------------------------------
        
        //----------------------------------------------------------------------
        
        
        // maintain a set of items that should be interpolated linearly
        // this isn't absolutely necessary, but makes the animations nicer
        // the PolarLocationAnimator should read this set and act accordingly
        m_vis.addFocusGroup(linear, new DefaultTupleSet());
        m_vis.getGroup(Visualization.FOCUS_ITEMS).addTupleSetListener(
            new TupleSetListener() {
                public void tupleSetChanged(TupleSet t, Tuple[] add, Tuple[] rem) {
                   TupleSet linearInterp = m_vis.getGroup(linear);
                 //  TupleSet linearInterp = m_vis.getSourceData(tree);
                   
                    if ( add.length < 1 ) return; linearInterp.clear();
                    for (  Node n = (Node)add[0]; n!=null; n=n.getParent() ){

                        linearInterp.addTuple(n);
                         
                    }
                  
                }
            }
        );
        
      
      
       SearchTupleSet search = new PrefixSearchTupleSet();
        m_vis.addFocusGroup(Visualization.SEARCH_ITEMS, search);
        search.addTupleSetListener(new TupleSetListener() {
            public void tupleSetChanged(TupleSet t, Tuple[] add, Tuple[] rem) {
                m_vis.cancel("animatePaint");
                m_vis.run("recolor");
                m_vis.run("animatePaint");
            }
        });
        //*******************************************
        
        
        
    }
    
    // ------------------------------------------------------------------------
    
    public static void main(String argv[]) {
        String infile = DATA_FILE;
        String label = "name";
        
        if ( argv.length > 1 ) {
            infile = argv[0];
            label = argv[1];
        }
        
        UILib.setPlatformLookAndFeel();
        
        JFrame frame = new JFrame("p r e f u s e  |  r a d i a l g r a p h v i e w");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(demo(infile, label));
        frame.pack();
        frame.setVisible(true);
    }
    
    public static JPanel demo() {
        return demo(DATA_FILE, "name");
    }
    //------------------------------------------------------------------------
    public static JPanel demo(String datafile, final String label) {
        Graph g = null;
        try {
            g = new GraphMLReader().readGraph(datafile);
           
            
            // g.addColumn(VisualItem.LABEL,"InDegree");
           // g.addColumn(VisualItem.LABEL,"OutDegree");
            
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(1);
        }
        return demo(g, label);
    }
    //---------------------------------------------------------------------------
    public static JPanel demo(final Graph g, final String label) {        
        // create a new radial tree view
        //****************************************
          
                
         //***************************************
        
         int maxhops = 4, hops = 4;
       
        final GraphDistanceFilter gdf = new GraphDistanceFilter(tree, hops);
       // g.getInDegree(label.);
        final RadialGraphView_First_Task gview = new RadialGraphView_First_Task(g, label);
        //
       // m_nodeRenderer.setTextField(VisualItem.LABEL);
      
        final Visualization vis = gview.getVisualization();
        
        // create a search panel for the tree map
          
       // animate.add(new VisibilityFilter(treeNodes, pfilter));
        
      
        SearchQueryBinding sq = new SearchQueryBinding(
             (Table)vis.getGroup(treeNodes), label,
             (SearchTupleSet)vis.getGroup(Visualization.SEARCH_ITEMS));
        JSearchPanel search = sq.createSearchPanel();
        search.setShowResultCount(true);
        search.setBorder(BorderFactory.createEmptyBorder(5,5,4,0));
        search.setFont(FontLib.getFont("Tahoma", Font.PLAIN, 11));
        //-----------------------------------------------------------------------
        pfilter=new AndPredicate(sq.getPredicate()); 
        ActionList animate = new ActionList(Activity.INFINITY);
        
        animate.add(new RepaintAction());
        animate.add(new VisibilityFilter(treeNodes, pfilter));
        vis.putAction("layout", animate);
        RangeQueryBinding list_query = new   RangeQueryBinding(
                (Table)vis.getGroup(treeNodes),
                "name"
                );
         final AndPredicate filter = new AndPredicate(list_query.getPredicate());
         
         
         System.out.println(list_query.getPredicate().toString());
         ActionList update = new ActionList();
        vis.addFocusGroup("test", tset);
        UpdateListener lstnr = new UpdateListener() {
        public void update(Object src) {
        Iterator iter = vis.items(treeNodes,filter);
        tset.clear();
        while(iter.hasNext()){
          // System.out.println(iter.next().toString());
            Tuple val=(Tuple)iter.next();
            NodeItem n=(NodeItem) val;
            Iterator edge=n.edges();
            while(edge.hasNext())
            {
               EdgeItem m=(EdgeItem) edge.next();
               m.setVisible(false);
            }    
          System.out.println(val.toString());
          tset.addTuple(val);
         }
                     vis.run("repaint");
         }
    };
         update.add(new VisibilityFilter(treeNodes, filter));
       
        
        vis.putAction("update", update);
        
        UpdateListener lstnr2 = new UpdateListener() {
            public void update(Object src) {
                vis.run("update");
            }
        }; 
      
       filter.addExpressionListener(lstnr);
       filter.addExpressionListener(lstnr2);
        /*/ 
       
        */
        
        //vis.run(update);
        
        JSlider slider=list_query.createSlider();
        System.out.print(slider.getCursor().getName());
        
        final JFastLabel title = new JFastLabel("                 ");
        title.setPreferredSize(new Dimension(350, 40));
        title.setVerticalAlignment(SwingConstants.BOTTOM);
        title.setBorder(BorderFactory.createEmptyBorder(3,0,0,0));
        title.setFont(FontLib.getFont("Tahoma", Font.PLAIN, 14));
        
        //------------------
       
        
        //------------------
        
        
        gview.addControlListener(new ControlAdapter() {
             Display d;
             NodeItem i;
            
            public void itemEntered(VisualItem item, MouseEvent e) {
                 if ( item.canGetString("InDegree") )
                 title.setText(item.getString("InDegree"));
                     
                    d = (Display)e.getSource();
                    i = (NodeItem) item;
                    d.setPreferredSize(new Dimension(350, 40));
                    //InDegree=Integer.toString(i.getInDegree());
                    //OutDegree=Integer.toString(i.getOutDegree());
                   // d.setToolTipText("Indegree="+InDegree);
                  // d.setToolTipText("OutDegree="+OutDegree);
                   //d.addControlListener(new ToolTipControl("Edge"));
                   //  d.setToolTipText("OutDegree="+OutDegree);
                  //  System.out.println(item.getString(label));
                    JPopupMenu jpub = new JPopupMenu();
                    
                    jpub.add("InDegree: " + i.getInDegree());
                    jpub.add("OutDegree: " +i.getOutDegree());
                    jpub.show(e.getComponent(),(int) item.getX(),
                    (int) item.getY());
                    if(item instanceof NodeItem) 
                    {             
                       //NodeItem t1=(NodeItem) item;
                     //  Iterator it=t1.edges();
                      // t1.setVisible(false);
                       //while(it.hasNext())
                    //   {
                         // EdgeItem value = (EdgeItem)it.next();
                         // value.setVisible(false);
                          
                           
                           
                               
                   //    }
                        //t1.getParentEdge().setString(label, label);
                     //  System.out.print("I am an Edge");
                    }
            }
           // gview.addControlListener(new ToolTipControl("attributeName"));
            
           // @Override
            public void itemExited(VisualItem item, MouseEvent e) {
             title.setText(InDegree);
             d.setToolTipText(null);
         //    i.setSize(1.5);
             
                   
            }
 
            
            
            
         
        }
                );
        
        Box box = new Box(BoxLayout.X_AXIS);
        box.add(Box.createHorizontalStrut(10));
        box.add(title);
        box.add(Box.createHorizontalGlue());
        box.add(search);
        box.add(slider);
        box.add(Box.createHorizontalStrut(3));
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(gview, BorderLayout.CENTER);
        panel.add(box, BorderLayout.SOUTH);
        
        Color BACKGROUND = Color.WHITE;
        Color FOREGROUND = Color.DARK_GRAY;
        UILib.setColor(panel, BACKGROUND, FOREGROUND);
        
        return panel;
    }
    
    // ------------------------------------------------------------------------
    
    /**
     * Switch the root of the tree by requesting a new spanning tree
     * at the desired root
     */
    public static class TreeRootAction extends GroupAction {
        public TreeRootAction(String graphGroup) {
            super(graphGroup);
        }
        public void run(double frac) {
            //TupleSet focus = m_vis.getGroup(Visualization.FOCUS_ITEMS);
           TupleSet focus = m_vis.getGroup(Visualization.FOCUS_ITEMS);
            if ( focus==null || focus.getTupleCount() == 0 ) return;
        
            Graph g = (Graph)m_vis.getGroup(m_group);
            Node f = null;
            Iterator tuples = focus.tuples();
            
           
            while (tuples.hasNext() && !g.containsTuple(f=(Node)tuples.next()))
            {
               f = null;
                  
            }
            
            g.getSpanningTree(f);
            InDegree=Integer.toString(f.getInDegree());
            OutDegree=Integer.toString(f.getOutDegree());
            
            
             //System.out.println( f.getColumnCount());
            
           //   System.out.println( f.getDegree()-f.getInDegree()); 
            // System.out.println( g.getSpanningTree(f).getInDegree(f));
        }
    }
 //-----------------------------------------------------------------------------

//-----------------------------------------------------------------------------
    
    /**
     * Set node fill colors
     */
    public static class NodeColorAction extends ColorAction {
        public NodeColorAction(String group) {
            super(group, VisualItem.FILLCOLOR, ColorLib.rgb(250,250,250));
           
            add("_hover", ColorLib.gray(220,230));
            add("_highlight", ColorLib.rgb(200,0,200));
            add("ingroup('_search_')", ColorLib.rgb(255,0,190));
           // add("_hover", ColorLib.gray(
          //  add("ingroup('DGNHC_SOURCEGROUP')", ColorLib.rgb(255,190,190));
            add("ingroup('_focus_')", ColorLib.rgb(198,229,229));
            add("ingroup('test')",ColorLib.rgb(132,255,163));
        }
        
       //--------------------------------------------------------------------  
        public static class NodeColorAction2 extends ColorAction {
        public NodeColorAction2(String group) {
            super(group, VisualItem.STROKECOLOR, ColorLib.rgba(255,255,255,0));
            add("_fixed", ColorLib.gray(220,230));
           add("_highlight", ColorLib.rgb(255,200,240));
           // add("_hover", ColorLib.gray(
            add("ingroup('_search_')", ColorLib.rgb(255,0,190));
            add("ingroup('_focus_')", ColorLib.rgb(198,229,229));
        }
         }
        
        //------------------------------------------------------------------
        
        public static class TreeEdgesColorAction extends ColorAction { 
        public TreeEdgesColorAction(String group) {
            super(group, VisualItem.STROKECOLOR, ColorLib.rgba(255,255,255,0));
          
           add("_highlight", ColorLib.rgb(255,230,240));
           // add("_hover", ColorLib.gray(

             }
        }
     
    } 
//-----------------------------------------------------------------------------   
    /**
     * Set node text colors
     */
    public static class TextColorAction extends ColorAction {
        public TextColorAction(String group) {
            super(group, VisualItem.TEXTCOLOR, ColorLib.gray(0));
            add("_hover", ColorLib.rgb(255,0,0));
            add("_highlight", ColorLib.rgb(0,255,255));
        }
    } // end of inner class TextColorAction
   
 }
//---------------------------------------------------------------------------
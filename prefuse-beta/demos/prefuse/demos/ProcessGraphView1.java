package prefuse.demos;



import java.awt.BorderLayout;

import java.awt.Checkbox;

import java.awt.Color;

import java.awt.Component;

import java.awt.Dimension;

import java.awt.Font;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;

import java.awt.event.WindowAdapter;

import java.awt.event.WindowEvent;

import java.awt.geom.Rectangle2D;

import java.util.Iterator;



import javax.swing.AbstractAction;

import javax.swing.BorderFactory;

import javax.swing.Box;

import javax.swing.BoxLayout;

import javax.swing.JButton;

import javax.swing.JCheckBox;

import javax.swing.JDialog;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JList;

import javax.swing.JMenu;

import javax.swing.JMenuBar;

import javax.swing.JPanel;

import javax.swing.JScrollPane;

import javax.swing.JSplitPane;

import javax.swing.JTextArea;

import javax.swing.KeyStroke;

import javax.swing.ListSelectionModel;

import javax.swing.SwingConstants;

import javax.swing.event.ChangeEvent;

import javax.swing.event.ChangeListener;

import javax.swing.event.ListSelectionEvent;

import javax.swing.event.ListSelectionListener;



import prefuse.Constants;

import prefuse.Display;

import prefuse.Visualization;

import prefuse.action.ActionList;

import prefuse.action.RepaintAction;

import prefuse.action.assignment.ColorAction;

import prefuse.action.assignment.DataColorAction;

import prefuse.action.assignment.DataSizeAction;

import prefuse.action.assignment.FontAction;

import prefuse.action.filter.GraphDistanceFilter;

import prefuse.action.filter.VisibilityFilter;

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

import prefuse.data.Table;

import prefuse.data.Tuple;

import prefuse.data.event.TupleSetListener;

import prefuse.data.expression.AndPredicate;

import prefuse.data.expression.Predicate;

import prefuse.data.expression.parser.ExpressionParser;

import prefuse.data.io.GraphMLReader;

import prefuse.data.query.ListQueryBinding;

import prefuse.data.query.SearchQueryBinding;

import prefuse.data.tuple.TupleSet;

import prefuse.render.DefaultRendererFactory;

import prefuse.render.LabelRenderer;

import prefuse.render.PolygonRenderer;

import prefuse.render.Renderer;

import prefuse.util.ColorLib;

import prefuse.util.FontLib;

import prefuse.util.GraphLib;

import prefuse.util.GraphicsLib;

import prefuse.util.display.DisplayLib;

import prefuse.util.display.ItemBoundsListener;

import prefuse.util.force.ForceSimulator;

import prefuse.util.io.IOLib;

import prefuse.util.ui.JFastLabel;

import prefuse.util.ui.JForcePanel;

import prefuse.util.ui.JSearchPanel;

import prefuse.util.ui.JToggleGroup;

import prefuse.util.ui.JValueSlider;

import prefuse.util.ui.UILib;

import prefuse.visual.AggregateItem;

import prefuse.visual.AggregateTable;

import prefuse.visual.VisualGraph;

import prefuse.visual.VisualItem;



/**

 * @author <a href="http://jheer.org">jeffrey heer</a>

 */

// Modified from original GraphView, Richard Evans



public class ProcessGraphView1 extends JPanel {



    private static final String graph = "graph";

    private static final String nodes = "graph.nodes";

    private static final String edges = "graph.edges";

    public static final String AGGR = "aggregates";



    public Visualization m_vis;

    public int agIndex;

    

    public ProcessGraphView1(Graph g,  String label, String agLabel) {

    	super(new BorderLayout());

    	

        // create a new, empty visualization for our data

        m_vis = new Visualization();

        

        

        // --------------------------------------------------------------------

        // set up the renderers

        

        LabelRenderer tr = new LabelRenderer();

        tr.setRoundedCorner(8, 8);

        

        DefaultRendererFactory drf = new DefaultRendererFactory();

        drf.setDefaultRenderer(tr);

        m_vis.setRendererFactory(drf);

        

        // draw aggregates as polygons with curved edges

        Renderer polyR = new PolygonRenderer(Constants.POLY_TYPE_CURVE);

        ((PolygonRenderer)polyR).setCurveSlack(0.15f);

        drf.add("ingroup('aggregates')", polyR);

  

        // --------------------------------------------------------------------

        // register the data with a visualization

        

        // adds graph to visualization and sets renderer label field

        setGraph(g, label, agLabel);

        

        // fix selected focus nodes

        TupleSet focusGroup = m_vis.getGroup(Visualization.FOCUS_ITEMS); 

        focusGroup.addTupleSetListener(new TupleSetListener() {

            public void tupleSetChanged(TupleSet ts, Tuple[] add, Tuple[] rem)

            {

                for ( int i=0; i<rem.length; ++i )

                    ((VisualItem)rem[i]).setFixed(false);

                for ( int i=0; i<add.length; ++i ) {

                    ((VisualItem)add[i]).setFixed(false);

                    ((VisualItem)add[i]).setFixed(true);

                }

                if ( ts.getTupleCount() == 0 ) {

                    ts.addTuple(rem[0]);

                    ((VisualItem)rem[0]).setFixed(false);

                }

                m_vis.run("draw");

            }

        });   

        

        // --------------------------------------------------------------------

        // create actions to process the visual data



        int hops = 20;

        final GraphDistanceFilter filter = new GraphDistanceFilter(graph, hops);



        // label fill color palette

        int[] fpalette = ColorLib.getHSBPalette((agIndex + 1), 200, 100);

        ColorAction fill = new DataColorAction(nodes, agLabel,

                Constants.NOMINAL, VisualItem.FILLCOLOR, fpalette);

        

        // aggregate poly fill color palette

        int[] apalette = ColorLib.getHSBPalette((agIndex + 1), 200, 50);

        ColorAction aFill = new DataColorAction(AGGR, "id",

        	Constants.NOMINAL, VisualItem.FILLCOLOR, apalette);

        

        fill.add(VisualItem.FIXED, ColorLib.rgb(255,100,100));

        fill.add(VisualItem.HIGHLIGHT, ColorLib.rgb(255,200,125));

              

        // set up dynamic queries, search set

        ListQueryBinding   yearsQ    = new ListQueryBinding(m_vis.getGroup(nodes), agLabel, false);

        SearchQueryBinding searchQ   = new SearchQueryBinding(m_vis.getGroup(nodes), label);

        

        // construct the filtering predicate

        AndPredicate pfilter = new AndPredicate(searchQ.getPredicate());     

        pfilter.add(yearsQ.getPredicate());

  

        ActionList draw = new ActionList();

        draw.add(filter);

        draw.add(fill);

        draw.add(aFill);

        

        draw.add(new ColorAction(nodes, VisualItem.STROKECOLOR, 0));

        draw.add(new ColorAction(nodes, VisualItem.TEXTCOLOR, ColorLib.rgb(0,0,0)));

        draw.add(new ColorAction(edges, VisualItem.FILLCOLOR, ColorLib.gray(200)));

        draw.add(new ColorAction(edges, VisualItem.STROKECOLOR, ColorLib.gray(200)));

      

        ActionList animate = new ActionList(Activity.INFINITY);

        animate.add(new ForceDirectedLayout(graph));

        animate.add(new AggregateLayout(AGGR));

        animate.add(fill);

        animate.add(new RepaintAction());

        animate.add(new VisibilityFilter(nodes, pfilter));

               

        // finally, we register our ActionList with the Visualization.

        // we can later execute our Actions by invoking a method on our

        // Visualization, using the name we've chosen below.

        m_vis.putAction("draw", draw);

        m_vis.putAction("layout", animate);



        m_vis.runAfter("draw", "layout");

        

        

        // --------------------------------------------------------------------

        // set up a display to show the visualization

        

        Display display = new Display(m_vis);

        display.setSize(700,700);

        display.pan(350, 350);

 //       display.setHighQuality(true);   // What does this do?

        display.setForeground(Color.GRAY);

        display.setBackground(Color.WHITE);

        

        // main display controls

        display.addControlListener(new AggregateDragControl());

        display.addControlListener(new FocusControl(2));

        display.addControlListener(new PanControl());

        display.addControlListener(new ZoomControl());

        display.addControlListener(new WheelZoomControl());

        display.addControlListener(new ZoomToFitControl());

        display.addControlListener(new NeighborHighlightControl());

        

        display.setForeground(Color.GRAY);

        display.setBackground(Color.WHITE);

              

        final JTextArea title = new JTextArea(3, 20);

        title.setPreferredSize(new Dimension(350, 20));

        title.setBorder(BorderFactory.createEmptyBorder(3,0,0,0));

        title.setFont(FontLib.getFont("Tahoma", Font.PLAIN, 10));

        

        display.addControlListener(new ControlAdapter() {

            public void itemEntered(VisualItem item, MouseEvent e) {

            	Table t = item.getTable();

                if ( item.canGetString(t.getColumnName(0))) {

                	for (int i=1; i<t.getColumnCount(); i++) {

                		if (!t.getColumnName(i).startsWith("_")) {

                			title.append(t.getColumnName(i) + ": " + item.getString(t.getColumnName(i)) + "\n\r");

                		}

                	}

                }

            }

            public void itemExited(VisualItem item, MouseEvent e) {

                title.setText(null);

            }

        });

        

        // --------------------------------------------------------------------        

        // launch the visualization

        

        

        Box nodeInfo = new Box(BoxLayout.Y_AXIS);

        nodeInfo.add(Box.createVerticalStrut(6));

        nodeInfo.add(title);

        Box ni = new Box(BoxLayout.Y_AXIS);

        ni.add(nodeInfo);

        ni.add(Box.createVerticalStrut(8));

        ni.setBorder(BorderFactory.createTitledBorder("Details"));

        

        // create a panel for editing force values

        ForceSimulator fsim = ((ForceDirectedLayout)animate.get(0)).getForceSimulator();

        JForcePanel fpanel = new JForcePanel(fsim);

             

        final JValueSlider slider = new JValueSlider("Distance", 0, hops, hops);

        slider.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {

                filter.setDistance(slider.getValue().intValue());

                m_vis.run("draw");

            }

        });

        slider.setBackground(Color.WHITE);

        slider.setPreferredSize(new Dimension(300,30));

        slider.setMaximumSize(new Dimension(300,30));

        

        Box cf = new Box(BoxLayout.Y_AXIS);

        cf.add(slider);

        cf.setBorder(BorderFactory.createTitledBorder("Connectivity Filter"));

                 

        // and the checkboxes for filtering

        JToggleGroup yq = yearsQ.createCheckboxGroup();

        Box radioBox = new Box(BoxLayout.Y_AXIS);

        yq.setAxisType(BoxLayout.Y_AXIS);

        yq.setLayout(new GridLayout(10, 2));

        for (int i=0; i<yq.getComponentCount(); i++) {

        	JCheckBox cb = (JCheckBox)yq.getComponent(i);

        	cb.setBackground(ColorLib.getColor(apalette[i]));

        	cb.setSelected(true);

        }

        radioBox.add(yq);

        Box yf = new Box(BoxLayout.Y_AXIS);

        yf.add(yq);

        yq.setAlignmentX(Component.RIGHT_ALIGNMENT);

        yf.setBorder(BorderFactory.createTitledBorder("Filter of " + agLabel));



        // set up search box

        JSearchPanel searcher = searchQ.createSearchPanel();

        searcher.setLabelText(label + ": ");

        searcher.setBorder(BorderFactory.createEmptyBorder(5,5,5,0));

        Box sf = new Box(BoxLayout.Y_AXIS);

        sf.add(searcher);

        sf.setBorder(BorderFactory.createTitledBorder("Search"));



        // add the boxes to the frame

        cf.add(Box.createHorizontalStrut(300));

        yf.add(Box.createHorizontalStrut(300));

        sf.add(Box.createHorizontalStrut(300));

        ni.add(Box.createHorizontalStrut(300));

        fpanel.add(cf);

        fpanel.add(yf);

        fpanel.add(sf);

        fpanel.add(ni);

        

        // create a new JSplitPane to present the interface

        JSplitPane split = new JSplitPane();

        split.setRightComponent(display);

        split.setLeftComponent(fpanel);

        split.setOneTouchExpandable(true);

        split.setContinuousLayout(false);

        split.setDividerLocation(300);

        

        // now we run our action list

         m_vis.run("draw");

        

        add(split);

    }

    

    public void setGraph(Graph g, String label) {

        // update labeling

        DefaultRendererFactory drf = (DefaultRendererFactory)

                                                m_vis.getRendererFactory();

        ((LabelRenderer)drf.getDefaultRenderer()).setTextField(label);

        

        // update graph

        m_vis.removeGroup(graph);

        VisualGraph vg = m_vis.addGraph(graph, g);

        m_vis.setValue(edges, null, VisualItem.INTERACTIVE, Boolean.FALSE);

        VisualItem f = (VisualItem)vg.getNode(0);

        m_vis.getGroup(Visualization.FOCUS_ITEMS).setTuple(f);

        f.setFixed(false);

    }

    

    public void setGraph(Graph g, String label, String agLabel) {

        // update labeling

        DefaultRendererFactory drf = (DefaultRendererFactory)

                                                m_vis.getRendererFactory();

        ((LabelRenderer)drf.getDefaultRenderer()).setTextField(label);

        

        // update graph

        m_vis.removeGroup(graph);

        VisualGraph vg = m_vis.addGraph(graph, g);

        m_vis.setValue(edges, null, VisualItem.INTERACTIVE, Boolean.FALSE);



        AggregateTable at = m_vis.addAggregates(AGGR);

        at.addColumn(VisualItem.POLYGON, float[].class);

        at.addColumn("id", int.class);

//        m_vis.setInteractive(AGGR, null, true);

        

        agIndex = 0;

        String[] agFields = new String[20];

        Iterator nodes = vg.nodes();

        VisualItem n = (VisualItem)nodes.next();

       agFields[agIndex] = (String)n.get(agLabel);

        AggregateItem aitem = (AggregateItem)at.addItem();

        aitem.setInt("id", agIndex);

        aitem.addItem(n);



        // Add items to the aggregate table based on the agLabel column

        while ( nodes.hasNext() ) {

        	n = (VisualItem)nodes.next();

        	int index = -1;

        	for (int i = 0; i<=agIndex; i++ ) {

        		if ( agFields[i].equals( (String)n.get(agLabel) ) ) {

        			index = i;

        			aitem.setInt("id", index);

        			aitem.addItem(n);

        		}

        	}

        	if ( index == -1 ){

        		agIndex += 1;

                agFields[agIndex] = (String)n.get(agLabel);

                aitem = (AggregateItem)at.addItem();

                aitem.setInt("id", agIndex);

                aitem.addItem(n);

        	}

        }

        

        VisualItem f = (VisualItem)vg.getNode(0);

        m_vis.getGroup(Visualization.FOCUS_ITEMS).setTuple(f);

        f.setFixed(false);

    }

    

    // ------------------------------------------------------------------------

    // Main and demo methods

    

    public static void main(String[] args) {

        UILib.setPlatformLookAndFeel();

        

        // create graphview

        String datafile ="/Array.xml";;

        String label = "label";

        String agLabel = (String)null;

        if ( args.length > 1 ) {

            datafile = args[0];

            label = args[1];

         }

        if ( args.length > 2 ) {

            agLabel = args[2];

        }

        JFrame frame = demo(datafile, label, agLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    

    public static JFrame demo() {

        return demo((String)null, "label", (String)null);

    }

    

    public static JFrame demo(String datafile, String label, String agLabel) {

        Graph g = null;

        if ( datafile == null ) {

            g = GraphLib.getGrid(15,15);

            label = "label";

        } else {

            try {

                g = new GraphMLReader().readGraph(datafile);

            } catch ( Exception e ) {

                e.printStackTrace();

                System.exit(1);

            }

        }

       

        return demo(g, label, agLabel);

    }

    

    public static JFrame demo(Graph g, final String label, String agLabel) {

        final ProcessGraphView1 view = new ProcessGraphView1(g, label, agLabel);

        

        // set up menu

        JMenu dataMenu = new JMenu("Data");

        dataMenu.add(new OpenGraphAction(view));

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

        });

        JMenuBar menubar = new JMenuBar();

        menubar.add(dataMenu);

        

        // launch window

        JFrame frame = new JFrame("p r e f u s e  |  P r o c e s s V i e w");

        frame.setJMenuBar(menubar);

        frame.setContentPane(view);

        frame.pack();

        frame.setVisible(true);

        

        frame.addWindowListener(new WindowAdapter() {

            public void windowActivated(WindowEvent e) {

                view.m_vis.run("layout");

            }

            public void windowDeactivated(WindowEvent e) {

                view.m_vis.cancel("layout");

            }

        });

     

        return frame;

    }

    

    

    // ------------------------------------------------------------------------

    

    /**

     * Swing menu action that loads a graph into the graph viewer.

     */

    public abstract static class GraphMenuAction extends AbstractAction {

        private ProcessGraphView1 m_view;

        public GraphMenuAction(String name, String accel, ProcessGraphView1 view) {

            m_view = view;

            this.putValue(AbstractAction.NAME, name);

            this.putValue(AbstractAction.ACCELERATOR_KEY,

                          KeyStroke.getKeyStroke(accel));

        }

        public void actionPerformed(ActionEvent e) {

            m_view.setGraph(getGraph(), "label");

        }

        protected abstract Graph getGraph();

    }

    

    public static class OpenGraphAction extends AbstractAction {

        private ProcessGraphView1 m_view;



        public OpenGraphAction(ProcessGraphView1 view) {

            m_view = view;

            this.putValue(AbstractAction.NAME, "Open File...");

            this.putValue(AbstractAction.ACCELERATOR_KEY,

                          KeyStroke.getKeyStroke("ctrl O"));

        }

        public void actionPerformed(ActionEvent e) {

            Graph g = IOLib.getGraphFile(m_view);

            if ( g == null ) return;

            String label = getLabel(m_view, g, "Choose a field to use for node labels:");

            if ( label != null ) {

                 String agField = getLabel(m_view, g, "Choose a field to use for aggregation:");

                if ( agField != null ) {

                    m_view.setGraph(g, label, agField);

                } else {

                	m_view.setGraph(g, label);

                }           	

            }

        }

        public static String getLabel(Component c, Graph g, String dlabel) {

            // get the column names

            Table t = g.getNodeTable();

            int  cc = t.getColumnCount();

            String[] names = new String[cc];

            for ( int i=0; i<cc; ++i )

                names[i] = t.getColumnName(i);

            

            // where to store the result

            final String[] label = new String[1];



            // -- build the dialog -----

            // we need to get the enclosing frame first

            while ( c != null && !(c instanceof JFrame) ) {

                c = c.getParent();

            }

            final JDialog dialog = new JDialog(

                    (JFrame)c, "Choose Field", true);

            

            // create the ok/cancel buttons

            final JButton ok = new JButton("OK");

            ok.setEnabled(false);

            ok.addActionListener(new ActionListener() {

               public void actionPerformed(ActionEvent e) {

                   dialog.setVisible(false);

               }

            });

            JButton cancel = new JButton("Cancel");

            cancel.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    label[0] = null;

                    dialog.setVisible(false);

                }

            });

            

            // build the selection list

            final JList list = new JList(names);

            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            list.getSelectionModel().addListSelectionListener(

            new ListSelectionListener() {

                public void valueChanged(ListSelectionEvent e) {

                    int sel = list.getSelectedIndex(); 

                    if ( sel >= 0 ) {

                        ok.setEnabled(true);

                        label[0] = (String)list.getModel().getElementAt(sel);

                    } else {

                        ok.setEnabled(false);

                        label[0] = null;

                    }

                }

            });

            JScrollPane scrollList = new JScrollPane(list);

            

            JLabel title = new JLabel(dlabel);

            

            // layout the buttons

            Box bbox = new Box(BoxLayout.X_AXIS);

            bbox.add(Box.createHorizontalStrut(5));

            bbox.add(Box.createHorizontalGlue());

            bbox.add(ok);

            bbox.add(Box.createHorizontalStrut(5));

            bbox.add(cancel);

            bbox.add(Box.createHorizontalStrut(5));

            

            // put everything into a panel

            JPanel panel = new JPanel(new BorderLayout());

            panel.add(title, BorderLayout.NORTH);

            panel.add(scrollList, BorderLayout.CENTER);

            panel.add(bbox, BorderLayout.SOUTH);

            panel.setBorder(BorderFactory.createEmptyBorder(5,2,2,2));

            

            // show the dialog

            dialog.setContentPane(panel);

            dialog.pack();

            dialog.setLocationRelativeTo(c);

            dialog.setVisible(true);

            dialog.dispose();

            

            // return the label field selection

            return label[0];

        }

    }

    

    public static class FitOverviewListener implements ItemBoundsListener {

        private Rectangle2D m_bounds = new Rectangle2D.Double();

        private Rectangle2D m_temp = new Rectangle2D.Double();

        private double m_d = 15;

        public void itemBoundsChanged(Display d) {

            d.getItemBounds(m_temp);

            GraphicsLib.expand(m_temp, 25/d.getScale());

            

            double dd = m_d/d.getScale();

            double xd = Math.abs(m_temp.getMinX()-m_bounds.getMinX());

            double yd = Math.abs(m_temp.getMinY()-m_bounds.getMinY());

            double wd = Math.abs(m_temp.getWidth()-m_bounds.getWidth());

            double hd = Math.abs(m_temp.getHeight()-m_bounds.getHeight());

            if ( xd>dd || yd>dd || wd>dd || hd>dd ) {

                m_bounds.setFrame(m_temp);

                DisplayLib.fitViewToBounds(d, m_bounds, 0);

            }

        }

    }

    

} 
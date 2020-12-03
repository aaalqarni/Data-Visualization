package prefuse.demos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.ColorAction;
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
import prefuse.data.query.RangeQueryBinding;
import prefuse.data.query.SearchQueryBinding;
import prefuse.data.search.PrefixSearchTupleSet;
import prefuse.data.search.SearchTupleSet;
import prefuse.data.tuple.DefaultTupleSet;
import prefuse.data.tuple.TupleSet;
//import prefuse.demos.RadialGraphView_First.FinalControlListener;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.EdgeRenderer;
import prefuse.render.LabelRenderer;
import prefuse.util.*;
import prefuse.util.force.ForceSimulator;
import prefuse.util.ui.*;
import prefuse.visual.NodeItem;
import prefuse.visual.VisualGraph;
import prefuse.visual.VisualItem;
import prefuse.visual.EdgeItem;

/**
 * This demo is based on the prefuse.demos.applets.GraphView.java demo and shows
 * some simple uses of predicates and of the prefuse expression language. While
 * implementing I recognized that everything from this demo is already shown in
 * other demos, so you may get what you want by searching for 'expression' or 
 * 'predicate' within all files in the prefuse.demos folder. 
 * For example the process of creating and registering a new function or predicate
 * in the prefuse expression language and creating derived columns is also
 * shown in prefuse.demos.ZipDecode.java.
 * 
 * @author <a href="http://jheer.org">jeffrey heer</a>
 * @author Bj�rn Kruse
 */
public class GraphViewPredicatesDemo extends JPrefuseApplet {

    private static final String graph = "graph";
    private static final String nodes = "graph.nodes";
    private static final String edges = "graph.edges";
	private static LabelRenderer tr;
    private static EdgeRenderer m_edgeRenderer;
    private static EdgeRenderer bi_direction;
    private static  JTextField p3housesValue;
    private static  JTextField p3housesScore;
    private static  JButton p1button;
    //------------------------------------
    private static  JTextField target;
    private static  JTextField source;
    private static  JTextField both;
    //-----------------------------------
    private static  JLabel target_label;
    private static  JLabel source_label;
    private static  JLabel both_label;
    //-----------------------------------------
    static JPanel   container;
    //-----------------------------------------
    private static JLabel nodes_count;
    private static JLabel edges_count;
    //-----------------------------------------
    private static JTextField jTextField1;
    private static JTextField jTextField2;
    //-----------------------------------------
    static Display d;
    static Double default_Nodesise;
    //-----------------------------------------
    static int numb_edges;
    static int numb_Nodes;
    //--------------------------------------------
    final static TupleSet tset = new DefaultTupleSet();
    public void init() {
        UILib.setPlatformLookAndFeel();
       //JComponent graphview = demo("/Arabic_Text_Rank.xml", "name");
       // JComponent graphview = demo("/NetWork_Analyses.xml", "name");
       //JComponent graphview = demo("/Array5.xml", "name");
      //JComponent graphview = demo("Array_Twitter - Updated.xml", "name");

       JComponent graphview = demo("/test.xml", "name");
        
        this.getContentPane().add(graphview);
        this.setSize(new Dimension(800, 400));
    }

    public static JComponent demo(String datafile, String label) {
        Graph g = null;
        if ( datafile == null ) {
            g = GraphLib.getGrid(15,15);
        } else {
            try {
                g = new GraphMLReader().readGraph(datafile);
            } catch ( Exception e ) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        return demo(g, label);
    }

    
    public static JComponent demo(Graph g, final String label) {
    	/* 
    	 * some Predicates that will be used throughout the demo, especially the
    	 * filter predicate will be used from the checkboxes to color the nodes  
    	 */
        
        numb_Nodes=g.getNodeCount();
        numb_edges=g.getEdgeCount();
        final AndPredicate filter = new AndPredicate();
        final Predicate ageGreater24 = ExpressionParser.predicate("age > 24");
        final Predicate weightLess50 = ExpressionParser.predicate("weight < 50");
        final Predicate nameStartswithT = 
        	ExpressionParser.predicate("LEFT(name, 1) == 'T'");
       // char b='أ';

        final Predicate nameContainsA = 
        	ExpressionParser.predicate("POSITION('أ', name) != -1");

        // demonstrate the use of predicates to add new derived columns
    	g.addColumn("fullage", "age >= 18");
    	g.addColumn(VisualItem.LABEL, "CONCAT(name, '(', id, ')')");
    	g.addColumn("multiline", "CONCAT(name, '\\n', " +
    			"'is ', age, ' years old', '\\n', " +
    			"'and weights ', weight, ' kilos')");
    	
        // create a new, empty visualization for our data
        final Visualization vis = new Visualization();
        VisualGraph vg = vis.addGraph(graph, g);
        vis.setValue(edges, null, VisualItem.INTERACTIVE, Boolean.FALSE);

       
        TupleSet focusGroup = vis.getGroup(Visualization.FOCUS_ITEMS); 
        focusGroup.addTupleSetListener(new TupleSetListener() {
            public void tupleSetChanged(TupleSet ts, Tuple[] add, Tuple[] rem)
            {
                for ( int i=0; i<rem.length; ++i )
                    ((VisualItem)rem[i]).setFixed(false);
                for ( int i=0; i<add.length; ++i ) {
                    ((VisualItem)add[i]).setFixed(false);
                    ((VisualItem)add[i]).setFixed(true);
                }
                vis.run("draw");
            }
        });

       //Add decoration for edges
        DirectedGraphNeighborHighlightControl_Edges Edge_highlight=
        new DirectedGraphNeighborHighlightControl_Edges (vis);
        //----------------------------------------------------

        // set up the renderers
        tr = new LabelRenderer(label);
        tr.setRoundedCorner(8, 8);
        bi_direction=new EdgeRenderer(Constants.EDGE_TYPE_LINE, Constants.EDGE_ARROW_NONE);
        //vis.setRendererFactory(new DefaultRendererFactory(tr));
        DefaultRendererFactory rf = new DefaultRendererFactory(tr);
        Predicate biDirectional = new BiDirectionPredicate(g);
        //Decoration------------------
        rf.add(biDirectional, bi_direction);
        vis.setRendererFactory(rf);

        
        // -- set up the actions ----------------------------------------------
        
        int maxhops = 4, hops = 4;
       
        final GraphDistanceFilter gdf = new GraphDistanceFilter(graph, hops);
        
      //  final VisibilityFilter vs=new  VisibilityFilter ();

        ActionList draw = new ActionList();
        draw.add(gdf);
        draw.add(new ColorAction(nodes, VisualItem.FILLCOLOR, ColorLib.rgb(200,200,255)));
        draw.add(new ColorAction(nodes, VisualItem.STROKECOLOR, ColorLib.rgb(47 , 79 , 79)));
        draw.add(new ColorAction(nodes, VisualItem.TEXTCOLOR, ColorLib.rgb(0,0,0)));
        draw.add(new ColorAction(edges, VisualItem.FILLCOLOR, ColorLib.gray(0)));
        //-------------------------------------------------------------------------
        ColorAction edgeColor= new ColorAction(edges,
                VisualItem.STROKECOLOR, ColorLib.rgb(192 ,192, 192));
                edgeColor.add("ingroup('DGNHC_BOTHGROUP')", ColorLib.rgb(0,0,250));
                edgeColor.add("ingroup('DGNHC_SOURCEGROUP')", ColorLib.rgb(250,0,0));
                edgeColor.add("ingroup('DGNHC_TARGETGROUP') ", ColorLib.rgb(250,0,250));
                edgeColor.add("ingroup('_search_')", ColorLib.rgb(255,190,190));

     //  draw.add(edgeColor);
        
        Predicate highlight_and_filter = 
        	new AndPredicate(filter, ExpressionParser.predicate("_highlight"));

        
        
        /*
         * NOTE: the highlight_and_filter rule (rule is nothing else than a 
         * predicate) has to be added to the ColorAction BEFORE one of the rules
         * 'filter' or '_highlight' is added. The reason: Rules are evaluated in
         * the order in which they are added to the ColorAction, so earlier 
         * rules will have precedence over rules added later. See ColorAction
         * javadocs comment.
         * 
         */
        ColorAction fill = new ColorAction(nodes, 
                VisualItem.FILLCOLOR, ColorLib.rgb(200,200,255));
        fill.add("_fixed", ColorLib.rgb(255,100,100));
        fill.add(highlight_and_filter, ColorLib.rgb(200,155,255));
        fill.add("_highlight", ColorLib.rgb(255,200,125));
        fill.add("ingroup('_search_')", ColorLib.rgb(255,190,190));
        fill.add("ingroup('tset')", ColorLib.rgb(255,100,190));
        fill.add(filter, ColorLib.rgb(100,100,255));
        
        ForceDirectedLayout fdl = new ForceDirectedLayout(graph);
        ForceSimulator fsim = fdl.getForceSimulator();
        fsim.getForces()[0].setParameter(0, -1.2f);
        fsim.getForces()[2].setParameter(1, 100f);
        
        ActionList animate = new ActionList(Activity.INFINITY);
        //animate.add(fdl);
        //animate.add(fill);
        animate.add(edgeColor);
        animate.add(new RepaintAction());
        animate.add(new ForceDirectedLayout("graph", true));
        animate.add(new RepaintAction());
        
        // finally, we register our ActionList with the Visualization.
        // we can later execute our Actions by invoking a method on our
        // Visualization, using the name we've chosen below.
        vis.putAction("draw", draw);
        vis.putAction("layout", animate);
        vis.runAfter("draw", "layout");
        //------------------------------------------------------------------------
        
        
        
        // --------------------------------------------------------------------
        // STEP 4: set up a display to show the visualization
        
        Display display = new Display(vis);
        display.setSize(500,500);
        display.setForeground(Color.GRAY);
        display.setBackground(Color.WHITE);
       //ForceSimulator fsim = ((ForceDirectedLayout)animate.get(0)).getForceSimulator();
        //JForcePanel fpanel = new JForcePanel(fsim);
        
        // main display controls
        display.addControlListener(new FocusControl(1));
        display.addControlListener(new DragControl());
        display.addControlListener(new PanControl());
        display.addControlListener(new ZoomControl());
        display.addControlListener(new WheelZoomControl());
        display.addControlListener(new ZoomToFitControl());
        display.addControlListener(new NeighborHighlightControl());
        display.addControlListener(Edge_highlight);
        display.addControlListener(new ControlAdapter() {
        	@Override
        	public void itemClicked(VisualItem item, MouseEvent e) {
        		Display d = item.getVisualization().getDisplay(0);
        		//d.animatePanToAbs(new Point2D.Double(item.getX(), item.getY()), 10000);
        	}
        });


        display.setForeground(Color.GRAY);
        display.setBackground(Color.WHITE);
        //****************************************************

        final JFastLabel title = new JFastLabel("                 ");
        title.setPreferredSize(new Dimension(350, 40));
        title.setVerticalAlignment(SwingConstants.BOTTOM);
        title.setBorder(BorderFactory.createEmptyBorder(3,0,0,0));
        title.setFont(FontLib.getFont("Tahoma", Font.PLAIN, 12));
        //****************************************************
        display.addControlListener(new ControlAdapter() {
            
             
             JPopupMenu jpub = new JPopupMenu();
            public void itemEntered(VisualItem item, MouseEvent e) {
                     if ( item.canGetString(label) )

                    
                  
                   d = (Display)e.getSource();
                   d.setPreferredSize(new Dimension(350, 40));
                   if(item instanceof NodeItem)
                    {

              //   String InDegree = (String) item.get("InDegree");
                // String OutDegree = (String) item.get("OutDegree");
                   //JPopupMenu jpub = new JPopupMenu();
                   //------------------Moved to A class -----------------------
                   NodeItem temp=(NodeItem)item;
                   default_Nodesise=temp.getSize();
                    p3housesValue.setText(temp.getString("name"));
                    if(temp.canGetDouble("score"))

                      p3housesScore.setText(item.getString("score"));
                    
                    item.setSize(item.getDouble("score")*5.5);
                    if(temp.canGetDouble("score"))
                    d.setToolTipText("Score:"+item.getString("score"));
                    
                    Iterator iter2=temp.inEdges();
                    Iterator iter3=temp.outEdges();
                      while (iter2.hasNext()) 
                      {

                        EdgeItem edge = (EdgeItem) iter2.next();
                        NodeItem sourceNode=(NodeItem)edge.getSourceItem();
                        sourceNode.setSize(sourceNode.getDouble("score")*5.5);
                        Double val=edge.getDouble("strength");
                        System.out.println(val*20);
                        edge.setSize(val*10);
                    
                     }
                     //-----------------------------------------------
                      while (iter3.hasNext()) 
                      {

                        EdgeItem edge = (EdgeItem) iter3.next();
                        NodeItem TargetNode=(NodeItem)edge.getTargetItem();
                        TargetNode.setSize(TargetNode.getDouble("score")*5.5);
                        Double val=edge.getDouble("strength");
                        System.out.println(val*100);
                        edge.setSize(val*10);
                        
                    
                     }
                      
                  }
            }
             public void itemExited(VisualItem item, MouseEvent e) {
             title.setText(null);
             d.setToolTipText(null);
              if(item instanceof NodeItem)
                    {
                   NodeItem temp=(NodeItem)item;
                   temp.setSize(default_Nodesise);
                    Iterator iter_ex_1=temp.inEdges();
                    Iterator iter_ex_2=temp.outEdges();
                    
                     while (iter_ex_1.hasNext()) 
                      {

                        EdgeItem edge = (EdgeItem) iter_ex_1.next();
                        NodeItem sourceNode=(NodeItem)edge.getSourceItem();
                        sourceNode.setSize(default_Nodesise);
                      
                     }
                     while (iter_ex_2.hasNext()) 
                      {

                        EdgeItem edge = (EdgeItem) iter_ex_2.next();
                        NodeItem targetNode=(NodeItem)edge.getTargetItem();
                        targetNode.setSize(default_Nodesise);
                      
                     }                     
                    }

            }
        });

       
            

         //***************************************************
//         ForceSimulator fsim2 = ((ForceDirectedLayout)animate.get(0)).getForceSimulator();
  //      JForcePanel fpanel2 = new JForcePanel(fsim2);
        // --------------------------------------------------------------------        
        // STEP 5: launching the visualization
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        // create a panel for editing force values
        //first box
        final JValueSlider slider = new JValueSlider("Distance", 0, maxhops, hops);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gdf.setDistance(slider.getValue().intValue());
                vis.run("draw");
            }
        });

        //--------------------------------------------------------------------------------
        SearchTupleSet search = new PrefixSearchTupleSet();
        vis.addFocusGroup(Visualization.SEARCH_ITEMS, search);
        search.addTupleSetListener(new TupleSetListener() {
            public void tupleSetChanged(TupleSet t, Tuple[] add, Tuple[] rem) {
                vis.cancel("animatePaint");
                vis.run("recolor");
                vis.run("animatePaint");
            }
        });
        
        //---------------------------------------------------------------------------------
        
        SearchQueryBinding sq = new SearchQueryBinding(
             (Table)vis.getGroup(edges),"strength",
             (SearchTupleSet)vis.getGroup(Visualization.SEARCH_ITEMS));
        JSearchPanel search2 = sq.createSearchPanel();
        search2.setShowResultCount(true);
        search2.setBorder(BorderFactory.createEmptyBorder(5,5,4,0));
        search2.setFont(FontLib.getFont("Tahoma", Font.PLAIN, 11));
        //---------------------------------------------------------------------------------
         SearchQueryBinding sq_2 = new SearchQueryBinding(
             (Table)vis.getGroup(nodes),"score",
             (SearchTupleSet)vis.getGroup(Visualization.SEARCH_ITEMS));
        JSearchPanel search3 = sq_2.createSearchPanel();
        search3.setShowResultCount(true);
        search3.setBorder(BorderFactory.createEmptyBorder(5,5,4,0));
        search3.setFont(FontLib.getFont("Tahoma", Font.PLAIN, 14));
        //----------------------------------------------------------------------
        
        //----------------------------------------------------------------------
        RangeQueryBinding r=new RangeQueryBinding(
            (Table)vis.getGroup(nodes),"score");
        
        
     //   JSearchPanel search2 = sq.createSearchPanel();
        final AndPredicate filter_list = new AndPredicate(r.getPredicate());
       
         vis.addFocusGroup("tset", tset);
        UpdateListener lstnr = new UpdateListener() {
        public void update(Object src) {
        Iterator iter = vis.items(nodes,filter_list);
        tset.clear();
        while(iter.hasNext()){
        
            Tuple val=(Tuple)iter.next();
             System.out.println(val.toString());
          tset.addTuple(val);
         }
       
                     vis.run("repaint");
         }
        };
        ActionList update = new ActionList();
        update.add(new VisibilityFilter(nodes, filter_list));
       
        
        vis.putAction("update", update);
        
        UpdateListener lstnr2 = new UpdateListener() {
            public void update(Object src) {
                vis.run("update");
            }
        }; 
     filter_list.addExpressionListener(lstnr);
    // filter_list.addExpressionListener(lstnr2);
     
        r.createVerticalRangeSlider().setDoubleBuffered(true);
        JSlider search_5 = r.createSlider();
               // System.out.print(r.createVerticalRangeSlider().getHighValue());
                 System.out.print(r.createSlider().getLabelTable());
                 System.out.print(r.createSlider().getAlignmentY());
        
        //search2.setBorder(BorderFactory.createEmptyBorder(5,5,4,0));
       //search2.setFont(FontLib.getFont("Tahoma", Font.PLAIN, 11));

        
        //--------------------------------------------------------------------------------
    //    ForceSimulator fsim = ((ForceDirectedLayout)animate.get(0)).getForceSimulator();
   //     JForcePanel fpanel = new JForcePanel(fsim);
        slider.setBackground(Color.gray);
        slider.setPreferredSize(new Dimension(300,30));
        slider.setMaximumSize(new Dimension(300,30));
        JPanel p = new JPanel(new BorderLayout()); 
        p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
        
//        panel.add(fpanel2);
        panel.add( search_5);
        
        Box firstBox = new Box(BoxLayout.Y_AXIS);
        firstBox.setMaximumSize(new Dimension(300,30));
        firstBox.add(slider);
        firstBox.setBorder(BorderFactory.createTitledBorder("Connectivity Filter"));
        panel.add(firstBox);
        panel.add(Box.createVerticalStrut(5));
        //-------------------------------------------------------------------------
      //  panel.add(search2);
       // panel.add(search3);
         //------------------------------------------------------------------------
        JLabel p3labeltrSubs = new JLabel("Node:");
        p3labeltrSubs.setFont(new Font("Serif", Font.BOLD, 14));
       // p3labeltrSubs.setBounds(600,80,50,30);
        //------------------------------------------------------------------------
        JLabel score= new JLabel("Score:");
        score.setFont(new Font("Serif", Font.BOLD, 14));
        
        //------------------------------------------------------------------------
        source_label =new JLabel("In Ties");
        target_label =new JLabel("Out Ties");
        both_label=new JLabel("Bi Ties");
      //  p3labeltrSubs.setBounds(600,150,50,30);
        
         p3housesScore= new JTextField(30);
       p3housesScore.setBounds(140,12,50,0);
       p3housesScore.setEnabled(false);
     // p3housesScore.setBackground(Color.red);
        //------------------------------------------------------------------------
       

       p3housesValue= new JTextField(30);
       p3housesValue.setBounds(140,12,50,0);
       p3housesValue.setEnabled(false);
       
       
       //-----------------------------------------------------------------------
       target= new JTextField(30);
       target.setBounds(130,12,0,0);
       target.setEnabled(false);
       target.setBackground(Color.RED);
      // target.setText("In Ties");
       target.setFont(new Font("Serif", Font.BOLD, 14));
       
       //target.add(score);
       //-----------------------------------------------------------------------
       source= new JTextField(30);
       source.setBounds(140,12,0,0);
       source.setEnabled(false);
       source.setBackground(Color.pink);
       //source.setText("Out Ties");
       source.setFont(new Font("Serif", Font.BOLD, 14));
      //-------------------------------------------------------------------------
        //p1button.setBounds(140,12,50,0);
     //   p1button.setEnabled(false);
       //---------------------------------------------------------------------------
        Box FBox = new Box(BoxLayout.X_AXIS);
        FBox.setMaximumSize(new Dimension(150,100));
        FBox.setBorder(BorderFactory.createTitledBorder("Node Score"));
        FBox.add(score);
        //-------------------------------------------------------------------------
        both= new JTextField(30);
        both.setBounds(130,12,0,0);
        both.setEnabled(false);
        both.setBackground(Color.BLUE);
        both.setFont(new Font("Serif", Font.BOLD, 14));
       // both.setText("Bi Direction    ");
        //-------------------------------------------------------------------------
        Box legendBox = new Box(BoxLayout.X_AXIS);
        legendBox.setMaximumSize(new Dimension(3000,100));
        legendBox.setBorder(BorderFactory.createTitledBorder("Edges Legend"));
        //legendBox.add(target_label);
        //legendBox.add(source_label);
        //legendBox.add(both_label);
        
        //----------------------------------------------------------------------
        Box TargetBox = new Box(BoxLayout.Y_AXIS);
        TargetBox.setMaximumSize(new Dimension(300,100));
        TargetBox.setBorder(BorderFactory.createTitledBorder(""));
        TargetBox.add(target_label);
        TargetBox.add(target);
        //---------------------------------------------------------------------
        
       
        
        //----------------------------------------------------------------------
        Box SourcetBox = new Box(BoxLayout.Y_AXIS);
        SourcetBox.setMaximumSize(new Dimension(300,100));
        SourcetBox.setBorder(BorderFactory.createTitledBorder(""));
        TargetBox.add(source_label);
        TargetBox.add(source);
        
        //----------------------------------------------------------------------
       Box BothBox = new Box(BoxLayout.Y_AXIS);
        BothBox.setMaximumSize(new Dimension(300,100));
        BothBox.setBorder(BorderFactory.createTitledBorder(""));
        BothBox.add(both_label);
        BothBox.add(both);
        
         legendBox.add(TargetBox);
         legendBox.add(BothBox);
         legendBox.add(SourcetBox);
        
        //legendBox.add(both);
        //legendBox.add;
         //----------------------------------------------------------------------
         container=new JPanel();
         
         
        //------------------------------------------------------------------------
        nodes_count= new JLabel("Nodes # ");
        nodes_count.setFont(new Font("Serif", Font.BOLD, 14));
       jTextField1=new JTextField(7);
       jTextField1.setEnabled(false);
       jTextField1.setBounds(140,12,50,0);
       String val_nodes=String.valueOf(g.getNodeCount());
       jTextField1.setText(val_nodes);
       //-----------------------------------------------------------------------
       edges_count= new JLabel("Edges#");
       edges_count.setFont(new Font("Serif", Font.BOLD, 14));
       jTextField2=new JTextField(7);
       jTextField2.setEnabled(false);
       jTextField2.setBounds(140,12,50,0);
       //System.out.println("Edges#"+);
       String val_edges=String.valueOf(g.getEdgeCount());
       jTextField2.setText(val_edges);
       
        //-------------------------------------------------------------------------
        Box ThirdBox = new Box(BoxLayout.X_AXIS);
        ThirdBox.setMaximumSize(new Dimension(150,100));
        ThirdBox.setBorder(BorderFactory.createTitledBorder("Node Name"));
        ThirdBox.add(p3labeltrSubs);
        ThirdBox.add(p3housesValue);
        FBox.add(p3housesScore);
        panel.add(ThirdBox); 
        ThirdBox.add(Box.createVerticalStrut(5));
        panel.add(FBox);
        
        panel.add(Box.createVerticalStrut(20));
        panel.add(legendBox);
        panel.add(Box.createVerticalStrut(20));
        //-------------------------------------------------------------------------
        
        
        //second box
        JComboBox labelsCombobox = new JComboBox(new String[]{"normal", "extended", "multiline"});
        labelsCombobox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
		        String action = (String)cb.getSelectedItem();
		        if (action.equals("normal"))
		        	tr.setTextField("name");
		        if (action.equals("extended"))
		        	tr.setTextField(VisualItem.LABEL);
		        if (action.equals("multiline"));
		        	tr.setTextField("multiline");
			}
        });
        
        Box secondBox = new Box(BoxLayout.X_AXIS);
        secondBox.setMaximumSize(new Dimension(300,30));
        secondBox.add(new JLabel("Label:   "));
        secondBox.add(labelsCombobox);
        secondBox.setBorder(BorderFactory.createTitledBorder("Choose a label"));
       // panel.add(secondBox);
         panel.add(container);
        panel.add(Box.createVerticalStrut(5));
        
        //third box
        JCheckBox nameContainsCB = new JCheckBox("Name contains 'A'", false);
        JCheckBox nameStartswithCB = new JCheckBox("Name starts with 'T'", false);
        JCheckBox ageCB = new JCheckBox("Age greater than 24", false);
        JCheckBox weightCB = new JCheckBox("Weight smaller than 50", false);
        JCheckBox Legend = new JCheckBox("Target Edges", false);
        nameContainsCB.setBackground(Color.white);
        nameStartswithCB.setBackground(Color.white);
        ageCB.setBackground(Color.white);
        weightCB.setBackground(Color.white);
       // Legend.setBackground(Color.pink);

        nameContainsCB.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JCheckBox cb = (JCheckBox) e.getSource();
        		if (cb.isSelected()) filter.add(nameContainsA);
        		else filter.remove(nameContainsA);
        	}
        });
        nameStartswithCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JCheckBox cb = (JCheckBox) e.getSource();
				if (cb.isSelected()) filter.add(nameStartswithT);
				else filter.remove(nameStartswithT);
			}
        });
        ageCB.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JCheckBox cb = (JCheckBox) e.getSource();
        		if (cb.isSelected()) filter.add(ageGreater24);
        		else filter.remove(ageGreater24);
        	}
        });

        weightCB.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JCheckBox cb = (JCheckBox) e.getSource();
        		if (cb.isSelected()) filter.add(weightLess50);
        		else filter.remove(weightLess50);
        	}
        });
        
        Box checkboxesBox = new Box(BoxLayout.Y_AXIS);
        checkboxesBox.add(nameContainsCB);
        checkboxesBox.add(nameStartswithCB);
        checkboxesBox.add(ageCB);
        checkboxesBox.add(weightCB);
        
        Box thirdBox = new Box(BoxLayout.X_AXIS);
        thirdBox.setMaximumSize(new Dimension(300,30));
        thirdBox.add(checkboxesBox);
        thirdBox.setBorder(BorderFactory.createTitledBorder("Choose a restriction to highlight"));
       // panel.add(thirdBox);
        container.add(nodes_count);
        container.add(jTextField1);
        
        container.add(edges_count);
        container.add(jTextField2);
       
        //-----------------------------------------------------------------------
        panel.add(Box.createVerticalStrut(5));
        panel.add(Box.createVerticalGlue());
        //----------------------------------------
        
        // create a new JSplitPane to present the interface
        JSplitPane split = new JSplitPane();
        split.setLeftComponent(display);
        split.setRightComponent(panel);
        split.setOneTouchExpandable(true);
        split.setContinuousLayout(false);
        split.setDividerLocation(700);
        split.setBackground(Color.WHITE);
        
       // position and fix the default focus node
         NodeItem focus = (NodeItem)vg.getNode(0);
        PrefuseLib.setX(focus, null, 400);
        PrefuseLib.setY(focus, null, 250);
        focusGroup.setTuple(focus);

        // now we run our action list and return
        return split;
    }
    
    
} 
package prefuse.demos;

import java.awt.*;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.Iterator;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.ActionSwitch;
import prefuse.action.GroupAction;
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
import prefuse.data.Node;
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
import prefuse.util.ColorLib;
import prefuse.util.FontLib;
import prefuse.util.GraphLib;
import prefuse.util.PrefuseLib;
import prefuse.util.UpdateListener;
import prefuse.util.force.DragForce;
import prefuse.util.force.Force;
import prefuse.util.force.ForceSimulator;
import prefuse.util.force.NBodyForce;
import prefuse.util.force.SpringForce;
import static prefuse.util.force.SpringForce.DEFAULT_SPRING_COEFF;
import static prefuse.util.force.SpringForce.SPRING_LENGTH;
import prefuse.util.ui.*;
import prefuse.util.ui.JValueSlider;
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
public class GraphViewPredicatesDemo_last_Distance_Filter extends JPrefuseApplet {

    private static final String graph = "graph";
    private static final String nodes = "graph.nodes";
    private static final String edges = "graph.edges";
    private static final String linear="linear";
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
    private static JLabel nodeScore_Max;
    private static JLabel nodeScore_Min;
   //-----------------------------------------
    //-----------------------------------------
    private static JLabel edgeScore_Max;
    private static JLabel edgeScore_Min;
   //========================================= 
    private static JTextField jTextField1;
    private static JTextField jTextField2;
    //-----------------------------------------
    static Display d;
    static Display d_2;
    static Double default_NodesSize;
    //-----------------------------------------
   //tset is a focus group that used to represent filtered nodes.
    final static TupleSet tset = new DefaultTupleSet();
    final static TupleSet Edges_tset = new DefaultTupleSet();
    //-----------------------------------------
    static int numb_edges;
    static int numb_Nodes;
    
    @Override
    public void init()
  {
         final long startTime=System.nanoTime();
            //Set the file names of the data
         UILib.setPlatformLookAndFeel();
         //JComponent graphview = demo("/Arabic_Text_Rank.xml", "name");
         JComponent graphview = demo("/test.xml", "name");
        //JComponent graphview = demo("/NetWork_Analyses.xml", "name");
        //JComponent graphview = demo("/Array5.xml", "name");
      
        this.getContentPane().add(graphview);
        this.setSize(new Dimension(700,700));
        final long endTime=System.nanoTime();
        long duration=endTime-startTime;
            //double seconds = (double)(duration/ 1000000000);
            //Total=Total+duration;
        System.out.println("Total Time"+duration);
    }
 //==========================================================================
    static double  doubleFormat(double number)
    {
        if(number==0)
            return 0;
        else
        {
            DecimalFormat newDouble=new DecimalFormat("0.000");
            double newResult=Double.parseDouble(newDouble.format(number));
            return newResult;
        }
    }
    //==========================================================================
//----------------Slider of Force Simulator--------------------------
 public class FloatJSlider extends JSlider{

        private static final long serialVersionUID = 1L;
        static final float FLOAT_MINIMUM = 0.0f;
        static final float FLOAT_MAXIMUM = 100.0f;
        static final float FLOAT_MIDDLE = 50.0f;
        static final int PRECISION_MULTIPLIER  = 100;

        public FloatJSlider(){
                super();
                setFloatMinimum(FLOAT_MINIMUM);
                setFloatMaximum(FLOAT_MAXIMUM);
                setFloatValue(FLOAT_MIDDLE);
        }

        public FloatJSlider(float min, float max, float val){
                super();
                setFloatMinimum(min);
                setFloatMaximum(max);
                setFloatValue(val);
        }

        public float getFloatMaximum() {
                return( getMaximum()/FLOAT_MAXIMUM );
        }

        public float getFloatMinimum() {
                return( getMinimum()/FLOAT_MAXIMUM );
        }

        public float getFloatValue() {
                return( getValue()/FLOAT_MAXIMUM );
        }

        public void setFloatMaximum(float max) {
                setMaximum((int)(max*PRECISION_MULTIPLIER));
        }

        public void setFloatMinimum(float min) {
                setMinimum((int)(min*PRECISION_MULTIPLIER));
        }

        public void setFloatValue(float val) {
                setValue((int)(val*PRECISION_MULTIPLIER));
                setToolTipText(Float.toString(val));
        }

	}
//----------------Edge Actions--------------------------

public static class Edges_Action extends GroupAction {
        public Edges_Action(String graphGroup) {
            super(graphGroup);
        }
        public void run(double frac) 
	{
          
           TupleSet focus = m_vis.getGroup(Visualization.FOCUS_ITEMS);
           Iterator it = m_vis.items();

            while (it.hasNext())
			{

				VisualItem ed = (VisualItem) it.next();
				if(ed.isInGroup(nodes))
				{

				NodeItem n2 = (NodeItem)ed;

				
					if(!n2.isVisible())
					{
						Iterator e3 = n2.edges();
						while (e3.hasNext()) 
						{

						EdgeItem e4 = (EdgeItem) e3.next();

						if (e4.isVisible())
						{
						e4.setVisible(false);
						}
                     

						}
						if(n2.isVisible())
						{
							Iterator e4 = n2.edges();
							while (e4.hasNext()) 
							{

								EdgeItem e5 = (EdgeItem) e3.next();

								if (e5.isVisible())
								{
								e5.setVisible(true);

								}


							}
               
						}	
					}
				}
			}
		}
  }
//--------------------Edges_Restore(edges)-------------------------------------
 public static class Edges_Restore extends GroupAction
 {
        public Edges_Restore(String graphGroup)
        {
            super(graphGroup);
        }
        public void run(double frac)
		{
            //TupleSet focus = m_vis.getGroup(Visualization.FOCUS_ITEMS);
           TupleSet focus = m_vis.getGroup(Visualization.FOCUS_ITEMS);
           Iterator it = m_vis.items();

            while (it.hasNext())
			{

				VisualItem ed = (VisualItem) it.next();
				if(ed.isInGroup(edges))
				{
					EdgeItem n2 = (EdgeItem)ed;
					NodeItem sourceItem=(NodeItem)n2.getSourceItem();
					NodeItem TargetItem=(NodeItem)n2.getTargetItem();
					if(sourceItem.isVisible() && TargetItem.isVisible())
					{
                                            n2.setVisible(true);
                                            
					}
					else
					{
					n2.setVisible(false);
					}

				}
			}
		}
 }       

public static class nodes_check extends GroupAction
 {
        public nodes_check(String graphGroup)
        {
            super(graphGroup);
        }
        public void run(double frac)
		{
            
           TupleSet focus = m_vis.getGroup(Visualization.FOCUS_ITEMS);
           Iterator it = m_vis.items();

            while (it.hasNext()) 
			{

				VisualItem ed = (VisualItem) it.next();
				if(ed.isInGroup(edges))
				{
					EdgeItem n2 = (EdgeItem)ed;
					NodeItem sourceItem=(NodeItem)n2.getSourceItem();
					NodeItem TargetItem=(NodeItem)n2.getTargetItem();
					if(!sourceItem.isVisible() || !TargetItem.isVisible())
					{
					//System.out.println(sourceItem);
					//System.out.println(TargetItem);
					  n2.setVisible(false);
                                          
					}
            
				}
			}
		}
 }



    
//--------------------------------------------------------------------------
    public static JComponent demo(String datafile, String label) {
        Graph g = null;
        if ( datafile == null ) {
            g = GraphLib.getGrid(50,50);
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
        
       // demonstrate the use of predicates to add new derived columns
    	
        // create a new, empty visualization for our data
        final Visualization vis = new Visualization();
        VisualGraph vg = vis.addGraph(graph, g);
        //vis.setValue(edges, null, VisualItem.INTERACTIVE, Boolean.FALSE);
        //vis.setValue(edges, null, VisualItem.FIXED, Boolean.FALSE);
        //vis.setValue(nodes, null, VisualItem.INTERACTIVE, Boolean.TRUE);
        vis.addFocusGroup("tset",tset);
        vis.addFocusGroup("Edges_tset",Edges_tset);



        vis.addFocusGroup(linear, new DefaultTupleSet());
        TupleSet focusGroup = vis.getGroup(Visualization.FOCUS_ITEMS); 
      
        focusGroup.addTupleSetListener(new TupleSetListener() {
        public void tupleSetChanged(TupleSet ts, Tuple[] add, Tuple[] rem)
        {
               
                final long startTime=System.nanoTime();
               //-----------------------------------------------
                TupleSet linearInterp = vis.getGroup(linear);
                //TupleSet linearInterp = vis.getGroup("ALL_ITEMS");
                 
                 //  TupleSet linearInterp = m_vis.getSourceData(tree);

                    if ( add.length < 1 ) return;
                    linearInterp.clear();
                    /*for (  Node n = (Node)add[0]; n!=null; n=n.getParent() )
                    {
                        

                        linearInterp.addTuple(n);

                    }
                    * *
                    */
                    for ( int i=0; i<rem.length; ++i )
                    ((VisualItem)rem[i]).setFixed(false);
                    for ( int i=0; i<add.length; ++i ) {
                        ((VisualItem)add[i]).setFixed(false);
                        ((VisualItem)add[i]).setFixed(true);
                         final long endTime=System.nanoTime();  
                         long duration=endTime-startTime;
                        //double seconds = (double)(duration/ 1000000000);
                         //Total=Total+duration;
                          System.out.println("Total Time"+duration);
                    }
                    if ( ts.getTupleCount() == 0 )
                    {
                        ts.addTuple(rem[0]);
                        ((VisualItem)rem[0]).setFixed(false);
                    }
                   
               //-----------------------------------------------
                
               vis.run("draw");
             
            }
        });
        
       //Add decoration for edges
	   //-----------------------------------------------------------
	//Here the object of DirectedGraphNeighborHighlightControl_Edges1 with vis object.
        DirectedGraphNeighborHighlightControl_Edges Edge_highlight=
        new DirectedGraphNeighborHighlightControl_Edges (vis);
        //----------------------------------------------------

        // set up the renderers
        tr = new LabelRenderer(label);
        tr.setRoundedCorner(8, 8);
	//bi_direction object of type EdgeRender.
        bi_direction=new EdgeRenderer(Constants.EDGE_TYPE_LINE, Constants.EDGE_ARROW_NONE);
        //vis.setRendererFactory(new DefaultRendererFactory(tr));
        DefaultRendererFactory rf = new DefaultRendererFactory(tr);
        BiDirectionPredicate biDirectional = new BiDirectionPredicate(g);
        //======================= ============================
         //NumberFormat formatter = new DecimalFormat("#0.000000000000000000000000000000000000");
         //System.out.println("BiDirection Predicate Total Time="+biDirectional.getTotal());
        //===================================================
        
        //Decoration------------------
        rf.add(biDirectional, bi_direction);
        //rf.add(edges,new LabelRenderer("strength"));
        vis.setRendererFactory(rf);

        
        // -- set up the actions ----------------------------------------------
        
        int maxhops = 4, hops =4;
		//To show multiple graphs at the same time GraphDistanceFilter() should be commented
		final GraphDistanceFilter gdf = new GraphDistanceFilter(graph, hops);
        
	
	//----------------------------------------------------------------------------------
        ActionList draw = new ActionList();
	//Drwing GraphDistanceFilter() and filter them by depth
        //---------------------to show multiple graphs in the same window I commonted it-----------------------------------------------------
        draw.add(gdf);
		//Decoration of the nodes and edges-------------------------------------------------
        draw.add(new ColorAction(nodes, VisualItem.FILLCOLOR, ColorLib.rgb(200,200,255)));
        draw.add(new ColorAction(nodes, VisualItem.STROKECOLOR, ColorLib.rgb(47 , 79 , 79)));
        draw.add(new ColorAction(nodes, VisualItem.TEXTCOLOR, ColorLib.rgb(0,0,0)));
        draw.add(new ColorAction(edges, VisualItem.FILLCOLOR, ColorLib.gray(0)));
        //-------------------------------------------------------------------------
        //======================================================================
        ColorAction nArrow = new ColorAction(edges, VisualItem.FILLCOLOR,ColorLib.rgb(0 , 0, 0));
        //======================================================================
        ColorAction edgeColor= new ColorAction(edges,
                VisualItem.STROKECOLOR, ColorLib.rgb(192 ,192, 192));
        
        
        
	//-------------------------------------------------------------
	//Edge Color by groups
	//======================================================================
        Edge_highlight.addNeighborColorsToAction(edgeColor, ColorLib.rgb(255, 0, 0), ColorLib.rgb(255, 175, 175), ColorLib.rgb(0,0, 255));
        
        //======================================================================
        //edgeColor.add("ingroup('DGNHC_BOTHGROUP')", ColorLib.rgb(0,0,250));
        //edgeColor.add("ingroup('DGNHC_SOURCEGROUP')", ColorLib.rgb(250,0,0));
        edgeColor.add(biDirectional, ColorLib.rgb(0,0,250));
        edgeColor.add("ingroup('tset')", ColorLib.rgb(50,20,0));
         

        draw.add(edgeColor);
        draw.add(nArrow);
        //final AndPredicate filter = new AndPredicate();
        //Predicate highlight_and_filter = 
        //	new AndPredicate(filter, ExpressionParser.predicate("_highlight"));
        
        
        
        /*
         * NOTE: the highlight_and_filter rule (rule is nothing else than a 
         * predicate) has to be added to the ColorAction BEFORE one of the rules
         * 'filter' or '_highlight' is added. The reason: Rules are evaluated in
         * the order in which they are added to the ColorAction, so earlier 
         * rules will have precedence over rules added later. See ColorAction
         * javadocs comment.
         * 
         */
		 //Decoration results of the filters
        ColorAction fill = new ColorAction(nodes, 
                VisualItem.FILLCOLOR, ColorLib.rgb(200,200,255));
        fill.add("_fixed", ColorLib.rgb(255,100,100));
        //fill.add(highlight_and_filter, ColorLib.rgb(200,155,255));
        fill.add("_highlight", ColorLib.rgb(246,161,104));
        //fill.add("ingroup('_search_')", ColorLib.rgb(255,190,190));
        //fill.add("ingroup('tset')", ColorLib.rgb(255,0,0));
       // fill.add(filter, ColorLib.rgb(100,100,255));
        //-------------------------------------------------------------------
	//================================================================
       // GridLayout fd1=new GridLayout(graph);
        ForceDirectedLayout fdl = new ForceDirectedLayout(graph,true);
        ForceSimulator fsim = fdl.getForceSimulator();
        Force[] forces=fsim.getForces();
        SpringForce sf=(SpringForce) forces[2];
        sf.setParameter(SPRING_LENGTH, 42);
     
       fsim.getForces()[0].setParameter(0, -1.2f);
        fsim.getForces()[0].setParameter(0, -100.2f);
        fsim.getForces()[2].setParameter(1, 100f);
       fsim.getForces()[3].setParameter(1, 500f);
       ForceSimulator neFS = fdl.getForceSimulator();
       neFS.addForce(new NBodyForce());
       neFS.addForce(new DragForce());
       neFS.addForce(new SpringForce(DEFAULT_SPRING_COEFF,1));
       fdl.setForceSimulator(neFS);
        ActionList animate = new ActionList(Activity.INFINITY);
        animate.add(fdl);
        animate.add(fill);
        animate.add(edgeColor);
        animate.add(new RepaintAction());
        
        // finally, we register our ActionList with the Visualization.
        // we can later execute our Actions by invoking a method on our
        // Visualization, using the name we've chosen below.
        vis.putAction("draw", draw);
        vis.putAction("layout", animate);
        vis.runAfter("draw", "layout");
        
        
        
        // --------------------------------------------------------------------
        // STEP 4: set up a display to show the visualization
        
        Display display = new Display(vis);
        display.setSize(500,500);
        display.setForeground(Color.GRAY);
        display.setBackground(Color.WHITE);
       //ForceSimulator fsim = ((ForceDirectedLayout)animate.get(0)).getForceSimulator();
       //JForcePanel fpanel = new JForcePanel(fsim);
        
        // main display controls
        display.addControlListener(new FocusControl(40));
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
        		d.animatePanToAbs(new Point2D.Double(item.getX(), item.getY()), 100);
        	}
        });


        display.setForeground(Color.GRAY);
        display.setBackground(Color.WHITE);
        //****************************************************

        final JFastLabel title = new JFastLabel("                 ");
        title.setPreferredSize(new Dimension(350, 40));
        title.setVerticalAlignment(SwingConstants.BOTTOM);
        title.setBorder(BorderFactory.createEmptyBorder(3,0,0,0));
        title.setFont(FontLib.getFont("Tahoma", Font.PLAIN, 15));
        //****************************************************
        display.addControlListener(new ControlAdapter() 
	{
            
             
            JPopupMenu jpub = new JPopupMenu();
            @Override
            public void itemEntered(VisualItem item, MouseEvent e) 
            {
		if ( item.canGetString(label) )
                   d = (Display)e.getSource();
                   d.setPreferredSize(new Dimension(350, 40));
		  if(item instanceof NodeItem)
		  {

                    //String InDegree = (String) item.get("InDegree");
                    //String OutDegree = (String) item.get("OutDegree");
                   //JPopupMenu jpub = new JPopupMenu();
                   //------------------Moved to A class -----------------------
                   NodeItem temp=(NodeItem)item;
                   default_NodesSize=temp.getSize();
                    p3housesValue.setText(temp.getString("name"));
                    if(temp.canGetDouble("score"))

                      p3housesScore.setText(item.getString("score"));
                    
                    item.setSize(3.5);
                    if(temp.canGetDouble("score"))
                    d.setToolTipText("Score:"+item.getString("score"));
                    
                    Iterator iter2=temp.inEdges();
                    Iterator iter3=temp.outEdges();
                      while (iter2.hasNext()) 
                    {

                        EdgeItem edge = (EdgeItem) iter2.next();
                        NodeItem sourceNode=(NodeItem)edge.getSourceItem();
                      // sourceNode.setSize(sourceNode.getDouble("score")*5.5);
                        sourceNode.setSize(1.5);
                        Double val=edge.getDouble("strength");
                        System.out.println(val*10);
                        edge.setSize(val*10);
                    
                    }
                     //-----------------------------------------------
                      while (iter3.hasNext()) 
                    {
                        EdgeItem edge = (EdgeItem) iter3.next();
			NodeItem TargetNode=(NodeItem)edge.getTargetItem();
			//  TargetNode.setSize(TargetNode.getDouble("score")*5.5);
			TargetNode.setSize(1.5);
			Double val=edge.getDouble("strength");
			System.out.println(val*10);
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
		temp.setSize(default_NodesSize);
                Iterator iter_ex_1=temp.inEdges();
                Iterator iter_ex_2=temp.outEdges();
                    
                 while (iter_ex_1.hasNext()) 
		{

                    EdgeItem edge = (EdgeItem) iter_ex_1.next();
		    NodeItem sourceNode=(NodeItem)edge.getSourceItem();
		    sourceNode.setSize(default_NodesSize);
                      
		}
		while (iter_ex_2.hasNext()) 
                {

                        EdgeItem edge = (EdgeItem) iter_ex_2.next();
                        NodeItem targetNode=(NodeItem)edge.getTargetItem();
                        targetNode.setSize(default_NodesSize);
                      
                 }                     
               }

            }
         });

       
            

         //***************Force Simulator is apprroved by Prefuse********************************
        ForceSimulator fsim2 = ((ForceDirectedLayout)animate.get(0)).getForceSimulator();
        JForcePanel fpanel2 = new JForcePanel(fsim2);
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
        slider.setFocusTraversalKeysEnabled(true);
        //slider.setDropTarget(dt);
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
        
    
      
		//------------------SearchQueryBinding for searching for a node value by text field but it's diasabled.
        SearchQueryBinding sq_strength = new SearchQueryBinding(
             (Table)vis.getGroup(edges), "strength",
             (SearchTupleSet)vis.getGroup(Visualization.SEARCH_ITEMS));
        JSearchPanel search4 = sq_strength .createSearchPanel();
        search4.setShowResultCount(true);
        search4.setBorder(BorderFactory.createEmptyBorder(5,5,4,0));
        search4.setFont(FontLib.getFont("Tahoma", Font.PLAIN, 14));
		//--------------------------------------------------------------------------------
		/*RangeQueryBinding that let us creating a slider that is used for filtirng edges and nodes */
		//--------------------------------------------------------------------------------
        final RangeQueryBinding slider_scores=new RangeQueryBinding(
                (Table)vis.getGroup(nodes),
                "score"
                );
                
		//---------------------------------------------------------------------------------
        final RangeQueryBinding slider_strength=new RangeQueryBinding(
                (Table)vis.getGroup(edges),
                "strength"
                );
		
        //-------------Get the value of the slider prediction------------
        final AndPredicate slide= new AndPredicate(slider_scores.getPredicate());
        final AndPredicate slide_strength= new AndPredicate(slider_strength.getPredicate());

        //------------------------Fill the tupleset of Nodes Filter----------------------------
        UpdateListener lstnr = new UpdateListener()
        {
			public void update(Object src) {
                        double _nodeScore_Max=Double.parseDouble(slider_scores.getNumberModel().getHighValue().toString());
                        nodeScore_Max.setText(String.valueOf(doubleFormat(_nodeScore_Max)));  
                        //======================================================
                        double _nodeScore_Min=Double.parseDouble(slider_scores.getNumberModel().getLowValue().toString());
                        nodeScore_Min.setText(String.valueOf(doubleFormat(_nodeScore_Min)));
			Iterator iter = vis.items(nodes,slide);
			tset.clear();
			while(iter.hasNext())
			{
       
			tset.addTuple((Tuple)iter.next());
			}
             vis.run("repaint");
              
        }
       
      };
      //----------------Fill the tupleset of Edges Filter------------------------------------
      UpdateListener lstnr_strength = new UpdateListener()
      {
        public void update(Object src) {
        //======================================================================
       double _edgeScore_Max=Double.parseDouble(slider_strength.getNumberModel().getHighValue().toString());
      edgeScore_Max.setText(String.valueOf(doubleFormat(_edgeScore_Max)));
      double _edgeScore_Min=Double.parseDouble(slider_strength.getNumberModel().getLowValue().toString());
      edgeScore_Min.setText(String.valueOf(doubleFormat(_edgeScore_Min)));
        //======================================================================    
        Iterator iter = vis.items(edges,slide_strength);
        Edges_tset.clear();
        while(iter.hasNext())
        {

                Edges_tset.addTuple((Tuple)iter.next());
        }
             vis.run("repaint");

        }

      };
      //------------------------------------------------------------------------
      ActionSwitch update = new ActionSwitch();
      ActionSwitch Edge_Update = new ActionSwitch();
      ActionSwitch Edge_remover = new ActionSwitch();
      ActionSwitch edge_restore_action = new ActionSwitch();
      ActionSwitch edge_restore_action_edge_slider = new ActionSwitch();
      //------------------------------------------------------------------------
     UpdateListener lstnr2 = new UpdateListener() {
            public void update(Object src) {
                vis.run("update");
            }
        };
        
        
        UpdateListener lstnr3 = new UpdateListener() {
            public void update(Object src) {
                vis.run("Edge_action");
            }
        };
      //------------------------------------------------
        UpdateListener lstnr_edge = new UpdateListener() {
            public void update(Object src) {
                vis.run("Edges_update");
            }
        };

        UpdateListener edge_restore = new UpdateListener() {
            public void update(Object src) {
                vis.run("Edges_restore");
            }
        };
        UpdateListener edge_restore_2 = new UpdateListener() {
            public void update(Object src) {
                vis.run("Edges_restore_2");
            }
        };
      //------------Add listner to update the graph by an action---------
      slide_strength.addExpressionListener(lstnr_strength);
      slide_strength.addExpressionListener(lstnr_edge);
      //edge_restore_2 is used to prevent the  edges of hidden nodes to be  drawn.
      slide_strength.addExpressionListener(edge_restore_2);
	  //-----------------------Remove filtered Edges---------------------
      Edge_remover.add(new VisibilityFilter(edges,slide_strength));
      //------------------------------------------------------------
      slide.addExpressionListener(lstnr);
      slide.addExpressionListener(lstnr2);
      slide.addExpressionListener(lstnr3);
      slide.addExpressionListener(edge_restore);
      //-------------Remove filtered Edges-------------------------------
      update.add(new VisibilityFilter(nodes,slide));
      edge_restore_action.add(new Edges_Restore(edges));
	  //Restore the edges of the  filtered nodes. 
      edge_restore_action_edge_slider.add(new nodes_check(edges));

      update.add(new Edges_Action(nodes));
      vis.putAction("update", update);
      
      Edge_Update.add(new Edges_Action(nodes));
      vis.putAction("Edges_restore_2", edge_restore_action_edge_slider);
      //remove unchosen edges
      vis.putAction("Edge_action", Edge_Update);
      //Removing edges of invisible nodes
      vis.putAction("Edges_update", Edge_remover);
     //Resoring Edges of Nodes
     vis.putAction("Edges_restore", edge_restore_action );
     //vis.runAfter("Edges_restore","Edge_action");
     
    
      //------------------------------------------------------------------------

     //Range Slider Filter for range of Maximum and Minimum value of nodes scores.
      JRangeSlider slide2=slider_scores.createHorizontalRangeSlider();
     /* slide2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                slide2StateChange(evt);
            }

            private void slide2StateChange(ChangeEvent evt) {
                 JSlider source = (JSlider)evt.getSource();
                  if (!source.getValueIsAdjusting())
                  {
                     int fps = (int)source.getValue();
                     System.out.println(fps);
                  }    
            }
        });
      */
      //=======================================================================
      double _nodeScore_Max=Double.parseDouble(slider_scores.getNumberModel().getHighValue().toString());
      nodeScore_Max=new JLabel(String.valueOf(doubleFormat(_nodeScore_Max)));
      double _nodeScore_Min=Double.parseDouble(slider_scores.getNumberModel().getLowValue().toString());
      nodeScore_Min=new JLabel(String.valueOf(doubleFormat(_nodeScore_Min)));
      //slide2.paintComponent(null);
       
      //========================================================================
     //slide2.setModel(javax.swing.BoundedRangeModel brm);
     //Range Slider Filter for range of Maximum and Minimum value of edges strength.
      JRangeSlider slide3=slider_strength.createHorizontalRangeSlider();
      double _edgeScore_Max=Double.parseDouble(slider_strength.getNumberModel().getHighValue().toString());
      edgeScore_Max=new JLabel(String.valueOf(doubleFormat(_edgeScore_Max)));
      double _edgeScore_Min=Double.parseDouble(slider_strength.getNumberModel().getMinValue().toString());
      edgeScore_Min=new JLabel(String.valueOf(doubleFormat(_edgeScore_Min)));
     
    
    //---------------------------------------------------------------------------------
		//  ForceSimulator fsim = ((ForceDirectedLayout)animate.get(0)).getForceSimulator();
		//  JForcePanel fpanel = new JForcePanel(fsim);
		//-----------------Graph Distance Filter that's diasabled-----------------------
        slider.setBackground(Color.gray);
        slider.setPreferredSize(new Dimension(300,30));
        slider.setMaximumSize(new Dimension(300,30));
		//----------------the Main Panel------------------------
        JPanel p = new JPanel(new BorderLayout()); 
        p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
        //--------------Force Simulation Panel that's approved by prefuse-----------
        panel.add(fpanel2);
		//----------------Box of GDF------------------------------------------------
        Box firstBox = new Box(BoxLayout.Y_AXIS);
        firstBox.setMaximumSize(new Dimension(300,30));
        firstBox.add(slider);
		firstBox.setBorder(BorderFactory.createTitledBorder("Connectivity Filter"));
        panel.add(firstBox);
		panel.add(Box.createVerticalStrut(5));
        //-----------------------------Box of Nodes Score Filter------------------
        Box SearchBox = new Box(BoxLayout.X_AXIS);
        SearchBox.setMaximumSize(new Dimension(300,100));
        SearchBox.setBorder(BorderFactory.createTitledBorder("Nodes' Score Filter"));
        SearchBox.add(nodeScore_Min);
        SearchBox.add(slide2);
        SearchBox.add(nodeScore_Max);
        //-----------------------------Box of Edges Strength Filter------------------
        Box StrengthBox = new Box(BoxLayout.X_AXIS);
        StrengthBox.setMaximumSize(new Dimension(300,100));
        StrengthBox.setBorder(BorderFactory.createTitledBorder("Edges' Strength Search"));
        StrengthBox.add(edgeScore_Min);
        StrengthBox.add(slide3);
        StrengthBox.add(edgeScore_Max);        
        //----------------------Add the Boxes to the MainPanel------------------------
        panel.add(SearchBox);
        panel.add(StrengthBox);
        //----------------------Text Field to show the current Node-------------------------
        JLabel p3labeltrSubs = new JLabel("Node:");
        p3labeltrSubs.setFont(new Font("Serif", Font.BOLD, 14));
        //-----------------------Text Field to show the current Node's Score----------------------
        JLabel score= new JLabel("Score:");
        score.setFont(new Font("Serif", Font.BOLD, 14));
       //------------------------Add Legend--------------------------------
        source_label =new JLabel("Out Ties");
        target_label =new JLabel("In Ties");
        both_label=new JLabel("Bi Ties");
             
        p3housesScore= new JTextField(30);
		p3housesScore.setBounds(140,12,50,0);
		p3housesScore.setEnabled(false);
        //------------------------------------------------------------------------
       	p3housesValue= new JTextField(30);
		p3housesValue.setBounds(140,12,50,0);
		p3housesValue.setEnabled(false);
       //-----------------------------------------------------------------------
       target= new JTextField(30);
       target.setBounds(130,12,0,0);
       target.setEnabled(false);
       target.setBackground(Color.RED);
       target.setFont(new Font("Serif", Font.BOLD, 14));
       //-----------------------------------------------------------------------
       source= new JTextField(30);
       source.setBounds(140,12,0,0);
       source.setEnabled(false);
       source.setBackground(Color.pink);
       source.setFont(new Font("Serif", Font.BOLD, 14));
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
        //JCheckBox nameContainsCB = new JCheckBox("Name contains 'A'", false);
        //JCheckBox nameStartswithCB = new JCheckBox("Name starts with 'T'", false);
        //JCheckBox ageCB = new JCheckBox("Age greater than 24", false);
        //JCheckBox weightCB = new JCheckBox("Weight smaller than 50", false);
        //JCheckBox Legend = new JCheckBox("Target Edges", false);
        //nameContainsCB.setBackground(Color.white);
        //nameStartswithCB.setBackground(Color.white);
        //ageCB.setBackground(Color.white);
        //weightCB.setBackground(Color.white);
       //Legend.setBackground(Color.pink);
        /*
        nameContainsCB.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JCheckBox cb = (JCheckBox) e.getSource();
        		if (cb.isSelected()) filter.add(nameContainsA);
        		else filter.remove(nameContainsA);
        	}
        });
        nameStartswithCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				JCheckBox cb = (JCheckBox) e.getSource();
				if (cb.isSelected()) filter.add(nameStartswithT);
				else filter.remove(nameStartswithT);
			}
        });
        ageCB.addActionListener(new ActionListener()
		{
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
        */
        Box thirdBox = new Box(BoxLayout.X_AXIS);
        thirdBox.setMaximumSize(new Dimension(300,30));
        //thirdBox.add(checkboxesBox);
        thirdBox.setBorder(BorderFactory.createTitledBorder("Choose a restriction to highlight"));
       // panel.add(thirdBox);
        container.add(nodes_count);
        container.add(jTextField1);
        
        container.add(edges_count);
        container.add(jTextField2);
       
        //-----------------------------------------------------------------------
        panel.add(Box.createVerticalStrut(5));
        panel.add(Box.createVerticalGlue());
        //----------------------------------------------------------------------
        
        // create a new JSplitPane to present the interface
        JSplitPane split = new JSplitPane();
        split.setLeftComponent(display);
        split.setRightComponent(panel);
        split.setOneTouchExpandable(true);
        split.setContinuousLayout(true);
        split.setDividerLocation(700);
        split.setBackground(Color.GREEN);
        
       // position and fix the default focus node
        NodeItem focus = (NodeItem)vg.getNode(0);
        PrefuseLib.setX(focus, null, 400);
        PrefuseLib.setY(focus, null, 250);
        focusGroup.setTuple(focus);

        // now we run our action list and return
        return split;
    }
    
    
} 
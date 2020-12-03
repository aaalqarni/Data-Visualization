package prefuse;

//import .*;
import java.awt.event.MouseEvent;

import java.util.Iterator;



import javax.swing.JFrame;



import prefuse.Constants;

import prefuse.Display;

import prefuse.Visualization;

import prefuse.action.ActionList;

import prefuse.action.RepaintAction;

import prefuse.action.assignment.ColorAction;

import prefuse.action.layout.graph.ForceDirectedLayout;

import prefuse.activity.Activity;

import prefuse.controls.ControlAdapter;

import prefuse.controls.FocusControl;



import prefuse.controls.PanControl;

import prefuse.controls.ZoomControl;

import prefuse.data.Edge;

import prefuse.data.Graph;

import prefuse.data.Node;



import prefuse.data.Tuple;

import prefuse.data.expression.AbstractPredicate;



import prefuse.data.expression.Predicate;



import prefuse.data.tuple.TupleSet;



import prefuse.render.DefaultRendererFactory;

import prefuse.render.EdgeRenderer;



import prefuse.render.Renderer;

import prefuse.render.ShapeRenderer;

import prefuse.util.ColorLib;



import prefuse.visual.EdgeItem;

import prefuse.visual.NodeItem;

import prefuse.visual.VisualItem;



import prefuse.visual.expression.InGroupPredicate;



import java.util.HashMap;

import java.util.HashSet;



// A neighbor hightlight control for directed graphs 



public class DemoGraph26_Training {



    public static final String GRAPH = "graph";



    public static final String NODES = "graph.nodes";



    public static final String EDGES = "graph.edges";



    public static void main(String[] argv) {

	new DemoGraph26_Training();

    }



    public DemoGraph26_Training() {

	Demo dem = new Demo();



	JFrame frame = new JFrame("some rawish juice for directed graphs");



	frame.getContentPane().add(dem.getDisplay());

	frame.pack();

	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	frame.setVisible(true);



	dem.paintIt();



    }



    private class Demo {



	private Display view;



	private DemoVisualization visu;



	private Graph data;



	public Demo() {



	    data = new DemoGraph();



	    visu = new DemoVisualization(data);



	    view = new DemoDisplay(visu, visu.getNeighborHighlightControl());



	}



	public void paintIt() {

	    visu.run("layout");

	}



	public Display getDisplay() {

	    return view;

	}



	public Visualization getVisualization() {

	    return visu;

	}



	public Graph getGraph() {

	    return data;

	}

    }



    private class DemoDisplay extends Display {



	public DemoDisplay(Visualization vis,

		DirectedGraphNeighborHighlightControl dgNeighborHighlightControl) {

	    super(vis);



	    setSize(500, 500);

	    pan(250, 250); // move to the middle



	    setHighQuality(true);

	    addControlListener(new ZoomControl());

	    addControlListener(new PanControl());

	    addControlListener(new FocusControl(1));



	    // MAD - adding the neighbor highlight control to the display



	    addControlListener(dgNeighborHighlightControl);

	}

    }



    private class DemoVisualization extends Visualization {



	private DirectedGraphNeighborHighlightControl neighborHighLightControl;



	public DemoVisualization(Graph data) {



	    addGraph(GRAPH, data);



	    // MAD - the new neighbor highlight control



	    neighborHighLightControl = new DirectedGraphNeighborHighlightControl(

		    this);



	    // set up some renderers ------------------------------------



	    Renderer nodeR = new ShapeRenderer(20);

	    DefaultRendererFactory drf = new DefaultRendererFactory();

	    drf.setDefaultRenderer(nodeR);

	    setRendererFactory(drf);



	    EdgeRenderer edgeR = new EdgeRenderer(Constants.EDGE_TYPE_LINE,

		    Constants.EDGE_ARROW_FORWARD);

	    edgeR.setArrowHeadSize(8, 12);

	    edgeR.setDefaultLineWidth(1);

	    drf.setDefaultEdgeRenderer(edgeR);



	    EdgeRenderer edgeR2 = new EdgeRenderer(Constants.EDGE_TYPE_CURVE,

		    Constants.EDGE_ARROW_NONE);

	    edgeR2.setDefaultLineWidth(2);



	    Predicate biDirectional = new BiDirectionPredicate(data);
            Predicate targetPredicte= new TargetPredicate(data);



	    drf.add(biDirectional, edgeR2);



	    // set up colors --------------------------------------------



	    ColorAction nStroke = new ColorAction(NODES,

		    VisualItem.STROKECOLOR, ColorLib.rgb(200, 0, 0));

	    ColorAction nFill = new ColorAction(NODES, VisualItem.FILLCOLOR,

		    ColorLib.rgb(200, 200, 200));



	    // MAD - adding the neighbor highlight colors to the the action



	    neighborHighLightControl.addNeighborColorsToAction(nFill, ColorLib

		    .rgb(250, 0, 0), ColorLib.rgb(0, 250, 0), ColorLib.rgb(0,

		    0, 250));



	    ColorAction nEdges = new ColorAction(EDGES, VisualItem.STROKECOLOR,

		    ColorLib.rgb(0, 200, 0));


             neighborHighLightControl.addNeighborColorsToAction(nEdges, ColorLib

		    .rgb(0, 0, 0), ColorLib.rgb(255, 0, 0), ColorLib.rgb(0,

		    0, 250));
             
             
	    nEdges.add(biDirectional, ColorLib.rgb(0, 0, 200));
            nEdges.add(targetPredicte, ColorLib.rgb(200, 0, 0));



	    ColorAction nArrow = new ColorAction(EDGES, VisualItem.FILLCOLOR,

		    ColorLib.rgb(200, 0, 0));



	    // bundle the color actions --------------------------------



	    ActionList colors = new ActionList();

	    colors.add(nStroke);

	    colors.add(nFill);

	    colors.add(nEdges);



	    colors.add(nArrow);



	    // now create the main layout routine ----------------------



	    ActionList layout = new ActionList(Activity.INFINITY);

	    layout.add(colors);

	    layout.add(new ForceDirectedLayout(GRAPH, true));

	    layout.add(new RepaintAction());

	    putAction("layout", layout);



	}



	public DirectedGraphNeighborHighlightControl getNeighborHighlightControl()
        {

	    return neighborHighLightControl;

	}



    }



    private class DemoGraph extends Graph {

	public DemoGraph() {



	    super(true);



	    Node lastN1 = null;

	    Node lastN2 = null;

	    Node lastLastN2 = null;



	    for (int i = 1; i < 20; ++i) {

		Node n1 = addNode();

		Node n2 = addNode();

		Node n3 = addNode();
                
                Node n4 = addNode();
             //  Node n5=addNode();


		addEdge(n1, n2);
                 
                addEdge(n2, n1);

		addEdge(n1, n3);

		addEdge(n2, n3);

		addEdge(n3, n2);
                
                addEdge(n2, n4);
              //  addEdge(n2, n5);


		if (lastN1 != null) {

		    addEdge(lastN1, n1);



		}

		if (lastLastN2 != null) {

		    addEdge(lastLastN2, n3);



		}

		lastLastN2 = lastN2;

		lastN2 = n2;

		lastN1 = n1;

	    }



	}

    }

    

    // MAD - the brand new neighbor highlight control


//------------------------------------------------------------------------------
    private class DirectedGraphNeighborHighlightControl extends ControlAdapter {



	static final String SOURCEGROUPNAME = "DGNHC_SOURCEGROUP",

		TARGETGROUPNAME = "DGNHC_TARGETGROUP",

		BOTHGROUPNAME = "DGNHC_BOTHGROUP";



	TupleSet sourceTupleSet, targetTupleSet, bothTupleSet;



	public DirectedGraphNeighborHighlightControl(Visualization vis) {



	    try {

		vis.addFocusGroup(SOURCEGROUPNAME);

		vis.addFocusGroup(TARGETGROUPNAME);

		vis.addFocusGroup(BOTHGROUPNAME);

	    } catch (Exception e) {

		System.out

			.println("Problems over problems while adding focus groups to visualization "

				+ e.getMessage());

	    }



	    sourceTupleSet = vis.getFocusGroup(SOURCEGROUPNAME);

	    targetTupleSet = vis.getFocusGroup(TARGETGROUPNAME);

	    bothTupleSet = vis.getFocusGroup(BOTHGROUPNAME);

	}



	public void addNeighborColorsToAction(ColorAction fill,

		int colorSource, int colorTarget, int colorBoth) {

	    fill.add(new InGroupPredicate(SOURCEGROUPNAME), colorSource);

	    fill.add(new InGroupPredicate(TARGETGROUPNAME), colorTarget);

	    fill.add(new InGroupPredicate(BOTHGROUPNAME), colorBoth);

	}



	public void itemEntered(VisualItem item, MouseEvent e) {

	    if (item instanceof NodeItem)

		setTupleSets((NodeItem) item);

	}



	public void itemExited(VisualItem item, MouseEvent e) {

	    if (item instanceof NodeItem) {

		sourceTupleSet.clear();

		targetTupleSet.clear();

		bothTupleSet.clear();

	    }

	}



	protected void setTupleSets(NodeItem centerNode) {



	    HashSet source = new HashSet();

	    HashSet target = new HashSet();

	    HashSet both = new HashSet();



	    Iterator iter1 = centerNode.inEdges();

	    while (iter1.hasNext()) {

		EdgeItem edge = (EdgeItem) iter1.next();

		//NodeItem sourceNode = edge.getSourceItem();

		source.add(edge);

	    }



	    Iterator iter2 = centerNode.outEdges();
                     //iter1 = centerNode.inEdges();
                   
	    while (iter2.hasNext()) {
                
		EdgeItem edge = (EdgeItem) iter2.next();
             
                    NodeItem targetNode = edge.getTargetItem();
                    
               
                
              //  EdgeItem temp= 
 //-----------------------------------------------------------------------------               
               //Node s = data.getNodeFromKey(tpl.getInt(data.getEdgeSourceField()));
               // int temp= targetNode.
               // NodeItem sourceNode =edge.getSourceItem();
//                Node s=edge.getGraph().getNode(
//                targetNode.getSourceTuple().getInt(edge.getGraph().getEdgeSourceField()));
              // System.out.printlin() 
//------------------------------------------------------------------------------
		//if (source.contains(edge))
              /* if(targetNode.equals(sourceNode) )
                {
                    System.out.println(edge);
                    System.out.println(edge_in);
                    
		    both.add(edge);
                    source.remove(edge_in);

		}
                * 
                */
               //else {
                   System.out.println("Target::"+edge);
                          
		    target.add(edge);

		//}
              }
               
            Iterator iterSource = source.iterator(); 
            Iterator iterTarget = target.iterator();
            Iterator nItr=centerNode.outEdges();
            while(nItr.hasNext() ){
                iterTarget = target.iterator();
               EdgeItem Itr=(EdgeItem)nItr.next();  
            while(iterTarget.hasNext())
            { EdgeItem in_Edge=(EdgeItem)iterTarget.next();
                NodeItem sourceNode =in_Edge.getTargetItem();
                NodeItem targetNode=Itr.getSourceItem();
                if(targetNode.equals(sourceNode) )
                {
                    System.out.println(in_Edge);
                   // System.out.println(out_Edge);
                    
		    both.add(in_Edge);
                    both.add(Itr);
                    //source.remove(in_Edge);

		}
                //EdgeItem anEdge=(EdgeItem)nItr.
                
            }
            }
            //-------------------------------------------------------------
             //iterSource = source.iterator(); 
                //iterTarget = target.iterator();
                nItr=centerNode.edges();
            while(nItr.hasNext() ){
               EdgeItem Itr=(EdgeItem)nItr.next(); 
                iterSource = source.iterator();
            while(iterSource.hasNext())
            {  EdgeItem in_Edge=(EdgeItem)iterSource.next();
               
                NodeItem sourceNode =in_Edge.getSourceItem();
                NodeItem targetNode=Itr.getTargetItem();
                if(targetNode.equals(sourceNode) )
                {
                    System.out.println(in_Edge);
                   // System.out.println(out_Edge);
                    
		    both.add(in_Edge);
                    both.add(Itr);
                    //source.remove(in_Edge);

		}
                //EdgeItem anEdge=(EdgeItem)nItr.
                
            }
            }
            
            
            
            //-------------------------------------------------------------
            
            
	   /* 
            while(iterSource.hasNext() )
            {
                EdgeItem in_Edge=(EdgeItem)iterSource.next();
                while(iterTarget.hasNext()){
                EdgeItem out_Edge=(EdgeItem)iterTarget.next();
                NodeItem sourceNode =in_Edge.getSourceItem();
                NodeItem targetNode=out_Edge.getTargetItem();
                if(targetNode.equals(sourceNode) )
                {
                    System.out.println(in_Edge);
                    System.out.println(out_Edge);
                    
		    both.add(in_Edge);
                    both.add(out_Edge);
                    //source.remove(in_Edge);

		}
                
                }
            }    
	   
            iterSource = source.iterator(); 
          iterTarget = target.iterator();
          //----------------------------------------
            while(iterTarget.hasNext())
            {  EdgeItem out_Edge=(EdgeItem)iterTarget.next();
               
                //while(iterTarget.hasNext()){
                  while(iterSource.hasNext() ){
                EdgeItem in_Edge=(EdgeItem)iterSource.next();
                NodeItem sourceNode =in_Edge.getSourceItem();
                NodeItem targetNode=out_Edge.getTargetItem();
                if(targetNode.equals(sourceNode) )
                {
                    //System.out.println(edge);
                    //System.out.println(edge_in);
                    
		    both.add(in_Edge);
                    both.add(out_Edge);
                    //source.remove(in_Edge);

		}
                
                }
            }    
          //----------------------------------------
            */
            // both.removeAll(both);
             target.removeAll(both);
             source.removeAll(both);


	   
            iterSource = source.iterator();
	    while (iterSource.hasNext()) {

		sourceTupleSet.addTuple( (EdgeItem)iterSource.next());

	    }

	    iterTarget = target.iterator();
             //
            int i=0;
            int i_t=0;
	    while (iterTarget.hasNext()) {
               i++;
               
                 System.out.println("Target");
		targetTupleSet.addTuple((EdgeItem) iterTarget.next());

	    }
            System.out.println("Target::"+i);
//------------------------------------------------------------------------------
	    Iterator iterBoth = both.iterator();

	    while (iterBoth.hasNext()) {
                 i_t++;
                
		bothTupleSet.addTuple((EdgeItem) iterBoth.next());
//-----------------------------------------------------------------------------                

	    }
             System.out.println("BoTh::"+i_t);

	}

    }


//------------------------------------------------------------------------------
    private class BiDirectionPredicate extends AbstractPredicate {



	private Graph data;



	private HashMap<Tuple, Boolean> memory; // remove the memory if your

                                                // precious graph is dynamic



	public BiDirectionPredicate(Graph g) {

	    data = g;

	    memory = new HashMap<Tuple, Boolean>();

	}



	public boolean getBoolean(Tuple tpl) {



	    if (!(tpl instanceof Edge)) {

		return false;

	    }

	    if (memory.containsKey(tpl)) {

		return memory.get(tpl);

	    }



	    Node s = data.getNodeFromKey(tpl.getInt(data.getEdgeSourceField()));

	    Node t = data.getNodeFromKey(tpl.getInt(data.getEdgeTargetField()));



	    boolean biDirectional = false;



	    Iterator it = data.edges(s);



	    while (it.hasNext()) {

		Edge aEdge = (Edge) it.next();

		if (aEdge.getSourceNode() == t)

		    biDirectional = true;

	    }

	    memory.put(tpl, biDirectional);

	    return biDirectional;

	}



    }
    
    
     private class TargetPredicate extends AbstractPredicate {



	private Graph data;



	private HashMap<Tuple, Boolean> memory; // remove the memory if your

                                                // precious graph is dynamic



	public TargetPredicate(Graph g) {

	    data = g;

	    memory = new HashMap<Tuple, Boolean>();

	}



	public boolean getBoolean(Tuple tpl) {



	    if (!(tpl instanceof Edge)) {

		return false;

	    }

	    if (memory.containsKey(tpl)) {

		return memory.get(tpl);

	    }



	    Node s = data.getNodeFromKey(tpl.getInt(data.getEdgeSourceField()));
            System.out.println(tpl.getInt(data.getEdgeSourceField())+"::"
                    +tpl.getInt(data.getEdgeTargetField()));
            
                    
	    Node t = data.getNodeFromKey(tpl.getInt(data.getEdgeTargetField()));
            



	    boolean targetPredicate = false;



	    Iterator it = data.edges(s);

                  int i=0;

	    while (it.hasNext()) {

		Edge aEdge = (Edge)it.next();
                System.out.println(i++);
                //Edge temp=(Edge) a
                    
		if (aEdge.getTargetNode()!= t &&!targetPredicate )

		    targetPredicate = false;

	    }
          

	    memory.put(tpl, targetPredicate);

	    return targetPredicate;

	}



    }

}

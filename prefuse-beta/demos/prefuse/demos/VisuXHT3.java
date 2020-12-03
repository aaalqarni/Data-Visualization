package prefuse.demos;

import java.awt.Color;

import java.awt.event.MouseEvent;

import java.awt.event.WindowAdapter;

import java.awt.event.WindowEvent;

import java.util.HashSet;

import java.util.Iterator;



import javax.swing.JFrame;

import javax.swing.JPanel;

import prefuse.Display;

import prefuse.Visualization;

import prefuse.action.Action;

import prefuse.action.ActionList;

import prefuse.action.RepaintAction;

import prefuse.action.assignment.ColorAction;

import prefuse.action.assignment.DataSizeAction;

import prefuse.action.layout.Layout;

import prefuse.action.layout.SpecifiedLayout;

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

import prefuse.data.io.GraphMLReader;

import prefuse.data.tuple.TupleSet;

import prefuse.demos.*;

import prefuse.render.DefaultRendererFactory;

import prefuse.render.LabelRenderer;

import prefuse.util.ColorLib;

import prefuse.visual.EdgeItem;

import prefuse.visual.NodeItem;

import prefuse.visual.VisualItem;

import prefuse.visual.expression.InGroupPredicate;



@SuppressWarnings("serial")

public class VisuXHT3 extends JPanel {



    private static final String graph = "graph";

    private static final String nodes = "graph.nodes";

    private static final String edges = "graph.edges";

    private static final String[] neighborGroups = { "ParentNode", "ChildNode","ParentEdges","ChildEdges" };

    private Visualization m_vis;

    

    public VisuXHT3(Graph g, String label) {

        m_vis = new Visualization();

        

        LabelRenderer tr = new LabelRenderer(label);

        tr.setRoundedCorner(20, 20);

        m_vis.setRendererFactory(new DefaultRendererFactory(tr));



        m_vis.addGraph(graph, g);



        ColorAction fill = new ColorAction(nodes, VisualItem.FILLCOLOR, ColorLib.rgb(200,200,255));

        fill.add(VisualItem.HIGHLIGHT, ColorLib.rgb(255,200,125));

        

        

        

        ActionList coloring = new ActionList();

        coloring.add(fill);

        coloring.add(new ColorAction(nodes, VisualItem.TEXTCOLOR, ColorLib.rgb(0,0,0)));

        coloring.add(new ColorAction(edges, VisualItem.FILLCOLOR, ColorLib.rgb(0,0, 0)));

        coloring.add(new ColorAction(edges, VisualItem.STROKECOLOR, ColorLib.gray(200)));

        



          

        

        Layout specifiedLayout = new SpecifiedLayout ("graph.nodes", "x-coord", "y-coord");

             

        ActionList draw = new ActionList(Activity.INFINITY);  

      

        draw.add(coloring);  

        draw.add(specifiedLayout);  

        draw.add(new RepaintAction());  

        m_vis.putAction("draw", draw);  

        

        

        Display display = new Display(m_vis);

        display.setSize(700,700);

        display.pan(-100,20);

     

        display.setForeground(Color.GRAY);

        display.setBackground(Color.WHITE);

        

     

        display.addControlListener(new FocusControl(1));

        display.addControlListener(new DragControl());

        display.addControlListener(new PanControl());

        display.addControlListener(new ZoomControl());

        display.addControlListener(new WheelZoomControl());

        display.addControlListener(new ZoomToFitControl());

        display.addControlListener(new NeighborHighlightControlForDirectedGraphs(m_vis,neighborGroups));

    	

        m_vis.setInteractive(edges, null, true);  

        m_vis.run("draw");

        fill.add(new InGroupPredicate(neighborGroups[0]), ColorLib.rgb(0, 0,250));

        fill.add(new InGroupPredicate(neighborGroups[1]), ColorLib.rgb(0, 250,0));

        fill.add(new InGroupPredicate(neighborGroups[2]), ColorLib.rgb(250,0,0));

        fill.add(new InGroupPredicate(neighborGroups[3]), ColorLib.rgb(250,250,0));

        add(display);

    }

    

    public static void main(String[] args) {

        Graph g = null;

        try {

        	//g = new GraphMLReader().readGraph("XHypertree4.xml");
                g = new GraphMLReader().readGraph("Array.xml");
        	

        } catch ( Exception e ) {

        	e.printStackTrace();

        	System.exit(1);

        }

        final VisuXHT3 view = new VisuXHT3(g, "name");

        JFrame frame = new JFrame("prefuse  |  specifiedLayout and DataSizeAction demo");

        frame.setContentPane(view);

        frame.pack();

        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   

       

       

    }

    public class NeighborHighlightControlForDirectedGraphs extends ControlAdapter {

    	

    	private Visualization visu;

    	

    	String ParentNodeGroupName, ChildNodeGroupName, ParentEdgesGroupName, ChildEdgesGroupName;

    	

    	TupleSet ParentNodeTupleSet, ChildNodeTupleSet, ParentEdgesTupleSet, ChildEdgesTupleSet;

    	

    	public NeighborHighlightControlForDirectedGraphs(Visualization vis, String[] groupNames) {

    	    visu = vis;

    	    

    	    ParentNodeGroupName = groupNames[0];

    	    ChildNodeGroupName = groupNames[1];

    	    ParentEdgesGroupName = groupNames[2];

    	    ChildEdgesGroupName = groupNames[3];

    

    	    

    	    try {

    		visu.addFocusGroup(ParentNodeGroupName);

    		visu.addFocusGroup(ChildNodeGroupName);

    		visu.addFocusGroup(ChildEdgesGroupName);

    		visu.addFocusGroup(ParentEdgesGroupName);

    		

    	

    	    } catch (Exception e) {

    		System.out.println("Problems over problems while adding foucs groups to visualization " + e.getMessage());

    	    }

    	    

    	    ParentNodeTupleSet = visu.getFocusGroup(ParentNodeGroupName);

    	    ChildNodeTupleSet = visu.getFocusGroup(ChildNodeGroupName);

    	    ParentEdgesTupleSet = visu.getFocusGroup(ParentEdgesGroupName);

    	    ChildEdgesTupleSet = visu.getFocusGroup(ChildEdgesGroupName);

       	}

    	

        @Override
    	public void itemEntered(VisualItem item, MouseEvent e) {

    	    if (item instanceof NodeItem) 

    	    {

    	    setChildHighlight((NodeItem) item);

    	    setParentHighlight((NodeItem) item); 

    	    }

    	}

    	

    	public void itemExited(VisualItem item, MouseEvent e) {

    	    if (item instanceof NodeItem) {



    	    	ParentNodeTupleSet.clear();

        		ChildNodeTupleSet.clear();

        		ParentEdgesTupleSet.clear();

        		ChildEdgesTupleSet.clear();

    		}

    	}

    	

    	protected void setParentHighlight(NodeItem centerNode) {

    	    

    	    HashSet source = new HashSet();

    	    HashSet ParentEdges = new HashSet();

    	    Iterator iterInEdges = centerNode.inEdges();

    	    while (iterInEdges.hasNext()) {

    		EdgeItem edge = (EdgeItem) iterInEdges.next();

    		NodeItem sourceNode = edge.getSourceItem();

    		source.add(sourceNode);

    	ParentEdges.add(edge);

    		setParentHighlight(edge.getSourceItem());

    		

    	    }

    	    



    	    Iterator iterSource = source.iterator();

    	    while (iterSource.hasNext()){

   	    	ParentNodeTupleSet.addTuple((NodeItem)iterSource.next());

   	    	}

    	    

    	    Iterator iterEdges = ParentEdges.iterator();

    	    while (iterEdges.hasNext()){

   	    	ParentEdgesTupleSet.addTuple((EdgeItem)iterEdges.next());

    	    }

    	    } 	    

    	   

    	



    	protected void setChildHighlight(NodeItem centerNode) {

    	    

    	    HashSet Child = new HashSet();

    	    HashSet ChildEdges = new HashSet();

    	    Iterator iterOutEdges = centerNode.outEdges();

    	    while (iterOutEdges.hasNext()) {

    		EdgeItem edge = (EdgeItem) iterOutEdges.next();

    		NodeItem ChildNode = edge.getTargetItem();

    		setChildHighlight(edge.getTargetItem());

    		Child.add(ChildNode);

    		ChildEdges.add(edge);

    		

    	    }

    	        	    



    	    Iterator iterChild = Child.iterator();

    	    while (iterChild.hasNext())

    		ChildNodeTupleSet.addTuple((NodeItem)iterChild.next());

    	    Iterator iterEdges = ChildEdges.iterator();

    	    while (iterEdges.hasNext())

    		ChildEdgesTupleSet.addTuple((EdgeItem)iterEdges.next());

    	    

    	   

    	}

    	

    }

        

    

}

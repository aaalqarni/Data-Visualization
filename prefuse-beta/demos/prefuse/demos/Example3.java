package prefuse.demos;



import java.util.Random;

import javax.swing.JFrame;

import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.ColorAction;
import prefuse.action.layout.RandomLayout;
import prefuse.action.layout.graph.ForceDirectedLayout;
import prefuse.activity.Activity;
import prefuse.controls.DragControl;
import prefuse.controls.PanControl;
import prefuse.controls.ZoomControl;
import prefuse.data.Graph;
import prefuse.data.Node;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.ShapeRenderer;
import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;

public class Example3{

	private static Graph graph;
	private static Visualization vis;
	private static Display d;	
	
    public static void main(String[] argv)
	{
        setUpData();
		setUpVisualization();
		//setUpRenderers();
		setUpActions();
		setUpDisplay();
		
		// launch the visualization -------------------------------------
        
        // The following is standard java.awt.
        // A JFrame is the basic window element in awt. 
        // It has a menu (minimize, maximize, close) and can hold
        // other gui elements. 
        
        // Create a new window to hold the visualization.  
        // We pass the text value to be displayed in the menubar to the constructor.
        JFrame frame = new JFrame("prefuse example");
        
        // Ensure application exits when window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // The Display object (d) is a subclass of JComponent, which
        // can be added to JFrames with the add method.
        frame.add(d);
        
        // Prepares the window.
        frame.pack();           
        
        // Shows the window.
        frame.setVisible(true); 
        
        // We have to start the ActionLists that we added to the visualization
        vis.run("color");
        vis.run("layout");
		
	}
	
    public static void setUpData()
	{
    	// Here we are manually creating the data structures.  500 nodes are
    	// added to the Graph structure.  500 edges are made randomly 
    	// between the nodes.
		graph = new Graph();
        
    	// For this example, we will add a little bit more
        // information to the graph.  
        // We can add data columns (recall that the graph
        // is backed by a table).
        
        // Add columns for gender, age, job, and id.  
		// The second parameter is for the type of 
		// data that will be stored in the table.
        graph.addColumn("gender", Integer.class);
        graph.addColumn("age", Integer.class);
        graph.addColumn("job", String.class);
        graph.addColumn("id", Integer.class);

        // The set of jobs that our population will randomly pull from.
        String[] jobs = {"Teacher", "Plumber", "Student", "Software Engineer"};
        
        // A random number generator.
        Random rand = new Random();
        
        // Now we set the data values as we randomly create the nodes. 
        for (int i = 0; i < 150; i++)
        {
        	Node n = graph.addNode();
        	n.set("job", jobs[rand.nextInt(4)]);
        	n.set("gender", rand.nextInt(2));
        	n.set("age", rand.nextInt(46) + 20); 
        	n.set("id", i);
        }
        
        // We'll leave the random connections. 
        for(int i = 0; i < 150; i++)
        {
        	int first = rand.nextInt(150);
        	int second = rand.nextInt(150);
        	graph.addEdge(first, second);
        }
        
	}
	
	public static void setUpVisualization()
	{
        // -- 2. the visualization --------------------------------------------
        // We must first creat the Visualization object.
		vis = new Visualization();
        
        // Now we add our previously created Graph object to the visualization.
        // The graph gets a textual label so that we can refer to it later on.
        vis.add("graph", graph);
	}
     
	public static void setUpRenderers()
	{
        // -- 3. the renderers and renderer factory ---------------------------
        
        // Create a default ShapeRenderer
        FinalRenderer r = new FinalRenderer();
        
        // create a new DefaultRendererFactory
        // This Factory will use the ShapeRenderer for all nodes.
        vis.setRendererFactory(new DefaultRendererFactory(r));
	}
	
	public static void setUpActions()
	{
        // -- 4. the processing actions ---------------------------------------
        
        // We must color the nodes of the graph.  
        // Notice that we refer to the nodes using the text label for the graph,
        // and then appending ".nodes".  The same will work for ".edges" when we
        // only want to access those items.
        // The ColorAction must know what to color, what aspect of those 
        // items to color, and the color that should be used.
        ColorAction fill = new ColorAction("graph.nodes", VisualItem.FILLCOLOR, ColorLib.rgb(0, 200, 0));
       
        // Similarly to the node coloring, we use a ColorAction for the 
        // edges
        ColorAction edges = new ColorAction("graph.edges", VisualItem.STROKECOLOR, ColorLib.gray(200));
        
        // Create an action list containing all color assignments
        // ActionLists are used for actions that will be executed
        // at the same time.  
        ActionList color = new ActionList();
        color.add(fill);
        color.add(edges);
        
        // The layout ActionList is constantly run to recalculate 
        // the positions of the nodes.
        ActionList layout = new ActionList(Activity.INFINITY);
        
        // We add the layout to the layout ActionList, and tell it
        // to operate on the "graph".
        layout.add(new ForceDirectedLayout("graph", true));
        
        // We add a RepaintAction so that every time the layout is 
        // changed, the Visualization updates it's screen.
        layout.add(new RepaintAction());
        
        // add the actions to the visualization
        vis.putAction("color", color);
        vis.putAction("layout", layout);
	}
        
	public static void setUpDisplay()
	{
        // -- 5. the display and interactive controls -------------------------
        
        // Create the Display object, and pass it the visualization that it 
        // will hold.
		d = new Display(vis);
        
        // Set the size of the display.
        d.setSize(720, 500); 
        
        // We use the addControlListener method to set up interaction.
        
        // The DragControl is a built in class for manually moving
        // nodes with the mouse. 
        d.addControlListener(new DragControl());
        // Pan with left-click drag on background
        d.addControlListener(new PanControl()); 
        // Zoom with right-click drag
        d.addControlListener(new ZoomControl());
	}

    private static class FinalRenderer {

        public FinalRenderer() {
        }
    }
}


import java.util.Random;

import javax.swing.JFrame;

import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.ColorAction;
import prefuse.action.assignment.DataColorAction;
import prefuse.action.layout.graph.ForceDirectedLayout;
import prefuse.activity.Activity;
import prefuse.controls.DragControl;
import prefuse.controls.PanControl;
import prefuse.controls.ZoomControl;
import prefuse.data.Graph;
import prefuse.data.Node;
import prefuse.data.Schema;
import prefuse.demos.FinalRenderer;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.LabelRenderer;
import prefuse.util.ColorLib;
import prefuse.util.FontLib;
import prefuse.util.PrefuseLib;
import prefuse.visual.VisualItem;
import prefuse.visual.expression.InGroupPredicate;

public class Example5 
{
	private static Graph graph;
	private static Visualization vis;
	private static Display d;	
	
    public static void main(String[] argv) 
	{
		setUpData();
		setUpVisualization();
		setUpRenderers();
		setUpActions();
		setUpDisplay();
		
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
	
	private static void setUpData()
	{
        // -- 1. load the data ------------------------------------------------
    	
    	// Here we are manually creating the data structures.  500 nodes are
    	// added to the Graph structure.  500 edges are made randomly 
    	// between the nodes.
       	
		graph = new Graph();
        
    	// For this example, we will add a little bit more
        // information to the graph.  
        // We can add data columns (recall that the graph
        // is backed by a table).
        
        // Add columns for gender, age, and job.
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
	
	private static void setUpVisualization()
	{
        // -- 2. the visualization --------------------------------------------
        // We must first creat the Visualization object.
		vis = new Visualization();
        
        // Now we add our previously created Graph object to the visualization.
        // The graph gets a textual label so that we can refer to it later on.
        vis.add("graph", graph);
	}
	
	private static void setUpRenderers()
	{
        // -- 3. the renderers and renderer factory ---------------------------
        FinalRenderer r = new FinalRenderer();
        DefaultRendererFactory drf = new DefaultRendererFactory(r);
        
        // We now have to have a renderer for our decorators.
        drf.add(new InGroupPredicate("nodedec"), new LabelRenderer("id"));
        
        // create a new DefaultRendererFactory
        // This Factory will use the ShapeRenderer for all nodes.
        vis.setRendererFactory(drf);
	
     // -- Decorators
        final Schema DECORATOR_SCHEMA = PrefuseLib.getVisualItemSchema();
        DECORATOR_SCHEMA.setDefault(VisualItem.INTERACTIVE, false); 
    	DECORATOR_SCHEMA.setDefault(VisualItem.TEXTCOLOR, ColorLib.rgb(0, 200, 0)); 
    	DECORATOR_SCHEMA.setDefault(VisualItem.FONT, FontLib.getFont("Tahoma",16));
        
        vis.addDecorators("nodedec", "graph.nodes", DECORATOR_SCHEMA);
	}
	
	private static void setUpActions()
	{
        // -- 4. the processing actions ---------------------------------------
        
        // We must color the nodes of the graph.  
        // Notice that we refer to the nodes using the text label for the graph,
        // and then appending ".nodes".  The same will work for ".edges" when we
        // only want to access those items.
        // The ColorAction must know what to color, what aspect of those 
        // items to color, and the color that should be used.
        // First we have to create a color palette.  Colors are represented as ints (8 bit red, 8 bit green, 8 bit blue, 8 bit alpha).
        // The ColorLib class has convenience methods for constructing color ints.
        
        int[] palette = {ColorLib.rgb(200, 0, 0), ColorLib.rgb(0,0, 200)}; 


        // Now we create the DataColorAction
        // We give it the nodes to color, the data column to color by, a constant (don't worry about it (or check the api)), 
        // the way to color, and the palette to use.
        
        DataColorAction fill = new DataColorAction("graph.nodes", "gender", Constants.NOMINAL, VisualItem.FILLCOLOR, palette);
        
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
        
        layout.add(new FinalDecoratorLayout("nodedec"));
        
        // We add a RepaintAction so that every time the layout is 
        // changed, the Visualization updates it's screen.
        layout.add(new RepaintAction());
        
        // add the actions to the visualization
        vis.putAction("color", color);
        vis.putAction("layout", layout);
	}
	
        
        // -- 5. the display and interactive controls -------------------------
    private static void setUpDisplay()
    {
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
        
      // d.addControlListener(new FinalControlListener() {});
    }
    
}
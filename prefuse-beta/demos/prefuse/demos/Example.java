import java.awt.Color;
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
import prefuse.data.io.DataIOException;
import prefuse.data.io.GraphMLReader;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.LabelRenderer;
import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;
import prefuse.action.ItemAction;
import prefuse.controls.*;

public class Example {
public static final String NODE_DECORATORS = "nodeDeco";
    public static void main(String[] argv) {
        
        // -- 1. load the data ------------------------------------------------
        
        // load the socialnet.xml file. it is assumed that the file can be
        // found at the root of the java classpath
        Graph graph = null;
        try {
            graph = new GraphMLReader().readGraph("/test.xml");
        } catch ( DataIOException e ) {
            System.err.println("Error loading graph. Exiting...");
            System.exit(1);
        }
        
        
        // -- 2. the visualization --------------------------------------------
        
        // add the graph to the visualization as the data group "graph"
        // nodes and edges are accessible as "graph.nodes" and "graph.edges"
        Visualization vis = new Visualization();
        vis.add("graph", graph);
        vis.setInteractive("graph.edges", null, false);
        
        // -- 3. the renderers and renderer factory ---------------------------
        
        // draw the "name" label for NodeItems
        LabelRenderer r = new LabelRenderer("name");
        LabelRenderer r2 = new LabelRenderer("gender");
        r.setRoundedCorner(10, 8); // round the corners
        r.setRoundedCorner(10, 8);
        // create a new default renderer factory
        // return our name label renderer as the default for all non-EdgeItems
        // includes straight line edges for EdgeItems by default
       vis.setRendererFactory(new DefaultRendererFactory(r));
       //vis.setRendererFactory(new DefaultRendererFactory(r2));
        // -- 4. the processing actions ---------------------------------------
        
        // create our nominal color palette
        // pink for females, baby blue for males
        int[] palette = new int[] {
            ColorLib.rgb(255,180,255), ColorLib.rgb(190,190,255) 
        };
        // map nominal data values to colors using our provided palette
        DataColorAction fill = new DataColorAction("graph.nodes", "name",
                Constants.NOMINAL, 
                VisualItem.FILLCOLOR,
                palette);
        // use black for node text
       /* ColorAction text = new ColorAction("graph.nodes",
                VisualItem.TEXTCOLOR, ColorLib.gray(0));*/
        
        ItemAction text=new TextColorAction("graph.nodes");
        // use light grey for edges
        ColorAction edges = new ColorAction("graph.edges",
                 VisualItem.STROKECOLOR, ColorLib.gray(200));
        
        
                //VisualItem.STROKECOLOR, ColorLib.gray(200));
               
        // create an action list containing all color assignments
        ActionList color = new ActionList();
        color.add(fill);
        color.add(text);
        color.add(edges);
        /***************Original Code*************************
        // create an action list with an animated layout
        ActionList layout = new ActionList(Activity.INFINITY);
        layout.add(new ForceDirectedLayout("graph"));
        layout.add(new RepaintAction());
        vis.putAction("color", color);
        vis.putAction("layout",layout);
       *********************************************************/
  ActionList layout = new ActionList(Activity.INFINITY);
        layout.add(new ForceDirectedLayout("graph"));
        layout.add(new RepaintAction());
        vis.putAction("color", color);
        vis.putAction("layout",layout);
   
        
        // -- 5. the display and interactive controls -------------------------
        
        Display display = new Display(vis);
        display.setSize(800,800); // set display size
        // drag individual items around
        display.addControlListener(new FocusControl(1));
        display.addControlListener(new DragControl());
        display.addControlListener(new PanControl());
        display.addControlListener(new ZoomControl());
        display.addControlListener(new WheelZoomControl());
        display.addControlListener(new ZoomToFitControl());
        display.addControlListener(new NeighborHighlightControl());
        
         display.setForeground(Color.GRAY);
        display.setBackground(Color.WHITE);
        
        // -- 6. launch the visualization -------------------------------------
        
        // create a new window to hold the visualization
        JFrame frame = new JFrame("prefuse example");
        // ensure application exits when window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(display);
        frame.pack();           // layout components in window
        frame.setVisible(true); // show the window
        
        // assign the colors
       vis.run("color");
        // start up the animated layout
        vis.run("layout");
        
    }
    
    
    public static class TextColorAction extends ColorAction {
        public TextColorAction(String group) {
            super(group, VisualItem.TEXTCOLOR, ColorLib.rgb(100, 100,255));
            add("_hover", ColorLib.rgb(255,240,0));
            add("_highlight", ColorLib.rgb(2,250,0));
        }
    } // end of inner class TextColorAction
    
    
    public static class GraphEdges extends ColorAction {
        public GraphEdges(String group) {
            super(group, VisualItem.STROKECOLOR, ColorLib.gray(240));
            add("_hover", ColorLib.rgb(255,240,0));
            add("_highlight", ColorLib.rgb(255,250,0));
        }
    } // end of inner class TextColorAction
    
    
}

package prefuse_tutorial;

import javax.swing.JFrame;

import prefuse.data.*;
import prefuse.data.io.*;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.render.*;
import prefuse.util.*;
import prefuse.action.assignment.*;
import prefuse.Constants;
import prefuse.visual.*;
import prefuse.action.*;
import prefuse.activity.*;
import prefuse.action.layout.graph.*;
import prefuse.controls.*;

public class SocialNetworkVis {

    public static void main(String argv[]) {

        // 1. Load the data

        Graph graph = null;
        /* graph will contain the core data */
        try {
           
           // graph = new GraphMLReader().readGraph("data/socialnet.xml");
              graph = new GraphMLReader().readGraph("data/socialnet.xml");
        /* load the data from an XML file */
        } catch (DataIOException e) {
            e.printStackTrace();
            System.err.println("Error loading graph. Exiting...");
            System.exit(1);
        }

        // 2. prepare the visualization

        Visualization vis = new Visualization();
        /* vis is the main object that will run the visualization */
        vis.add("socialnet", graph);
        /* add our data to the visualization */

        // 3. setup the renderers and the render factory

        // labels for name
        LabelRenderer nameLabel = new LabelRenderer("name");
        nameLabel.setRoundedCorner(8, 8);
        /* nameLabel decribes how to draw the data elements labeled as "name" */

        // create the render factory
        vis.setRendererFactory(new DefaultRendererFactory(nameLabel));

        // 4. process the actions

        // colour palette for nominal data type
        int[] palette = new int[]{ColorLib.rgb(255, 180, 180), ColorLib.rgb(190, 190, 255)};
        /* ColorLib.rgb converts the colour values to integers */


        // map data to colours in the palette
        DataColorAction fill = new DataColorAction("socialnet.nodes", "gender", Constants.NOMINAL, VisualItem.FILLCOLOR, palette);
        /* fill describes what colour to draw the graph based on a portion of the data */

        // node text
        ColorAction text = new ColorAction("socialnet.nodes", VisualItem.TEXTCOLOR, ColorLib.gray(0));
        /* text describes what colour to draw the text */

        // edge
        ColorAction edges = new ColorAction("socialnet.edges", VisualItem.STROKECOLOR, ColorLib.gray(200));
        /* edge describes what colour to draw the edges */

        // combine the colour assignments into an action list
        ActionList colour = new ActionList();
        colour.add(fill);
        colour.add(text);
        colour.add(edges);
        vis.putAction("colour", colour);
        /* add the colour actions to the visualization */

        // create a separate action list for the layout
        ActionList layout = new ActionList(Activity.INFINITY);
        layout.add(new ForceDirectedLayout("socialnet"));
        /* use a force-directed graph layout with default parameters */

        layout.add(new RepaintAction());
        /* repaint after each movement of the graph nodes */

        vis.putAction("layout", layout);
        /* add the laout actions to the visualization */

        // 5. add interactive controls for visualization

        Display display = new Display(vis);
        display.setSize(700, 700);
        display.pan(350, 350);	// pan to the middle
        display.addControlListener(new DragControl());
        /* allow items to be dragged around */

        display.addControlListener(new PanControl());
        /* allow the display to be panned (moved left/right, up/down) (left-drag)*/

        display.addControlListener(new ZoomControl());
        /* allow the display to be zoomed (right-drag) */

        // 6. launch the visualizer in a JFrame

        JFrame frame = new JFrame("prefuse tutorial: socialnet");
        /* frame is the main window */

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(display);
        /* add the display (which holds the visualization) to the window */

        frame.pack();
        frame.setVisible(true);

        /* start the visualization working */
        vis.run("colour");
        vis.run("layout");

    }
}

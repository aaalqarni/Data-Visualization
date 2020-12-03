import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.ColorAction;
import prefuse.action.layout.graph.ForceDirectedLayout;
import prefuse.activity.Activity;
import prefuse.controls.Control;
import prefuse.controls.DragControl;
import prefuse.controls.PanControl;
import prefuse.controls.WheelZoomControl;
import prefuse.controls.ZoomControl;
import prefuse.controls.ZoomToFitControl;
import prefuse.data.Graph;
import prefuse.data.Tuple;
import prefuse.data.event.TupleSetListener;
import prefuse.data.tuple.TupleSet;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.LabelRenderer;
import prefuse.util.ColorLib;
import prefuse.util.GraphLib;
import prefuse.util.force.ForceSimulator;
import prefuse.util.ui.UILib;
import prefuse.visual.VisualGraph;
import prefuse.visual.VisualItem;
/**
 * @author <a href="http://jheer.org">jeffrey heer</a>
 */
public class SelectDemo extends JPanel 
{
    private static final String graph = "graph";
    private static final String nodes = "graph.nodes";
    private static final String edges = "graph.edges";
    private Visualization m_vis;
    
    public SelectDemo(Graph g) {
        super(new BorderLayout());
        
        // create a new, empty visualization for our data
        m_vis = new Visualization();
        
        // mine
        m_vis.addFocusGroup("selected");
                
        // --------------------------------------------------------------------
        // set up the renderers
        
        LabelRenderer tr = new LabelRenderer();
        tr.setRoundedCorner(8, 8);
        m_vis.setRendererFactory(new DefaultRendererFactory(tr));
        // --------------------------------------------------------------------
        // register the data with a visualization
        
        // adds graph to visualization and sets renderer label field
        setGraph(g);
        
        TupleSet focusGroup = m_vis.getGroup("selected"); 
        focusGroup.addTupleSetListener(new TupleSetListener() 
        {
            public void tupleSetChanged(TupleSet ts, Tuple[] add, Tuple[] rem)
            {
                // You can access the group here whenever it changes.
                System.out.println("ch ch ch ch changes");
            }
        });
        
        // --------------------------------------------------------------------
        // create actions to process the visual data
       ColorAction fill = new ColorAction(nodes, VisualItem.FILLCOLOR, ColorLib.rgb(200,200,255));
       
        fill.add("ingroup('selected')", ColorLib.rgb(225, 100, 100));
        
        ActionList draw = new ActionList();
        
        draw.add(fill);
        
        draw.add(new ColorAction(nodes, VisualItem.TEXTCOLOR, ColorLib.rgb(0,0,0)));
        draw.add(new ColorAction(edges, VisualItem.FILLCOLOR, ColorLib.gray(200)));
         
        ColorAction edgeColor = new ColorAction(edges, VisualItem.STROKECOLOR, ColorLib.gray(200));
        
        edgeColor.add("ingroup('selected')", ColorLib.rgb(225, 100, 100));
        
        draw.add(edgeColor);
        
        ActionList animate = new ActionList(Activity.INFINITY);
        animate.add(new ForceDirectedLayout(graph));
        animate.add(fill);
        animate.add(new RepaintAction());
        
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
        display.setForeground(Color.GRAY);
        display.setBackground(Color.WHITE);
        
        // main display controls
        //display.addControlListener(new FocusControl(1));
        display.addControlListener(new DragControl());
        display.addControlListener(new PanControl());
        display.addControlListener(new ZoomControl());
        display.addControlListener(new WheelZoomControl());
        display.addControlListener(new ZoomToFitControl());
        display.addControlListener(new Control(){
            public boolean isEnabled() {
                // TODO Auto-generated method stub
                return true;
            }
            public void setEnabled(boolean enabled) {
                // TODO Auto-generated method stub

            }
            public void itemDragged(VisualItem item, MouseEvent e) {
                // TODO Auto-generated method stub

            }
            public void itemMoved(VisualItem item, MouseEvent e) {
                // TODO Auto-generated method stub

            }
            public void itemWheelMoved(VisualItem item, MouseWheelEvent e) {
                // TODO Auto-generated method stub

            }
            public void itemClicked(VisualItem item, MouseEvent e) 
            {

                // IMPORTANT: These four lines are all you need to 
                // change the selected items programmatically  
                TupleSet focused = m_vis.getFocusGroup("selected");

                focused.clear();

                focused.addTuple(item);

                m_vis.run("draw");
            }
            public void itemPressed(VisualItem item, MouseEvent e) {
                // TODO Auto-generated method stub

            }
            public void itemReleased(VisualItem item, MouseEvent e) {
                // TODO Auto-generated method stub

            }
            public void itemEntered(VisualItem item, MouseEvent e) {
                // TODO Auto-generated method stub

            }
            public void itemExited(VisualItem item, MouseEvent e) {
                // TODO Auto-generated method stub

            }
            public void itemKeyPressed(VisualItem item, KeyEvent e) {
                // TODO Auto-generated method stub

            }
            public void itemKeyReleased(VisualItem item, KeyEvent e) {
                // TODO Auto-generated method stub

            }
            public void itemKeyTyped(VisualItem item, KeyEvent e) {
                // TODO Auto-generated method stub

            }
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub

            }
            public void mouseDragged(MouseEvent e) {
                // TODO Auto-generated method stub

            }
            public void mouseMoved(MouseEvent e) {
                // TODO Auto-generated method stub

            }
            public void mouseWheelMoved(MouseWheelEvent e) {
                // TODO Auto-generated method stub

            }
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub

            }
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub

            }
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub

            }});
        display.setForeground(Color.GRAY);
        display.setBackground(Color.WHITE);
        
        // --------------------------------------------------------------------        
        // launch the visualization
        
        // create a panel for editing force values
        ForceSimulator fsim = ((ForceDirectedLayout)animate.get(0)).getForceSimulator();
     
        // now we run our action list
        m_vis.run("draw");
        
        add(display);
    }
    
    public void setGraph(Graph g) {
        // update graph
        m_vis.removeGroup(graph);
        VisualGraph vg = m_vis.addGraph(graph, g);
        m_vis.setValue(edges, null, VisualItem.INTERACTIVE, Boolean.TRUE);
        VisualItem f = (VisualItem)vg.getNode(0);
        m_vis.getGroup(Visualization.FOCUS_ITEMS).setTuple(f);
        f.setFixed(false);
    }
    
    // ------------------------------------------------------------------------
    // Main and demo methods
    
    public static void main(String[] args) 
    {
        UILib.setPlatformLookAndFeel();
                
        Graph g = GraphLib.getGrid(5, 5);
        
        final SelectDemo view = new SelectDemo(g);
        
        JFrame frame = new JFrame("p r e f u s e  |  s e l e c t  d e m o");
       
        frame.add(view);
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
    
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
} // end of class GraphView
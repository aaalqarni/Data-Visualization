package prefuse.demos.applets;

import prefuse.util.ui.JPrefuseApplet;


public class RadialGraphView1 extends JPrefuseApplet {

    public void init() {
        this.setContentPane(
            prefuse.demos.GraphViewPredicatesDemo1.demo("/Array.xml", "name"));
    }
    
} 
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prefuse.demos;

import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import prefuse.Visualization;
import prefuse.action.assignment.ColorAction;
import prefuse.controls.ControlAdapter;
import prefuse.data.Edge;
import prefuse.data.Graph;
import prefuse.data.Node;
import prefuse.data.Tuple;
import prefuse.data.expression.AbstractPredicate;
import prefuse.data.tuple.TupleSet;
import prefuse.visual.EdgeItem;
import prefuse.visual.NodeItem;
import prefuse.visual.VisualItem;
import prefuse.visual.expression.InGroupPredicate;

/**
 *
 * @author aaalqarni
 */
public class DirectedGraphNeighborHighlightControl extends ControlAdapter  {
    



	static final String SOURCEGROUPNAME = "DGNHC_SOURCEGROUP_NODE",

		TARGETGROUPNAME = "DGNHC_TARGETGROUP_NODE",

		BOTHGROUPNAME = "DGNHC_BOTHGROUP_NODE";



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

    DirectedGraphNeighborHighlightControl() {
        throw new UnsupportedOperationException("Not yet implemented");
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

		NodeItem sourceNode = edge.getSourceItem();

		source.add(sourceNode);

	    }



	    Iterator iter2 = centerNode.outEdges();

	    while (iter2.hasNext()) {

		EdgeItem edge = (EdgeItem) iter2.next();

		NodeItem targetNode = edge.getTargetItem();



		if (source.contains(targetNode)) {

		    both.add(targetNode);

		} else {

		    target.add(targetNode);

		}

	    }

	    source.removeAll(both);



	    Iterator iterSource = source.iterator();

	    while (iterSource.hasNext()) {

		sourceTupleSet.addTuple((NodeItem) iterSource.next());

	    }

	    Iterator iterTarget = target.iterator();

	    while (iterTarget.hasNext()) {

		targetTupleSet.addTuple((NodeItem) iterTarget.next());

	    }

	    Iterator iterBoth = both.iterator();

	    while (iterBoth.hasNext()) {

		bothTupleSet.addTuple((NodeItem) iterBoth.next());

	    }

	}

    }

//-----------------------------------------------------------------------------

   
    
    
    
    


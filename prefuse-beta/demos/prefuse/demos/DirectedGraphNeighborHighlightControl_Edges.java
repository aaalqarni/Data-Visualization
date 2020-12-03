/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prefuse.demos;

import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Iterator;
import prefuse.Visualization;
import prefuse.action.assignment.ColorAction;
import prefuse.controls.ControlAdapter;
import prefuse.data.tuple.TupleSet;
import prefuse.visual.EdgeItem;
import prefuse.visual.NodeItem;
import prefuse.visual.VisualItem;
import prefuse.visual.expression.InGroupPredicate;

/**
 *
 * @author aaalqarni
 */
public class DirectedGraphNeighborHighlightControl_Edges extends ControlAdapter {



	static final String SOURCEGROUPNAME = "DGNHC_SOURCEGROUP",

		TARGETGROUPNAME = "DGNHC_TARGETGROUP",

		BOTHGROUPNAME = "DGNHC_BOTHGROUP";



	TupleSet sourceTupleSet, targetTupleSet, bothTupleSet;



	public DirectedGraphNeighborHighlightControl_Edges(Visualization vis) {



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

            if(centerNode.canGetString("name"));
            System.out.println("@"+centerNode.getString("name"));
           
	    Iterator iter1 = centerNode.inEdges();
	    while (iter1.hasNext()) {

		EdgeItem edge = (EdgeItem) iter1.next();

		//NodeItem sourceNode = edge.getSourceItem();

		source.add(edge);

	    }
	    Iterator iter2 = centerNode.outEdges();      
	    while (iter2.hasNext()) {
                
		EdgeItem edge = (EdgeItem) iter2.next();
             
                    NodeItem targetNode = edge.getTargetItem();
                   // System.out.println("Target::"+edge);
                    target.add(edge);

		//}
              }
               
            Iterator iterSource = source.iterator(); 
            Iterator iterTarget = target.iterator();
          //----------------------------------------
            
          Iterator nItr=centerNode.edges();
          
           nItr=centerNode.edges();
            while(nItr.hasNext() ){
               EdgeItem Itr=(EdgeItem)nItr.next(); 
                
            while(iterTarget.hasNext())
            {  EdgeItem in_Edge=(EdgeItem)iterTarget.next();
               
               NodeItem sourceNode =in_Edge.getTargetItem();
                NodeItem targetNode=Itr.getSourceItem();
                
              //  NodeItem sourceNode =in_Edge.getTargetItem();
              //  NodeItem targetNode=Itr.getSourceItem();
                if(targetNode.equals(sourceNode) )
                {
                    //System.out.println(in_Edge);
                    //System.out.println(Itr);
                    
		    both.add(in_Edge);
                    both.add(Itr);
                   // source.remove(in_Edge);

		}
                //EdgeItem anEdge=(EdgeItem)nItr.
                
            }
            }
          //----------------------------------------
            
              nItr=centerNode.edges();
            while(nItr.hasNext() ){
               EdgeItem Itr=(EdgeItem)nItr.next(); 
                iterSource = source.iterator();
            while(iterSource.hasNext())
            {  EdgeItem in_Edge=(EdgeItem)iterSource.next();
               
               NodeItem sourceNode =in_Edge.getSourceItem();
                NodeItem targetNode=Itr.getTargetItem();
                
              //  NodeItem sourceNode =in_Edge.getTargetItem();
              //  NodeItem targetNode=Itr.getSourceItem();
                if(targetNode.equals(sourceNode) )
                {
                    //Iadd some operations here
                  //  System.out.println(in_Edge.getFloat("strength")*100);
                    if(targetNode.canGetString("strength")){
                    String temp =Itr.getString("strength");
                     System.out.println(temp);
                    //temp.longValue();
                    }
                   
                    
		    both.add(in_Edge);
                    both.add(Itr);
                    }
                    //source.remove(in_Edge);

		
                //EdgeItem anEdge=(EdgeItem)nItr.
                
            }
            }
            
        //----------------------------------------
            
            // both.removeAll(both);
             target.removeAll(both);
             source.removeAll(both);


	   
            iterSource = source.iterator();
            int b=0;
	    while (iterSource.hasNext()) {
                b++;
		sourceTupleSet.addTuple( (EdgeItem)iterSource.next());

	    }
            System.out.println("Indegree::"+b);
	    iterTarget = target.iterator();
             //
            int i=0;
            int i_t=0;
	    while (iterTarget.hasNext()) {
               i++;
               
                // System.out.println("Target");
		targetTupleSet.addTuple((EdgeItem) iterTarget.next());

	    }
            System.out.println("Outdegree::"+i);
//------------------------------------------------------------------------------
	    Iterator iterBoth = both.iterator();

	    while (iterBoth.hasNext()) {
                 i_t++;
                
		bothTupleSet.addTuple((EdgeItem) iterBoth.next());
//-----------------------------------------------------------------------------                

	    }
             System.out.println("BoTh::"+i_t);
              System.out.println("--------------------------------");
	}

    }


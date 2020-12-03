/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prefuse.demos;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Iterator;
import prefuse.data.Edge;
import prefuse.data.Graph;
import prefuse.data.Node;
import prefuse.data.Tuple;
import prefuse.data.expression.AbstractPredicate;


public final class BiDirectionPredicate extends AbstractPredicate {



    private Graph data;
    public  long Total;
    
    private HashMap<Tuple, Boolean> memory; // remove the memory if your
    
   public BiDirectionPredicate(Graph g) {

	    data = g;
            memory = new HashMap<Tuple, Boolean>();
            Total =0;

	}

   public double getTotal() {
        //NumberFormat formatter = new DecimalFormat("#0.00000");
       // Total=(double)formatter.format(Total);
        return Total;
    }
       
    @Override
	public boolean getBoolean(Tuple tpl) {
        //===========Get Time=============
             final long startTime=System.nanoTime();
       //================================
           

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
            final long endTime=System.nanoTime();
            long duration=endTime-startTime;
            //double seconds = (double)(duration/ 1000000000);
            //Total=Total+duration;
            //System.out.println(Total);
	    memory.put(tpl, biDirectional);

	    return biDirectional;

	}
         //===================================
            
         //===================================



    }
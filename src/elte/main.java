
package elte;


import java.util.StringTokenizer;
import egraph.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;


public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Egraph G = ExampleEGraphs.getG6();
        
        ArrayList<Eflow> flows = ExampleEGraphs.getG6flows();
        ArrayList<Ecommodity> commodities = ExampleEGraphs.getG6commodities();
        
        Algorithm.a1(G, commodities, flows);
        
       // System.out.println("c is free");
       // System.out.println(G.getIsCycleFreeC());
        
        G.displayGraph();
      
    }
    
}


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
        
        Egraph G = ExampleEGraphs.getG8();
        
        ArrayList<Eflow> flows = ExampleEGraphs.getG8flows();
        ArrayList<Ecommodity> commodities = ExampleEGraphs.getG8commodities();
        
        //G.displayGraph();
      
        Egraph g2 = Algorithm.a1(G, commodities, flows);
        
        G.setFlows(flows);
        G.displayGraph();
        g2.displayGraph();
      
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elte;


import java.util.StringTokenizer;
import egraph.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

/**
 *
 * @author pcgejza
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Egraph G = ExampleEGraphs.getG6();
        
        ArrayList<Eflow> flows = ExampleEGraphs.getG6flows();
        
        Algorithm.a1(G, flows);
        
       // System.out.println("c is free");
       // System.out.println(G.getIsCycleFreeC());
        
        G.displayGraph();
        
        /*
        System.out.println("Is commodity : ");
        System.out.println(G.isCommodity("Csucs1", "Csucs4"));
        
        System.out.println("Is single commodity : ");
        System.out.println(G.isSingleCommodity("Csucs1", "Csucs4"));
        */
        
        
        /*
        // TODO code application logic here
       	Graph graph = new Graph();
	double cost;
	boolean[] used;
	int i, n;

	graph.addVertex("A");
	graph.addVertex("B");
	graph.addVertex("C");
	graph.addVertex("D");
	graph.addVertex("E");
	graph.addEdge("A","B",3.0);
	graph.addEdge("A","C",4.0);
	graph.addEdge("B","C",2.0);
	graph.addEdge("B","D",1.0);
	graph.addEdge("C","E",3.0);
	graph.addEdge("D","E",8.0);
	cost = graph.getShortestPath("D", "E");
	System.out.println("Shortest Path length: "+cost);	

	used = graph.getUsedEdges();

	for (i=0;i<used.length;i++){
	    Edge e = (Edge) graph.getEdge(i);
	    System.out.println(e.start+" - "+e.end+"  "+used[i]);
	}
            */
    }
    
}

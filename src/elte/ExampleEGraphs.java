/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elte;

import org.graphstream.graph.*;
import egraph.Egraph;

/**
 *
 * @author pcgejza
 */
public class ExampleEGraphs {
    
    public static Egraph getG1(){
        Egraph G = new Egraph();
        
        G.addNode("Csucs1");
        G.addNode("Csucs2");
        G.addNode("Csucs3");
        G.addNode("Csucs4");
        
        G.addEdge("Csucs1", "Csucs2", 3, 2);
        G.addEdge("Csucs2", "Csucs4", 4, 3);
        G.addEdge("Csucs2", "Csucs3", 7, 5);
        G.addEdge("Csucs3", "Csucs4", 66, 5);
        
        return G;
    }
    
    public static Egraph getG2(){
        Egraph G = new Egraph();
        
        G.addNode("S");
        G.addNode("T");
        G.addNode("v1");
        G.addNode("v2");
        G.addNode("v3");
        G.addNode("v4");
        
        G.addEdge("S", "v1", 1, 1);
        G.addEdge("S", "v2", 1, 1);
        G.addEdge("v1", "v2", 1, 1);
        G.addEdge("v2", "v3", 1, 1);
        G.addEdge("v3", "v4", 1, 1);
        G.addEdge("v3", "T", 1, 1);
        G.addEdge("v4", "T", 1, 1);
        
        return G;
    }
    
    //https://upload.wikimedia.org/wikipedia/commons/thumb/9/94/Max_flow.svg/220px-Max_flow.svg.png
    public static Egraph getG3(){
        Egraph G = new Egraph();
        
        G.addNode("S");
        G.addNode("T");
        G.addNode("o");
        G.addNode("p");
        G.addNode("q");
        G.addNode("r");
        
        G.addEdge("S", "o", 3, 3);
        G.addEdge("S", "p", 3, 2);
        G.addEdge("o", "p", 2, 0);
        G.addEdge("p", "r", 2, 2);
        G.addEdge("o", "q", 3, 3);
        G.addEdge("q", "r", 4, 1);
        G.addEdge("r", "T", 3, 3);
        G.addEdge("q", "T", 2, 2);
        
        return G;
    }
    
    //http://deliveryimages.acm.org/10.1145/2630000/2628036/figs/f4.jpg
    public static Egraph getG4(){
        Egraph G = new Egraph();
        
        G.addNode("S");
        G.addNode("T");
        G.addNode("a");
        G.addNode("b");
        G.addNode("c");
        G.addNode("d");
        
        G.addEdge("S", "a", 4, 0);
        G.addEdge("S", "c", 9, 5);
        G.addEdge("a", "b", 5, 5);
        G.addEdge("b", "T", 9, 5);
        G.addEdge("c", "d", 5, 5);
        G.addEdge("d", "a", 9, 5);
        G.addEdge("d", "T", 3, 0);
        
        return G;
    }
    
    //http://staff.ustc.edu.cn/~csli/graduate/algorithms/book6/581_a.gif
    public static Egraph getG5(){
        Egraph G = new Egraph();
        
        G.addNode("S");
        G.addNode("T");
        G.addNode("v1");
        G.addNode("v2");
        G.addNode("v3");
        G.addNode("v4");
        
        G.addEdge("S", "v1", 16, 11);
        G.addEdge("S", "v2", 13, 8);
        G.addEdge("v1", "v2", 10, 10);
        G.addEdge("v2", "v1", 4, 1);
        G.addEdge("v1", "v3", 12, 12);
        G.addEdge("v3", "v2", 9, 4);
        G.addEdge("v4", "v3", 7, 7);
        G.addEdge("v2", "v4", 14, 11);
        G.addEdge("v4", "T", 4, 4);
        G.addEdge("v3", "T", 20, 15);
        
        return G;
    }
    
    
}

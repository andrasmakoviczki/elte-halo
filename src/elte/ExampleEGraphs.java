/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elte;

import egraph.Eedge;
import egraph.Eflow;
import org.graphstream.graph.*;
import egraph.Egraph;
import egraph.Enode;
import java.util.ArrayList;

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
    
    //András példa gráf
    public static Egraph getG6(){
        Egraph G = new Egraph();
        
        G.addNode("s1");
        G.addNode("t1");
        G.addNode("s2");
        G.addNode("t2");
        G.addNode("a");
        G.addNode("b");
        G.addNode("c");
        G.addNode("d");
        G.addNode("e");
        
        G.addEdge("s1", "a", 1, 0);
        G.addEdge("s1", "b", 24, 11);
        G.addEdge("a", "d", 9, 0);
        G.addEdge("d", "t2", 5, 5);
        G.addEdge("b", "d", 8, 3+5);
        G.addEdge("d", "t1", 3, 3);
        G.addEdge("s2", "c", 6, 6);
        G.addEdge("c", "b", 6, 5);
        G.addEdge("c", "e", 1, 1);
        G.addEdge("b", "e", 8, 8);
        G.addEdge("e", "t1", 9, 8+1);
        G.addEdge("t1", "t2", 7, 1);
        
        return G;
    }
    
    public static ArrayList<Eflow> getG6flows(){
        ArrayList<Eflow> eflows = new ArrayList<Eflow>();
        
        Eflow eflow1 = new Eflow();
        eflow1.addEdge(new Eedge(new Enode("s1"), new Enode("b"), 11));
        eflow1.addEdge(new Eedge(new Enode("b"), new Enode("d"), 3));
        eflow1.addEdge(new Eedge(new Enode("b"), new Enode("e"), 8));
        eflow1.addEdge(new Eedge(new Enode("d"), new Enode("t1"), 3));
        eflow1.addEdge(new Eedge(new Enode("e"), new Enode("t1"), 8));
        
        eflows.add(eflow1);
        
        Eflow eflow2 = new Eflow();
        eflow2.addEdge(new Eedge(new Enode("s2"), new Enode("c"), 6));
        eflow2.addEdge(new Eedge(new Enode("c"), new Enode("b"), 5));
        eflow2.addEdge(new Eedge(new Enode("c"), new Enode("e"), 1));
        eflow2.addEdge(new Eedge(new Enode("b"), new Enode("d"), 5));
        eflow2.addEdge(new Eedge(new Enode("e"), new Enode("t1"), 1));
        eflow2.addEdge(new Eedge(new Enode("d"), new Enode("t2"), 5));
        eflow2.addEdge(new Eedge(new Enode("t1"), new Enode("t2"), 1));
        
        eflows.add(eflow2);
        
        
        
        return eflows;
    }
    
    
    
}

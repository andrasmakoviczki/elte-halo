
package elte;

import egraph.Ecommodity;
import egraph.Eedge;
import egraph.Eflow;
import org.graphstream.graph.*;
import egraph.Egraph;
import egraph.Enode;
import java.util.ArrayList;


public class ExampleEGraphs {
    
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
    
    //András példa gráf2
    public static Egraph getG7(){
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
        G.addNode("f");
        G.addNode("g");
        G.addNode("h");
        G.addNode("i");
        G.addNode("j");
        G.addNode("k");
        G.addNode("l");
        
        G.addEdge("s1", "a", 1, 0);
        G.addEdge("s1", "b", 24, 11);
        G.addEdge("a", "d", 9, 0);
        G.addEdge("d", "f", 5, 5);
        G.addEdge("b", "d", 8, 3+5);
        G.addEdge("d", "g", 3, 3);
        G.addEdge("s2", "c", 6, 6);
        G.addEdge("c", "b", 6, 5);
        G.addEdge("c", "e", 1, 1);
        G.addEdge("b", "e", 8, 8);
        G.addEdge("e", "g", 9, 8+1);
        G.addEdge("t1", "t2", 7, 1);
        G.addEdge("f", "h", 7, 6);
        G.addEdge("g", "i", 12, 11);
        G.addEdge("g", "j", 5, 0);
        G.addEdge("j", "l", 6, 0);
        G.addEdge("l", "t1", 16, 1+11);
        G.addEdge("l", "t2", 6, 5);
        G.addEdge("i", "l", 17, 11+6);
        G.addEdge("h", "k", 3, 0);
        G.addEdge("h", "i", 6, 6);
        G.addEdge("i", "k", 5, 0);
        G.addEdge("k", "l", 2, 0);
        G.addEdge("k", "t2", 2, 0);
        
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
    
    public static ArrayList<Eflow> getG7flows(){
        ArrayList<Eflow> eflows = new ArrayList<Eflow>();
        
        Eflow eflow1 = new Eflow();
        eflow1.addEdge(new Eedge(new Enode("s1"), new Enode("b"), 11));
        eflow1.addEdge(new Eedge(new Enode("b"), new Enode("d"), 3));
        eflow1.addEdge(new Eedge(new Enode("b"), new Enode("e"), 8));
        eflow1.addEdge(new Eedge(new Enode("d"), new Enode("g"), 3));
        eflow1.addEdge(new Eedge(new Enode("e"), new Enode("g"), 8));
        eflow1.addEdge(new Eedge(new Enode("g"), new Enode("i"), 11));
        eflow1.addEdge(new Eedge(new Enode("i"), new Enode("l"), 11));
        eflow1.addEdge(new Eedge(new Enode("l"), new Enode("t1"), 11));
        
        eflows.add(eflow1);
        
        Eflow eflow2 = new Eflow();
        eflow2.addEdge(new Eedge(new Enode("s2"), new Enode("c"), 6));
        eflow2.addEdge(new Eedge(new Enode("c"), new Enode("b"), 5));
        eflow2.addEdge(new Eedge(new Enode("c"), new Enode("e"), 1));
        eflow2.addEdge(new Eedge(new Enode("b"), new Enode("d"), 5));
        eflow2.addEdge(new Eedge(new Enode("e"), new Enode("g"), 1));
        eflow2.addEdge(new Eedge(new Enode("d"), new Enode("f"), 5));
        eflow2.addEdge(new Eedge(new Enode("f"), new Enode("h"), 6));
        eflow2.addEdge(new Eedge(new Enode("h"), new Enode("i"), 6));
        eflow2.addEdge(new Eedge(new Enode("i"), new Enode("l"), 6));
        eflow2.addEdge(new Eedge(new Enode("l"), new Enode("t2"), 5));
        eflow2.addEdge(new Eedge(new Enode("l"), new Enode("t1"), 1));
        
        
        eflows.add(eflow2);
        
        
        
        return eflows;
    }
    
    public static ArrayList<Ecommodity> getG6commodities(){
        ArrayList<Ecommodity> ecommodities = new ArrayList<Ecommodity>();
        
        ecommodities.add(new Ecommodity(new Enode("s1"), new Enode("t1"), 11.0));
        ecommodities.add(new Ecommodity(new Enode("s2"), new Enode("t2"), 6.0));
        
        return ecommodities;
    }
    
    public static ArrayList<Ecommodity> getG7commodities(){
        ArrayList<Ecommodity> ecommodities = new ArrayList<Ecommodity>();
        
        ecommodities.add(new Ecommodity(new Enode("s1"), new Enode("t1"), 11.0));
        ecommodities.add(new Ecommodity(new Enode("s2"), new Enode("t2"), 6.0));
        
        return ecommodities;
    }
    
    
    //Fólia példa gráf
    public static Egraph getG8(){
        Egraph G = new Egraph();
        
        G.addNode("s");
        G.addNode("t");
        G.addNode("a");
        G.addNode("b");
        G.addNode("c");
        G.addNode("d");
        G.addNode("e");
        G.addNode("f");
        
        
        G.addEdge("s", "a", 1, 0);
        G.addEdge("s", "b", 1, 0);
        G.addEdge("s", "c", 1, 0);
        G.addEdge("a", "b", 1, 0);
        G.addEdge("c", "b", 1, 0);
        G.addEdge("a", "d", 1, 0);
        G.addEdge("b", "e", 1, 0);
        G.addEdge("c", "f", 1, 0);
        G.addEdge("e", "d", 1, 0);
        G.addEdge("d", "t", 1, 0);
        G.addEdge("e", "t", 1, 0);
        G.addEdge("e", "f", 1, 0);
        G.addEdge("f", "t", 1, 0);
        
        return G;
    }
    
    
    public static ArrayList<Ecommodity> getG8commodities(){
        ArrayList<Ecommodity> ecommodities = new ArrayList<Ecommodity>();
        
        ecommodities.add(new Ecommodity(new Enode("s"), new Enode("t"), 2));
        
        return ecommodities;
    }
    
    public static ArrayList<Eflow> getG8flows(){
        ArrayList<Eflow> eflows = new ArrayList<Eflow>();
        
        Eflow eflow1 = new Eflow();
        eflow1.addEdge(new Eedge(new Enode("s"), new Enode("a"), 1));
        eflow1.addEdge(new Eedge(new Enode("s"), new Enode("c"), 1));
        eflow1.addEdge(new Eedge(new Enode("a"), new Enode("b"), 1));
        eflow1.addEdge(new Eedge(new Enode("b"), new Enode("e"), 1));
        eflow1.addEdge(new Eedge(new Enode("e"), new Enode("d"), 1));
        eflow1.addEdge(new Eedge(new Enode("d"), new Enode("t"), 1));
        eflow1.addEdge(new Eedge(new Enode("c"), new Enode("f"), 1));
        eflow1.addEdge(new Eedge(new Enode("f"), new Enode("t"), 1));
        
        
        eflows.add(eflow1);
        
        
        return eflows;
    }
    
    
    
    
    
}


package egraph;

import java.util.ArrayList;
import java.util.HashMap;



public class Eflow {
    
    ArrayList<Eedge> edges;

    public ArrayList<Eedge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Eedge> edges) {
        this.edges = edges;
    }

    public Eflow(){
	this.edges = new ArrayList<Eedge>();
    }
    
    public void addEdge(Eedge edge){
        this.edges.add(edge);
    }
    
    public String toString(){
        return ""+this.edges.size();
    }
    
    public Enode getStartNode(){
        return this.edges.get(0).getNode1();
    }
    
}

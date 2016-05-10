
package egraph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Enode {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    private String name;
    private HashMap<String, Eedge> outEdges;
    private HashMap<String, Eedge> inEdges;

    public HashMap<String, Eedge> getOutEdges() {
        return outEdges;
    }

    public void setOutEdges(HashMap<String, Eedge> outEdges) {
        this.outEdges = outEdges;
    }

    public HashMap<String, Eedge> getInEdges() {
        return inEdges;
    }

    public void setInEdges(HashMap<String, Eedge> inEdges) {
        this.inEdges = inEdges;
    }
    
    public Enode(String name){
        this.name = name;
        this.outEdges = new HashMap<String, Eedge>();
        this.inEdges = new HashMap<String, Eedge>();
    }
    
    public void addInEdge(String key, Eedge eedge){
        this.inEdges.put(key, eedge);
    }
    
    public void addOutEdge(String key, Eedge eedge){
        this.outEdges.put(key, eedge);
    }

    
    public String toString(){
        return this.name;
    }
    
    /**
     * Kiszámolja a bemenő élek kapacitását
     * @return 
     */
    public double getAllInEdgeCapacity(){
        double capacity = 0.0;
        
        Iterator it = this.inEdges.entrySet().iterator();
        Eedge itEdge = null;
        
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            itEdge = (Eedge)  pair.getValue();
            capacity += itEdge.getCapacity();
            //it.remove(); // avoids a ConcurrentModificationException
        }
       
       return capacity;
    }
    
    /**
     * Kiszámolja a kimenő élek kapacitását
     * @return 
     */
    public double getAllOutEdgeCapacity(){
        double capacity = 0.0;
        
        Iterator it = this.outEdges.entrySet().iterator();
        Eedge itEdge = null;
        
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            itEdge = (Eedge)  pair.getValue();
            capacity += itEdge.getCapacity();
            //it.remove(); // avoids a ConcurrentModificationException
        }
       
       return capacity;
    }
    
    /**
     * Kiszámolja a bemenő élek folyamát
     * @return 
     */
    public double getAllInEdgeFlow(){
        double flow = 0.0;
        
        Iterator it = this.inEdges.entrySet().iterator();
        Eedge itEdge = null;
        
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            itEdge = (Eedge)  pair.getValue();
            flow += itEdge.getFlow();
            //it.remove(); // avoids a ConcurrentModificationException
        }
       
       return flow;
    }
    
    /**
     * Kiszámolja a kimenő élek folyamát
     * @return 
     */
    public double getAllOutEdgeFlow(){
        double flow = 0.0;
        
        Iterator it = this.outEdges.entrySet().iterator();
        Eedge itEdge = null;
        
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            itEdge = (Eedge)  pair.getValue();
            flow += itEdge.getFlow();
            //it.remove(); // avoids a ConcurrentModificationException
        }
       
       return flow;
    }
    
}

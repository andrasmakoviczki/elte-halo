
package egraph;

import com.sun.javafx.geom.Edge;
import java.util.HashMap;

public class Eedge {
    
    private String id;
    private Enode node1;
    private Enode node2;
    private double capacity = 0.0;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
    private double flow = 0.0;
    
    public Eedge(Enode node1, Enode node2, double capacity){
        this.node1 = node1;
        this.node2 = node2;
        this.capacity = capacity;
        this.setIdAutomatic();
    }
    
    public Eedge(Enode node1, Enode node2, double capacity, double flow){
        this.node1 = node1;
        this.node2 = node2;
        this.capacity = capacity;
        this.flow = flow;
        this.setIdAutomatic();
    }
    
    public void setIdAutomatic(){
        this.id = this.node1.getName()+"_"+this.node2.getName();
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getFlow() {
        return flow;
    }

    public void setFlow(double flow) {
        this.flow = flow;
    }
    
    // folyam csökkentése értékkel
    public void decFlow(double flow) {
        this.flow -= flow;
    }
    
    // folyam növelése értékkel
    public void incFlow(double flow) {
        this.flow += flow;
    }
    
    public Enode getNode1() {
        return node1;
    }
    

    public void setNode1(Enode node1) {
        this.node1 = node1;
    }

    public Enode getNode2() {
        return node2;
    }

    public void setNode2(Enode node2) {
        this.node2 = node2;
    }
    
    public String toString(){
        return this.id;
    }
    
    /**
     * Slack-elt él azt jelenti, hogy a kapacitás nincs teljesen kihasználva
     * @return boolean
     */
    public boolean isSlack(){
        return this.getSlack() > 0.0;
    }
    
    public double getSlack(){
        return this.capacity - this.flow;
    }
    
    public void reverseNodes(){
        Enode n1 = this.node1;
        this.node1 = this.node2;
        this.node2 = n1;
        this.setIdAutomatic();
        return;
    }
    
}

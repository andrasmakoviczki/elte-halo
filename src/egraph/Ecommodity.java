/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package egraph;

/**
 *
 * @author pcgejza
 */
public class Ecommodity {
    
    Enode startNode;
    Enode endNode;
    double cost;

    public Ecommodity(Enode startNode, Enode endNode, double cost) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.cost = cost;
    }

    public Enode getStartNode() {
        return startNode;
    }

    public void setStartNode(Enode startNode) {
        this.startNode = startNode;
    }

    public Enode getEndNode() {
        return endNode;
    }

    public void setEndNode(Enode endNode) {
        this.endNode = endNode;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
    
    
    
    
}

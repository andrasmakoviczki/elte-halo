
package egraph;

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

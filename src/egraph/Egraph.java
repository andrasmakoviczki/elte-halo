
package egraph;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

public class Egraph {
    
    private HashMap<String, Eedge> edges;
    private HashMap<String, Enode> nodes;
    private ArrayList<Eflow> flows;
    private ArrayList<Ecommodity> commodities;
    
    public Egraph(){
	edges = new HashMap<String, Eedge>();
	nodes = new HashMap<String, Enode>();
	flows = new ArrayList<Eflow>();
    }

    public HashMap<String, Eedge> getEdges() {
        return edges;
    }

    public void setEdges(HashMap<String, Eedge> edges) {
        this.edges = edges;
    }

    public HashMap<String, Enode> getNodes() {
        return nodes;
    }

    public void setNodes(HashMap<String, Enode> nodes) {
        this.nodes = nodes;
    }
    
    public ArrayList<Eflow> getFlows(){
        return this.flows;
    }
    
    public void displayGraph(){
        
        Graph graph = new SingleGraph("Disp 1");
        graph.addAttribute("ui.stylesheet", styleSheet);
        Iterator it = null;
        Enode n = null, n1 = null, n2 = null;
        Eedge e = null;
        Edge gEdge = null;
        Eflow eFlow = null;
        
        
        it = this.nodes.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            n = (Enode)  pair.getValue();
            graph.addNode(n.getName()).addAttribute("ui.label", n.getName());
        }
        
        it = this.edges.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            e = (Eedge)  pair.getValue();
            n1 =  e.getNode1();
            n2 =  e.getNode2();
            graph.addEdge(e.getId(), n1.getName(), n2.getName(), true).addAttribute("ui.label", e.getFlow());
        }
        
        for (int i = 0; i < this.flows.size(); i++) {
            eFlow = this.flows.get(i);
            //System.out.println("FOLYAM"+i+" ---------");
            for(int j = 0; j < eFlow.getEdges().size(); j++){
                e = eFlow.getEdges().get(j);
                gEdge = graph.getEdge(e.getId());  
                //gEdge.setAttribute("ui.label", gEdge.getAttribute('ui.label')+","+ "");
                if(gEdge != null)
                   gEdge.addAttribute("ui.style", "fill-color: rgb(255,0,0); size : 3px;");
            }
        }

        
        
        Viewer viewer = graph.display();
        
        
        
    }

    
    public void addNode(String id){
        this.nodes.put(id, new Enode(id));
    }
    
    public Enode getNode(String key){
        return this.nodes.get(key);
    }
    
    public void addEdge(String node1, String node2, double capacity, double flow){
        Enode enode1 = this.nodes.get(node1);
        Enode enode2 = this.nodes.get(node2);
        
        this.edges.put(node1+"_"+node2,new Eedge(enode1, enode2, capacity, flow));
        
        Eedge e = this.edges.get(node1+"_"+node2);
        enode1.addOutEdge(node1+"_"+node2, e);
        enode2.addInEdge(node1+"_"+node2, e);
    }
    
    
    public void printEdges(){
        Iterator it = this.edges.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Eedge ee = (Eedge)  pair.getValue();
            System.out.println(pair.getKey() + " = " +ee.getCapacity());
            //it.remove(); // avoids a ConcurrentModificationException
        }
    }
    
    /**
     * DEFINITION 1
     * A gráf commodity-e s és t csúcs között
     * @param s
     * @param t
     * @return 
     */
    public boolean isCommodity(String s, String t){
        Enode sNode = this.nodes.get(s);
        Enode tNode = this.nodes.get(t);
        Enode v = null;
        Eedge e = null;
        double vOutSum = 0.0 , vInSum = 0.0;
        int vCountEx = 0;
        int vCountInEx = 0;
        Iterator it = null;
        
        // 1. feltétel ∀v ∈ V \ {s, t}: ∑ e∈out(v) F(e) = ∑ e∈in(v) F(e)
        it = this.nodes.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            v = (Enode) pair.getValue();
            
            if(v != sNode && v != tNode){
                vOutSum = v.getAllOutEdgeFlow();
                vInSum = v.getAllInEdgeFlow();
                       
                if(vOutSum == vInSum){
                    vCountEx++;
                }else{
                    vCountInEx++;
                }
                //System.out.println("*** "+v.getName()+" "+vOutSum+" = "+vInSum);
            }
        }
                
        if(vCountInEx == 0){ // amennyiben az első feltétel nem teljesül, false-al térünk vissza
            return false;
        }
        
        // 2. feltétel 
        double sAllOutEdgesFlow = sNode.getAllOutEdgeFlow();
        double tAllInEdgesFlow = sNode.getAllOutEdgeFlow();
        
        
        if(sAllOutEdgesFlow != tAllInEdgesFlow){
            return false;
        }
        
        
        // 3.feltétel
        boolean cfValEth = false;
        it = this.edges.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            e = (Eedge) pair.getValue();
            
            if(e.getFlow() > e.getCapacity()){
                cfValEth = true;
            }
            //System.out.println("E = "+e.getId()+", F : "+e.getFlow()+", c : "+e.getCapacity());
        }
        
        return !cfValEth;
    }
    
    /**
     * DEFINITION 2
     * A gráf single commodity-e s és t csúcs között
     * @param s
     * @param t
     * @return 
     */
    public boolean isSingleCommodity(String s, String t){
        Enode sNode = this.nodes.get(s);
        Enode tNode = this.nodes.get(t);
      
        
        return true;
    }
    
    /**
     * C halmaz lekérdezése
     * @return 
     */
    public HashMap<String, Eedge> getC(){
        HashMap<String, Eedge> hm = new HashMap<String, Eedge>();
        
        Iterator it = this.edges.entrySet().iterator();
        Eedge e = null;
        
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            e = (Eedge) pair.getValue();
            if(e.getFlow() > 0){
                hm.put((String) pair.getKey(), e);
            }
        }
        
        return hm;
    }
    
    /**
     * C halmaz-ban van-e kör
     * @return 
     */
    public boolean getIsCycleFreeC(){
        boolean cIsFree = true;
        
        HashMap<String, Boolean> inEdges = new HashMap<String, Boolean>();
        Iterator it = this.getC().entrySet().iterator();
        Eedge e = null;
        Enode n1, n2;
        HashMap<String, Eedge> en = null;
        
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            e = (Eedge) pair.getValue();
            n1 = (Enode) e.getNode1();
            n2 = (Enode) e.getNode2();
            
            if(inEdges.containsKey(n2.getName())){
                cIsFree = false;
            }
            en = n2.getOutEdges();
            
            if(en.size() > 0){
                inEdges.put(n1.getName(), true);
            }
        }
        
        return cIsFree;
    }
    
    private void set0FlowsToEdges(){
        Iterator it = this.edges.entrySet().iterator();
        Eedge e = null;
        
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            e = (Eedge) pair.getValue();
            e.setFlow(0);
        }
    }
    
    
    public void setFlows(ArrayList<Eflow> flows){
        this.flows = flows;
        this.set0FlowsToEdges(); // lenullázzuk az összes élen végigmenő folyamot
        Eflow f = null;
        Eedge e = null;
        Eedge te = null;
        ArrayList<Eedge> edges = null;
        
        for (int i = 0; i < flows.size(); i++) {
            f = flows.get(i);
            edges = f.getEdges();
            for(int j = 0; j < edges.size(); j++){
                e = edges.get(j);
                te = this.edges.get(e.getId());
                te.setFlow(te.getFlow()+e.getCapacity());
            }
        }
    }
    
    public void setCommodities(ArrayList<Ecommodity> commodities){
        this.commodities = commodities;
    }
    
    public ArrayList<Ecommodity> getCommodities(){
        return this.commodities;
    }
    
    
    /**
     * Visszaad egy slack nélküli élt
     * @return 
     */
    public Eedge getWithoutSlackEdge(){
        Eedge ews = null;
        
        Iterator it = this.edges.entrySet().iterator();
        Eedge e = null;
        
        while (it.hasNext() && ews == null) {
            Map.Entry pair = (Map.Entry)it.next();
            e = (Eedge) pair.getValue();
            
            if(e.getFlow() > 0 && e.getFlow() == e.getCapacity()){
                ews = e;
            }
        }
        
        
        return ews;
    }
    
    /**
     * Visszaadja a slack nélküli éleket
     * @return 
     */
    public ArrayList<Eedge> getWithoutSlackEdges(){
        ArrayList<Eedge> edges = new ArrayList<Eedge>();
        
        Iterator it = this.edges.entrySet().iterator();
        Eedge e = null;
        
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            e = (Eedge) pair.getValue();
            
            if(e.getFlow() > 0 && e.getFlow() == e.getCapacity()){
                edges.add(e);
            }
        }
        
        return edges;
    }
    
    
    protected String styleSheet =
            "node {" +
            "	text-size: 25px;" +
            "	size: 15px;" +
            "}" +
            "node.marked {" +
            "	fill-color: red;" +
            "}";
    
    public Egraph(Egraph gcopy){
	edges = new HashMap<String, Eedge>();
	nodes = new HashMap<String, Enode>();
	flows = new ArrayList<Eflow>();
	commodities = new ArrayList<Ecommodity>();
        
        
        for (Map.Entry<String,Enode> entry : gcopy.getNodes().entrySet()) {
            Enode entryNode = entry.getValue();
            this.addNode(entryNode.getName());
        }
        
        for (Map.Entry<String,Eedge> entry : gcopy.getEdges().entrySet()) {
            Eedge entryEdge = entry.getValue();
            this.addEdge(entryEdge.getNode1().getName(), entryEdge.getNode2().getName(),  entryEdge.getCapacity(),   entryEdge.getFlow());
        }
    }
    
    public void resetNodeOutEdges(){
        for (Map.Entry<String,Enode> entry : this.getNodes().entrySet()) {
            Enode entryNode = entry.getValue();
            entryNode.setOutEdges(new HashMap<String, Eedge>());
            
            for (Map.Entry<String,Eedge> entry2 : this.getEdges().entrySet()) {
                Eedge entryEdge = entry2.getValue();
                if(entryEdge.getNode1().getName() == entryNode.getName()){
                    entryNode.addOutEdge(entryEdge.getId(), entryEdge);
                }
            }
        }
    }
    
            
}


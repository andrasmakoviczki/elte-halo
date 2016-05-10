
package elte;

import egraph.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;


public class Algorithm {
    
    public static Egraph a1(Egraph graph,  ArrayList<Ecommodity> commodities, ArrayList<Eflow> flows){
        Eflow f = new Eflow();
        Egraph graph2 = new Egraph(graph);
        ArrayList<Boolean> edgesIsSlack = new ArrayList<Boolean>();
        graph2.setFlows(flows);
        graph2.setCommodities(commodities);
        Eedge edgeWithoutSlack = null;
        Eflow currentFlow = null;
        double sStar = 0.0;
        boolean uIsAffected; // az u csúcs érintve lett-e
        Enode   v = null, sNode = null, 
                vComma = null, vCommaComma = null;
        
        // 1. vegyünk egy slack nélküli élt (gyakorlatilag az összes ilyenen végig kell iterálni)
        for(int iWSlack = 0; iWSlack<graph2.getWithoutSlackEdges().size(); iWSlack++){
            System.out.println("------------------");
            edgeWithoutSlack = graph2.getWithoutSlackEdges().get(iWSlack);
            System.out.println("Current edge without slack = "+edgeWithoutSlack.toString());
            
            ArrayList fwEdges = getFlowSWithEdge(flows, edgeWithoutSlack);
            
            //2. Vegyünk egy K ∈ K commodity-t. A megfelelő folyamot F-fel jelöljük.
            for(int iFwEdges = 0; iFwEdges < fwEdges.size(); iFwEdges++){
                currentFlow = getFlowWithEdge(flows, edgeWithoutSlack);
                System.out.println("Current flow = "+currentFlow.toString());
                
                Egraph graph3 = new Egraph(graph2);
                
                sStar = getSstar(graph, currentFlow);

                
                System.out.println("Current sStar = "+sStar);
                
                //3. A végpontból kimenő e 1 él, v csúcsa egy szélességi keresést végez
                graph3 = reversalEdgesEflow(graph3, currentFlow);
                v = graph3.getNode(edgeWithoutSlack.getNode1().getName());
          
                ArrayList szk = breadthTree(graph3, v);
                uIsAffected = (boolean) szk.get(0);
                ArrayList eK = (ArrayList) szk.get(1);
                ArrayList eS = (ArrayList) szk.get(2);
                
                System.out.println("uIsAffected = "+uIsAffected);
                
                //4. Ha a szélességi keresés a 3. lépésben érintette az u csúcsot
                if(uIsAffected == true){
                    //Minden élre az E k -ban és e 1 -hez csökkentjük a folyam K commodity-jét s ∗ -gal, és minden
                    //    élre az E s -ben növeljük a folyam K commodity-jét s ∗ -gal. Ha kör keletkezik a
                    //    mellékfolyamokban, azt eltávolítjuk.
                    for(int iEk = 0; iEk < eK.size(); iEk++){
                        Eedge EkEdge = (Eedge) eK.get(iEk);
                        Eedge graphEkEdge = graph3.getEdges().get(EkEdge.getId());
                        //graphEkEdge.decFlow(sStar);
                    }
                    for(int iEs = 0; iEs < eS.size(); iEs++){
                        Eedge EsEdge = (Eedge) eS.get(iEs);
                        Eedge graphEkEdge = graph3.getEdges().get(EsEdge.getId());
                        //graphEkEdge.incFlow(sStar);
                    }
                    
                    edgesIsSlack.add(true);
                }else{
                    edgesIsSlack.add(false);
                }
            }
            
        }
        
        
        boolean algorithmResult = true;
        for(int i=0; i<edgesIsSlack.size(); i++){
            if(edgesIsSlack.get(i) == false){
                algorithmResult = false;
            }
        }
        
        System.out.println("*************** ALGORITHM RESULT : "+algorithmResult);
        
        return graph2;
    }
    
    // szélességi keresés gyökere
    public static Eflow getFlowWithEdge(ArrayList<Eflow> flows, Eedge edge){
        Eflow flow = null;
       
        outerloop:
        for(int i = 0; i < flows.size(); i++){
            ArrayList<Eedge> edges = flows.get(i).getEdges();
                
            for(int j = 0; j < edges.size(); j++){
                if(edges.get(j).getId().equals(edge.getId())){
                    flow = flows.get(i);
                    break outerloop;
                }
            }
        }
        
        return flow;
    }
    public static ArrayList getFlowSWithEdge(ArrayList<Eflow> flows, Eedge edge){
        ArrayList<Eflow> retFlows = new ArrayList<Eflow>();
       
        for(int i = 0; i < flows.size(); i++){
            ArrayList<Eedge> edges = flows.get(i).getEdges();
            boolean exists = false;
            
            for(int j = 0; j < edges.size(); j++){
                
                if(edges.get(j).getId().equals(edge.getId())){
                    exists = true;
                }
                
            }
            
            if(exists){
               retFlows.add(flows.get(i));
            }
        }
        
        return retFlows;
    }
    
    /**
     * s* megállapítása egy gráfon belül, a flownak megfelelő éleken
     * @param flow
     * @return 
     */
    public static double getSstar(Egraph graph, Eflow flow){
        double sStar = 0.0;
        double minSlack = 0.0;
        Eedge thisEdge = null;
        
        // megkeressük a minimális slack-et
        /*
        for(int i = 0; i < flow.getEdges().size(); i++){
            thisEdge = graph.getEdges().get(flow.getEdges().get(i).getId());
            if(thisEdge.isSlack()){
                    System.out.println(thisEdge.getId()+" , "+thisEdge.getSlack());
                if(thisEdge.getSlack() < minSlack || minSlack == 0.0){
                    minSlack = thisEdge.getSlack();
                }
            }
        }
        */
        Iterator it = graph.getEdges().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            thisEdge = (Eedge)  pair.getValue();
            if(thisEdge.isSlack()){
                if(thisEdge.getSlack() < minSlack || minSlack == 0.0){
                    minSlack = thisEdge.getSlack();
                }
            }
        }
        
        return minSlack == 0.0 ? 0.0 : getRndNmbr(0.0001, minSlack);
    }
    
    /**
     * Véletlen szám generálása
     * @param rangeMin
     * @param rangeMax
     * @return 
     */
    private static double getRndNmbr(double rangeMin, double rangeMax){
        Random r = new Random();
        return rangeMin + (rangeMax - rangeMin) * r.nextDouble();
    }
    
    
    private static ArrayList breadthTree(Egraph g, Enode n){
        HashMap<String, Eedge> edges = n.getOutEdges();
       
        HashMap<String,Eedge> temp = new HashMap<String, Eedge>();
        temp.putAll(edges);
        
        temp = iterateNodes(edges, new HashMap<String, Eedge>(), new HashMap<String, Integer>(), g.getNodes().size());
        
        // a szélességi érintette-e a csúcsot
        boolean nExistIn = nodeExistsOnList(n, temp);
        
        
        ArrayList retDatas = new ArrayList();
        retDatas.add(0, nExistIn);
        
        //az E k halmaz azokat az éleket tartalmazza, melyekre a 3. pont a. feltétele teljesül
        ArrayList eK = new ArrayList();
        //az E s halmaz azokat az éleket tartalmazza, melyek a b. feltétellel ki lettek választva
        ArrayList eS = new ArrayList();
        
        for (Entry<String,Eedge> entry : temp.entrySet()) {
            Eedge teedge = (Eedge) entry.getValue();
            
            if(teedge.getFlow() > 0.0){
                eK.add(teedge);
            }
            if(teedge.isSlack()){
                eS.add(teedge);
            }
        }
        
        retDatas.add(1, eK);
        retDatas.add(2, eS);
        
        
        return retDatas;
    }
    
    // szélességi fa bejárás
    private static HashMap<String, Eedge> iterateNodes(HashMap<String, Eedge> edges, HashMap<String, Eedge> traveledEdges, HashMap<String, Integer> traveledNodesCount, int nodesCount){
        Enode vComma = null, vCommaComma = null;
        Eedge thisEdge = null;
        HashMap<String, Eedge> edgesGy = new HashMap<String, Eedge>();
       
        for (Entry<String,Eedge> entry : edges.entrySet()) {
            thisEdge = (Eedge) entry.getValue();
            if(thisEdge.isSlack() || thisEdge.getFlow() > 0.0){ // az él slack-el kell legyen
                
                vComma = thisEdge.getNode1();
                vCommaComma = thisEdge.getNode2();
                
                if(traveledNodesCount.get(thisEdge.getNode2().getName()) == null){
                    traveledNodesCount.put(thisEdge.getNode2().getName(), 1);
                }else{
                    traveledNodesCount.put(thisEdge.getNode2().getName(), traveledNodesCount.get(thisEdge.getNode2().getName()) + 1);
                }
                //System.out.println(" >> "+thisEdge.getId());
                
                if(traveledNodesCount.get(thisEdge.getNode2().getName()) < nodesCount){ // -> KÖR
                    traveledEdges.put(thisEdge.getId(), thisEdge);
                    
                    if(vCommaComma.getOutEdges().size() > 0){
                        for (Entry<String,Eedge> entry2 : vCommaComma.getOutEdges().entrySet()) {
                            Eedge entry2Edge = entry2.getValue();
                            
                            if(traveledNodesCount.get(entry2Edge.getNode2().getName()) == null){
                                traveledNodesCount.put(entry2Edge.getNode2().getName(), 1);
                            }else{
                                traveledNodesCount.put(entry2Edge.getNode2().getName(), traveledNodesCount.get(entry2Edge.getNode2().getName()) + 1);
                            }
                            
                            if(traveledNodesCount.get(entry2Edge.getNode2().getName()) < nodesCount){ // -> KÖR
                                edgesGy.put(entry2.getKey(), entry2.getValue());
                            }
                        }
                    }
                }
            }
        }
        
        if(edgesGy.size() > 0){
            iterateNodes(edgesGy, traveledEdges, traveledNodesCount, nodesCount);
        }
        
        return traveledEdges;
    }
    
    // egy csúcs benne van az az élek cél halmazában
    private static boolean nodeExistsOnList(Enode node, HashMap<String, Eedge> edges){
        boolean isExists = false;
        Eedge thisEdge = null;
        
        for (Entry<String,Eedge> entry : edges.entrySet()) {
            thisEdge = (Eedge) entry.getValue();
            if(thisEdge.getNode2().getName().equals(node.getName())){
                isExists = true;
            }
        }
        return isExists;
    }
    
    private static Egraph reversalEdges(Egraph g, ArrayList<Eedge> edges){
        for(int iEk = 0; iEk < edges.size(); iEk++){
            Eedge EkEdge = (Eedge) edges.get(iEk);
            Eedge graphEkEdge = g.getEdges().get(EkEdge.getId());
            Enode n1 = graphEkEdge.getNode1();
            Enode n2 = graphEkEdge.getNode2();
            
            graphEkEdge.setNode2(n1);
            graphEkEdge.setNode1(n2);
            graphEkEdge.setIdAutomatic();
            
        }
        
        return g;
    }
    
    private static Egraph reversalEdgesEflow(Egraph g, Eflow eflow){
        
        for(int iEk = 0; iEk < eflow.getEdges().size(); iEk++){
            Eedge EkEdge = (Eedge) eflow.getEdges().get(iEk);
            Eedge graphEkEdge = g.getEdges().get(EkEdge.getId());
            Enode n1 = graphEkEdge.getNode1();
            Enode n2 = graphEkEdge.getNode2();
            
            graphEkEdge.setNode2(n1);
            graphEkEdge.setNode1(n2);
            graphEkEdge.setIdAutomatic();
        }
        
        g.resetNodeOutEdges();
        
        return g;
    }
    
}

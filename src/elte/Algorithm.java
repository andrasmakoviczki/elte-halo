/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elte;

import egraph.*;
import java.util.ArrayList;

/**
 *
 * @author pcgejza
 */
public class Algorithm {
    
    public static Eflow a1(Egraph graph, ArrayList<Eflow> flows){
        Eflow f = new Eflow();
        graph.setFlows(flows);
        
        
        Eedge edgeWithoutSlack = null;
        
        
        // 1. vegyünk egy slack nélküli élt
        edgeWithoutSlack = graph.getWithoutSlackEdge();
        
        return f;
    }
    
    
}

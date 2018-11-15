/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.pa.v0;

import java.util.ArrayList;
import java.util.List;

import model.Connection;
import model.GestorPercurso;
import model.GestorPercurso.Criteria;
import model.Place;

/**
 *
 * @author Darfkman
 */
public class ProjetoPAV0 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
        GestorPercurso gestor = new GestorPercurso();
        
        gestor.load();
        System.out.println(gestor.toString());
        /*Minimum path between 2 places (and it returns to the entrance)*/
        Place p1 = gestor.getPlace(1);
        Place p2 = gestor.getPlace(4);
        
        System.out.println("Places:");
        System.out.println(p1);
        System.out.println(p2);
        
        List<Place> bestPlacesToGo = new ArrayList<>();
        List<Connection> bestConnectionsToGo = new ArrayList<>();
        gestor.minimumCostPath(Criteria.COST, p1, p2, bestPlacesToGo,bestConnectionsToGo,0);
        
        System.out.println(bestPlacesToGo);
        System.out.println(bestConnectionsToGo);
        
        /*User choose up to 3 places to visit on his trip
         * Calculate the minimum short parth as well as what paths to take*/
        Place p3 = gestor.getPlace(3);
        List<Place> placesToVisit = new ArrayList<>();
        placesToVisit.add(p2);
        placesToVisit.add(p3);
        
        System.out.println(p2);
        System.out.println(p3);
        
        List<Place> fullVisits = new ArrayList<>();
        List<Connection> fullPath = new ArrayList<>();
        /*Path from entrance, to Veados, then to Pomar and then back to entrance*/
        gestor.getPathWithInterestPoints(placesToVisit, Criteria.COST,fullVisits,fullPath);
        
        System.out.println(fullVisits);
        System.out.println(fullPath);
    }
    
}

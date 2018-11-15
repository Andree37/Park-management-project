/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.pa.v0;

import FileHandler.ObjectsFileHandler;
import dijkstra.graph.Vertex;

import java.util.ArrayList;
import java.util.List;

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
        Place p1 = gestor.getPlace(2);
        Place p2 = gestor.getPlace(1);
        
        List<Place> bestPlacesToGo = new ArrayList<>();
        gestor.minimumCostPath(Criteria.COST, p1, p2, bestPlacesToGo);
        
        System.out.println(bestPlacesToGo);
    }
    
}

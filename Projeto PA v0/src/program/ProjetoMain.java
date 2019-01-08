/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

import Logger.Logger;
import java.util.ArrayList;
import java.util.List;

import model.Gestor.Connection;
import model.Gestor.GestorPercurso;
import model.Gestor.GestorPercurso.Criteria;
import model.Gestor.Place;
import model.Gestor.ResultadoPercurso;

/**
 * Class ProjetoMain, is responsible for the main method of this project
 *
 * @author (Daniel Afonso e André Ribeiro)
 * @version (19/11/18)
 */
public class ProjetoMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /*THIS MAIN IS TO BE COMPARED USING MAPA1.DAT, IT WORKS WITH OTHERS 
		 * BUT THE CONTEXT OF THE COMMENTS NEXT TO THE METHODS DON'T APPLY*/
        //Must Load file from /load/mapa1.dat
        GestorPercurso gestor = new GestorPercurso();
        try {
            gestor.load();
        } catch (NullPointerException e) {
            System.err.println("Must choose a readable file");
            return;
        }

        System.out.println("\ntoString() of the manager to show it has been loaded correctly");
        System.out.println(gestor.toString());
        /* Minimum path between 2 places (and it returns to the entrance) */
        System.out.println("\nA quick tour, from the entrance to a point, this case, Veados");
        Place p1 = gestor.getVertexWith(1); //Entrada
        Place p2 = gestor.getVertexWith(4); //Veados

        System.out.println("Places:");
        System.out.println(p1);
        System.out.println(p2);

        List<Place> bestPlacesToGo = new ArrayList<>();
        List<Connection> bestConnectionsToGo = new ArrayList<>();
        gestor.minimumCostPath(Criteria.COST, p1, p2, bestPlacesToGo, bestConnectionsToGo, 0, true, true);

        System.out.println(bestPlacesToGo);
        System.out.println(bestConnectionsToGo);

        /*
		 * User choose 3 places to visit on his trip Calculate the minimum short
		 * parth as well as what paths to take.
                 * User can choose as many places to visit as there are places on the gestor
         */

 /* Paths of the choice of the user, for example */
        Place p3 = gestor.getVertexWith(3); //Pomar
        Place p4 = gestor.getVertexWith(5);  //Moinho
        List<Place> placesToVisit = new ArrayList<>();
        placesToVisit.add(p2);
        placesToVisit.add(p3);
        placesToVisit.add(p4);
        System.out.println("\nChoice of paths:");
        System.out.println(p3);
        System.out.println(p2);
        System.out.println(p4);

        /*
		 * Path from entrance, to Pomar then Veados, then Moinho and then back to entrance with
		 * bridges and with the cost criteria and bikes
         */
        ResultadoPercurso result = null;
        
        result = gestor.getPathWithInterestPoints(placesToVisit, Criteria.COST, true, true);
        System.out.println("\nPaths with bridges and counting cost from Entrance, Pomar then Veados, then Moinho");
        System.out.println(result.getListPlacesCopy());
        System.out.println(result.getListConnectionsCopy());
        System.out.println("Cost :" + result.getCost());

        /*
		 * Path from entrance, to Pomar then Veados, then Moinho and then back to entrance with
		 * bridges and with the distance criteria and bikes
         */

        
        result = gestor.getPathWithInterestPoints(placesToVisit, Criteria.DISTANCE, true, true);
        System.out.println("\nPaths with bridges and counting distance from Entrance, Pomar then Veados, then Moinho");
        System.out.println(result.getListPlacesCopy());
        System.out.println(result.getListConnectionsCopy());
        System.out.println("Cost :" + result.getCost());

        /*
		 * Path from entrance, to Pomar then Veados, then Moinho and then back to entrance
		 * without bridges with the cost criteria without bikes
         */

        result = gestor.getPathWithInterestPoints(placesToVisit, Criteria.COST, false, false);
        System.out.println("\nPaths with no bridges and counting cost from Entrance, Pomar then Veados, then Moinho");
        System.out.println(result.getListPlacesCopy());
        System.out.println(result.getListConnectionsCopy());
        System.out.println("Cost :" + result.getCost());

        /*
		 * Path from entrance, to Pomar then Veados, then Moinho and then back to entrance
		 * without bridges with the distance criteria without bikes
         */

        result = gestor.getPathWithInterestPoints(placesToVisit, Criteria.DISTANCE, false, false);
        System.out.println("\nPaths with no bridges and counting distance from Entrance, Pomar then Veados, then Moinho");
        System.out.println(result.getListPlacesCopy());
        System.out.println(result.getListConnectionsCopy());
        System.out.println("Cost :" + result.getCost());
        System.out.println("Criteria: " + result.getCriteria());
        System.out.println("/n Logger: " + Logger.getInstance().toString());
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

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

		/*THIS MAIN IS TO BE COMPARED USING MAPA1.DAT, IT WORKS WITH OTHERS 
		 * BUT THE CONTEXT OF THE COMMENTS NEXT TO THE METHODS DON'T APPLY*/
		
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
		Place p1 = gestor.getVertexWith(1);
		Place p2 = gestor.getVertexWith(4);

		System.out.println("Places:");
		System.out.println(p1);
		System.out.println(p2);

		List<Place> bestPlacesToGo = new ArrayList<>();
		List<Connection> bestConnectionsToGo = new ArrayList<>();
		gestor.minimumCostPath(Criteria.COST, p1, p2, bestPlacesToGo, bestConnectionsToGo, 0, true);

		System.out.println(bestPlacesToGo);
		System.out.println(bestConnectionsToGo);

		/*
		 * User choose up to 3 places to visit on his trip Calculate the minimum short
		 * parth as well as what paths to take
		 */

		/* Paths of the choice of the user, for example */
		Place p3 = gestor.getVertexWith(3);
		Place p4 = gestor.getVertexWith(6);
		List<Place> placesToVisit = new ArrayList<>();
		placesToVisit.add(p2);
		placesToVisit.add(p3);
		placesToVisit.add(p4);
		System.out.println("\nChoice of paths:");
		System.out.println(p2);
		System.out.println(p3);
		System.out.println(p4);

		/*
		 * Path from entrance, to Pomar then Veados and then back to entrance with
		 * bridges and with the cost criteria
		 */
		int cost = 0;
		List<Place> fullVisits = new ArrayList<>();
		List<Connection> fullPath = new ArrayList<>();
		cost = gestor.getPathWithInterestPoints(placesToVisit, Criteria.COST, fullVisits, fullPath, true);
		System.out.println("\nPaths with bridges and counting cost");
		System.out.println(fullVisits);
		System.out.println(fullPath);
		System.out.println("Cost :"+cost);
		
		/*
		 * Path from entrance, to Pomar then Veados and then back to entrance with
		 * bridges and with the distance criteria
		 */
		List<Place> fullVisitsDistance = new ArrayList<>();
		List<Connection> fullPathDistance = new ArrayList<>();
		cost = gestor.getPathWithInterestPoints(placesToVisit, Criteria.DISTANCE, fullVisitsDistance, fullPathDistance, true);
		System.out.println("\nPaths with bridges and counting distance");
		System.out.println(fullVisitsDistance);
		System.out.println(fullPathDistance);
		System.out.println("Cost :"+cost);

		/*
		 * Path from entrance, to Pomar then Veados and then back to entrance
		 * without bridges with the cost criteria
		 */
		List<Place> fullVisitsWalk = new ArrayList<>();
		List<Connection> fullPathWalk = new ArrayList<>();
		cost = gestor.getPathWithInterestPoints(placesToVisit, Criteria.COST, fullVisitsWalk, fullPathWalk, false);
		System.out.println("\nPaths with no bridges and counting cost");
		System.out.println(fullVisitsWalk);
		System.out.println(fullPathWalk);
		System.out.println("Cost :"+cost);
		
		/*
		 * Path from entrance, to Pomar then Veados and then back to entrance
		 * without bridges with the distance criteria
		 */
		List<Place> fullVisitsWalkDistance = new ArrayList<>();
		List<Connection> fullPathWalkDistance = new ArrayList<>();
		cost = gestor.getPathWithInterestPoints(placesToVisit, Criteria.DISTANCE, fullVisitsWalkDistance, fullPathWalkDistance, false);
		System.out.println("\nPaths with no bridges and counting distance");
		System.out.println(fullVisitsWalkDistance);
		System.out.println(fullPathWalkDistance);
		System.out.println("Cost :"+cost);
	}

}

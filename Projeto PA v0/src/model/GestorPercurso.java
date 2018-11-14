/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import FileHandler.Objects;
import FileHandler.ObjectsFileHandler;
import dijkstra.graph.Edge;
import dijkstra.graph.Graph;
import dijkstra.graph.GraphEdgeList;
import dijkstra.graph.InvalidEdgeException;
import dijkstra.graph.InvalidVertexException;
import dijkstra.graph.Vertex;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.Connection;

/**
 *
 * @author Darfkman
 */
public class GestorPercurso {
	public enum Criteria {
		DISTANCE, COST;

		public String getUnit() {
			switch (this) {
			case COST:
				return "�-";

			case DISTANCE:
				return "Miles";
			}
			return "Unknown";
		}
	};

	private final Graph<Place, Connection> graph;

	public GestorPercurso() {
		this.graph = new GraphEdgeList<>();

	}

	private Vertex<Place> checkPlace(Place place) throws InvalidVertexException {
		if (place == null)
			throw new InvalidVertexException("Place cannot be null");

		Vertex<Place> find = null;
		for (Vertex<Place> v : graph.vertices()) {
			if (v.element().equals(place)) { 
				find = v;
			}
		}

		if (find == null)
			throw new InvalidVertexException("Place with id (" + place.getId() + ") does not exist");

		return find;
	}
	
	public void load() {
		Objects objects = ObjectsFileHandler.load();
		List<Connection> connections = objects.listConnections();
		List<Place> places = objects.listPlaces();
		addPlaces(places);
		addConnections(connections, places);
	}

	private void addPlaces(List<Place> places) throws InvalidVertexException {
		for (Place place : places) {
			if (place == null)
				throw new InvalidVertexException("Place cannot be null");

			try {
				graph.insertVertex(place);
			} catch (InvalidVertexException e) {
				throw new InvalidVertexException("Place with id (" + place.getId() + ") already exists");

			}
		}
	}

	private void addConnections(List<Connection> connections,List<Place> places) throws InvalidEdgeException {

		for (Connection con : connections) {
			if (con == null)
				throw new InvalidEdgeException("Connection is null");
			Vertex<Place> a1 = checkPlace(places.get(con.getConnections().get(0) - 1));
			Vertex<Place> a2 = checkPlace(places.get(con.getConnections().get(1) - 1));
			
			try {
				graph.insertEdge(a1, a2, con);
			} catch (InvalidVertexException e) {
				throw new InvalidEdgeException("The connection (" + con.getName() + ") already exists");
			}
		}
	}

	public List<Connection> getConnectionsBetween(Place place1, Place place2) throws InvalidVertexException {
		Vertex<Place> a1 = checkPlace(place1);
		Vertex<Place> a2 = checkPlace(place2);
		List<Connection> connectionslt = new ArrayList<>();

		for (Edge<Connection, Place> e : graph.edges()) {
			if (e.vertices()[0].equals(a1) && e.vertices()[1].equals(a2)
					|| e.vertices()[1].equals(a1) && e.vertices()[0].equals(a2)) {
				connectionslt.add(e.element());
			}
		}
		return connectionslt;
	}

	@Override
	public String toString() {
		String info = "";
		info += "PATH PLANER (" + graph.numVertices() + " places | " + graph.numEdges() + " connections)\n";
		for (Vertex<Place> place1 : graph.vertices()) {
			for (Vertex<Place> place2 : graph.vertices()) {
				if (!place1.equals(place2)) {

					info += place1.element().toString() + " TO " + place2.element().toString() + "\n";

					List<Connection> cons = getConnectionsBetween(place1.element(), place2.element());
					if (cons.size() != 0) {
						info += "\t" + cons.get(0).toString() + "\n";
					} else {
						info += "\t(no connections)\n";
					}
					if (cons.size() > 1) {
						info += "\t" + cons.get(1).toString() + "\n";
					}
					info += "\n";
				}
			}
		}
		return info;
	}
	/*
	 * public int minimumCostPath(Criteria criteria, Airport orig, Airport dst,
	 * List<Airport> airports, List<Flight> flights) throws FlightPlannerException {
	 * HashMap<Vertex<Airport>, Double> costs = new HashMap<>();
	 * 
	 * HashMap<Vertex<Airport>, Vertex<Airport>> pre = new HashMap<>();
	 * 
	 * Vertex<Airport> origin = checkAirport(orig); Vertex<Airport> destination =
	 * checkAirport(dst);
	 * 
	 * dijkstra(criteria, origin, destination, costs, pre, airports, flights);
	 * 
	 * double cost = costs.get(destination); return (int) cost; }
	 * 
	 * private void dijkstra(Criteria criteria, Vertex<Airport> orig,
	 * Vertex<Airport> destination, Map<Vertex<Airport>, Double> costs,
	 * Map<Vertex<Airport>, Vertex<Airport>> predecessors, List<Airport> airports,
	 * List<Flight> flights) {
	 * 
	 * Set<Vertex<Airport>> unvisited = new HashSet<>();
	 * 
	 * for(Vertex<Airport> v : graph.vertices()) { unvisited.add(v);
	 * costs.put(v,Double.MAX_VALUE); predecessors.put(v, null); }
	 * costs.put(orig,0.0);
	 * 
	 * while(!unvisited.isEmpty()) { Vertex<Airport> u =
	 * findLowerCostVertex(unvisited, costs); if(costs.get(u) == Double.MAX_VALUE) {
	 * throw new InvalidParameterException("Erro"); } unvisited.remove(u);
	 * for(Edge<Flight, Airport> edge: graph.incidentEdges(u)) { Vertex<Airport> v =
	 * graph.opposite(u, edge); if(unvisited.contains(v)) { double flightCost = 0;
	 * switch(criteria) { case COST: flightCost = edge.element().getPriceEuros() +
	 * costs.get(u); break; case DISTANCE: flightCost =
	 * edge.element().getDistanceMiles() + costs.get(u); break; case TIME:
	 * flightCost = edge.element().getDurationMinutes() + costs.get(u); break;
	 * 
	 * } if(flightCost < costs.get(v)) { costs.put(v, flightCost);
	 * predecessors.put(v, u);
	 * 
	 * } }
	 * 
	 * }
	 * 
	 * } airports.add(destination.element()); do {
	 * airports.add(0,predecessors.get(destination).element()); Flight lowCost =
	 * null; List list =
	 * getFlightsBetween(destination.element(),predecessors.get(destination).element
	 * ()); Flight f1 = null; Flight f2 = null; if(list.size() > 1){ f1 =
	 * getFlightsBetween(destination.element(),predecessors.get(destination).element
	 * ()).get(0); f2 =
	 * getFlightsBetween(destination.element(),predecessors.get(destination).element
	 * ()).get(1); }else if(list.size() > 0){ f1 =
	 * getFlightsBetween(destination.element(),predecessors.get(destination).element
	 * ()).get(0); lowCost = f1; } switch(criteria) { case COST: if(list.size() >
	 * 1){ if(f1.getPriceEuros() < f2.getPriceEuros()){ lowCost = f1; }else{ lowCost
	 * = f2; } } break; case DISTANCE: if(list.size() > 1){
	 * if(f1.getDistanceMiles()< f2.getDistanceMiles()){ lowCost = f1; }else{
	 * lowCost = f2; } } break; case TIME: if(list.size() > 1){
	 * if(f1.getDurationMinutes()< f2.getDurationMinutes()){ lowCost = f1; }else{
	 * lowCost = f2; } } break; } flights.add(0,lowCost); destination =
	 * predecessors.get(destination);
	 * 
	 * } while (predecessors.get(destination)!= null);
	 * 
	 * 
	 * }
	 * 
	 * private Vertex<Airport> findLowerCostVertex(Set<Vertex<Airport>> unvisited,
	 * Map<Vertex<Airport>, Double> costs) { double initial = Double.MAX_VALUE;
	 * Vertex<Airport> bestAirport = null; for (Vertex<Airport> airport : unvisited)
	 * { if (costs.get(airport) <= initial) { bestAirport = airport; initial =
	 * costs.get(airport); } } return bestAirport; }
	 */
}

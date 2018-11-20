/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import FileHandler.Objects;
import FileHandler.ObjectsFileHandler;
import diGraph.DiGraph;
import diGraph.DiGraphImpl;
import graph.Edge;
import graph.GraphEdgeList;
import graph.InvalidEdgeException;
import graph.InvalidVertexException;
import graph.Vertex;
import model.Connection.Type;

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
				return "Dollars";

			case DISTANCE:
				return "Miles";
			}
			return "Unknown";
		}
	};

	private final DiGraph<Place, Connection> graph;

	public GestorPercurso() {
		this.graph = new DiGraphImpl<>();
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

	public void load() throws NullPointerException {
		Objects objects = ObjectsFileHandler.load();
		List<Connection> connections = objects.listConnections();
		List<Place> places = objects.listPlaces();
		addPlaces(places);
		addConnections(connections, places);
	}

	private void addPlaces(List<Place> places) throws InvalidVertexException {
		for (Place place : places) {
			if (place == null) {
				throw new InvalidVertexException("Place cannot be null");
			}
			addPlace(place);
		}
	}

	private void addConnections(List<Connection> connections, List<Place> places) throws InvalidEdgeException {

		for (Connection con : connections) {
			if (con == null)
				throw new InvalidEdgeException("Connection is null");
			Vertex<Place> a1 = checkPlace(places.get(con.getConnections().get(0) - 1));
			Vertex<Place> a2 = checkPlace(places.get(con.getConnections().get(1) - 1));

			addConnection(a1, a2, con);
			if (con.getType().equals(Type.PATH.getUnit())) {
				Connection back = new Connection(con.getId() + 50, con.getType(), con.getName(), con.getConnections(),
						con.isAvailable(), con.getPrice(), con.getDistance());
				addConnection(a2, a1, back);
			}
		}
	}

	public Vertex<Place> addPlace(Place place) {
		return graph.insertVertex(place);
	}

	public Edge<Connection, Place> addConnection(Vertex<Place> p1, Vertex<Place> p2, Connection edgeElement) {
		return graph.insertEdge(p1, p2, edgeElement);
	}

	public Iterable<Vertex<Place>> getPlaces() {
		return graph.vertices();
	}

	public Iterable<Edge<Connection, Place>> getConnections() {
		return graph.edges();
	}

	public Place getVertexWith(int id) {
		Place place = null;
		for (Vertex<Place> p : graph.vertices()) {
			if (p.element().getId() == id) {
				place = p.element();
			}
		}
		return place;
	}

	@Override
	public String toString() {
		String info = "";
		info += "PATH PLANER (" + graph.numVertices() + " places | " + graph.numEdges() + " connections)\n";
		for (Vertex<Place> place1 : graph.vertices()) {
			for (Vertex<Place> place2 : graph.vertices()) {
				if (!place1.equals(place2)) {

					info += place1.element().toString() + " TO " + place2.element().toString() + "\n";

					List<Connection> cons = graph.getConnectionsBetween(place1.element(), place2.element());
					if (!cons.isEmpty()) {
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

	public int minimumCostPath(Criteria criteria, Place orig, Place dst, List<Place> places,
			List<Connection> connections, int insert, boolean bridge, boolean bike) throws GestorPercursoException {

		HashMap<Vertex<Place>, Double> distance = new HashMap<>();

		HashMap<Vertex<Place>, Vertex<Place>> pre = new HashMap<>();

		HashMap<Vertex<Place>, Edge<Connection, Place>> connMap = new HashMap<>();

		Vertex<Place> origin = checkPlace(orig);
		Vertex<Place> destination = checkPlace(dst);

		dijkstra(criteria, origin, distance, pre, connMap, bridge, bike);

		double cost = distance.get(destination);

		while (destination != origin) {
			places.add(insert, destination.element());
			connections.add(insert, connMap.get(destination).element());
			destination = pre.get(destination);
		}

		return (int) cost;
	}

	private void dijkstra(Criteria criteria, Vertex<Place> orig, Map<Vertex<Place>, Double> costs,
			Map<Vertex<Place>, Vertex<Place>> predecessors, HashMap<Vertex<Place>, Edge<Connection, Place>> connMap,
			boolean bridges, boolean bike) {

		List<Vertex<Place>> unvisited = new ArrayList<>();
		for (Vertex<Place> v : graph.vertices()) {
			unvisited.add(v);
			costs.put(v, Double.MAX_VALUE);
			predecessors.put(v, null);
		}
		costs.put(orig, 0.0);
		while (!unvisited.isEmpty()) {
			Vertex<Place> u = findLowerCostVertex(unvisited, costs);
			unvisited.remove(u);
			for (Edge<Connection, Place> edge : graph.outboundEdges(u)) {
				if (bike && !edge.element().isAvailable()) {
					continue;
				}
				Vertex<Place> opposite = graph.opposite(u, edge);
				if (unvisited.contains(opposite)) {
					double cost = 0;
					switch (criteria) {
					case COST:
						cost = edge.element().getPrice() + costs.get(u);
						break;
					case DISTANCE:
						cost = edge.element().getDistance() + costs.get(u);
						break;

					}
					if (bridges) {
						if (cost < costs.get(opposite)) {
							costs.put(opposite, cost);
							predecessors.put(opposite, u);
							connMap.put(opposite, edge);
						}
					} else {
						if (cost < costs.get(opposite) && edge.element().getType().equals(Type.PATH.getUnit())) {
							costs.put(opposite, cost);
							predecessors.put(opposite, u);
							connMap.put(opposite, edge);
						}
					}
				}
			}
		}

	}

	private Vertex<Place> findLowerCostVertex(List<Vertex<Place>> unvisited, Map<Vertex<Place>, Double> costs) {
		double initial = Double.MAX_VALUE;

		Vertex<Place> best = null;

		for (Vertex<Place> place : unvisited) {
			if (costs.get(place) <= initial) {
				best = place;
				initial = costs.get(place);
			}
		}
		return best;
	}

	public int getPathWithInterestPoints(List<Place> placesToVisit, Criteria criteria, List<Place> fullVisits,
			List<Connection> fullPath, boolean bridge, boolean bike) {

		int cost = 0;
		int insert = 0; // where to put the next places
		Place entrance = getVertexWith(1); // returns the entrance, first place to come from and last to go
		Place orig = entrance; // first origin
		Place dst = null; // all the destinations that the customer wants to see
                
		for (Place p : placesToVisit) {
			dst = p; // destination to calculate
                        
			cost += minimumCostPath(criteria, orig, dst, fullVisits, fullPath, insert, bridge, bike);
                        insert = fullVisits.size(); // where to insert on the lists
                        
			orig = p; // calculation for next destination
			
		}
		dst = entrance;
		cost += minimumCostPath(criteria, orig, dst, fullVisits, fullPath, insert, bridge, bike); // calculation of the
																									// path
		// from the
		// last
		// destination back to the entrance
		fullVisits.add(0, entrance); // put the entrance in the beggining, to show where we started
		return cost; // returns final cost
	}
        
        
}

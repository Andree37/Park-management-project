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
import dijkstra.graph.Edge;
import dijkstra.graph.Graph;
import dijkstra.graph.GraphEdgeList;
import dijkstra.graph.InvalidEdgeException;
import dijkstra.graph.InvalidVertexException;
import dijkstra.graph.Vertex;
import model.Connection.Type;

/**
 *
 * @author Darfkman
 */
public class GestorPercurso implements DiWeightedGraph {
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

	private void addConnections(List<Connection> connections, List<Place> places) throws InvalidEdgeException {

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
			if (e.element().getType().equals(Type.BRIDGE.getUnit())) { // if its bridge it returns only orig->dest
				if (new ConnectionBridge(e, a1, a2).isConnectedVertices()) {
					connectionslt.add(e.element());
				}
			} else { // if its path then it returns orig -> dest and dest -> orig
				if (new ConnectionPath(e, a1, a2).isConnectedVertices()) {
					connectionslt.add(e.element());
				}
			}
		}
		return connectionslt;
	}

	public Iterable<Vertex<Place>> getPlaces() {
		return graph.vertices();
	}

	public Iterable<Edge<Connection, Place>> getConnections() {
		return graph.edges();
	}

	@Override
	public Place getPlace(int id) {
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

	public int minimumCostPath(Criteria criteria, Place orig, Place dst, List<Place> places)
			throws GestorPercursoException {
		HashMap<Vertex<Place>, Double> distance = new HashMap<>();

		HashMap<Vertex<Place>, Vertex<Place>> pre = new HashMap<>();

		Vertex<Place> origin = checkPlace(orig);
		Vertex<Place> destination = checkPlace(dst);

		dijkstra(criteria, origin, distance, pre);

		double cost = distance.get(destination);

		while (destination != origin) {
			places.add(0, destination.element());
			destination = pre.get(destination);
		}

		places.add(0, origin.element());

		return (int) cost;
	}

	private void dijkstra(Criteria criteria, Vertex<Place> orig, Map<Vertex<Place>, Double> costs,
			Map<Vertex<Place>, Vertex<Place>> predecessors) {

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
			for (Edge<Connection, Place> edge : incidentEdges(u)) {
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
					if (cost < costs.get(opposite)) {
						costs.put(opposite, cost);
						predecessors.put(opposite, u);
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

	@Override
	public int numVertices() {
		return graph.numVertices();
	}

	@Override
	public int numEdges() {
		return graph.numEdges();
	}

	@Override
	public Iterable<Vertex<Place>> vertices() {
		return graph.vertices();
	}

	@Override
	public Iterable<Edge<Connection, Place>> edges() {
		return graph.edges();
	}

	@Override
	public Iterable<Edge<Connection, Place>> incidentEdges(Vertex<Place> v) throws InvalidEdgeException {
		List<Edge<Connection, Place>> incidentEdges = new ArrayList<>();
		for (Edge<Connection, Place> edge : graph.edges()) {
			if (edge == null) {
				throw new InvalidEdgeException("Edge can not be null");
			}
			if (edge.element().getType().equals(Type.BRIDGE.getUnit())) {
				if (edge.vertices()[0] == v) { /* edge.vertices()[0] == v || edge.vertices()[1] == v */
					incidentEdges.add(edge);
				}
			} else {
				if (edge.vertices()[0] == v || edge.vertices()[1] == v) {
					incidentEdges.add(edge);
				}
			}

		}

		return incidentEdges;
	}

	@Override
	public Vertex<Place> opposite(Vertex<Place> v, Edge<Connection, Place> e)
			throws InvalidVertexException, InvalidEdgeException {

		Vertex<Place> vertex = checkPlace(v.element());
		Edge<Connection, Place> conn = null;

		if (vertex == null) {
			throw new InvalidVertexException("Vertex can not be null");
		}
		if (e == null) {
			throw new InvalidEdgeException("Edge can not be null");
		}

		for (Edge<Connection, Place> edge : graph.edges()) {
			if (e.equals(edge)) {
				conn = edge;
			}
		}

		if (!(conn.vertices()[0].equals(vertex) || conn.vertices()[1].equals(vertex)))
			return null; /* this edge does not connect vertex v */
		if (!(conn.element().getType().equals(Type.BRIDGE.getUnit()))) {
			if (conn.vertices()[0] == v)
				return conn.vertices()[1];
			else
				return conn.vertices()[0];
		} else {
			return null; // in case it is a bridge, we shouldnt return the opposite
		}
	}

	@Override
	public boolean areAdjacent(Vertex<Place> u, Vertex<Place> v) throws InvalidVertexException {
		//we allow loops, so we do not check if u == v
        if(u == null || v == null) {
        	throw new InvalidVertexException("Vertex can not be not");
        }
        ConnectionsBetween connections = null;
        
        /* find and edge that contains both u and v keeping in mind, that bridges are unidrectional*/
        for (Edge<Connection, Place> edge : graph.edges()) {
        	if(edge.element().getType().equals(Type.BRIDGE.getUnit())) {
        		connections = new ConnectionBridge(edge, u, v);
        	}
        	else {
        		connections = new ConnectionPath(edge, u, v);
        	}
            	if(connections.isConnectedVertices()) {
            		return true;
            	}
        }
        return false;
	}

	@Override
	public Vertex<Place> insertVertex(Place vElement) {
		return graph.insertVertex(vElement);
	}

	@Override
	public Edge<Connection, Place> insertEdge(Vertex<Place> u, Vertex<Place> v, Connection edgeElement)
			throws InvalidVertexException {
		if(u == null || v == null) {
			throw new InvalidVertexException("Vertex can not be null");
		}
		
		return graph.insertEdge(u, v, edgeElement);
	}

	@Override
	public Edge<Connection, Place> insertEdge(Place vElement1, Place vElement2, Connection edgeElement)
			throws InvalidVertexException {
		if(vElement1 == null || vElement2 == null) {
			throw new InvalidVertexException("Vertex can not be null");
		}
		
		return graph.insertEdge(vElement1, vElement2, edgeElement);
	}

	@Override
	public Place removeVertex(Vertex<Place> v) throws InvalidVertexException {
		if(v == null) {
			throw new InvalidVertexException("Vertex can not be null");
		}
		return graph.removeVertex(v);
	}

	@Override
	public Connection removeEdge(Edge<Connection, Place> e) throws InvalidEdgeException {
		if(e == null) {
			throw new InvalidEdgeException("Edge can not be null");
		}
		
		return graph.removeEdge(e);
	}

	@Override
	public Place replace(Vertex<Place> v, Place newElement) throws InvalidVertexException {
		if(v == null) {
			throw new InvalidVertexException("Vertex can not be null");
		}
		
		return graph.replace(v, newElement);
		
	}

	@Override
	public Connection replace(Edge<Connection, Place> e, Connection newElement) throws InvalidEdgeException {
		if(e == null) {
			throw new InvalidEdgeException("Edge can not be null");
		}
		
		return graph.replace(e, newElement);
	}

}

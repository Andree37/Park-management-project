package model;

import java.util.List;

import dijkstra.graph.Edge;
import dijkstra.graph.Graph;
import dijkstra.graph.Vertex;

public interface DiWeightedGraph extends Graph<Place,Connection>{
	
	/*
	 * Comment on it
	 */
	List<Connection> getConnectionsBetween(Place place1, Place place2);
	
	Iterable<Vertex<Place>> getPlaces();
	
	Iterable<Edge<Connection, Place>> getConnections();
	
	Place getPlace(int id);

	
}

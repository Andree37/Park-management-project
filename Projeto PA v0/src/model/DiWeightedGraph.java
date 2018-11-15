package model;

import java.util.List;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

public interface DiWeightedGraph extends Graph<Place,Connection>{
	
	/*
	 * Comment on it
	 */
	List<Connection> getConnectionsBetween(Place place1, Place place2);
	
	Iterable<Vertex<Place>> getPlaces();
	
	Iterable<Edge<Connection, Place>> getConnections();
	
	Place getPlace(int id);

	
}

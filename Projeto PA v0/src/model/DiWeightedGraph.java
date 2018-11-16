package model;

import java.util.List;

import graph.Edge;
import graph.InvalidEdgeException;
import graph.InvalidVertexException;
import graph.Vertex;

public interface DiWeightedGraph {

	/*
	 * Comment on it
	 */
	public List<Connection> getConnectionsBetween(Place place1, Place place2);

	public Iterable<Vertex<Place>> getPlaces();

	public Iterable<Edge<Connection, Place>> getConnections();

	public Place getPlace(int id);
	
	public Iterable<Edge<Connection, Place>> inboundEdges(Vertex<Place> v) throws InvalidEdgeException;
	
	public Iterable<Edge<Connection, Place>> outboundEdges(Vertex<Place> v) throws InvalidVertexException;

	public int numVertices();

	public int numEdges();

	public Iterable<Vertex<Place>> vertices();

	public Iterable<Edge<Connection, Place>> edges();

	public Vertex<Place> insertVertex(Place vElement);

	public Edge<Connection, Place> insertEdge(Vertex<Place> u, Vertex<Place> v, Connection edgeElement)
			throws InvalidVertexException;

	public Edge<Connection, Place> insertEdge(Place vElement1, Place vElement2, Connection edgeElement)
			throws InvalidVertexException;

	public Place removeVertex(Vertex<Place> Place) throws InvalidVertexException;

	public Connection removeEdge(Edge<Connection, Place> e) throws InvalidEdgeException;

	public Place replace(Vertex<Place> Place, Place newElement) throws InvalidVertexException;

	public Connection replace(Edge<Connection, Place> e, Connection newElement) throws InvalidEdgeException;
	
	public boolean areAdjacent(Vertex<Place> u, Vertex<Place> v) throws InvalidVertexException;

}

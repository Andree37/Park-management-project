package model;

import java.util.List;

import graph.Edge;
import graph.InvalidEdgeException;
import graph.InvalidVertexException;
import graph.Vertex;

public interface DiWeightedGraph<V,E> {

	/*
	 * Comment on it
	 */
	public List<Connection> getConnectionsBetween(V place1, V place2);

	public Iterable<Vertex<V>> getVertices();

	public Iterable<Edge<E, V>> getEdges();

	public V getVertexWith(int id);
	
	public Iterable<Edge<E, V>> inboundEdges(Vertex<V> v) throws InvalidEdgeException;
	
	public Iterable<Edge<E, V>> outboundEdges(Vertex<V> v) throws InvalidVertexException;

	public int numVertices();

	public int numEdges();

	public Iterable<Vertex<V>> vertices();

	public Iterable<Edge<E, V>> edges();

	public Vertex<V> insertVertex(V vElement);

	public Edge<E, V> insertEdge(Vertex<V> u, Vertex<V> v, E edgeElement)
			throws InvalidVertexException;

	public Edge<E, V> insertEdge(V vElement1, V vElement2, E edgeElement)
			throws InvalidVertexException;

	public V removeVertex(Vertex<V> ver) throws InvalidVertexException;

	public E removeEdge(Edge<E, V> e) throws InvalidEdgeException;

	public V replace(Vertex<V> ver, V newElement) throws InvalidVertexException;

	public E replace(Edge<E, V> e, E newElement) throws InvalidEdgeException;
	
	public boolean areAdjacent(Vertex<V> u, Vertex<V> v) throws InvalidVertexException;

}

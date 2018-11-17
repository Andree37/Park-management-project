package diGraph;

import graph.Edge;
import graph.InvalidEdgeException;
import graph.InvalidVertexException;
import graph.Vertex;

public interface DiGraph<V,E> {

	public int numVertices();

    public int numEdges();

    public Iterable<Vertex<V>> vertices();

    public Iterable<Edge<E, V>> edges();

    public Iterable<Edge<E, V>> inboundEdges(Vertex<V> v)
    		throws InvalidVertexException;
    
    public Iterable<Edge<E, V>> outboundEdges(Vertex<V> v) 
    		throws InvalidVertexException; 

    public Vertex<V> opposite(Vertex<V> v, Edge<E, V> e)
            throws InvalidVertexException, InvalidEdgeException;

    public boolean areAdjacent(Vertex<V> u, Vertex<V> v)
            throws InvalidVertexException;

    public Vertex<V> insertVertex(V vElement);

    public Edge<E, V> insertEdge(Vertex<V> u, Vertex<V> v, E edgeElement)
            throws InvalidVertexException;

    public Edge<E, V> insertEdge(V vElement1, V vElement2, E edgeElement)
            throws InvalidVertexException;

    public V removeVertex(Vertex<V> v) throws InvalidVertexException;

    public E removeEdge(Edge<E, V> e) throws InvalidEdgeException;
    
    public V replace(Vertex<V> v, V newElement) throws InvalidVertexException;

    public E replace(Edge<E, V> e, E newElement) throws InvalidEdgeException;
    
    /*to be defined by another class that uses this to have multiple types of edges (one with direction and
     * one without direction)*/
    public boolean eTypeIsUniDirectional(Edge<E, V> edge);

}

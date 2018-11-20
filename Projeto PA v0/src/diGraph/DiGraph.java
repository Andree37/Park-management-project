package diGraph;

import java.util.List;

import graph.Edge;
import graph.InvalidEdgeException;
import graph.InvalidVertexException;
import graph.Vertex;

/**
 *
 * Interface DiGraph , is the interface of a DiGraph where we set the behavior of the graph to be implemented
 *
 * @author (Daniel Afonso & André Ribeiro)
 * @version (19/11/18)
 * @param <V> - the vertex type
 * @param <E> - the edge type
 */
public interface DiGraph<V, E> {

    /**
     * Returns the number of vertices in the graph
     *
     * @return int - number of vertices in the graph
     */
    public int numVertices();

    /**
     * Returns the number of edges in the graph
     *
     * @return int - number of edges in the graph
     */
    public int numEdges();

    /**
     * Returns an iterable of vertices
     *
     * @return Iterable - iterable of vertices
     */
    public Iterable<Vertex<V>> vertices();

    /**
     * Returns an iterable of edges
     *
     * @return Iterable - iterable of edges
     */
    public Iterable<Edge<E, V>> edges();

    /**
     * Returns all the edges that are inbound of v
     *
     * @param v - the vertex to check
     * @return Iterable - edges that are inbound of v
     * @throws InvalidVertexException
     */
    public Iterable<Edge<E, V>> inboundEdges(Vertex<V> v)
            throws InvalidVertexException;

    /**
     * Returns all edges that are outbound of v
     *
     * @param v - the vertex to check
     * @return Iterable - edges that are outbound of v
     * @throws InvalidVertexException
     */
    public Iterable<Edge<E, V>> outboundEdges(Vertex<V> v)
            throws InvalidVertexException;

    /**
     * Returns the vertex opposite of this edge (keeping in mind that it only returns the edge if v is outbound of said edge)
     *
     * @param v - the vertex to check
     * @param e - the edge to check
     * @return Vertex - a vertex that is opposite of v
     * @throws InvalidVertexException
     * @throws InvalidEdgeException
     */
    public Vertex<V> opposite(Vertex<V> v, Edge<E, V> e)
            throws InvalidVertexException, InvalidEdgeException;

    /**
     * Returns if the vertices are adjacent if and only if it respects the order
     *
     * @param u - the vertex to check
     * @param v - the vertex to check
     * @return boolean - true if they are adjacent, false otherwise
     * @throws InvalidVertexException
     */
    public boolean areAdjacent(Vertex<V> u, Vertex<V> v)
            throws InvalidVertexException;

    /**
     * Inserts a vertex in the graph using the vertex element
     *
     * @param vElement - element of the vertex to add
     * @return Vertex - the vertex that is inserted in the graph
     */
    public Vertex<V> insertVertex(V vElement);

    /**
     * Inserts an edge in the graph using two vertices and an element of the edge, the edge saves the vertices in the order that is sent
     *
     * @param u - vertex to add
     * @param v - vertex to add
     * @param edgeElement - element of the edge
     * @return Edge - the edge that is inserted in the graph
     * @throws InvalidVertexException
     */
    public Edge<E, V> insertEdge(Vertex<V> u, Vertex<V> v, E edgeElement)
            throws InvalidVertexException;

    /**
     * Inserts an edge in the graph using two elements of the vertices and the edge element, the edge saves the vertices in the order that is sent
     *
     * @param vElement1 - the element of the vertex to add
     * @param vElement2 - the element of the vertex to add
     * @param edgeElement - element of the edge
     * @return Edge - the edge that is inserted in the graph
     * @throws InvalidVertexException
     */
    public Edge<E, V> insertEdge(V vElement1, V vElement2, E edgeElement)
            throws InvalidVertexException;

    /**
     * Removes a vertex from the graph even if there is an edge connected to it (it gets removed too)
     *
     * @param v - the vertex to remove
     * @return V - the element of the vertex
     * @throws InvalidVertexException
     */
    public V removeVertex(Vertex<V> v) throws InvalidVertexException;

    /**
     * Removes an edge from the graph
     *
     * @param e - the edge to remove
     * @return E - the element of the edge
     * @throws InvalidEdgeException
     */
    public E removeEdge(Edge<E, V> e) throws InvalidEdgeException;

    /**
     * Replaces the element of a vertex with a new element
     *
     * @param v - the vertex to change
     * @param newElement - the new element of the vertex
     * @return V - the old element of the vertex
     * @throws InvalidVertexException
     */
    public V replace(Vertex<V> v, V newElement) throws InvalidVertexException;

    /**
     * Replaces the element of the edge with a new element
     *
     * @param e - the edge to change
     * @param newElement - the new element of the edge
     * @return E - the old element of the edge
     * @throws InvalidEdgeException
     */
    public E replace(Edge<E, V> e, E newElement) throws InvalidEdgeException;

    /**
     * Gets the Edges that are connected to the vertices, order matters
     *
     * @param v1 - the vertex to check
     * @param v2 - the vertex to check
     * @return List - the list of the edges
     * @throws InvalidVertexException
     */
    public List<E> getConnectionsBetween(V v1, V v2)
            throws InvalidVertexException;

}

package diGraph;

import java.util.ArrayList;
import java.util.List;

import graph.Edge;
import graph.GraphEdgeList;
import graph.InvalidEdgeException;
import graph.InvalidVertexException;
import graph.Vertex;

/**
 *
 * Class DiGraphImpl, is the class that implements the interface DiGraph and imploments its methods
 *
 * @author (Daniel Afonso e André Ribeiro)
 * @version (19/11/18)
 * @param <V> - The vertex type
 * @param <E> - The edge type
 */
public class DiGraphImpl<V, E> implements DiGraph<V, E> {

    private GraphEdgeList<V, E> graph;

    /**
     * The constructor of the DiGraphImpl
     */
    public DiGraphImpl() {
        //we decided to use the graph already implemented in the classes and we adapted it to our needs, this case a DiGraph behavior
        graph = new GraphEdgeList<>();
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
    public Iterable<Vertex<V>> vertices() {
        return graph.vertices();
    }

    @Override
    public Iterable<Edge<E, V>> edges() {
        return graph.edges();
    }

    @Override
    public Iterable<Edge<E, V>> inboundEdges(Vertex<V> v) throws InvalidVertexException {

        if (v == null) {
            throw new InvalidVertexException("Vertice can not be null");
        }

        List<Edge<E, V>> inboundEdges = new ArrayList<>();
        for (Edge<E, V> edge : graph.edges()) {

            if (edge.vertices()[1] == v) { // inbounds are on the position 1 of the array
                inboundEdges.add(edge);
            }
        }
        return inboundEdges;
    }

    @Override
    public Iterable<Edge<E, V>> outboundEdges(Vertex<V> v) throws InvalidVertexException {
        if (v == null) {
            throw new InvalidVertexException("Vertice can not be null");
        }

        List<Edge<E, V>> outboundEdges = new ArrayList<>();
        for (Edge<E, V> edge : graph.edges()) {
            if (edge.vertices()[0] == v) { // outbound are on position 0 of the array
                outboundEdges.add(edge);
            }
        }
        return outboundEdges;
    }

    @Override
    public boolean areAdjacent(Vertex<V> u, Vertex<V> v) throws InvalidVertexException {
        // we allow loops, so we do not check if u == v
        if (u == null || v == null) {
            throw new InvalidVertexException("Vertex can not be not");
        }
        /*
		 * find and edge that contains both u and v keeping in mind its unidirectional
         */
        for (Edge<E, V> edge : graph.edges()) {
            if (edge.vertices()[0].equals(u) && edge.vertices()[1].equals(v)) { // since its uni directional it can
                // only be in this order
                return true;
            }
        }
        return false;
    }

    @Override
    public Vertex<V> insertVertex(V vElement) {
        return graph.insertVertex(vElement);
    }

    @Override
    public Edge<E, V> insertEdge(Vertex<V> u, Vertex<V> v, E edgeElement) throws InvalidVertexException {
        if (u == null || v == null) {
            throw new InvalidVertexException("Vertex can not be null");
        }

        return graph.insertEdge(u, v, edgeElement);
    }

    @Override
    public Edge<E, V> insertEdge(V vElement1, V vElement2, E edgeElement) throws InvalidVertexException {
        if (vElement1 == null || vElement2 == null) {
            throw new InvalidVertexException("Vertex can not be null");
        }

        return graph.insertEdge(vElement1, vElement2, edgeElement);
    }

    @Override
    public V removeVertex(Vertex<V> v) throws InvalidVertexException {
        if (v == null) {
            throw new InvalidVertexException("Vertex can not be null");
        }
        //the graph takes care of the edges attached to the vertex, therefore we only have to call it here
        return graph.removeVertex(v);
    }

    @Override
    public E removeEdge(Edge<E, V> e) throws InvalidEdgeException {
        if (e == null) {
            throw new InvalidEdgeException("Edge can not be null");
        }

        return graph.removeEdge(e);
    }

    @Override
    public V replace(Vertex<V> v, V newElement) throws InvalidVertexException {
        if (v == null) {
            throw new InvalidVertexException("Vertex can not be null");
        }

        return graph.replace(v, newElement);

    }

    @Override
    public E replace(Edge<E, V> e, E newElement) throws InvalidEdgeException {
        if (e == null) {
            throw new InvalidEdgeException("Edge can not be null");
        }

        return graph.replace(e, newElement);
    }

    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E, V> e) throws InvalidVertexException, InvalidEdgeException {
        if (!(e.vertices()[0] == v)) {
            return null;
            /* this edge does not connect vertex v,we have only to check from position 0, since its directional */
        }

        return e.vertices()[1];

    }

    @Override
    public List<E> getConnectionsBetween(V v1, V v2) throws InvalidVertexException {
        Vertex<V> vertex1 = checkVertex(v1);
        Vertex<V> vertex2 = checkVertex(v2);
        List<E> connectionslt = new ArrayList<>();

        for (Edge<E, V> e : graph.edges()) {
            // if its uni directional it returns only orig->dest
            if (e.vertices()[0].equals(vertex1) && e.vertices()[1].equals(vertex2)) {
                connectionslt.add(e.element());
            }
        }
        return connectionslt;
    }

    //private method to get the vertex refered to a certain vertex element
    private Vertex<V> checkVertex(V v) throws InvalidVertexException {
        if (v == null) {
            throw new InvalidVertexException("Place cannot be null");
        }

        Vertex<V> find = null;
        for (Vertex<V> vertex : graph.vertices()) {
            if (vertex.element().equals(v)) {
                find = vertex;
            }
        }

        if (find == null) {
            throw new InvalidVertexException("Vertex does not exist");
        }

        return find;
    }

}

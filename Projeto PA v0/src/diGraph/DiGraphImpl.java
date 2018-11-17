package diGraph;

import java.util.ArrayList;
import java.util.List;

import graph.Edge;
import graph.GraphEdgeList;
import graph.InvalidEdgeException;
import graph.InvalidVertexException;
import graph.Vertex;

public class DiGraphImpl<V,E> implements DiGraph<V,E>{

	private GraphEdgeList<V, E> graph;

	public DiGraphImpl() {
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

			if (eTypeIsUniDirectional(edge)) { // bridge or not
				if (edge.vertices()[1] == v) { // bridges have inbound edges at only the destination vertice
					inboundEdges.add(edge);
				}
			} else {
				if (edge.vertices()[0] == v || edge.vertices()[1] == v) { // paths have on both
					inboundEdges.add(edge);
				}
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
			if (edge.vertices()[0].equals(u) && edge.vertices()[1].equals(v)) { // it can only be in this direction to
																				// pass the verification
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
        if(!(e.vertices()[0] ==v || e.vertices()[1] == v)) return null; /* this edge does not connect vertex v */
        
        if(e.vertices()[0] == v) return e.vertices()[1];
        else return e.vertices()[0];
        
    }

	@Override
	public boolean eTypeIsUniDirectional(Edge<E, V> edge) {
		return false;
	}


}

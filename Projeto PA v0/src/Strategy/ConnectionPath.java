package Strategy;

import graph.Edge;
import graph.Vertex;

public class ConnectionPath<E,V> implements ConnectionStrategy{
	
	private Edge<E,V> edge;
	private Vertex<V> p1;
	private Vertex<V> p2;

	public ConnectionPath(Edge<E,V> edge, Vertex<V> p1, Vertex<V> p2) {
		this.edge = edge;
		this.p1 = p1;
		this.p2 = p2;
	}

	@Override
	public boolean isConnectedVertices() {
		return (edge.vertices()[0] == p1 && edge.vertices()[1] == p2 ||
				edge.vertices()[1] == p1 && edge.vertices()[0] == p2) ;
	}

}

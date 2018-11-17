package Strategy;

import graph.Edge;
import graph.Vertex;
import model.Connection;
import model.Place;

public class ConnectionPath implements ConnectionStrategy{
	
	private Edge<Connection,Place> edge;
	private Vertex<Place> p1;
	private Vertex<Place> p2;

	public ConnectionPath(Edge<Connection,Place> edge, Vertex<Place> p1, Vertex<Place> p2) {
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

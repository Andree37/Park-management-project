package model;

import dijkstra.graph.Edge;
import dijkstra.graph.Vertex;

public class ConnectionBridge implements ConnectionsBetween{

	private Edge<Connection, Place> edge;
	private Vertex<Place> a1;
	private Vertex<Place> a2;
	
	public ConnectionBridge(Edge<Connection, Place> edge,Vertex<Place> a1,Vertex<Place> a2) {
		this.edge = edge;
		this.a1 = a1;
		this.a2 = a2;
	}

	@Override
	public boolean isConnectedVertices() {
		return edge.vertices()[0].equals(a1) && edge.vertices()[1].equals(a2);
	}

	
	

}

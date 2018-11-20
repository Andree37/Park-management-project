package Test;




import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import diGraph.DiGraph;
import diGraph.DiGraphImpl;
import graph.Edge;
import graph.Vertex;
import model.Connection;
import model.Place;
import model.Connection.Type;

public class DiGraphImplTest {

	private DiGraph<Place, Connection> graph;

	@Before
	public void setUp() throws Exception {
		graph = new DiGraphImpl<>();
	}

	@Test
	public void insertVertex_True_OnInsertVertices() {
		Place place1 = new Place(1, "Entrada");
		Place place2 = new Place(2, "Saida");
		graph.insertVertex(place1);
		graph.insertVertex(place2);

		assertEquals("The implementation does not return 2", 2, graph.numVertices());
	}
	
	@Test
	public void insertEdge_True_OnInsertEdges() {
		Place place1 = new Place(1, "Entrada");
		Place place2 = new Place(2, "Saida");
		graph.insertVertex(place1);
		graph.insertVertex(place2);
		Connection conn = new Connection(1, Type.PATH.getUnit(), "caminho", null, true, 20, 100);

		graph.insertEdge(place1, place2, conn);
		
		assertEquals("The implementation does not return the right amount of edges",1,graph.numEdges());
	}

	@Test
	public void getConnectionsBetween_True_TwoConnectedVertices() {
		Place place1 = new Place(1, "Entrada");
		Place place2 = new Place(2, "Saida");
		graph.insertVertex(place1);
		graph.insertVertex(place2);
		Connection conn = new Connection(1, Type.PATH.getUnit(), "caminho", null, true, 20, 100);

		graph.insertEdge(place1, place2, conn);

		List<Connection> conns = graph.getConnectionsBetween(place1, place2);

		assertEquals("The implementation does not return true", true, conns.contains(conn));

	}

	@Test
	public void opposite_True_OnGettingOppositeFromBridgeAndPath() {
		Place place1 = new Place(1, "Entrada");
		Place place2 = new Place(2, "Saida");
		Vertex<Place> vertex = graph.insertVertex(place1);
		Vertex<Place> opposite = graph.insertVertex(place2);
		
		// the opposite of a path is the other vertex
		Connection conn = new Connection(1, Type.PATH.getUnit(), "caminho", null, true, 20, 100);
		Edge<Connection,Place> edge = graph.insertEdge(place1, place2, conn);
		
		assertEquals("The implementation does not return the right vertex",opposite,graph.opposite(vertex, edge));
		//but if sent the other way around, it should return null
		assertEquals("The implementation does not return the right vertex",null,graph.opposite(opposite, edge));
	}
	
	@Test
	public void getPlaces_True_OnSize() {
		int count = 0;
		Place place1 = new Place(1, "Entrada");
		Place place2 = new Place(2, "Saida");
		Place place3 = new Place(3, "Casa");
		graph.insertVertex(place1);
		graph.insertVertex(place2);
		graph.insertVertex(place3);

		for (Vertex<Place> p : graph.vertices()) {
			count++;
		}

		assertEquals("The implementation does not have count == 3", 3, count);
	}

	@Test
	public void getConnections_true_onSize() {
		int count = 0;
		Place place1 = new Place(1, "Entrada");
		Place place2 = new Place(2, "Saida");
		Place place3 = new Place(3, "Casa");
		Connection conn1 = new Connection(1, Type.BRIDGE.getUnit(), "Ponte Lima", null, false, 2, 10);
		Connection conn2 = new Connection(1, Type.PATH.getUnit(), "caminho", null, false, 3, 20);
		Connection conn3 = new Connection(1, Type.BRIDGE.getUnit(), "Ponte Vasco da Gama", null, false, 4, 15);
		graph.insertVertex(place1);
		graph.insertVertex(place2);
		graph.insertVertex(place3);
		graph.insertEdge(place1, place2, conn1);
		graph.insertEdge(place2, place3, conn2);
		graph.insertEdge(place1, place3, conn3);

		for (Edge<Connection, Place> edge : graph.edges()) {
			count++;
		}

		assertEquals("The implementation does not have count == 3", 3, count);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addConnections_ExceptionThrown_OnAddingAnExistantEdge() {
		Connection conn1 = new Connection(1, Type.BRIDGE.getUnit(), "Ponte Lima", null, false, 2, 10);
		Place place1 = new Place(1, "Entrada");
		Place place2 = new Place(2, "Saida");
		graph.insertVertex(place1);
		graph.insertVertex(place2);

		graph.insertEdge(place1, place2, conn1);
		graph.insertEdge(place2, place1, conn1);
	}

	@Test
	public void inboundEdges_True_OnGettingEdgesThatAreBridgesAndPaths() {
		int countPlace1 = 0;
		int countPlace2 = 0;
		Place place1 = new Place(1, "Entrada");
		Place place2 = new Place(2, "Saida");
		Connection conn1 = new Connection(1, Type.BRIDGE.getUnit(), "Ponte Lima", null, false, 2, 10);
		Connection conn2 = new Connection(1, Type.PATH.getUnit(), "caminho", null, false, 3, 20);
		Vertex<Place> p1 = graph.insertVertex(place1);
		Vertex<Place> p2 = graph.insertVertex(place2);
		graph.insertEdge(place1, place2, conn1);
		graph.insertEdge(place2, place1, conn2);

		// it should only return one in each edge

		for (Edge<Connection, Place> con : graph.inboundEdges(p1)) {
			countPlace1++;
		}
		for (Edge<Connection, Place> con : graph.inboundEdges(p2)) {
			countPlace2++;
		}
		assertEquals("The implementation does not have the right amount of connections", true,
				countPlace1 == 1 && countPlace2 == 1);
	}

	@Test
	public void outboundEdges_True_OnGettingNumberOfOutboundEdgesFromVertices() {

		int countP1 = 0;
		int countP2 = 0;

		Place place1 = new Place(1, "Entrada");
		Place place2 = new Place(2, "Saida");
		Connection conn1 = new Connection(1, Type.BRIDGE.getUnit(), "Ponte Lima", null, false, 2, 10);
		Connection conn2 = new Connection(1, Type.PATH.getUnit(), "caminho", null, false, 3, 20);
		Vertex<Place> p1 = graph.insertVertex(place1);
		Vertex<Place> p2 = graph.insertVertex(place2);
		graph.insertEdge(place1, place2, conn1); 
		graph.insertEdge(place2, place1, conn2);

		for (Edge<Connection, Place> e : graph.outboundEdges(p1)) { // should only have 1 outbound
			countP1++;
		}
		for (Edge<Connection, Place> e : graph.outboundEdges(p2)) { // should only have 1 outbound
			countP2++;
		}

		assertEquals("The implementation does not return the right Vertex", 1, countP1);
		assertEquals("The implementation does not return the right Vertex", 1, countP2);
	}

	@Test
	public void areAdjacent_True_OnAdjacencyFromPathAndBridge() {
		// return true if the order is correct, from origin to destination
		Place place1 = new Place(1, "Entrada");
		Place place2 = new Place(2, "Saida");
		Place place3 = new Place(3, "Cantina");
		Connection conn1 = new Connection(1, Type.BRIDGE.getUnit(), "Ponte Lima", null, false, 2, 10);
		Connection conn2 = new Connection(1, Type.PATH.getUnit(), "caminho", null, false, 3, 20);
		Vertex<Place> p1 = graph.insertVertex(place1);
		Vertex<Place> p2 = graph.insertVertex(place2);
		Vertex<Place> p3 = graph.insertVertex(place3);
		graph.insertEdge(place1, place2, conn1); // bridge
		graph.insertEdge(place2, place3, conn2);
		// the order matters
		assertEquals("The implementation does not return the correct answer", true, graph.areAdjacent(p1, p2));
		assertEquals("The implementation does not return the correct answer", false, graph.areAdjacent(p2, p1));
		// the order matters
		assertEquals("The implementation does not return the correct answer", true, graph.areAdjacent(p2, p3));
		assertEquals("The implementation does not return the correct answer", false, graph.areAdjacent(p3, p2));
	}

	@Test
	public void removeEdge_True_OnRemovalOfAnExistingEdge() {
		Place place1 = new Place(1, "Entrada");
		Place place2 = new Place(2, "Saida");
		Connection conn1 = new Connection(1, Type.BRIDGE.getUnit(), "Ponte Lima", null, false, 2, 10);
		graph.insertVertex(place1);
		graph.insertVertex(place2);
		Edge<Connection, Place> edge1 = graph.insertEdge(place1, place2, conn1);

		graph.removeEdge(edge1);

		assertEquals("The implementation does not return the correct amount of edges", 0, graph.numEdges());

	}

	@Test
	public void removeVertice_True_OnRemovalOfANExistingVertice() {
		Place place1 = new Place(1, "Entrada");
		Vertex<Place> p1 = graph.insertVertex(place1);

		graph.removeVertex(p1);

		assertEquals("The implementation does not return the correct amount of vertices", 0, graph.numVertices());
	}

	@Test
	public void replaceVertice_True_OnReplaceAnExistingVertice() {
		Place place1 = new Place(1, "Entrada");
		Place place2 = new Place(2, "Saida");
		Vertex<Place> p1 = graph.insertVertex(place1);

		graph.replace(p1, place2);

		String newName = "";

		for (Vertex<Place> v : graph.vertices()) {
			newName = v.element().getName(); // since theres only 1 i can do this to access the vertice
		}

		assertEquals("Vertice did not change", place2.getName(), newName);
	}

	@Test
	public void replaceEdge_True_OnReplaceAnExistingEdge() {
		Place place1 = new Place(1, "Entrada");
		Place place2 = new Place(2, "Saida");
		Connection conn1 = new Connection(1, Type.BRIDGE.getUnit(), "Ponte Lima", null, false, 2, 10);
		graph.insertVertex(place1);
		graph.insertVertex(place2);
		Edge<Connection, Place> edge1 = graph.insertEdge(place1, place2, conn1);

		Connection conn2 = new Connection(1, Type.PATH.getUnit(), "caminho", null, false, 3, 20);

		graph.replace(edge1, conn2);

		String newName = "";

		for (Edge<Connection, Place> e : graph.edges()) {
			newName = e.element().getName();// since theres only 1 i can do this to access the edge
		}

		assertEquals("Edge did not change", conn2.getName(), newName);
	}

}

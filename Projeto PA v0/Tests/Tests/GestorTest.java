package Tests;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import graph.Edge;
import graph.Vertex;
import model.Connection;
import model.Connection.Type;
import model.GestorPercurso;
import model.Place;

public class GestorTest {

	private GestorPercurso gestor;
	
	@Before
	public void setUp() throws Exception {
		gestor = new GestorPercurso();
	}

	
	@Test
	public void insertVertex_True_OnInsertVertices() {
		Place place1 = new Place(1, "Entrada");
		Place place2 = new Place(2,"Saida");
		gestor.insertVertex(place1);
		gestor.insertVertex(place2);
		
		assertEquals("The implementation does not return 2",true,gestor.numVertices() == 2);
	}
	
	@Test
	public void getConnectionsBetween_True_TwoConnectedVertices() {
		Place place1 = new Place(1, "Entrada");
		Place place2 = new Place(2,"Saida");
		gestor.insertVertex(place1);
		gestor.insertVertex(place2);
		Connection conn = new Connection(1, Type.PATH.getUnit(), "caminho", null, true, 20, 100);
		
		gestor.insertEdge(place1, place2, conn);
		
		List<Connection> conns = gestor.getConnectionsBetween(place1, place2);
		
		assertEquals("The implementation does not return true",true,conns.contains(conn));
		
	}
	
	@Test
	public void getPlaces_True_OnSize() {
		int count = 0;
		Place place1 = new Place(1,"Entrada");
		Place place2 = new Place(2,"Saida");
		Place place3 = new Place(3,"Casa");
		gestor.insertVertex(place1);
		gestor.insertVertex(place2);
		gestor.insertVertex(place3);
		
		for(Vertex<Place> p : gestor.getPlaces()) {
			count++;
		}
		
		assertEquals("The implementation does not have count == 3",true,count == 3);
	}
	
	@Test
	public void getConnections_true_onSize() {
		int count = 0;
		Place place1 = new Place(1,"Entrada");
		Place place2 = new Place(2,"Saida");
		Place place3 = new Place(3,"Casa");
		Connection conn1 = new Connection(1, Type.BRIDGE.getUnit(), "Ponte Lima", null, false, 2, 10);
		Connection conn2 = new Connection(1, Type.PATH.getUnit(), "caminho", null, false, 3, 20);
		Connection conn3 = new Connection(1, Type.BRIDGE.getUnit(), "Ponte Vasco da Gama", null, false, 4, 15);
		gestor.insertVertex(place1);
		gestor.insertVertex(place2);
		gestor.insertVertex(place3);
		gestor.insertEdge(place1, place2, conn1);
		gestor.insertEdge(place2, place3, conn2);
		gestor.insertEdge(place1, place3, conn3);
		
		for(Edge<Connection, Place> edge : gestor.getConnections()) {
			count++;
		}
		
		assertEquals("The implementation does not have count == 3",true,count == 3);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addConnections_ExceptionThrown_OnAddingAnExistantEdge() {
		Connection conn1 = new Connection(1, Type.BRIDGE.getUnit(), "Ponte Lima", null, false, 2, 10);
		Place place1 = new Place(1,"Entrada");
		Place place2 = new Place(2,"Saida");
		gestor.insertVertex(place1);
		gestor.insertVertex(place2);
		
		gestor.insertEdge(place1, place2, conn1);
		gestor.insertEdge(place2, place1, conn1);
	}
	
	@Test
	public void getPlace_True_OnGettingExistentPlace() {
		Place place = new Place(1,"Entrada");
		gestor.insertVertex(place);
		
		Place p = gestor.getPlace(1);
		
		assertEquals("The implementation does not have this vertice",true,place.equals(p));
	}
	
	@Test
	public void incidentEdges_True_OnGettingVerticesFromBridgesAndPaths() {
		int countPlace1= 0;
		int countPlace2 = 0;
		Place place1 = new Place(1,"Entrada");
		Place place2 = new Place(2,"Saida");
		Connection conn1 = new Connection(1, Type.BRIDGE.getUnit(), "Ponte Lima", null, false, 2, 10);
		Connection conn2 = new Connection(1, Type.PATH.getUnit(), "caminho", null, false, 3, 20);
		Vertex<Place> p1 = gestor.insertVertex(place1);
		Vertex<Place> p2 = gestor.insertVertex(place2);
		gestor.insertEdge(place1, place2, conn1); // bridge
		gestor.insertEdge(place2, place1, conn2);
		
		//insert a bridge between place1 and place2
		//insert a path between place 2 and place1
		//since a bridge only knows its incidents on the origin, place1 should have 2 incident edge
		//and place 2 should have 1
		
		for(Edge<Connection, Place> con : gestor.incidentEdges(p1)) {
			countPlace1++;
		}
		for(Edge<Connection, Place> con : gestor.incidentEdges(p2)) {
			countPlace2++;
		}
		assertEquals("The implementation does not have the right amount of connections",true,countPlace1 == 2 
				&& countPlace2 == 1);	
	}
	
	@Test
	public void opposite_True_OnGettingOppositeFromBridgeAndPath() {
		Place place1 = new Place(1,"Entrada");
		Place place2 = new Place(2,"Saida");
		Connection conn1 = new Connection(1, Type.BRIDGE.getUnit(), "Ponte Lima", null, false, 2, 10);
		Connection conn2 = new Connection(1, Type.PATH.getUnit(), "caminho", null, false, 3, 20);
		Vertex<Place> p1 = gestor.insertVertex(place1);
		Vertex<Place> p2 = gestor.insertVertex(place2);
		Edge<Connection,Place> edge1 = gestor.insertEdge(place1, place2, conn1); // bridge
		Edge<Connection,Place> edge2 = gestor.insertEdge(place2, place1, conn2);
		
		Vertex<Place> opposite1 = null;
		Vertex<Place> opposite2 = null;
		opposite1 = gestor.opposite(p1, edge1);
		opposite2 = gestor.opposite(p2, edge2);
		
		assertEquals("The implementation does not return the right Vertex",true,opposite1 == null); //since a bridge has no opposite
		assertEquals("The implementation does not return the right Vertex",true,opposite2 == p1);
	}
	
	@Test
	public void areAdjacent_True_OnAdjacencyFromPathAndBridge() {
		//bridges only return true if the order is correct, from origin to destination
		Place place1 = new Place(1,"Entrada");
		Place place2 = new Place(2,"Saida");
		Place place3 = new Place(3,"Cantina");
		Connection conn1 = new Connection(1, Type.BRIDGE.getUnit(), "Ponte Lima", null, false, 2, 10);
		Connection conn2 = new Connection(1, Type.PATH.getUnit(), "caminho", null, false, 3, 20);
		Vertex<Place> p1 = gestor.insertVertex(place1);
		Vertex<Place> p2 = gestor.insertVertex(place2);
		Vertex<Place> p3 = gestor.insertVertex(place3);
		Edge<Connection,Place> edge1 = gestor.insertEdge(place1, place2, conn1); // bridge
		Edge<Connection,Place> edge2 = gestor.insertEdge(place2, place3, conn2);
		
		assertEquals("The implementation does not return the correct answer",true,gestor.areAdjacent(p1,p2));
		assertEquals("The implementation does not return the correct answer",false,gestor.areAdjacent(p2,p1)); //since its a bridge and its not on the right order
		assertEquals("The implementation does not return the correct answer",true,gestor.areAdjacent(p2,p3));
		assertEquals("The implementation does not return the correct answer",true,gestor.areAdjacent(p3,p2)); //since its a path, the order doesnt matter
	}
	
	@Test
	public void removeEdge_True_OnRemovalOfAnExistingEdge() {
		Place place1 = new Place(1,"Entrada");
		Place place2 = new Place(2,"Saida");
		Connection conn1 = new Connection(1, Type.BRIDGE.getUnit(), "Ponte Lima", null, false, 2, 10);
		Vertex<Place> p1 = gestor.insertVertex(place1);
		Vertex<Place> p2 = gestor.insertVertex(place2);
		Edge<Connection,Place> edge1 = gestor.insertEdge(place1, place2, conn1);
		
		gestor.removeEdge(edge1);
		
		assertEquals("The implementation does not return the correct amount of edges",true,gestor.numEdges()==0);
		
	}
	
	@Test
	public void removeVertice_True_OnRemovalOfANExistingVertice() {
		Place place1 = new Place(1,"Entrada");
		Vertex<Place> p1 = gestor.insertVertex(place1);
		
		gestor.removeVertex(p1);
		
		assertEquals("The implementation does not return the correct amount of vertices",true,gestor.numVertices()==0);
	}
	
	@Test
	public void replaceVertice_True_OnReplaceAnExistingVertice() {
		Place place1 = new Place(1,"Entrada");
		Place place2 = new Place(2,"Saida");
		Vertex<Place> p1 = gestor.insertVertex(place1);
		
		gestor.replace(p1, place2);
		
		String newName = "";
		
		for(Vertex<Place> v : gestor.vertices()) {
			newName = v.element().getName(); //since theres only 1 i can do this to access the vertice
		}
		
		assertEquals("Vertice did not change",true,newName.equals(place2.getName()));
	}
	
	@Test
	public void replaceEdge_True_OnReplaceAnExistingEdge() {
		Place place1 = new Place(1,"Entrada");
		Place place2 = new Place(2,"Saida");
		Connection conn1 = new Connection(1, Type.BRIDGE.getUnit(), "Ponte Lima", null, false, 2, 10);
		Vertex<Place> p1 = gestor.insertVertex(place1);
		Vertex<Place> p2 = gestor.insertVertex(place2);
		Edge<Connection,Place> edge1 = gestor.insertEdge(place1, place2, conn1);
		
		Connection conn2 = new Connection(1, Type.PATH.getUnit(), "caminho", null, false, 3, 20);
		
		gestor.replace(edge1, conn2);
		
		String newName = "";
		
		for(Edge<Connection, Place> e : gestor.edges()) {
			newName = e.element().getName();//since theres only 1 i can do this to access the edge
		}
		
		assertEquals("Edge did not change",true,newName.equals(conn2.getName()));
	}
	
}
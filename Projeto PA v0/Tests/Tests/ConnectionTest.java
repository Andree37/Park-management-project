package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Connection;
import model.Connection.Type;
import model.Place;

public class ConnectionTest {

	private Connection connection;
	
	@Before
	public void setUp() throws Exception {
		//creation of a connection that is a path and has 2 connected, just for testing purposes
		List<Integer> connID = new ArrayList<>();
		connID.add(1);
		connID.add(2);
		connection = new Connection(1, Type.PATH.getUnit(), "caminho", connID, true, 10, 100);
	}

	@Test
	public void getId_True_OnGettingIDFromExistingConnection() {
		assertEquals("Does not return the correct id",1,connection.getId());
	}

	@Test
	public void getName_True_OnGettingNameFromExisingConnection() {
		assertEquals("Does not return the correct name","caminho",connection.getName());
	}
	
	@Test
	public void getType_True_OnGettingTypeFromExistingConnection() {
		assertEquals("Does not return the correct type",Type.PATH.getUnit(),connection.getType());
	}
	
	@Test
	public void getConnections_True_OnGettingConnectionsFromExistingConnection() {
		assertEquals("Does not return the correct number of connections",2,connection.getConnections().size());
	}
	
	@Test
	public void isAvailable_True_OnGettingAvailableFromExistingConnection() {
		assertEquals("Does not return the correct answer",true,connection.isAvailable());
	}
	
	@Test
	public void getPrice_True_OnGettingPriceFromExistingConnection() {
		assertEquals("Does not return the correct price",10,connection.getPrice());
	}
	
	@Test
	public void getDistance_True_OnGettingDistanceFromExistingConnection() {
		assertEquals("Does not reutnr the correct distance",100,connection.getDistance());
	}
	
	@Test
	public void equals_False_OnEqualWithAnotherConnection() {
		Connection conn = new Connection(2, Type.BRIDGE.getUnit(), "ponte", null, true, 2, 20);
		assertEquals("The implementation does not return the correct answer",false,connection.equals(conn));
	}
}

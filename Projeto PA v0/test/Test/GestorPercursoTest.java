package Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import diGraph.DiGraph;
import diGraph.DiGraphImpl;
import graph.Edge;
import graph.Vertex;
import java.util.ArrayList;
import java.util.List;
import model.Connection;
import model.GestorPercurso;
import model.GestorPercursoException;
import model.Place;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Darfkman
 */
public class GestorPercursoTest {
     private GestorPercurso gestor;
     
    public GestorPercursoTest() {
       //gestor = new GestorPercurso();
       
    }
  
    
    @Before
    public void setUp() {
      gestor = new GestorPercurso();
       
    }
   
   

    

    /**
     * Test of addPlace method, of class GestorPercurso.
     */
    @Test
    public void testAddPlace() {
        System.out.println("addPlace");
        Place place = new Place(1,"test");
        DiGraphImpl graph = new DiGraphImpl();
        List list1 = new ArrayList<>();
        gestor.addPlace(place);
        list1.add(graph.insertVertex(place));
        String expResult = list1.toString();
        String result = gestor.getPlaces().toString();
        assertEquals(expResult, result);   
    }
     /**
     * Test of addPlace method, of class GestorPercurso.
     */
    @Test(expected = GestorPercursoException.class)
    public void testAddPlace_ExceptionThrown_onNull() {
        System.out.println("addPlace");
        Place place = null;
        GestorPercurso instance = new GestorPercurso();
        instance.addPlace(place); 
    }
    
    @Test(expected = GestorPercursoException.class)
    public void testAddPlace_ExceptionThrown_onAddingSamePlace() {
        System.out.println("addPlace");
         Place place1 = new Place(1,"test1");
         Place place2 = new Place(1,"test2");
        GestorPercurso instance = new GestorPercurso();
        instance.addPlace(place1);
        instance.addPlace(place2);  
    }


    /**
     * Test of addConnection method, of class GestorPercurso.
     */
    @Test
    public void testAddConnection() {
        System.out.println("addConnection");
        GestorPercurso instance = new GestorPercurso();
        Place place1 = new Place(1,"test1");
        Place place2 = new Place(2,"test2");
        Vertex<Place> p1 = gestor.addPlace(place1);
        Vertex<Place> p2 = gestor.addPlace(place2);
        Vertex<Place> p3 = instance.addPlace(place1);
        Vertex<Place> p4 = instance.addPlace(place2);
        Connection c1 = new Connection(1, Connection.Type.PATH.getUnit(), "caminho", null, true, 20, 100);
        instance.addConnection(p3, p4, c1);
        gestor.addConnection(p1, p2, c1);
    }

    /**
     * Test of getPlaces method, of class GestorPercurso.
     */
    @Test
    public void testGetPlaces() {
        System.out.println("getPlaces");
        GestorPercurso instance = new GestorPercurso();
        Iterable<Vertex<Place>> expResult = new ArrayList<>();
        Iterable<Vertex<Place>> result = instance.getPlaces();
        assertEquals(expResult, result);


    }

    /**
     * Test of getConnections method, of class GestorPercurso.
     */
    @Test
    public void testGetConnections() {
        System.out.println("getConnections");
        GestorPercurso instance = new GestorPercurso();
        Iterable<Edge<Connection, Place>> expResult = new ArrayList<>();
        Iterable<Edge<Connection, Place>> result = instance.getConnections();
        assertEquals(expResult, result);


    }

    /**
     * Test of getVertexWith method, of class GestorPercurso.
     */
    @Test
    public void testGetVertexWith() {
        System.out.println("getVertexWith");
        int id = 0;
        Place place = new Place(id,"Spot");
        GestorPercurso instance = new GestorPercurso();
        instance.addPlace(place);
        gestor.addPlace(place);
        Place expResult = gestor.getVertexWith(id);
        Place result = instance.getVertexWith(id);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of minimumCostPath method, of class GestorPercurso.
     */
    @Test
    public void testMinimumCostPath_offBike_onCOST() {
        System.out.println("minimumCostPath");
        GestorPercurso instance = new GestorPercurso();
        GestorPercurso.Criteria criteria = GestorPercurso.Criteria.COST;
        Place place1 = new Place(1,"test1");
        Place place2 = new Place(2,"test2");
        Place place3 = new Place(3,"test3");
        Place place4 = new Place(4,"test4");
        Place place5 = new Place(5,"test5");
        Vertex<Place> p1 = instance.addPlace(place1);
        Vertex<Place> p2 = instance.addPlace(place2);
        Vertex<Place> p3 = instance.addPlace(place3);
        Vertex<Place> p4 = instance.addPlace(place4);
        Vertex<Place> p5 = instance.addPlace(place5);
        Connection c1 = new Connection(1, Connection.Type.PATH.getUnit(), "caminho1", null, true, 4, 100);
        Connection c2 = new Connection(2, Connection.Type.PATH.getUnit(), "caminho2", null, true, 2, 100);
        Connection c3 = new Connection(3, Connection.Type.PATH.getUnit(), "caminho3", null, true, 3, 100);
        Connection c4 = new Connection(4, Connection.Type.PATH.getUnit(), "caminho4", null, true, 4, 100);
        Connection c5 = new Connection(5, Connection.Type.PATH.getUnit(), "caminho5", null, true, 0, 100);
        Connection c6 = new Connection(6, Connection.Type.PATH.getUnit(), "caminho6", null, true, 2, 100);
        Connection c51 = new Connection(51, Connection.Type.PATH.getUnit(), "caminho1", null, true, 4, 100);
        Connection c52 = new Connection(52, Connection.Type.PATH.getUnit(), "caminho2", null, true, 2, 100);
        Connection c53 = new Connection(53, Connection.Type.PATH.getUnit(), "caminho3", null, true, 3, 100);
        Connection c54 = new Connection(54, Connection.Type.PATH.getUnit(), "caminho4", null, true, 4, 100);
        Connection c55 = new Connection(55, Connection.Type.PATH.getUnit(), "caminho5", null, true, 0, 100);
        Connection c56 = new Connection(56, Connection.Type.PATH.getUnit(), "caminho6", null, true, 2, 100);
        instance.addConnection(p1, p4, c1);
        instance.addConnection(p1, p2, c2);
        instance.addConnection(p2, p5, c3);
        instance.addConnection(p3, p2, c4);
        instance.addConnection(p5, p3, c5);
        instance.addConnection(p5, p4, c6);
        instance.addConnection(p4, p1, c51);
        instance.addConnection(p2, p1, c52);
        instance.addConnection(p5, p2, c53);
        instance.addConnection(p2, p3, c54);
        instance.addConnection(p3, p5, c55);
        instance.addConnection(p4, p5, c56);

        Place orig = place1;
        Place dst = place3;
        List<Place> places =  new ArrayList<>();
        List<Connection> connections =  new ArrayList<>();
        int insert = 0;
        boolean bridge = false;
        boolean bike = false;
        /*
        Acording to the layout of this map, the easiest way to go from place1 to place3 is by going through place2
        then place5 and then finally arrive at place3. 
        The cost(€) of p1->p2->p5->p3 is respectively 2+3+0 = 5.
        */
        int expResult = 5; 
        int result = instance.minimumCostPath(criteria, orig, dst, places, connections, insert, bridge, bike);
        assertEquals(expResult, result);

    }
    /**
     * Test of minimumCostPath method, of class GestorPercurso.
     */
    @Test
    public void testMinimumCostPath_onBike_onCOST() {
        System.out.println("minimumCostPath");
        GestorPercurso instance = new GestorPercurso();
        GestorPercurso.Criteria criteria = GestorPercurso.Criteria.COST;
        
        Place place1 = new Place(1,"test1");
        Place place2 = new Place(2,"test2");
        Place place3 = new Place(3,"test3");
        Place place4 = new Place(4,"test4");
        Place place5 = new Place(5,"test5");
        Vertex<Place> p1 = instance.addPlace(place1);
        Vertex<Place> p2 = instance.addPlace(place2);
        Vertex<Place> p3 = instance.addPlace(place3);
        Vertex<Place> p4 = instance.addPlace(place4);
        Vertex<Place> p5 = instance.addPlace(place5);     
        Connection c1 = new Connection(1, Connection.Type.PATH.getUnit(), "caminho1", null, true, 4, 100);
        Connection c2 = new Connection(2, Connection.Type.PATH.getUnit(), "caminho2", null, false, 2, 100);
        Connection c3 = new Connection(3, Connection.Type.PATH.getUnit(), "caminho3", null, true, 3, 100);
        Connection c4 = new Connection(4, Connection.Type.PATH.getUnit(), "caminho4", null, true, 4, 100);
        Connection c5 = new Connection(5, Connection.Type.PATH.getUnit(), "caminho5", null, false, 0, 100);
        Connection c6 = new Connection(6, Connection.Type.PATH.getUnit(), "caminho6", null, true, 2, 100);
        //2way connections
        Connection c51 = new Connection(51, Connection.Type.PATH.getUnit(), "caminho1", null, true, 4, 100);
        Connection c52 = new Connection(52, Connection.Type.PATH.getUnit(), "caminho2", null, true, 2, 100);
        Connection c53 = new Connection(53, Connection.Type.PATH.getUnit(), "caminho3", null, true, 3, 100);
        Connection c54 = new Connection(54, Connection.Type.PATH.getUnit(), "caminho4", null, true, 4, 100);
        Connection c55 = new Connection(55, Connection.Type.PATH.getUnit(), "caminho5", null, true, 0, 100);
        Connection c56 = new Connection(56, Connection.Type.PATH.getUnit(), "caminho6", null, true, 2, 100);
        instance.addConnection(p1, p4, c1);
        instance.addConnection(p1, p2, c2);
        instance.addConnection(p5, p2, c3);
        instance.addConnection(p2, p3, c4);
        instance.addConnection(p5, p3, c5);
        instance.addConnection(p4, p5, c6);
        instance.addConnection(p4, p1, c51);
        instance.addConnection(p2, p1, c52);
        instance.addConnection(p5, p2, c53);
        instance.addConnection(p2, p3, c54);
        instance.addConnection(p3, p5, c55);
        instance.addConnection(p4, p5, c56);
        Place orig = place1;
        Place dst = place3;
        List<Place> places =  new ArrayList<>();
        List<Connection> connections =  new ArrayList<>();
        int insert = 0;
        boolean bridge = true;
        boolean bike = true;
        /*
        Acording to the layout of this map, the easiest way to go from place1 to place3 is by going through place2
        then place5 and then finally arrive at place3. 
        But the only path with a bike from place1 to place3 is p1->p4->p5->p2->p3.
        So the cost of p1->p4->p5->p2->p3 is respectively 4+2+3+4 = 13.
        */
        int expResult = 13;
        int result = instance.minimumCostPath(criteria, orig, dst, places, connections, insert, bridge, bike);
        assertEquals(expResult, result);

    }
    
    /**
     * Test of minimumCostPath method, of class GestorPercurso.
     */
    @Test
    public void testMinimumCostPath_withBridges_onCOST() {
        System.out.println("minimumCostPath");
        GestorPercurso instance = new GestorPercurso();
        GestorPercurso.Criteria criteria = GestorPercurso.Criteria.COST;
        
        Place place1 = new Place(1,"test1");
        Place place2 = new Place(2,"test2");
        Place place3 = new Place(3,"test3");
        Place place4 = new Place(4,"test4");
        Place place5 = new Place(5,"test5");
        Vertex<Place> p1 = instance.addPlace(place1);
        Vertex<Place> p2 = instance.addPlace(place2);
        Vertex<Place> p3 = instance.addPlace(place3);
        Vertex<Place> p4 = instance.addPlace(place4);
        Vertex<Place> p5 = instance.addPlace(place5);     
        Connection c1 = new Connection(1, Connection.Type.BRIDGE.getUnit(), "caminho1", null, true, 4, 100);
        Connection c2 = new Connection(2, Connection.Type.PATH.getUnit(), "caminho2", null, true, 1, 100);
        Connection c3 = new Connection(3, Connection.Type.BRIDGE.getUnit(), "caminho3", null, true, 3, 100);
        Connection c4 = new Connection(4, Connection.Type.BRIDGE.getUnit(), "caminho4", null, true, 4, 100);
        Connection c5 = new Connection(5, Connection.Type.PATH.getUnit(), "caminho5", null, true, 0, 100);
        Connection c6 = new Connection(6, Connection.Type.BRIDGE.getUnit(), "caminho6", null, true, 2, 100);
        //2way connections
        Connection c52 = new Connection(52, Connection.Type.PATH.getUnit(), "caminho2", null, true, 2, 100);
        Connection c55 = new Connection(55, Connection.Type.PATH.getUnit(), "caminho5", null, true, 0, 100);

        instance.addConnection(p1, p4, c1);
        instance.addConnection(p1, p2, c2);
        instance.addConnection(p5, p2, c3);
        instance.addConnection(p2, p3, c4);
        instance.addConnection(p5, p3, c5);
        instance.addConnection(p4, p5, c6);
        instance.addConnection(p2, p1, c52);
        instance.addConnection(p3, p5, c55);
        
        Place orig = place1;
        Place dst = place5;
        List<Place> places =  new ArrayList<>();
        List<Connection> connections =  new ArrayList<>();
        int insert = 0;
        boolean bridge = true;
        boolean bike = false;
        /*
        Acording to the layout of this map, the  way to go from place1 to place5 is by going through place2
        then place3 and then finally arrive at place5. 
        But the only path with or without a bike from place1 to place3 is p1->p2->p3->p5.
        So the cost of p1->p2->p3->p5 is respectively 1+4+0 = 5.
        */
        int expResult = 5;
        int result = instance.minimumCostPath(criteria, orig, dst, places, connections, insert, bridge, bike);
        assertEquals(expResult, result);

    }
    /**
     * Test of minimumCostPath method, of class GestorPercurso.
     */
    @Test
    public void testMinimumCostPath_withBridges_onDistance() {
        System.out.println("minimumCostPath");
        GestorPercurso instance = new GestorPercurso();
        GestorPercurso.Criteria criteria = GestorPercurso.Criteria.DISTANCE;
        
        Place place1 = new Place(1,"test1");
        Place place2 = new Place(2,"test2");
        Place place3 = new Place(3,"test3");
        Place place4 = new Place(4,"test4");
        Place place5 = new Place(5,"test5");
        Vertex<Place> p1 = instance.addPlace(place1);
        Vertex<Place> p2 = instance.addPlace(place2);
        Vertex<Place> p3 = instance.addPlace(place3);
        Vertex<Place> p4 = instance.addPlace(place4);
        Vertex<Place> p5 = instance.addPlace(place5);     
        Connection c1 = new Connection(1, Connection.Type.BRIDGE.getUnit(), "caminho1", null, true, 4, 100);
        Connection c2 = new Connection(2, Connection.Type.PATH.getUnit(), "caminho2", null, false, 1, 100);
        Connection c3 = new Connection(3, Connection.Type.BRIDGE.getUnit(), "caminho3", null, true, 3, 100);
        Connection c4 = new Connection(4, Connection.Type.BRIDGE.getUnit(), "caminho4", null, true, 4, 100);
        Connection c5 = new Connection(5, Connection.Type.PATH.getUnit(), "caminho5", null, false, 0, 100);
        Connection c6 = new Connection(6, Connection.Type.BRIDGE.getUnit(), "caminho6", null, true, 2, 100);
        //2way connections
        Connection c52 = new Connection(52, Connection.Type.PATH.getUnit(), "caminho2", null, true, 2, 100);
        Connection c55 = new Connection(55, Connection.Type.PATH.getUnit(), "caminho5", null, true, 0, 100);

        instance.addConnection(p1, p4, c1);
        instance.addConnection(p1, p2, c2);
        instance.addConnection(p5, p2, c3);
        instance.addConnection(p2, p3, c4);
        instance.addConnection(p5, p3, c5);
        instance.addConnection(p4, p5, c6);
        instance.addConnection(p2, p1, c52);
        instance.addConnection(p3, p5, c55);
        
        Place orig = place1;
        Place dst = place5;
        List<Place> places =  new ArrayList<>();
        List<Connection> connections =  new ArrayList<>();
        int insert = 0;
        boolean bridge = true;
        boolean bike = false;
        /*
        Acording to the layout of this map, the easiest way to go from place1 to place5 is by going through place4
        and then finally arrive at place5. 
        Since the distance of any connection is 100 then the expected result will be 100*n,
        n being the number of connections
        So the cost of p1->p4->p5 is 100*2 = 200
        */
        int expResult = 200; 
        int result = instance.minimumCostPath(criteria, orig, dst, places, connections, insert, bridge, bike);
        assertEquals(expResult, result);

    }
    
    /**
     * Test of minimumCostPath method, of class GestorPercurso.
     */
    @Test
    public void testMinimumCostPath_withBridges_OnBike_onDistance() {
        System.out.println("minimumCostPath");
        GestorPercurso instance = new GestorPercurso();
        GestorPercurso.Criteria criteria = GestorPercurso.Criteria.DISTANCE;
        
        Place place1 = new Place(1,"test1");
        Place place2 = new Place(2,"test2");
        Place place3 = new Place(3,"test3");
        Place place4 = new Place(4,"test4");
        Place place5 = new Place(5,"test5");
        Vertex<Place> p1 = instance.addPlace(place1);
        Vertex<Place> p2 = instance.addPlace(place2);
        Vertex<Place> p3 = instance.addPlace(place3);
        Vertex<Place> p4 = instance.addPlace(place4);
        Vertex<Place> p5 = instance.addPlace(place5);     
        Connection c1 = new Connection(1, Connection.Type.BRIDGE.getUnit(), "caminho1", null, false, 4, 100);
        Connection c2 = new Connection(2, Connection.Type.PATH.getUnit(), "caminho2", null, true, 1, 100);
        Connection c3 = new Connection(3, Connection.Type.BRIDGE.getUnit(), "caminho3", null, true, 3, 100);
        Connection c4 = new Connection(4, Connection.Type.BRIDGE.getUnit(), "caminho4", null, true, 4, 100);
        Connection c5 = new Connection(5, Connection.Type.PATH.getUnit(), "caminho5", null, true, 0, 100);
        Connection c6 = new Connection(6, Connection.Type.BRIDGE.getUnit(), "caminho6", null, true, 2, 100);
        //2way connections
        Connection c52 = new Connection(52, Connection.Type.PATH.getUnit(), "caminho2", null, true, 2, 100);
        Connection c55 = new Connection(55, Connection.Type.PATH.getUnit(), "caminho5", null, true, 0, 100);

        instance.addConnection(p1, p4, c1);
        instance.addConnection(p1, p2, c2);
        instance.addConnection(p5, p2, c3);
        instance.addConnection(p2, p3, c4);
        instance.addConnection(p5, p3, c5);
        instance.addConnection(p4, p5, c6);
        instance.addConnection(p2, p1, c52);
        instance.addConnection(p3, p5, c55);
        
        Place orig = place1;
        Place dst = place5;
        List<Place> places =  new ArrayList<>();
        List<Connection> connections =  new ArrayList<>();
        int insert = 0;
        boolean bridge = true;
        boolean bike = true;
        /*
        Acording to the layout of this map, the easiest way to go from place1 to place5 is by going through place4
        and then finally arrive at place5. But by bike the connection to place4 is not available,
        and from place2 to place 5 the bridge only goes one way from place5,
        so the next place to go is place3.
        Since the distance of any connection is 100 then the expected result will be 100*n,
        n being the number of connections
        So the cost of p1->p2->p3->p5 is 100*3 = 300
        */
        int expResult = 300; 
        int result = instance.minimumCostPath(criteria, orig, dst, places, connections, insert, bridge, bike);
        assertEquals(expResult, result);

    }
    
    
    
    /**
     * Test of getPathWithInterestPoints method, of class GestorPercurso.
     */
    @Test(expected = GestorPercursoException.class)
    public void testGetPathWithInterestPoints_ExceptionThrown_OnInvalidInput() {
        System.out.println("getPathWithInterestPoints");
        List<Place> placesToVisit = null;
        GestorPercurso.Criteria criteria = null;
        List<Place> fullVisits = null;
        List<Connection> fullPath = null;
        boolean bridge = false;
        boolean bike = false;
        GestorPercurso instance = new GestorPercurso();

        int result = instance.getPathWithInterestPoints(placesToVisit, criteria, fullVisits, fullPath, bridge, bike);
     
  
    }
     /**
     * Test of getPathWithInterestPoints method, of class GestorPercurso.
     */
    @Test
    public void testGetPathWithInterestPoints_2PlacestoVisit() {
        System.out.println("getPathWithInterestPoints");
        
        GestorPercurso instance = new GestorPercurso();
        GestorPercurso.Criteria criteria = GestorPercurso.Criteria.COST;
        
        Place place1 = new Place(1,"test1");
        Place place2 = new Place(2,"test2");
        Place place3 = new Place(3,"test3");
        Place place4 = new Place(4,"test4");
        Place place5 = new Place(5,"test5");
        Vertex<Place> p1 = instance.addPlace(place1);
        Vertex<Place> p2 = instance.addPlace(place2);
        Vertex<Place> p3 = instance.addPlace(place3);
        Vertex<Place> p4 = instance.addPlace(place4);
        Vertex<Place> p5 = instance.addPlace(place5);     
        Connection c1 = new Connection(1, Connection.Type.BRIDGE.getUnit(), "caminho1", null, false, 4, 100);
        Connection c2 = new Connection(2, Connection.Type.PATH.getUnit(), "caminho2", null, true, 1, 100);
        Connection c3 = new Connection(3, Connection.Type.BRIDGE.getUnit(), "caminho3", null, true, 3, 100);
        Connection c4 = new Connection(4, Connection.Type.BRIDGE.getUnit(), "caminho4", null, true, 4, 100);
        Connection c5 = new Connection(5, Connection.Type.PATH.getUnit(), "caminho5", null, true, 1, 100);
        Connection c6 = new Connection(6, Connection.Type.BRIDGE.getUnit(), "caminho6", null, true, 2, 100);
        //2way connections
        Connection c52 = new Connection(52, Connection.Type.PATH.getUnit(), "caminho2", null, true, 1, 100);
        Connection c55 = new Connection(55, Connection.Type.PATH.getUnit(), "caminho5", null, true, 1, 100);

        instance.addConnection(p1, p4, c1);
        instance.addConnection(p1, p2, c2);
        instance.addConnection(p5, p2, c3);
        instance.addConnection(p2, p3, c4);
        instance.addConnection(p5, p3, c5);
        instance.addConnection(p4, p5, c6);
        instance.addConnection(p2, p1, c52);
        instance.addConnection(p3, p5, c55);      
        boolean bridge = true;
        boolean bike = false;
        List<Place> placesToVisit = new ArrayList<>();
        placesToVisit.add(place4);
        placesToVisit.add(place2);
        List<Place> fullVisits = new ArrayList<>();
        List<Connection> fullPath = new ArrayList<>();
        /*
        Acording to the layout of this map the entry point is place1.
        The easiest path to go visit place4 and place2 is by going from place1 to place4
        and then place5 to place2 and back to place1. 
        So the cost of p1->p4->p5->p2->p1 is respectively 4+2+3+1 = 10.
        */
        int expResult = 10;//check visual graph graphTest1 for better understanding
        int result = instance.getPathWithInterestPoints(placesToVisit, criteria, fullVisits, fullPath, bridge, bike);
        System.out.println(fullVisits.toString());
        assertEquals(expResult, result);
  
    }
    
    /**
     * Test of getPathWithInterestPoints method, of class GestorPercurso.
     */
    @Test
    public void testGetPathWithInterestPoints_3PlacesToVisit() {
        System.out.println("getPathWithInterestPoints");
        
        GestorPercurso instance = new GestorPercurso();
        GestorPercurso.Criteria criteria = GestorPercurso.Criteria.COST;
        
        Place place1 = new Place(1,"test1");
        Place place2 = new Place(2,"test2");
        Place place3 = new Place(3,"test3");
        Place place4 = new Place(4,"test4");
        Place place5 = new Place(5,"test5");
        Vertex<Place> p1 = instance.addPlace(place1);
        Vertex<Place> p2 = instance.addPlace(place2);
        Vertex<Place> p3 = instance.addPlace(place3);
        Vertex<Place> p4 = instance.addPlace(place4);
        Vertex<Place> p5 = instance.addPlace(place5);     
        Connection c1 = new Connection(1, Connection.Type.BRIDGE.getUnit(), "caminho1", null, false, 4, 100);
        Connection c2 = new Connection(2, Connection.Type.PATH.getUnit(), "caminho2", null, true, 1, 100);
        Connection c3 = new Connection(3, Connection.Type.BRIDGE.getUnit(), "caminho3", null, true, 3, 100);
        Connection c4 = new Connection(4, Connection.Type.BRIDGE.getUnit(), "caminho4", null, true, 4, 100);
        Connection c5 = new Connection(5, Connection.Type.BRIDGE.getUnit(), "caminho5", null, true, 1, 100);
        Connection c6 = new Connection(6, Connection.Type.BRIDGE.getUnit(), "caminho6", null, true, 2, 100);
 
        //2way connections
        Connection c52 = new Connection(52, Connection.Type.PATH.getUnit(), "caminho2", null, true, 1, 100);

        
        instance.addConnection(p1, p4, c1);
        instance.addConnection(p1, p2, c2);
        instance.addConnection(p5, p2, c3);
        instance.addConnection(p2, p3, c4);
        instance.addConnection(p3, p5, c5);
        instance.addConnection(p4, p5, c6);
        instance.addConnection(p2, p1, c52);

     
        boolean bridge = true;
        boolean bike = false;
        List<Place> placesToVisit = new ArrayList<>();
        placesToVisit.add(place3);
        placesToVisit.add(place4);
        placesToVisit.add(place2);
        List<Place> fullVisits = new ArrayList<>();
        List<Connection> fullPath = new ArrayList<>();
        /*
        Acording to the layout of this map the entry point is place1.
        The easiest path to go visit place4, place3 and place2 is by going from place1 to place4
        and then place5 to place2 to place3 to place5 to place2 back to place1. 
        So the cost of p1->p4->p5->p2->p3->p5->p2->p1 is respectively 4+2+3+4+1+3+1 = 18.
        */
        int expResult = 18;//check visual graph graphTest2
        int result = instance.getPathWithInterestPoints(placesToVisit, criteria, fullVisits, fullPath, bridge, bike);
        System.out.println(fullVisits.toString());
        System.out.println(fullPath.toString());
        assertEquals(expResult, result);
  
    }
   
}

package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import FileHandler.Objects;
import FileHandler.ObjectsFileHandler;
import diGraph.DiGraph;
import diGraph.DiGraphImpl;
import graph.Edge;
import graph.InvalidVertexException;
import graph.Vertex;
import model.Connection.Type;

/**
 *
 * Class GestorPercurso, is responsible for the behavior of the gestor
 *
 * @author (Daniel Afonso & André Ribeiro)
 * @version (19/11/18)
 */
public class GestorPercurso {

    /**
     * Enum Criteria, is an enum for the type of criteria to take on the calculation of paths
     */
    public enum Criteria {

        /**
         * Abstraction of value in space
         */
        DISTANCE,

        /**
         * Abstraction of value in currency
         */
        COST;

        /**
         *
         * @return String type of criteria
         */
        public String getUnit() {
            switch (this) {
                case COST:
                    return "Dollars";

                case DISTANCE:
                    return "Miles";
            }
            return "Unknown";
        }
    };

    private final DiGraph<Place, Connection> graph;

    /**
     * Constructor of the GestorPercurso
     */
    public GestorPercurso() {
        this.graph = new DiGraphImpl<>();
    }

    private Vertex<Place> checkPlace(Place place) throws GestorPercursoException {
        if (place == null) {
            throw new GestorPercursoException("Place cannot be null");
        }

        Vertex<Place> find = null;
        for (Vertex<Place> v : graph.vertices()) {
            if (v.element().equals(place)) {
                find = v;
            }
        }

        if (find == null) {
            throw new InvalidVertexException("Place with id (" + place.getId() + ") does not exist");
        }

        return find;
    }

    /**
     * Loads a file from the computer into a gestor
     *
     * @throws NullPointerException
     */
    public void load() throws NullPointerException {
        Objects objects = ObjectsFileHandler.load();
        List<Connection> connections = objects.listConnections();
        List<Place> places = objects.listPlaces();
        addPlaces(places);
        addConnections(connections, places);
    }

    private void addPlaces(List<Place> places) throws GestorPercursoException {
        for (Place place : places) {
            if (place == null) {
                throw new GestorPercursoException("Place cannot be null");
            }
            addPlace(place);
        }
    }

    private void addConnections(List<Connection> connections, List<Place> places) throws GestorPercursoException {

        for (Connection con : connections) {
            if (con == null) {
                throw new GestorPercursoException("Connection is null");
            }
            Vertex<Place> a1 = checkPlace(places.get(con.getConnections().get(0) - 1));
            Vertex<Place> a2 = checkPlace(places.get(con.getConnections().get(1) - 1));

            addConnection(a1, a2, con);
            if (con.getType().equals(Type.PATH.getUnit())) {
                Connection back = new Connection(con.getId() + 50, con.getType(), con.getName(), con.getConnections(),
                        con.isAvailable(), con.getPrice(), con.getDistance());
                addConnection(a2, a1, back);
            }
        }
    }

    /**
     * Adds a place into the gestor
     *
     * @param place - the place to add
     * @return Vertex - the vertex that was added
     */
    public Vertex<Place> addPlace(Place place) throws GestorPercursoException {
        if(place != null){
            for (Vertex<Place> v : graph.vertices()) {
            if (v.element().getId()==place.getId()) {
               throw new GestorPercursoException("Place has ID of existing place in the graph");
            }
        }
        return graph.insertVertex(place);
        } else {
         throw new GestorPercursoException("Place cannot be null");
        }
    }

    /**
     * Adds a connection into the gestor
     *
     * @param p1 - the place to connect
     * @param p2 - the place to connect
     * @param edgeElement - the element of the connection to add
     * @return Edge - the edge that was added
     */
    public Edge<Connection, Place> addConnection(Vertex<Place> p1, Vertex<Place> p2, Connection edgeElement) throws GestorPercursoException{
        if(p1.equals(p2))
           throw new GestorPercursoException("Connections within the same place are not permitted"); 
        if(edgeElement != null){
            for (Edge<Connection,Place> v : graph.edges()) {
            if (v.element().getId()==edgeElement.getId()) {
               throw new GestorPercursoException("Connection has ID of existing connection in the graph");
            }
        }
        return graph.insertEdge(p1, p2, edgeElement);
        } else {
            throw new GestorPercursoException("Edge element cannot be null");
        }
    }

    /**
     * Returns an iterable of the places of the gestor
     *
     * @return Iterable - an iterable of the places
     */
    public Iterable<Vertex<Place>> getPlaces() {
        return graph.vertices();
    }

    /**
     * Returns an iterable of the connections of the gestor
     *
     * @return Iterable - an iterable of the connections
     */
    public Iterable<Edge<Connection, Place>> getConnections() {
        return graph.edges();
    }

    /**
     * Returns a Place with this id that is in the gestor
     *
     * @param id - the id of the place
     * @return Place- the place with this id
     */
    public Place getVertexWith(int id) {
        Place place = null;
        for (Vertex<Place> p : graph.vertices()) {
            if (p.element().getId() == id) {
                place = p.element();
            }
        }
        return place;
    }

   

    /**
     * Calculates the minimum cost of a certain path from the origin to the destination
     *
     * @param criteria - the criteria of the cost
     * @param orig - where we start
     * @param dst - where we finish
     * @param places - list to be returned as parameter
     * @param connections - list to be returned as parameter
     * @param insert - where to insert the new discovered paths and places
     * @param bridge - true if it accepts bridges, false otherwise
     * @param bike - true if it only accepts bikes, false otherwise
     * @return int - the cost of the minimum short path
     * @throws GestorPercursoException
     */
    public int minimumCostPath(Criteria criteria, Place orig, Place dst, List<Place> places,
            List<Connection> connections, int insert, boolean bridge, boolean bike) throws GestorPercursoException {  
         if(dst == null)
            throw new GestorPercursoException("Destination cannot be null");
         if(insert < 0)
            throw new GestorPercursoException("Bad list insert");
         if(places == null)
            throw new GestorPercursoException("Places cannot be null");
          if(connections == null)
            throw new GestorPercursoException("Connections cannot be null");

        // this method is pivital for our implementation, althought its not the fully developed method
        // we use this method in another to give us the best path we can take from x amounts of places, up to 3
        HashMap<Vertex<Place>, Double> distance = new HashMap<>(); // initialization of the map for the distances

        HashMap<Vertex<Place>, Vertex<Place>> pre = new HashMap<>(); // initialization of the map for the predecessors

        HashMap<Vertex<Place>, Edge<Connection, Place>> connMap = new HashMap<>(); // inicialization of the map for the connections between edges

        Vertex<Place> origin = checkPlace(orig); //vertex of origin
        Vertex<Place> destination = checkPlace(dst); //vertex of destination

        dijkstra(criteria, origin, distance, pre, connMap, bridge, bike); //method dijkstra

        double cost = distance.get(destination); //the cost of the distance

        while (destination != origin) { //insertions of the paths and connections into the lists to return 
            places.add(insert, destination.element());
            connections.add(insert, connMap.get(destination).element());
            destination = pre.get(destination);
        }
        // return the cost as int, since connections save only as int
        return (int) cost;
    }

    private void dijkstra(Criteria criteria, Vertex<Place> orig, Map<Vertex<Place>, Double> costs,
            Map<Vertex<Place>, Vertex<Place>> predecessors, HashMap<Vertex<Place>, Edge<Connection, Place>> connMap,
            boolean bridges, boolean bike) throws GestorPercursoException{
        if(criteria == null)
            throw new GestorPercursoException("Path must have a valid criteria");
        if(orig == null)
            throw new GestorPercursoException("Origin cannot be null");
        // method dijkstra, developed together with slides of the class
        List<Vertex<Place>> unvisited = new ArrayList<>();
        for (Vertex<Place> v : graph.vertices()) {
            unvisited.add(v);
            costs.put(v, Double.MAX_VALUE);
            predecessors.put(v, null);
        }
        costs.put(orig, 0.0);
        while (!unvisited.isEmpty()) {
            Vertex<Place> u = findLowerCostVertex(unvisited, costs);
            unvisited.remove(u);
            for (Edge<Connection, Place> edge : graph.outboundEdges(u)) {
                if (bike && !edge.element().isAvailable()) { //if it only accepts bikes, and the edge isnt available for bikes, it is not put in the list
                    continue;
                }
                Vertex<Place> opposite = graph.opposite(u, edge);
                if (unvisited.contains(opposite)) {
                    double cost = 0;
                    switch (criteria) { // calculation of the cost according to the criteria
                        case COST:
                            cost = edge.element().getPrice() + costs.get(u);
                            break;
                        case DISTANCE:
                            cost = edge.element().getDistance() + costs.get(u);
                            break;

                    }
                    if (bridges) { // if it allows bridges, both bridges and non bridges should pass
                        if (cost < costs.get(opposite)) {
                            costs.put(opposite, cost);
                            predecessors.put(opposite, u);
                            connMap.put(opposite, edge);
                        }
                    } else { // if it doesnt allow bridges, only paths should pass
                        if (cost < costs.get(opposite) && edge.element().getType().equals(Type.PATH.getUnit())) {
                            costs.put(opposite, cost);
                            predecessors.put(opposite, u);
                            connMap.put(opposite, edge);
                        }
                    }
                }
            }
        }

    }

    private Vertex<Place> findLowerCostVertex(List<Vertex<Place>> unvisited, Map<Vertex<Place>, Double> costs) {
        double initial = Double.MAX_VALUE;

        Vertex<Place> best = null;

        for (Vertex<Place> place : unvisited) {
            if (costs.get(place) <= initial) {
                best = place;
                initial = costs.get(place);
            }
        }
        return best;
    }

    /**
     *
     * @param placesToVisit - list of places the user wants to visit
     * @param criteria - the criteria that user chooses
     * @param fullVisits - the list places to be returned
     * @param fullPath - the list of connections to be returned
     * @param bridge - true if it allows bridges, false otherwise
     * @param bike - true if it only allows bikes, false otherwise
     * @return
     */
    public int getPathWithInterestPoints(List<Place> placesToVisit, Criteria criteria, List<Place> fullVisits,
            List<Connection> fullPath, boolean bridge, boolean bike) throws GestorPercursoException {
        if(placesToVisit == null || placesToVisit.size() > graph.numVertices())
            throw new GestorPercursoException("Places to visit must be inserted or too many places");
        int bestCost = Integer.MAX_VALUE;
        int cost;
        int insert; // where to put the next places
        Place entrance = getVertexWith(1); // returns the entrance, first place to come from and last to go
        Place orig; // first origin
        Place dst; // all the destinations that the customer wants to see
        List<Place> visits = new ArrayList<>();
        List<Connection> path = new ArrayList<>();
        List<Place> placesToSee; // places to check if it is the best to go
        
        // since max is only 3, we can do this
        int times = placesToVisit.size(); //times to repeat the operation
        
        times = factorial(times); // when its 2 or 3, we want to do 3! or 2!
       
        while (times != 0) {
            insert = 0; // for the next round
            cost = 0;
            orig = entrance;
            visits.clear();
            path.clear();
            //trying to get the best path out of the input from the user
            placesToSee = mixedPlaces(placesToVisit, times); // all the combinations

            for (Place p : placesToSee) {
                dst = p; // destination to calculate

                cost += minimumCostPath(criteria, orig, dst, visits, path, insert, bridge, bike);
                insert = visits.size(); // where to insert on the lists

                orig = p; // calculation for next destination

            }

            dst = entrance;
            cost += minimumCostPath(criteria, orig, dst, visits, path, insert, bridge, bike); // calculation of the
            // path
            // from the
            // last
            // destination back to the entrance
            visits.add(0, entrance); // put the entrance in the beggining, to show where we started

            //calculation if the path is the best
            if (cost < bestCost) {
                copyListsPlace(visits, fullVisits);
                copyListsConnection(path, fullPath);
                bestCost = cost;
            }
            times--; //count of the times we did a certain destination
        }
        return bestCost; // returns final cost with the best value
    }

    // this method copies the list of places from an origin to its destination
    private void copyListsPlace(List<Place> origin, List<Place> dest) {
        dest.clear();
        for (Place p : origin) {
            dest.add(p);
        }
    }

    /**
     *
     * @param number
     * @return int factorial of n
     */
    public static int factorial(int number) {
        int result = 1;
        for (int factor = 2; factor <= number; factor++) {
            result *= factor;
        }
        return result;
    }

    // this method copies the list of connections from an origin to its destination
    private void copyListsConnection(List<Connection> origin, List<Connection> dest) {
        dest.clear();
        for (Connection c : origin) {
            dest.add(c);
        }
    }

    //this method returns all of the possibilities of the array
    private List<Place> mixedPlaces(List<Place> placesToVisit, int times) {
        int size = placesToVisit.size();
        List<Place> placesToSee = new ArrayList<>();
  
      
        switch (size) {
            case 3: //if the user choose 3 places to go
                if (times > 3) {
                    //this returns all of the possibilities until the 3rd time of it running
                    placesToSee.add(placesToVisit.get(times % 3));
                    placesToSee.add(placesToVisit.get((times - 1) % 3));
                    placesToSee.add(placesToVisit.get((times - 2) % 3));
                } else if (times == 3) {
                    //for the rest we have to manually input them
                    placesToSee.add(placesToVisit.get(0));
                    placesToSee.add(placesToVisit.get(1));
                    placesToSee.add(placesToVisit.get(2));
                } else if (times == 2) {
                    //that is why we have these 3 other ones here
                    placesToSee.add(placesToVisit.get(2));
                    placesToSee.add(placesToVisit.get(0));
                    placesToSee.add(placesToVisit.get(1));
                } else {
                    placesToSee.add(placesToVisit.get(1));
                    placesToSee.add(placesToVisit.get(2));
                    placesToSee.add(placesToVisit.get(0));
                }
                break;
            case 2: //if the user choose 2 places to go
                if (times == 2) {
                    placesToSee.add(placesToVisit.get(0));
                    placesToSee.add(placesToVisit.get(1));
                } else {
                    placesToSee.add(placesToVisit.get(1));
                    placesToSee.add(placesToVisit.get(0));
                }
                break;
            default: //if the user choose 1 place to go
                placesToSee.add(placesToVisit.get(0));
                break;
        }
        return placesToSee;
    }

     /**
     * the toString() of this object
     *
     * @return String - the toString() of this object
     */
    @Override
    public String toString() {
        String info = "";
        info += "PATH PLANER (" + graph.numVertices() + " places | " + graph.numEdges() + " connections)\n";
        for (Vertex<Place> place1 : graph.vertices()) {
            for (Vertex<Place> place2 : graph.vertices()) {
                if (!place1.equals(place2)) {

                    info += place1.element().toString() + " TO " + place2.element().toString() + "\n";

                    List<Connection> cons = graph.getConnectionsBetween(place1.element(), place2.element());
                    if (!cons.isEmpty()) {
                        info += "\t" + cons.get(0).toString() + "\n";
                    } else {
                        info += "\t(no connections)\n";
                    }
                    if (cons.size() > 1) {
                        info += "\t" + cons.get(1).toString() + "\n";
                    }
                    info += "\n";
                }
            }
        }
        return info;
    }
}

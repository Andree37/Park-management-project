package FileHandler;

import java.util.*;
import model.Gestor.Connection;
import model.Gestor.Place;

/**
 *
 * Class Objects, represents a class that controls the objects used in the program
 *
 * @author (Daniel Afonso e André Ribeiro)
 * @version (11/11/18)
 */
public class Objects {

    private final ArrayList<Object> listOfObjects; // list of objects
    private final ArrayList<Connection> listOfConnections; // list of connections
    private final ArrayList<Place> listOfPlaces; //list of places

    /**
     * Initializes Objects
     *
     */
    public Objects() { // inicialization of the atributes
        listOfObjects = new ArrayList<>();
        listOfConnections = new ArrayList<>();
        listOfPlaces = new ArrayList<>();
    }

    /**
     * Adds an object to the list
     *
     * @param object - object to add
     */
    public void addObject(Object object) {
        listOfObjects.add(object);
    }

    /**
     * Returns the list of objects
     *
     * @return - the list
     */
    public ArrayList<Object> listObjects() {
        return listOfObjects;
    }

    /**
     * Returns the list of Places
     *
     * @return - the list
     */
    public ArrayList<Place> listPlaces() {

        for (Object obj : listOfObjects) {
            if (obj.getClass().equals(new Place().getClass())) {
                listOfPlaces.add((Place) obj);
            }
        }
        return listOfPlaces;
    }

    /**
     * Returns the list of Connections
     *
     * @return - the list
     */
    public ArrayList<Connection> listConnections() {
        for (Object obj : listOfObjects) {
            if (obj.getClass().equals(new Connection().getClass())) {
                listOfConnections.add((Connection) obj);
            }
        }
        return listOfConnections;
    }

    /**
     * Returns a string of Objects and their status
     *
     * @return string - list of objects and its status
     */
    public String listOfObjects() {
        String result = "";
        for (Object object : listOfObjects) {

            result += object.toString() + "\r\n";

        }
        return result;
    }
}

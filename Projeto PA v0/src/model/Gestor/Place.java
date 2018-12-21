/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Gestor;

import java.util.Objects;

/**
 * Class Place, is responsible for the behavior of a place
 *
 * @author (Daniel Afonso e André Ribeiro)
 * @version (19/11/18)
 */
public class Place {

    private final int id;
    private final String name;

    /**
     * Constructor of the class Place
     *
     * @param id - id of place
     * @param name - name of place
     */
    public Place(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Constructor of a default Place, id = 0, name = ""
     */
    public Place() {
        this.id = 0;
        this.name = "";
    }

    /**
     * Returns the id of the place
     *
     * @return int id of place
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the place
     *
     * @return String name of place
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the toString() of this object
     *
     * @return String - the toString() of this object
     */
    @Override
    public String toString() {
        return name + '(' + id + ')';
    }

    /**
     * Returns the hashcode of this object
     *
     * @return int - haschode of this object
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        return hash;
    }

    /**
     * Returns if this object is equal to another
     *
     * @param obj - the other object
     * @return boolean - true if its equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Place other = (Place) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }

}

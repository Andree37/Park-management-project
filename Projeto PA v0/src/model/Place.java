/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

/**
 * Class Place, is responsible for the behavior of a place
 * 
 * @author (Daniel Afonso & André Ribeiro)
 * @version (19/11/18)
 */
public class Place {
	private final int id;
	private final String name;

    /**
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
     *
     * @return int id of place
     */
    public int getId() {
		return id;
	}

    /**
     *
     * @return String name of place
     */
    public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name + '(' + id + ')';
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 79 * hash + this.id;		
		return hash;
	}

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

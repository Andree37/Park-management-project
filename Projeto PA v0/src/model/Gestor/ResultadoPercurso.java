/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Gestor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import model.Gestor.GestorPercurso.Criteria;

/**
 *
 * @author AndreLaptop
 */
public class ResultadoPercurso implements Serializable{
    private Criteria criteria;
    private double cost;
    private List<Place> listPlaces;
    private List<Connection> listConnections;
    private boolean bikeAccess;
    private boolean bridgesAllowed;

  
    

    public ResultadoPercurso(Criteria criteria, List<Place> listPlaces, List<Connection> listConnections,boolean bikeAccess, boolean bridgesAllowed) {
        this.criteria = criteria;
        this.listPlaces = listPlaces;
        this.listConnections = listConnections;
        this.bikeAccess = bikeAccess;
        this.bridgesAllowed = bridgesAllowed;
        this.cost = initCost();
    }
    
    private double initCost() {
        double cost = 0.0;
        
        for(Connection c : listConnections) {
            cost += c.getPrice();
        }
        
        return cost;
    }

    public double getCost() {
        return cost;
    }

    public List<Place> getListPlacesCopy() {
        List unmodifiablePlaces = Collections.unmodifiableList(listPlaces);
        return unmodifiablePlaces;
    }

    public List<Connection> getListConnectionsCopy() {
        List unmodifiableConnections = Collections.unmodifiableList(listConnections);
        return unmodifiableConnections;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setListPlaces(List<Place> listPlaces) {
        this.listPlaces = listPlaces;
    }

    public void setListConnections(List<Connection> listConnections) {
        this.listConnections = listConnections;
    }

    public void setBikeAccess(boolean bikeAccess) {
        this.bikeAccess = bikeAccess;
    }

    public void setBridgesAllowed(boolean bridgesAllowed) {
        this.bridgesAllowed = bridgesAllowed;
    }
    
    public double getDistance() {
        double distance = 0.0;
        
        for(Connection c : listConnections) {
            distance += c.getDistance();
        }
        
        return distance;
    }
  
    public String getCriteria() {
        return criteria.toString();
    }

    public boolean isBikeAccess() {
        return bikeAccess;
    }
    
    public boolean isBridgesAllowed() {
        return bridgesAllowed;
    }
      
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

import java.util.List;
import model.Emission.PrintableDAO;
import model.Gestor.GestorPercurso;
import model.Gestor.GestorPercurso.Criteria;
import model.Gestor.GestorPercursoException;
import model.Gestor.Place;
import model.Gestor.ResultadoPercurso;

/**
 *
 * @author AndreLaptop
 */
class Controller {

    private GestorPercurso gestor;
    private boolean bike;
    private boolean bridge;
    private Criteria criteria;
    private List<Place> placesToCalculate;

    Controller(GestorPercurso gestor, boolean bike, boolean bridge, Criteria criteria, List<Place> placesToCalculate) {
        this.bike = bike;
        this.bridge = bridge;
        this.criteria = criteria;
        this.placesToCalculate = placesToCalculate;
        this.gestor = gestor;
    }

    public ResultadoPercurso setPurchase() {
            return gestor.getPathWithInterestPoints(placesToCalculate, criteria, bridge, bike);
    }
    
    public void clearList() {
        placesToCalculate.clear();
    }

}

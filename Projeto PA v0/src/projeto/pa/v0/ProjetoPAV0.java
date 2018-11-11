/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.pa.v0;

import FileHandler.ObjectsFileHandler;
import java.util.ArrayList;
import model.GestorPercurso;

/**
 *
 * @author Darfkman
 */
public class ProjetoPAV0 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
        GestorPercurso gestor = new GestorPercurso();
        
        gestor.addPlaces();
        gestor.addConnections();
        System.out.println(gestor.toString());
    }
    
}

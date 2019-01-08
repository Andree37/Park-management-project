/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

import GestorFX.javafxgraph_v2.graphview.FactoryPlacementStrategy;
import GestorFX.javafxgraph_v2.graphview.GraphPanel;
import GestorFX.javafxgraph_v2.graphview.VertexPlacementStrategy;
import diGraph.DiGraph;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import model.Emission.PrintableDAO;
import model.Emission.PrintableDAOSerializable;
import model.Gestor.GestorPercurso;

/**
 *
 * @author AndreLaptop
 */
public class ProjetoFXMain extends Application {


    @Override
    public void start(Stage primaryStage) {

        //load the graph pane
        VertexPlacementStrategy placementStrategy = FactoryPlacementStrategy.getPlacementStrategy("circular");
        GestorPercurso gestor = new GestorPercurso();

        //load gestor and the panel
        try {
            gestor.load();
        } catch (NullPointerException e) {
            System.err.println("Must choose a readable file");
            return;
        }
        DiGraph theGraph = gestor.getGraph();
        GraphPanel graphPanel = new GraphPanel(theGraph, placementStrategy);
        
        //create DAO
        PrintableDAO dao = new PrintableDAOSerializable("Serialized.dat");
        
        UIBase root = new UIBase(gestor,dao);
        root.setCenter(graphPanel);
        primaryStage.setScene(new Scene(root,1200,700));
        primaryStage.show();
        
        //after its added to a Scene, we plot the graph
        graphPanel.plotGraph();
    }

    /**
     * @param args the command line arguments
     */ 
    public static void main(String[] args) {
        launch(args);
    }

}

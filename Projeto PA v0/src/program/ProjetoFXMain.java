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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import model.Gestor.GestorPercurso;

/**
 *
 * @author AndreLaptop
 */
public class ProjetoFXMain extends Application {

    @FXML
    private Pane graphPane;

    @Override
    public void start(Stage primaryStage) {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("UI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ProjetoFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }

        //load the graph pane
        VertexPlacementStrategy placementStrategy = FactoryPlacementStrategy.getPlacementStrategy("circular");
        GestorPercurso gestor = new GestorPercurso();

        try {
            gestor.load();
        } catch (NullPointerException e) {
            System.err.println("Must choose a readable file");
            return;
        }
        DiGraph theGraph = gestor.getGraph();
        
        GraphPanel graphPanel = new GraphPanel(theGraph, placementStrategy);
        graphPane = new Pane();
        graphPane.getChildren().add(graphPanel);

        primaryStage.setScene(new Scene(root,900,600));
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

import GestorFX.javafxgraph_v2.graphview.FactoryPlacementStrategy;
import GestorFX.javafxgraph_v2.graphview.GraphPanel;
import GestorFX.javafxgraph_v2.graphview.VertexPlacementStrategy;
import Logger.Logger;
import diGraph.DiGraph;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import model.Emission.PrintableDAO;
import model.Emission.PrintableDAOSQLite;
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
        Properties prop = new Properties();
        InputStream input = null;
        String DAOType = "";
        try {
            input = new FileInputStream("config.properties");
            // loads the properties file
            prop.load(input);
            DAOType = prop.getProperty("DAO");
        } catch (IOException ex) {
            ex.getMessage();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.getMessage();
                }
            }
        }
        PrintableDAO dao = null;
        if (DAOType.equals("Serializable")) {
            dao = new PrintableDAOSerializable("Serialized.dat");
        }
        if (DAOType.equals("SQLite")) {
            dao = new PrintableDAOSQLite("jdbc:mysql://localhost:3306/", "root", "1234");
        }

        UIBase root = new UIBase(gestor, dao);
        root.setCenter(graphPanel);
        primaryStage.setScene(new Scene(root, 1200, 700));
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

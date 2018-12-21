/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorFX.javafxgraph_v2.graphview;

/**
 *
 * @author AndreLaptop
 */
public class FactoryPlacementStrategy {

    public static VertexPlacementStrategy getPlacementStrategy(String strategy) {
        switch (strategy.toLowerCase()) {
            case "circular":
                return new CircularPlacementStrategy();
            case "sorted":
                return new CircularSortedPlacementStrategy();
            default: // random  
                return new RandomPlacementStrategy();
        }
    }

}

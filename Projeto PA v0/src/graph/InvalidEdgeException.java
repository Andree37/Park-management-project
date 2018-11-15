/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra.graph;

/**
 *
 * @author brunomnsilva
 */
public class InvalidEdgeException extends RuntimeException {

    public InvalidEdgeException() {
        super("The edge is invalid or does not belong to this graph.");
    }

    public InvalidEdgeException(String string) {
        super(string);
    }
    
}

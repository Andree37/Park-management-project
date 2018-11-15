/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

/**
 *
 * @author brunomnsilva
 */
public class InvalidVertexException extends RuntimeException {

    public InvalidVertexException() {
        super("The vertex is invalid or does not belong to this graph.");
    }

    public InvalidVertexException(String string) {
        super(string);
    }
    
}

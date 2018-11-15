/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

/**
 * Data-independent representation of an edge.
 * @author brunomnsilva
 * @param <E> Type of value stored in the edge
 * @param <V> Type of value stored in the vertices that this edge connects.
 */
public interface Edge<E, V> {
    /**
     * Returns the element (object reference) stored in this edge.
     * @return stored element
     */
    public E element();
    
    /**
     * Returns references of both vertices that this edge connects in the form
     * of an array. 
     * @return an array of length 2, i.e., vertices()[0] and vertices()[1]
     */
    public Vertex<V>[] vertices();
}

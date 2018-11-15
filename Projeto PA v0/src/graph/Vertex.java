/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra.graph;

/**
 * Data-independent representation of a vertex.
 * @author brunomnsilva
 * @param <V> Type of value stored in the vertex.
 */
public interface Vertex<V> {
    /**
     * Returns the element (object reference) stored in this vertex.
     * @return stored element
     */
    public V element();
}

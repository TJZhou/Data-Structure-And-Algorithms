package com.tianju.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * Tianju Zhou
 * Jun 14, 2020
 * @param <K> Key
 * @param <V> Value
 */
class Vertex<K, V> {

    K key;
    V val;
    int indegree;
    List<Edge<K, V>> adjacencyList;

    public Vertex(K key, V val) {
        this.key = key;
        this.val = val;
        this.adjacencyList = new LinkedList<>();
    }

    public K getKey() {
        return key;
    }

    public V getVal() {
        return val;
    }

    public int getIndegree() {
        return indegree;
    }

    public List<Edge<K, V>> getNeighbors() {
        return adjacencyList;
    }
}

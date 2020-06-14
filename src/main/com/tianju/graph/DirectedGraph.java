package com.tianju.graph;

import java.util.List;

/**
 * Tianju Zhou
 * Jun 14, 2020
 * @param <K> Key
 * @param <V> Value
 */
public class DirectedGraph<K, V> extends Graph<K, V> {

    public DirectedGraph() {
        super();
    }

    public int edgeNum() {
        int sum = 0;
        for(List<Node<K,V>> l : adjacencyList.values())
            sum += l.size();
        return sum;
    }

    public void addEdge(Node<K, V> from, Node<K, V> to) {
        if(!vertices.contains(from))
            addVertex(from);
        if(!vertices.contains(to))
            addVertex(to);
        adjacencyList.get(from).add(to);
    }

    public void removeEdge(Node<K, V> from, Node<K, V> to) {
        adjacencyList.get(from).remove(to);
    }
}

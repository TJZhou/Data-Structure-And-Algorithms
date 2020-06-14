package com.tianju.graph;

import java.util.List;

/**
 * Tianju Zhou
 * Jun 14, 2020
 * @param <K> Key
 * @param <V> Value
 */
public class UnDirectedGraph<K, V> extends Graph<K, V> {

    public UnDirectedGraph() {
        super();
    }

    public int edgeNum() {
        int sum = 0;
        for(List<Node<K,V>> l : adjacencyList.values())
            sum += l.size();
        return sum / 2;
    }

    public void addEdge(Node<K, V> from, Node<K, V> to) {
        if(!vertices.contains(from))
            addVertex(from);
        if(!vertices.contains(to))
            addVertex(to);
        adjacencyList.get(from).add(to);
        adjacencyList.get(to).add(from);
    }

    public void removeEdge(Node<K, V> from, Node<K, V> to) {
        adjacencyList.get(from).remove(to);
        adjacencyList.get(to).remove(from);
    }
}

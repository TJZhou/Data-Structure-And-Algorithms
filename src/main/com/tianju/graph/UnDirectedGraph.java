package com.tianju.graph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    /**
     * Refer to Tushar Roy
     * https://www.youtube.com/watch?v=n_t0a_8H8VY
     */
    public boolean containsCycle() {
        Set<Node<K, V>> visited = new HashSet<>();
        for(Node<K, V> node : vertices) {
            if(visited.contains(node))
                continue;
            if(cycleDetect(visited, node, null))
                return true;
        }
        return false;
    }

    private boolean cycleDetect(Set<Node<K, V>> visited, Node<K, V> node, Node<K, V> parent) {
        visited.add(node);
        for(Node<K, V> n : adjacencyList.get(node)) {
            if(n == parent)
                continue;
            if(visited.contains(n) || cycleDetect(visited, n, node))
                return true;
        }
        return false;
    }
}

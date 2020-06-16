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
public class UnDirectedGraph<K, V, W> extends Graph<K, V, W> {

    public UnDirectedGraph() {
        super();
    }

    public int edgeNum() {
        int sum = 0;
        for(List<Edge<K, V, W>> l : adjacencyList.values())
            sum += l.size();
        return sum / 2;
    }

    public void addEdge(Vertex<K, V> from, Vertex<K, V> to, W w) {
        if(!vertices.contains(from))
            addVertex(from);
        if(!vertices.contains(to))
            addVertex(to);
        adjacencyList.get(from).add(new Edge<>(from, to, w));
        adjacencyList.get(to).add(new Edge<>(to, from, w));
    }

    public void removeEdge(Vertex<K, V> from, Vertex<K, V> to) {
        adjacencyList.get(from).removeIf(e -> e.to == to);
        adjacencyList.get(to).removeIf(e -> e.from == from);
    }

    /**
     * Refer to Tushar Roy
     * https://www.youtube.com/watch?v=n_t0a_8H8VY
     */
    public boolean containsCycle() {
        Set<Vertex<K, V>> visited = new HashSet<>();
        for(Vertex<K, V> vertex : vertices) {
            if(visited.contains(vertex))
                continue;
            if(cycleDetect(visited, vertex, null))
                return true;
        }
        return false;
    }

    private boolean cycleDetect(Set<Vertex<K, V>> visited, Vertex<K, V> vertex, Vertex<K, V> parent) {
        visited.add(vertex);
        for(Edge<K, V, W> e : adjacencyList.get(vertex)) {
            if(e.to == parent)
                continue;
            if(visited.contains(e.to) || cycleDetect(visited, e.to, vertex))
                return true;
        }
        return false;
    }
}

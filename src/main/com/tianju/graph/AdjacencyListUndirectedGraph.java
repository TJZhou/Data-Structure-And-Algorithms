package com.tianju.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * Tianju Zhou
 * Jun 14, 2020
 * @param <K> Key
 * @param <V> Value
 */
public class AdjacencyListUndirectedGraph<K, V> extends AdjacencyListGraph<K, V> {

    public AdjacencyListUndirectedGraph() {
        super();
    }

    public int edgeNum() {
        int sum = 0;
        for(Vertex<K, V> v : vertices)
            sum += v.adjacencyList.size();
        return sum / 2;
    }

    public void addEdge(Vertex<K, V> from, Vertex<K, V> to, double w) {
        from.indegree++;
        to.indegree++;
        if(!vertices.contains(from))
            addVertex(from);
        if(!vertices.contains(to))
            addVertex(to);
        from.adjacencyList.add(new Edge<>(from, to, w));
        to.adjacencyList.add(new Edge<>(to, from, w));
    }

    public void removeEdge(Vertex<K, V> from, Vertex<K, V> to) {
        from.indegree--;
        to.indegree--;
        from.adjacencyList.removeIf(e -> e.to == to);
        to.adjacencyList.removeIf(e -> e.from == from);
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
        if(visited.contains(vertex))
            return true;
        visited.add(vertex);
        for(Edge<K, V> e : vertex.adjacencyList) {
            // the vertex has self contained cycle
            if(e.from == e.to)
                return true;
            if(e.to == parent)
                continue;
            if(cycleDetect(visited, e.to, vertex))
                return true;
        }
        return false;
    }
}

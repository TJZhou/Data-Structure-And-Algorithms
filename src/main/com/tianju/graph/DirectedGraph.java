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
public class DirectedGraph<K, V, W> extends Graph<K, V, W> {

    public DirectedGraph() {
        super();
    }

    public int edgeNum() {
        int sum = 0;
        for(List<Edge<K, V, W>> l : adjacencyList.values())
            sum += l.size();
        return sum;
    }

    public void addEdge(Vertex<K, V> from, Vertex<K, V> to, W w) {
        if(!vertices.contains(from))
            addVertex(from);
        if(!vertices.contains(to))
            addVertex(to);
        adjacencyList.get(from).add(new Edge<K, V, W>(from, to, w));
    }

    public void removeEdge(Vertex<K, V> from, Vertex<K, V> to) {
        adjacencyList.get(from).removeIf(e -> e.to == to);
    }

    public boolean containsCycle() {
        Set<Vertex<K, V>> notVisited = new HashSet<>(vertices);
        Set<Vertex<K, V>> beingVisited = new HashSet<>();
        Set<Vertex<K, V>> totallyVisited = new HashSet<>();
        while(notVisited.size() > 0) {
            Vertex<K, V> vertex = notVisited.iterator().next();
            if(cycleDetect(notVisited, beingVisited, totallyVisited, vertex))
                return true;
        }
        return false;
    }

    /**
     * Refer to Tushar Roy: https://www.youtube.com/watch?v=rKQaZuoUR4M
     * @param notVisited: vertices which have not been visited yet
     * @param beingVisited: vertices currently are being visited
     * @param totallyVisited: vertices which has already been visited
     * @return true if there are cycles detected(vertices n in the beingVisited set)
     */
    private boolean cycleDetect(Set<Vertex<K, V>> notVisited, Set<Vertex<K, V>> beingVisited, Set<Vertex<K, V>> totallyVisited, Vertex<K, V> v) {
        notVisited.remove(v);
        beingVisited.add(v);
        for(Edge<K, V, W> e : adjacencyList.get(v)) {
            if(totallyVisited.contains(e.to))
                continue;
            if(beingVisited.contains(e.to))
                return true;
            if(cycleDetect(notVisited, beingVisited, totallyVisited, e.to))
                return true;
        }
        beingVisited.remove(v);
        totallyVisited.add(v);
        return false;
    }
}

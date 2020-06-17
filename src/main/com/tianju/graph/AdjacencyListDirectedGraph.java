package com.tianju.graph;

import java.util.*;

/**
 * Tianju Zhou
 * Jun 14, 2020
 * @param <K> Key
 * @param <V> Value
 */
public class AdjacencyListDirectedGraph<K, V> extends AdjacencyListGraph<K, V> {

    public AdjacencyListDirectedGraph() {
        super();
    }

    public int edgeNum() {
        int sum = 0;
        for(Vertex<K, V> v : vertices)
            sum += v.adjacencyList.size();
        return sum;
    }

    public void addEdge(Vertex<K, V> from, Vertex<K, V> to, double w) {
        to.indegree++;
        if(!vertices.contains(from))
            addVertex(from);
        if(!vertices.contains(to))
            addVertex(to);
        from.adjacencyList.add(new Edge<>(from, to, w));
    }

    public void removeEdge(Vertex<K, V> from, Vertex<K, V> to) {
        to.indegree--;
        from.adjacencyList.removeIf(e -> e.to == to);
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
    private boolean cycleDetect(Set<Vertex<K, V>> notVisited, Set<Vertex<K, V>> beingVisited, Set<Vertex<K, V>> totallyVisited, Vertex<K, V> vertex) {
        if(beingVisited.contains(vertex))
            return true;
        notVisited.remove(vertex);
        beingVisited.add(vertex);
        for(Edge<K, V> neighbor : vertex.adjacencyList) {
            if(totallyVisited.contains(neighbor.to))
                continue;
            if(cycleDetect(notVisited, beingVisited, totallyVisited, neighbor.to))
                return true;
        }
        beingVisited.remove(vertex);
        totallyVisited.add(vertex);
        return false;
    }

    public Collection<Vertex<K, V>> topologicalSort() {
        if(containsCycle())
            throw new IllegalArgumentException("Cannot sort graph with cycle");
        List<Vertex<K, V>> res = new ArrayList<>();
        Map<Vertex<K, V>, Integer> indegreeMap = new HashMap<>();
        Queue<Vertex<K, V>> q = new LinkedList<>();
        for(Vertex<K, V> v : vertices) {
            if(v.indegree == 0) {
                q.offer(v);
            } else {
                indegreeMap.put(v, v.indegree);
            }
        }

        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                Vertex<K, V> vertex = q.poll();
                res.add(vertex);
                for(Edge<K, V> e : vertex.adjacencyList) {
                    Vertex<K, V> neighbor = e.to;
                    indegreeMap.put(neighbor, indegreeMap.get(neighbor) - 1);
                    if(indegreeMap.get(neighbor) == 0) {
                        indegreeMap.remove(neighbor);
                        q.offer(neighbor);
                    }
                }
            }
        }
        return res;
    }
}

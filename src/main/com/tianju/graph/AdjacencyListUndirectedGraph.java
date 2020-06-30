package com.tianju.graph;

import java.util.*;

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
        for (Vertex<K, V> v : vertices)
            sum += v.adjacencyList.size();
        return sum / 2;
    }

    public void addEdge(Vertex<K, V> from, Vertex<K, V> to, double w) {
        from.indegree++;
        to.indegree++;
        if (!vertices.contains(from))
            addVertex(from);
        if (!vertices.contains(to))
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
        for (Vertex<K, V> vertex : vertices) {
            if (visited.contains(vertex))
                continue;
            if (cycleDetect(visited, vertex, null))
                return true;
        }
        return false;
    }

    private boolean cycleDetect(Set<Vertex<K, V>> visited, Vertex<K, V> vertex, Vertex<K, V> parent) {
        if (visited.contains(vertex))
            return true;
        visited.add(vertex);
        for (Edge<K, V> e : vertex.adjacencyList) {
            // the vertex has self contained cycle
            if (e.from == e.to)
                return true;
            if (e.to == parent)
                continue;
            if (cycleDetect(visited, e.to, vertex))
                return true;
        }
        return false;
    }

    /**
     * prim's algorithm to find minimum spanning tree
     *
     * @param src : random vertex to start prim's algorithm
     * @return : A prev map to store the parent vertex of each vertex
     */
    public Map<Vertex<K, V>, Vertex<K, V>> minimumSpanningTree1(Vertex<K, V> src) {
        Map<Vertex<K, V>, Vertex<K, V>> prev = new HashMap<>();
        Map<Vertex<K, V>, Double> costs = new HashMap<>();
        PriorityQueue<Map.Entry<Vertex<K, V>, Double>> pq = new PriorityQueue<>(Map.Entry.comparingByValue());
        for (Vertex<K, V> v : vertices)
            costs.put(v, Double.MAX_VALUE);
        costs.put(src, 0.0);
        prev.put(src, null);
        for (Map.Entry<Vertex<K, V>, Double> e : costs.entrySet())
            pq.offer(e);
        while (!pq.isEmpty()) {
            Vertex<K, V> v = pq.poll().getKey();
            for (Edge<K, V> edge : v.adjacencyList) {
                assert edge.from == v;
                // if the vertex is selected than continue
                if (prev.get(edge.to) != null)
                    continue;
                double cost = costs.get(edge.from) + edge.w;
                if (costs.get(edge.to) > cost) {
                    costs.put(edge.to, cost);
                    prev.put(edge.to, edge.from);
                    pq.removeIf(e -> e.getKey() == edge.to);
                    pq.offer(new AbstractMap.SimpleEntry<>(edge.to, cost));
                }
            }
        }
        return prev;
    }

    // Kruskal algorithm to find MST
    public List<Edge<K, V>> minimumSpanningTree2() {
        PriorityQueue<Edge<K, V>> pq = new PriorityQueue<>(Comparator.comparingDouble(x -> x.w));
        List<Edge<K, V>> res = new ArrayList<>();
        Map<Vertex<K, V>, Vertex<K, V>> disjointSet = new HashMap<>();
        for (Vertex<K, V> v : vertices) {
            for (Edge<K, V> e : v.adjacencyList)
                pq.offer(e);
        }
        // initialize disjoint set. At beginning, each set only has one vertex (each node is a set)
        for(Vertex<K, V> v : vertices)
            disjointSet.put(v, v);
        while (!pq.isEmpty()) {
            Edge<K, V> e = pq.poll();
            // use Union-Find Algorithm to detect cycle
            Vertex<K, V> group1 = find(disjointSet,e.from);
            Vertex<K, V> group2 = find(disjointSet, e.to);
            if (group1 == group2)
                continue;
            disjointSet.put(group1, group2);
            res.add(e);
        }
        return res;
    }

    private Vertex<K, V> find(Map<Vertex<K, V>, Vertex<K, V>> disjointSet, Vertex<K, V> v) {
        while(v != disjointSet.get(v)) {
            // path compression
            disjointSet.put(disjointSet.get(v), disjointSet.get(disjointSet.get(v)));
            v = disjointSet.get(v);
        }
        return v;
    }
}
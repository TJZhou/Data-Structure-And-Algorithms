package com.tianju.graph;


import java.util.*;

/**
 * Tianju Zhou
 * Jun 14, 2020
 * @param <K> Vertex Key
 * @param <V> Vertex Value
 * @param <W> Edge Weight
 */
public abstract class Graph<K, V, W> {

    protected Set<Vertex<K, V>> vertices;
    protected Map<Vertex<K, V>, List<Edge<K, V, W>>> adjacencyList;

    public Graph() {
        this.vertices = new HashSet<>();
        this.adjacencyList = new HashMap<>();
    }

    public int vertexNum() {
        return vertices.size();
    }
    
    public abstract int edgeNum();

    public void addVertex(Vertex<K, V> vertex) {
        vertices.add(vertex);
        adjacencyList.put(vertex, new LinkedList<>());
    }

    public void removeVertex(Vertex<K, V> vertex) {
        vertices.remove(vertex);
        adjacencyList.remove(vertex);
        for(List<Edge<K, V, W>> l : adjacencyList.values()) {
            l.removeIf(e -> e.to == vertex);
        }
    }

    public Set<Vertex<K, V>> getVertices() {
        return vertices;
    }

    public Map<Vertex<K, V>, List<Edge<K, V, W>>> getAdjacencyList() {
        return adjacencyList;
    }

    public List<Edge<K, V, W>> getNeighbors(Vertex<K,V> vertex) {
        return adjacencyList.get(vertex);
    }


    public List<Vertex<K, V>> dfs(Vertex<K, V> vertex) {
        Set<Vertex<K, V>> visited = new HashSet<>();
        // dfs result
        List<Vertex<K, V>> res = new ArrayList<>();
        dfsHelper(res, visited, vertex);
        // make sure all disconnected vertex has also been visited
        for(Vertex<K, V> v : vertices) {
            if(!visited.contains(v))
                dfsHelper(res, visited, v);
        }
        return res;
    }

    private void dfsHelper(List<Vertex<K, V>> res, Set<Vertex<K, V>> visited, Vertex<K, V> v) {
        if(visited.contains(v))
            return;
        visited.add(v);
        res.add(v);
        for(Edge<K, V, W> neighbor: adjacencyList.get(v))
            dfsHelper(res, visited, neighbor.to);
    }

    public List<Vertex<K, V>> bfs(Vertex<K, V> vertex) {
        Set<Vertex<K, V>> visited = new HashSet<>();
        // bfs result
        List<Vertex<K, V>> res = new ArrayList<>();
        bfsHelper(res, visited, vertex);
        // make sure all disconnected vertex has also been visited
        for(Vertex<K, V> v : vertices) {
            if(!visited.contains(v))
                bfsHelper(res, visited, v);
        }
        return res;
    }

    private void bfsHelper(List<Vertex<K, V>> res, Set<Vertex<K, V>> visited,  Vertex<K, V> vertex) {
        Queue<Vertex<K, V>> q = new LinkedList<>();
        q.offer(vertex);
        visited.add(vertex);
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                Vertex<K, V> v = q.poll();
                res.add(v);
                for(Edge<K, V,W> neighbor: adjacencyList.get(v)) {
                    if(visited.contains(neighbor.to)) continue;
                    q.offer(neighbor.to);
                    visited.add(neighbor.to);
                }
            }
        }
    }

    public abstract  boolean containsCycle();

    public abstract void addEdge(Vertex<K, V> from, Vertex<K, V> to, W w);

    public abstract void removeEdge(Vertex<K, V> from, Vertex<K, V> to);
}

package com.tianju.graph;


import java.util.*;

/**
 * Tianju Zhou
 * Jun 14, 2020
 * @param <K> Vertex Key
 * @param <V> Vertex Value
 */
public abstract class AdjacencyListGraph<K, V> {

    protected Set<Vertex<K, V>> vertices;

    public AdjacencyListGraph() {
        this.vertices = new HashSet<>();
    }

    public int vertexNum() {
        return vertices.size();
    }

    public abstract int edgeNum();

    public void addVertex(Vertex<K, V> vertex) {
        vertices.add(vertex);
    }

    public void addVertices(Collection<Vertex<K, V>> vertices) {
        for(Vertex<K, V> v : vertices)
            addVertex(v);
    }

    public void addEdge(Edge<K, V> e) {
        addEdge(e.from, e.to, e.w);
    }

    public void addEdges(Collection<Edge<K, V>> edges) {
        for(Edge<K, V> e : edges)
            addEdge(e);
    }

    public void removeVertex(Vertex<K, V> vertex) {
        // vertices in the adjacency list of current removed vertex should have indegree-1
        vertex.adjacencyList.forEach(e -> e.to.indegree--);
        vertices.remove(vertex);
        for(Vertex<K, V> v : vertices)
            v.adjacencyList.removeIf(e -> e.to == vertex);
    }

    public Set<Vertex<K, V>> getVertices() {
        return vertices;
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

    private void dfsHelper(List<Vertex<K, V>> res, Set<Vertex<K, V>> visited, Vertex<K, V> vertex) {
        if(visited.contains(vertex))
            return;
        visited.add(vertex);
        res.add(vertex);
        for(Edge<K, V> neighbor : vertex.adjacencyList)
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
                for(Edge<K, V> neighbor: v.adjacencyList) {
                    if(visited.contains(neighbor.to)) continue;
                    q.offer(neighbor.to);
                    visited.add(neighbor.to);
                }
            }
        }
    }

    public abstract  boolean containsCycle();

    public abstract void addEdge(Vertex<K, V> from, Vertex<K, V> to, double w);

    public abstract void removeEdge(Vertex<K, V> from, Vertex<K, V> to);
}

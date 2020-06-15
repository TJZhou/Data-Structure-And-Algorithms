package com.tianju.graph;


import java.util.*;

/**
 * Tianju Zhou
 * Jun 14, 2020
 * @param <K> Key
 * @param <V> Value
 */
public abstract class Graph<K,V> {

    protected Set<Node<K, V>> vertices;
    protected Map<Node<K, V>, List<Node<K, V>>> adjacencyList;

    public Graph() {
        this.vertices = new HashSet<>();
        this.adjacencyList = new HashMap<>();
    }

    public int vertexNum() {
        return vertices.size();
    }
    
    public abstract int edgeNum();

    public void addVertex(Node<K, V> node) {
        vertices.add(node);
        adjacencyList.put(node, new LinkedList<>());
    }

    public void removeVertex(Node<K, V> node) {
        vertices.remove(node);
        adjacencyList.remove(node);
        for(List<Node<K, V>> l : adjacencyList.values()) {
            l.remove(node);
        }
    }

    public Set<Node<K, V>> getVertices() {
        return vertices;
    }

    public Map<Node<K, V>, List<Node<K, V>>> getAdjacencyList() {
        return adjacencyList;
    }

    public List<Node<K, V>> getNeighbors(Node<K,V> node) {
        return adjacencyList.get(node);
    }


    public List<Node<K, V>> dfs(Node<K, V> node) {
        Set<Node<K, V>> visited = new HashSet<>();
        // dfs result
        List<Node<K, V>> res = new ArrayList<>();
        dfsHelper(res, visited, node);
        // make sure all disconnected node has also been visited
        for(Node<K, V> n : vertices) {
            if(!visited.contains(n))
                dfsHelper(res, visited, n);
        }
        return res;
    }

    private void dfsHelper(List<Node<K, V>> res, Set<Node<K, V>> visited, Node<K, V> n) {
        if(visited.contains(n))
            return;
        visited.add(n);
        res.add(n);
        for(Node<K,V> neighbor: adjacencyList.get(n))
            dfsHelper(res, visited, neighbor);
    }

    public List<Node<K, V>> bfs(Node<K, V> node) {
        Set<Node<K, V>> visited = new HashSet<>();
        // bfs result
        List<Node<K, V>> res = new ArrayList<>();
        bfsHelper(res, visited, node);
        // make sure all disconnected node has also been visited
        for(Node<K, V> n : vertices) {
            if(!visited.contains(n))
                bfsHelper(res, visited, n);
        }
        return res;
    }

    private void bfsHelper(List<Node<K, V>> res, Set<Node<K, V>> visited,  Node<K, V> node) {
        Queue<Node<K, V>> q = new LinkedList<>();
        q.offer(node);
        visited.add(node);
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                Node<K, V> n = q.poll();
                res.add(n);
                for(Node<K,V> neighbor: adjacencyList.get(n)) {
                    if(visited.contains(neighbor)) continue;
                    q.offer(neighbor);
                    visited.add(neighbor);
                }
            }
        }
    }

    public abstract  boolean containsCycle();

    public abstract void addEdge(Node<K, V> from, Node<K, V> to);

    public abstract void removeEdge(Node<K, V> from, Node<K, V> to);
}

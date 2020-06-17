package com.tianju.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * Tianju Zhou
 * Jun 16, 2020
 */
public class AdjacencyMatrixUndirectedGraph extends AdjacencyMatrixGraph {

    public AdjacencyMatrixUndirectedGraph() {
        super();
    }

    public AdjacencyMatrixUndirectedGraph(int V) {
        super(V);
    }

    public void addEdge(int from, int to, int weight) {
        E++;
        indegrees[from]++;
        indegrees[to]++;
        adjacencyMatrix[from][to] = weight;
        adjacencyMatrix[to][from] = weight;
    }

    public void removeEdge(int from, int to) {
        E--;
        indegrees[from]--;
        indegrees[to]--;
        adjacencyMatrix[from][to] = null;
        adjacencyMatrix[to][from] = null;
    }

    public boolean containsCycle() {
        Set<Integer> visited = new HashSet<>();
        for(int i = 0; i < V; i++) {
            if(visited.contains(i))
                continue;
            if(cycleDetect(visited, i, null))
                return true;
        }
        return false;
    }

    private boolean cycleDetect(Set<Integer> visited, int v, Integer parent) {
        if(visited.contains(v))
            return true;
        visited.add(v);
        for(int neighbor = 0; neighbor < V; neighbor++) {
            // if neighbor equals to current vertex, then the vertex has a self cycle
            if(neighbor == v && adjacencyMatrix[v][neighbor] != null)
                return true;
            if(adjacencyMatrix[v][neighbor] == null || (parent != null && parent == neighbor))
                continue;
            if(cycleDetect(visited, neighbor, v))
                return true;
        }
        return false;
    }
}

package com.tianju.graph;

/**
 * Tianju Zhou
 * Jun 16, 2020
 * There are several restriction compared to Adjacency List.
 *  1. Vertices should be ordered. Eg: 0, 1 ... 10
 *  2. Requires more additional space O(n^2) while Adjacency List requires O(n+e)
 *  3. Once initialized, it's hard to add or remove vertex
 */
public abstract class AdjacencyMatrixGraph {
    protected int V, E;
    // elements in adjacency matrix can be null, so use Integer[][] instead of int[][]
    protected Integer[][] adjacencyMatrix;
    protected int[] indegrees;

    public AdjacencyMatrixGraph() {
        this.V = 10;
        this.adjacencyMatrix = new Integer[V][V];
        this.indegrees = new int[V];
    }

    public AdjacencyMatrixGraph(int V) {
        this.V = V;
        this.adjacencyMatrix = new Integer[V][V];
        this.indegrees = new int[V];
    }

    public int vertexNum() {
        return V;
    }

    public int edgeNum() {
        return E;
    }

    public abstract  boolean containsCycle();

    public abstract void addEdge(int from, int to, int weight);

    public abstract void removeEdge(int from, int to);
}

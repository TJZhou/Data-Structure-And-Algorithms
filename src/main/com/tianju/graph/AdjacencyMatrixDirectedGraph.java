package com.tianju.graph;

import java.util.*;

/**
 * Tianju Zhou
 * Jun 16, 2020
 */
public class AdjacencyMatrixDirectedGraph extends AdjacencyMatrixGraph {

    public AdjacencyMatrixDirectedGraph() {
        super();
    }

    public AdjacencyMatrixDirectedGraph(int V) {
        super(V);
    }

    public void addEdge(int from, int to, int weight) {
        indegrees[to]++;
        E++;
        adjacencyMatrix[from][to] = weight;
    }

    public void removeEdge(int from, int to) {
        indegrees[to]--;
        E--;
        adjacencyMatrix[from][to] = null;
    }

    public boolean containsCycle() {
        Set<Integer> notVisited = new HashSet<>();
        Set<Integer> beingVisited = new HashSet<>();
        Set<Integer> totallyVisited = new HashSet<>();
        for(int i = 0 ; i < V; i++)
            notVisited.add(i);
        while(notVisited.size() > 0) {
            int vertex = notVisited.iterator().next();
            if(detectCycle(notVisited, beingVisited, totallyVisited, vertex))
                return true;
        }
        return false;
    }

    private boolean detectCycle(Set<Integer> notVisited, Set<Integer> beingVisited, Set<Integer> totallyVisited, int v) {
        if(beingVisited.contains(v))
            return true;
        notVisited.remove(v);
        beingVisited.add(v);
        for(int i = 0; i < V; i++) {
            Integer neighbor = adjacencyMatrix[v][i];
            if(neighbor == null || totallyVisited.contains(i))
                continue;
            if(detectCycle(notVisited, beingVisited, totallyVisited, i))
                return true;
        }
        beingVisited.remove(v);
        totallyVisited.add(v);
        return false;
    }

    public List<Integer> topologicalSort() {
        if(containsCycle())
            throw new IllegalArgumentException("Cannot sort graph with cycle");
        Queue<Integer> q = new LinkedList<>();
        Map<Integer, Integer> indegreesMap = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < V; i++) {
            if(indegrees[i] == 0) {
                q.offer(i);
            } else {
                indegreesMap.put(i, indegrees[i]);
            }
        }
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                int vertex = q.poll();
                res.add(vertex);
                for(int j = 0; j < V; j++) {
                    if(adjacencyMatrix[vertex][j] == null) continue;
                    indegreesMap.put(j, indegreesMap.get(j) - 1);
                    if(indegreesMap.get(j) == 0) {
                        indegreesMap.remove(j);
                        q.offer(j);
                    }
                }
            }
        }
        return res;
    }
}

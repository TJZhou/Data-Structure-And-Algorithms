package com.tianju.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Tianju Zhou
 * Jun 14, 2020
 */
public class AdjacencyListDirectedGraphTest {

    Vertex<Integer, Integer> v1 = new Vertex<>(1, 1);
    Vertex<Integer, Integer> v2 = new Vertex<>(2, 2);
    Vertex<Integer, Integer> v3 = new Vertex<>(3, 3);
    Vertex<Integer, Integer> v4 = new Vertex<>(4, 4);
    Vertex<Integer, Integer> v5 = new Vertex<>(5, 5);
    Vertex<Integer, Integer> v6 = new Vertex<>(6, 6);

    Edge<Integer, Integer> e1 = new Edge<>(v1, v2, 1);
    Edge<Integer, Integer> e2 = new Edge<>(v1, v3, 2);
    Edge<Integer, Integer> e3 = new Edge<>(v1, v4, 3);
    Edge<Integer, Integer> e4 = new Edge<>(v2, v3, 4);
    Edge<Integer, Integer> e5 = new Edge<>(v2, v5, 5);
    Edge<Integer, Integer> e6 = new Edge<>(v2, v6, 6);
    Edge<Integer, Integer> e7 = new Edge<>(v3, v6, 7);

    @Test
    public void adjacencyListDirectedGraphTest() {
        AdjacencyListGraph<Integer, Integer> g = new AdjacencyListDirectedGraph<>();
        g.addVertices(Arrays.asList(v1, v2, v3, v4, v5, v6));
        g.addEdges(Arrays.asList(e1, e2, e3, e4, e5, e6, e7));

        Assertions.assertEquals(g.vertexNum(), 6);
        Assertions.assertEquals(g.edgeNum(), 7);
        Assertions.assertEquals(v1.getIndegree(), 0);
        Assertions.assertEquals(v2.getIndegree(), 1);
        Assertions.assertEquals(v1.getNeighbors().get(0).to.val, 2);
        Assertions.assertEquals(v2.getNeighbors().get(0).to.val, 3);
        Assertions.assertFalse(g.containsCycle());

        g.removeVertex(v3);
        g.removeEdge(v1, v4);
        Assertions.assertEquals(g.vertexNum(), 5);
        Assertions.assertEquals(g.edgeNum(), 3);
        Assertions.assertEquals(v6.getIndegree(), 1);
    }

    @Test
    public void topologicalSortTest() {
        AdjacencyListDirectedGraph<Integer, Integer> g = new AdjacencyListDirectedGraph<>();
        Edge<Integer, Integer> ee1 = new Edge<>(v6, v5, 1);
        Edge<Integer, Integer> ee2 = new Edge<>(v5, v4, 2);
        Edge<Integer, Integer> ee3 = new Edge<>(v4, v3, 3);
        Edge<Integer, Integer> ee4 = new Edge<>(v3, v2, 4);
        Edge<Integer, Integer> ee5 = new Edge<>(v2, v1, 5);
        Edge<Integer, Integer> ee6 = new Edge<>(v1, v6, 6);
        g.addVertices(Arrays.asList(v1, v2, v3, v4, v5, v6));
        g.addEdges(Arrays.asList(ee1, ee2, ee3, ee4, ee5));
        Assertions.assertIterableEquals(g.topologicalSort(), Arrays.asList(v6, v5, v4, v3, v2, v1));
        g.addEdge(ee6);
        Assertions.assertThrows(IllegalArgumentException.class, g::topologicalSort);
    }

    @Test
    public void dijkstraTest() {
        AdjacencyListDirectedGraph<Integer, Integer> g = new AdjacencyListDirectedGraph<>();
        g.addVertices(Arrays.asList(v1, v2, v3, v4, v5, v6));
        g.addEdges(Arrays.asList(e1, e2, e3, e4, e5, e6, e7));

        Assertions.assertEquals(g.shortestDistance1(v2, v1), Double.MAX_VALUE);
        Assertions.assertEquals(g.shortestDistance1(v2, v6), 6);
        Assertions.assertEquals(g.shortestDistance1(v1, v6), 7);
    }

    @Test
    public void bellmanFordTest() {
        AdjacencyListDirectedGraph<Integer, Integer> g = new AdjacencyListDirectedGraph<>();
        g.addVertices(Arrays.asList(v1, v2, v3, v4, v5, v6));
        g.addEdges(Arrays.asList(e1, e2, e3, e4, e5, e6, e7));

        Assertions.assertEquals(g.shortestDistance2(v2, v1), Double.MAX_VALUE);
        Assertions.assertEquals(g.shortestDistance2(v2, v6), 6);
        Assertions.assertEquals(g.shortestDistance2(v1, v6), 7);

        g.addEdge(new Edge<>(v3, v1, -10));
        Assertions.assertThrows(IllegalArgumentException.class, () -> g.shortestDistance2(v1, v3));
    }
}

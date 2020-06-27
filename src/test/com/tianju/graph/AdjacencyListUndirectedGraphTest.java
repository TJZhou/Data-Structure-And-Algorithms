package com.tianju.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

/**
 * Tianju Zhou
 * Jun 14, 2020
 */
public class AdjacencyListUndirectedGraphTest {

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
    public void adjacencyListUndirectedGraphTest() {
        AdjacencyListGraph<Integer, Integer> g = new AdjacencyListUndirectedGraph<>();


        g.addVertices(Arrays.asList(v1, v2, v3, v4, v5, v6));
        g.addEdges(Arrays.asList(e1, e2, e3, e4, e5, e6, e7));

        Assertions.assertEquals(g.vertexNum(), 6);
        Assertions.assertEquals(g.edgeNum(), 7);
        Assertions.assertEquals(v1.getIndegree(), 3);
        Assertions.assertEquals(v2.getIndegree(), 4);
        Assertions.assertEquals(v1.getNeighbors().get(0).to.val, 2);
        Assertions.assertEquals(v2.getNeighbors().get(0).to.val, 1);
        Assertions.assertTrue(g.containsCycle());

        // as the edges are undirected, once we add edge n1 - n4, we automatically create edge n4 - n1
        g.removeEdge(v4, v1);
        g.removeVertex(v3);
        Assertions.assertFalse(g.containsCycle());
        Assertions.assertEquals(g.vertexNum(), 5);
        Assertions.assertEquals(g.edgeNum(), 3);
        Assertions.assertEquals(v6.getIndegree(), 1);
    }

    @Test
    public void primTest() {
        AdjacencyListUndirectedGraph<Integer, Integer> g = new AdjacencyListUndirectedGraph<>();
        g.addVertices(Arrays.asList(v1, v2, v3, v4, v5, v6));
        g.addEdges(Arrays.asList(e1, e2, e3, e4, e5, e6, e7));
        Map<Vertex<Integer, Integer>, Vertex<Integer, Integer>> prev = g.minimumSpanningTree(v1);
        Assertions.assertNull(prev.get(v1));
        Assertions.assertEquals(prev.get(v5), v2);
        Assertions.assertEquals(prev.get(v2), v1);
    }
}

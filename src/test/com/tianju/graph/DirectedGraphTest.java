package com.tianju.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tianju Zhou
 * Jun 14, 2020
 */
public class DirectedGraphTest {

    @Test
    public void directedGraphTest() {
        Graph<Integer, Integer, Integer> g = new DirectedGraph<>();

        Vertex<Integer, Integer> v1 = new Vertex<>(1, 1);
        Vertex<Integer, Integer> v2 = new Vertex<>(2, 2);
        Vertex<Integer, Integer> v3 = new Vertex<>(3, 3);
        Vertex<Integer, Integer> v4 = new Vertex<>(4, 4);
        Vertex<Integer, Integer> v5 = new Vertex<>(5, 5);
        Vertex<Integer, Integer> v6 = new Vertex<>(6, 6);

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(v6);

        g.addEdge(v1, v2, 1);
        g.addEdge(v1, v3, 2);
        g.addEdge(v1, v4, 3);
        g.addEdge(v2, v3, 4);
        g.addEdge(v2, v5, 5);
        g.addEdge(v2, v6, 6);
        g.addEdge(v3, v6, 7);

        Assertions.assertEquals(g.vertexNum(), 6);
        Assertions.assertEquals(g.edgeNum(), 7);
        Assertions.assertEquals(g.getNeighbors(v1).get(0).to.val, 2);
        Assertions.assertEquals(g.getNeighbors(v2).get(0).to.val, 3);
        // coz we store nods in a set, so the visited order is not guaranteed
        // Assertions.assertArrayEquals(g.bfs(v2).toArray(), new Vertex[]{n2, v3, v5, v6, v4, v1});
        Assertions.assertFalse(g.containsCycle());

        g.removeVertex(v3);
        Assertions.assertFalse(g.containsCycle());

        g.removeEdge(v1, v4);
        Assertions.assertEquals(g.vertexNum(), 5);
        Assertions.assertEquals(g.edgeNum(), 3);
        // Although edge from n1 to n4 is removed. n4 is still in the graph. It's isolated
        // Assertions.assertArrayEquals(g.bfs(v2).toArray(), new Vertex[]{n2, v5, v6, v4, v1});
    }
}

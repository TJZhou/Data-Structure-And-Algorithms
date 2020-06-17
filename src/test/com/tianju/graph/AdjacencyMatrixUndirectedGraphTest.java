package com.tianju.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tianju Zhou
 * Jun 16, 2020
 */
public class AdjacencyMatrixUndirectedGraphTest {

    @Test
    public void adjacencyMatrixUndirectedGraphTest() {
        AdjacencyMatrixGraph g = new AdjacencyMatrixUndirectedGraph(7);
        g.addEdge(1, 2, 1);
        g.addEdge(1, 3, 2);
        g.addEdge(1, 4, 3);
        g.addEdge(2, 3, 4);
        g.addEdge(2, 5, 5);
        g.addEdge(2, 6, 6);
        g.addEdge(3, 6, 7);

        Assertions.assertEquals(g.vertexNum(), 7);
        Assertions.assertEquals(g.edgeNum(), 7);

        Assertions.assertTrue(g.containsCycle());
        g.removeEdge(3, 1);
        g.removeEdge(3, 6);
        Assertions.assertEquals(g.edgeNum(), 5);
        Assertions.assertFalse(g.containsCycle());

        g.addEdge(0,0, 0);
        Assertions.assertTrue(g.containsCycle());
    }
}

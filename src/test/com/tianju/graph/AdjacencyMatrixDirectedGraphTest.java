package com.tianju.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tianju Zhou
 * Jun 16, 2020
 */
public class AdjacencyMatrixDirectedGraphTest {

    @Test
    public void adjacencyMatrixDirectedGraphTest() {
        AdjacencyMatrixGraph g = new AdjacencyMatrixDirectedGraph(7);
        g.addEdge(1, 2, 1);
        g.addEdge(1, 3, 2);
        g.addEdge(1, 4, 3);
        g.addEdge(2, 3, 4);
        g.addEdge(2, 5, 5);
        g.addEdge(2, 6, 6);
        g.addEdge(3, 6, 7);

        Assertions.assertEquals(g.vertexNum(), 7);
        Assertions.assertEquals(g.edgeNum(), 7);

        Assertions.assertFalse(g.containsCycle());
        g.addEdge(3, 1, 0);
        Assertions.assertTrue(g.containsCycle());

        g.removeEdge(3, 1);
        g.removeEdge(1, 4);
        Assertions.assertEquals(g.edgeNum(), 6);

        g.addEdge(0,0, 0);
        Assertions.assertTrue(g.containsCycle());
    }

    @Test
    public void topologicalSortTest() {
        AdjacencyMatrixDirectedGraph g = new AdjacencyMatrixDirectedGraph(6);
        g.addEdge(1,0,0);
        g.addEdge(2,1,0);
        g.addEdge(3,2,0);
        g.addEdge(4,3,0);
        g.addEdge(5,4,0);
        Assertions.assertArrayEquals(g.topologicalSort().toArray(), new Integer[] {5,4,3,2,1,0});

        g.addEdge(0, 5, 0);
        Assertions.assertThrows(IllegalArgumentException.class, g::topologicalSort);
    }
}

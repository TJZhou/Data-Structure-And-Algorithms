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
        Graph<Integer, Integer> g = new DirectedGraph<>();

        Node<Integer, Integer> n1 = new Node<>(1, 1);
        Node<Integer, Integer> n2 = new Node<>(2, 2);
        Node<Integer, Integer> n3 = new Node<>(3, 3);
        Node<Integer, Integer> n4 = new Node<>(4, 4);
        Node<Integer, Integer> n5 = new Node<>(5, 5);
        Node<Integer, Integer> n6 = new Node<>(6, 6);

        g.addVertex(n1);
        g.addVertex(n2);
        g.addVertex(n3);
        g.addVertex(n4);
        g.addVertex(n5);
        g.addVertex(n6);

        g.addEdge(n1, n2);
        g.addEdge(n1, n3);
        g.addEdge(n1, n4);
        g.addEdge(n2, n3);
        g.addEdge(n2, n5);
        g.addEdge(n2, n6);
        g.addEdge(n3, n6);

        Assertions.assertEquals(g.vertexNum(), 6);
        Assertions.assertEquals(g.edgeNum(), 7);
        Assertions.assertEquals(g.getNeighbors(n1).get(0).val, 2);
        Assertions.assertEquals(g.getNeighbors(n2).get(0).val, 3);
        // Assertions.assertArrayEquals(g.bfs(n2).toArray(), new Node[]{n2, n3, n5, n6, n4, n1});
        Assertions.assertFalse(g.containsCycle());

        g.removeVertex(n3);
        System.out.println(g.containsCycle());
        Assertions.assertFalse(g.containsCycle());

        g.removeEdge(n1, n4);
        Assertions.assertEquals(g.vertexNum(), 5);
        Assertions.assertEquals(g.edgeNum(), 3);
        // Although edge from n1 to n4 is removed. n4 is still in the graph. It's isolated
        // Assertions.assertArrayEquals(g.bfs(n2).toArray(), new Node[]{n2, n5, n6, n4, n1});
    }
}

package com.tianju.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tianju Zhou
 * Jun 14, 2020
 */
public class UnDirectedGraphTest {

    @Test
    public void directedGraphTest() {
        Graph<Integer, Integer> g = new UnDirectedGraph<>();

        Node<Integer, Integer> n1 = new Node<>(1,1);
        Node<Integer, Integer> n2 = new Node<>(2,2);
        Node<Integer, Integer> n3 = new Node<>(3,3);
        Node<Integer, Integer> n4 = new Node<>(4,4);
        Node<Integer, Integer> n5 = new Node<>(5,5);
        Node<Integer, Integer> n6 = new Node<>(6,6);

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
        Assertions.assertEquals(g.getNeighbors(n2).get(0).val, 1);
        g.bfs(n1);

        // as the edges are undirected, once we add edge n1 - n4, we automatically create edge n4 - n1
        g.removeEdge(n4, n1);
        g.removeVertex(n3);
        Assertions.assertEquals(g.vertexNum(), 5);
        Assertions.assertEquals(g.edgeNum(), 3);

        System.out.println("\nAfter remove. Source n4:");
        g.bfs(n4);

        System.out.println("\nAfter remove. Source n1:");
        g.bfs(n1);
    }
}

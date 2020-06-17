package com.tianju.graph;

/**
 * Tianju Zhou
 * Jun 15, 2020
 * @param <K> Vertex Key
 * @param <V> Vertex Value
 */
public class Edge<K, V> {

    Vertex<K, V> from, to;
    double w;

    public Edge(Vertex<K, V> from, Vertex<K, V> to, double w) {
        this.from = from;
        this.to = to;
        this.w = w;
    }
}

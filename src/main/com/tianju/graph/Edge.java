package com.tianju.graph;

/**
 * Tianju Zhou
 * Jun 15, 2020
 * @param <K> Vertex Key
 * @param <V> Vertex Value
 * @param <W> Edge Weight
 */
public class Edge<K, V, W> {
    Vertex<K, V> from, to;
    W w;
    public Edge(Vertex<K, V> from, Vertex<K, V> to, W w) {
        this.from = from;
        this.to = to;
        this.w = w;
    }
}

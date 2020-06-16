package com.tianju.graph;

/**
 * Tianju Zhou
 * Jun 14, 2020
 * @param <K> Key
 * @param <V> Value
 */
class Vertex<K,V> {
    K key;
    V val;
    Vertex(K key, V val) {
        this.key = key;
        this.val = val;
    }
}

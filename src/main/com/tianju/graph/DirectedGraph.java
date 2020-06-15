package com.tianju.graph;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Tianju Zhou
 * Jun 14, 2020
 * @param <K> Key
 * @param <V> Value
 */
public class DirectedGraph<K, V> extends Graph<K, V> {

    public DirectedGraph() {
        super();
    }

    public int edgeNum() {
        int sum = 0;
        for(List<Node<K,V>> l : adjacencyList.values())
            sum += l.size();
        return sum;
    }

    public void addEdge(Node<K, V> from, Node<K, V> to) {
        if(!vertices.contains(from))
            addVertex(from);
        if(!vertices.contains(to))
            addVertex(to);
        adjacencyList.get(from).add(to);
    }

    public void removeEdge(Node<K, V> from, Node<K, V> to) {
        adjacencyList.get(from).remove(to);
    }

    public boolean containsCycle() {
        Set<Node<K, V>> notVisited = new HashSet<>(vertices);
        Set<Node<K, V>> beingVisited = new HashSet<>();
        Set<Node<K, V>> totallyVisited = new HashSet<>();
        while(notVisited.size() > 0) {
            Node<K, V> node = notVisited.iterator().next();
            if(cycleDetect(notVisited, beingVisited, totallyVisited, node))
                return true;
        }
        return false;
    }

    /**
     * Refer to Tushar Roy: https://www.youtube.com/watch?v=rKQaZuoUR4M
     * @param notVisited: nodes which have not been visited yet
     * @param beingVisited: nodes currently are being visited
     * @param totallyVisited: nodes which has already been visited
     * @return true if there are cycles detected(node n in the beingVisited set)
     */
    private boolean cycleDetect(Set<Node<K, V>> notVisited, Set<Node<K, V>> beingVisited, Set<Node<K, V>> totallyVisited, Node<K, V> n) {
        notVisited.remove(n);
        beingVisited.add(n);
        for(Node<K, V> neighbor : adjacencyList.get(n)) {
            if(totallyVisited.contains(n))
                continue;
            if(beingVisited.contains(neighbor))
                return true;
            if(cycleDetect(notVisited, beingVisited, totallyVisited, neighbor))
                return true;
        }
        beingVisited.remove(n);
        totallyVisited.add(n);
        return false;
    }
}

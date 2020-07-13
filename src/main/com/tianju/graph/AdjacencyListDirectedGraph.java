package com.tianju.graph;

import java.util.*;

/**
 * Tianju Zhou
 * Jun 14, 2020
 * @param <K> Key
 * @param <V> Value
 */
public class AdjacencyListDirectedGraph<K, V> extends AdjacencyListGraph<K, V> {

    public AdjacencyListDirectedGraph() {
        super();
    }

    public int edgeNum() {
        int sum = 0;
        for(Vertex<K, V> v : vertices)
            sum += v.adjacencyList.size();
        return sum;
    }

    public void addEdge(Vertex<K, V> from, Vertex<K, V> to, double w) {
        to.indegree++;
        if(!vertices.contains(from))
            addVertex(from);
        if(!vertices.contains(to))
            addVertex(to);
        from.adjacencyList.add(new Edge<>(from, to, w));
    }

    public void removeEdge(Vertex<K, V> from, Vertex<K, V> to) {
        to.indegree--;
        from.adjacencyList.removeIf(e -> e.to == to);
    }

    public boolean containsCycle() {
        Set<Vertex<K, V>> notVisited = new HashSet<>(vertices);
        Set<Vertex<K, V>> beingVisited = new HashSet<>();
        Set<Vertex<K, V>> totallyVisited = new HashSet<>();
        while(notVisited.size() > 0) {
            Vertex<K, V> vertex = notVisited.iterator().next();
            if(cycleDetect(notVisited, beingVisited, totallyVisited, vertex))
                return true;
        }
        return false;
    }

    /**
     * Refer to Tushar Roy: https://www.youtube.com/watch?v=rKQaZuoUR4M
     * @param notVisited: vertices which have not been visited yet
     * @param beingVisited: vertices currently are being visited
     * @param totallyVisited: vertices which has already been visited
     * @return true if there are cycles detected(vertices n in the beingVisited set)
     */
    private boolean cycleDetect(Set<Vertex<K, V>> notVisited, Set<Vertex<K, V>> beingVisited, Set<Vertex<K, V>> totallyVisited, Vertex<K, V> vertex) {
        if(beingVisited.contains(vertex))
            return true;
        notVisited.remove(vertex);
        beingVisited.add(vertex);
        for(Edge<K, V> neighbor : vertex.adjacencyList) {
            if(totallyVisited.contains(neighbor.to))
                continue;
            if(cycleDetect(notVisited, beingVisited, totallyVisited, neighbor.to))
                return true;
        }
        beingVisited.remove(vertex);
        totallyVisited.add(vertex);
        return false;
    }

    // topological sort only applies for DAG (Directed Acyclic Graph)
    public Collection<Vertex<K, V>> topologicalSort() {
        // we can also directly use topologicalSort to detect cycle.
        // if the queue loop is less than vertices times, than there must be a cycle
        // see post on leetCode https://leetcode.com/problems/course-schedule/discuss/58516/Easy-BFS-Topological-sort-Java
        if(containsCycle())
            throw new IllegalArgumentException("Cannot sort graph with cycle");
        List<Vertex<K, V>> res = new ArrayList<>();
        Map<Vertex<K, V>, Integer> indegreeMap = new HashMap<>();
        Queue<Vertex<K, V>> q = new LinkedList<>();
        for(Vertex<K, V> v : vertices) {
            if(v.indegree == 0) {
                q.offer(v);
            } else {
                indegreeMap.put(v, v.indegree);
            }
        }
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                Vertex<K, V> vertex = q.poll();
                res.add(vertex);
                for(Edge<K, V> e : vertex.adjacencyList) {
                    Vertex<K, V> neighbor = e.to;
                    indegreeMap.put(neighbor, indegreeMap.get(neighbor) - 1);
                    if(indegreeMap.get(neighbor) == 0) {
                        indegreeMap.remove(neighbor);
                        q.offer(neighbor);
                    }
                }
            }
        }
        return res;
    }

    // dijkstra algorithm only applies for graph which doesn't have negative weighted edge
    public double shortestDistance1(Vertex<K, V> src, Vertex<K, V> dst) {
        Map<Vertex<K, V>, Double> dist = new HashMap<>();
        Map<Vertex<K, V>, Vertex<K, V>> prev = new HashMap<>();
        Set<Vertex<K, V>> visited = new HashSet<>();
        PriorityQueue<Map.Entry<Vertex<K, V>, Double>> pq = new PriorityQueue<>(Map.Entry.comparingByValue());
        for(Vertex<K, V> vertex : vertices)
            dist.put(vertex, Double.MAX_VALUE);
        dist.put(src, 0.0);
        pq.offer(new AbstractMap.SimpleEntry<>(src, 0.0));
        while(!pq.isEmpty()) {
            double minDist = pq.peek().getValue();
            Vertex<K, V> v = pq.poll().getKey();
            visited.add(v);
            for(Edge<K, V> edge : v.adjacencyList) {
                assert edge.from == v;
                if(visited.contains(edge.to)) continue;
                double temp = minDist + edge.w;
                if(temp < dist.get(edge.to)) {
                    dist.put(edge.to, temp);
                    prev.put(edge.to, edge.from);
                    pq.offer(new AbstractMap.SimpleEntry<>(edge.to, temp));
                }
            }
        }
        return dist.get(dst);
    }

    // Bellman ford algorithm. Will report graph which has negative cycle
    public double shortestDistance2(Vertex<K, V> src, Vertex<K, V> dst) {
        Map<Vertex<K, V>, Double> dist = new HashMap<>();
        Map<Vertex<K, V>, Vertex<K, V>> prev = new HashMap<>();
        for(Vertex<K, V> vertex : vertices)
            dist.put(vertex, Double.MAX_VALUE);
        dist.put(src, 0.0);
        for(int i = 0; i < vertices.size() - 1; i++)
            updateDist(dist, prev);
        // check one more time, if the path length is still decrease then there must be a negative cycle
        if(updateDist(dist, prev))
            throw new IllegalArgumentException("Graph contains negative cycle. Shortest path doesn't exist!");
        return dist.get(dst);
    }

    private boolean updateDist(Map<Vertex<K, V>, Double> dist, Map<Vertex<K, V>, Vertex<K, V>> prev) {
        boolean findShorterPath = false;
        for(Vertex<K, V> v : vertices) {
            for(Edge<K, V> e : v.adjacencyList) {
                // the edge must start from vertex v
                assert e.from == v;
                // avoid overflow
                if(dist.get(v) != Double.MAX_VALUE && dist.get(e.to) > dist.get(v) + e.w) {
                    dist.put(e.to, dist.get(v) + e.w);
                    prev.put(e.to, v);
                    findShorterPath = true;
                }
            }
        }
        return findShorterPath;
    }

    /**
     * Use Kosaraju's algorithm to find strongly connected component
     * @return  A list of strongly connected component sub-graphs
     */
    public List<List<Vertex<K, V>>> stronglyConnectedComponent() {
        List<List<Vertex<K, V>>> res = new ArrayList<>();
        Set<Vertex<K, V>> visited = new HashSet<>();
        Map<Vertex<K,V>, List<Edge<K, V>>> reversedAdjacencyList = new HashMap<>();
        Deque<Vertex<K, V>> stack = new LinkedList<>();

        // build reversed adjacency list
        for(Vertex<K, V> v : vertices) {
            for(Edge<K, V> neighbor : v.adjacencyList) {
                reversedAdjacencyList.putIfAbsent(neighbor.to, new LinkedList<>());
                reversedAdjacencyList.get(neighbor.to).add(new Edge<>(neighbor.to, neighbor.from, neighbor.w));
            }
        }

        // kosaraju's algorithm - first pass
        for(Vertex<K, V> v : vertices) {
            if(!visited.contains(v))
                kosarajuHelper(v, visited, stack);
        }

        // kosaraju's algorithm - second pass after edge reversed
        visited.clear();
        while(!stack.isEmpty()) {
            kosarajuHelperReversed(reversedAdjacencyList, stack.peek(), visited);
            List<Vertex<K, V>> connectedComponent = new ArrayList<>();
            while(!stack.isEmpty() && visited.contains(stack.peek()))
                connectedComponent.add(stack.pop());
            res.add(connectedComponent);
        }
        return res;
    }

    private void kosarajuHelper(
            Vertex<K, V> v, Set<Vertex<K, V>> visited, Deque<Vertex<K, V>> stack) {
        if(visited.contains(v))
            return;
        visited.add(v);
        for(Edge<K, V> neighbor : v.adjacencyList)
            kosarajuHelper(neighbor.to, visited, stack);
        stack.push(v);
    }

    private void kosarajuHelperReversed(Map<Vertex<K,V>, List<Edge<K, V>>> reversedAdjacencyLis, Vertex<K, V> v, Set<Vertex<K, V>> visited) {
        if(visited.contains(v))
            return;
        visited.add(v);
        for(Edge<K, V> neighbor: reversedAdjacencyLis.get(v))
            kosarajuHelperReversed(reversedAdjacencyLis, neighbor.to, visited);
    }
}

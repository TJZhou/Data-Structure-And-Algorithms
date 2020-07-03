# Graph

## Adjacency List

### Add Vertex: O(1)

### Add Edge: O(1)

### Remove Vertex: O(V+E)
> In each node of adjacency list (Takes O(V)), we need to remove corresponding edges of the removed vertex (Take O(E)). Overall time complexity is O(V+E)

### Remove Edge: O(E)
> Adjacency list use linkedlist to store edges. Find the edge takes O(E) and than remove takes constant time. Overall time complexity is O(E)

### BFS: O(V+E)

### DFS: O(V+E)
> Same as BFS, need to iterate through each node and edge

### Cycle Detect
1. Undirected Graph
- Using Union-Find (Only suitable for undirected graph): Without Rank or Path Compression - O(V). With Rank - O(logV)???
- Using DFS/BFS: O(V), if the number of edges processed is equal to the number of vertex, then there must be a cycle. 

2. Directed Graph
- Using DFS/BFS: O(V+E)

### Shortest Path
1. Topological Sort(Only Suitable for DAG - Directed Acyclic Graph, but can have negative weights): O(V+E) based on BFS
2. Dijkstra (Can not have negative weight): O(ElogV). Need to maintain a MinHeap which store vertices and the order is based on the distance from source vertex to current vertex. 
3. Bellman-Ford: O(VE). Iterate V-1 times to relax all edges and update minimum distance. And then iterate 1 more time to detect cycle (If minimum distance still shrinks thant there must be a cycle).

### Minimum Spanning Tree (Suitable for Undirected Graph):
1. Prim's Algorithm: O(VlogV + ElogV) = O(ElogV). While using Fibonacci Heap: O(E + V log(V)) ???
2. Kruskal Algorithm: Build Heap O(ElogE), Union-Find O(VlogV). Time complexity is O(ElogE) + O(VlogV). E <= V^2, logE <= 2logV thus overall time complexity is O(2ElogV + VlogV) = O(ElogV)

## Adjacency Matrix

### Add Vertex: O(V^2), we need to relocate array

### Add Edge: O(1)

### Remove Vertex: O(V^2)

### Remove Edge: O(1)

### BFS: O(V^2)

### DFS: O(V^2)

### Shortest Path:
1. Topological Sort: O(V^2) Based on BFS
2. Dijkstra: O(V^2)
3. Bellman-Ford: O(V^3)


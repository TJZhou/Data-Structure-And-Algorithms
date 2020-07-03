# Tree

## Binary Search Tree
### BFS
- Time Complexity: O(n)
- Space Complexity: Best O(1), each node only has one child. Worst O(n/2), perfectly balanced tree

### DFS
- Time Complexity: O(n)
- Space Complexity: Best O(logn), perfectly balanced tree. Worst O(n/2), each node only has one child

### Search, Insert, Delete, getMin, getMax
- Time Complexity: Average O(logn) or O(h) while h is the height of the BST. 
Worst: O(n) while tt forms a linkedlist like structure
- Space Complexity: O(1), no extra space needed

## Balanced Tree VS. Heap
| Balanced Tree  |      Heap     |
| -------------- | ------------- |
| Create O(nlogn)| Create O(n)   |
| getMin O(logn) | getMin O(1)   |
| getMax O(logn) | getMax O(1)   |
| Insert O(logn) | Insert O(logn) - Amortized O(1)  |
| Delete O(logn) | Delete O(logn)|
| Find O(logn)   | Find O(n)     |
| predecessor O(logn)||
| successor O(logn)||

> If the goal is to get the minimum element or maximum element then use Heap. If the goal is to find the element then use Balanced Search Tree.

## AVL Tree VS. Red Black Tree
> The AVL tree is more balanced compared to Red-Black Trees, but it may cause more rotations during insertion and deletion. So if your application involves  frequent insertions and deletions, then Red Black trees should be preferred. And if the insertions and deletions are less frequent and search is a more frequent operation, then AVL tree should be preferred over Red-Black Tree.

# Data Structure

## ArrayList

Time Complexity:
- Access: O(1)
- Search: O(n)
- Insert: O(n)
- Delete: O(n)

## LinkedList

Time Complexity:
- Access: O(n)
- Search: O(n)
- Insert: O(1)
- Delete: O(1)

Note: Insert and Delete at certain position - We need to find the position first which takes O(n) and then do the insertion/deletion which takes O(1)

## Heap (PriorityQueue)
Time Complexity:
- Create Heap: O(n)
- getMin/getMax: O(1)
- Insert: O(logn)
- Delete: O(logn)
- Search: O(n)

## HashMap
Time Complexity:
- get: O(1)
- delete: O(1)
- add: O(1)

0. HashMap Data Structure:
    Array + LinkedList + Red Black Tree  
    When the length of LinkedList is greater than 8 it converts to Red Black Tree

1. Hash Function in the HashMap  
    `return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);`  
    XOR the first 16 bits and last 16 bits. Reduce the probability of collision.

2. Process of Add Element put(key, val)  
    2.0 Get the hash of key `(h = key.hashCode()) ^ (h >>> 16)`. Calculate the index using hash `(arr.length - 1) & hash`  
    2.1 Judge whether arr[index] contains element. If current arr[index] is empty than create a Node object and put it into arr[index]  
    2.2 If arr[index] is not empty - Collision occurs. Iterate through the LinkedList/RBT in the arr[index] if find an element in the LinkedList/RBT with same key than update the value.  
    2.3 If no element which has the same key found, than add the current element in the LinkedList/RBT. If current structure is red black tree (RBT), create a TreeNode and insert into the RBT.  
    2.4 If current structure is LinkedList, create a simple Node and add into the LinkedList. If the length of LinkedList is greater than 8 and size of arr is greater than 64, than transfer LinkedList to RBT.  
    2.5 After insertion, judge the size of HashMap, if size is greater than `capacity * loadFactor` than double the size of arr and rehash all elements in the HashMap (Cause we use `(arr.length - 1) & hash` to locate the index, the result is related to the size of arr. So we need to recalculate the hash of all elements).

3. Process of Get Element get(key)  
    3.0 Get the hash of key `(h = key.hashCode()) ^ (h >>> 16)`. Calculate the index using hash `(arr.length - 1) & hash`  
    3.1 Iterate through the LinkedList/RBT in the current arr[index], use `equals` function to judge whether key is equal. If element with same key found return the value otherwise return null

4. JDK 1.7 VS. JDK 1.8  
    4.0 Before JDK 1.8, there is no RBT data structure. And insert Node at the head of the LinkedList.
    4.1 After JDK 1.8, the Node is inserted at the end of the LikedList to make sure no cycle will be formed in multi-thread insertion.

5. Original Size of HashMap  
    Default Size 16, load factor 0.75 (Reduce collision). The size is 2 to the power of n in order to improve the efficiency. `hash % arr.length` is equals to `(arr.length - 1) & hash` when the size is 2 to the power of n.

6. HashMap is NOT Thread Safe  
    Use HashTable, SynchronizedMap or ConcurrentHashMap to keep thread safe.

7. Get/Put/Remove Time Complexity Analysis  
    7.0 Ideally: Get, Put, Delete - O(1) All keys distributed evenly in the array.  
    7.1 Worst case: O(logn). When all keys have same hashCode and form a LinkedList with size n O(n). This will further become O(logn) after converting to RBT.
    7.2 Amortized: O(1)
    
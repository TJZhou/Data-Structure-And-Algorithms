# Sort

## Insertion Sort
- Time Complexity: O(n^2)
- Space Complexity: O(1)

## Bubble Sort
- Time Complexity: O(n^2)
- Space Complexity: O(1)

## Selection Sort
- Time Complexity: O(n^2)
- Space Complexity: O(1)

## Count Sort (Only Suitable for Sorting Integer)
- Time Complexity: O(n+k)
- Space Complexity: O(k)
> Assume k is the largest element in the array. Then the time complexity is O(n+k). If k is less than or equals to the number of elements n, then the time complexity becomes O(n). But assume k is n^2, than time complexity is O(K) = O(n^2)


## Radix Sort
- Time Complexity: O(logr(k) * (n+r))
- Space Complexity: O(n+r)
> Enhancement of Count Sort. To handle large K problem. Time complexity: O(d * (n + r)):  If K is the maximum possible value, then d would be O(logr(k)). r is the radix, if the array is decimal based then normally r is 10. So the time complexity equals O(logr(k) * (n+r)) now. Given restriction r is large enough, assume r = n. Then this expression becomes O(logn(k) * 2n). Given restriction 1 < k < n^c. Based on the prerequisite given the final time complexity is O(logn(n^c) * 2n) = O(n)

## Quick Sort
- Time Complexity: O(nlogn) ~ O(n^2)
- Space Complexity: O(nlogn) - recursive stack

## Merge Sort
- Time Complexity: O(nlogn)
- Space Complexity: O(n) - need auxiliary array
> Assume we have overall N elements. First we divide array into two halves, and each half has N/2 elements. We do this process recursively until there are N/2 pairs in each pair there are only 2 elements. This forms a stack which has the depth of logN. Then we start to do the merge, at the bottom level we do N/2 times merge process for each 2 elements. This costs N. At the second last level we do N/4 times merge process for each pair which contains 4 elements with two in left half and 2 in right half. This also costs N. At the third last level, we do N/8 times merge process for each pair with 4 in left half pair and 4 in right half pair. Cost N….. We need to iterate this process logN times. So the total time complexity is O(NlogN)

## Heap Sort
- Time Complexity: O(nlogn)
- Space Complexity: O(1) - in place
```
1. Build Heap 
Assume we have an array nums of n elements
Build a Heap: 
    for(int i = n/2; i>=0; i—) {
        heapify(nums[i])
    }
Time complexity:
(0 * n/2) + (1 * n/4) + (2 * n/8) + ... + (height * 1)
which means we don’t need to do operations for leaf nodes. For nods at the last level (just above the leaf nodes), we need only to compare it with the leaf nodes which only take constant 1 time operation, and there are overall n/4 nods at this level. For nodes at the last second level, the operation time becomes 2 and there are overall n/8 nodes at this level.
This expression equals 
n/4 * (1/2^0 + 2/2^1 + 3/2^2 + … + height/(n/4))
= n/4 * (number bounded to constant) 
= O(n)
```
```
2. Sort
  while(nums.length > 0) {
        get(max)
        swap(max, node at nums.length - 1)
        discard nodes at nums.length-1
        heapify();
    }
Time complexity:
h*n/2 + (h-1)*n/4 + ... + 0 * 1 = O(nlogn)

Overall time complexity: O(n + nlogn) = O(nlogn)
```
package com.tianju.sort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Collections;

public class HeapSortTest {
    @Test
    public void heapSortTest() {
        HeapSort<Integer> minHeapSort = new HeapSort<>();
        Integer[] testArr = new Integer[] {12,3,5,11,8,9,0,2,22,4,6,6,6};
        minHeapSort.sort(testArr);
        Assertions.assertArrayEquals(testArr, new Integer[] {0,2,3,4,5,6,6,6,8,9,11,12,22});

        HeapSort<Integer> maxHeapSort = new HeapSort<>(Collections.reverseOrder());
        maxHeapSort.sort(testArr);
        Assertions.assertArrayEquals(testArr, new Integer[] {22,12,11,9,8,6,6,6,5,4,3,2,0});
    }
}

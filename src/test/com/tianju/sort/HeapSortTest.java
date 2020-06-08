package com.tianju.sort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Collections;

/**
 * Tianju Zhou
 * Jun 8, 2020
 */
public class HeapSortTest {
    @Test
    public void heapSortTest() {
        Integer[] testArr = new Integer[] {12,3,5,11,8,9,0,2,22,4,6,6,6};
        HeapSort.heapSort(testArr);
        Assertions.assertArrayEquals(testArr, new Integer[] {0,2,3,4,5,6,6,6,8,9,11,12,22});

        HeapSort.heapSort(testArr, Collections.reverseOrder());
        Assertions.assertArrayEquals(testArr, new Integer[] {22,12,11,9,8,6,6,6,5,4,3,2,0});
    }
}

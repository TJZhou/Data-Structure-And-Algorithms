package com.tianju.sort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 * Tianju Zhou
 * Jun 8, 2020
 */
public class MergeSortTest {
    @Test
    public void mergeSortTest() {
        Double[] testArr1 = new Double[1000000];
        for(int i = 0; i < 1000000; i++)
            testArr1[i] = Math.random() * 100000;
        Double[] testArr2 = Arrays.copyOf(testArr1, 1000000);

        Long start = System.currentTimeMillis();
        MergeSort.mergeSort(testArr1);
        Long end = System.currentTimeMillis();
        System.out.println("Merge Sort Time cost:" + (end-start));
        Arrays.sort(testArr2);
        Assertions.assertArrayEquals(testArr1, testArr2);

        MergeSort.mergeSort(testArr1, Collections.reverseOrder());
        MergeSort.mergeSort(testArr2, Collections.reverseOrder());
        Assertions.assertArrayEquals(testArr1, testArr2);
    }
}

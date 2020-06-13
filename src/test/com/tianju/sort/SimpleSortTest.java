package com.tianju.sort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Tianju Zhou
 * Jun 8, 2020
 */
public class SimpleSortTest {

    @Test
    public void countSortTest() {
        Random ra = new Random();
        int[] testArr1 = new int[1000000];
        for(int i = 0; i < 1000000; i++)
            testArr1[i] = ra.nextInt(100000);
        int[] testArr2 = Arrays.copyOf(testArr1, 1000000);
        SimpleSort.countSort(testArr1, 100000);
        Arrays.sort(testArr2);
        Assertions.assertArrayEquals(testArr1, testArr2);
    }

    @Test
    public void insertionSortTest() {
        Integer[] testArr = new Integer[] {12,3,5,11,8,9,0,2,22,4,6,6,6};
        SimpleSort.insertionSort(testArr);
        Assertions.assertArrayEquals(testArr, new Integer[] {0,2,3,4,5,6,6,6,8,9,11,12,22});

        SimpleSort.insertionSort(testArr, Collections.reverseOrder());
        Assertions.assertArrayEquals(testArr, new Integer[] {22,12,11,9,8,6,6,6,5,4,3,2,0});
    }

    @Test
    public void bubbleSortTest() {
        Integer[] testArr = new Integer[] {12,3,5,11,8,9,0,2,22,4,6,6,6};
        SimpleSort.bubbleSort(testArr);
        Assertions.assertArrayEquals(testArr, new Integer[] {0,2,3,4,5,6,6,6,8,9,11,12,22});

        SimpleSort.bubbleSort(testArr, Collections.reverseOrder());
        Assertions.assertArrayEquals(testArr, new Integer[] {22,12,11,9,8,6,6,6,5,4,3,2,0});
    }

    @Test
    public void selectionSortTest() {
        Integer[] testArr = new Integer[] {12,3,5,11,8,9,0,2,22,4,6,6,6};
        SimpleSort.selectionSort(testArr);
        Assertions.assertArrayEquals(testArr, new Integer[] {0,2,3,4,5,6,6,6,8,9,11,12,22});

        SimpleSort.selectionSort(testArr, Collections.reverseOrder());
        Assertions.assertArrayEquals(testArr, new Integer[] {22,12,11,9,8,6,6,6,5,4,3,2,0});
    }
}

package com.tianju.sort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

public class RadixSortTest {

    private final int size = 1000000;
    private final int upperBound = 100000;

    @Test
    public void radixSortTest() {
        Random ra = new Random();
        int[] testArr1 = new int[size];
        for(int i = 0; i < size; i++)
            testArr1[i] = ra.nextInt(upperBound);
        int[] testArr2 = Arrays.copyOf(testArr1, size);

        Long start = System.currentTimeMillis();
        RadixSort.radixSort(testArr1);
        Long end = System.currentTimeMillis();
        System.out.println("Radix Sort Time cost:" + (end - start));

        Arrays.sort(testArr2);
        Assertions.assertArrayEquals(testArr1, testArr2);
    }

    @Test
    public void quickSortTest() {
        Random ra = new Random();
        Integer[] testArr1 = new Integer[size];
        for (int i = 0; i < size; i++)
            testArr1[i] = ra.nextInt(upperBound);
        Integer[] testArr2 = Arrays.copyOf(testArr1, size);

        Long start = System.currentTimeMillis();
        QuickSort.quickSort(testArr1);
        Long end = System.currentTimeMillis();
        System.out.println("Quick Sort Time cost:" + (end - start));

        Arrays.sort(testArr2);
        Assertions.assertArrayEquals(testArr1, testArr2);
    }
}

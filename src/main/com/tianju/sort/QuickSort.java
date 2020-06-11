package com.tianju.sort;

import java.util.Comparator;

/**
 * Tianju Zhou
 * Jun 8, 2020
 */
public class QuickSort {

    public static<T extends Comparable<? super T>> void quickSort(T[] elements) {
        quickSort(elements, Comparator.naturalOrder());
    }

    public static<T> void quickSort(T[] elements, Comparator<? super T> c) {
        partitionHelper(elements, 0, elements.length - 1, c);
    }

    private static <T> void partitionHelper(T[] elements, int left, int right, Comparator<? super T> c) {
        if(left >= right)
            return;
        // when the chunk size less than or equals to 10, use simple insertion sort
        if(right - left <= 10) {
            SimpleSort.insertionSort(elements, c, left, right);
            return;
        }
        int l = left, r = right, pivot = l;
        T p = elements[pivot];
        while(l <= r) {
            while(c.compare(elements[l], p) < 0)
                l++;
            while(c.compare(elements[r], p) > 0)
                r--;
            if(l > r) break;
            swap(elements, l, r);
            l++;
            r--;
        }
        partitionHelper(elements, left, r, c);
        partitionHelper(elements, l, right, c);
    }

    private static<T> void swap(T[] elements, int idx1, int idx2) {
        T temp = elements[idx1];
        elements[idx1] = elements[idx2];
        elements[idx2] = temp;
    }
}

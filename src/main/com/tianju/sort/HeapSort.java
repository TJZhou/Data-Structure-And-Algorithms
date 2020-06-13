package com.tianju.sort;

import com.tianju.util.Util;

import java.util.Comparator;

/**
 * Tianju Zhou
 * Jun 8, 2020
 */
public class HeapSort {

    public static<T extends Comparable<? super T>> void heapSort(T[] elements) {
        heapSort(elements, Comparator.naturalOrder());
    }

    public static <T> void heapSort(T[] elements, Comparator<? super T> comparator) {
        int len = elements.length;

        // build heap first
        for(int i = (len >> 1) - 1; i >= 0; i--)
            siftDown(elements, comparator, i, len);

        // there is still element not sorted
        while(len > 0) {
            // put the Min/Max element at the end of array, and decrease the unsorted length
            Util.swap(elements, 0, --len);
            // siftDown the first element
            siftDown(elements, comparator, 0, len);
        }
    }

    /**
     *
     * @param elements: array need to be sorted
     * @param comparator:
     * @param idx: Index in the array. In Heapify process, it decreases from size/2 - 1 to 0. In sort process, it's always 0.
     * @param len: Unsorted length.
     * @param <T>: Generic Type
     */
    private static <T> void siftDown(T[] elements, Comparator<? super T> comparator, int idx, int len) {
        T t = elements[idx];
        int half = len >> 1;
        while(idx < half) {
            int left = (idx << 1) + 1, right = (idx << 1) + 2, child = left;
            if(right < len && comparator.compare(elements[left], elements[right]) < 0)
                child = right;
            if(comparator.compare(t, elements[child]) > 0)
                break;
            Util.swap(elements, idx, child);
            idx = child;
        }
    }
}

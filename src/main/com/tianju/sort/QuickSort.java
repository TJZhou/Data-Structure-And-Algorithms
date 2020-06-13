package com.tianju.sort;

import com.tianju.util.Util;

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
        int l = left, r = right, pivot = l;
        T p = elements[pivot];
        while(l <= r) {
            while(c.compare(elements[l], p) < 0)
                l++;
            while(c.compare(elements[r], p) > 0)
                r--;
            if(l > r) break;
            Util.swap(elements, l, r);
            l++;
            r--;
        }
        partitionHelper(elements, left, r, c);
        partitionHelper(elements, l, right, c);
    }
}

package com.tianju.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Tianju Zhou
 * Jun 8, 2020
 */
public class MergeSort {

    public static<T extends Comparable<? super T>> void mergeSort(T[] elements) {
        mergeSort(elements, Comparator.naturalOrder());
    }

    public static<T> void mergeSort(T[] elements, Comparator<? super T> c) {
        divideHelper(elements, 0, elements.length - 1, c);
    }

    private static<T> void divideHelper(T[] elements, int left, int right, Comparator<? super T> c) {
        if(right <= left) return;
        // when the chunk size less than or equals to 10, use simple insertion sort
        if(right - left <= 10) {
            SimpleSort.insertionSort(elements, c, left, right);
            return;
        }
        int mid = left + (right - left) / 2;
        divideHelper(elements, left, mid, c);
        divideHelper(elements, mid + 1, right, c);
        mergeHelper(elements, left, mid, right, c);
    }

    private static<T> void mergeHelper(T[] elements, int left, int mid, int right, Comparator<? super T> c) {
        T[] l = Arrays.copyOfRange(elements, left, mid + 1);
        T[] r = Arrays.copyOfRange(elements, mid+1, right + 1);
        int i = 0, j = 0;
        while(left <= right) {
            if(i >= l.length) {
                elements[left++] = r[j++];
            } else if (j >= r.length) {
                elements[left++] = l[i++];
            } else if(c.compare(l[i], r[j]) < 0){
                elements[left++] = l[i++];
            } else {
                elements[left++] = r[j++];
            }
        }
    }
}

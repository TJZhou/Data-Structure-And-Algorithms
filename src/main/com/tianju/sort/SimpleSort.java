package com.tianju.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Tianju Zhou
 * Jun 8, 2020
 */
public class SimpleSort {

    public static<T extends Comparable<? super T>> void insertionSort(T[] elements) {
        Comparator<T> c = Comparator.naturalOrder();
        insertionSort(elements, c);
    }

    public static <T> void insertionSort(T[] elements, Comparator<? super T> c) {
        for(int i = 0; i < elements.length; i++) {
            int lo = 0, hi = i;
            T ele = elements[i];
            // binary search for insertion index
            while(lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if(c.compare(elements[mid], ele) > 0) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }
            // insert
            System.arraycopy(elements, lo, elements, lo + 1, i - lo);
            elements[lo] = ele;
        }
    }


    public static<T extends Comparable<? super T>> void bubbleSort(T[] elements) {
        bubbleSort(elements, Comparator.naturalOrder());
    }

    public static<T> void bubbleSort(T[] elements, Comparator<? super T> c) {
        for(int i = 0; i < elements.length; i++) {
            for(int j = 0; j < elements.length - i - 1; j++) {
                if(c.compare(elements[j], elements[j+1]) > 0)
                    swap(elements, j, j+1);
            }
        }
    }

    public static<T extends Comparable<? super T>> void selectionSort(T[] elements) {
        selectionSort(elements, Comparator.naturalOrder());
    }

    public static<T> void selectionSort(T[] elements, Comparator<? super T> c) {
        for(int i = 0; i < elements.length; i++) {
            T t = elements[i];
            int idx = i;
            for(int j = i + 1; j < elements.length; j++) {
                if(c.compare(t, elements[j]) > 0) {
                    t = elements[j];
                    idx = j;
                }
            }
            swap(elements, i, idx);
        }
    }

    private static<T> void swap(T[] elements, int idx1, int idx2) {
        T temp = elements[idx1];
        elements[idx1] = elements[idx2];
        elements[idx2] = temp;
    }
}

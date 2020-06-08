package com.tianju.sort;

import java.util.Comparator;

/**
 * Tianju Zhou
 * Jun 8, 2020
 */
public class HeapSort {

    public static<T> void heapSort(Object[] elements) {
        heapSort(elements, Comparator.naturalOrder());
    }

    public static <T> void heapSort(Object[] elements, Comparator<? super T> comparator) {
        int len = elements.length, unsortedLength = len;

        // build heap first
        for(int i = len / 2 - 1; i >= 0; i--)
            siftDown(elements, comparator, i, len, elements[i]);

        // still element not sorted
        while(unsortedLength > 0) {
            // put the Min/Max element at the end of array
            swap(elements, 0, --unsortedLength);
            // siftDown the first element
            siftDown(elements, comparator, 0, unsortedLength, elements[0]);
        }

        // reverse array order
        for(int i = 0; i < elements.length / 2; i++)
            swap(elements, i, elements.length - i - 1);
    }

    private static void swap(Object[] elements, int idx1, int idx2) {
        Object temp = elements[idx1];
        elements[idx1] = elements[idx2];
        elements[idx2] = temp;
    }

    @SuppressWarnings("unchecked")
    private static <T> void siftDown(Object[] elements, Comparator<? super T> comparator, int idx, int len, Object ele) {
        T t = (T) ele;
        int half = len / 2;
        while(idx < half) {
            int left = 2 * idx + 1, right = 2 * idx + 2, child = left;
            if(right < len && comparator.compare((T) elements[right], (T) elements[left]) < 0)
                child = right;
            if(comparator.compare(t, (T) elements[child]) < 0)
                break;
            swap(elements, idx, child);
            idx = child;
        }
    }
}

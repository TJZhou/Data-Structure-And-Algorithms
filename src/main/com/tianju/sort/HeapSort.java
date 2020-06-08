package com.tianju.sort;

import java.util.Collection;
import java.util.Comparator;

public class HeapSort<T extends Comparable<? super T>>  {

    private Comparator<? super T> comparator;

    private Object[] elements;

    public HeapSort() {
        this(Comparator.naturalOrder());
    }

    public HeapSort(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    public void sort(Object[] elements) {
        this.elements = elements;
        int unsortedLength = elements.length;
        // build heap first
        heapify(unsortedLength);
        while(unsortedLength > 0) {
            // put the Min/Max element at the end of array
            swap(0, --unsortedLength);
            // siftDown the first element
            siftDown(0, unsortedLength, elements[0]);
        }
        // reverse array order
        for(int i = 0; i < elements.length / 2; i++)
            swap(i, elements.length - i - 1);
    }

    private void swap(int idx1, int idx2) {
        Object temp = elements[idx1];
        elements[idx1] = elements[idx2];
        elements[idx2] = temp;
    }

    private void heapify(int len) {
        for(int i = len / 2 - 1; i >= 0; i--)
            siftDown(i, len, elements[i]);
    }

    @SuppressWarnings("unchecked")
    private void siftDown(int idx, int len, Object ele) {
        T t = (T) ele;
        int half = len / 2;
        while(idx < half) {
            int left = 2 * idx + 1, right = 2 * idx + 2, child = left;
            if(right < len && comparator.compare((T) elements[right], (T) elements[left]) <= 0)
                child = right;
            if(comparator.compare(t, (T) elements[child]) <= 0)
                break;
            swap(idx, child);
            idx = child;
        }
    }
}

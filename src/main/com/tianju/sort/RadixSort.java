package com.tianju.sort;

import com.tianju.util.Util;
import java.util.LinkedList;

public class RadixSort {

    public static void radixSort(int[] elements) {
        int max = Util.getMax(elements), n = 1;
        while(max > 0) {
            countSort(elements, n);
            n *= 10;
            max /= 10;
        }
    }

    private static void countSort(int[] elements, int n) {
        LinkedList<Integer>[] lists = new LinkedList[10];
        for(int ele : elements) {
            int i = ele / n % 10;
            if(lists[i] == null)
                lists[i] = new LinkedList<>();
            lists[i].add(ele);
        }

        int i = 0;
        for(LinkedList<Integer> list : lists) {
            if(list == null)
                continue;
            for(int num : list)
                elements[i++] = num;
        }
    }
}

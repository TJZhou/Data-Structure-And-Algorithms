package com.tianju.util;

import java.util.Comparator;

public class Util {

    public static<T> void swap(T[] elements, int idx1, int idx2) {
        T temp = elements[idx1];
        elements[idx1] = elements[idx2];
        elements[idx2] = temp;
    }

    public static void swap(int[] elements, int idx1, int idx2) {
        int temp = elements[idx1];
        elements[idx1] = elements[idx2];
        elements[idx2] = temp;
    }

    public static void swap(double[] elements, int idx1, int idx2) {
        double temp = elements[idx1];
        elements[idx1] = elements[idx2];
        elements[idx2] = temp;
    }

    public static<T extends Comparable<? super T>> T getMax(T[] elements) {
        return getMax(elements, Comparator.naturalOrder());
    }

    public static<T extends Comparable<? super T>> T getMin(T[] elements) {
        return getMax(elements, Comparator.reverseOrder());
    }

    public static<T> T getMax(T[] elements, Comparator<T> c) {
        T ele = elements[0];
        for(int i = 1; i < elements.length; i++) {
            if(c.compare(elements[i],ele)> 0)
                ele = elements[i];
        }
        return ele;
    }

    public static int getMax(int[] elements) {
        int ele = elements[0];
        for(int i = 1; i < elements.length; i++) {
            if(elements[i] > ele)
                ele = elements[i];
        }
        return ele;
    }

    public static double getMax(double[] elements) {
        double ele = elements[0];
        for(int i = 1; i < elements.length; i++) {
            if(elements[i] > ele)
                ele = elements[i];
        }
        return ele;
    }

    public static int getMin(int[] elements) {
        int ele = elements[0];
        for(int i = 1; i < elements.length; i++) {
            if(elements[i] < ele)
                ele = elements[i];
        }
        return ele;
    }

    public static double getMin(double[] elements) {
        double ele = elements[0];
        for(int i = 1; i < elements.length; i++) {
            if(elements[i] < ele)
                ele = elements[i];
        }
        return ele;
    }
}

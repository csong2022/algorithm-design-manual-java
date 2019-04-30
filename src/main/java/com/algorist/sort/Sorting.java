/*
Copyright 2003 by Steven S. Skiena; all rights reserved.

Permission is granted for use in non-commercial applications
provided this copyright notice remains intact and unchanged.

This program appears in my book:

"Programming Challenges: The Programming Contest Training Manual"
by Steven Skiena and Miguel Revilla, Springer-Verlag, New York 2003.

See our website www.programming-challenges.com for additional information.

This book can be ordered from Amazon.com at

http://www.amazon.com/exec/obidos/ASIN/0387001638/thealgorithmrepo/

*/
package com.algorist.sort;

import com.algorist.datastructure.PriorityQueue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

import static java.util.Objects.compare;

/**
 * Sorting algorithms.
 * <p>
 * Translate from sorting.c.
 *
 * @author csong2022
 */
@SuppressWarnings("WeakerAccess")
public class Sorting {

    public static <T extends Comparable<T>> void insertionSort(T[] s, int l, int h) {
        insertionSort(s, l, h, Comparator.naturalOrder());
    }

    /**
     * Insertion sort.
     *
     * @param s   array.
     * @param l   index low bound (inclusive).
     * @param h   index upper bound (inclusive).
     * @param c   comparator.
     * @param <T> element type.
     */
    public static <T> void insertionSort(T[] s, int l, int h, Comparator<T> c) {
        for (int i = l + 1; i <= h; i++) {
            for (int j = i; j > l && less(s[j], s[j - 1], c); j--) {
                swap(s, j, j - 1);
            }
        }
    }

    public static <T extends Comparable<T>> void selectionSort(T[] s, int l, int h) {
        selectionSort(s, l, h, Comparator.naturalOrder());
    }

    /**
     * Selection sort.
     *
     * @param s   array.
     * @param l   index low bound (inclusive).
     * @param h   index upper bound (inclusive).
     * @param c   comparator.
     * @param <T> element type.
     */
    public static <T> void selectionSort(final T[] s, final int l, final int h, final Comparator<T> c) {
        for (int i = l; i <= h; i++) {
            int min = i;
            for (int j = i + 1; j <= h; j++) {
                if (less(s[j], s[min], c)) {
                    min = j;
                }
            }
            swap(s, i, min);
        }
    }

    public static <T extends Comparable<T>> void quickSort(T[] s, int l, int h) {
        quickSort(s, l, h, Comparator.naturalOrder());
    }

    /**
     * Quick sort.
     *
     * @param s   array.
     * @param l   index low bound (inclusive).
     * @param h   index upper bound (inclusive).
     * @param c   comparator.
     * @param <T> element type.
     */
    public static <T> void quickSort(final T[] s, final int l, final int h, final Comparator<T> c) {
        if (h > l) {
            final int p = partition(s, l, h, c);
            quickSort(s, l, p - 1, c);
            quickSort(s, p + 1, h, c);
        }
    }

    private static <T> int partition(T[] s, int l, int h, Comparator<T> c) {
        //noinspection UnnecessaryLocalVariable
        int p = h;            /* pivot element index */
        int firsthigh = l;    /* divider position for pivot element */
        for (int i = l; i < h; i++)
            if (less(s[i], s[p], c)) {
                swap(s, i, firsthigh);
                firsthigh++;
            }
        swap(s, p, firsthigh);

        return firsthigh;
    }

    public static <T extends Comparable<T>> void heapSort(T[] s, int l, int h) {
        heapSort(s, l, h, Comparator.naturalOrder());
    }

    /**
     * Heap sort.
     *
     * @param s   array.
     * @param l   index low bound (inclusive).
     * @param h   index upper bound (inclusive).
     * @param c   comparator.
     * @param <T> element type.
     */
    public static <T> void heapSort(final T[] s, final int l, final int h, final Comparator<T> c) {
        if (l >= h) {
            return;
        }

        final PriorityQueue<T> q = PriorityQueue.makeHeap(s, l, h, c);

        for (int i = l; i <= h; i++) {
            s[i] = q.extractMin();
        }
    }

    public static <T extends Comparable<T>> int binarySearch(T[] s, T key, int low, int high) {
        return binarySearch(s, key, low, high, Comparator.naturalOrder());
    }

    /**
     * Binary search.
     *
     * @param s    sorted array.
     * @param key  lookup key.
     * @param low  index low bound (inclusive).
     * @param high index upper bound (inclusive).
     * @param c    comparator.
     * @param <T>  element type.
     * @return index corresponding to the lookup key if found, otherwise -1;
     */
    public static <T> int binarySearch(T[] s, T key, int low, int high, Comparator<T> c) {
        int middle;            /* index of middle element */

        if (low > high) return (-1);    /* key not found */

        middle = (low + high) >>> 1;

        int cmp = Objects.compare(s[middle], key, c);
        if (cmp == 0) return (middle);

        if (cmp > 0)
            return binarySearch(s, key, low, middle - 1, c);
        else
            return binarySearch(s, key, middle + 1, high, c);
    }

    public static <T extends Comparable<T>> void mergeSort(T[] s, int l, int h) {
        mergeSort(s, l, h, Comparator.naturalOrder());
    }

    /**
     * Merge sort.
     *
     * @param s    array.
     * @param low  index low bound (inclusive).
     * @param high index upper bound (inclusive).
     * @param c    comparator.
     * @param <T>  element type.
     */
    public static <T> void mergeSort(final T[] s, final int low, final int high, final Comparator<T> c) {
        final T[] aux = Arrays.copyOf(s, s.length);
        mergeSort(s, aux, low, high, c);
    }

    private static <T> void mergeSort(final T[] s, final T[] aux, final int low, final int high, final Comparator<T> c) {
        if (low < high) {
            final int middle = (low + high) >>> 1;
            mergeSort(s, aux, low, middle, c);
            mergeSort(s, aux, middle + 1, high, c);
            merge(s, aux, low, middle, high, c);
        }
    }

    private static <T> void merge(final T[] s, final T[] aux, final int low, final int middle, final int high, final Comparator<T> c) {
        System.arraycopy(s, low, aux, low, (high - low + 1));

        int i = low, j = middle + 1;
        for (int k = low; k <= high; k++) {
            if (i > middle) {
                s[k] = aux[j++];
            } else if (j > high) {
                s[k] = aux[i++];
            } else if (less(aux[i], aux[j], c)) {
                s[k] = aux[i++];
            } else {
                s[k] = aux[j++];
            }
        }
    }

    public static <T extends Comparable<T>> boolean isSorted(final T[] s) {
        return isSorted(s, Comparator.naturalOrder());
    }

    /**
     * Test if array is already sorted.
     *
     * @param s   array.
     * @param c   comparator.
     * @param <T> element type.
     * @return true if array is sorted, otherwise false.
     */
    public static <T> boolean isSorted(final T[] s, final Comparator<T> c) {
        for (int i = 1; i < s.length; i++) {
            if (compare(s[i], s[i - 1], c) < 0) {
                return false;
            }
        }

        return true;
    }

    private static <T> void swap(T[] s, int i, int j) {
        T tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }

    private static <T> boolean less(T x, T y, Comparator<T> c) {
        return compare(x, y, c) < 0;
    }
}

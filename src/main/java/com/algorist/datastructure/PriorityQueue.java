/*
Copyright 2003 by Steven S. Skiena; all rights reserved.

Permission is granted for use in non-commerical applications
provided this copyright notice remains intact and unchanged.

This program appears in my book:

"Programming Challenges: The Programming Contest Training Manual"
by Steven Skiena and Miguel Revilla, Springer-Verlag, New York 2003.

See our website www.programming-challenges.com for additional information.

This book can be ordered from Amazon.com at

http://www.amazon.com/exec/obidos/ASIN/0387001638/thealgorithmrepo/

*/
package com.algorist.datastructure;

import java.util.Comparator;
import java.util.Objects;

import static com.algorist.datastructure.ArrayUtils.ARRAY_SIZE_THRESHOLD;
import static com.algorist.datastructure.ArrayUtils.newArray;

/**
 * Implementation of a heap / priority queue abstract data type.
 *
 * @param <T> element type.
 * @author csong2022
 */
public class PriorityQueue<T> {
    private Comparator<T> c;
    private T[] q;                          /* body of queue */
    private int n;                          /* number of queue elements */

    public PriorityQueue(Comparator<T> c) {
        this(c, ARRAY_SIZE_THRESHOLD);
    }

    public PriorityQueue(Comparator<T> c, int initCapacity) {
        this.c = c;
        this.q = newArray(initCapacity + 1);
        this.n = 0;
    }

    private static int parent(int n) {
        if (n == 1) return (-1);
        else return n / 2;        /* implicitly take floor(n/2) */
    }

    public static <T extends Comparable<T>> PriorityQueue<T> makeHeap(final T[] s, final int l, final int h) {
        return makeHeap(s, l, h, Comparator.naturalOrder());
    }

    public static <T> PriorityQueue<T> makeHeap(final T[] s, final int l, final int h, final Comparator<T> comparator) {
        final PriorityQueue<T> q = new PriorityQueue<>(comparator, h - l + 1);
        for (int i = l; i <= h; i++) {
            q.q[i - l + 1] = s[i];
        }
        q.n = h - l + 1;

        for (int i = q.n; i >= 1; i--) {
            q.bubbleDown(i);
        }

        return q;
    }

    public static <T> PriorityQueue<T> makeHeap1(final T[] s, final int l, final int h, final Comparator<T> comparator) {
        final PriorityQueue<T> q = new PriorityQueue<>(comparator, h - l + 1);
        for (int i = l; i <= h; i++) {
            q.insert(s[i]);
        }

        return q;
    }

    private int youngChild(int n) {
        return 2 * n;
    }

    private void bubbleUp(int p) {
        if (parent(p) == -1) return;    /* at root of heap, no parent */

        if (compare(this.q[parent(p)], this.q[p]) > 0) {
            swap(p, parent(p));
            bubbleUp(parent(p));
        }
    }

    private void bubbleDown(int p) {
        int c = youngChild(p);    /* child index */
        int min_index = p;         /* index of lightest child */

        for (int i = 0; i <= 1; i++)
            if ((c + i) <= this.n) {
                if (compare(q[min_index], q[c + i]) > 0) min_index = c + i;
            }

        if (min_index != p) {
            swap(p, min_index);
            bubbleDown(min_index);
        }
    }

    public void insert(T x) {
        resize();
        this.q[++this.n] = x;
        bubbleUp(this.n);
    }

    private void resize() {
        this.q = ArrayUtils.resize(this.q, this.n + 1);
    }

    private void swap(int i, int j) {
        T temp = this.q[i];
        this.q[i] = this.q[j];
        this.q[j] = temp;
    }

    public T extractMin() {
        T min = null;            /* minimum value */
        if (isEmpty()) {
            System.out.printf("Warning: empty priority queue.\n");
        } else {
            min = q[1]; /* minimum value */
            this.q[1] = this.q[this.n];
            this.q[this.n--] = null;
            bubbleDown(1);
            resize();
        }

        return min;
    }

    public boolean isEmpty() {
        return this.n == 0;
    }

    private int compare(T x, T y) {
        return Objects.compare(x, y, c);
    }

    public void print() {
        for (int i = 1; i <= this.n; i++)
            System.out.printf("%d ", this.q[i]);

        System.out.printf("\n");
    }

    public int size() {
        return this.n;
    }

}

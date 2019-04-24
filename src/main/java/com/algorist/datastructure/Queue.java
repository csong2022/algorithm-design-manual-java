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
package com.algorist.datastructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static com.algorist.datastructure.ArrayUtils.ARRAY_SIZE_THRESHOLD;
import static com.algorist.datastructure.ArrayUtils.newArray;

/**
 * Implementation of a FIFO queue abstract data type.
 * <p>
 * Translate from queue.h, queue.c. Implement with dynamic array. Add iterator implementation.
 *
 * @param <T> element type.
 * @author csong2022
 */
public class Queue<T> implements Iterable<T> {
    private T[] q;                          /* body of queue */
    private int first;                      /* position of first element */
    private int last;                       /* position of last element */
    private int count;                      /* number of queue elements */

    public Queue() {
        this(ARRAY_SIZE_THRESHOLD);
    }

    public Queue(int initCapacity) {
        this.q = newArray(initCapacity);
        this.first = 0;
        this.last = this.q.length - 1;
        this.count = 0;
    }

    public void enqueue(T x) {
        resize();
        this.last = (this.last + 1) % this.q.length;
        this.q[this.last] = x;
        this.count++;
    }

    public T dequeue() {
        if (isEmpty()) {
            System.out.println("Warning: empty queue dequeue.");
            return null;
        } else {
            T x = this.q[this.first];
            this.q[this.first] = null;
            this.first = (this.first + 1) % this.q.length;
            this.count--;

            return x;
        }
    }

    public T headq() {
        return this.q[this.first];
    }

    public boolean isEmpty() {
        return this.count == 0;
    }

    private void resize() {
        T[] adjustedQ = ArrayUtils.resize(this.q, first, count);

        if (adjustedQ.length != this.q.length) {
            this.q = adjustedQ;
            this.first = 0;
            this.last = this.count - 1;
        }
    }

    public void print() {
        for (int i = this.first; i != this.last; i = (i + 1) % this.q.length) {
            System.out.printf("%s ", this.q[i]);
        }

        System.out.printf("%s ", this.q[this.last]);
        System.out.println();
    }

    public int size() {
        return this.count;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int iterSoFar = 0;

            @Override
            public boolean hasNext() {
                return iterSoFar < count;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                int index = (first + iterSoFar) % q.length;
                T x = q[index];
                iterSoFar++;
                return x;
            }
        };
    }
}

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

import static com.algorist.datastructure.ArrayUtils.ARRAY_SIZE_THRESHOLD;
import static com.algorist.datastructure.ArrayUtils.newArray;

/**
 * Implementation of a LIFO stack abstract data type.
 *
 * @param <T> element type.
 * @author csong2022
 */
public class Stack<T> implements Iterable<T> {
    private T[] s;        /* body of queue */
    private int top;                        /* position of top element */
    private int count;                      /* number of stack elements */

    public Stack() {
        this(ARRAY_SIZE_THRESHOLD);
    }

    public Stack(int initCapacity) {
        this.s = newArray(initCapacity);
        this.top = -1;
        this.count = 0;
    }

    public void push(T x) {
        resize();
        this.s[++this.top] = x;
        this.count++;
    }

    public T pop() {
        if (isEmpty()) {
            System.out.printf("Warning: empty stack pop.\n");
            return null;
        } else {
            T x = this.s[this.top--];
            this.count--;
            resize();
            return x;
        }
    }

    /**
     * Dynamically adjust array size.
     */
    private void resize() {
        this.s = ArrayUtils.resize(this.s, count);
    }

    public boolean isEmpty() {
        return this.count == 0;
    }

    public void print() {
        for (int i = (this.count - 1); i >= 0; i--)
            System.out.printf("%s ", this.s[i]);

        System.out.printf("\n");
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = count - 1;

            public boolean hasNext() {
                return this.index >= 0;
            }

            public T next() {
                return s[this.index--];
            }
        };
    }
}

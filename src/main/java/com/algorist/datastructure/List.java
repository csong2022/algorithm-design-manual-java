/*
Copyright 2005 by Steven S. Skiena; all rights reserved.

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
import java.util.Objects;

/**
 * Linked list-based container implementation.
 * <p>
 * Translate from list-demo.c, list.h, item.h. Add iterator implementation.
 *
 * @param <T> Element type.
 * @author csong2022
 */
public class List<T> implements Iterable<T> {
    private Node<T> head;

    public List() {
        this.head = null;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public boolean contains(T x) {
        return search(x) != null;
    }

    private Node<T> search(T x) {
        Node<T> p = this.head;

        while (p != null && !Objects.equals(p.item, x)) {
            p = p.next;
        }

        return p;
    }

    public void insert(T x) {
        this.head = insert(this.head, x);
    }

    private Node<T> insert(Node<T> l, T x) {
        return new Node<>(x, l);
    }

    public void print() {
        print(this.head);
    }

    private void print(Node<T> l) {
        while (l != null) {
            System.out.printf("%s ", l.item);
            l = l.next;
        }

        System.out.println();
    }

    public void delete(T x) {
        Node<T> pred = null;
        Node<T> p = this.head;

        while (p != null && !Objects.equals(p.item, x)) {
            pred = p;
            p = p.next;
        }

        if (p != null) {
            if (pred == null) {
                this.head = p.next;
            } else {
                pred.next = p.next;
            }
            p.next = null;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private static class Node<T> {
        final T item;         /* data item */
        Node<T> next;   /* point to successor */

        Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }
    }

    private class ListIterator implements Iterator<T> {
        private Node<T> pred = null, predpred = null;
        private Node<T> current = List.this.head;

        @Override
        public boolean hasNext() {
            return this.current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            final T x = this.current.item;
            this.predpred = this.pred;
            this.pred = this.current;
            this.current = this.current.next;
            return x;
        }

        @Override
        public void remove() {
            if (this.pred != null) {
                Node<T> n = this.pred;
                if (this.predpred == null) {
                    head = this.current;
                } else {
                    this.predpred.next = this.current;
                }
                n.next = null;
            }
        }
    }
}

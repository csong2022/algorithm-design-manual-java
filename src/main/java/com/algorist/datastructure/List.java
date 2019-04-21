/*
Copyright 2005 by Steven S. Skiena; all rights reserved.

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

import java.util.Objects;

/**
 * Linked list-based container implementation.
 * <p>
 * Translate from list-demo.c, list.h, item.h
 *
 * @param <T> Element type.
 * @author csong2022
 */
public class List<T> {
    private Node<T> head;

    public List() {
        this.head = null;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public boolean contains(T x) {
        return search(this.head, x) != null;
    }

    private Node<T> search(Node<T> l, T x) {
        if (l == null) return null;

        if (Objects.equals(l.item, x))
            return l;
        else
            return search(l.next, x);
    }

    private Node<T> predecessor(Node<T> l, T x) {
        if ((l == null) || (l.next == null)) {
            // System.out.printf("Error: predecessor sought on null list.\n");
            return null;
        }

        if (Objects.equals(l.next.item, x))
            return l;
        else
            return predecessor(l.next, x);
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

        System.out.printf("\n");
    }

    public void delete(T x) {
        delete(this.head, x);
    }

    private void delete(Node<T> l, T x) {
        Node<T> p;            /* item pointer */
        Node<T> pred;            /* predecessor pointer */

        p = search(l, x);
        if (p != null) {
            pred = predecessor(l, x);
            if (pred == null)    /* splice out out list */
                this.head = p.next;
            else
                pred.next = p.next;

            p.next = null;        /* remove reference */
        }
    }

    static class Node<T> {
        T item;         /* data item */
        Node<T> next;   /* point to successor */

        Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }
    }
}

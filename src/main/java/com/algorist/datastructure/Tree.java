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

import java.util.Comparator;
import java.util.Objects;

/**
 * Binary search tree container implementation.
 *
 * @param <T> data item type.
 * @author csong2022
 */
public class Tree<T> {

    private final Comparator<T> c;
    private Node<T> root;

    public Tree(Comparator<T> c) {
        this.c = c;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public boolean contains(T x) {
        return search(this.root, x) != null;
    }

    private Node<T> search(Node<T> l, T x) {
        if (l == null) return null;

        int cmp = Objects.compare(x, l.item, c);
        if (cmp == 0) return l;
        if (cmp < 0)
            return search(l.left, x);
        else
            return search(l.right, x);
    }

    public void insert(T x) {
        this.root = insert(this.root, x, null);
    }

    private Node<T> insert(Node<T> l, T x, Node<T> parent) {
        if (l == null) {
            return new Node<>(x, parent);
        }

        int cmp = Objects.compare(x, l.item, c);
        if (cmp < 0)
            l.left = insert(l.left, x, l);
        else if (cmp > 0)
            l.right = insert(l.right, x, l);
        return l;
    }

    public void print() {
        print(this.root);
    }

    private void print(Node<T> l) {
        if (l != null) {
            print(l.left);
            System.out.printf("%s ", l.item);
            print(l.right);
        }
    }


    private Node<T> successorDescendant(final Node<T> t) {
        if (t.right == null)
            return null;

        Node<T> succ = t.right;
        while (succ.left != null)
            succ = succ.left;

        return succ;
    }

    private Node<T> findMinimum(final Node<T> t) {
        if (t == null)
            return null;

        Node<T> min = t;
        while (min.left != null)
            min = min.left;
        return min;
    }

    private Node<T> predecessorDescendant(final Node<T> t) {
        if (t == null)
            return null;

        Node<T> pred = t.left;
        while (pred.right != null)
            pred = pred.right;
        return pred;
    }

    public void delete(final T x) {
        this.root = this.delete(this.root, x);
    }

    private Node<T> delete(final Node<T> t, final T x) {
        final Node<T> d = this.search(t, x); /* node with key to delete */

        if (d == null) {
            System.out.printf("Warning: key to be deleted %s is not in the tree.\n", x.toString());
            return t;
        }

        final Node<T> p;                           /* node to be physically deleted */

        if (d.parent == null) { /* if d is the root */
            if (d.left == null && d.right == null) {
                return null;    /* root-only tree */
            }

            if (d.left != null) {
                p = this.predecessorDescendant(d);
            } else {
                p = this.successorDescendant(d);
            }
        } else {
            if (d.left == null || d.right == null) {
                final Node<T> child;
                if (d.left != null) {
                    child = d.left;
                } else {
                    child = d.right;
                }

                if (d.parent.left == d) { /* fill null pointer */
                    d.parent.left = child;
                } else {
                    d.parent.right = child;
                }

                if (child != null) child.parent = d.parent;

                return t;

            } else { /* p has 2 children */
                p = this.successorDescendant(d);
            }
        }

        if (p != null) {
            final T newKey = p.item; /* deal with simpler case of deletion */
            this.delete(t, p.item);
            d.item = newKey;
        }

        return t;
    }

    private static class Node<T> {
        T item;                /* data item */
        Node<T> parent;        /* pointer to parent */
        Node<T> left;          /* pointer to left child */
        Node<T> right;         /* pointer to right child */

        Node(T item, Node<T> parent) {
            this.item = item;
            this.parent = parent;
            this.left = this.right = null;
        }
    }
}

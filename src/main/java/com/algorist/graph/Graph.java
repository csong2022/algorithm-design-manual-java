package com.algorist.graph;
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

/**
 * A generic adjacency list graph data type.
 *
 * @param <T> edge node type.
 * @author csong2022
 */
public class Graph<T extends EdgeNode<T>> {
    private T[] edges;          /* adjacency info */
    private int[] degree;       /* outdegree of each vertex */
    private int nvertices;      /* number of vertices in the graph */
    private int nedges;         /* number of edges in the graph */
    private boolean directed;   /* is the graph directed? */

    public Graph(int nvertices, boolean directed) {
        this.nvertices = nvertices;
        this.nedges = 0;
        this.directed = directed;

        this.edges = (T[]) new EdgeNode[nvertices + 1];
        this.degree = new int[nvertices + 1];

        for (int i = 1; i < this.degree.length; i++) this.degree[i] = 0;
    }

    public int nvertices() {
        return this.nvertices;
    }

    public int nedges() {
        return this.nedges;
    }

    public T edge(int v) {
        return this.edges[v];
    }

    public boolean isDirected() {
        return directed;
    }

    public T findEdge(int x, int y) {
        for (T p = edges[x]; p != null; p = p.next()) {
            if (p.y() == y) return p;
        }
        return null;
    }

    public void insertEdge(int x, T n, boolean directed) {
        n.setNext(edges[x]);
        edges[x] = n;
        degree[x]++;

        if (!directed)
            insertEdge(n.y(), n.copy(x), true);
        else
            nedges++;
    }

    public void deleteEdge(int x, int y, boolean directed) {
        T pBack = null;
        for (T p = edges[x]; p != null; p = p.next())
            if (p.y() == y) {
                degree[x]--;
                if (pBack != null)
                    pBack.setNext(p.next());
                else
                    edges[x] = p.next();

                if (!directed)
                    deleteEdge(y, x, true);
                else
                    nedges--;

                return;
            }

        System.out.printf("Warning: deletion(%d,%d) not found in g.\n", x, y);
    }

    public void print() {
        for (int i = 1; i <= nvertices; i++) {
            System.out.printf("%d: ", i);

            for (T p = edges[i]; p != null; p = p.next()) {
                System.out.printf(" %d", p.y());
            }
            System.out.printf("\n");
        }
    }
}

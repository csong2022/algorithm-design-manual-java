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
package com.algorist.graph;

import com.algorist.datastructure.List;

import java.util.Iterator;

/**
 * A generic adjacency list graph data type.
 * <p>
 * Translate from graph.h, graph.c, include functionalities in wgraph.h, wgraph.c.
 * Generify the edge node type, using List data structure as adjacent list.
 * <p>
 * Implementation supports unweighted, weighted, and flow graph.
 *
 * @param <T> edge node type.
 * @author csong2022
 */
public class Graph<T extends EdgeNode> {
    private final List<T>[] edges;    /* adjacency info */
    @SuppressWarnings("MismatchedReadAndWriteOfArray")
    private final int[] degree;       /* outdegree of each vertex */
    private final int nvertices;      /* number of vertices in the graph */
    private final boolean directed;   /* is the graph directed? */
    private int nedges;         /* number of edges in the graph */

    @SuppressWarnings("unchecked")
    public Graph(int nvertices, boolean directed) {
        this.nvertices = nvertices;
        this.nedges = 0;
        this.directed = directed;

        this.edges = new List[nvertices + 1];
        this.degree = new int[nvertices + 1];

        for (int i = 1; i < this.degree.length; i++) {
            this.degree[i] = 0;
            this.edges[i] = new List<>();
        }
    }

    public int nvertices() {
        return this.nvertices;
    }

    public int nedges() {
        return this.nedges;
    }

    public List<T> edges(int v) {
        return this.edges[v];
    }

    public boolean isDirected() {
        return directed;
    }

    public T findEdge(int x, int y) {
        for (T p : edges[x]) {
            if (p.y() == y) return p;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public void insertEdge(int x, T n, boolean directed) {
        edges[x].insert(n);
        degree[x]++;

        if (!directed)
            insertEdge(n.y(), (T) n.copy(x), true);
        else
            nedges++;
    }

    public void deleteEdge(int x, int y, boolean directed) {
        Iterator<T> iterator = this.edges[x].iterator();
        while (iterator.hasNext()) {
            T p = iterator.next();
            if (p.y() == y) {
                iterator.remove();
                this.degree[x]--;
                if (!directed) {
                    this.deleteEdge(y, x, true);
                } else {
                    this.nedges--;
                }

                return;
            }
        }

        System.out.printf("Warning: deletion(%d,%d) not found in g.%n", x, y);
    }

    public void print() {
        for (int i = 1; i <= nvertices; i++) {
            System.out.printf("%d: ", i);

            for (T p : edges[i]) {
                System.out.printf(" %d", p.y());
            }
            System.out.println();
        }
    }
}

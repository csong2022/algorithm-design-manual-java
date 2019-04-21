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
package com.algorist.graph;

/**
 * A generic implementation of graph traversal: depth-first search.
 *
 * @param <T> edge node type.
 * @author csong2022
 */
public class DFS<T extends EdgeNode<T>> extends AbstractGraphSearch<T> {
    private int[] entryTime;         /* time of vertex entry */
    private int[] exitTime;          /* time of vertex exit */
    private int time;                 /* current event time */
    private boolean finished = false; /* if true, cut off search immediately */

    public DFS(Graph<T> g) {
        super(g);
        this.entryTime = new int[g.nvertices() + 1];
        this.exitTime = new int[g.nvertices() + 1];
        this.time = 0;
        this.finished = false;
    }

    EdgeType edgeClassification(int x, int y) {
        if (parent[y] == x) return EdgeType.TREE;
        if (discovered[y] && !processed[y]) return EdgeType.BACK;
        if (processed[y] && (entryTime[y] > entryTime[x])) return EdgeType.FORWARD;
        if (processed[y] && (entryTime[y] < entryTime[x])) return EdgeType.CROSS;

        System.out.printf("Warning: self loop (%d,%d)\n", x, y);
        return null;
    }

    @Override
    public void search(int v, GraphSearchCallback<T> callback) {
        if (finished) return;        /* allow for search termination */

        discovered[v] = true;
        setEntryTime(v);

        callback.processVertexEarly(v);

        for (T p = g.edge(v); p != null; p = p.next()) {
            int y = p.y();
            if (!discovered[y]) {
                parent[y] = v;
                callback.processEdge(v, y);
                search(y, callback);
            } else if (!processed[y] || g.isDirected())
                callback.processEdge(v, y);

            if (finished) return;
        }

        callback.processVertexLate(v);

        setExitTime(v);
        processed[v] = true;
    }

    int entryTime(int v) {
        return this.entryTime[v];
    }

    void setEntryTime(int v) {
        this.entryTime[v] = ++time;
    }

    int exitTime(int v) {
        return this.exitTime[v];
    }

    void setExitTime(int v) {
        this.exitTime[v] = ++time;
    }

    void setFinished() {
        this.finished = true;
    }

    /**
     * DFS edge types
     */
    enum EdgeType {
        TREE,    /* tree edge */
        BACK,    /* back edge */
        CROSS,   /* cross edge */
        FORWARD; /* forward edge */
    }
}

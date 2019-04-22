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

import com.algorist.datastructure.Queue;

/**
 * A generic implementation of graph traversal: breadth-first search.
 *
 * @param <T> edge node type.
 * @author csong2022
 */
public class BFS<T extends EdgeNode<T>> extends AbstractGraphSearch<T> {

    public BFS(Graph<T> g) {
        super(g);
    }

    @Override
    public void search(int start, GraphSearchCallback<T> callback) {
        Queue<Integer> q = new Queue<>();
        q.enqueue(start);
        discovered[start] = true;

        while (!q.isEmpty()) {
            int v = q.dequeue();
            callback.processVertexEarly(v);
            processed[v] = true;

            for (T p : g.edge(v)) {
                int y = p.y();

                if (callback.validateEdge(p)) {
                    if (!processed[y] || g.isDirected())
                        callback.processEdge(v, y);
                    if (!discovered[y]) {
                        q.enqueue(y);
                        discovered[y] = true;
                        parent[y] = v;
                    }
                }
            }

            callback.processVertexLate(v);
        }
    }
}

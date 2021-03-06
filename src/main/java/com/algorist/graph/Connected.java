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

/**
 * Compute the connected components of a graph.
 * <p>
 * Translate from connected.c.
 *
 * @author csong2022
 */
public class Connected<T extends EdgeNode> {
    public Connected(Graph<T> g) {
        BFS<T> bfs = new BFS<>(g);
        ConnectedCallback<T> callback = new ConnectedCallback<>();

        int c = 0;
        for (int i = 1; i <= g.nvertices(); i++)
            if (!bfs.discovered(i)) {
                c++;
                System.out.printf("Component %d:", c);
                bfs.search(i, callback);
                System.out.println();
            }
    }

    private static class ConnectedCallback<T extends EdgeNode> extends DefaultGraphSearchCallback<T> {
        @Override
        public void processVertexEarly(int v) {
            System.out.printf(" %d", v);
        }
    }
}

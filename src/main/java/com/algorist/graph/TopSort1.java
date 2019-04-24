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

import com.algorist.datastructure.Stack;

/**
 * Topologically sort a directed acyclic graph by DFS numbering (DAG)
 * <p>
 * Translate from topsort1.c.
 *
 * @param <T> edge node type.
 * @author csong2022
 */
public class TopSort1<T extends EdgeNode> {

    public TopSort1(Graph<T> g) {
        DFS<T> dfs = new DFS<>(g);
        TopSort1Callback<T> callback = new TopSort1Callback<>(dfs);

        for (int i = 1; i <= g.nvertices(); i++)
            if (!dfs.discovered(i))
                dfs.search(i, callback);

        Stack<Integer> sorted = callback.sorted();
        sorted.print();  /* report topological order */
    }

    private static class TopSort1Callback<T extends EdgeNode> implements GraphSearchCallback<T> {
        private final DFS<T> dfs;
        private final Stack<Integer> sorted;

        TopSort1Callback(DFS<T> dfs) {
            this.dfs = dfs;
            this.sorted = new Stack<>();
        }

        @Override
        public void processVertexEarly(int v) {
        }

        @Override
        public void processVertexLate(int v) {
            sorted.push(v);
        }

        @Override
        public void processEdge(int x, int y) {
            DFS.EdgeType edgeType = dfs.edgeClassification(x, y);

            if (edgeType == DFS.EdgeType.BACK)
                System.out.println("Warning: directed cycle found, not a DAG");
        }

        Stack<Integer> sorted() {
            return this.sorted;
        }
    }
}

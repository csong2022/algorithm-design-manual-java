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

import com.algorist.utils.IterableUtils;

/**
 * Identify a cycle in a graph, if one exists.
 *
 * @param <T> edge node type.
 * @author csong2022
 */
public class FindCycle<T extends EdgeNode<T>> {

    public FindCycle(Graph<T> g) {
        DFS<T> dfs = new DFS<>(g);
        FindCycleCallback<T> callback = new FindCycleCallback<>(dfs);
        dfs.search(1, callback);
    }

    private static class FindCycleCallback<T extends EdgeNode<T>> implements GraphSearchCallback<T> {
        private DFS<T> dfs;

        public FindCycleCallback(DFS<T> dfs) {
            this.dfs = dfs;
        }

        @Override
        public void processVertexEarly(int v) {
        }

        @Override
        public void processVertexLate(int v) {
        }

        @Override
        public void processEdge(int x, int y) {
            DFS.EdgeType edgeType = dfs.edgeClassification(x, y);

            if (edgeType == DFS.EdgeType.BACK) {    /* found back edge! */
                System.out.printf("Cycle from %d to %d:", y, x);
                Iterable<Integer> path = dfs.findPath(y, x);
                System.out.println(IterableUtils.toString(path));
                System.out.println();
                dfs.setFinished();
            }
        }
    }
}

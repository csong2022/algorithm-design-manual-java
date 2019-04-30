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
 * Identify strongly connected components in a graph.
 * <p>
 * Translate from strong.c.
 *
 * @param <T> edge node type.
 * @author csong2022
 */
public class Strong<T extends EdgeNode> {

    public Strong(Graph<T> g) {
        DFS<T> dfs = new DFS<>(g);
        StrongCallback<T> callback = new StrongCallback<>(dfs, g);

        for (int i = 1; i <= g.nvertices(); i++)
            if (!dfs.discovered(i)) {
                dfs.search(i, callback);
            }
    }

    private static class StrongCallback<T extends EdgeNode> extends DefaultGraphSearchCallback<T> {
        private final int[] low;        /* oldest vertex surely in component of v */
        private final int[] scc;        /* strong component number for each vertex */

        private final Stack<Integer> active;            /* active vertices of unassigned component */
        private final DFS<T> dfs;
        private int components_found;        /* number of strong components identified */

        StrongCallback(DFS<T> dfs, Graph<T> g) {
            this.dfs = dfs;

            this.low = new int[g.nvertices() + 1];
            this.scc = new int[g.nvertices() + 1];

            for (int i = 1; i <= g.nvertices(); i++) {
                low[i] = i;
                scc[i] = -1;
            }
            components_found = 0;
            active = new Stack<>();
        }

        @Override
        public void processVertexEarly(int v) {
            active.push(v);
        }

        @Override
        public void processVertexLate(int v) {
            if (low[v] == v) {        /* edge (parent[v],v) cuts off scc */
                /*printf("strong component started backtracking from %d\n",v);*/
                popComponent(v);
            }

            if (parent(v) > 0 && entryTime(low[v]) < entryTime(low[parent(v)]))
                low[parent(v)] = low[v];
        }

        private void popComponent(int v) {
            int t;                  /* vertex placeholder */

            components_found++;
            System.out.printf("%d is in component %d %n", v, components_found);

            scc[v] = components_found;
            while ((t = active.pop()) != v) {
                scc[t] = components_found;
                System.out.printf("%d is in component %d with %d %n", t, components_found, v);
            }
        }

        @Override
        public void processEdge(int x, int y) {
            DFS.EdgeType edgeType = dfs.edgeClassification(x, y); /* edge class */

            if (edgeType == DFS.EdgeType.BACK) {
                if (entryTime(y) < entryTime(low[x]))
                    low[x] = y;
            }

            if (edgeType == DFS.EdgeType.CROSS) {
                if (scc[y] == -1)    /* component not yet assigned */
                    if (entryTime(y) < entryTime(low[x]))
                        low[x] = y;
            }
        }

        private int parent(int v) {
            return dfs.parent(v);
        }

        private int entryTime(int v) {
            return dfs.entryTime(v);
        }
    }
}

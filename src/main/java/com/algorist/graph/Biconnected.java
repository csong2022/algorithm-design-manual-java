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
 * Identify articulation vertices in a graph
 *
 * @param <T> edge node type.
 * @author csong2022
 */
public class Biconnected<T extends EdgeNode> {

    public Biconnected(Graph<T> g) {
        DFS<T> dfs = new DFS<>(g);

        BiconnectedCallback<T> callback = new BiconnectedCallback<>(dfs, g);

        for (int i = 1; i <= g.nvertices(); i++)
            if (!dfs.discovered(i)) dfs.search(i, callback);
    }

    private static class BiconnectedCallback<T extends EdgeNode> implements GraphSearchCallback<T> {
        final int[] reachable_ancestor;    /* earliest reachable ancestor of v */
        final int[] tree_out_degree;       /* DFS tree outdegree of v */

        private final DFS<T> dfs;

        BiconnectedCallback(DFS<T> dfs, Graph<T> g) {
            this.dfs = dfs;
            this.reachable_ancestor = new int[g.nvertices() + 1];
            this.tree_out_degree = new int[g.nvertices() + 1];

            for (int i = 1; i <= g.nvertices(); i++)
                tree_out_degree[i] = 0;
        }

        @Override
        public void processVertexEarly(int v) {
            reachable_ancestor[v] = v;
        }

        @Override
        public void processVertexLate(int v) {
            int time_v;        /* earliest reachable time for v */
            int time_parent;    /* earliest reachable time for parent[v] */

            if (parent(v) < 1) {    /* test if v is the root */
                if (tree_out_degree[v] > 1)
                    System.out.printf("root articulation vertex: %d %n", v);
                return;
            }

            boolean root = parent(parent(v)) < 1;        /* test if parent[v] is root */
            if (reachable_ancestor[v] == parent(v) && (!root))
                System.out.printf("parent articulation vertex: %d %n", parent(v));

            if (reachable_ancestor[v] == v) {
                System.out.printf("bridge articulation vertex: %d %n", parent(v));

                if (tree_out_degree[v] > 0)    /* test if v is not a leaf */
                    System.out.printf("bridge articulation vertex: %d %n", v);
            }

            time_v = entryTime(reachable_ancestor[v]);
            time_parent = entryTime(reachable_ancestor[parent(v)]);

            if (time_v < time_parent)
                reachable_ancestor[parent(v)] = reachable_ancestor[v];
        }

        @Override
        public void processEdge(int x, int y) {
            DFS.EdgeType edgeType = dfs.edgeClassification(x, y);

            if (edgeType == DFS.EdgeType.TREE)
                tree_out_degree[x] = tree_out_degree[x] + 1;

            if ((edgeType == DFS.EdgeType.BACK) && (parent(x) != y)) {
                if (entryTime(y) < entryTime(reachable_ancestor[x]))
                    reachable_ancestor[x] = y;
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

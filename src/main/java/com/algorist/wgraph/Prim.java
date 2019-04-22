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
package com.algorist.wgraph;

import com.algorist.graph.Graph;
import com.algorist.graph.GraphSearchUtils;

/**
 * Compute minimum spanning trees of graphs via Dijkstra/Prim's algorithm.
 *
 * @author csong2022
 */
public class Prim {
    private static final int MAXINT = 100007;
    private int[] parent; /* discovery relation */

    public Prim(Graph<WeightedEdgeNode> g, int start) {
        this.parent = new int[g.nvertices() + 1];

        boolean[] intree = new boolean[g.nvertices() + 1];  /* is the vertex in the tree yet? */
        int[] distance = new int[g.nvertices() + 1];        /* cost of adding to tree */

        for (int i = 1; i <= g.nvertices(); i++) {
            intree[i] = false;
            distance[i] = MAXINT;
            parent[i] = -1;
        }

        distance[start] = 0;
        int v = start;    /* current vertex to process */

        while (!intree[v]) {
            intree[v] = true;

            for (WeightedEdgeNode p : g.edge(v)) {
                int w = p.y();
                int weight = p.weight();
                if (distance[w] > weight && !intree[w]) {
                    distance[w] = weight;
                    parent[w] = v;
                }
            }

            v = 1;
            int dist = MAXINT;
            for (int i = 1; i <= g.nvertices(); i++)
                if (!intree[i] && dist > distance[i]) {
                    dist = distance[i];
                    v = i;
                }
        }
    }

    public Iterable<Integer> findPath(int start, int end) {
        return GraphSearchUtils.findPath(start, end, this.parent);
    }
}

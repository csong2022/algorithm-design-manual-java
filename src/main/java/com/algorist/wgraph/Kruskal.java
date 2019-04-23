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
package com.algorist.wgraph;

import com.algorist.datastructure.SetUnion;
import com.algorist.graph.Graph;

import java.util.Comparator;

import static com.algorist.sort.Sorting.quickSort;

/**
 * Compute minimum spanning trees of graphs via Kruskal's algorithm.
 *
 * @author csong2022
 */
public class Kruskal {
    private static final Comparator<EdgePair> WEIGHT_COMPARE = new Comparator<EdgePair>() {
        @Override
        public int compare(EdgePair x, EdgePair y) {
            if (x.weight < y.weight) return -1;
            else if (x.weight > y.weight) return 1;
            else return 0;
        }
    };

    public Kruskal(Graph<WeightedEdgeNode> g) {
        SetUnion s = new SetUnion(g.nvertices()); /* set union data structure */

        System.out.printf("initialized set union\n");
        EdgePair[] e = toEdgeArray(g);
        quickSort(e, 0, e.length - 1, WEIGHT_COMPARE);

        for (int i = 0; i < g.nedges(); i++) {
            s.print();
            if (!s.sameComponent(e[i].x, e[i].y)) {
                System.out.printf("edge (%d,%d) of weight %d in MST\n", e[i].x, e[i].y, e[i].weight);
                s.unionSets(e[i].x, e[i].y);
            }
        }
    }

    private static EdgePair[] toEdgeArray(Graph<WeightedEdgeNode> g) {
        EdgePair[] e = new EdgePair[g.nedges()];

        int m = 0;
        for (int i = 1; i <= g.nvertices(); i++) {
            for (WeightedEdgeNode p : g.edges(i)) {
                if (p.y() > i) {
                    e[m++] = new EdgePair(i, p.y(), p.weight());
                }
            }
        }

        return e;
    }

    private static class EdgePair {
        final int x, y;                       /* adjacency info */
        final int weight;                     /* edge weight, if any */

        EdgePair(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
    }
}

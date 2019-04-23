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

import com.algorist.graph.Graph;

/**
 * Flow graph.
 *
 * @author csong2022
 */
public class FlowGraph extends Graph<FlowEdgeNode> {
    public FlowGraph(int nvertices, boolean directed) {
        super(nvertices, directed);
    }

    public void addResidualEdges() {
        for (int i = 1; i <= nvertices(); i++) {
            for (FlowEdgeNode p : edges(i)) {
                if (findEdge(p.y(), i) == null) {
                    insertEdge(p.y(), new FlowEdgeNode(i, 0), true);
                }
            }
        }
    }

    public void print() {
        for (int i = 1; i <= nvertices(); i++) {
            System.out.printf("%d: ", i);
            for (FlowEdgeNode p : edges(i)) {
                System.out.printf(" %s", p.toString());
            }
            System.out.println();
        }
    }
}

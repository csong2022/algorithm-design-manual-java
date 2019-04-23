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

import com.algorist.graph.GraphReader;

import java.util.Scanner;

/**
 * Flow graph reader.
 *
 * @author csong2022
 */
public class FlowGraphReader implements GraphReader<FlowEdgeNode> {
    @Override
    public FlowGraph readGraph(Scanner scanner, boolean directed) {
        int nvertices = scanner.nextInt();
        int m = scanner.nextInt();

        FlowGraph g = new FlowGraph(nvertices, directed);

        for (int i = 1; i <= m; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int w = scanner.nextInt();
            FlowEdgeNode edgeNode = new FlowEdgeNode(y, w);
            g.insertEdge(x, edgeNode, directed);
        }
        return g;
    }
}

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
import com.algorist.graph.GraphReader;

import java.util.Scanner;

/**
 * Weighted graph reader.
 *
 * @author csong2022
 */
public class WeightedGraphReader implements GraphReader<WeightedEdgeNode> {

    @Override
    public Graph<WeightedEdgeNode> readGraph(Scanner scanner, boolean directed) {
        final int nvertices = scanner.nextInt();
        final Graph<WeightedEdgeNode> g = new Graph<>(nvertices, directed);

        final int nedges = scanner.nextInt();

        int x, y, w;            /* placeholder for edge and weight */
        for (int i = 1; i <= nedges; i++) {
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            g.insertEdge(x, new WeightedEdgeNode(y, w), directed);
        }

        return g;
    }
}

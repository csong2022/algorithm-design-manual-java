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

import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import com.algorist.utils.IterableUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

/**
 * Driver program demonstrating breadth-first search.
 *
 * @author csong2022
 */
public class BFSDemoTest implements TestCaseWithInput {
    @Override
    public void process(Scanner scanner) {
        UnweightedGraphReader reader = new UnweightedGraphReader();
        Graph<UnweightedEdgeNode> g = reader.readGraph(scanner, false);
        g.print();

        BFS<UnweightedEdgeNode> bfs = new BFS<>(g);
        BFSDemoCallback callback = new BFSDemoCallback();

        bfs.search(1, callback);

        for (int i = 1; i <= g.nvertices(); i++)
            System.out.printf(" %d", bfs.parent(i));
        System.out.println();

        System.out.println();
        for (int i = 1; i <= g.nvertices(); i++) {
            Iterable<Integer> path = bfs.findPath(1, i);
            System.out.println(IterableUtils.toString(path));
        }
    }

    @Test
    public void test() throws IOException {
        TestEngine.execute(this, "grid", "grid-bfs-demo-out");
    }

    private static class BFSDemoCallback implements GraphSearchCallback<UnweightedEdgeNode> {
        @Override
        public void processVertexEarly(int v) {
            System.out.printf("processed vertex %d%n", v);
        }

        @Override
        public void processVertexLate(int v) {

        }

        @Override
        public void processEdge(int x, int y) {
            System.out.printf("processed edge (%d,%d)%n", x, y);
        }
    }
}
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
 * Driver program demonstrating depth-first search numbering and
 * edge labeling.
 *
 * @author csong2022
 */
public class DFSDemoTest implements TestCaseWithInput {
    @Override
    public void process(Scanner scanner) {
        UnweightedGraphReader reader = new UnweightedGraphReader();
        Graph<UnweightedEdgeNode> g = reader.readGraph(scanner, false);
        g.print();

        DFS<UnweightedEdgeNode> dfs = new DFS<>(g);
        DFSDemoTest.DFSDemoCallback callback = new DFSDemoTest.DFSDemoCallback(dfs);

        dfs.search(1, callback);

        System.out.println();
        for (int i = 1; i <= g.nvertices(); i++) {
            Iterable<Integer> path = dfs.findPath(1, i);
            System.out.println(IterableUtils.toString(path));
        }
    }

    @Test
    public void test() throws IOException {
        TestEngine.execute(this, "grid", "grid-dfs-demo-out");
    }

    private static class DFSDemoCallback extends DefaultGraphSearchCallback<UnweightedEdgeNode> {
        private final DFS<UnweightedEdgeNode> dfs;

        public DFSDemoCallback(DFS<UnweightedEdgeNode> dfs) {
            this.dfs = dfs;
        }

        @Override
        public void processVertexEarly(int v) {
            dfs.setEntryTime(v);
            System.out.printf("entered vertex %d at time %d%n", v, dfs.entryTime(v));
        }

        @Override
        public void processVertexLate(int v) {
            dfs.setExitTime(v);
            System.out.printf("exit vertex %d at time %d%n", v, dfs.exitTime(v));
        }

        @Override
        public void processEdge(int x, int y) {
            DFS.EdgeType edgeType = dfs.edgeClassification(x, y);

            switch (edgeType) {
                case BACK:
                    System.out.printf("back edge (%d,%d)%n", x, y);
                    break;
                case TREE:
                    System.out.printf("tree edge (%d,%d)%n", x, y);
                    break;
                case FORWARD:
                    System.out.printf("forward edge (%d,%d)%n", x, y);
                    break;
                case CROSS:
                    System.out.printf("cross edge (%d,%d)%n", x, y);
                    break;
                default:
                    System.out.printf("edge (%d,%d)%n not in valid class=%s", x, y, edgeType);
            }
        }
    }
}
package com.algorist.backtrack;

import com.algorist.graph.Graph;
import com.algorist.graph.UnweightedEdgeNode;
import com.algorist.graph.UnweightedGraphReader;
import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.util.Scanner;

import static com.algorist.backtrack.Backtrack.NMAX;

public class PathsTest implements TestCaseWithInput {
    @Override
    public void process(Scanner scanner) {
        Graph<UnweightedEdgeNode> g = new UnweightedGraphReader().readGraph(scanner, false);
        g.print();

        Backtrack backtrack = new Backtrack();
        Paths<UnweightedEdgeNode> paths = new Paths<>(g);

        int[] a = new int[NMAX];            /* solution vector */
        for (int i = 1; i <= g.nvertices(); i++) {
            System.out.printf("%nPaths from 1 to %d:%n", i);
            backtrack.backtrack(a, 0, i, paths);
        }
    }

    @Test
    public void test() throws Exception {
        TestEngine.execute(this, "paths-graph", "paths-graph-out");
    }
}
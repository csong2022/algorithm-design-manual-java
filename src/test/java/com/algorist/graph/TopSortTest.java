package com.algorist.graph;

import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

public class TopSortTest implements TestCaseWithInput {
    @Override
    public void process(Scanner scanner) {
        UnweightedGraphReader reader = new UnweightedGraphReader();
        Graph<UnweightedEdgeNode> g = reader.readGraph(scanner, true);
        g.print();

        TopSort<UnweightedEdgeNode> topSort = new TopSort<>(g);

        int[] out = topSort.sorted();
        for (int i = 1; i <= g.nvertices(); i++)
            System.out.printf(" %d", out[i]);
        System.out.printf("\n");
    }

    @Test
    public void test() throws IOException {
        TestEngine.execute("grid", "grid-topsort-out", this);
    }
}
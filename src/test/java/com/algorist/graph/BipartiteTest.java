package com.algorist.graph;

import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

public class BipartiteTest implements TestCaseWithInput {
    @Override
    public void process(Scanner scanner) {
        UnweightedGraphReader reader = new UnweightedGraphReader();
        Graph<UnweightedEdgeNode> g = reader.readGraph(scanner, false);
        g.print();

        Bipartite bipartite = new Bipartite<>(g);
        for (int i = 1; i <= g.nvertices(); i++)
            System.out.printf(" %d", bipartite.color(i).ordinal());
        System.out.printf("\n");
    }

    @Test
    public void testGrid() throws IOException {
        TestEngine.execute("grid", "bipartite-grid", this);
    }

    @Test
    public void testTree() throws IOException {
        TestEngine.execute("tree", "bipartite-tree", this);
    }

    @Test
    public void testArt3() throws IOException {
        TestEngine.execute("art3", "bipartite-art3", this);
    }
}
package com.algorist.graph;

import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

public class BiconnectedTest implements TestCaseWithInput {
    @Override
    public void process(Scanner scanner) {
        UnweightedGraphReader reader = new UnweightedGraphReader();
        Graph<UnweightedEdgeNode> g = reader.readGraph(scanner, false);
        g.print();

        new Biconnected<>(g);
    }

    @Test
    public void testGrid() throws IOException {
        TestEngine.execute(this, "grid", "biconnected-grid");
    }

    @Test
    public void testTree() throws IOException {
        TestEngine.execute(this, "tree", "biconnected-tree");
    }

    @Test
    public void testArt3() throws IOException {
        TestEngine.execute(this, "art3", "biconnected-art3");
    }

    @Test
    public void testBaase() throws IOException {
        TestEngine.execute(this, "baase", "biconnected-baase");
    }

    @Test
    public void testClrGraph() throws IOException {
        TestEngine.execute(this, "clr-graph", "biconnected-clr-graph");
    }
}
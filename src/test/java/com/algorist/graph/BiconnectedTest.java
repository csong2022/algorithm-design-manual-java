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
        TestEngine.execute("grid", "biconnected-grid", this);
    }

    @Test
    public void testTree() throws IOException {
        TestEngine.execute("tree", "biconnected-tree", this);
    }

    @Test
    public void testArt3() throws IOException {
        TestEngine.execute("art3", "biconnected-art3", this);
    }

    @Test
    public void testBaase() throws IOException {
        TestEngine.execute("baase", "biconnected-baase", this);
    }

    @Test
    public void testClrGraph() throws IOException {
        TestEngine.execute("clr-graph", "biconnected-clr-graph", this);
    }
}
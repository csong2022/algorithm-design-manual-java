package com.algorist.graph;

import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

public class ConnectedTest implements TestCaseWithInput {

    @Override
    public void process(Scanner scanner) {
        UnweightedGraphReader reader = new UnweightedGraphReader();
        Graph<UnweightedEdgeNode> g = reader.readGraph(scanner, false);
        g.print();

        new Connected<>(g);
    }

    @Test
    public void testConnected() throws IOException {
        TestEngine.execute(this, "connected-in", "connected-out");
    }

    @Test
    public void testGrid() throws IOException {
        TestEngine.execute(this, "grid", "grid-connected-out");
    }
}
package com.algorist.graph;

import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

public class TopSort1Test implements TestCaseWithInput {
    @Override
    public void process(Scanner scanner) {
        UnweightedGraphReader reader = new UnweightedGraphReader();
        Graph<UnweightedEdgeNode> g = reader.readGraph(scanner, true);
        g.print();

        new TopSort1<>(g);
    }

    @Test
    public void test() throws IOException {
        TestEngine.execute(this, "grid", "grid-topsort1-out");
    }
}
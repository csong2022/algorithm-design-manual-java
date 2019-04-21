package com.algorist.graph;

import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

public class StrongTest implements TestCaseWithInput {
    @Override
    public void process(Scanner scanner) {
        UnweightedGraphReader reader = new UnweightedGraphReader();
        Graph<UnweightedEdgeNode> g = reader.readGraph(scanner, true);
        g.print();

        new Strong<>(g);
    }

    @Test
    public void testG1() throws IOException {
        TestEngine.execute(this, "g-1", "strong-g-1");
    }

    @Test
    public void testG2() throws IOException {
        TestEngine.execute(this, "g-2", "strong-g-2");
    }

    @Test
    public void testG3() throws IOException {
        TestEngine.execute(this, "g-3", "strong-g-3");
    }

    @Test
    public void testG4() throws IOException {
        TestEngine.execute(this, "g-4", "strong-g-4");
    }

    @Test
    public void testG5() throws IOException {
        TestEngine.execute(this, "g-5", "strong-g-5");
    }

    @Test
    public void testClr() throws IOException {
        TestEngine.execute(this, "strong-clr", "strong-clr-out");
    }
}
package com.algorist.wgraph;

import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

public class FloydTest implements TestCaseWithInput {
    @Override
    public void process(Scanner scanner) {
        AdjacencyMatrix g = AdjacencyMatrix.readAdjacencyMatrix(scanner, false);
        g.printGraph();

        new Floyd(g);

        g.print();
    }

    @Test
    public void test() throws IOException {
        TestEngine.execute(this, "wgrid", "wgrid-floyd-out");
    }
}
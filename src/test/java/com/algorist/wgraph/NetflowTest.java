package com.algorist.wgraph;

import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.util.Scanner;

public class NetflowTest implements TestCaseWithInput {
    public void process(Scanner scanner) {
        int source = scanner.nextInt();
        int sink = scanner.nextInt();
        FlowGraph g = new FlowGraphReader().readGraph(scanner, true);

        new Netflow(g, source, sink);
        g.print();

        int flow = 0; /* total flow */
        for (FlowEdgeNode p : g.edge(source)) {
            flow += p.flow();
        }

        System.out.printf("total flow = %d\n", flow);
    }

    @Test
    public void testNetflow1() throws Exception {
        TestEngine.execute(this, "netflow1-in", "netflow1-out");
    }

    @Test
    public void testNetflow2() throws Exception {
        TestEngine.execute(this, "netflow2-in", "netflow2-out");
    }

    @Test
    public void testNetflow3() throws Exception {
        TestEngine.execute(this, "netflow3-in", "netflow3-out");
    }
}
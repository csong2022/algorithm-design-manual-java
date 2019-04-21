package com.algorist.wgraph;

import com.algorist.graph.Graph;
import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import com.algorist.utils.IterableUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

public class PrimTest implements TestCaseWithInput {
    @Override
    public void process(Scanner scanner) {
        WeightedGraphReader reader = new WeightedGraphReader();
        Graph<WeightedEdgeNode> g = reader.readGraph(scanner, false);

        Prim prim = new Prim(g, 1);
        System.out.printf("Out of Prim\n");

        System.out.println();
        for (int i = 1; i <= g.nvertices(); i++) {
            /*printf(" %d parent=%d\n",i,parent[i]);*/
            Iterable<Integer> path = prim.findPath(1, i);
            System.out.println(IterableUtils.toString(path));
        }
    }

    @Test
    public void test() throws IOException {
        TestEngine.execute("wgrid", "wgrid-prim-out", this);
    }

}
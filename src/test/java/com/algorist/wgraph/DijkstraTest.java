package com.algorist.wgraph;

import com.algorist.graph.Graph;
import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import com.algorist.utils.IterableUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

public class DijkstraTest implements TestCaseWithInput {
    @Override
    public void process(Scanner scanner) {
        WeightedGraphReader reader = new WeightedGraphReader();
        Graph<WeightedEdgeNode> g = reader.readGraph(scanner, false);

        Dijkstra dijkstra = new Dijkstra(g, 1);

        System.out.println();
        for (int i = 1; i <= g.nvertices(); i++) {
            /*printf(" %d parent=%d\n",i,parent[i]);*/
            Iterable<Integer> path = dijkstra.findPath(1, i);
            System.out.println(IterableUtils.toString(path));
        }
    }

    @Test
    public void test() throws IOException {
        TestEngine.execute(this, "wgrid", "wgrid-dijkstra-out");
    }
}
package com.algorist.graph;

import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import com.algorist.utils.IterableUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

public class BFSDemoTest implements TestCaseWithInput {
    @Override
    public void process(Scanner scanner) {
        UnweightedGraphReader reader = new UnweightedGraphReader();
        Graph<UnweightedEdgeNode> g = reader.readGraph(scanner, false);
        g.print();

        BFS<UnweightedEdgeNode> bfs = new BFS<>(g);
        BFSDemoCallback callback = new BFSDemoCallback();

        bfs.search(1, callback);

        for (int i = 1; i <= g.nvertices(); i++)
            System.out.printf(" %d", bfs.parent(i));
        System.out.printf("\n");

        System.out.printf("\n");
        for (int i = 1; i <= g.nvertices(); i++) {
            Iterable<Integer> path = bfs.findPath(1, i);
            System.out.println(IterableUtils.toString(path));
        }
    }

    @Test
    public void test() throws IOException {
        TestEngine.execute("grid", "grid-bfs-demo-out", this);
    }

    private static class BFSDemoCallback implements GraphSearchCallback<UnweightedEdgeNode> {
        @Override
        public void processVertexEarly(int v) {
            System.out.printf("processed vertex %d\n", v);
        }

        @Override
        public void processVertexLate(int v) {

        }

        @Override
        public void processEdge(int x, int y) {
            System.out.printf("processed edge (%d,%d)\n", x, y);
        }
    }
}
package com.algorist.graph;

import org.junit.Test;

public class FindCycleTest {
    @Test
    public void test() {
        final Graph<UnweightedEdgeNode> g = new Graph<>(4, true);
        g.insertEdge(1, new UnweightedEdgeNode(2), true);
        g.insertEdge(2, new UnweightedEdgeNode(3), true);
        g.insertEdge(3, new UnweightedEdgeNode(1), true);
        g.insertEdge(3, new UnweightedEdgeNode(4), true);

        g.print();

        new FindCycle(g);
    }
}
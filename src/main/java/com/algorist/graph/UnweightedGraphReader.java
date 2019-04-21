package com.algorist.graph;

import java.util.Scanner;

/**
 * Unweighted graph reader.
 *
 * @author csong2022
 */
public class UnweightedGraphReader implements GraphReader<UnweightedEdgeNode> {

    @Override
    public Graph<UnweightedEdgeNode> readGraph(Scanner scanner, boolean directed) {
        final int nvertices = scanner.nextInt();
        final Graph<UnweightedEdgeNode> g = new Graph<>(nvertices, directed);

        final int nedges = scanner.nextInt();

        int x, y;            /* vertices in edge (x,y) */
        for (int i = 1; i <= nedges; i++) {
            x = scanner.nextInt();
            y = scanner.nextInt();
            g.insertEdge(x, new UnweightedEdgeNode(y), directed);
        }

        return g;
    }
}

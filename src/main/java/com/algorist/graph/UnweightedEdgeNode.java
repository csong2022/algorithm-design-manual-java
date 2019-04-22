package com.algorist.graph;

/**
 * Unweighted edge note.
 *
 * @author csong2022
 */
public class UnweightedEdgeNode implements EdgeNode<UnweightedEdgeNode> {
    private final int y;                /* adjancency info */

    public UnweightedEdgeNode(int y) {
        this.y = y;

    }

    @Override
    public int y() {
        return y;
    }

    public UnweightedEdgeNode copy(int v) {
        return new UnweightedEdgeNode(v);
    }
}

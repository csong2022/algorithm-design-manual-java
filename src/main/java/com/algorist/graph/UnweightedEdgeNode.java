package com.algorist.graph;

/**
 * Unweighted edge note.
 *
 * @author csong2022
 */
public class UnweightedEdgeNode implements EdgeNode<UnweightedEdgeNode> {
    private final int y;                /* adjancency info */
    private UnweightedEdgeNode next;

    public UnweightedEdgeNode(int y) {
        this.y = y;

    }

    @Override
    public int y() {
        return y;
    }

    @Override
    public UnweightedEdgeNode next() {
        return this.next;
    }

    @Override
    public void setNext(UnweightedEdgeNode next) {
        this.next = next;
    }

    public UnweightedEdgeNode copy(int v) {
        return new UnweightedEdgeNode(v);
    }
}

/*
Copyright 2003 by Steven S. Skiena; all rights reserved.

Permission is granted for use in non-commerical applications
provided this copyright notice remains intact and unchanged.

This program appears in my book:

"Programming Challenges: The Programming Contest Training Manual"
by Steven Skiena and Miguel Revilla, Springer-Verlag, New York 2003.

See our website www.programming-challenges.com for additional information.

This book can be ordered from Amazon.com at

http://www.amazon.com/exec/obidos/ASIN/0387001638/thealgorithmrepo/

*/
package com.algorist.wgraph;

import com.algorist.graph.EdgeNode;

/**
 * Flow Graph edge node.
 *
 * @author csong2022
 */
public class FlowEdgeNode implements EdgeNode<FlowEdgeNode> {
    private final int y;                   /* neighboring vertex */
    private final int capacity;            /* capacity of edge */
    int residual;                          /* residual capacity of edge */
    private int flow;                      /* flow through edge */
    private FlowEdgeNode next;

    public FlowEdgeNode(int y, int capacity) {
        this.y = y;
        this.capacity = capacity;
        this.flow = 0;
        this.residual = capacity;
    }

    @Override
    public int y() {
        return this.y;
    }

    public int capacity() {
        return this.capacity;
    }

    public int flow() {
        return this.flow;
    }

    public void increaseFlow(int delta) {
        this.flow += delta;
    }

    public int residual() {
        return this.residual;
    }

    public void decreaseResidual(int delta) {
        this.residual -= delta;
    }

    public void increaseResidual(int delta) {
        this.residual += delta;
    }

    @Override
    public FlowEdgeNode next() {
        return this.next;
    }

    @Override
    public void setNext(FlowEdgeNode next) {
        this.next = next;
    }

    @Override
    public FlowEdgeNode copy(int v) {
        return new FlowEdgeNode(v, this.capacity);
    }

    public String toString() {
        return String.format("%d(%d,%d,%d)", y, capacity, flow, residual);
    }
}

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
 * Weighted edge node.
 *
 * @author csong2022
 */
public class WeightedEdgeNode implements EdgeNode<WeightedEdgeNode> {
    private final int y;                /* adjancency info */
    private final int weight;            /* edge weight */
    private WeightedEdgeNode next;

    public WeightedEdgeNode(int y, int weight) {
        this.y = y;
        this.weight = weight;
    }

    @Override
    public int y() {
        return this.y;
    }

    public int weight() {
        return this.weight;
    }

    @Override
    public WeightedEdgeNode next() {
        return next;
    }

    @Override
    public void setNext(WeightedEdgeNode next) {
        this.next = next;
    }

    @Override
    public WeightedEdgeNode copy(int v) {
        return new WeightedEdgeNode(v, this.weight);
    }
}

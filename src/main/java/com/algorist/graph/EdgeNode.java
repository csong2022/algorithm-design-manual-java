package com.algorist.graph;

/**
 * Adjacency list node.
 * <p>
 * Generify from edgenode type in graph.h.
 *
 * @author csong2022
 */
public interface EdgeNode {
    /**
     * @return adjacent vertex.
     */
    int y();           /* adjancency info */

    /**
     * Create a copy of edge node for given vertex.
     *
     * @param v vertex.
     * @return copy of edge node for given vertex.
     */
    EdgeNode copy(int v);
}

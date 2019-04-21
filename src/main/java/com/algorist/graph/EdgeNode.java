package com.algorist.graph;

/**
 * Adjacency list node.
 *
 * @param <T> Edge node type.
 * @author csong2022
 */
public interface EdgeNode<T extends EdgeNode> {
    /**
     * @return adjacent vertex.
     */
    int y();           /* adjancency info */

    /**
     * @return next in the adjacency list.
     */
    T next();          /* next edge in list */

    /**
     * Set next adjacent node.
     *
     * @param next next node.
     */
    void setNext(T next);

    /**
     * Create a copy of edge node for given vertex.
     *
     * @param v vertex.
     * @return copy of edge node for given vertex.
     */
    T copy(int v);
}

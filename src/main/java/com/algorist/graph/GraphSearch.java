package com.algorist.graph;

/**
 * Graph traversal.
 *
 * @param <T> edge node type.
 * @author csong2022
 */
public interface GraphSearch<T extends EdgeNode<T>> {
    /**
     * Graph traverse from starting point.
     *
     * @param start    starting vertex.
     * @param callback graph traversal callback.
     */
    void search(final int start, final GraphSearchCallback<T> callback);

    /**
     * Find path from starting point to end.
     *
     * @param start starting point.
     * @param end   ending point.
     * @return path from starting to end point.
     */
    Iterable<Integer> findPath(final int start, final int end);
}

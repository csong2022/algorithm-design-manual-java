package com.algorist.graph;

/**
 * Graph traversal callback.
 *
 * @param <T> edge node type.
 * @author csong2022
 */
public interface GraphSearchCallback<T extends EdgeNode> {

    /**
     * Validate node.
     *
     * @param p edge node.
     * @return true if node is valid, otherwise false.
     */
    default boolean validateEdge(T p) {
        return true;
    }

    /**
     * bfs-dfs.c
     * process_vertex_early(int)
     *
     * @param v vertex.
     */
    void processVertexEarly(int v);

    /**
     * bfs-dfs.c
     * process_vertex_late(int)
     *
     * @param v vertex.
     */
    void processVertexLate(int v);

    /**
     * bfs-dfs.c
     * process_edge(int, int)
     *
     * @param x edge starting point.
     * @param y edge end point.
     */
    void processEdge(int x, int y);
}

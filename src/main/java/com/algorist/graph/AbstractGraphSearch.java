package com.algorist.graph;

/**
 * Common graph search code between BFS and DFS.
 *
 * @param <T> edges node type.
 * @author csong2022
 */
public abstract class AbstractGraphSearch<T extends EdgeNode> implements GraphSearch<T> {
    final Graph<T> g;            /* The graph */
    final boolean[] processed;   /* which vertices have been processed */
    final boolean[] discovered;  /* which vertices have been found */
    final int[] parent;          /* discovery relation */

    AbstractGraphSearch(Graph<T> g) {
        this.g = g;
        this.processed = new boolean[g.nvertices() + 1];
        this.discovered = new boolean[g.nvertices() + 1];
        this.parent = new int[g.nvertices() + 1];

        initialize();
    }

    public void initialize() {
        for (int i = 0; i <= g.nvertices(); i++) {
            processed[i] = discovered[i] = false;
            parent[i] = -1;
        }
    }

    public int parent(int v) {
        return parent[v];
    }

    public boolean processed(int v) {
        return processed[v];
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean discovered(int v) {
        return discovered[v];
    }

    @Override
    public Iterable<Integer> findPath(int start, int end) {
        return GraphSearchUtils.findPath(start, end, this.parent);
    }
}

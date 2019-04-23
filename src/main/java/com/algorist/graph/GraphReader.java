package com.algorist.graph;

import java.util.Scanner;

/**
 * Graph reader.
 *
 * @param <T> edge node type.
 * @author csong2022
 */
public interface GraphReader<T extends EdgeNode> {
    /**
     * Read graph from input.
     *
     * @param scanner  input.
     * @param directed directed or not?
     * @return graph.
     */
    Graph<T> readGraph(Scanner scanner, boolean directed);
}

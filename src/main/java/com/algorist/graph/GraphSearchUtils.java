package com.algorist.graph;

import com.algorist.datastructure.Stack;

/**
 * Graph search utilities.
 *
 * @author csong2022
 */
public class GraphSearchUtils {

    public static Iterable<Integer> findPath(final int start, final int end, final int[] parents) {
        final Stack<Integer> path = new Stack<>();
        findPath(start, end, parents, path);
        return path;
    }

    private static void findPath(final int start, final int end, final int[] parents, final Stack<Integer> path) {
        if ((start == end) || (end == -1)) {
            path.push(start);
        } else {
            path.push(end);
            findPath(start, parents[end], parents, path);
        }
    }
}

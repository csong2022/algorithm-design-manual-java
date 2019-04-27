package com.algorist.backtrack;

/**
 * Back track callback API.
 *
 * @param <T> input data type.
 * @author csong2022
 */
public interface BacktrackCallback<T> {
    /**
     * Test the first k elements of vector a are a complete solution for the given problem.
     *
     * @param a     solution vector.
     * @param k     first k elements.
     * @param input allow pass general information.
     * @return true if the first k elements of vector a are a complete solution, otherwise false.
     */
    boolean isaSolution(int[] a, int k, T input);

    /**
     * Process a complete solution once it is constructed.
     *
     * @param a     solution vector.
     * @param k     first k elements.
     * @param input general input.
     */
    void processSolution(int[] a, int k, T input);

    /**
     * Fills an array c with the complete set of possible candidates for the kth position of a,
     * given the contents of the first k - 1 positions.
     *
     * @param a     solution vector.
     * @param k     kth element.
     * @param input general input.
     * @param c     candidates.
     * @return the number of candidates.
     */
    int constructCandidates(int[] a, int k, T input, int[] c);

    /**
     * Make a move based on updated kth position of a.
     *
     * @param a     solution vector.
     * @param k     kth element.
     * @param input general input.
     */
    void makeMove(int[] a, int k, T input);

    /**
     * Undo the move based on updated kth position of a.
     *
     * @param a     solution vector.
     * @param k     kth element.
     * @param input general input.
     */
    void unmakeMove(int[] a, int k, T input);
}

package com.algorist.backtrack;

/**
 * Back track callback API.
 *
 * @param <T> input data type.
 * @author csong2022
 */
public interface BacktrackCallback<T> {
    void processSolution(int[] a, int k, T input);

    boolean isaSolution(int[] a, int k, T input);

    void makeMove(int[] a, int k, T input);

    void unmakeMove(int[] a, int k, T input);

    int constructCandidates(int[] a, int k, T input, int[] c);
}

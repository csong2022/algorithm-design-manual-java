/*
Copyright 2003 by Steven S. Skiena; all rights reserved.

Permission is granted for use in non-commercial applications
provided this copyright notice remains intact and unchanged.

This program appears in my book:

"Programming Challenges: The Programming Contest Training Manual"
by Steven Skiena and Miguel Revilla, Springer-Verlag, New York 2003.

See our website www.programming-challenges.com for additional information.

This book can be ordered from Amazon.com at

http://www.amazon.com/exec/obidos/ASIN/0387001638/thealgorithmrepo/

*/
package com.algorist.backtrack;

/**
 * Construct all permutations via backtracking.
 * <p>
 * Translate from permutations.c.
 *
 * @author csong2022
 */
public class Permutations implements BacktrackCallback<Integer> {
    @Override
    public void processSolution(int[] a, int k, Integer n) {
        for (int i = 1; i <= k; i++) System.out.printf(" %d", a[i]);

        System.out.println();
    }

    @Override
    public boolean isaSolution(int[] a, int k, Integer n) {
        return k == n;
    }

    @Override
    public void makeMove(int[] a, int k, Integer n) {
    }

    @Override
    public void unmakeMove(int[] a, int k, Integer n) {
    }

    @Override
    public int constructCandidates(int[] a, int k, Integer n, int[] c) {
        boolean[] inPerm = new boolean[n + 1];        /* what is now in the permutation? */

        for (int i = 1; i < n; i++) inPerm[i] = false;
        for (int i = 1; i < k; i++) inPerm[a[i]] = true;

        int ncandidates = 0;
        for (int i = 1; i <= n; i++) {
            if (!inPerm[i]) {
                c[ncandidates] = i;
                ncandidates++;
            }
        }

        return ncandidates;
    }
}

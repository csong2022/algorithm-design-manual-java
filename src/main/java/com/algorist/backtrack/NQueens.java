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

import static java.lang.Math.abs;

/**
 * Solve the eight queens problem using backtracking.
 *
 * @author csong2022
 */
public class NQueens implements BacktrackCallback<Integer> {
    private int solutionCount;            /* how many solutions are there? */

    @Override
    public void processSolution(int[] a, int k, Integer n) {
        solutionCount++;
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
        boolean legalMove;        /* might the move be legal? */

        int ncandidates = 0;
        for (int i = 1; i <= n; i++) {
            legalMove = true;

            for (int j = 1; j < k; j++) {
                if (abs((k) - j) == abs(i - a[j]))  /* diagonal threat */
                    legalMove = false;
                if (i == a[j])                      /* column threat */
                    legalMove = false;
            }

            if (legalMove) {
                c[ncandidates] = i;
                ncandidates++;
            }
        }

        return ncandidates;
    }

    public void setSolutionCount(int solutionCount) {
        this.solutionCount = solutionCount;
    }

    public int solutionCount() {
        return this.solutionCount;
    }
}

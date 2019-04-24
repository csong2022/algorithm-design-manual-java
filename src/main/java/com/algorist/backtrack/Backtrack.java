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
 * A generic backtracking implementation.
 * <p>
 * Translate from backtrack.h, backtrack.c.
 *
 * @author csong2022
 */
public class Backtrack {
    public static final int NMAX = 100;                /* maximum solution size */
    private static final int MAXCANDIDATES = 100;      /* max possible next extensions */
    private boolean finished = false;                  /* found all solutions yet? */

    public <T> void backtrack(int[] a, int k, T input, BacktrackCallback<T> callback) {

        if (callback.isaSolution(a, k, input))
            callback.processSolution(a, k, input);

        else {
            k++;
            int[] c = new int[MAXCANDIDATES];   /* candidates for next position */
            int ncandidates = callback.constructCandidates(a, k, input, c); /* next position candidate count */
            for (int i = 0; i < ncandidates; i++) {
                a[k] = c[i];
                callback.makeMove(a, k, input);

                backtrack(a, k, input, callback);
                if (finished) return;    /* terminate early */

                callback.unmakeMove(a, k, input);
            }
        }
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}

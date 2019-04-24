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
 * Construct all subsets via backtracking.
 * <p>
 * Translate from subsets.c.
 *
 * @author csong2022
 */
public class Subsets implements BacktrackCallback<Integer> {
    @Override
    public void processSolution(int[] a, int k, Integer n) {
        System.out.print("{");
        for (int i = 1; i <= k; i++)
            if (a[i] == 1) System.out.printf(" %d", i);

        System.out.println(" }");
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
        c[0] = 1;
        c[1] = 0;
        return 2;
    }
}

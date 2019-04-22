/*
Copyright 2003 by Steven S. Skiena; all rights reserved.

Permission is granted for use in non-commerical applications
provided this copyright notice remains intact and unchanged.

This program appears in my book:

"Programming Challenges: The Programming Contest Training Manual"
by Steven Skiena and Miguel Revilla, Springer-Verlag, New York 2003.

See our website www.programming-challenges.com for additional information.

This book can be ordered from Amazon.com at

http://www.amazon.com/exec/obidos/ASIN/0387001638/thealgorithmrepo/

*/
package com.algorist.backtrack;

import com.algorist.graph.EdgeNode;
import com.algorist.graph.Graph;

import static com.algorist.backtrack.Backtrack.NMAX;

/**
 * Enumerate the paths in a graph via backtracking.
 *
 * @author csong2022
 */
public class Paths<T extends EdgeNode> implements BacktrackCallback<Integer> {
    private final Graph<T> g;       /* graph to traverse */
    private int solutionCount;      /* how many solutions are there? */

    public Paths(Graph<T> g) {
        this.g = g;
        this.solutionCount = 0;
    }

    @Override
    public void processSolution(int[] a, int k, Integer n) {
        this.solutionCount++;

        System.out.print("{");
        for (int i = 1; i <= k; i++)
            System.out.printf(" %d", a[i]);
        System.out.println(" }");
    }

    @Override
    public boolean isaSolution(int[] a, int k, Integer t) {
        return a[k] == t;
    }

    @Override
    public void makeMove(int[] a, int k, Integer n) {
    }

    @Override
    public void unmakeMove(int[] a, int k, Integer n) {

    }

    @Override
    public int constructCandidates(int[] a, int k, Integer n, int[] c) {
        boolean[] inSol = new boolean[NMAX];        /* what's already in the solution? */

        for (int i = 1; i < NMAX; i++) inSol[i] = false;
        for (int i = 1; i < k; i++) inSol[a[i]] = true;

        int ncandidates;
        if (k == 1) {              /* always start from vertex 1 */
            c[0] = 1;
            ncandidates = 1;
        } else {
            ncandidates = 0;
            int last = a[k - 1];   /* last vertex on current path */
            for (T p : g.edge(last)) {
                if (!inSol[p.y()]) {
                    c[ncandidates] = p.y();
                    ncandidates++;
                }
            }
        }

        return ncandidates;
    }

    public int solutionCount() {
        return this.solutionCount;
    }
}

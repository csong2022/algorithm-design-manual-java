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
package com.algorist.dp;

import static java.lang.Math.min;

/**
 * Elevator stop optimization via dynamic programming.
 *
 * @author csong2022
 */
public class Elevator {
    static final int MAX_RIDERS = 50; /* what is the capacity of the elevator? */
    private static final int NFLOORS = 25;    /* the height of the building in floors */
    private static final int MAXINT = 100007;

    final int[] stops;          /* what floor does everyone get off at? */
    final int[][] m;            /* dynamic programming cost table */
    private final int[][] p;            /* dynamic programming parent table */
    private final int nriders;  /* number of riders */
    private final int nstops;   /* number of allowable stops */

    public Elevator(int[] stops, int nstops) {
        this.nriders = stops.length - 1;
        this.nstops = nstops;
        this.stops = stops;
        this.m = new int[NFLOORS + 1][nstops + 1];
        this.p = new int[NFLOORS + 1][nstops + 1];
    }

    /**
     * m[i][j] denotes the cost of serving all the riders using j stops,
     * the last of which is at floor i.  Zero is the originating floor.
     */
    int optimizeFloors() {
        for (int i = 0; i <= NFLOORS; i++) {
            m[i][0] = floorsWalked(0, MAXINT);
            p[i][0] = -1;
        }

        for (int j = 1; j <= nstops; j++)
            for (int i = 0; i <= NFLOORS; i++) {
                m[i][j] = MAXINT;
                for (int k = 0; k <= i; k++) {
                    int cost = m[k][j - 1] - floorsWalked(k, MAXINT) +
                            floorsWalked(k, i) + floorsWalked(i, MAXINT);
                    if (cost < m[i][j]) {
                        m[i][j] = cost;
                        p[i][j] = k;
                    }
                }
            }

        int laststop = 0;    /* the elevator's last stop */
        for (int i = 1; i <= NFLOORS; i++)
            if (m[i][nstops] < m[laststop][nstops])
                laststop = i;

        return laststop;
    }

    private int floorsWalked(int previous, int current) {
        int nsteps = 0;        /* total distance traveled */

        for (int i = 1; i <= nriders; i++)
            if (stops[i] > previous && stops[i] <= current)
                nsteps += min(stops[i] - previous, current - stops[i]);

        return nsteps;
    }

    void reconstructPath(int lastfloor, int stopsToGo) {
        if (stopsToGo > 1)
            reconstructPath(p[lastfloor][stopsToGo], stopsToGo - 1);

        System.out.printf("%d\n", lastfloor);
    }

    void printCostTable() {
        printMatrix(m);
    }

    void printParentTable() {
        printMatrix(p);
    }

    private void printMatrix(int[][] m) {
        for (int j = 0; j <= nstops; j++) {
            for (int i = 0; i <= NFLOORS; i++)
                System.out.printf("%3d", m[i][j]);
            System.out.println();
        }
    }
}

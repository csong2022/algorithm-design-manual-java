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

import java.util.Scanner;

import static java.lang.Math.max;

/**
 * Optimally balance partitions using dynamic programming.
 * <p>
 * Translate from partition.c.
 *
 * @author csong2022
 */
public class Partition {
    private static final int MAXINT = 100000;        /* infinity */

    static Data read(Scanner scanner) {
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            s[i] = scanner.nextInt();
        }

        return new Data(s, n, k);
    }

    private static void printBooks(int[] s, int start, int end) {
        System.out.print("{");
        for (int i = start; i <= end; i++) System.out.printf(" %d ", s[i]);
        System.out.println("}");
    }

    static void printMatrix(int[][] m, int n, int k) {
        System.out.println();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++)
                System.out.printf(" %d ", m[i][j]);
            System.out.println();
        }
    }

    private static void reconstructPartition(int[] s, int[][] d, int n, int k) {
        if (k == 1)
            printBooks(s, 1, n);
        else {
            reconstructPartition(s, d, d[n][k], k - 1);
            printBooks(s, d[n][k] + 1, n);
        }
    }

    public static void partition(int[] s, int n, int k) {
        int[] p = new int[n + 1];          /* prefix sums array */
        int[][] m = new int[n + 1][k + 1]; /* DP table for values */
        int[][] d = new int[n + 1][k + 1]; /* DP table for dividers */
        int cost;                          /* test split cost */

        p[0] = 0;                /* construct prefix sums */
        for (int i = 1; i <= n; i++) p[i] = p[i - 1] + s[i];

        for (int i = 1; i <= n; i++) m[i][1] = p[i];    /* initialize boundaries */
        for (int j = 1; j <= k; j++) m[1][j] = s[1];

        for (int i = 2; i <= n; i++)             /* evaluate main recurrence */
            for (int j = 2; j <= k; j++) {
                m[i][j] = MAXINT;
                for (int x = 1; x <= (i - 1); x++) {
                    cost = max(m[x][j - 1], p[i] - p[x]);
                    if (m[i][j] > cost) {
                        m[i][j] = cost;
                        d[i][j] = x;
                    }
                }
            }

        reconstructPartition(s, d, n, k);        /* print book partition */
    }

    static class Data {
        final int[] s;              /* book thicknesses to partition */
        final int n;                /* how many books? */
        final int k;                /* how many partitions? */

        Data(int[] s, int n, int k) {
            this.s = s;
            this.n = n;
            this.k = k;
        }
    }
}

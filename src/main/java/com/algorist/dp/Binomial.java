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

/**
 * Compute the binomial coefficients using dynamic programming.
 *
 * @author csong2022
 */
public class Binomial {
    public static long binomialCoefficient(int n, int m) {
        if (m > n) {
            throw new IllegalArgumentException("m > n");
        }

        long[][] bc = new long[n + 1][n + 1];    /* table of binomial coefficient values */

        for (int i = 0; i <= n; i++) bc[i][0] = 1;
        for (int j = 0; j <= n; j++) bc[j][j] = 1;

        for (int i = 1; i <= n; i++)
            for (int j = 1; j < i; j++)
                bc[i][j] = bc[i - 1][j - 1] + bc[i - 1][j];

        return bc[n][m];
    }
}

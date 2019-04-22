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

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
 * Fibonacci Numbers.
 *
 * @author csong2022
 */
public class Fib {
    static final int MAXN = 45;                    /* largest n or m */
    private static final long UNKNOWN = -1;        /* contents denote an empty cell */

    public static long fibR(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        return fibR(n - 1) + fibR(n - 2);
    }

    private static long fibC(int n, long[] f) {
        if (f[n] == UNKNOWN)
            f[n] = fibC(n - 1, f) + fibC(n - 2, f);

        return f[n];
    }

    public static long fibCDriver(int n) {
        long[] f = new long[MAXN + 1];

        f[0] = 0;
        f[1] = 1;

        for (int i = 2; i <= n; i++) f[i] = UNKNOWN;

        return fibC(n, f);
    }

    public static long fibDp(int n) {
        long[] f = new long[MAXN + 1];         /* array for caching computed fib values */

        f[0] = 0;
        f[1] = 1;

        for (int i = 2; i <= n; i++) f[i] = f[i - 1] + f[i - 2];

        return f[n];
    }

    public static long fibDp2(int n) {
        long back1 = 1, back2 = 0;    /* last two values of f[n] */
        long next;                    /* placeholder for sum */

        if (n == 0) return 0;

        for (int i = 2; i < n; i++) {
            next = back1 + back2;
            back2 = back1;
            back1 = next;
        }

        return back1 + back2;
    }
}

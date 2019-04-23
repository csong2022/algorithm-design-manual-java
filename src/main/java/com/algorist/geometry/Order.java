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
package com.algorist.geometry;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Demonstrate traversal orders on a grid.
 *
 * @author csong2022
 */
public class Order {

    public static void rowMajor(int n, int m) {
        int i, j;    /* counters */

        for (i = 1; i <= n; i++)
            for (j = 1; j <= m; j++)
                process(i, j);
    }

    public static void columnMajor(int n, int m) {
        int i, j;    /* counters */

        for (j = 1; j <= m; j++)
            for (i = 1; i <= n; i++)
                process(i, j);
    }


    public static void snakeOrder(int n, int m) {
        int i, j;    /* counters */

        for (i = 1; i <= n; i++)
            for (j = 1; j <= m; j++)
                process(i, j + (m + 1 - 2 * j) * ((i + 1) % 2));
    }

    public static void diagonalOrder(int n, int m) {
        int d, j;    /* diagonal and point counters */
        int pcount;    /* points on diagonal */
        int height;    /* row of lowest point */

        for (d = 1; d <= (m + n - 1); d++) {
            height = 1 + max(0, d - m);
            pcount = min(d, (n - height + 1));
            for (j = 0; j < pcount; j++)
                process(min(m, d) - j, height + j);
        }
    }

    private static void process(int i, int j) {
        System.out.printf("(%d,%d)\n", i, j);
    }
}

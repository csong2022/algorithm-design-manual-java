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
package com.algorist.sort;

import org.junit.Test;

/**
 * Example search program using sentinels
 *
 * @author csong2022
 */
public class SentinelsTest {
    private static final int MAXINT = 1000000;

    @Test
    public void test() {
        int i, n, x;
        int[] a = new int[100];

        for (i = 1; i <= 20; i++) a[i] = i;
        n = 20;
        x = -1;

        i = n;
        while ((a[i] >= x) && (i >= 1)) {
            a[i + 1] = a[i];
            i = i - 1;
        }
        a[i + 1] = x;

        System.out.printf("without sentinel, i=%d a[1]=%d%n", i, a[1]);
        for (i = 1; i <= 25; i++) System.out.printf("%d ", a[i]);
        x = -2;

        n = 21;
        i = n;
        a[0] = -MAXINT;
        while (a[i] >= x) {
            a[i + 1] = a[i];
            i = i - 1;
        }
        a[i + 1] = x;

        System.out.printf("%nwith sentinel, i=%d a[1]=%d%n", i, a[1]);
        for (i = 1; i <= 25; i++) System.out.printf("%d ", a[i]);
    }
}

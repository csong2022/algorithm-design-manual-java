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
package com.algorist.numerical;

/**
 * Compute the greatest common divisor of two integers.
 *
 * @author csong2022
 */
public class GCD {
    public static long gcd1(long p, long q) {
        if (q > p) return gcd1(q, p);

        if (q == 0) return p;

        System.out.printf(" gcd(%d,%d) &=& gcd(%d \\mod %d, %d) = gcd(%d,%d) \n", p, q, p, q, q, q, p % q);
        return gcd1(q, p % q);
    }

    /*	Find the gcd(p,q) and x,y such that p*x + q*y = gcd(p,q)	*/
    public static long[] gcd(long p, long q) {
        long x, y;
        long x1, y1;   /* previous coefficients */
        long g;        /* value of gcd(p,q) */
        long[] r;

        if (q > p) {
            r = gcd(q, p);
            x = r[1];
            y = r[2];
            return new long[]{r[0], y, x};
        }

        if (q == 0) {
            x = 1;
            y = 0;
            return new long[]{p, x, y};
        }

        r = gcd(q, p % q);
        g = r[0];
        x1 = r[1];
        y1 = r[2];

        x = y1;
        y = (x1 - (p / q) * y1);
        return new long[]{g, x, y};
    }
}

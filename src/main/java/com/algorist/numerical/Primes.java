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
package com.algorist.numerical;

import static java.lang.Math.sqrt;

/**
 * Compute the prime factorization of an integer.
 *
 * @author csong2022
 */
public class Primes {

    public static void primeFactorization(final long x) {
        long c = x;        /* remaining product to factor */
        while ((c % 2) == 0) {
            System.out.println(2);
            c /= 2;
        }

        long i = 3;        /* counter */
        while (i <= (sqrt(c) + 1)) {
            if ((c % i) == 0) {
                System.out.println(i);
                c /= i;
            } else
                i += 2;
        }

        if (c > 1) System.out.println(c);
    }
}

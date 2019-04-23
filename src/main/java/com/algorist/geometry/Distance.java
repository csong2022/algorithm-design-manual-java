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

import static java.lang.Math.sqrt;

/**
 * Compute Euclidian distances.
 *
 * @author csong2022
 */
public class Distance {

    public static double distance(double[] a, double[] b) {
        if (a.length < 1) {
            throw new IllegalArgumentException("a vector is empty.");
        }
        if (b.length < 1) {
            throw new IllegalArgumentException("b vector is empty.");
        }
        if (a.length != b.length) {
            throw new IllegalArgumentException("Two vectors have different dimension.");
        }

        double d = 0.0;

        for (int i = 0; i < a.length; i++)
            d += (a[i] - b[i]) * (a[i] - b[i]);

        return sqrt(d);
    }
}

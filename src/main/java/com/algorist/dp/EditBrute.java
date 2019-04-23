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
package com.algorist.dp;

/**
 * Compute string edit distance *without* dynamic programming!
 *
 * @author csong2022
 */
public class EditBrute extends EditDistance {

    public EditBrute(StringEdit stringEdit) {
        super(stringEdit);
    }

    public int stringCompare(String s, String t, int i, int j) {
        int[] opt = new int[3];        /* cost of the three options */
        int lowest_cost;    /* lowest cost */

        if (i == 0) return j * indel(' ');
        if (j == 0) return i * indel(' ');

        opt[MATCH] = stringCompare(s, t, i - 1, j - 1) + match(s.charAt(i), t.charAt(j));
        opt[INSERT] = stringCompare(s, t, i, j - 1) + indel(t.charAt(j));
        opt[DELETE] = stringCompare(s, t, i - 1, j) + indel(s.charAt(i));

        lowest_cost = opt[MATCH];
        for (int k = INSERT; k <= DELETE; k++)
            if (opt[k] < lowest_cost) lowest_cost = opt[k];

        m[i][j] = new Cell(lowest_cost, -1);    /* REMOVE FROM PRINTED VERSION */

        return lowest_cost;
    }
}

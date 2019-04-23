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

import static com.algorist.dp.EditDistance.DELETE;
import static com.algorist.dp.EditDistance.INSERT;


/**
 * Compute the optimal alignment matching two strings.
 *
 * @author csong2022
 */
public class StringEdit {
    EditDistance.Cell[][] m;

    public void setM(EditDistance.Cell[][] m) {
        this.m = m;
    }

    int[] goalCell(String s, String t) {
        return new int[]{s.length() - 1, t.length() - 1};
    }

    int match(char c, char d) {
        return c == d ? 0 : 1;
    }

    int indel(char c) {
        return 1;
    }

    void rowInit(int i) {        /* what is m[0][i]? */
        if (i > 0)
            m[0][i] = new EditDistance.Cell(i, INSERT);
        else
            m[0][i] = new EditDistance.Cell(i, -1);
    }

    void columnInit(int i) {   /* what is m[i][0]? */
        if (i > 0)
            m[i][0] = new EditDistance.Cell(i, DELETE);
        else
            m[i][0] = new EditDistance.Cell(i, -1);
    }

    void matchOut(String s, String t, int i, int j) {
        if (s.charAt(i) == t.charAt(j)) System.out.print("M");
        else System.out.print("S");
    }

    void insertOut(String t, int j) {
        System.out.print("I");
    }

    void deleteOut(String s, int i) {
        System.out.print("D");
    }
}

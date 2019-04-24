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
 * Approximately match one string as a substring of another, where is s in t?
 *
 * @author csong2022
 */
public class SubStringEdit extends StringEdit {

    int[] goalCell(String s, String t, EditDistance.Cell[][] m) {
        int i = s.length() - 1;
        int j = 0;

        for (int k = 1; k < t.length(); k++)
            if (m[i][k].cost < m[i][j].cost) j = k;

        return new int[]{i, j};
    }

    void rowInit(int i, EditDistance.Cell[][] m) {        /* what is m[0][i]? */
        m[0][i] = new EditDistance.Cell(0, -1);
    }
}

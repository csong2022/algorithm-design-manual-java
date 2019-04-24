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

import static com.algorist.dp.EditDistance.MAXLEN;

/**
 * Longest common subsequence of two strings.
 * <p>
 * Generify from lcs.c.
 *
 * @author csong2022
 */
public class LCS extends StringEdit {

    @Override
    int match(char c, char d) {
        return (c == d) ? 0 : MAXLEN;
    }

    @Override
    void matchOut(String s, String t, int i, int j) {
        if (s.charAt(i) == t.charAt(j)) System.out.printf("%c", s.charAt(i));
    }

    @Override
    void insertOut(String t, int j) {
    }

    @Override
    void deleteOut(String s, int i) {
    }
}

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
package com.algorist.string;

import java.util.Scanner;

/**
 * Corporate name changing program -- string example
 * <p>
 * Translate from name.c.
 *
 * @author csong2022
 */
public class Name {

    public static String[][] readChanges(Scanner scanner) {
        int nmergers = scanner.nextInt();
        String[][] mergers = new String[nmergers][2];

        scanner.nextLine();
        for (int i = 0; i < nmergers; i++) {
            String line = scanner.nextLine();
            mergers[i] = readQuotedStrings(line);
        }

        return mergers;
    }

    private static String[] readQuotedStrings(String s) {
        String[] strings = new String[2];

        int j = 0;        /* counter */
        int start, end;
        for (int i = 0; i < 2; i++) {
            while (j < s.length() && s.charAt(j) != '\"') j++;
            start = j + 1;
            j += 2;
            while (j < s.length() && s.charAt(j) != '\"') j++;
            end = j;
            j += 2;
            strings[i] = s.substring(start, end);
        }

        return strings;
    }


    /**
     * Find pattern in the text.
     *
     * @param p pattern
     * @param t text
     * @return Return the position of the first occurrence of the pattern p
     * in the text t, and -1 if it does not occur.
     */
    private static int findmatch(String p, char[] t, int tlen) {
        int plen = p.length();

        for (int i = 0; i <= (tlen - plen); i = i + 1) {
            int j = 0;
            while ((j < plen) && (t[i + j] == p.charAt(j)))
                j = j + 1;
            if (j == plen) return i;
        }

        return -1;
    }

    /**
     * Replace the substring of length xlen starting at position pos in
     * string s with the contents of string y.
     *
     * @param s    original text.
     * @param slen length of original text.
     * @param pos  starting position;
     * @param xlen original substring length.
     * @param y    new substring.
     * @return length of replaced text.
     */
    private static int replaceXwithY(char[] s, int slen, int pos, int xlen, String y) {
        int i;                /* counter */
        int ylen;            /* lengths of relevant strings */

        ylen = y.length();

        if (xlen >= ylen)
            for (i = (pos + xlen); i <= slen; i++) s[i + (ylen - xlen)] = s[i];
        else
            for (i = slen; i >= (pos + xlen); i--) s[i + (ylen - xlen)] = s[i];

        for (i = 0; i < ylen; i++) s[pos + i] = y.charAt(i);

        return slen + (ylen - xlen);
    }

    static int processMergers(char[] s, int slen, String[][] mergers, int nmergers) {

        int pos;
        for (int j = 0; j < nmergers; j++)
            while ((pos = findmatch(mergers[j][0], s, slen)) != -1) {
                slen = replaceXwithY(s, slen, pos,
                        mergers[j][0].length(), mergers[j][1]);
            }

        return slen;
    }
}

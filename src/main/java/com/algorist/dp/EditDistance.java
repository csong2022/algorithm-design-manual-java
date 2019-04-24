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
 * A generic implementation of string comparison via dynamic programming.
 * <p>
 * Translate from editdistance.h, editdistance.c.
 *
 * @author csong2022
 */
public class EditDistance {
    static final int MAXLEN = 101; /* longest possible string */
    static final int MATCH = 0;    /* enumerated type symbol for match */
    static final int INSERT = 1;   /* enumerated type symbol for insert */
    static final int DELETE = 2;   /* enumerated type symbol for delete */
    final Cell[][] m;              /* dynamic programming table */
    private final StringEdit stringEdit;

    public EditDistance(StringEdit stringEdit) {
        this.m = new Cell[MAXLEN + 1][MAXLEN + 1];
        this.stringEdit = stringEdit;
    }

    public int stringCompare(String s, String t) {
        int[] opt = new int[3];        /* cost of the three options */

        for (int j = 0; j < t.length(); j++) rowInit(j);
        for (int i = 0; i < s.length(); i++) columnInit(i);

        for (int i = 1; i < s.length(); i++)
            for (int j = 1; j < t.length(); j++) {
                opt[MATCH] = m[i - 1][j - 1].cost + match(s.charAt(i), t.charAt(j));
                opt[INSERT] = m[i][j - 1].cost + indel(t.charAt(j));
                opt[DELETE] = m[i - 1][j].cost + indel(s.charAt(i));

                m[i][j] = new Cell(opt[MATCH], MATCH);
                for (int k = INSERT; k <= DELETE; k++)
                    if (opt[k] < m[i][j].cost) {
                        m[i][j].cost = opt[k];
                        m[i][j].parent = k;
                    }
            }

        int[] c = goalCell(s, t);
        return m[c[0]][c[1]].cost;
    }

    public void reconstructPath(String s, String t, int i, int j) {
        switch (m[i][j].parent) {
            case MATCH:
                reconstructPath(s, t, i - 1, j - 1);
                matchOut(s, t, i, j);
                break;

            case INSERT:
                reconstructPath(s, t, i, j - 1);
                insertOut(t, j);
                break;

            case DELETE:
                reconstructPath(s, t, i - 1, j);
                deleteOut(s, i);
                break;

            default:
                break;
        }
    }

    public void printMatrix(String s, String t, boolean costQ) {
        System.out.print("   ");
        for (int i = 0; i < t.length(); i++)
            System.out.printf("  %c", t.charAt(i));
        System.out.println();

        for (int i = 0; i < s.length(); i++) {
            System.out.printf("%c: ", s.charAt(i));
            for (int j = 0; j < t.length(); j++) {
                if (costQ)
                    System.out.printf(" %2d", m[i][j].cost);
                else
                    System.out.printf(" %2d", m[i][j].parent);

            }
            System.out.println();
        }
    }

    private int[] goalCell(String s, String t) {
        return stringEdit.goalCell(s, t, m);
    }

    int match(char c, char d) {
        return stringEdit.match(c, d);
    }

    int indel(char c) {
        return stringEdit.indel(c);
    }

    private void rowInit(int i) {
        stringEdit.rowInit(i, m);
    }

    private void columnInit(int i) {
        stringEdit.columnInit(i, m);
    }

    private void matchOut(String s, String t, int i, int j) {
        stringEdit.matchOut(s, t, i, j);
    }

    private void insertOut(String t, int j) {
        stringEdit.insertOut(t, j);
    }

    private void deleteOut(String s, int i) {
        stringEdit.deleteOut(s, i);
    }

    static class Cell {
        int cost;               /* cost of reaching this cell */
        int parent;             /* parent cell */

        public Cell(int cost, int parent) {
            this.cost = cost;
            this.parent = parent;
        }
    }
}

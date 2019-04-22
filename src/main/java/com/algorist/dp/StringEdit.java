package com.algorist.dp;

import static com.algorist.dp.EditDistance.DELETE;
import static com.algorist.dp.EditDistance.INSERT;


/**
 * Compute the optimal alignment matching two strings.
 */
public class StringEdit {
    EditDistance.Cell[][] m;

    public StringEdit(EditDistance.Cell[][] m) {
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

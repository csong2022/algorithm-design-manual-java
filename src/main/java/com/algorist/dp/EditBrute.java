package com.algorist.dp;

/**
 * Compute string edit distance *without* dynamic programming!
 */
public class EditBrute extends EditDistance {

    public EditBrute(Cell[][] m, StringEdit stringEdit) {
        super(m, stringEdit);
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

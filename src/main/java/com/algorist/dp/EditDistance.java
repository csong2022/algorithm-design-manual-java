package com.algorist.dp;

/**
 * A generic implementation of string comparison via dynamic programming.
 */
public class EditDistance {
    static final int MAXLEN = 101; /* longest possible string */
    static final int MATCH = 0;    /* enumerated type symbol for match */
    static final int INSERT = 1;   /* enumerated type symbol for insert */
    static final int DELETE = 2;   /* enumerated type symbol for delete */

    static class Cell {
        int cost;               /* cost of reaching this cell */
        int parent;             /* parent cell */

        public Cell(int cost, int parent) {
            this.cost = cost;
            this.parent = parent;
        }
    }

    final Cell[][] m;         /* dynamic programming table */
    final StringEdit stringEdit;

    public EditDistance(Cell[][] m, StringEdit stringEdit) {
        this.m = m;
        this.stringEdit = stringEdit;
    }

    public int stringCompare(String s, String t) {
        int[] opt = new int[3];        /* cost of the three options */

        int cols = m[0].length;
        for (int i = 0; i < cols; i++) {
            rowInit(i);
        }

        int rows = m.length;
        for (int i = 0; i < rows; i++) {
            columnInit(i);
        }

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
        /*printf("trace (%d,%d)\n",i,j);*/

        switch (m[i][j].parent) {
            case -1:
                break;
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

    int[] goalCell(String s, String t) {
        return stringEdit.goalCell(s, t);
    }

    int match(char c, char d) {
        return stringEdit.match(c, d);
    }

    int indel(char c) {
        return stringEdit.indel(c);
    }

    void rowInit(int i) {
        stringEdit.rowInit(i);
    }

    void columnInit(int i) {
        stringEdit.columnInit(i);
    }

    void matchOut(String s, String t, int i, int j) {
        stringEdit.matchOut(s, t, i, j);
    }

    void insertOut(String t, int j) {
        stringEdit.insertOut(t, j);
    }

    void deleteOut(String s, int i) {
        stringEdit.deleteOut(s, i);
    }
}

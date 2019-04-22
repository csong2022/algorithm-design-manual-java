package com.algorist.dp;

public class SubStringEdit extends StringEdit {

    int[] goalCell(String s, String t) {
        int i = s.length() - 1;
        int j = 0;

        for (int k = 1; k < t.length(); k++)
            if (m[i][k].cost < m[i][j].cost) j = k;

        return new int[]{i, j};
    }

    void rowInit(int i) {        /* what is m[0][i]? */
        m[0][i] = new EditDistance.Cell(0, -1);
    }
}

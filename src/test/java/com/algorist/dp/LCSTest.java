package com.algorist.dp;

import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.util.Scanner;

public class LCSTest implements TestCaseWithInput {
    public void process(Scanner scanner) {
        String s = ' ' + scanner.next();
        String t = ' ' + scanner.next();

        LCS stringEdit = new LCS();
        EditDistance editDistance = new EditDistance(stringEdit);

        int complen = editDistance.stringCompare(s, t);
        int lcslen = (s.length() + t.length() - 2 - complen) / 2;

        System.out.printf("length of longest common subsequence = %d%n", lcslen);

        int[] p = stringEdit.goalCell(s, t);
        int i = p[0], j = p[1];

        editDistance.reconstructPath(s, t, i, j);
        System.out.println();
    }

    @Test
    public void test() throws Exception {
        TestEngine.execute(this, "stringedit-in", "lcs-out");
    }
}
package com.algorist.dp;

import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.util.Scanner;

public class SubStringEditTest implements TestCaseWithInput {
    public void process(Scanner scanner) {
        String s = ' ' + scanner.next();
        String t = ' ' + scanner.next();

        SubStringEdit stringEdit = new SubStringEdit();
        EditDistance editDistance = new EditDistance(stringEdit);

        System.out.printf("matching cost = %d \n", editDistance.stringCompare(s, t));

        editDistance.printMatrix(s, t, true);
        System.out.println();
        editDistance.printMatrix(s, t, false);

        int[] p = stringEdit.goalCell(s, t);
        int i = p[0], j = p[1];
        System.out.printf("%d %d\n", i, j);

        editDistance.reconstructPath(s, t, i, j);
        System.out.println();
    }

    @Test
    public void test() throws Exception {
        TestEngine.execute(this, "substringedit-in", "substringedit-out");
    }

}
package com.algorist.backtrack;

import com.algorist.test.TestCaseWithoutInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.io.IOException;

import static com.algorist.backtrack.Backtrack.NMAX;

public class NQueensTest implements TestCaseWithoutInput {
    public void process() {
        Backtrack backtrack = new Backtrack();
        NQueens nQueens = new NQueens();

        int[] a = new int[NMAX];            /* solution vector */

        for (int i = 1; i <= 10; i++) {
            nQueens.setSolutionCount(0);
            backtrack.backtrack(a, 0, i, nQueens);
            System.out.printf("n=%d  solutionCount=%d\n", i, nQueens.solutionCount());
        }
    }

    @Test
    public void test() throws IOException {
        TestEngine.execute(this, "8-queens-out");
    }

}
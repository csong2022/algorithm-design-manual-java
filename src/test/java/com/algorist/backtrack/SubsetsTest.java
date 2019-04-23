package com.algorist.backtrack;

import com.algorist.test.TestCaseWithoutInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.io.IOException;

import static com.algorist.backtrack.Backtrack.NMAX;

public class SubsetsTest implements TestCaseWithoutInput {

    @Override
    public void process() {
        int[] a = new int[NMAX];            /* solution vector */

        Subsets subsets = new Subsets();

        Backtrack backtrack = new Backtrack();
        backtrack.backtrack(a, 0, 3, subsets);
    }

    @Test
    public void test() throws IOException {
        TestEngine.execute(this, "subsets-out");
    }
}
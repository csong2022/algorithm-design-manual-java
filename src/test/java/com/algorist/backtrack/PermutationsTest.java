package com.algorist.backtrack;

import com.algorist.test.TestCaseWithoutInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import static com.algorist.backtrack.Backtrack.NMAX;

public class PermutationsTest implements TestCaseWithoutInput {
    @Override
    public void process() {
        int[] a = new int[NMAX];            /* solution vector */

        Permutations permutations = new Permutations();

        Backtrack backtrack = new Backtrack();
        backtrack.backtrack(a, 0, 0, permutations);
        backtrack.backtrack(a, 0, 1, permutations);
        backtrack.backtrack(a, 0, 2, permutations);
        backtrack.backtrack(a, 0, 3, permutations);
    }

    @Test
    public void test() throws Exception {
        TestEngine.execute(this, "permutations-out");
    }
}
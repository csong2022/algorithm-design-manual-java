package com.algorist.leetcodeadapter;


import java.util.*;

import com.algorist.test.TestCaseWithoutInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class SolutionTest implements TestCaseWithoutInput {

    @Test
    public void process() {
        Solution sut = new Solution();
        int[] input = {1,2,3};
        List<List<Integer>> output = sut.subsets(input);
        List<List<Integer>> expected = new ArrayList<>();

        expected.add(Arrays.asList(1,2,3));
        expected.add(Arrays.asList(1,2));
        expected.add(Arrays.asList(1,3));
        expected.add(Arrays.asList(1));
        expected.add(Arrays.asList(2,3));
        expected.add(Arrays.asList(2));
        expected.add(Arrays.asList(3));
        expected.add(Arrays.asList());
        assertEquals(output, expected);
    }


}
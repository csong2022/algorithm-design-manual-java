package com.algorist.datastructure;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SetUnionTest {
    private SetUnion uf;

    @Before
    public void setUp() {
        int[][] pairs = new int[][]{
                {5, 4},
                {4, 9},
                {7, 6},
                {10, 5},
                {3, 2},
                {9, 10},
                {6, 1},
                {8, 3},
                {7, 2},
                {2, 1},
                {7, 8}
        };
        this.uf = new SetUnion(pairs.length);

        for (int i = 0; i < pairs.length; i++) {
            int[] pair = pairs[i];
            this.uf.unionSets(pair[0], pair[1]);
        }
    }

    @Test
    public void test() {
        this.uf.print();

        final int[] c1 = {1, 2, 3, 6, 7, 8};
        assertTrue(this.withinSameComponent(c1));

        final int[] c2 = {4, 5, 9, 10};
        assertTrue(this.withinSameComponent(c2));
    }

    private boolean withinSameComponent(final int[] c1) {
        for (int i = 1; i < c1.length; i++) {
            if (!this.uf.sameComponent(c1[0], c1[i])) {
                return false;
            }
        }
        return true;
    }


}
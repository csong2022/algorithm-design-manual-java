package com.algorist.sort;

import com.algorist.test.TestCaseWithoutInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.io.IOException;

import static com.algorist.numerical.Random.randomPermutation;
import static com.algorist.sort.Sorting.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SortingTest implements TestCaseWithoutInput {
    private static final int NELEM = 100;

    @Override
    public void process() {
        final Integer[] s = new Integer[NELEM];

        initArray(s);
        insertionSort(s, 0, s.length - 1);
        System.out.println("\n\nInsertion sort: ");
        print(s);

        initArray(s);
        selectionSort(s, 0, s.length - 1);
        System.out.println("\n\nSelection sort: ");
        print(s);

        initArray(s);
        quickSort(s, 0, s.length - 1);
        System.out.println("\n\nQuicksort: ");
        print(s);

        initArray(s);
        heapSort(s, 0, s.length - 1);
        System.out.println("\n\nHeapsort sort: ");
        print(s);

        initArray(s);
        mergeSort(s, 0, s.length - 1);
        //System.out.println("\n\nMergesort: ");
        //print(s);

        for (int i = 0; i < s.length; i++) {
            s[i] = 2 * (s.length - i);
        }
        randomPermutation(s);
        heapSort(s, 0, s.length - 1);
        for (int i = 1; i < 2 * s.length + 1; i++) {
            if (i % 2 == 0) {
                assertFalse("" + i, binarySearch(s, i, 0, s.length - 1) == -1);
            } else {
                assertTrue("" + i, binarySearch(s, i, 0, s.length - 1) == -1);
            }
        }
    }

    private void initArray(Integer[] s) {
        for (int i = 0; i < s.length; i++) s[i] = s.length - i;
        randomPermutation(s);
    }

    private void print(Integer[] s) {
        for (int i = 0; i < s.length; i++) System.out.printf("%d ", s[i]);
    }

    @Test
    public void test() throws IOException {
        TestEngine.execute(this, "sorting-out");
    }
}
/*
Copyright 2003 by Steven S. Skiena; all rights reserved.

Permission is granted for use in non-commerical applications
provided this copyright notice remains intact and unchanged.

This program appears in my book:

"Programming Challenges: The Programming Contest Training Manual"
by Steven Skiena and Miguel Revilla, Springer-Verlag, New York 2003.

See our website www.programming-challenges.com for additional information.

This book can be ordered from Amazon.com at

http://www.amazon.com/exec/obidos/ASIN/0387001638/thealgorithmrepo/

*/
package com.algorist.sort;

import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

import static com.algorist.sort.Sorting.quickSort;
import static java.lang.Math.abs;

/**
 * Rank the desirability of suitors -- sorting example.
 *
 * @author csong2022
 */
public class PollyTest implements TestCaseWithInput {
    private static final int NSUITORS = 100; /* maximum number of suitors */
    private static final int BESTHEIGHT = 180; /* best height in centimeters */
    private static final int BESTWEIGHT = 75; /* best weight in kilograms */

    private static Suitor[] readSuitors(final Scanner scanner) {
        final Suitor[] suitors = new Suitor[NSUITORS];
        int height, weight;
        int nsuitors = 0;
        Suitor suitor;
        while (scanner.hasNext()) {
            suitor = new Suitor();
            suitor.first = scanner.next();
            suitor.last = scanner.next();
            height = scanner.nextInt();
            weight = scanner.nextInt();
            suitor.height = abs(height - BESTHEIGHT);
            if (weight > BESTWEIGHT) {
                suitor.weight = weight - BESTWEIGHT;
            } else {
                suitor.weight = -weight;
            }
            suitors[nsuitors] = suitor;
            nsuitors++;
        }

        return Arrays.copyOf(suitors, nsuitors);
    }

    @Override
    public void process(Scanner scanner) {
        final Suitor[] suitors = readSuitors(scanner);

        quickSort(suitors, 0, suitors.length - 1, new SuitorComparator());

        for (final Suitor suitor : suitors) {
            System.out.printf("%s, %s\n", suitor.last, suitor.first);
        }
    }

    @Test
    public void test() throws IOException {
        TestEngine.execute(this, "polly-in", "polly-out");
    }

    private static class Suitor {
        String first;
        String last;
        int height;
        int weight;
    }

    private static class SuitorComparator implements Comparator<Suitor> {
        @Override
        public int compare(final Suitor a, final Suitor b) {
            final int result;            /* result of comparsion */

            if (a.height < b.height) return -1;
            else if (a.height > b.height) return 1;

            if (a.weight < b.weight) return -1;
            else if (a.weight > b.weight) return 1;

            if ((result = a.last.compareTo(b.last)) != 0) return result;

            return a.first.compareTo(b.first);
        }
    }
}

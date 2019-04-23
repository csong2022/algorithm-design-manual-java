package com.algorist.numerical;

import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.util.Scanner;

public class GCDTest implements TestCaseWithInput {
    @Override
    public void process(Scanner scanner) {
        long p, q;
        long x, y, g1, g2;

        while (scanner.hasNext()) {
            p = scanner.nextLong();
            q = scanner.nextLong();
            System.out.printf("gcd of p=%d and q=%d = %d\n", p, q, g1 = GCD.gcd1(p, q));
            long[] r = GCD.gcd(p, q);
            x = r[1];
            y = r[2];
            System.out.printf(" %d*%d + %d*%d = %d\n", p, x, q, y, g2 = r[0]);

            if (g1 != g2) System.out.println("ERROR: GCD");
            if ((p * x + q * y) != g1) System.out.println("ERROR: DIOPHONINE SOLUTION WRONG!");
        }
    }

    @Test
    public void test() throws Exception {
        TestEngine.execute(this, "gcd-in", "gcd-out");
    }
}
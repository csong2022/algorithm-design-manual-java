package com.algorist.dp;

import com.algorist.test.TestCaseWithoutInput;
import com.algorist.test.TestEngine;
import org.junit.Ignore;
import org.junit.Test;

import static com.algorist.dp.Fib.*;

@Ignore
public class FibTest implements TestCaseWithoutInput {
    @Override
    public void process() {
        for (int i = 0; i < MAXN; i++) {
            System.out.printf("fib_c(%d) = %d\n", i, fibCDriver(i));
        }

        for (int i = 0; i < MAXN; i++) {
            System.out.printf("fib_dp(%d) = %d\n", i, fibDp(i));
        }

        for (int i = 0; i < MAXN; i++) {
            System.out.printf("fib_dp2(%d) = %d\n", i, fibDp2(i));
        }

        for (int i = 0; i < MAXN; i++) {
            System.out.printf("fib(%d) = %d\n", i, fibR(i));
        }
    }

    @Test
    public void test() throws Exception {
        TestEngine.execute(this, "fib-out");
    }
}
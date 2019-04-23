package com.algorist.numerical;

import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

import static com.algorist.numerical.Primes.primeFactorization;

public class PrimesTest implements TestCaseWithInput {
    @Override
    public void process(Scanner scanner) {
        long p;
        while (scanner.hasNext()) {
            p = scanner.nextLong();
            System.out.printf("prime factorization of p=%d \n", p);
            primeFactorization(p);
        }
    }

    @Test
    public void test() throws IOException {
        TestEngine.execute(this, "primes-in", "primes-out");
    }
}
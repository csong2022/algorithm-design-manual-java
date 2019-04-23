package com.algorist.numerical;

import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.util.Scanner;

import static com.algorist.numerical.BigNum.*;

public class BigNumTest implements TestCaseWithInput {
    @Override
    public void process(Scanner scanner) {
        int a, b;
        BigNum n1, n2, n3, zero;

        while (scanner.hasNext()) {
            a = scanner.nextInt();
            b = scanner.nextInt();
            System.out.printf("a = %d    b = %d\n", a, b);
            n1 = intToBignum(a);
            n2 = intToBignum(b);

            n3 = add(n1, n2);

            System.out.print("addition -- ");
            n3.print();

            System.out.printf("compare_bignum a ? b = %d\n", compare(n1, n2));

            n3 = subtract(n1, n2);
            System.out.print("subtraction -- ");
            n3.print();

            n3 = multiply(n1, n2);
            System.out.print("multiplication -- ");
            n3.print();

            zero = intToBignum(0);
            if (compare(zero, n2) == 0)
                System.out.println("division -- NaN ");
            else {
                n3 = divide(n1, n2);
                System.out.print("division -- ");
                n3.print();
            }
            System.out.println("--------------------------");
        }
    }

    @Test
    public void test() throws Exception {
        TestEngine.execute(this, "bignum-in", "bignum-out");
    }
}
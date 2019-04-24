/*
Copyright 2003 by Steven S. Skiena; all rights reserved.

Permission is granted for use in non-commercial applications
provided this copyright notice remains intact and unchanged.

This program appears in my book:

"Programming Challenges: The Programming Contest Training Manual"
by Steven Skiena and Miguel Revilla, Springer-Verlag, New York 2003.

See our website www.programming-challenges.com for additional information.

This book can be ordered from Amazon.com at

http://www.amazon.com/exec/obidos/ASIN/0387001638/thealgorithmrepo/

*/
package com.algorist.numerical;

import java.util.Arrays;

import static java.lang.Math.abs;
import static java.lang.Math.max;

/**
 * Implementation of large integer arithmetic: addition, subtraction,
 * multiplication, and division.
 *
 * @author csong2022
 */
public class BigNum {
    private static final int MAXDIGITS = 100;   /* maximum length bignum */

    private static final int PLUS = 1;          /* positive sign bit */
    private static final int MINUS = -1;        /* negative sign bit */

    private final char[] digits;                /* represent the number */
    private int signbit;                        /* 1 if positive, -1 if negative */
    private int lastdigit;                      /* index of high-order digit */

    private BigNum() {
        this.digits = new char[MAXDIGITS];
        for (int i = 0; i < MAXDIGITS; i++) this.digits[i] = (char) 0;
        this.signbit = PLUS;
        this.lastdigit = -1;
    }

    private BigNum(BigNum n) {
        this.digits = Arrays.copyOf(n.digits, n.digits.length);
        this.signbit = n.signbit;
        this.lastdigit = n.lastdigit;
    }

    public static BigNum intToBignum(int s) {
        BigNum n = new BigNum();

        if (s >= 0) n.signbit = PLUS;
        else n.signbit = MINUS;

        int t = abs(s); /* int to work with */

        while (t > 0) {
            n.lastdigit++;
            n.digits[n.lastdigit] = (char) (t % 10);
            t /= 10;
        }

        if (s == 0) n.lastdigit = 0;

        return n;
    }

    /*	c = a +-/* b;	*/

    /**
     * c = a + b
     *
     * @param a 1st operand.
     * @param b 2nd operand.
     * @return sum.
     */
    public static BigNum add(BigNum a, BigNum b) {
        BigNum c = intToBignum(0);

        if (a.signbit == b.signbit) c.signbit = a.signbit;
        else {
            if (a.signbit == MINUS) {
                a.signbit = PLUS;
                c = subtract(b, a);
                a.signbit = MINUS;
            } else {
                b.signbit = PLUS;
                c = subtract(a, b);
                b.signbit = MINUS;
            }
            return c;
        }

        c.lastdigit = max(a.lastdigit, b.lastdigit) + 1;
        int carry = 0;           /* carry digit */

        for (int i = 0; i <= c.lastdigit; i++) {
            c.digits[i] = (char) ((carry + a.digits[i] + b.digits[i]) % 10);
            carry = (carry + a.digits[i] + b.digits[i]) / 10;
        }

        c.zeroJustify();
        return c;
    }

    /**
     * c = a - b
     *
     * @param a 1st operand.
     * @param b 2nd operand.
     * @return difference.
     */
    public static BigNum subtract(BigNum a, BigNum b) {
        BigNum c = intToBignum(0);

        if (a.signbit == MINUS || b.signbit == MINUS) {
            b.signbit = -1 * b.signbit;
            c = add(a, b);
            b.signbit = -1 * b.signbit;
            return c;
        }

        if (compare(a, b) == PLUS) {
            c = subtract(b, a);
            c.signbit = MINUS;
            return c;
        }

        c.lastdigit = max(a.lastdigit, b.lastdigit);
        int borrow = 0;        /* has anything been borrowed? */

        for (int i = 0; i <= c.lastdigit; i++) {
            int v = a.digits[i] - borrow - b.digits[i];  /* placeholder digit */
            if (a.digits[i] > 0)
                borrow = 0;
            if (v < 0) {
                v += 10;
                borrow = 1;
            }

            c.digits[i] = (char) (v % 10);
        }

        c.zeroJustify();
        return c;
    }

    public static int compare(BigNum a, BigNum b) {
        if (a.signbit == MINUS && b.signbit == PLUS) return PLUS;
        if (a.signbit == PLUS && b.signbit == MINUS) return MINUS;

        if (b.lastdigit > a.lastdigit) return PLUS * a.signbit;
        if (a.lastdigit > b.lastdigit) return MINUS * a.signbit;

        for (int i = a.lastdigit; i >= 0; i--) {
            if (a.digits[i] > b.digits[i]) return MINUS * a.signbit;
            if (b.digits[i] > a.digits[i]) return PLUS * a.signbit;
        }

        return 0;
    }

    /**
     * c = a * b
     *
     * @param a 1st operand.
     * @param b 2nd operand.
     * @return product.
     */
    public static BigNum multiply(BigNum a, BigNum b) {
        BigNum tmp;            /* placeholder bignum */

        BigNum c = intToBignum(0);
        BigNum row = new BigNum(a);        /* represent shifted row */

        for (int i = 0; i <= b.lastdigit; i++) {
            for (int j = 1; j <= b.digits[i]; j++) {
                tmp = add(c, row);
                c = tmp;
            }
            row.digitShift(1);
        }

        c.signbit = a.signbit * b.signbit;

        c.zeroJustify();
        return c;
    }

    /**
     * c = a / b
     *
     * @param a 1st operand.
     * @param b 2nd operand.
     * @return division.
     */
    public static BigNum divide(BigNum a, BigNum b) {
        BigNum c = intToBignum(0);

        c.signbit = a.signbit * b.signbit;

        int asign = a.signbit;
        int bsign = b.signbit;

        a.signbit = PLUS;
        b.signbit = PLUS;

        BigNum row = intToBignum(0);     /* represent shifted row */
        BigNum tmp;                      /* placeholder bignum */

        c.lastdigit = a.lastdigit;

        for (int i = a.lastdigit; i >= 0; i--) {
            row.digitShift(1);
            row.digits[0] = a.digits[i];
            c.digits[i] = 0;
            while (compare(row, b) != PLUS) {
                c.digits[i]++;
                tmp = subtract(row, b);
                row = tmp;
            }
        }

        c.zeroJustify();

        a.signbit = asign;
        b.signbit = bsign;

        return c;
    }

    private void zeroJustify() {
        while ((lastdigit > 0) && (digits[lastdigit] == 0))
            lastdigit--;

        if (lastdigit == 0 && digits[0] == 0)
            signbit = PLUS;    /* hack to avoid -0 */
    }

    private void digitShift(int d)        /* multiply n by 10^d */ {
        if (lastdigit == 0 && digits[0] == 0) return;

        //noinspection ManualArrayCopy
        for (int i = lastdigit; i >= 0; i--)
            digits[i + d] = digits[i];

        for (int i = 0; i < d; i++) digits[i] = 0;

        lastdigit += d;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (signbit == MINUS) builder.append("- ");
        for (int i = lastdigit; i >= 0; i--) {
            builder.append((char) ('0' + digits[i]));
        }
        return builder.toString();
    }

    public void print() {
        System.out.println(toString());
    }
}

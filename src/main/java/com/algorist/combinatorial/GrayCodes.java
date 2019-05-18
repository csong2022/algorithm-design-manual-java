package com.algorist.combinatorial;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

/**
 * Generate n-bit Gray Codes.
 * <p>
 * Given a number n, generate bit patterns from 0 to 2^n-1 such that successive patterns differ by one bit.
 * <p>
 * Examples:
 * <p>
 * Following is 2-bit sequence (n = 2)
 * 00 01 11 10
 * <p>
 * Following is 3-bit sequence (n = 3)
 * 000 001 011 010 110 111 101 100
 * <p>
 * And Following is 4-bit sequence (n = 4)
 * 0000 0001 0011 0010 0110 0111 0101 0100 1100 1101 1111
 * 1110 1010 1011 1001 1000
 *
 * @author csong2022
 * @see <a href="https://www.geeksforgeeks.org/given-a-number-n-generate-bit-patterns-from-0-to-2n-1-so-that-successive-patterns-differ-by-one-bit/">Generate n-bit Gray Codes</a>
 */
public class GrayCodes {

    /**
     * n-bit Gray Codes can be generated from list of (n-1)-bit Gray codes using following steps.
     * 1) Let the list of (n-1)-bit Gray codes be L1. Create another list L2 which is reverse of L1.
     * 2) Modify the list L1 by prefixing a ‘0’ in all codes of L1.
     * 3) Modify the list L2 by prefixing a ‘1’ in all codes of L2.
     * 4) Concatenate L1 and L2. The concatenated list is required list of n-bit Gray codes.
     *
     * @param n number of bits.
     * @return n-bit gray codes.
     */
    public static List<String> generateGrayCodes(int n) {
        if (n <= 0) {
            return emptyList();
        }

        List<String> arr = new ArrayList<>();

        // start with 1-bit pattern
        arr.add("0");
        arr.add("1");

        // Every iteration of this loop generates 2 times codes from previously
        // generated i codes.
        int size = 2;
        for (int i = 2; i <= n; i++, size <<= 1) {
            // Enter the previously generated codes again in arr[] in reverse
            // order and prefix with 1. Now arr[] has double number of codes.
            for (int j = size - 1; j >= 0; j--) {
                arr.add("1" + arr.get(j));
            }

            // append 0 to the first half
            for (int j = 0; j < size; j++) {
                arr.set(j, "0" + arr.get(j));
            }
        }
        return arr;
    }
}

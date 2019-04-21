package com.algorist.test;

import java.util.Scanner;

/**
 * Test case takes input, and produce output to stdout.
 *
 * @author csong2022
 */
public interface TestCaseWithInput {
    /**
     * Processes input, executes testing logic, produces output to stdout.
     *
     * @param scanner program input from stdin or file input.
     */
    void process(Scanner scanner);
}

/*
Copyright 2005 by Steven S. Skiena; all rights reserved.

Permission is granted for use in non-commerical applications
provided this copyright notice remains intact and unchanged.

This program appears in my book:

"Programming Challenges: The Programming Contest Training Manual"
by Steven Skiena and Miguel Revilla, Springer-Verlag, New York 2003.

See our website www.programming-challenges.com for additional information.

This book can be ordered from Amazon.com at

http://www.amazon.com/exec/obidos/ASIN/0387001638/thealgorithmrepo/

*/

package com.algorist.datastructure;

import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

/**
 * Driver for a linked list-based container implementation.
 *
 * @author csong2022
 */
public class ListDemoTest implements TestCaseWithInput {
    @Override
    public void process(Scanner scanner) {
        int d;        /* input item */

        List<Integer> l = new List<>(); /* list under construction */

        while (scanner.hasNext()) {
            char c = scanner.next().charAt(0);
            c = Character.toLowerCase(c);
            if (c == 'p')
                l.print();
            if (c == 'i') {
                d = scanner.nextInt();
                System.out.printf("new item: %d%n", d);
                l.insert(d);
            }
            if (c == 's') {
                d = scanner.nextInt();
                if (!l.contains(d))
                    System.out.printf("item %d not found%n", d);
                else
                    System.out.printf("item %d found%n", d);
            }
            if (c == 'd') {
                d = scanner.nextInt();
                if (!l.contains(d))
                    System.out.printf("item to delete %d not found%n", d);
                else
                    System.out.printf("item %d deleted%n", d);
                l.delete(d);
            }
        }
    }

    @Test
    public void test() throws IOException {
        TestEngine.execute(this, "list-in", "list-out");
    }
}
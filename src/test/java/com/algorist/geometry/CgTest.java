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
package com.algorist.geometry;

import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.util.Scanner;

import static com.algorist.geometry.Geometry.*;
import static com.algorist.geometry.Geometry.Line.pointsToLine;
import static com.algorist.geometry.Geometry.Point.readPoint;

/**
 * Driver program for computational geometry routines; give the
 * basic geometric primitives a workout.
 *
 * @author csong2022
 */
public class CgTest implements TestCaseWithInput {

    public void process(Scanner scanner) {
        Point p1, p2, q1, q2, i = new Point(0, 0);
        Line l1, l2;
        Segment s1, s2, s3, s4;

        while (scanner.hasNext()) {
            p1 = readPoint(scanner);
            p2 = readPoint(scanner);
            q1 = readPoint(scanner);
            q2 = readPoint(scanner);

            s1 = new Segment(p1, p2);
            s2 = new Segment(q1, q2);

            l1 = pointsToLine(p1, p2);
            l2 = pointsToLine(q1, q2);

            s1.print();
            s2.print();

            System.out.println("segments_intersect test");
            System.out.printf("%d%n", s1.intersect(s2) ? 1 : 0);

            System.out.println("intersection point");
            Point tmp = l1.intersectionPoint(l2);
            if (tmp != null) {
                i = tmp;
            }
            i.print();

            System.out.println("--------------------------------");
        }
    }

    @Test
    public void test() throws Exception {
        TestEngine.execute(this, "cgtest-in", "cgtest-out");
    }
}

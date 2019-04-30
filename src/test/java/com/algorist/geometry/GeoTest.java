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

import static com.algorist.geometry.Geometry.Line;
import static com.algorist.geometry.Geometry.Line.pointAndSlopeToLine;
import static com.algorist.geometry.Geometry.Line.pointsToLine;
import static com.algorist.geometry.Geometry.Point;
import static com.algorist.geometry.Geometry.Point.readPoint;

/**
 * Driver program for geometry routines.
 *
 * @author csong2022
 */
public class GeoTest implements TestCaseWithInput {

    public void process(Scanner scanner) {
        Point p1, p2, q1, q2, i = new Point(0, 0);
        Line l1, l2, l3, l4;

        while (scanner.hasNext()) {
            p1 = readPoint(scanner);
            p2 = readPoint(scanner);
            q1 = readPoint(scanner);
            q2 = readPoint(scanner);

            p1.print();
            p2.print();
            q1.print();
            q2.print();

            l1 = pointsToLine(p1, p2);
            l2 = pointsToLine(q1, q2);

            l1.print();
            l2.print();

            System.out.println("slope and line tests");
            l3 = pointAndSlopeToLine(p1, -l1.a);
            l3.print();

            l3 = pointAndSlopeToLine(p2, -l1.a);
            l3.print();

            l3 = pointAndSlopeToLine(q1, -l2.a);
            l3.print();

            l3 = pointAndSlopeToLine(q2, -l2.a);
            l3.print();

            System.out.println("parallel lines test");
            System.out.printf("%d%n", l1.parallelQ(l2) ? 1 : 0);

            System.out.println("intersection point");
            Point tmp = l1.intersectionPoint(l2);
            if (tmp != null) {
                i = tmp;
            }
            i.print();

            System.out.println("closest point");
            i = l1.closestPoint(p1);
            i.print();
            i = l1.closestPoint(p2);
            i.print();
            i = l1.closestPoint(q1);
            i.print();
            i = l1.closestPoint(q2);
            i.print();

            i = l2.closestPoint(p1);
            i.print();
            i = l2.closestPoint(p2);
            i.print();
            i = l2.closestPoint(q1);
            i.print();
            i = l2.closestPoint(q2);
            i.print();

            System.out.println("--------------------------------");
        }
    }

    @Test
    public void test() throws Exception {
        TestEngine.execute(this, "geotest-in", "geotest-out");
    }

}
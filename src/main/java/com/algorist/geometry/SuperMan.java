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
package com.algorist.geometry;

import static com.algorist.geometry.Geometry.*;
import static java.lang.Math.acos;
import static java.lang.Math.sqrt;

/**
 * Compute Superman's flight path -- geometry example
 *
 * @author csong2022
 */
public class SuperMan {

    /**
     * Compute Superman's flight path
     *
     * @param s        Superman's initial position
     * @param t        target position
     * @param c        circles
     * @param ncircles number of circles
     */
    public SuperMan(Point s, Point t, Circle[] c, int ncircles) {
        double xray = 0.0;    /* length of intersection with circles */
        double around = 0.0;  /* length around circular arcs */
        double angle;         /* angle subtended by arc */

        Line l = pointsToLine(s, t);     /* line from start to target position */

        for (int i = 1; i <= ncircles; i++) {
            Point close = closestPoint(c[i].c, l);  /* closest point */
            double d = distance(c[i].c, close);     /* distance from circle-center */
            if (d >= 0 && d < c[i].r && pointInBox(close, s, t)) {
                xray += 2 * sqrt(c[i].r * c[i].r - d * d);
                angle = acos(d / c[i].r);
//                around += ((2 * angle) / (2 * PI)) * (2 * PI * c[i].r);
                around += 2 * angle * c[i].r;
                System.out.printf("circle %d (%7.3f,%7.3f,%7.3f) is %7.3f away from l, xray=%7.3f around=%7.3f angle=%7.3f.%n",
                        i, c[i].c.x, c[i].c.y, c[i].r, d, xray, around, angle);
            }
        }

        double travel = distance(s, t) - xray + around; /* total travel distance */
        System.out.printf("Superman sees through %7.3f units, and flies %7.3f units%n",
                xray, travel);
    }
}

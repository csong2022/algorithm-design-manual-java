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

import java.util.Comparator;

import static com.algorist.geometry.Geometry.*;
import static com.algorist.sort.Sorting.quickSort;

/**
 * Compute convex hulls of points in the plane using the Gries/Graham scan algorithm.
 *
 * @author csong2022
 */
public class ConvexHull {

    public static Polygon convex_hull(Point in[], int n, Polygon hull) {
        int i;            /* input counter */
        int top;        /* current hull size */

        if (n <= 3) {        /* all points on hull! */
            for (i = 0; i < n; i++)
                hull.p[i] = in[i];
            hull.n = n;
            return hull;
        }

        n = sort_and_remove_duplicates(in, n);

        Point first_point = in[0];   /* first hull point */

        quickSort(in, 1, n - 1, new SmallerAngle(first_point));


        hull.p[0] = first_point;
        hull.p[1] = in[1];

        //Point.copy(first_point, in[n]);    /* sentinel to avoid special case */
        in[n] = first_point;  /* sentinel to avoid special case */
        top = 1;
        i = 2;

        while (i <= n) {
            if (cw(hull.p[top - 1], hull.p[top], in[i]))
                top--;    /* top not on hull */
            else {
                if (!collinear(hull.p[top - 1], hull.p[top], in[i]))
                    top++;
                hull.p[top] = in[i];
                i++;
            }
        }

        hull.n = top;

        return hull;
    }

    private static int sort_and_remove_duplicates(Geometry.Point in[], int n) {
        int i;                  /* counter */
        int oldn;               /* number of points before deletion */
        int hole;               /* index marked for potential deletion */

        quickSort(in, 0, n - 1, new LeftLower());

        oldn = n;
        hole = 1;
        for (i = 1; i < oldn; i++) {
            if (in[hole - 1].x == in[i].x && in[hole - 1].y == in[i].y) n--;
            else {
                in[hole] = in[i];
                hole++;
            }
        }
        in[hole] = in[oldn - 1];

        return n;
    }

    private static class LeftLower implements Comparator<Point> {
        @Override
        public int compare(Point p1, Point p2) {
            if (p1.x < p2.x) return -1;
            if (p1.x > p2.x) return 1;

            if (p1.y < p2.y) return -1;
            if (p1.y > p2.y) return 1;

            return 0;
        }
    }

    private static class SmallerAngle implements Comparator<Point> {
        private Point first_point;

        public SmallerAngle(Point first_point) {
            this.first_point = first_point;
        }

        @Override
        public int compare(Point p1, Point p2) {
            if (collinear(first_point, p1, p2))
                return (distance(first_point, p1) <= distance(first_point, p2)) ? -1 : 1;
            else
                return (ccw(first_point, p1, p2)) ? -1 : 1;
        }
    }
}

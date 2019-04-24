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

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.*;

/**
 * Basic geometric primitives and data types -- Lines, Circles, Segments
 *
 * @author csong2022
 */
public class Geometry {
    static final double PI = 3.1415926;        /* ratio of circumference to diameter */
    static final int DIMENSION = 2;            /* dimension of points */
    static final int MAXPOLY = 200;            /* maximum number of points in a polygon */
    private static final double EPSILON = 0.000001;    /* a quantity small enough to be zero */

    static Point readPoint(Scanner scanner) {
        return new Point(scanner.nextDouble(), scanner.nextDouble());
    }

    static Point[] readPoints(Scanner scanner) {
        int n = scanner.nextInt();    /*number of points */
        Point[] in = new Point[n];

        for (int i = 0; i < n; i++)
            in[i] = readPoint(scanner);
        return in;
    }

    static Line pointsToLine(Point p1, Point p2) {
        double a, b, c;
        if (p1.x == p2.x) {
            a = 1;
            b = 0;
            c = -p1.x;
        } else {
            b = 1;
            a = -(p1.y - p2.y) / (p1.x - p2.x);
            c = -(a * p1.x) - (b * p1.y);
        }

        return new Line(a, b, c);
    }

    static Line pointAndSlopeToLine(Point p, double m) {
        double a = -m;
        double b = 1;
        double c = -(a * p.x + b * p.y);

        return new Line(a, b, c);
    }

    static boolean parallelQ(Line l1, Line l2) {
        return abs(l1.a - l2.a) <= EPSILON &&
                abs(l1.b - l2.b) <= EPSILON;
    }

    private static boolean sameLineQ(Line l1, Line l2) {
        return parallelQ(l1, l2) && abs(l1.c - l2.c) <= EPSILON;
    }

    static Point intersectionPoint(Line l1, Line l2) {
        if (sameLineQ(l1, l2)) {
            System.out.println("Warning: Identical lines, all points intersect.");
            return new Point(0.0, 0.0);
        }

        if (parallelQ(l1, l2)) {
            System.out.println("Error: Distinct parallel lines do not intersect.");
            return null;
        }

        double x, y;
        x = (l2.b * l1.c - l1.b * l2.c) / (l2.a * l1.b - l1.a * l2.b);

        if (abs(l1.b) > EPSILON)    /* test for vertical line */
            y = -(l1.a * x + l1.c) / l1.b;
        else
            y = -(l2.a * x + l2.c) / l2.b;

        return new Point(x, y);
    }

    static Point closestPoint(Point pIn, Line l) {
        if (abs(l.b) <= EPSILON) {    /* vertical line */
            return new Point(-l.c, pIn.y);
        }

        if (abs(l.a) <= EPSILON) {    /* horizontal line */
            return new Point(pIn.x, -l.c);
        }

        Line perp;        /* perpendicular to l through (x,y) */
        perp = pointAndSlopeToLine(pIn, 1 / l.a); /* non-degenerate line */
        return intersectionPoint(l, perp);
    }

    static double distance(Point a, Point b) {
        double d = (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
        return sqrt(d);
    }

    static boolean pointInBox(Point p, Point b1, Point b2) {
        return ((p.x >= min(b1.x, b2.x)) && (p.x <= max(b1.x, b2.x))
                && (p.y >= min(b1.y, b2.y)) && (p.y <= max(b1.y, b2.y)));
    }

    static boolean segmentsIntersect(Segment s1, Segment s2) {
        Line l1, l2;        /* lines containing the input segments */
        Point p;        /* intersection point */

        l1 = pointsToLine(s1.p1, s1.p2);
        l2 = pointsToLine(s2.p1, s2.p2);

        if (sameLineQ(l1, l2))    /* overlapping or disjoint segments */
            return (pointInBox(s1.p1, s2.p1, s2.p2) ||
                    pointInBox(s1.p2, s2.p1, s2.p2) ||
                    pointInBox(s2.p1, s1.p1, s1.p2) ||
                    pointInBox(s2.p2, s1.p1, s1.p2));

        if (parallelQ(l1, l2)) return false;

        p = intersectionPoint(l1, l2);

        return p != null && pointInBox(p, s1.p1, s1.p2) && pointInBox(p, s2.p1, s2.p2);
    }

    private static double signedTriangleArea(Point a, Point b, Point c) {
        return ((a.x * b.y - a.y * b.x + a.y * c.x
                - a.x * c.y + b.x * c.y - c.x * b.y) / 2.0);
    }

    static double triangleArea(Point a, Point b, Point c) {
        return abs(signedTriangleArea(a, b, c));
    }

    static boolean ccw(Point a, Point b, Point c) {
        return signedTriangleArea(a, b, c) > EPSILON;
    }

    static boolean cw(Point a, Point b, Point c) {
        return signedTriangleArea(a, b, c) < -EPSILON;
    }

    static boolean collinear(Point a, Point b, Point c) {
        return (abs(signedTriangleArea(a, b, c)) <= EPSILON);
    }

    static class Line {
        final double a;        /* x-coefficient */
        final double b;        /* y-coefficient */
        final double c;        /* constant term */

        Line(double a, double b, double c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public void print() {
            System.out.printf("(a=%7.3f,b=%7.3f,c=%7.3f)\n", this.a, this.b, this.c);
        }
    }

    static class Point {
        final double x;
        final double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public Point(Point other) {
            this(other.x, other.y);
        }

        static void print(Point[] p, int n) {
            for (int i = 0; i < n; i++)
                System.out.printf("(%f,%f)\n", p[i].x, p[i].y);
        }

        @Override
        public int hashCode() {
            return (int) (17 * Double.doubleToLongBits(this.x) + Double.doubleToLongBits(this.y));
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof Point) {
                Point b = (Point) other;
                return b.x == this.x && b.y == this.y;
            } else return false;
        }

        void print() {
            System.out.printf("%7.3f %7.3f\n", this.x, this.y);
        }
    }

    static class Polygon {
        final Point[] p;       /* array of points in polygon */
        final int n;            /* number of points in polygon */

        public Polygon(Point[] p) {
            this(p, p.length);
        }

        public Polygon(Point[] p, int n) {
            this.p = p;
            this.n = n;
        }

        Point[] toPints() {
            return Arrays.copyOf(p, n);
        }

        void print() {
            for (int i = 0; i < n; i++)
                System.out.printf("(%f,%f)\n", p[i].x, p[i].y);
        }
    }

    static class Segment {
        final Point p1, p2;    /* endpoints of line segment */

        public Segment(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        void print() {
            System.out.print("segment: ");
            this.p1.print();
            this.p2.print();
        }
    }

    static class Triangle {
        final Point a, b, c;

        public Triangle(Point a, Point b, Point c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    static class Triangulation {
        final int[][] t; /* indices of vertices in triangulation */
        final int n;     /* number of triangles in triangulation */

        public Triangulation(int[][] t, int n) {
            this.t = t;
            this.n = n;
        }
    }

    static class Circle {
        final Point c;         /* center of circle */
        final double r;        /* radius of circle */

        public Circle(Point c, double r) {
            this.c = c;
            this.r = r;
        }
    }

}

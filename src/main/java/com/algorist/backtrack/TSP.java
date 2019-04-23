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
package com.algorist.backtrack;

import java.util.Arrays;
import java.util.Scanner;

import static com.algorist.numerical.Random.randomPermutation;
import static java.lang.Math.sqrt;

/**
 * Heuristics for solving TSP.
 *
 * @author csong2022
 */
public class TSP {

    private static int sq(int x) {
        return x * x;
    }

    static double transition(TspSolution s, TspInstance t, int i, int j) {
        double was, willbe;        /* before and after costs */
        boolean neighbors = false; /* i,j neighboring tour positions? */

        if (i == j) {
            /*printf("Warning: null transition i=%d j=%d\n",i,j);*/
            return 0.0;
        }

        if (i > j) return transition(s, t, j, i);

        if (i == (j - 1)) neighbors = true;

        int tmp;
        if ((i == 1) && (j == s.n)) {
            tmp = i;
            i = j;
            j = tmp;
            neighbors = true;
        }

        if (neighbors) {
            was = distance(s, i - 1, i, t) + distance(s, j, j + 1, t);
        } else {
            was = distance(s, i - 1, i, t) + distance(s, i, i + 1, t)
                    + distance(s, j - 1, j, t) + distance(s, j, j + 1, t);
        }

        tmp = s.p[i];
        s.p[i] = s.p[j];
        s.p[j] = tmp;

        if (neighbors) {
            willbe = distance(s, i - 1, i, t) + distance(s, j, j + 1, t);
        } else {
            willbe = distance(s, i - 1, i, t) + distance(s, i, i + 1, t)
                    + distance(s, j - 1, j, t) + distance(s, j, j + 1, t);
        }

        return willbe - was;
    }

    static double distance(TspSolution s, int x, int y, TspInstance t) {
        int i, j;

        i = x;
        j = y;

        if (i == (t.n + 1)) i = 1;
        if (j == (t.n + 1)) j = 1;

        if (i == 0) i = t.n;
        if (j == 0) j = t.n;

        Point pi = t.p[s.p[i]];
        Point pj = t.p[s.p[j]];
        return sqrt((double) (sq(pi.x - pj.x) + sq(t.p[s.p[i]].y - pj.y)));
    }

    static double solutionCost(TspSolution s, TspInstance t) {
        double cost = distance(s, t.n, 1, t);  /* cost of solution */
        for (int i = 1; i < t.n; i++)
            cost += distance(s, i, i + 1, t);

        return cost;
    }

    static class Point {
        final int x, y;   /* x and y coordinates of point */

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class TspInstance {
        final Point[] p;  /* array of points */
        final int n;      /* how many points in problem? */

        public TspInstance(Point[] p, int n) {
            this.p = p;
            this.n = n;
        }

        static TspInstance read(Scanner scanner) {
            int n = scanner.nextInt();

            Point[] p = new Point[n + 1];
            for (int i = 1; i <= n; i++) {
                int j = scanner.nextInt();
                p[j] = new Point(scanner.nextInt(), scanner.nextInt());
            }

            return new TspInstance(p, n);
        }

        void print() {
            for (int i = 1; i <= this.n; i++)
                System.out.printf("%d %d %d\n", i, p[i].x, p[i].y);
        }
    }

    static class TspSolution {
        final int[] p;    /* array of indices */
        final int n;      /* how many elements in permutation? */

        public TspSolution(int n) {
            this.n = n;
            this.p = new int[n + 1];

            for (int i = 1; i <= n; i++)
                p[i] = i;
        }

        public TspSolution(int[] p, int n) {
            this.p = p;
            this.n = n;
        }

        public TspSolution(TspSolution s) {
            this.n = s.n;
            this.p = Arrays.copyOf(s.p, s.p.length);
        }

        static TspSolution read(Scanner scanner) {
            int n = scanner.nextInt();
            int[] p = new int[n + 1];
            for (int i = 1; i <= n; i++)
                p[i] = scanner.nextInt();
            return new TspSolution(p, n);
        }

        void randomSolution() {
            randomPermutation(p, 1, this.n - 1);
        }

        void print() {
            for (int i = 1; i <= this.n; i++)
                System.out.printf(" %d", p[i]);
            System.out.printf("\n------------------------------------------------------\n");
        }
    }
}

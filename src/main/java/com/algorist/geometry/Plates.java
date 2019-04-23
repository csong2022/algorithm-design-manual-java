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

import static java.lang.Math.sqrt;

/**
 * Compute the number of circles in two different disk packings.
 * Assuming we have an $w \times l$ box, how many unit disks
 * can we pack in there assumming we have w disks on the bottom?
 *
 * @author csong2022
 */
public class Plates {
    /**
     * how many triangular-lattice layers of radius r balls fit in height h?
     */
    static int denseLayers(double w, double h, double r) {
        if ((2 * r) > h) return 0;

        double gap = 2.0 * r * (sqrt(3) / 2.0);  /* distance between layers */
        return 1 + floor((h - 2.0 * r) / gap);
    }

    private static int platesPerRow(int row, double w, double r) {
        int plates_per_full_row;        /* number of plates in full/even row */

        plates_per_full_row = floor(w / (2 * r));

        if ((row % 2) == 0) return plates_per_full_row;

        if (((w / (2 * r)) - plates_per_full_row) >= 0.5)     /* odd row full, too */
            return plates_per_full_row;
        else
            return plates_per_full_row - 1;
    }

    /**
     * How many radius r plates fit in a hexagonal-lattice packed w*h box?
     */
    static int densePlates(double w, double l, double r) {
        int layers = denseLayers(w, l, r);  /* number of layers of balls */

        return ceil(layers / 2.0) * platesPerRow(0, w, r) +
                floor(layers / 2.0) * platesPerRow(1, w, r);
    }

    static int gridPlates(double w, double h, double r) {
        int layers = floor(h / (2 * r)); /* number of layers of balls */

        return layers * platesPerRow(0, w, r);
    }

    /*
     * Hexagonal coordinates start with the center of disk (0,0) at
     * geometric point (0,0).  The hexagonal coordinate $(xh,yh)$
     * refers to the center of the disk on the horizontal row xh
     * and positive-slope diagonal yh.   The geometric coordinate of
     * such a point is a function of the radius of the disk $r$.
     */
    private static double[] hexToGeo(int xh, int yh, double r) {
        double yg = (2.0 * r) * xh * (sqrt(3) / 2.0);
        double xg = (2.0 * r) * xh * (1.0 / 2.0) + (2.0 * r) * yh;
        return new double[]{xg, yg};
    }

    private static double[] geoToHex(double xg, double yg, double r) {
        double xh = (2.0 / sqrt(3)) * yg / (2.0 * r);
        double yh = (xg - (2.0 * r) * (xh) * (1.0 / 2.0)) / (2.0 * r);
        return new double[]{xh, yh};
    }

    /*
     * Under the hexagonal coordinate system, the set of hexagons defined
     * by coordinates (hx,hy), where $0 <= hx <= xmax$ and $0 <= hx <= ymax$
     * forms a diamond-shaped patch, not a conventional axis-oriented
     * rectangle.  To solve this problem, we define array coordinates
     * so that (ax,ay) refers to the position in an axis-oriented
     * rectangle with (0,0) as the lower righthand point in the matrix.
     */
    private static int[] arrayToHex(int xa, int ya) {
        int xh = xa;
        int yh = ya - xa + ceil(xa / 2.0);
        return new int[]{xh, yh};
    }

    private static int[] hexToArray(int xh, int yh) {
        int xa = xh;
        int ya = yh + xh - ceil(xh / 2.0);
        return new int[]{xa, ya};
    }

    static int platesOnTop(int xh, int yh, double w, double l, double r) {
        int number_on_top = 0;        /* total plates on top */
        int layers;            /* number of rows in grid */
        int rowlength;            /* number of plates in row */
        int row;            /* counter */
        int yla, yra;        /* array coordinates */

        layers = denseLayers(w, l, r);

        for (row = xh + 1; row < layers; row++) {
            rowlength = platesPerRow(row, w, r) - 1;

            int[] ret = hexToArray(row, yh - (row - xh));
            yla = ret[1];
            if (yla < 0) yla = 0;            /* left boundary */

            ret = hexToArray(row, yh);
            yra = ret[1];
            if (yra > rowlength) yra = rowlength;    /* right boundary */

            number_on_top += yra - yla + 1;
        }

        return number_on_top;
    }

    static int floor(double d) {
        return (int) d;
    }

    private static int ceil(double d) {
        return (int) (d + 1.0 - 1e-10);
    }
}

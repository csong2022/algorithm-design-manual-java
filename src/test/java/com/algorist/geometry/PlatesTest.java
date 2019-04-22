package com.algorist.geometry;

import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

import static com.algorist.geometry.Plates.*;

public class PlatesTest implements TestCaseWithInput {
    @Override
    public void process(Scanner scanner) {
        double w;            /* box width */
        double l;            /* box length */
        double r;            /* plate radius */

        int i, j;            /* counters */
        int xh, yh, xa, ya;
        double xhf, yhf, xg, yg;
        int xmax, ymax;

        System.out.printf("input box width, box length, and plate radius:\n");
        w = scanner.nextDouble();
        l = scanner.nextDouble();
        r = scanner.nextDouble();
        System.out.printf("box width=%f, box length=%f, and plate radius=%f:\n", w, l, r);

        System.out.printf("dense packing = %d\n", dense_plates(w, l, r));
        System.out.printf("grid packing = %d\n", grid_plates(w, l, r));

        /* print all the possible hexes in the box */

        xmax = floor(w / (2 * r));
        ymax = dense_layers(w, l, r);

        for (i = 0; i < xmax; i++)
            System.out.printf("(0,%d) has %d on top.\n", i, plates_on_top(0, i, w, l, r));
    }

    @Test
    public void testPlates1() throws IOException {
        TestEngine.execute(this, "plates1-in", "plates1-out");
    }

    @Test
    public void testPlates2() throws IOException {
        TestEngine.execute(this, "plates2-in", "plates2-out");
    }

    @Test
    public void testPlates3() throws IOException {
        TestEngine.execute(this, "plates3-in", "plates3-out");
    }
}
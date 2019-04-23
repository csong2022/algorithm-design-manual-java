package com.algorist.geometry;

import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

import static com.algorist.geometry.ConvexHull.convexHull;
import static com.algorist.geometry.Geometry.*;

public class ConvexHullTest implements TestCaseWithInput {
    @Override
    public void process(Scanner scanner) {
        int n = scanner.nextInt();    /*number of points */
        Point[] in = new Point[n + 1];

        for (int i = 0; i < n; i++)
            in[i] = readPoint(scanner);

        Polygon polygon = convexHull(in, n);

        polygon.print();
    }

    @Test
    public void testChin() throws IOException {
        TestEngine.execute(this, "chin", "chin-out");
    }

    @Test
    public void testChin1() throws IOException {
        TestEngine.execute(this, "chin1", "chin1-out");
    }

    @Test
    public void testi2() throws IOException {
        TestEngine.execute(this, "i.2", "i.2-out");
    }

    @Test
    public void testi4() throws IOException {
        TestEngine.execute(this, "i.4", "i.4-out");
    }

    @Test
    public void testi9() throws IOException {
        TestEngine.execute(this, "i.9", "i.9-out");
    }

    @Test
    public void testi10() throws IOException {
        TestEngine.execute(this, "i.10", "i.10-out");
    }

    @Test
    public void testi19() throws IOException {
        TestEngine.execute(this, "i.19", "i.19-out");
    }

    @Test
    public void testConvex_bad_10() throws IOException {
        TestEngine.execute(this, "convex-bad.10", "convex-bad.10-out");
    }

    @Test
    public void testVDError_uniq_dat() throws IOException {
        TestEngine.execute(this, "VDError-uniq.dat", "VDError-uniq.dat-out");
    }

    @Test
    public void testVDError_dat() throws IOException {
        TestEngine.execute(this, "VDError.dat", "VDError-uniq.dat-out");
    }
}
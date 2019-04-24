package com.algorist.geometry;

import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

import static com.algorist.geometry.Geometry.*;

public class SuperManTest implements TestCaseWithInput {
    @Override
    public void process(Scanner scanner) {
        Point s = readPoint(scanner);
        Point t = readPoint(scanner);

        int ncircles = scanner.nextInt();
        Circle[] c = new Circle[ncircles + 1];

        for (int i = 1; i <= ncircles; i++) {
            Point center = readPoint(scanner);
            double r = scanner.nextDouble();
            c[i] = new Circle(center, r);
        }

        System.out.printf("%7.3f %7.3f%n", s.x, s.y);
        System.out.printf("%7.3f %7.3f%n", t.x, t.y);
        System.out.printf("%d%n", ncircles);
        for (int i = 1; i <= ncircles; i++)
            System.out.printf("%7.3f %7.3f %7.3f%n", c[i].c.x, c[i].c.y, c[i].r);

        new SuperMan(s, t, c, ncircles);
    }

    @Test
    public void testSuperin1() throws IOException {
        TestEngine.execute(this, "superin1", "superin1-out");
    }

    @Test
    public void testSuperin2() throws IOException {
        TestEngine.execute(this, "superin2", "superin2-out");
    }

    @Test
    public void testSuperin3() throws IOException {
        TestEngine.execute(this, "superin3", "superin3-out");
    }

    @Test
    public void testSuperin4() throws IOException {
        TestEngine.execute(this, "superin4", "superin4-out");
    }
}
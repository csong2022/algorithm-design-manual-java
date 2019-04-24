package com.algorist.geometry;

import com.algorist.test.TestCaseWithoutInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import static com.algorist.geometry.Distance.distance;

public class DistanceTest implements TestCaseWithoutInput {
    public void process() {
        double[] a = new double[]{6, 2, 3};
        double[] b = new double[]{6, 3, 4};
        System.out.printf("distance = %f%n", distance(a, b));
    }

    @Test
    public void test() throws Exception {
        TestEngine.execute(this, "distance-out");
    }
}
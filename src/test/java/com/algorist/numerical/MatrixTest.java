package com.algorist.numerical;

import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

public class MatrixTest implements TestCaseWithInput {
    @Override
    public void process(Scanner scanner) {
        Matrix a = Matrix.read(scanner);
        a.print();

        Matrix b = Matrix.read(scanner);
        b.print();

        Matrix c = Matrix.multiply(a, b);
        c.print();
    }

    @Test
    public void testMatrixData1() throws IOException {
        TestEngine.execute(this, "matrix-data1", "matrix-data1-out");
    }

    @Test
    public void testMatrixData2() throws IOException {
        TestEngine.execute(this, "matrix-data2", "matrix-data2-out");
    }
}
package com.algorist.geometry;

import com.algorist.test.TestCaseWithoutInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.io.IOException;

import static com.algorist.geometry.Order.*;

public class OrderTest implements TestCaseWithoutInput {
    @Override
    public void process() {
        System.out.printf("row_major\n");
        rowMajor(5, 5);

        System.out.printf("\ncolumn_major\n");
        columnMajor(3, 3);

        System.out.printf("\nsnake_order\n");
        snakeOrder(5, 5);

        System.out.printf("\ndiagonal_order\n");
        diagonalOrder(3, 4);

        System.out.printf("\ndiagonal_order\n");
        diagonalOrder(4, 3);
    }

    @Test
    public void test() throws IOException {
        TestEngine.execute(this, "order-out");
    }
}
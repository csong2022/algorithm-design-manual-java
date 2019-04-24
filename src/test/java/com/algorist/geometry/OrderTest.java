package com.algorist.geometry;

import com.algorist.test.TestCaseWithoutInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.io.IOException;

import static com.algorist.geometry.Order.*;

public class OrderTest implements TestCaseWithoutInput {
    @Override
    public void process() {
        System.out.println("row_major");
        rowMajor(5, 5);

        System.out.println("\ncolumn_major");
        columnMajor(3, 3);

        System.out.println("\nsnake_order");
        snakeOrder(5, 5);

        System.out.println("\ndiagonal_order");
        diagonalOrder(3, 4);

        System.out.println("\ndiagonal_order");
        diagonalOrder(4, 3);
    }

    @Test
    public void test() throws IOException {
        TestEngine.execute(this, "order-out");
    }
}
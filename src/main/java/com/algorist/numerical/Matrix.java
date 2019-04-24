/*
Copyright 2005 by Steven S. Skiena; all rights reserved.

Permission is granted for use in non-commercial applications
provided this copyright notice remains intact and unchanged.

This program appears in my book:

"Programming Challenges: The Programming Contest Training Manual"
by Steven Skiena and Miguel Revilla, Springer-Verlag, New York 2003.

See our website www.programming-challenges.com for additional information.

This book can be ordered from Amazon.com at

http://www.amazon.com/exec/obidos/ASIN/0387001638/thealgorithmrepo/

*/
package com.algorist.numerical;

import java.util.Scanner;

/**
 * Multiply two matrices.
 * <p>
 * Translate from matrix.c.
 *
 * @author csong2022
 */
public class Matrix {
    private final int[][] m;           /* value */
    private final int rows;            /* number of rows */
    private final int columns;         /* number of columns */

    private Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        m = new int[rows + 1][columns + 1];
        for (int i = 1; i <= rows; i++) {
            m[i] = new int[columns + 1];
            for (int j = 1; j <= columns; j++)
                m[i][j] = 0;
        }
    }

    public static Matrix read(Scanner scanner) {
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();

        Matrix matrix = new Matrix(rows, columns);

        for (int i = 1; i <= rows; i++)
            for (int j = 1; j <= columns; j++)
                matrix.m[i][j] = scanner.nextInt();

        return matrix;
    }

    public static Matrix multiply(Matrix a, Matrix b) {
        if (a.columns != b.rows) {
            System.out.println("Error: bounds dont match!");
            return null;
        }

        Matrix c = new Matrix(a.rows, b.columns);

        for (int i = 1; i <= a.rows; i++)
            for (int j = 1; j <= b.columns; j++) {
                c.m[i][j] = 0;
                for (int k = 1; k <= b.rows; k++)
                    c.m[i][j] += a.m[i][k] * b.m[k][j];
            }

        return c;
    }

    public void print() {
        for (int i = 1; i <= this.rows; i++) {
            for (int j = 1; j <= this.columns; j++)
                System.out.printf(" %d", m[i][j]);
            System.out.println();
        }
        System.out.println();
    }
}

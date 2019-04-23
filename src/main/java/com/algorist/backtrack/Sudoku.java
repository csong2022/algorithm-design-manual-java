/*
Copyright 2003 by Steven S. Skiena; all rights reserved.

Permission is granted for use in non-commercial applications
provided this copyright notice remains intact and unchanged.

This program appears in my book:

"Programming Challenges: The Programming Contest Training Manual"
by Steven Skiena and Miguel Revilla, Springer-Verlag, New York 2003.

See our website www.programming-challenges.com for additional information.

This book can be ordered from Amazon.com at

http://www.amazon.com/exec/obidos/ASIN/0387001638/thealgorithmrepo/

*/
package com.algorist.backtrack;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static com.algorist.backtrack.Sudoku.Board.DIMENSION;
import static com.algorist.backtrack.Sudoku.Board.copy;

/**
 * A backtracking program to solve Sudoku.
 *
 * @author csong2022
 */
public class Sudoku implements BacktrackCallback<Sudoku.Board> {

    private final Backtrack backtrack;
    private int steps;                /* how many total move insertions? */

    private Sudoku(Backtrack backtrack) {
        this.backtrack = backtrack;
    }

    public static void main(String[] args) throws IOException {
        int i, j;            /* counters */
        int[] a = new int[DIMENSION * DIMENSION + 1];
        Board board;        /* Seduko board structure */

        final Path inPath = Paths.get("src/test/resources/sudoku-examples/" + args[0]);
        try (final Scanner scanner = new Scanner(Files.newInputStream(inPath))) {
            board = Board.read(scanner);
        }

        board.print();

        Board temp = new Board();
        copy(board, temp);

        Backtrack backtrack = new Backtrack();

        Sudoku sudoku = new Sudoku(backtrack);

        boolean[] speed = {true, false};
        boolean[] intelligence = {true, false};

        for (boolean fast : speed)
            for (boolean smart : intelligence) {

                System.out.println("----------------------------------");
                sudoku.resetSteps();
                Board.copy(temp, board);
                backtrack.setFinished(false);

                backtrack.backtrack(a, 0, board, sudoku);
                /*print_board(&board);*/

                System.out.printf("It took %d steps to find this solution ", sudoku.steps());
                System.out.printf("for fast=%d  smart=%d\n", fast ? 1 : 0, smart ? 1 : 0);
            }
    }

    @Override
    public void processSolution(int[] a, int k, Board board) {
        backtrack.setFinished(true);
        System.out.println("process solution");
        board.print();
    }

    @Override
    public boolean isaSolution(int[] a, int k, Board board) {
        steps++;

        return board.freecount == 0;
    }

    @Override
    public void makeMove(int[] a, int k, Board board) {
        Point kthMove = board.move[k];
        board.fillSquare(kthMove.x, kthMove.y, a[k]);
    }

    @Override
    public void unmakeMove(int[] a, int k, Board board) {
        Point kthMove = board.move[k];
        board.freeSquare(kthMove.x, kthMove.y);
    }

    @Override
    public int constructCandidates(int[] a, int k, Board board, int[] c) {
        boolean[] possible = new boolean[DIMENSION + 1];     /* what is possible for the square */

        Point p = board.nextSquare();    /* which square should we fill next? */
        board.move[k] = p;           /* store our choice of next position */

        if (p.x < 0 && p.y < 0) return 0;    /* error condition, no moves possible */

        board.possibleValues(p.x, p.y, possible);

        int ncandidates = 0;
        for (int i = 1; i <= DIMENSION; i++) {
            if (possible[i]) {
                c[ncandidates] = i;
                ncandidates++;
            }
        }

        return ncandidates;
    }

    private void resetSteps() {
        this.steps = 0;
    }

    private int steps() {
        return this.steps;
    }

    static class Point {
        final int x, y;                /* x and y coordinates of point */

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Board {
        static final int BASED = 3;                       /* base dimension, 3*3 blocks */
        static final int DIMENSION = BASED * BASED;       /* 9*9 board */
        static final int NCELLS = DIMENSION * DIMENSION;  /* 81 cells in a 9*9 problem */

        final int[][] m;          /* matrix of board contents */
        final Point[] move;
        int freecount;            /* how many open squares remain? */
        boolean fast;             /* fast or slow nextmove algorithm? */
        boolean smart;            /* quickly test for unfillable squares?*/

        Board() {
            this.m = new int[DIMENSION + 1][DIMENSION + 1];
            for (int i = 0; i <= DIMENSION; i++) {
                this.m[i] = new int[DIMENSION + 1];
            }
            this.move = new Point[NCELLS + 1];

            for (int i = 0; i < DIMENSION; i++)
                for (int j = 0; j < DIMENSION; j++)
                    m[i][j] = 0;
            freecount = DIMENSION * DIMENSION;
        }

        static Board read(Scanner scanner) {
            char c;
            int value;

            Board board = new Board();

            for (int i = 0; i < DIMENSION; i++) {
                String line = scanner.next();
                for (int j = 0; j < DIMENSION; j++) {
                    c = line.charAt(j);
                    value = c - '0';
                    if (value != 0)
                        board.fillSquare(i, j, value);
                }
                //scanner.next(); /*newline*/
            }

            return board;
        }

        static void copy(Board a, Board b) {
            b.freecount = a.freecount;

            for (int i = 0; i < DIMENSION; i++)
                for (int j = 0; j < DIMENSION; j++)
                    b.m[i][j] = a.m[i][j];
        }

        void possibleValues(int x, int y, boolean[] possible) {
            /* is anything/everything possible? */
            boolean init = x >= 0 && y >= 0 && this.m[x][y] == 0;

            for (int i = 1; i <= DIMENSION; i++) possible[i] = init;

            for (int i = 0; i < DIMENSION; i++)
                if (m[x][i] != 0) possible[m[x][i]] = false;

            for (int i = 0; i < DIMENSION; i++)
                if (m[i][y] != 0) possible[m[i][y]] = false;

            int xlow = BASED * (x / BASED); /* origin of box with (x,y) */
            int ylow = BASED * (y / BASED);

            for (int i = xlow; i < xlow + BASED; i++)
                for (int j = ylow; j < ylow + BASED; j++)
                    if (m[i][j] != 0) possible[m[i][j]] = false;
        }

        public void printPossible(boolean[] possible) {
            for (int i = 0; i <= DIMENSION; i++)
                if (possible[i]) System.out.printf(" %d", i);
            System.out.println();
        }

        int possibleCount(int x, int y) {
            boolean[] possible = new boolean[DIMENSION + 1];     /* what is possible for the square */

            possibleValues(x, y, possible);
            int cnt = 0;        /* number of open squares */
            for (int i = 0; i <= DIMENSION; i++)
                if (possible[i]) cnt++;
            return cnt;
        }

        void fillSquare(int x, int y, int v) {
            if (this.m[x][y] == 0)
                this.freecount--;
            else
                System.out.printf("Warning: filling already filled square (%d,%d)\n", x, y);

            this.m[x][y] = v;
        }

        void freeSquare(int x, int y) {
            if (this.m[x][y] != 0)
                this.freecount++;
            else
                System.out.printf("Warning: freeing already empty square (%d,%d)\n", x, y);

            this.m[x][y] = 0;
        }

        Point nextSquare() {
            int bestcnt = DIMENSION + 1; /* the best square counts */
            boolean doomed = false;            /* some empty square without moves? */

            int x = -1, y = -1;
            for (int i = 0; i < DIMENSION; i++) {
                for (int j = 0; j < DIMENSION; j++) {
                    int newcnt = possibleCount(i, j); /* latest square counts */
                    if (newcnt == 0 && m[i][j] == 0)
                        doomed = true;
                    if (fast) {
                        if (newcnt < bestcnt && newcnt >= 1) {
                            bestcnt = newcnt;
                            x = i;
                            y = j;
                        }

                    } else {
                        if (newcnt >= 1 && m[i][j] == 0) {
                            x = i;
                            y = j;
                        }
                    }
                }
            }

            if (doomed && smart) {
                x = y = -1;        /* initialize to non-position */
            }

            return new Point(x, y);
        }

        void print() {
            System.out.printf("\nThere are %d free board positions.\n", freecount);

            for (int i = 0; i < DIMENSION; i++) {
                for (int j = 0; j < DIMENSION; j++) {
                    if (m[i][j] == 0)
                        System.out.print(" ");
                    else
                        System.out.printf("%c", (char) ('0' + m[i][j]));
                    if ((j + 1) % BASED == 0)
                        System.out.print("|");
                }
                System.out.println();
                if ((i + 1) % BASED == 0) {
                    for (int j = 0; j < (DIMENSION + BASED - 1); j++)
                        System.out.print("-");
                    System.out.println();
                }
            }
        }
    }
}

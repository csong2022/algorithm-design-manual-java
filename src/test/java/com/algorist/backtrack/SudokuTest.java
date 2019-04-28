package com.algorist.backtrack;

import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.util.Scanner;

import static com.algorist.backtrack.Sudoku.Board.DIMENSION;
import static com.algorist.backtrack.Sudoku.Board.copy;

public class SudokuTest implements TestCaseWithInput {
    @Override
    public void process(Scanner scanner) {
        int[] a = new int[DIMENSION * DIMENSION + 1];
        Sudoku.Board board = Sudoku.Board.read(scanner); /* Sudoku board structure */
        board.print();

        Sudoku.Board temp = new Sudoku.Board();
        copy(board, temp);

        Backtrack backtrack = new Backtrack();
        boolean[] speed = {true, false};
        boolean[] intelligence = {true, false};

        for (boolean fast : speed)
            for (boolean smart : intelligence) {
                Sudoku sudoku = new Sudoku(backtrack, fast, smart);

                System.out.println("----------------------------------");
                sudoku.resetSteps();
                Sudoku.Board.copy(temp, board);
                backtrack.setFinished(false);

                backtrack.backtrack(a, 0, board, sudoku);
                /*print_board(&board);*/

                System.out.printf("It took %d steps to find this solution ", sudoku.steps());
                System.out.printf("for fast=%d  smart=%d%n", fast ? 1 : 0, smart ? 1 : 0);
            }
    }

    @Test
    public void test() throws Exception {
        TestEngine.execute(this, "puzzle", "puzzle-out");
    }
}
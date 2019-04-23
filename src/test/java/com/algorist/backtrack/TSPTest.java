package com.algorist.backtrack;

import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

import static com.algorist.backtrack.TSP.solution_cost;

@Ignore
public class TSPTest implements TestCaseWithInput {

    @Override
    public void process(Scanner scanner) {
        TSP.TspInstance t;            /* tsp points */
        TSP.TspSolution s;            /* tsp solution */

        t = TSP.TspInstance.read(scanner);

        s = TSP.TspSolution.read(scanner);
        System.out.printf("OPTIMAL SOLUTION COST = %7.1f\n", solution_cost(s, t));
        s.print();

        s = new TSP.TspSolution(t.n);
        System.out.printf("solution_cost = %7.1f\n", solution_cost(s, t));
        s.print();

        Annealing annealing = new Annealing();
        s = annealing.repeated_annealing(t, 3);
        System.out.printf("repeated annealing %d iterations, cost = %7.1f\n",
                annealing.solution_count(), solution_cost(s, t));
        s.print();
    }

    @Test
    public void test() throws IOException {
        TestEngine.execute(this, "tsp48-in", "tsp48-out");
    }
}
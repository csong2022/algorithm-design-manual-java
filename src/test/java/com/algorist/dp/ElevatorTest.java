package com.algorist.dp;

import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.util.Scanner;

import static com.algorist.dp.Elevator.MAX_RIDERS;

public class ElevatorTest implements TestCaseWithInput {
    public void process(Scanner scanner) {
        int nriders = scanner.nextInt();
        int nstops = scanner.nextInt();

        int[] stops = new int[MAX_RIDERS];
        for (int i = 1; i <= nriders; i++)
            stops[i] = scanner.nextInt();

        Elevator elevator = new Elevator(stops, nstops);

        for (int i = 1; i <= nriders; i++)
            System.out.printf("%d%n", elevator.stops[i]);

        int laststop = elevator.optimizeFloors();

        elevator.printCostTable();
        System.out.println();
        elevator.printParentTable();

        System.out.printf("cost = %d%n", elevator.m[laststop][nstops]);

        elevator.reconstructPath(laststop, nstops);
    }

    @Test
    public void test() throws Exception {
        TestEngine.execute(this, "elevator-in", "elevator-out");
    }
}

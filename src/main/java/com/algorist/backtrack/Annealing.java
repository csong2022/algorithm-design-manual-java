/*
Copyright 2003 by Steven S. Skiena; all rights reserved.

Permission is granted for use in non-commerical applications
provided this copyright notice remains intact and unchanged.

This program appears in my book:

"Programming Challenges: The Programming Contest Training Manual"
by Steven Skiena and Miguel Revilla, Springer-Verlag, New York 2003.

See our website www.programming-challenges.com for additional information.

This book can be ordered from Amazon.com at

http://www.amazon.com/exec/obidos/ASIN/0387001638/thealgorithmrepo/

*/
package com.algorist.backtrack;

import static com.algorist.backtrack.TSP.*;
import static com.algorist.numerical.Random.randomFloat;
import static com.algorist.numerical.Random.randomInt;
import static java.lang.Math.pow;

/**
 * Simulated Annealing Implementation.
 *
 * @author csong2022
 */
public class Annealing {
    private static final boolean TRACE_OUTPUT = false;        /* print the swaps as they happen */
    /* how often we report progress */
    private static final int PRINT_FREQUENCY = 10000;

    /* start temperature -- probably  leave intact */
    private static final int INITIAL_TEMPERATURE = 1;

    /* how many times do we cool -- make higher to improve quality, lower to
       speed the program up.  Move in tandem with the COOLING_FRACTION */
    private static final int COOLING_STEPS = 500;

    /* how much to cool each time -- make higher to improve quality, lower to
	   speed the program up. */
    private static final double COOLING_FRACTION = 0.97;

    /* lower makes it faster, higher makes it potentially better. */
    private static final int STEPS_PER_TEMP = 1000;

    /* number e -- probably leave intact*/
    private static final double E = 2.718;

    /* problem specific Boltzman's constant. May have to adjust if your global
	   value function changes the sizes of the numbers it produces.  It is
	   important that jumps seem random at the begining of the run, and rare
	   at the end of a run, and this is a knob to tweak that. */
    private static final double K = 0.01;

    private int solutionCount = 0;

    public int solutionCount() {
        return this.solutionCount;
    }

    private void solutionCountUpdate(TspSolution s, TspInstance t) {
        // double solutionCost();

        solutionCount++;
        if ((solutionCount % PRINT_FREQUENCY) == 0)
            System.out.printf("%d %7.1f%n", solutionCount, solutionCost(s, t));
    }

    /**
     * Use random sampling to provide a heuristic solution to a given
     * optimization problem.
     */
    TspSolution randomSampling(TspInstance t, int nsamples) {
        TspSolution s = new TspSolution(t.n);       /* current tsp solution */
        double bestCost = solutionCost(s, t);     /* best cost so far */
        TspSolution bestsol = new TspSolution(s);

        for (int i = 1; i <= nsamples; i++) {
            s.randomSolution();
            double cost_now = solutionCost(s, t);  /* current cost */
            if (cost_now < bestCost) {
                bestCost = cost_now;
                bestsol = new TspSolution(s);
            }
            solutionCountUpdate(s, t);
        }

        return bestsol;
    }

    /**
     * Use hill climbing to provide a heuristic solution to a given
     * optimization problem.
     */
    private TspSolution hillClimbing(TspInstance t) {
        TspSolution s = new TspSolution(t.n);
        s.randomSolution();
        double cost = solutionCost(s, t); /* best cost so far */

        boolean stuck;            /* did I get a better solution? */
        do {
            stuck = true;
            for (int i = 1; i < t.n; i++)
                for (int j = i + 1; j <= t.n; j++) {
                    double delta = transition(s, t, i, j);   /* swap cost */
                    if (delta < 0) {
                        stuck = false;
                        cost += delta;

                    } else
                        transition(s, t, j, i);

                    solutionCountUpdate(s, t);
                }
        } while (!stuck);

        return s;
    }

    /*	These routines implement simulated annealing.  Pairs of components
	of the same type will be swapped at random, and the new arrangment
	accepted either if (1) it is an improvement, or (2) the penalty is
	less than a random number, which is a function of the temperature
	of the system.

	We are seeking to *minimize* the current_value.
*/

    TspSolution repeatedHillClimbing(TspInstance t, int nsamples) {
        TspSolution s = new TspSolution(t.n); /* current tsp solution */
        double bestCost = solutionCost(s, t);  /* best cost so far */
        TspSolution bestsol = new TspSolution(s);

        for (int i = 1; i <= nsamples; i++) {
            s = hillClimbing(t);
            double cost_now = solutionCost(s, t);  /* current cost */
            if (cost_now < bestCost) {
                bestCost = cost_now;
                bestsol = new TspSolution(s);
            }
        }

        return bestsol;
    }

    private TspSolution anneal(TspInstance t) {
        double temperature = INITIAL_TEMPERATURE;  /* the current system temp */

        TspSolution s = new TspSolution(t.n);
        double currentValue = solutionCost(s, t);  /* value of current state */

        for (int i = 1; i <= COOLING_STEPS; i++) {
            temperature *= COOLING_FRACTION;

            double startValue = currentValue;      /* value at start of loop */

            for (int j = 1; j <= STEPS_PER_TEMP; j++) {

                /* pick indices of elements to swap */
                int i1 = randomInt(1, t.n);
                int i2 = randomInt(1, t.n);

                double delta = transition(s, t, i1, i2);  /* value after swap */

                double flip = randomFloat();
                /* exponent for energy funct*/
                double exponent = (-delta / currentValue) / (K * temperature);
                double merit = pow(E, exponent);

                if (delta < 0) {    /*ACCEPT-WIN*/
                    currentValue = currentValue + delta;

                    if (TRACE_OUTPUT) {
                        System.out.printf("swap WIN %d--%d value %f  temp=%f i=%d j=%d\n",
                                i1, i2, currentValue, temperature, i, j);
                    }
                } else {
                    if (merit > flip) {        /*ACCEPT-LOSS*/
                        currentValue = currentValue + delta;
                        if (TRACE_OUTPUT) {
                            System.out.printf("swap LOSS %d--%d value %f merit=%f flip=%f i=%d j=%d\n",
                                    i1, i2, currentValue, merit, flip, i, j);
                        }
                    } else {                /* REJECT */
                        transition(s, t, i1, i2);
                    }
                }
                solutionCountUpdate(s, t);
            }
            if ((currentValue - startValue) < 0.0) { /* rerun at this temp */
                temperature /= COOLING_FRACTION;
                if (TRACE_OUTPUT) System.out.printf("rerun at temperature %f\n", temperature);
            }
        }

        return s;
    }

    public TspSolution repeatedAnnealing(TspInstance t, int nsamples) {
        TspSolution s = new TspSolution(t.n);  /* current tsp solution */
        double bestCost = solutionCost(s, t); /* best cost so far */
        TspSolution bestsol = new TspSolution(s);

        for (int i = 1; i <= nsamples; i++) {
            s = anneal(t);
            double costNow = solutionCost(s, t); /* current cost */
            if (costNow < bestCost) {
                bestCost = costNow;
                bestsol = new TspSolution(s);
            }
        }

        return bestsol;
    }

}

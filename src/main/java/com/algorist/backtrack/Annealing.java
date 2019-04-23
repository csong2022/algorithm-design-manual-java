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

    private int solution_count = 0;

    public int solution_count() {
        return this.solution_count;
    }

    void solution_count_update(TspSolution s, TspInstance t) {
        // double solution_cost();

        solution_count++;
        if ((solution_count % PRINT_FREQUENCY) == 0)
            System.out.printf("%d %7.1f\n", solution_count, solution_cost(s, t));
    }

    /**
     * Use random sampling to provide a heuristic solution to a given
     * optimization problem.
     */
    TspSolution random_sampling(TspInstance t, int nsamples) {
        TspSolution s = new TspSolution(t.n);       /* current tsp solution */
        double best_cost = solution_cost(s, t);     /* best cost so far */
        TspSolution bestsol = new TspSolution(s);

        for (int i = 1; i <= nsamples; i++) {
            s.random_solution();
            double cost_now = solution_cost(s, t);  /* current cost */
            if (cost_now < best_cost) {
                best_cost = cost_now;
                bestsol = new TspSolution(s);
            }
            solution_count_update(s, t);
        }

        return bestsol;
    }

    /**
     * Use hill climbing to provide a heuristic solution to a given
     * optimization problem.
     */
    TspSolution hill_climbing(TspInstance t) {
        TspSolution s = new TspSolution(t.n);
        s.random_solution();
        double cost = solution_cost(s, t); /* best cost so far */

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

                    solution_count_update(s, t);
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

    TspSolution repeated_hill_climbing(TspInstance t, int nsamples) {
        TspSolution s = new TspSolution(t.n); /* current tsp solution */
        double best_cost = solution_cost(s, t);  /* best cost so far */
        TspSolution bestsol = new TspSolution(s);

        for (int i = 1; i <= nsamples; i++) {
            s = hill_climbing(t);
            double cost_now = solution_cost(s, t);  /* current cost */
            if (cost_now < best_cost) {
                best_cost = cost_now;
                bestsol = new TspSolution(s);
            }
        }

        return bestsol;
    }

    TspSolution anneal(TspInstance t) {
        int i1, i2;                /* pair of items to swap */
        int i, j;                /* counters */
        double temperature;            /* the current system temp */
        double current_value;            /* value of current state */
        double start_value;            /* value at start of loop */
        double delta;                /* value after swap */
        double merit, flip;            /* hold swap accept conditions*/
        double exponent;            /* exponent for energy funct*/

        temperature = INITIAL_TEMPERATURE;

        TspSolution s = new TspSolution(t.n);
        current_value = solution_cost(s, t);

        for (i = 1; i <= COOLING_STEPS; i++) {
            temperature *= COOLING_FRACTION;

            start_value = current_value;

            for (j = 1; j <= STEPS_PER_TEMP; j++) {

                /* pick indices of elements to swap */
                i1 = randomInt(1, t.n);
                i2 = randomInt(1, t.n);

                delta = transition(s, t, i1, i2);

                flip = randomFloat();
                exponent = (-delta / current_value) / (K * temperature);
                merit = pow(E, exponent);

                if (delta < 0) {    /*ACCEPT-WIN*/
                    current_value = current_value + delta;

                    if (TRACE_OUTPUT) {
                        System.out.printf("swap WIN %d--%d value %f  temp=%f i=%d j=%d\n",
                                i1, i2, current_value, temperature, i, j);
                    }
                } else {
                    if (merit > flip) {        /*ACCEPT-LOSS*/
                        current_value = current_value + delta;
                        if (TRACE_OUTPUT) {
                            System.out.printf("swap LOSS %d--%d value %f merit=%f flip=%f i=%d j=%d\n",
                                    i1, i2, current_value, merit, flip, i, j);
                        }
                    } else {                /* REJECT */
                        transition(s, t, i1, i2);
                    }
                }
                solution_count_update(s, t);
            }
            if ((current_value - start_value) < 0.0) { /* rerun at this temp */
                temperature /= COOLING_FRACTION;
                if (TRACE_OUTPUT) System.out.printf("rerun at temperature %f\n", temperature);
            }
        }

        return s;
    }

    TspSolution repeated_annealing(TspInstance t, int nsamples) {
        TspSolution s;                 /* current tsp solution */
        double best_cost;               /* best cost so far */
        double cost_now;                /* current cost */
        int i;                          /* counter */

        s = new TspSolution(t.n);
        best_cost = solution_cost(s, t);
        TspSolution bestsol = new TspSolution(s);

        for (i = 1; i <= nsamples; i++) {
            s = anneal(t);
            cost_now = solution_cost(s, t);
            if (cost_now < best_cost) {
                best_cost = cost_now;
                bestsol = new TspSolution(s);
            }
        }

        return bestsol;
    }

}

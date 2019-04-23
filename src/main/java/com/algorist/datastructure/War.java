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
package com.algorist.datastructure;

import static com.algorist.numerical.Random.randomPermutation;

/**
 * Simulation of the children's card game War
 * <p>
 * Read in cards with format value, suit, e.g. 4h ranked by orders 23456789TJQKA and cdhs
 *
 * @author csong2022
 */
public class War {
    private static final int MAXSTEPS = 100000;
    private static final int NCARDS = 52;    /* number of cards */
    private static final int NSUITS = 4;     /* number of suits */
    private static final char[] values = "23456789TJQKA".toCharArray();
    private static final char[] suits = "cdhs".toCharArray();

    /**
     * Rank the card with given value and suit.
     *
     * @param value value of the card.
     * @param suit  suit of the card.
     * @return rank of the card.
     */
    static int rankCard(char value, char suit) {
        for (int i = 0; i < (NCARDS / NSUITS); i++)
            if (values[i] == value)
                for (int j = 0; j < NSUITS; j++)
                    if (suits[j] == suit)
                        return (i * NSUITS + j);

        System.out.printf("Warning: bad input value=%c, suit=%c\n", value, suit);
        return -1;
    }

    /**
     * Return the suit of the given card.
     *
     * @param card rank of the card.
     * @return suit of the card.
     */
    private static char suit(int card) {
        return suits[card % NSUITS];
    }

    /**
     * Return the value of the given card.
     *
     * @param card rank of the card.
     * @return value of the card.
     */
    private static char value(int card) {
        return values[card / NSUITS];
    }

    static void testcards() {
        for (int i = 0; i < NCARDS; i++)
            System.out.printf(" i=%d card[i]=%c%c rank=%d\n", i, value(i),
                    suit(i), rankCard(value(i), suit(i)));
    }

    static Queue[] randomInitDecks() {
        int[] perm = new int[NCARDS + 1];

        for (int i = 0; i < NCARDS; i = i + 1) {
            perm[i] = i;
        }

        randomPermutation(perm, 0, perm.length - 1);

        Queue<Integer> a = new Queue<>();
        Queue<Integer> b = new Queue<>();

        for (int i = 0; i < NCARDS / 2; i = i + 1) {
            a.enqueue(perm[2 * i]);
            b.enqueue(perm[2 * i + 1]);
        }

        printCardQueue(a);
        printCardQueue(b);

        return new Queue[]{a, b};
    }

    static void war(final Queue<Integer> a, final Queue<Integer> b) {
        int steps = 0;            /* step counter */
        Integer x, y;            /* top cards */
        Queue<Integer> c;            /* cards involved in the war */
        boolean inwar;            /* are we involved in a war? */

        inwar = false;
        c = new Queue<>();

        /*printf("deck counts a=%d b=%d\n",a->count,b->count);*/


        while (!a.isEmpty() && (!b.isEmpty() && steps < MAXSTEPS)) {
            steps++;
            x = a.dequeue();
            y = b.dequeue();
            c.enqueue(x);
            c.enqueue(y);
            if (inwar) {
                inwar = false;
            } else {
                if (value(x) > value(y))
                    clearQueue(c, a);
                else if (value(x) < value(y))
                    clearQueue(c, b);
                else if (value(y) == value(x))
                    inwar = true;
            }
        }

        /*printf("deck counts a=%d b=%d\n",a->count,b->count);*/

        if (!a.isEmpty() && b.isEmpty())
            System.out.printf("a wins in %d steps \n", steps);
        else if (a.isEmpty() && !b.isEmpty())
            System.out.printf("b wins in %d steps \n", steps);
        else if (!a.isEmpty() && !b.isEmpty())
            System.out.printf("game tied after %d steps, |a|=%d |b|=%d \n",
                    steps, a.size(), b.size());
        else
            System.out.printf("a and b tie in %d steps \n", steps);
    }

    private static void printCardQueue(Queue<Integer> q) {
        int i, j;

        for (int value : q) {
            System.out.printf("%c%c ", value(value), suit(value));
        }

        System.out.println();
    }

    private static void clearQueue(Queue<Integer> a, Queue<Integer> b) {
        /*printf("war ends with %d cards \n",a->count);*/
        while (!a.isEmpty())
            b.enqueue(a.dequeue());
    }
}

package com.algorist.datastructure;

import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

public class WarTest implements TestCaseWithInput {
    @Override
    public void process(Scanner scanner) {
        Queue<Integer>[] decks = new Queue[2]; /* player's decks */
        char value, suit, c;                   /* input characters */

        int lineno = 1;
        War war = new War();
        while (true) {
            for (int i = 0; i <= 1; i++) {
                decks[i] = new Queue<>();

                if (!scanner.hasNext()) return;
                System.err.printf("Read line #%d\n", lineno++);
                String line = scanner.nextLine();
                int j = 0;
                while (j < line.length()) {
                    c = line.charAt(j++);
                    if (c != ' ') {
                        value = c;
                        suit = line.charAt(j++);
                        decks[i].enqueue(war.rank_card(value, suit));
                    }
                }
            }

            war.war(decks[0], decks[1]);
        }
    }

    @Test
    public void test() throws IOException {
        TestEngine.execute(this, "war-in", "war-out");
    }
}
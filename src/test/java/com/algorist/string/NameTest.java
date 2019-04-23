package com.algorist.string;

import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

import static com.algorist.string.Name.processMergers;
import static com.algorist.string.Name.readChanges;

public class NameTest implements TestCaseWithInput {
    private static final int MAXLEN = 1001;       /* longest possible string */

    @Override
    public void process(Scanner scanner) {
        String[][] mergers = readChanges(scanner);

        int nlines = scanner.nextInt();
        scanner.nextLine();

        char[] s = new char[MAXLEN];
        for (int i = 1; i <= nlines; i++) {
            String line = scanner.nextLine();
            System.arraycopy(line.toCharArray(), 0, s, 0, line.length());

            int slen = processMergers(s, line.length(), mergers, mergers.length);
            System.out.println(new String(s, 0, slen));
        }
    }

    @Test
    public void test() throws IOException {
        TestEngine.execute(this, "name-in", "name-out");
    }
}
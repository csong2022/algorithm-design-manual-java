package com.algorist.datastructure;

import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;

public class TreeDemoTest implements TestCaseWithInput {
    @Override
    public void process(Scanner scanner) {
        final Tree<Integer> l = new Tree<Integer>(Comparator.naturalOrder());
        while (scanner.hasNext()) {
            final char c = Character.toLowerCase(scanner.next().charAt(0));
            final int d;
            switch (c) {
                case 'p':
                    l.print();
                    System.out.println();
                    break;
                case 'i':
                    d = scanner.nextInt();
                    System.out.printf("new item: %d%n", d);
                    l.insert(d);
                    break;
                case 's':
                    d = scanner.nextInt();
                    if (l.contains(d)) {
                        System.out.printf("item %d found%n", d);
                    } else {
                        System.out.printf("item %d not found%n", d);
                    }
                    break;
                case 'd':
                    d = scanner.nextInt();
                    System.out.printf(" deleting item %d%n", d);
                    l.delete(d);
                    l.print();
                    System.out.println();
                    break;
            }
        }
    }

    @Test
    public void testListIn() throws IOException {
        TestEngine.execute(this, "list-in", "treelist-out");
    }

    @Test
    public void testT1() throws IOException {
        TestEngine.execute(this, "t1.in", "t1.out");
    }
}
package com.algorist.datastructure;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PriorityQueueTest {
    @Test
    public void test() {
        final Integer[] a = new Integer[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = a.length - i;
        }

        final PriorityQueue<Integer> q1 = PriorityQueue.makeHeap(a, 0, a.length - 1);
        final PriorityQueue<Integer> q2 = PriorityQueue.makeHeap1(a, 0, a.length - 1, Comparator.naturalOrder());

        assertFalse(q1.isEmpty());
        q1.print();

        assertEquals(q2.size(), q1.size());

        for (int i = 0; i < q1.size(); i++) {
            assertEquals(q2.extractMin(), q1.extractMin());
        }
    }
}
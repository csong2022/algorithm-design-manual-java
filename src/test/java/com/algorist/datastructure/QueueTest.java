package com.algorist.datastructure;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class QueueTest {
    private Queue<Integer> q;

    @Before
    public void setUp() {
        this.q = new Queue<>(1);
    }

    @Test
    public void testDequeueEmptyQueue() {
        assertTrue(this.q.isEmpty());
        this.q.print();
        assertNull(this.q.dequeue());
    }

    @Test
    public void test() {
        assertTrue(this.q.isEmpty());

        for (int i = 1; i <= 16; i++) {
            this.q.enqueue(i);
        }

        this.q.print();
        assertFalse(this.q.isEmpty());

        int i;

        i = 1;
        for (Integer x : this.q) {
            assertThat(x, is(i++));
        }

        for (i = 1; i <= 16; i++) {
            assertThat(this.q.dequeue(), is(i));
        }

        assertTrue(this.q.isEmpty());
    }
}
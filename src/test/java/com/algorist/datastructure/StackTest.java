package com.algorist.datastructure;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StackTest {
    private Stack<Integer> s;

    @Before
    public void setUp() {
        this.s = new Stack<>(1);
    }

    @Test
    public void testPopEmptyStack() {
        assertTrue(this.s.isEmpty());
        this.s.print();
        this.s.pop();
    }

    @Test
    public void test() {
        assertTrue(this.s.isEmpty());

        for (int i = 1; i <= 16; i++) {
            this.s.push(i);
        }

        this.s.print();
        assertFalse(this.s.isEmpty());

        for (int i = 16; i >= 1; i--) {
            assertThat(this.s.pop(), is(i));
        }
        assertTrue(this.s.isEmpty());
    }
}
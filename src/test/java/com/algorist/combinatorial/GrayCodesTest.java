package com.algorist.combinatorial;

import org.junit.Test;

import java.util.List;

import static com.algorist.combinatorial.GrayCodes.generateGrayCodes;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GrayCodesTest {
    @Test
    public void test1Bit() {
        List<String> grayCodes = generateGrayCodes(1);
        assertThat(grayCodes, is(asList("0", "1")));
    }

    @Test
    public void test2Bits() {
        List<String> grayCodes = generateGrayCodes(2);
        assertThat(grayCodes, is(asList("00", "01", "11", "10")));
    }

    @Test
    public void test3Bits() {
        List<String> grayCodes = generateGrayCodes(3);
        assertThat(grayCodes, is(asList("000", "001", "011", "010",
                "110", "111", "101", "100")));
    }

    @Test
    public void test4Bits() {
        List<String> grayCodes = generateGrayCodes(4);
        assertThat(grayCodes, is(asList("0000", "0001", "0011", "0010", "0110", "0111", "0101", "0100", "1100", "1101", "1111",
                "1110", "1010", "1011", "1001", "1000")));
    }
}
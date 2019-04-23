package com.algorist.dp;

import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.util.Scanner;

import static com.algorist.dp.Partition.partition;
import static com.algorist.dp.Partition.read;

public class PartitionTest implements TestCaseWithInput {

    public void process(Scanner scanner) {
        Partition.Data data = read(scanner);
        partition(data.s, data.n, data.k);
    }

    @Test
    public void testData1() throws Exception {
        TestEngine.execute(this, "partition-data1", "partition-data1-out");
    }

    @Test
    public void testData2() throws Exception {
        TestEngine.execute(this, "partition-data2", "partition-data2-out");
    }
}
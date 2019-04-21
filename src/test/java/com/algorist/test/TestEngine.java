package com.algorist.test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertFalse;

/**
 * Execute test case, and compare output with expected.
 *
 * @author csong2022
 */
public class TestEngine {
    private static final String DATAFILES_DIR = "src/main/c/datafiles";
    private static final String OUTPUT_DIR = "out";

    /**
     * Execute test case with input.
     *
     * @param inputFileName  input file.
     * @param outputFileName output file.
     * @param testCase       test case to be executed.
     */
    public static void execute(String inputFileName, String outputFileName, TestCaseWithInput testCase) throws IOException {
        File inputFile = new File(DATAFILES_DIR, inputFileName);
        if (!inputFile.exists()) {
            throw new IllegalArgumentException(String.format("Input file %s doesn't exists", inputFileName));
        }

        File expectedfile = new File(DATAFILES_DIR, outputFileName);
        if (!expectedfile.exists()) {
            throw new IllegalArgumentException(String.format("Expected output file %s doesn't exists", outputFileName));
        }

        File outputFile = new File(OUTPUT_DIR, outputFileName);
        if (outputFile.exists()) {
            outputFile.delete();
        }

        PrintStream stdout = System.out;
        try (PrintStream outputStream = new PrintStream(new FileOutputStream(outputFile))) {
            System.setOut(outputStream);
            try (Scanner scanner = new Scanner(new FileInputStream(inputFile))) {
                testCase.process(scanner);
            }
        } finally {
            System.setOut(stdout);
        }

        assertFalse(String.format("Test result %s differs", outputFileName), diff(outputFile, expectedfile));
    }

    /**
     * Execute test case without input.
     *
     * @param outputFileName output file.
     * @param testcase       test case to be executed.
     */
    public static void execute(String outputFileName, TestCaseWithoutInput testcase) throws IOException {
        File expectedfile = new File(DATAFILES_DIR, outputFileName);
        if (!expectedfile.exists()) {
            throw new IllegalArgumentException(String.format("Expected output file %s doesn't exists", outputFileName));
        }

        File outputFile = new File(OUTPUT_DIR, outputFileName);
        if (outputFile.exists()) {
            outputFile.delete();
        }

        PrintStream stdout = System.out;
        try (PrintStream outputStream = new PrintStream(new FileOutputStream(outputFile))) {
            System.setOut(outputStream);
            testcase.process();
        } finally {
            System.setOut(stdout);
        }

        assertFalse(String.format("Test result %s differs", outputFileName), diff(outputFile, expectedfile));
    }

    /**
     * Diff two files.
     *
     * @param file1 file 1.
     * @param file2 file 2.
     * @return true if different.
     */
    private static boolean diff(File file1, File file2) throws IOException {
        List<String> lines1 = Files.readAllLines(Paths.get(file1.toURI()));
        List<String> lines2 = Files.readAllLines(Paths.get(file2.toURI()));

        if (lines1.size() != lines2.size()) {
            return true;
        }

        for (int i = 0; i < lines1.size(); i++) {
            String line1 = lines1.get(i);
            String line2 = lines2.get(i);

            if (!line1.equals(line2)) return true;
        }

        return false;
    }
}

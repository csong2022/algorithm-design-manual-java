package com.algorist.numerical;

/**
 * Compute random numbers within given ranges. Reimplement original random.c with Java Random.
 *
 * @author csong2022
 */
public class Random {
    private static final java.util.Random random = new java.util.Random(System.currentTimeMillis());

    /**
     * Compute random numbers within given ranges.
     *
     * @param low  lower bounds on number (inclusive).
     * @param high upper bounds on number (inclusive).
     * @return random number within the range.
     */
    public static int randomInt(final int low, final int high) {
        if (low >= high) {
            throw new IllegalArgumentException(String.format("Invalid range [%d, %d]", low, high));
        }

        return low + random.nextInt(high - low + 1);
    }

    public static double randomFloat() {
        return random.nextDouble();
    }

    public static <T> void randomPermutation(final T[] a) {
        randomPermutation(a, 0, a.length - 1);
    }

    public static <T> void randomPermutation(final T[] a, final int low, final int high) {
        for (int i = high; i > low; i--) {
            swap(a, i, randomInt(low, i));
        }
    }

    public static void randomPermutation(final int[] a, final int low, final int high) {
        for (int i = high; i > low; i--) {
            swap(a, i, randomInt(low, i));
        }
    }

    private static <T> void swap(final T[] a, final int i, final int j) {
        if (i != j) {
            final T tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
    }

    private static void swap(final int[] a, final int i, final int j) {
        if (i != j) {
            final int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
    }

    public static double randomFloat(final double low, final double high) {
        if (low >= high) {
            throw new IllegalArgumentException(String.format("Invalid range [%f, %f]", low, high));
        }

        return low + random.nextDouble() * (high - low);
    }
}

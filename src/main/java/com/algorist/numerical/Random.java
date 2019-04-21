package com.algorist.numerical;

/**
 * Compute random numbers within given ranges.
 */
public class Random {
    private static final java.util.Random random = new java.util.Random(System.currentTimeMillis());

    /**
     * Compute random numbers within given ranges.
     *
     * @param low  lower bounds on number.
     * @param high upper bounds on number.
     * @return random number within the range.
     */
    public static int randomInt(final int low, final int high) {
        if (low >= high) {
            throw new IllegalArgumentException(String.format("Invalid range [%d, %d]", low, high));
        }

        return low + random.nextInt(high - low);
    }

    public static <T> void randomPermutation(final T[] a) {
        for (int i = a.length; i > 1; i--) {
            swap(a, i - 1, randomInt(0, i - 1));
        }
    }

    private static <T> void swap(final T[] a, final int i, final int j) {
        if (i != j) {
            final T tmp = a[i];
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

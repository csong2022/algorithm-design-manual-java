package com.algorist.utils;

import java.util.Iterator;

import static com.algorist.datastructure.ArrayUtils.newArray;

/**
 * Iterable utils.
 *
 * @author csong2022
 */
public class IterableUtils {
    /**
     * Format iterable to single line.
     *
     * @param iterable iterable.
     * @param <T>      element type.
     * @return space delimited string.
     */
    public static <T> String toString(final Iterable<T> iterable) {
        final StringBuilder builder = new StringBuilder();

        final Iterator<T> iterator = iterable.iterator();

        if (iterator.hasNext()) {
            builder.append(iterator.next());
        }

        while (iterator.hasNext()) {
            builder.append(' ').append(iterator.next());
        }

        return builder.toString();
    }

    /**
     * Convert iterable to array.
     *
     * @param iterable iterable.
     * @param size     size of collection.
     * @param <T>      element type.
     * @return array.
     */
    public static <T> T[] toArray(Iterable<T> iterable, int size) {
        T[] arr = newArray(size);

        int m = 0;
        for (T item : iterable) {
            arr[m++] = item;
        }
        return arr;
    }
}

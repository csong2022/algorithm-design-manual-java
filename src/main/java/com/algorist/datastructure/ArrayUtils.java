package com.algorist.datastructure;

/**
 * @author csong2022
 */
public class ArrayUtils {

    public static final int ARRAY_SIZE_THRESHOLD = 16;

    /**
     * Create a new array.
     *
     * @param size size of array.
     * @param <T>  element type.
     * @return array.
     */
    public static <T> T[] newArray(int size) {
        return (T[]) new Object[size];
    }

    /**
     * Dynamically adjust array size to roughly keep it half full.
     *
     * @param arr   input array.
     * @param count number of elements filled.
     * @param <T>   element type.
     * @return new array if size requires adjustment, otherwise the input array.
     */
    public static <T> T[] resize(T[] arr, int count) {
        if (count == arr.length) { // double array size if reaches capacity.
            T[] newArr = newArray(arr.length * 2);
            System.arraycopy(arr, 0, newArr, 0, count);
            return newArr;

        } else if (count > ARRAY_SIZE_THRESHOLD && count <= arr.length / 4) {
            // half the array size if too low, and avoid threshing on small array.
            T[] newArr = newArray(arr.length / 2);
            System.arraycopy(arr, 0, newArr, 0, count);
            return newArr;
        } else {
            return arr;
        }
    }
}

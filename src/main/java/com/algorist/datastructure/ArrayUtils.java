package com.algorist.datastructure;

/**
 * @author csong2022
 */
public class ArrayUtils {

    public static final int ARRAY_SIZE_THRESHOLD = 16;

    /**
     * Create a generic array.
     *
     * @param size size of array.
     * @param <T>  element type.
     * @return generic array.
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] newArray(int size) {
        return (T[]) new Object[size];
    }

    /**
     * Dynamically adjust array size to roughly keep it half full.
     *
     * @param arr    input array.
     * @param length number of elements filled.
     * @param <T>    element type.
     * @return new array if size requires adjustment, otherwise the input array.
     */
    public static <T> T[] resize(T[] arr, int length) {
        return resize(arr, 0, length);
    }

    /**
     * Dynamically adjust array size to roughly keep it half full.
     *
     * @param arr    input array.
     * @param srcPos index of the first element.
     * @param length number of elements filled.
     * @param <T>    element type.
     * @return new array if size requires adjustment, otherwise the input array.
     */
    public static <T> T[] resize(T[] arr, int srcPos, int length) {
        int newSize = adjustedArraySize(arr, length);
        if (newSize == arr.length) {
            return arr;

        } else {
            T[] newArr = newArray(newSize);
            if (srcPos + length <= arr.length) {
                System.arraycopy(arr, srcPos, newArr, 0, length);
            } else {
                System.arraycopy(arr, srcPos, newArr, 0, arr.length - srcPos);
                System.arraycopy(arr, 0, newArr, arr.length - srcPos, (srcPos + length) % arr.length + 1);
            }

            return newArr;
        }
    }

    private static <T> int adjustedArraySize(T[] arr, int length) {
        int newSize = arr.length;
        if (length == arr.length) { // double array size if reaches capacity.
            newSize = arr.length * 2;
        } else if (length > ARRAY_SIZE_THRESHOLD && length <= arr.length / 4) {
            // half the array size if too low, and avoid threshing on small array.
            newSize = arr.length / 2;
        }

        return newSize;
    }
}

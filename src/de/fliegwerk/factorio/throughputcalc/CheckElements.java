package de.fliegwerk.factorio.throughputcalc;

/**
 * This utility class contains methods to check numeric values in min, max or range.
 *
 * @author Ludwig Richter (richter@fliegwerk.com)
 * @version 1.0
 * @since 11.0.2
 */
public class CheckElements {
    public static void checkMin(double value, double min, String name) {
        if (value < min)
            throw new IndexOutOfBoundsException(String.format("%s (%f) can not be less than %f!", name, value, min));
    }

    public static void checkMin(int value, int min, String name) {
        if (value < min)
            throw new IndexOutOfBoundsException(String.format("%s (%d) can not be less than %d!", name, value, min));
    }

    public static void checkMax(double value, double max, String name) {
        if (value > max)
            throw new IndexOutOfBoundsException(String.format("%s (%f) can not be greater than %f!", name, value, max));
    }

    public static void checkMax(int value, int max, String name) {
        if (value > max)
            throw new IndexOutOfBoundsException(String.format("%s (%d) can not be greater than %d!", name, value, max));
    }

    public static void checkRange(double value, double min, double max, String name) {
        checkMin(value, min, name);
        checkMax(value, max, name);
    }

    public static void checkRange(int value, int min, int max, String name) {
        checkMin(value, min, name);
        checkMax(value, max, name);
    }

    private CheckElements() {}
}

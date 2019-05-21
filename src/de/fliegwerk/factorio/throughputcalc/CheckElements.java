package de.fliegwerk.factorio.throughputcalc;

public class CheckElements {
    public static void checkRange(double value, double min, double max, String name) {
        if (value < min)
            throw new IndexOutOfBoundsException("Integer " + name + ' ' + value +
                    " can not be less than " + min + '!');
        if (value > max)
            throw new IndexOutOfBoundsException("Integer " + name + ' ' + value +
                    " can not be greater than " + max + '!');
    }

    public static void checkRange(int value, int min, int max, String name) {
        if (value < min)
            throw new IndexOutOfBoundsException("Double " + name + ' ' + value +
                    " can not be less than " + min + '!');
        if (value > max)
            throw new IndexOutOfBoundsException("Double " + name + ' ' + value +
                    " can not be greater than " + max + '!');
    }

    private CheckElements() {}
}

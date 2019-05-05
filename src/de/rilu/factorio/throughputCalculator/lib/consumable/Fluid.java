package de.rilu.factorio.throughputCalculator.lib.consumable;

import java.util.Objects;

public class Fluid extends Consumable {
    public static final double MIN_CVALUE = 0.1;
    public static final double DEFAULT_CVALUE = 1;
    public static final double MAX_CVALUE = 4.0;

    private final double cValue;

    public Fluid(String name, double cValue) {
        super(name);
        if (cValue < MIN_CVALUE || cValue > MAX_CVALUE)
            throw new IllegalArgumentException("Double c value can not be less than " + MIN_CVALUE + " or greater than " + MAX_CVALUE + '!');

        this.cValue = cValue;
    }

    public double getcValue() {
        return cValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Fluid fluid = (Fluid) o;
        return Double.compare(fluid.cValue, cValue) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cValue);
    }

    @Override
    public String toString() {
        return "Fluid{" +
                super.toString() +
                "cValue=" + cValue +
                '}';
    }
}

package de.fliegwerk.factorio.throughputcalc.consumables;

import java.util.Objects;

public class Fluid extends Consumable {
    private final double cValue;

    public Fluid(String name, double cValue) {
        super(name);
        this.cValue = cValue;
    }

    public double getcValue() {
        return cValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fluid)) return false;
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
                "cValue=" + cValue +
                "} " + super.toString();
    }
}

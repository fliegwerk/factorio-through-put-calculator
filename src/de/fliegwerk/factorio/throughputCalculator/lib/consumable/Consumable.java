package de.fliegwerk.factorio.throughputCalculator.lib.consumable;

import java.util.Objects;

public abstract class Consumable {
    private final String name;

    protected Consumable(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("String name can not be null or empty!");

        this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumable that = (Consumable) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "name='" + name + "', ";
    }
}

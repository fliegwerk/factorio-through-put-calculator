package de.fliegwerk.factorio.throughputcalc.consumables;

import java.util.Objects;

public abstract class Consumable {
    private final String name;

    public Consumable(String name) {
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Consumable)) return false;
        Consumable that = (Consumable) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Consumable{" +
                "name='" + name + '\'' +
                '}';
    }
}

package de.fliegwerk.factorio.throughputcalc.machines;

import java.util.Objects;

public abstract class MachinePrototype {
    private final String name;

    public MachinePrototype(String name) {
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MachinePrototype)) return false;
        MachinePrototype that = (MachinePrototype) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "MachinePrototype{" +
                "name='" + name + '\'' +
                '}';
    }
}

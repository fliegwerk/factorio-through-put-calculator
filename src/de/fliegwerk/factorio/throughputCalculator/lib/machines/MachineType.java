package de.fliegwerk.factorio.throughputCalculator.lib.machines;

import java.util.Objects;

public class MachineType {
    private final String name;

    public MachineType(String name) {
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
        if (!(o instanceof MachineType)) return false;
        MachineType that = (MachineType) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "MachineType{" +
                "name='" + name + '\'' +
                '}';
    }
}

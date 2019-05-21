package de.fliegwerk.factorio.throughputcalc.machines;

import de.fliegwerk.factorio.throughputcalc.CheckElements;

import java.util.Objects;

public abstract class MachinePrototype {
    public static final double MIN_ENERGY_CONSUMPTION = 0.1; /* kW */

    private final String name;
    private final MachineType machineType;
    private final double energyConsumption; /* kW */

    public MachinePrototype(String name, MachineType machineType, double energyConsumption) {
        Objects.requireNonNull(name, "String name can not be null!");
        Objects.requireNonNull(machineType, "Machine type can not be null!");
        CheckElements.checkMin(energyConsumption, MIN_ENERGY_CONSUMPTION, "Double energy consumption");

        this.name = name.trim();
        this.machineType = machineType;
        this.energyConsumption = energyConsumption;
    }

    public String getName() {
        return name;
    }

    public MachineType getMachineType() {
        return machineType;
    }

    public double getEnergyConsumption() {
        return energyConsumption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MachinePrototype)) return false;
        MachinePrototype that = (MachinePrototype) o;
        return Double.compare(that.energyConsumption, energyConsumption) == 0 &&
                name.equals(that.name) &&
                machineType == that.machineType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, machineType, energyConsumption);
    }

    @Override
    public String toString() {
        return "MachinePrototype{" +
                "name='" + name + '\'' +
                ", machineType=" + machineType +
                ", energyConsumption=" + energyConsumption +
                '}';
    }
}

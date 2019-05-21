package de.fliegwerk.factorio.throughputcalc.machines;

import de.fliegwerk.factorio.throughputcalc.CheckElements;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * This abstract class represents an initial structure for a machine prototype.
 * It contains only simple data types like a name, a machine type and it consume energy.
 *
 * @see MachineType
 *
 * @author Ludwig Richter (richter@fliegwerk.com)
 * @version 1.0.1
 * @since 11.0.2
 */
public abstract class MachinePrototype {
    /**
     * Number of minimal energy consumption for all machines prototypes
     */
    public static final double MIN_ENERGY_CONSUMPTION = 0.1; /* kW */

    private final String name;
    private final MachineType machineType;
    private final double energyConsumption; /* kW */

    /**
     * Creates a new machine prototype with given name, a {@link MachineType} and a energy consumption.
     * @param name name of this machine prototypes.
     * @param machineType machine type of this machine prototype
     * @param energyConsumption energy consumption in kilo watts for this machine prototype
     *
     * @throws NullPointerException if string name or machine type is {@code null}
     * @throws IndexOutOfBoundsException if energy consumption is less than {@value MIN_ENERGY_CONSUMPTION}
     */
    public MachinePrototype(@NotNull String name, @NotNull MachineType machineType, double energyConsumption) {
        Objects.requireNonNull(name, "String name can not be null!");
        Objects.requireNonNull(machineType, "Machine type can not be null!");
        CheckElements.checkMin(energyConsumption, MIN_ENERGY_CONSUMPTION, "Double energy consumption");

        this.name = name.trim();
        this.machineType = machineType;
        this.energyConsumption = energyConsumption;
    }

    /**
     * Returns the machine of this machine prototype.
     * @return name of machine prototype
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the {@link MachineType} of this machine prototype.
     * @return machine type of machine prototype
     */
    public MachineType getMachineType() {
        return machineType;
    }

    /**
     * Returns the energy consumption in kilo watts for this machine prototype.
     * @return energy consumption in kW for machine prototype
     */
    public double getEnergyConsumption() {
        return energyConsumption;
    }

    @Contract(value = "null -> false", pure = true)
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

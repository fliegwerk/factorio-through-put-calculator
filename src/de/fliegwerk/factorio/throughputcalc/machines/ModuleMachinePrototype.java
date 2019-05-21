package de.fliegwerk.factorio.throughputcalc.machines;

import de.fliegwerk.factorio.throughputcalc.CheckElements;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * This abstract class represents an extended structure for a module machine prototype.
 * It is based on a machine prototype.
 * It contains only simple data types like a name, a machine type, a energy consumption and a number of module slots.
 *
 * @see MachinePrototype
 *
 * @author Ludwig Richter (richter@fliegwerk.com)
 * @version 1.0.1
 * @since 11.0.2
 */
public abstract class ModuleMachinePrototype extends MachinePrototype {
    /**
     * Number of minimal number of module slots for all module machine prototypes
     */
    public static final int MIN_MODULE_SLOTS = 0;

    private final int moduleSlots;

    /**
     * Creates a new module machine prototype with given name, a {@link MachineType}, a energy consumption
     * and a number of module slots.
     * @param name name of this machine prototypes.
     * @param machineType machine type of this machine prototype
     * @param energyConsumption energy consumption in kilo watts for this machine prototype
     * @param moduleSlots number of module slots for this module machine
     *
     * @throws NullPointerException if string name or machine type is {@code null}
     * @throws IndexOutOfBoundsException if energy consumption is less than {@value MIN_ENERGY_CONSUMPTION}
     * @throws IndexOutOfBoundsException if module slots is less than {@value MIN_MODULE_SLOTS}
     */
    public ModuleMachinePrototype(@NotNull String name, @NotNull MachineType machineType,
                                  double energyConsumption, int moduleSlots) {
        super(name, machineType, energyConsumption);
        CheckElements.checkMin(moduleSlots, MIN_MODULE_SLOTS, "Integer module slots");

        this.moduleSlots = moduleSlots;
    }

    /**
     * Returns the number of module slots for this module machine prototype.
     * @return module slots for module machine prototype
     */
    public int getModuleSlots() {
        return moduleSlots;
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModuleMachinePrototype)) return false;
        if (!super.equals(o)) return false;
        ModuleMachinePrototype that = (ModuleMachinePrototype) o;
        return moduleSlots == that.moduleSlots;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), moduleSlots);
    }
}

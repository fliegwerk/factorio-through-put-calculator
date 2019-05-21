package de.fliegwerk.factorio.throughputcalc.machines;

import de.fliegwerk.factorio.throughputcalc.CheckElements;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class EffectMachinePrototype extends ModuleMachinePrototype {
    public static final double MIN_DISTRIBUTION_EFFICIENCY = 0.01;

    private final double distributionEfficiency;

    public EffectMachinePrototype(@NotNull String name, @NotNull MachineType machineType,
                                  double energyConsumption, int moduleSlots, double distributionEfficiency) {
        super(name, machineType, energyConsumption, moduleSlots);
        CheckElements.checkMin(distributionEfficiency, MIN_DISTRIBUTION_EFFICIENCY, "Double distribution efficiency");

        this.distributionEfficiency = distributionEfficiency;
    }

    public double getDistributionEfficiency() {
        return distributionEfficiency;
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EffectMachinePrototype)) return false;
        if (!super.equals(o)) return false;
        EffectMachinePrototype that = (EffectMachinePrototype) o;
        return Double.compare(that.distributionEfficiency, distributionEfficiency) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), distributionEfficiency);
    }

    @Override
    public String toString() {
        return "EffectMachinePrototype{" +
                "distributionEfficiency=" + distributionEfficiency +
                "} " + super.toString();
    }
}

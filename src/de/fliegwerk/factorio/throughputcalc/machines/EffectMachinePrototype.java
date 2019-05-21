package de.fliegwerk.factorio.throughputcalc.machines;

import java.util.Objects;

public class EffectMachinePrototype extends ModuleMachinePrototype {
    private final double distributionEfficiency;

    public EffectMachinePrototype(String name, int moduleSlots, double distributionEfficiency) {
        super(name, moduleSlots);
        this.distributionEfficiency = distributionEfficiency;
    }

    public double getDistributionEfficiency() {
        return distributionEfficiency;
    }

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

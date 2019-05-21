package de.fliegwerk.factorio.throughputcalc.machines;

import java.util.Objects;

public abstract class ModuleMachinePrototype extends MachinePrototype {
    private final int moduleSlots;

    public ModuleMachinePrototype(String name, int moduleSlots) {
        super(name);
        this.moduleSlots = moduleSlots;
    }

    public int getModuleSlots() {
        return moduleSlots;
    }

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

package de.fliegwerk.factorio.throughputcalc.machines;

import de.fliegwerk.factorio.throughputcalc.modules.Module;
import org.jetbrains.annotations.Contract;

import java.util.Arrays;
import java.util.Objects;

public class MachineConstructor<T extends ModuleMachinePrototype> {
    private final T prototype;
    private final Module[] modules;

    public MachineConstructor(T prototype, Module[] modules) {
        this.prototype = prototype;
        this.modules = (modules == null ? new Module[prototype.getModuleSlots()] : modules);
    }

    public MachineConstructor(T prototype) {
        this(prototype, null);
    }

    public T getPrototype() {
        return prototype;
    }

    public boolean addModule(Module module) {
        for (int i = 0; i < modules.length; i++) {
            if (modules[i] == null) {
                modules[i] = module;
                return true;
            }
        }
        return false;
    }

    public void setModule(Module module, int position) {
        // TODO: position <-> size checking
        modules[position] = module;
    }

    public Module[] getModules() {
        return modules;
    }

    public Module getModule(int position) {
        // TODO: position <-> size checking
        return modules[position];
    }

    public Module removeModule(int position) {
        // TODO: position <-> size checking
        Module removed = modules[position];
        modules[position] = null;
        return removed;
    }

    public Module[] removeModules() {
        Module[] removed = new Module[modules.length];
        for (int i = 0; i < modules.length; i++) {
            removed[i] = modules[i];
            modules[i] = null;
        }
        return removed;
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MachineConstructor)) return false;
        MachineConstructor<?> that = (MachineConstructor<?>) o;
        return prototype.equals(that.prototype) &&
                Arrays.equals(modules, that.modules);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(prototype);
        result = 31 * result + Arrays.hashCode(modules);
        return result;
    }

    @Override
    public String toString() {
        return "MachineConstructor{" +
                "prototype=" + prototype +
                ", modules=" + Arrays.toString(modules) +
                '}';
    }
}

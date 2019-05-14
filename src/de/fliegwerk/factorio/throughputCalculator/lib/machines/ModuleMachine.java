package de.fliegwerk.factorio.throughputCalculator.lib.machines;

import de.fliegwerk.factorio.throughputCalculator.lib.effects.Module;

public class ModuleMachine extends Machine {
    public static final int MIN_MODULE_SLOTS = 0;
    public static final int MAX_MODULE_SLOTS = 10;

    private Module[] modules;

    public ModuleMachine(String name, MachineType machineType, int moduleSlots) {
        super(name, machineType);

        if (moduleSlots < MIN_MODULE_SLOTS || moduleSlots > MAX_MODULE_SLOTS)
            throw new IllegalArgumentException("Integer effects slots can not be less than " +
                    MIN_MODULE_SLOTS + " or greater than " + MAX_MODULE_SLOTS + '!');

        this.modules = new Module[moduleSlots];
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

    public boolean addModules(Module[] newModules) {
        if (newModules.length > getEmptySlots())
            return false;

        int current = 0;
        for (int i = 0; i < modules.length; i++) {
            if (modules[i] == null)
                modules[i] = newModules[current++];
        }

        return current - 1 < newModules.length;
    }

    public Module[] getModules() {
        return modules;
    }

    public int getEmptySlots() {
        int empty = 0;
        for (Module module : modules)
            if (module != null) empty++;

        return empty;
    }

    public Module removeModule(int index) {
        if (index < 0 || index >= modules.length)
            throw new IllegalArgumentException("Integer index can not be less than 0 or greater than " + modules.length + '!');

        Module removed = modules[index];
        modules[index] = null;
        return removed;
    }

    public Module[] removeAllModules() {
        Module[] removed = new Module[modules.length];
        for (int i = 0; i < modules.length; i++) {
            removed[i] = modules[i];
            modules[i] = null;
        }
        return removed;
    }

    public double getSpeedBonus() {
        double speedBonus = 0.0;
        for (Module module : modules) {
            if (module != null)
                speedBonus += module.getSpeedBonus();
        }
        return speedBonus;
    }

    public double getEnergyConsumption() {
        double energyConsumption = 0.0;
        for (Module module : modules) {
            if (module != null)
                energyConsumption += module.getEnergyConsumption();
        }
        return energyConsumption;
    }

    public double getProductivityBonus() {
        double productivityBonus = 0.0;
        for (Module module : modules) {
            if (module != null)
                productivityBonus += module.getProductivityBonus();
        }
        return productivityBonus;
    }

    public double getPollutionBonus() {
        double pollutionBonus = 0.0;
        for (Module module : modules) {
            if (module != null)
                pollutionBonus += module.getPollutionBonus();
        }
        return pollutionBonus;
    }
}

package de.fliegwerk.factorio.throughputCalculator.lib.producer;

import java.util.Objects;

public class AssemblingMachine {
    public static final double MIN_CRAFTING_SPEED = 0.1;
    public static final double MAX_CRAFTING_SPEED = 100;
    public static final double MIN_ENERGY_CONSUMPTION = 1; /* kW */
    public static final double MAX_ENERGY_CONSUMPTION = 10_000; /* kW */
    public static final double MIN_POLLUTION = 0;
    public static final double MAX_POLLUTION = 1_000;
    public static final double MIN_MODULE_SLOTS = 0;
    public static final double MAX_MOduLE_SLOTS = 10;

    private final String name;
    private final double baseCraftingSpeed;
    private final double baseEnergyConsumption;
    private final double basePollution;
    private final int moduleSlots;

    public AssemblingMachine(String name, double baseCraftingSpeed, double baseEnergyConsumption, double basePollution, int moduleSlots) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("String name can not be null or empty!");
        if (baseCraftingSpeed < MIN_CRAFTING_SPEED || baseCraftingSpeed > MAX_CRAFTING_SPEED)
            throw new IllegalArgumentException("Double base crafting speed can not be less than " +
                    MIN_CRAFTING_SPEED + " or greater than " + MAX_CRAFTING_SPEED + '!');
        if (baseEnergyConsumption < MIN_ENERGY_CONSUMPTION || baseEnergyConsumption > MAX_ENERGY_CONSUMPTION)
            throw new IllegalArgumentException("Double base energy consumption can not be less than " +
                    MIN_ENERGY_CONSUMPTION + " or greater than " + MAX_ENERGY_CONSUMPTION + '!');
        if (basePollution < MIN_POLLUTION || basePollution > MAX_POLLUTION)
            throw new IllegalArgumentException("Double base pollution can not less than " +
                    MIN_POLLUTION + " or greater than " + MAX_POLLUTION + '!');
        if (moduleSlots < MIN_MODULE_SLOTS || moduleSlots > MAX_MOduLE_SLOTS)
            throw new IllegalArgumentException("Integer module slots can not be less than " +
                    MIN_MODULE_SLOTS + " or greater than " + MAX_MOduLE_SLOTS + '!');

        this.name = name.trim();
        this.baseCraftingSpeed = baseCraftingSpeed;
        this.baseEnergyConsumption = baseEnergyConsumption;
        this.basePollution = basePollution;
        this.moduleSlots = moduleSlots;
    }

    public String getName() {
        return name;
    }

    public double getBaseCraftingSpeed() {
        return baseCraftingSpeed;
    }

    public double getBaseEnergyConsumption() {
        return baseEnergyConsumption;
    }

    public double getBasePollution() {
        return basePollution;
    }

    public int getModuleSlots() {
        return moduleSlots;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AssemblingMachine)) return false;
        AssemblingMachine that = (AssemblingMachine) o;
        return Double.compare(that.baseCraftingSpeed, baseCraftingSpeed) == 0 &&
                Double.compare(that.baseEnergyConsumption, baseEnergyConsumption) == 0 &&
                Double.compare(that.basePollution, basePollution) == 0 &&
                moduleSlots == that.moduleSlots &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, baseCraftingSpeed, baseEnergyConsumption, basePollution, moduleSlots);
    }

    @Override
    public String toString() {
        return "AssemblingMachine{" +
                "name='" + name + '\'' +
                ", baseCraftingSpeed=" + baseCraftingSpeed +
                ", baseEnergyConsumption=" + baseEnergyConsumption +
                ", basePollution=" + basePollution +
                ", moduleSlots=" + moduleSlots +
                '}';
    }
}

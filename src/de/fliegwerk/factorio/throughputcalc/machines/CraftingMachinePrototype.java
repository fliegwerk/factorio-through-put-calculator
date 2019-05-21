package de.fliegwerk.factorio.throughputcalc.machines;

import de.fliegwerk.factorio.throughputcalc.CheckElements;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CraftingMachinePrototype extends ModuleMachinePrototype {
    public static final double MIN_CRAFTING_SPEED = 0.01;
    public static final double MIN_PRODUCTIVITY = 0.0;
    public static final double MIN_POLLUTION = 0.0;

    private final double craftingSpeed;
    private final double productivity;
    private final double pollution;

    public CraftingMachinePrototype(@NotNull String name, @NotNull MachineType machineType,
                                    double energyConsumption, int moduleSlots,
                                    double craftingSpeed, double productivity, double pollution) {
        super(name, machineType, energyConsumption, moduleSlots);
        CheckElements.checkMin(craftingSpeed, MIN_CRAFTING_SPEED, "Double crafting speed");
        CheckElements.checkMin(productivity, MIN_PRODUCTIVITY, "Double productivity");
        CheckElements.checkMin(pollution, MIN_POLLUTION, "Double pollution");

        this.craftingSpeed = craftingSpeed;
        this.productivity = productivity;
        this.pollution = pollution;
    }

    public double getCraftingSpeed() {
        return craftingSpeed;
    }

    public double getProductivity() {
        return productivity;
    }

    public double getPollution() {
        return pollution;
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CraftingMachinePrototype)) return false;
        if (!super.equals(o)) return false;
        CraftingMachinePrototype that = (CraftingMachinePrototype) o;
        return Double.compare(that.craftingSpeed, craftingSpeed) == 0 &&
                Double.compare(that.productivity, productivity) == 0 &&
                Double.compare(that.pollution, pollution) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), craftingSpeed, productivity, pollution);
    }

    @Override
    public String toString() {
        return "CraftingMachinePrototype{" +
                "craftingSpeed=" + craftingSpeed +
                ", productivity=" + productivity +
                ", pollution=" + pollution +
                "} " + super.toString();
    }
}

package de.fliegwerk.factorio.throughputcalc.machines;

public class CraftingMachinePrototype extends ModuleMachinePrototype {
    private final double craftingSpeed;

    public CraftingMachinePrototype(String name, int moduleSlots, double craftingSpeed) {
        super(name, moduleSlots);
        this.craftingSpeed = craftingSpeed;
    }

    public double getCraftingSpeed() {
        return craftingSpeed;
    }
}

package de.fliegwerk.factorio.throughputcalc.machines;

/**
 * This enum represents all possible types of machines.
 *
 * @see de.fliegwerk.factorio.throughputcalc.machines.MachinePrototype
 * @see de.fliegwerk.factorio.throughputcalc.machines.MachineConstructor
 * @see de.fliegwerk.factorio.throughputcalc.recipes.Recipe
 *
 * @author Ludwig Richter (richter@fliegwerk.com)
 * @version 1.0.1
 * @since 11.0.2
 */
public enum MachineType {
    effectMachine, miner, furnace, assemblingMachine, refinery, chemicalPlant, centrifuge, lab;

    public static MachineType getMachineType(String name) {
        if (name.equals("effect-machine"))
            throw new IllegalArgumentException("Type effect machine is reserved!!");

        if (name.equals("assembling-machine"))
            return assemblingMachine;
        else if (name.equals("chemical-plant"))
            return chemicalPlant;
        else
            return valueOf(name);
    }
}

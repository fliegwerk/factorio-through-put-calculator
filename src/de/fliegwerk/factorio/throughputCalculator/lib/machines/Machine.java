package de.fliegwerk.factorio.throughputCalculator.lib.machines;

public class Machine {
    private final String name;
    private final MachineType machineType;

    public Machine(String name, MachineType machineType) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("String name can not be null or empty!");
        if (machineType == null)
            throw new IllegalArgumentException("Machine type can not be null!");

        this.name = name.trim();
        this.machineType = machineType;
    }

    public String getName() {
        return name;
    }

    public MachineType getMachineType() {
        return machineType;
    }
}

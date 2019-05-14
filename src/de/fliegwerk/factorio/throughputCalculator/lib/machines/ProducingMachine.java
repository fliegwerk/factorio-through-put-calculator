package de.fliegwerk.factorio.throughputCalculator.lib.machines;

import java.util.List;

public class ProducingMachine extends ModuleMachine {
    public static final double MIN_CRAFTING_SPEED = 0.1;
    public static final double MAX_CRAFTING_SPEED = 100;
    public static final double MIN_ENERGY_CONSUMPTION = 1; /* kW */
    public static final double MAX_ENERGY_CONSUMPTION = 10_000; /* kW */
    public static final double MIN_POLLUTION = 0;
    public static final double MAX_POLLUTION = 1_000;

    private final double baseCraftingSpeed;
    private final double baseEnergyConsumption;
    private final double basePollution;

    public ProducingMachine(String name, MachineType machineType, int moduleSlots,
                            double baseCraftingSpeed, double baseEnergyConsumption, double basePollution) {
        super(name, machineType, moduleSlots);

        if (baseCraftingSpeed < MIN_CRAFTING_SPEED || baseCraftingSpeed > MAX_CRAFTING_SPEED)
            throw new IllegalArgumentException("Double base crafting speed can not be less than " +
                    MIN_CRAFTING_SPEED + " or greater than " + MAX_CRAFTING_SPEED + '!');
        if (baseEnergyConsumption < MIN_ENERGY_CONSUMPTION || baseEnergyConsumption > MAX_ENERGY_CONSUMPTION)
            throw new IllegalArgumentException("Double base energy consumption can not be less than " +
                    MIN_ENERGY_CONSUMPTION + " or greater than " + MAX_ENERGY_CONSUMPTION + '!');
        if (basePollution < MIN_POLLUTION || basePollution > MAX_POLLUTION)
            throw new IllegalArgumentException("Double base pollution can not less than " +
                    MIN_POLLUTION + " or greater than " + MAX_POLLUTION + '!');

        this.baseCraftingSpeed = baseCraftingSpeed;
        this.baseEnergyConsumption = baseEnergyConsumption;
        this.basePollution = basePollution;
    }

    public double getBaseCraftingSpeed() {
        return baseCraftingSpeed;
    }

    public double getCraftingSpeed(List<EffectMachine> effectMachines) {
        return baseCraftingSpeed * getCraftingSpeedMultiplier(effectMachines);
    }

    public double getBaseEnergyConsumption() {
        return baseEnergyConsumption;
    }

    public double getEnergyConsumption(List<EffectMachine> effectMachines) {
        return baseEnergyConsumption * getEnergyConsumptionMultiplier(effectMachines);
    }

    public double getBasePollution() {
        return basePollution;
    }

    public double getPollution(List<EffectMachine> effectMachines) {
        return basePollution * getPollutionMultiplier(effectMachines);
    }

    public double getCraftingSpeedMultiplier(List<EffectMachine> effectMachines) {
        double internalSpeedBonus = getSpeedBonus();
        double externalSpeedBonus = 0.0;
        for (EffectMachine effectMachine : effectMachines) {
            externalSpeedBonus += effectMachine.getSpeedBonus();
        }

        return 1 + (internalSpeedBonus + externalSpeedBonus);
    }

    public double getEnergyConsumptionMultiplier(List<EffectMachine> effectMachines) {
        double internalEnergyConsumption = getEnergyConsumption();
        double externalEnergyConsumption = 0.0;
        for (EffectMachine effectMachine : effectMachines) {
            externalEnergyConsumption += effectMachine.getEnergyConsumption();
        }

        return 1 + (internalEnergyConsumption + externalEnergyConsumption);
    }

    public double getProductivityMultiplier(List<EffectMachine> effectMachines) {
        double internalProductivityBonus = getProductivityBonus();
        double externalProductivityBonus = 0.0;
        for (EffectMachine effectMachine : effectMachines) {
            externalProductivityBonus += effectMachine.getProductivityBonus();
        }

        return 1 + (internalProductivityBonus + externalProductivityBonus);
    }

    public double getPollutionMultiplier(List<EffectMachine> effectMachines) {
        double internalPollutionBonus = getPollutionBonus();
        double externalPollutionBonus = 0.0;
        for (EffectMachine effectMachine : effectMachines) {
            externalPollutionBonus += effectMachine.getPollutionBonus();
        }

        return 1 + (internalPollutionBonus + externalPollutionBonus);
    }
}

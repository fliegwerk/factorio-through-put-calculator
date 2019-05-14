package de.fliegwerk.factorio.throughputCalculator.lib.machines;

public class EffectMachine extends ModuleMachine {
    public static final double MIN_DISTRIBUTION_EFFICIENCY = 0.1;
    public static final double MAX_DISTRIBUTION_EFFICIENCY = 5.0;

    private final double distributionEfficiency;

    public EffectMachine(String name, MachineType machineType, int moduleSlots, double distributionEfficiency) {
        super(name, machineType, moduleSlots);

        if (distributionEfficiency < MIN_DISTRIBUTION_EFFICIENCY || distributionEfficiency > MAX_DISTRIBUTION_EFFICIENCY)
            throw new IllegalArgumentException("Double distribution efficiency can not be less than " +
                    MIN_DISTRIBUTION_EFFICIENCY + " or greater than " + MAX_DISTRIBUTION_EFFICIENCY + '!');

        this.distributionEfficiency = distributionEfficiency;
    }

    public double getDistributionEfficiency() {
        return distributionEfficiency;
    }

    @Override
    public double getSpeedBonus() {
        return super.getSpeedBonus() * distributionEfficiency;
    }

    @Override
    public double getEnergyConsumption() {
        return super.getEnergyConsumption() * distributionEfficiency;
    }

    @Override
    public double getProductivityBonus() {
        return super.getProductivityBonus() * distributionEfficiency;
    }

    @Override
    public double getPollutionBonus() {
        return super.getPollutionBonus() * distributionEfficiency;
    }
}

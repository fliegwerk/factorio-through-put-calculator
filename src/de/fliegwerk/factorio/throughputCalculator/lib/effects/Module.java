package de.fliegwerk.factorio.throughputCalculator.lib.effects;

import java.util.Objects;

public class Module {
    public static final double MIN_MULTIPLIER = 0.1;
    public static final double MAX_MULTIPLIER = 100;

    private final String name;
    private final double energyConsumption;
    private final double speedBonus;
    private final double productivityBonus;
    private final double pollutionBonus;

    public Module(String name,
                  double energyConsumption, double speedBonus, int productivityBonus, int pollutionBonus) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("String name can not be null or empty!");
        if (energyConsumption < MIN_MULTIPLIER || energyConsumption > MAX_MULTIPLIER)
            throw new IllegalArgumentException("Double energy multiplier can not be less than " +
                    MIN_MULTIPLIER + " or greater than " + MAX_MULTIPLIER + '!');
        if (speedBonus < MIN_MULTIPLIER || speedBonus > MAX_MULTIPLIER)
            throw new IllegalArgumentException("Double speed multiplier can not be less than " +
                    MIN_MULTIPLIER + " or greater than " + MAX_MULTIPLIER + '!');
        if (productivityBonus < MIN_MULTIPLIER || productivityBonus > MAX_MULTIPLIER)
            throw new IllegalArgumentException("Double productivity multiplier can not be less than " +
                    MIN_MULTIPLIER + " or greater than " + MAX_MULTIPLIER + '!');
        if (pollutionBonus < MIN_MULTIPLIER || pollutionBonus > MAX_MULTIPLIER)
            throw new IllegalArgumentException("Double pollution multiplier can not be less than " +
                    MIN_MULTIPLIER + " or greater than " + MAX_MULTIPLIER + '!');

        this.name = name.trim();
        this.energyConsumption = energyConsumption;
        this.speedBonus = speedBonus;
        this.productivityBonus = productivityBonus;
        this.pollutionBonus = pollutionBonus;
    }

    public String getName() {
        return name;
    }

    public double getEnergyConsumption() {
        return energyConsumption;
    }

    public double getSpeedBonus() {
        return speedBonus;
    }

    public double getProductivityBonus() {
        return productivityBonus;
    }

    public double getPollutionBonus() {
        return pollutionBonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Module)) return false;
        Module module = (Module) o;
        return Double.compare(module.energyConsumption, energyConsumption) == 0 &&
                Double.compare(module.speedBonus, speedBonus) == 0 &&
                Double.compare(module.productivityBonus, productivityBonus) == 0 &&
                Double.compare(module.pollutionBonus, pollutionBonus) == 0 &&
                name.equals(module.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, energyConsumption, speedBonus, productivityBonus, pollutionBonus);
    }

    @Override
    public String toString() {
        return "Module{" +
                "name='" + name + '\'' +
                ", energyConsumption=" + energyConsumption +
                ", speedBonus=" + speedBonus +
                ", productivityBonus=" + productivityBonus +
                ", pollutionBonus=" + pollutionBonus +
                '}';
    }
}

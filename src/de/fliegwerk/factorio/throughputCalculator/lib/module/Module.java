package de.fliegwerk.factorio.throughputCalculator.lib.module;

import java.util.Objects;

public class Module {
    public static final double MIN_MULTIPLIER = 0.1;
    public static final double MAX_MULTIPLIER = 100;

    private final String name;
    private final double energyMultiplier;
    private final double speedMultiplier;
    private final double productivityMultiplier;
    private final double pollutionMultiplier;

    public Module(String name,
                  double energyMultiplier, double speedMultiplier, int productivityMultiplier, int pollutionMultiplier) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("String name can not be null or empty!");
        if (energyMultiplier < MIN_MULTIPLIER || energyMultiplier > MAX_MULTIPLIER)
            throw new IllegalArgumentException("Double energy multiplier can not be less than " +
                    MIN_MULTIPLIER + " or greater than " + MAX_MULTIPLIER + '!');
        if (speedMultiplier < MIN_MULTIPLIER || speedMultiplier > MAX_MULTIPLIER)
            throw new IllegalArgumentException("Double speed multiplier can not be less than " +
                    MIN_MULTIPLIER + " or greater than " + MAX_MULTIPLIER + '!');
        if (productivityMultiplier < MIN_MULTIPLIER || productivityMultiplier > MAX_MULTIPLIER)
            throw new IllegalArgumentException("Double productivity multiplier can not be less than " +
                    MIN_MULTIPLIER + " or greater than " + MAX_MULTIPLIER + '!');
        if (pollutionMultiplier < MIN_MULTIPLIER || pollutionMultiplier > MAX_MULTIPLIER)
            throw new IllegalArgumentException("Double pollution multiplier can not be less than " +
                    MIN_MULTIPLIER + " or greater than " + MAX_MULTIPLIER + '!');

        this.name = name.trim();
        this.energyMultiplier = energyMultiplier;
        this.speedMultiplier = speedMultiplier;
        this.productivityMultiplier = productivityMultiplier;
        this.pollutionMultiplier = pollutionMultiplier;
    }

    public String getName() {
        return name;
    }

    public double getEnergyMultiplier() {
        return energyMultiplier;
    }

    public double getSpeedMultiplier() {
        return speedMultiplier;
    }

    public double getProductivityMultiplier() {
        return productivityMultiplier;
    }

    public double getPollutionMultiplier() {
        return pollutionMultiplier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Module)) return false;
        Module module = (Module) o;
        return Double.compare(module.energyMultiplier, energyMultiplier) == 0 &&
                Double.compare(module.speedMultiplier, speedMultiplier) == 0 &&
                Double.compare(module.productivityMultiplier, productivityMultiplier) == 0 &&
                Double.compare(module.pollutionMultiplier, pollutionMultiplier) == 0 &&
                name.equals(module.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, energyMultiplier, speedMultiplier, productivityMultiplier, pollutionMultiplier);
    }

    @Override
    public String toString() {
        return "Module{" +
                "name='" + name + '\'' +
                ", energyMultiplier=" + energyMultiplier +
                ", speedMultiplier=" + speedMultiplier +
                ", productivityMultiplier=" + productivityMultiplier +
                ", pollutionMultiplier=" + pollutionMultiplier +
                '}';
    }
}

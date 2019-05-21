package de.fliegwerk.factorio.throughputcalc.modules;

import java.util.EnumMap;
import java.util.Objects;

public class Module {

    public static double sumBonus(Module[] modules, EffectType effectType) {
        double sum = 0.0;
        for (Module module : modules) {
            sum += (module != null ? module.getBonus(effectType) : 0.0);
        }
        return sum;
    }

    private final String name;
    private final EnumMap<EffectType, Double> effects;

    public Module(String name, EnumMap<EffectType, Double> effects) {
        this.name = name.trim();
        this.effects = effects;
    }

    public String getName() {
        return name;
    }

    public double getBonus(EffectType effectType) {
        return effects.getOrDefault(effectType, 0.0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Module)) return false;
        Module module = (Module) o;
        return name.equals(module.name) &&
                effects.equals(module.effects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, effects);
    }

    @Override
    public String toString() {
        return "Module{" +
                "name='" + name + '\'' +
                ", effects=" + effects +
                '}';
    }
}

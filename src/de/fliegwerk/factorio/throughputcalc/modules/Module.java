package de.fliegwerk.factorio.throughputcalc.modules;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Objects;

/**
 * This class represents a module with a name and a EnumMap of EffectTypes.
 * It can be used in a constructed machine.
 *
 * @see EffectType
 * @see de.fliegwerk.factorio.throughputcalc.machines.MachineConstructor
 *
 * @author Ludwig Richter (richter@fliegwerk.com)
 * @version 1.1.2
 * @since 11.0.2
 */
public class Module {

    /**
     * Sum up all bonuses of given modules array specified by an {@link EffectType}.
     * If a element of the array (module) is {@code null} it will be ignored.
     * @param modules array of modules
     * @param effectType specified effect type
     * @return sum of specified bonuses
     *
     * @throws NullPointerException if array of modules is {@code null}
     */
    public static double sumBonus(@NotNull Module[] modules, EffectType effectType) {
        Objects.requireNonNull(modules, "Array modules can not be null!");
        double sum = 0.0;
        for (Module module : modules) {
            sum += (module != null ? module.getBonus(effectType) : 0.0);
        }
        return sum;
    }

    private final String name;
    private final EnumMap<EffectType, Double> effects;

    /**
     * Creates a new Module with a given name and defined effects.
     * @param name name of this module
     * @param effects {@link EnumMap} defined bonuses for this module
     *
     * @throws NullPointerException if string name or enum map effects if {@code null}
     * @throws IndexOutOfBoundsException if string name trimmed is empty
     */
    public Module(@NotNull String name, @NotNull EnumMap<EffectType, Double> effects) {
        Objects.requireNonNull(name, "String name can not be null!");
        if (name.trim().isEmpty())
            throw new IllegalArgumentException("String name can not be empty!");
        Objects.requireNonNull(effects, "EnumMap effects can not be null!");

        this.name = name.trim();
        this.effects = effects;
    }

    /**
     * Returns the name of this module.
     * @return name of module
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the bonus of this module specified by {@link EffectType}.
     * If the specified effect type is not defined in this module it always return {@code 0.0}.
     * @param effectType specified effect type
     * @return bonus of effect type
     */
    public double getBonus(EffectType effectType) {
        return effects.getOrDefault(effectType, 0.0);
    }

    @Contract(value = "null -> false", pure = true)
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

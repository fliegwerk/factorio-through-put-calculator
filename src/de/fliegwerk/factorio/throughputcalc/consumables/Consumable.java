package de.fliegwerk.factorio.throughputcalc.consumables;

import org.jetbrains.annotations.Contract;

import java.util.Objects;

/**
 * This abstract class represents a basic structure for a consumable.
 * It can be used in recipes as ingredient and result specification.
 *
 * @see de.fliegwerk.factorio.throughputcalc.recipes.Recipe
 * @see Item
 * @see Fluid
 *
 * @author Ludwig Richter (richter@fliegwerk.com)
 * @version 1.1.2
 * @since 11.0.2
 */
public abstract class Consumable {
    private final String name;

    /**
     * Creates a new consumable with a given name.
     * @param name name of this consumable
     *
     * @throws NullPointerException if string name is {@code null}
     * @throws IllegalArgumentException if string name trimmed is empty
     */
    public Consumable(String name) {
        Objects.requireNonNull(name, "String name can not be null!");
        if (name.trim().isEmpty())
            throw new IllegalArgumentException("String name can not be empty!");

        this.name = name.trim();
    }

    /**
     * Returns the name of this recipe.
     * @return name of recipe
     */
    public String getName() {
        return name;
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Consumable)) return false;
        Consumable that = (Consumable) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Consumable{" +
                "name='" + name + '\'' +
                '}';
    }
}

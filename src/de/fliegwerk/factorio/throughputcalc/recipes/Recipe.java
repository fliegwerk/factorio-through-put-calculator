package de.fliegwerk.factorio.throughputcalc.recipes;

import de.fliegwerk.factorio.throughputcalc.CheckElements;
import de.fliegwerk.factorio.throughputcalc.consumables.Consumable;
import de.fliegwerk.factorio.throughputcalc.machines.MachineType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;

/**
 * This class represents a recipe.
 * It contains a {@link Map} with {@link Consumable} types as ingredients and results as well as a crafting time and an intermediate tag.
 * It also includes a possible {@link MachineType} in which type the crafting of this recipe is possible.
 *
 * @see Consumable
 * @see MachineType
 *
 * @author Ludwig Richter (richter@fliegwerk.com)
 * @version 1.1.2
 * @since 11.0.2
 */
public class Recipe {
    /**
     * Number of minimal consumables in the maps ingredients and results
     */
    public static final int MIN_CONSUMABLES = 1;
    /**
     * Number of minimal crafting time for all recipes
     */
    public static final double MIN_CRAFTING_TIME = 0.01; /* seconds */

    private final Map<Consumable, Integer> ingredients;
    private final Map<Consumable, Integer> results;
    private final MachineType machineType;
    private final double craftingTime; /* seconds */
    private final boolean intermediate;

    /**
     * Creates a new recipe with given
     * ingredients, results, a possible machine type, a crafting time and an intermediate tag.
     * @param ingredients {@link Map} with {@link Consumable} that describes the ingredients
     * @param results {@link Map} with {@link Consumable} that describes the results
     * @param machineType machine type for possible crafting
     * @param craftingTime time in seconds to craft this recipe
     * @param intermediate {@code true} if this recipe is intermediate, otherwise {@code false}
     *
     * @throws NullPointerException if the map ingredients or results or the machine type are {@code null}
     * @throws IndexOutOfBoundsException if the size of the map ingredients or results are
     * less than {@value MIN_CONSUMABLES}
     * @throws IndexOutOfBoundsException if the crafting time is less than {@value MIN_CRAFTING_TIME}
     */
    public Recipe(@NotNull Map<Consumable, Integer> ingredients, @NotNull Map<Consumable, Integer> results,
                  @NotNull MachineType machineType, double craftingTime, boolean intermediate) {
        Objects.requireNonNull(ingredients, "Map ingredients can not be null!");
        CheckElements.checkMin(ingredients.size(), MIN_CONSUMABLES, "Size of map ingredients");
        Objects.requireNonNull(results, "Map results can not be null!");
        CheckElements.checkMin(results.size(), MIN_CONSUMABLES, "Size of map results");
        Objects.requireNonNull(machineType, "Machine type can not be null!");
        CheckElements.checkMin(craftingTime, MIN_CRAFTING_TIME, "Double crafting time");

        this.ingredients = ingredients;
        this.results = results;
        this.machineType = machineType;
        this.craftingTime = craftingTime;
        this.intermediate = intermediate;
    }

    /**
     * Returns the ingredients as type of a {@link Consumable} {@link Map} of this recipe.
     * @return ingredients map
     */
    public Map<Consumable, Integer> getIngredients() {
        return ingredients;
    }

    /**
     * Returns the results as type of a {@link Consumable} {@link Map} of this recipe.
     * @return results map
     */
    public Map<Consumable, Integer> getResults() {
        return results;
    }

    /**
     * Return the possible {@link MachineType} in which type the crafting of this recipe is possible.
     * @return machine type for possible crafting
     */
    public MachineType getMachineType() {
        return machineType;
    }

    /**
     * Returns the time in seconds to craft this recipe.
     * @return crafting time in seconds
     */
    public double getCraftingTime() {
        return craftingTime;
    }

    /**
     * Returns {@code true} if this recipe is an intermediate recipe, otherwise {@code false}.
     * @return {@code true} if intermediate, otherwise {@code false}
     */
    public boolean isIntermediate() {
        return intermediate;
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe)) return false;
        Recipe recipe = (Recipe) o;
        return Double.compare(recipe.craftingTime, craftingTime) == 0 &&
                intermediate == recipe.intermediate &&
                ingredients.equals(recipe.ingredients) &&
                results.equals(recipe.results) &&
                machineType == recipe.machineType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredients, results, machineType, craftingTime, intermediate);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "ingredients=" + ingredients +
                ", results=" + results +
                ", machineType=" + machineType +
                ", craftingTime=" + craftingTime +
                ", intermediate=" + intermediate +
                '}';
    }
}

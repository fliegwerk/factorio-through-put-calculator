package de.fliegwerk.factorio.throughputcalc.recipes;

import de.fliegwerk.factorio.throughputcalc.consumables.Consumable;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;

public class Recipe {
    public static final int MIN_CONSUMABLES = 1;
    public static final double MIN_CRAFTING_TIME = 0.01; /* seconds */
    public static final double MAX_CRAFTING_TIME = 1_000; /* seconds */

    private final Map<Consumable, Integer> ingredients;
    private final Map<Consumable, Integer> results;
    private final double craftingTime; /* seconds */
    private final boolean intermediate;

    public Recipe(@NotNull Map<Consumable, Integer> ingredients, @NotNull Map<Consumable, Integer> results,
                  double craftingTime, boolean intermediate) {
        Objects.requireNonNull(ingredients, "Map ingredients can not be null!");
        Objects.requireNonNull(results, "Map results can not be null!");
        if (ingredients.size() < MIN_CONSUMABLES)
            throw new IndexOutOfBoundsException("Size of map ingredients can not be less than " + MIN_CONSUMABLES + '!');
        if (results.size() < MIN_CONSUMABLES)
            throw new IndexOutOfBoundsException("Size of map results can not be less than " + MIN_CONSUMABLES + '!');
        if (craftingTime < MIN_CRAFTING_TIME || craftingTime > MAX_CRAFTING_TIME)
            throw new IndexOutOfBoundsException("Double crafting time can not be less than " + MIN_CRAFTING_TIME + " or greater than " + MAX_CRAFTING_TIME + '!');

        this.ingredients = ingredients;
        this.results = results;
        this.craftingTime = craftingTime;
        this.intermediate = intermediate;
    }

    public Map<Consumable, Integer> getIngredients() {
        return ingredients;
    }

    public Map<Consumable, Integer> getResults() {
        return results;
    }

    public double getCraftingTime() {
        return craftingTime;
    }

    public boolean isIntermediate() {
        return intermediate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe)) return false;
        Recipe recipe = (Recipe) o;
        return Double.compare(recipe.craftingTime, craftingTime) == 0 &&
                intermediate == recipe.intermediate &&
                ingredients.equals(recipe.ingredients) &&
                results.equals(recipe.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredients, results, craftingTime, intermediate);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "ingredients=" + ingredients +
                ", results=" + results +
                ", craftingTime=" + craftingTime +
                ", intermediate=" + intermediate +
                '}';
    }
}

package de.fliegwerk.factorio.throughputCalculator.lib.recipe;

import de.fliegwerk.factorio.throughputCalculator.lib.machines.Machine;
import de.fliegwerk.factorio.throughputCalculator.lib.consumable.Consumable;
import de.fliegwerk.factorio.throughputCalculator.lib.consumable.ConsumableCount;
import de.fliegwerk.factorio.throughputCalculator.lib.machines.MachineType;

import java.util.List;
import java.util.Objects;

public class Recipe {
    public static final double MIN_CRAFTING_TIME = 0; /* seconds */
    public static final double MAX_CRAFTING_TIME = 1_000; /* seconds */
    public static final int MIN_NUMBER_OF_COLLECTIONS = 1;
    public static final int MAX_NUMBER_OF_COLLECTIONS = 1_000;
    public static final int MIN_NUMBER_OF_PRODUCERS = 1;
    public static final int MAX_NUMBER_OF_PRODUCERS = 1_000;

    private final String name;
    private final List<ConsumableCount> ingredients;
    private final List<ConsumableCount> results;
    private final List<MachineType> allowedMachineTypes;
    private final double craftingTime;
    private final boolean intermediate;

    public Recipe(String name, List<ConsumableCount> ingredients, List<ConsumableCount> results, List<MachineType> allowedMachineTypes,
                  double craftingTime, boolean intermediate) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("String name can not be null or empty!");
        if (ingredients == null || ingredients.isEmpty())
            throw new IllegalArgumentException("List ingredients can not be null or empty!");
        if (results == null || results.isEmpty())
            throw new IllegalArgumentException("List results can not be null or empty!");
        if (allowedMachineTypes == null || allowedMachineTypes.isEmpty())
            throw new IllegalArgumentException("List allowed assembling machines can not be null or empty!");
        if (ingredients.size() < MIN_NUMBER_OF_COLLECTIONS || ingredients.size() > MAX_NUMBER_OF_COLLECTIONS)
            throw new IllegalArgumentException("Size of list ingredients can not be less than " +
                    MIN_NUMBER_OF_COLLECTIONS + " or greater than " + MAX_NUMBER_OF_COLLECTIONS + '!');
        if (results.size() < MIN_NUMBER_OF_COLLECTIONS || results.size() > MAX_NUMBER_OF_COLLECTIONS)
            throw new IllegalArgumentException("Size of list results can not be less than " +
                    MIN_NUMBER_OF_COLLECTIONS + " or greater than " + MAX_NUMBER_OF_COLLECTIONS + '!');
        if (allowedMachineTypes.size() < MIN_NUMBER_OF_PRODUCERS || allowedMachineTypes.size() > MAX_NUMBER_OF_PRODUCERS)
            throw new IllegalArgumentException("Size of list allowed producers can not be less than " +
                    MIN_NUMBER_OF_PRODUCERS + " or greater than " + MAX_NUMBER_OF_PRODUCERS + '!');
        if (craftingTime < MIN_CRAFTING_TIME || craftingTime > MAX_CRAFTING_TIME)
            throw new IllegalArgumentException("Double craftingTime can not be less than " +
                    MIN_CRAFTING_TIME + " or greater than " + MAX_CRAFTING_TIME + '!');

        this.name = name.trim();
        this.ingredients = ingredients;
        this.results = results;
        this.allowedMachineTypes = allowedMachineTypes;
        this.craftingTime = craftingTime;
        this.intermediate = intermediate;
    }

    public String getName() {
        return name;
    }

    public boolean isIngredient(Consumable consumable) {
        return containsConsumable(ingredients, consumable);
    }

    public boolean isResult(Consumable consumable) {
        return containsConsumable(results, consumable);
    }

    public boolean isAllowedIn(MachineType machineType) {
        return allowedMachineTypes.contains(machineType);
    }

    public boolean isAllowedIn(Machine machine) {
        return isAllowedIn(machine.getMachineType());
    }

    public List<ConsumableCount> getIngredients() {
        return ingredients;
    }

    public List<ConsumableCount> getResults() {
        return results;
    }

    public ConsumableCount getConsumableCount(Consumable consumable) {
        for (ConsumableCount consumableCount : ingredients) {
            if (consumableCount.getConsumable() == consumable)
                return consumableCount;
        }
        for (ConsumableCount consumableCount : results) {
            if (consumableCount.getConsumable() == consumable)
                return consumableCount;
        }
        return null;
    }

    public List<MachineType> getAllowedMachineTypes() {
        return allowedMachineTypes;
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
                results.equals(recipe.results) &&
                allowedMachineTypes.equals(recipe.allowedMachineTypes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredients, results, allowedMachineTypes, craftingTime, intermediate);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "name=" + name +
                ", ingredients=" + ingredients +
                ", results=" + results +
                ", allowedMachineTypes=" + allowedMachineTypes +
                ", craftingTime=" + craftingTime +
                ", intermediate=" + intermediate +
                '}';
    }

    // private

    private static boolean containsConsumable(List<ConsumableCount> consumableCountList, Consumable consumable) {
        for (ConsumableCount consumableCount : consumableCountList) {
            if (consumableCount.getConsumable().equals(consumable))
                return true;
        }
        return false;
    }
}

package de.fliegwerk.factorio.throughputCalculator.lib;

import de.fliegwerk.factorio.throughputCalculator.lib.calculator.Calculator;
import de.fliegwerk.factorio.throughputCalculator.lib.consumable.Consumable;
import de.fliegwerk.factorio.throughputCalculator.lib.machines.Machine;
import de.fliegwerk.factorio.throughputCalculator.lib.recipe.Recipe;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        File dataJSON = new File("./res/data.json");
        Scanner scanner = new Scanner(System.in);

        System.out.println("> Generate new calculator");
        Calculator myCalculator = new Calculator();
        System.out.println("ID: " + myCalculator.getId());

        try {
            System.out.println("> Read from File: " + dataJSON.getPath());
             Calculator.readJSONFile(myCalculator, dataJSON);
        } catch (IOException | IllegalArgumentException exception) {
            System.err.println("Can not create new Calculator!");
            exception.printStackTrace();
            return;
        }

        System.out.println("=== Consumables ===");
        for (Consumable consumable : myCalculator.getConsumables()) {
            System.out.println(consumable);
        }

        System.out.println("=== Assembling machines ===");
        for (Machine machine : myCalculator.getMachines()) {
            System.out.println(machine);
        }

        System.out.println("=== Recipes ===");
        for (Recipe recipe : myCalculator.getRecipes()) {
            System.out.println(recipe);
        }

        System.out.println("> Add dependency recipes");
        myCalculator.addDependencyRecipes();

        System.out.print("Find recipe: ");
        String input = scanner.nextLine();
        while (!input.equals("exit")) {
            Consumable consumable = myCalculator.findConsumable(input);
            System.out.println("Found consumable: " + consumable);
            List<Recipe> recipes = myCalculator.findRecipes(consumable, Recipe::isResult);
            Recipe recipe = (recipes.isEmpty() ? null : recipes.get(0));
            System.out.println("Found recipe: " + recipe);

            System.out.print("Find recipe: ");
            input = scanner.nextLine();
        }

        scanner.close();
    }
}

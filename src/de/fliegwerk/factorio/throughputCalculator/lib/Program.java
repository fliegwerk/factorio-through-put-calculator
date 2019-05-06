package de.fliegwerk.factorio.throughputCalculator.lib;

import de.fliegwerk.factorio.throughputCalculator.lib.calculator.Calculator;
import de.fliegwerk.factorio.throughputCalculator.lib.consumable.Consumable;
import de.fliegwerk.factorio.throughputCalculator.lib.producer.AssemblingMachine;
import de.fliegwerk.factorio.throughputCalculator.lib.recipe.Recipe;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;

public class Program {
    public static void main(String[] args) {
        File dataJSON = new File("./res/data.json");

        Calculator myCalculator;

        try {
             myCalculator = Calculator.newCalcFromJSONFile(dataJSON);
        } catch (IOException | IllegalArgumentException exception) {
            System.err.println("Can not create new Calculator!");
            exception.printStackTrace();
            return;
        }

        System.out.println("ID: " + myCalculator.getId());
        System.out.println("=== Consumables ===");
        for (Consumable consumable : myCalculator.getConsumables()) {
            System.out.println(consumable);
        }

        System.out.println("=== Assembling machines ===");
        for (AssemblingMachine assemblingMachine : myCalculator.getAssemblingMachines()) {
            System.out.println(assemblingMachine);
        }

        System.out.println("=== Recipes ===");
        for (Recipe recipe : myCalculator.getRecipes()) {
            System.out.println(recipe);
        }

        myCalculator.buildDependencyTree();
    }
}

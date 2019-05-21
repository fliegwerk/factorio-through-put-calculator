package de.fliegwerk.factorio.throughputcalc;

import de.fliegwerk.factorio.throughputcalc.calculator.Calculator;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator myCalculator = new Calculator();

        System.out.print("Path to json file: res/");
        String path = "res/" + scanner.nextLine();

        readJSONFile(myCalculator, path);

        System.out.println("=== CONSUMABLES ===");
        for (var element : myCalculator.getConsumables()) {
            System.out.println(element);
        }

        System.out.println("=== EFFECT MACHINES ===");
        for (var element : myCalculator.getEffectMachinePrototypes()) {
            System.out.println(element);
        }

        System.out.println("=== CRAFTING MACHINES ===");
        for (var element : myCalculator.getCraftingMachinePrototypes()) {
            System.out.println(element);
        }

        System.out.println("=== RECIPES ===");
        for (var element : myCalculator.getRecipes()) {
            System.out.println(element);
        }

        System.out.printf("Find item:%n> ");
        String name = scanner.nextLine();
        System.out.println(myCalculator.findConsumable(name));

        scanner.close();
    }

    public static void readJSONFile(Calculator calculator, String path) {
        JSONObject root = null;
        try {
            root = new JSONObject(Files.readString(new File(path).toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        calculator.readJSON(root);
    }
}

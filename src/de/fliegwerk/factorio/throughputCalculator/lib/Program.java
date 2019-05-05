package de.fliegwerk.factorio.throughputCalculator.lib;

import de.fliegwerk.factorio.throughputCalculator.lib.calculator.Calculator;

import java.io.File;
import java.io.IOException;

public class Program {
    public static void main(String[] args) {
        File dataJSON = new File("./res/data.json");

        Calculator myCalculator;

        try {
             myCalculator = Calculator.readJSONFile(dataJSON);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        }

        System.out.println("ID: " + myCalculator.getId());
    }
}

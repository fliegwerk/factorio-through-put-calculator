package de.fliegwerk.factorio.throughputCalculator.lib.calculator;

import de.fliegwerk.factorio.throughputCalculator.lib.consumable.Consumable;
import de.fliegwerk.factorio.throughputCalculator.lib.consumable.ConsumableCount;
import de.fliegwerk.factorio.throughputCalculator.lib.consumable.Fluid;
import de.fliegwerk.factorio.throughputCalculator.lib.consumable.Item;
import de.fliegwerk.factorio.throughputCalculator.lib.producer.AssemblingMachine;
import de.fliegwerk.factorio.throughputCalculator.lib.recipe.Recipe;
import de.fliegwerk.factorio.throughputCalculator.lib.calculator.saxHandlers.AssemblingMachineHandler;
import de.fliegwerk.factorio.throughputCalculator.lib.calculator.saxHandlers.FluidHandler;
import de.fliegwerk.factorio.throughputCalculator.lib.calculator.saxHandlers.ItemHandler;
import de.fliegwerk.factorio.throughputCalculator.lib.calculator.saxHandlers.RecipeHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Calculator {

    // constants

    private static int nextId = 0;

    // static methods

    public static Calculator readXMLFiles(File itemXML, File fluidXML, File assemblingMachineXML, File recipeXML)
            throws IOException {

        Calculator calculator = new Calculator();

        try {
            System.out.println("-> Creating objects");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            System.out.println("-> Parse item XML");
            ItemHandler itemHandler = new ItemHandler(calculator);
            saxParser.parse(itemXML, itemHandler);

            System.out.println("-> Parse fluid XML");
            FluidHandler fluidHandler = new FluidHandler(calculator);
            saxParser.parse(fluidXML, fluidHandler);

            System.out.println("-> Parse assembling machine XML");
            AssemblingMachineHandler assemblingMachineHandler = new AssemblingMachineHandler(calculator);
            saxParser.parse(assemblingMachineXML, assemblingMachineHandler);

            System.out.println("-> Parse recipe XML");
            RecipeHandler recipeHandler = new RecipeHandler(calculator);
            saxParser.parse(recipeXML, recipeHandler);

        } catch (ParserConfigurationException | SAXException exception) {
            System.err.println("Something wrong happened.");
            System.err.println("Can not parse XML files!");
            System.err.println("Internal handler or parser error.");
            System.err.println("Please report this error to the author!");
        }

        return calculator;
    }

    public static Calculator newCalcFromJSONFile(File dataJSON)
            throws IOException, IllegalArgumentException {
        Calculator calculator = new Calculator();
        readJSONFile(calculator, dataJSON);
        return calculator;
    }

    public static void readJSONFile(Calculator calculator, File dataJSON)
            throws IOException, IllegalArgumentException {

        try {
            System.out.println(" -> parse dataJSON");
            JSONObject data = new JSONObject(Files.readString(dataJSON.toPath()));

            System.out.println(" -> parse item array");
            JSONArray items = data.getJSONArray("items");
            parseObjects(calculator, items, (calc, item) -> {
                String name = item.getString("name");
                int stackSize = item.getInt("stacksize");

                calc.addConsumable(new Item(name, stackSize));
            });

            System.out.println(" -> parse fluid array");
            JSONArray fluids = data.getJSONArray("fluids");
            parseObjects(calculator, fluids, (calc, fluid) -> {
                String name = fluid.getString("name");
                double cValue = fluid.getDouble("cvalue");

                calc.addConsumable(new Fluid(name, cValue));
            });

            System.out.println(" -> parse assembling machine array");
            JSONArray assemblingMachines = data.getJSONArray("assembling-machines");
            parseObjects(calculator, assemblingMachines, (calc, assemblingMachine) -> {
                String name = assemblingMachine.getString("name");
                double baseCraftingSpeed = assemblingMachine.getDouble("craftingspeed");
                double baseEnergyConsumption = assemblingMachine.getDouble("energyconsumption");
                double basePollution = assemblingMachine.getDouble("pollution");
                int moduleSlots = assemblingMachine.getInt("moduleSlots");

                calc.addAssemblingMachine(
                        new AssemblingMachine(name, baseCraftingSpeed, baseEnergyConsumption, basePollution, moduleSlots)
                );
            });

            System.out.println(" -> parse recipe array");
            JSONArray recipes = data.getJSONArray("recipes");

            parseObjects(calculator, recipes, (calc, recipe) -> {
                String name = recipe.getString("name");

                List<ConsumableCount> ingredients = getConsumableCount(calc, recipe.getJSONArray("ingredients"));
                List<ConsumableCount> results = getConsumableCount(calc, recipe.getJSONArray("results"));

                double craftingTime = recipe.getDouble("craftingtime");
                boolean intermediate = recipe.getBoolean("intermediate");

                List<AssemblingMachine> assemblingMachinesList = new ArrayList<>();
                JSONArray assemblingMachinesArray = recipe.getJSONArray("allowed-machines");
                for (int i = 0; i < assemblingMachinesArray.length(); i++) {
                    JSONObject object = assemblingMachinesArray.getJSONObject(i);
                    AssemblingMachine machine = calc.findAssemblingMachine(object.getString("name"));
                    if (machine != null)
                        assemblingMachinesList.add(machine);
                }

                calc.addRecipe(
                        new Recipe(name, ingredients, results, assemblingMachinesList,
                                craftingTime, intermediate)
                );
            });
        } catch (JSONException je) {
            throw new IllegalArgumentException("Can not parse given JSON file!\n" + je.getMessage());
        }
    }

    private static void parseObjects(Calculator calculator, JSONArray array, JSONObjectToCalculator function) {
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            if (!object.isEmpty()) {
                try {
                    function.convertJSONObjectToCalculator(calculator, object);

                } catch (JSONException | IllegalArgumentException exception) {
                    String name;
                    try {
                        name = object.getString("name");
                    } catch (JSONException je) {
                        name = Integer.toString(i);
                    }
                    System.err.println("Can not parse json object \"" + name + "\"!");
                    exception.printStackTrace();
                }
            }
        }
    }

    private static List<ConsumableCount> getConsumableCount(Calculator calculator, JSONArray array) {
        List<ConsumableCount> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject item = array.getJSONObject(i);

            Consumable consumable = calculator.findConsumable(item.getString("name"));
            int count = item.getInt("count");

            list.add(new ConsumableCount(consumable, count));
        }
        return list;
    }

    // private fields

    private final int id;
    private List<Consumable> consumables;
    private List<AssemblingMachine> assemblingMachines;
    private List<Recipe> recipes;

    // constructors

    public Calculator(List<Consumable> consumables, List<AssemblingMachine> assemblingMachines, List<Recipe> recipes) {
        this.id = nextId++;

        if (consumables != null)
            this.consumables = consumables;
        else
            this.consumables = new ArrayList<>();

        if (assemblingMachines != null)
            this.assemblingMachines = assemblingMachines;
        else
            this.assemblingMachines = new ArrayList<>();

        if (recipes != null)
            this.recipes = recipes;
        else
            this.recipes = new ArrayList<>();
    }

    public Calculator() {
        this(null, null, null);
    }

    // methods

    public void addJSONFile(File dataJSON)
            throws IOException, IllegalArgumentException {
        readJSONFile(this, dataJSON);
    }

    public int getId() {
        return id;
    }

    public boolean addConsumable(Consumable consumable) {
        if (!consumables.contains(consumable))
            return consumables.add(consumable);
        else
            return false;
    }

    public boolean addConsumable(List<Consumable> addConsumables) {
        return consumables.addAll(addConsumables);
    }

    public List<Consumable> getConsumables() {
        return consumables;
    }

    public Consumable findConsumable(String name) {
        for (Consumable consumable : consumables) {
            if (consumable.getName().equals(name))
                return consumable;
        }
        return null;
    }

    public boolean addAssemblingMachine(AssemblingMachine assemblingMachine) {
        if (!assemblingMachines.contains(assemblingMachine))
            return assemblingMachines.add(assemblingMachine);
        else
            return false;
    }

    public boolean addAssemblingMachine(List<AssemblingMachine> addAssemblingMachines) {
        return assemblingMachines.addAll(addAssemblingMachines);
    }

    public List<AssemblingMachine> getAssemblingMachines() {
        return assemblingMachines;
    }

    public AssemblingMachine findAssemblingMachine(String name) {
        for (AssemblingMachine assemblingMachine : assemblingMachines) {
            if (assemblingMachine.getName().equals(name))
                return assemblingMachine;
        }
        return null;
    }

    public boolean addRecipe(Recipe recipe) {
        if (!recipes.contains(recipe))
            return recipes.add(recipe);
        else
            return false;
    }

    public boolean addRecipes(List<Recipe> addRecipes) {
        return recipes.addAll(addRecipes);
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void buildDependencyTree() {
        // TODO: Dependency tree
    }
}

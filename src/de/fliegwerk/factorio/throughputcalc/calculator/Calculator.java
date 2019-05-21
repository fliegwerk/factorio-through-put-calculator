package de.fliegwerk.factorio.throughputcalc.calculator;

import de.fliegwerk.factorio.throughputcalc.consumables.*;
import de.fliegwerk.factorio.throughputcalc.machines.*;
import de.fliegwerk.factorio.throughputcalc.recipes.Recipe;

import org.jetbrains.annotations.NotNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;
import java.util.function.Consumer;

public class Calculator {

    private static int nextId = 0;

    private final int id;
    private List<Consumable> consumables;
    private List<EffectMachinePrototype> effectMachinePrototypes;
    private List<CraftingMachinePrototype> craftingMachinePrototypes;
    private List<Recipe> recipes;

    private EnumMap<MachineType, MachineConstructor> assignedMachines;

    public Calculator() {
        this.id = nextId++;
        this.consumables = new ArrayList<>();
        this.effectMachinePrototypes = new ArrayList<>();
        this.craftingMachinePrototypes = new ArrayList<>();
        this.recipes = new ArrayList<>();

        this.assignedMachines = new EnumMap<>(MachineType.class);
    }

    public int getId() {
        return id;
    }

    public List<Consumable> getConsumables() {
        return consumables;
    }

    public List<EffectMachinePrototype> getEffectMachinePrototypes() {
        return effectMachinePrototypes;
    }

    public List<CraftingMachinePrototype> getCraftingMachinePrototypes() {
        return craftingMachinePrototypes;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public EnumMap<MachineType, MachineConstructor> getAssignedMachines() {
        return assignedMachines;
    }

    public Consumable findConsumable(String name) {
        for (Consumable consumable : consumables) {
            if (consumable.getName().equals(name))
                return consumable;
        }
        // System.err.printf("Can not find consumable [\"%s\"]!%n", name);
        return null;
    }

    public void readJSON(JSONObject root) {
        Objects.requireNonNull(root, "JSONObject root can not be null!");

        findJSONArray(root, "items", this::addItems);
        findJSONArray(root, "fluids", this::addFluids);
        findJSONArray(root, "effect-machines", this::addEffectMachinePrototypes);
        findJSONArray(root, "crafting-machines", this::addCraftingMachinePrototypes);
        findJSONArray(root, "recipes", this::addRecipes);
    }

    public void addItems(JSONArray array) {
        parseJSONArray(array, (object) -> consumables.add(
                new Item(object.getString("name"), object.getInt("stack-size"))
        ));
    }

    public void addFluids(JSONArray array) {
        parseJSONArray(array, (object) -> consumables.add(
                new Fluid(object.getString("name"), object.getDouble("c-value"))
        ));
    }

    public void addEffectMachinePrototypes(JSONArray array) {
        parseJSONArray(array, (object) -> effectMachinePrototypes.add(
                new EffectMachinePrototype(
                        object.getString("name"),
                        MachineType.effectMachine,
                        object.getDouble("energy-consumption"),
                        object.getInt("module-slots"),
                        object.getDouble("distribution-efficiency")
                )
        ));
    }

    public void addCraftingMachinePrototypes(JSONArray array) {
        parseJSONArray(array, (object) -> craftingMachinePrototypes.add(
                new CraftingMachinePrototype(
                        object.getString("name"),
                        MachineType.getMachineType(object.getString("machine-type")),
                        object.getDouble("energy-consumption"),
                        object.getInt("module-slots"),
                        object.getDouble("crafting-speed"),
                        object.getDouble("productivity"),
                        object.getDouble("pollution")
                )
        ));
    }

    public void addRecipes(JSONArray array) {
        parseJSONArray(array, (object) -> recipes.add(
                new Recipe(
                    getRecipeConsumables(object.getJSONArray("ingredients")),
                    getRecipeConsumables(object.getJSONArray("results")),
                    MachineType.getMachineType(object.getString("machine-type")),
                    object.getDouble("crafting-time"),
                    object.getBoolean("intermediate")
                )
        ));
    }

    private Map<Consumable, Integer> getRecipeConsumables(JSONArray array) {
        Map<Consumable, Integer> result = new HashMap<>(array.length());

        for (int i = 0; i < array.length(); i++) {
            Consumable consumable = findConsumable(array.getJSONObject(i).getString("name"));
            Objects.requireNonNull(consumable, "Consumable can not be null!");
            result.put(
                    consumable,
                    array.getJSONObject(i).getInt("count")
            );
        }

        return result;
    }



    private void parseJSONArray(@NotNull JSONArray array, Consumer<JSONObject> function) {
        for (int i = 0; i < array.length(); i++)
            try {
                function.accept(array.getJSONObject(i));

            } catch (NullPointerException | IllegalArgumentException | IndexOutOfBoundsException |
                    JSONException argException) {
                System.err.println("Can not parse JSON object number " + i + '!');
                argException.printStackTrace();
            }
    }

    private void findJSONArray(@NotNull JSONObject root, String arrayName, Consumer<JSONArray> function) {
        try {
            function.accept(root.getJSONArray(arrayName));
        } catch (JSONException je) {
            System.err.printf("Can not find JSON array [\"%s\"]!%nMaybe missing something?%n", arrayName);
        }
    }

    //
//    // constants
//
//    private static int nextId = 0;
//
//    // static methods
//
//    public static Calculator readXMLFiles(File itemXML, File fluidXML, File assemblingMachineXML, File recipeXML)
//            throws IOException {
//
//        Calculator calculator = new Calculator();
//
//        try {
//            System.out.println("-> Creating objects");
//            SAXParserFactory factory = SAXParserFactory.newInstance();
//            SAXParser saxParser = factory.newSAXParser();
//
//            System.out.println("-> Parse item XML");
//            ItemHandler itemHandler = new ItemHandler(calculator);
//            saxParser.parse(itemXML, itemHandler);
//
//            System.out.println("-> Parse fluid XML");
//            FluidHandler fluidHandler = new FluidHandler(calculator);
//            saxParser.parse(fluidXML, fluidHandler);
//
//            System.out.println("-> Parse assembling machine XML");
//            AssemblingMachineHandler assemblingMachineHandler = new AssemblingMachineHandler(calculator);
//            saxParser.parse(assemblingMachineXML, assemblingMachineHandler);
//
//            System.out.println("-> Parse recipe XML");
//            RecipeHandler recipeHandler = new RecipeHandler(calculator);
//            saxParser.parse(recipeXML, recipeHandler);
//
//        } catch (ParserConfigurationException | SAXException exception) {
//            System.err.println("Something wrong happened.");
//            System.err.println("Can not parse XML files!");
//            System.err.println("Internal handler or parser error.");
//            System.err.println("Please report this error to the author!");
//        }
//
//        return calculator;
//    }
//
//    public static Calculator newCalcFromJSONFile(File dataJSON)
//            throws IOException, IllegalArgumentException {
//        Calculator calculator = new Calculator();
//        readJSONFile(calculator, dataJSON);
//        return calculator;
//    }
//
//    public static void readJSONFile(Calculator calculator, File dataJSON)
//            throws IOException, IllegalArgumentException {
//
//        try {
//            System.out.println(" -> parse dataJSON");
//            JSONObject data = new JSONObject(Files.readString(dataJSON.toPath()));
//
//            System.out.println(" -> parse item array");
//            JSONArray items = data.getJSONArray("items");
//            parseObjects(calculator, items, (calc, item) -> {
//                String name = item.getString("name");
//                int stackSize = item.getInt("stacksize");
//
//                calc.addConsumable(new Item(name, stackSize));
//            });
//
//            System.out.println(" -> parse fluid array");
//            JSONArray fluids = data.getJSONArray("fluids");
//            parseObjects(calculator, fluids, (calc, fluid) -> {
//                String name = fluid.getString("name");
//                double cValue = fluid.getDouble("cvalue");
//
//                calc.addConsumable(new Fluid(name, cValue));
//            });
//
//            System.out.println(" -> parse machine type array");
//            JSONArray machineTypes = data.getJSONArray("machine-types");
//            parseObjects(calculator, machineTypes, (calc, machineType) -> {
//                String name = machineType.getString("name");
//                calc.addMachineType(new MachineType(name));
//            });
//
//            System.out.println(" -> parse machine array");
//            JSONArray assemblingMachines = data.getJSONArray("machines");
//            parseObjects(calculator, assemblingMachines, (calc, machine) -> {
//                String name = machine.getString("name");
//                MachineType machineType = calc.findMachineType(machine.getString("machine-type"));
//                double baseCraftingSpeed = machine.getDouble("craftingspeed");
//                double baseEnergyConsumption = machine.getDouble("energyconsumption");
//                double basePollution = machine.getDouble("pollution");
//                int moduleSlots = machine.getInt("moduleSlots");
//
//                /* calc.addMachine(
//                        new Machine(name, machineType,
//                                baseCraftingSpeed, baseEnergyConsumption, basePollution, moduleSlots)
//                ); */
//            });
//
//            System.out.println(" -> parse recipe array");
//            JSONArray recipes = data.getJSONArray("recipes");
//
//            parseObjects(calculator, recipes, (calc, recipe) -> {
//                String name = recipe.getString("name");
//
//                List<ConsumableCount> ingredients = getConsumableCount(calc, recipe.getJSONArray("ingredients"));
//                List<ConsumableCount> results = getConsumableCount(calc, recipe.getJSONArray("results"));
//
//                double craftingTime = recipe.getDouble("craftingtime");
//                boolean intermediate = recipe.getBoolean("intermediate");
//
//                List<MachineType> allowedMachineTypes =
//                        getAllowedMachineTypes(calculator, recipe.getJSONArray("allowed-machine-types"));
//
//                calc.addRecipe(
//                        new Recipe(name, ingredients, results, allowedMachineTypes,
//                                craftingTime, intermediate)
//                );
//            });
//        } catch (JSONException je) {
//            throw new IllegalArgumentException("Can not parse given JSON file!\n" + je.getMessage());
//        }
//    }
//
//    private static void parseObjects(Calculator calculator, JSONArray array, BiConsumer<Calculator, JSONObject> function) {
//        for (int i = 0; i < array.length(); i++) {
//            JSONObject object = array.getJSONObject(i);
//            if (!object.isEmpty()) {
//                try {
//                    function.accept(calculator, object);
//
//                } catch (JSONException | IllegalArgumentException exception) {
//                    String name;
//                    try {
//                        name = object.getString("name");
//                    } catch (JSONException je) {
//                        name = Integer.toString(i);
//                    }
//                    System.err.println("Can not parse json object \"" + name + "\"!");
//                    exception.printStackTrace();
//                }
//            }
//        }
//    }
//
//    private static List<ConsumableCount> getConsumableCount(Calculator calculator, JSONArray array) {
//        List<ConsumableCount> list = new ArrayList<>();
//        for (int i = 0; i < array.length(); i++) {
//            JSONObject item = array.getJSONObject(i);
//
//            Consumable consumable = calculator.findConsumable(item.getString("name"));
//            int count = item.getInt("count");
//
//            list.add(new ConsumableCount(consumable, count));
//        }
//        return list;
//    }
//
//    private static List<MachineType> getAllowedMachineTypes(Calculator calculator, JSONArray array) {
//        List<MachineType> list = new ArrayList<>();
//        for (int i = 0; i < array.length(); i++) {
//            JSONObject object = array.getJSONObject(i);
//
//            MachineType machineType = calculator.findMachineType(object.getString("name"));
//            list.add(machineType);
//        }
//        return list;
//    }
//
//    private static Recipe findBestRecipe(List<Recipe> recipes, BiPredicate<Recipe, Recipe> rule) {
//        Recipe best = (recipes.isEmpty() ? null : recipes.get(0));
//
//        for (Recipe recipe : recipes) {
//            if (rule.test(recipe, best))
//                best = recipe;
//        }
//
//        return best;
//    }
//
//    private static BiPredicate<Recipe, Recipe> bestRecipeRule(Consumable consumable) {
//        return (recipe, best) -> {
//            int bestCount = best.getConsumableCount(consumable).getCount();
//            double bestTime = best.getCraftingTime();
//            int recipeCount = recipe.getConsumableCount(consumable).getCount();
//            double recipeTime = recipe.getCraftingTime();
//
//            return (bestCount / bestTime < recipeCount / recipeTime);
//        };
//    }
//
//    // private fields
//
//    private final int id;
//    private List<Consumable> consumables;
//    private List<MachineType> machineTypes;
//    private List<Machine> machines;
//    private List<Recipe> recipes;
//    private Map<MachineType, Machine> assignedMachines;
//
//    // constructors
//
//    public Calculator(List<Consumable> consumables, List<MachineType> machineTypes, List<Machine> machines, List<Recipe> recipes) {
//        this.id = nextId++;
//
//        if (consumables != null)
//            this.consumables = consumables;
//        else
//            this.consumables = new ArrayList<>();
//
//        if (machineTypes != null)
//            this.machineTypes = machineTypes;
//        else
//            this.machineTypes = new ArrayList<>();
//
//        if (machines != null)
//            this.machines = machines;
//        else
//            this.machines = new ArrayList<>();
//
//        if (recipes != null)
//            this.recipes = recipes;
//        else
//            this.recipes = new ArrayList<>();
//    }
//
//    public Calculator() {
//        this(null, null, null, null);
//    }
//
//    // methods
//
//    public void addJSONFile(File dataJSON)
//            throws IOException, IllegalArgumentException {
//        readJSONFile(this, dataJSON);
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public boolean addConsumable(Consumable consumable) {
//        if (!consumables.contains(consumable))
//            return consumables.add(consumable);
//        else
//            return false;
//    }
//
//    public boolean addConsumable(List<Consumable> addConsumables) {
//        return consumables.addAll(addConsumables);
//    }
//
//    public List<Consumable> getConsumables() {
//        return consumables;
//    }
//
//    public Consumable findConsumable(String name) {
//        for (Consumable consumable : consumables) {
//            if (consumable.getName().equals(name))
//                return consumable;
//        }
//        return null;
//    }
//
//    public boolean addMachineType(MachineType machineType) {
//        if (!machineTypes.contains(machineType))
//            return machineTypes.add(machineType);
//        else
//            return false;
//    }
//
//    public boolean addMachineType(List<MachineType> addMachineTypes) {
//        return machineTypes.addAll(addMachineTypes);
//    }
//
//    public List<MachineType> getMachineTypes() {
//        return machineTypes;
//    }
//
//    public MachineType findMachineType(String name) {
//        for (MachineType machineType : machineTypes) {
//            if (machineType.getName().equals(name))
//                return machineType;
//        }
//        return null;
//    }
//
//    public boolean addMachine(Machine machine) {
//        if (!machines.contains(machine))
//            return machines.add(machine);
//        else
//            return false;
//    }
//
//    public boolean addMachine(List<Machine> addMachines) {
//        return machines.addAll(addMachines);
//    }
//
//    public List<Machine> getMachines() {
//        return machines;
//    }
//
//    public Machine findMachine(String name) {
//        for (Machine machine : machines) {
//            if (machine.getName().equals(name))
//                return machine;
//        }
//        return null;
//    }
//
//    public Machine findMachine(Recipe recipe) {
//        Machine best = assignedMachines.get(recipe.getAllowedMachineTypes().get(0));
//        for (MachineType machineType : recipe.getAllowedMachineTypes()) {
//            Machine possibleMachine = assignedMachines.get(machineType);
//            double craftingTime = recipe.getCraftingTime();
//            int ingredientCount = 0;
//            for (ConsumableCount count : recipe.getIngredients()) {
//                ingredientCount *= count.getCount();
//            }
//            int resultCount = 0;
//            for (ConsumableCount count : recipe.getResults()) {
//                resultCount *= count.getCount();
//            }
//            //double speed = possibleMachine.getBaseCraftingSpeed();
//
//            //if ()
//        }
//        return null;
//    }
//
//    public boolean addRecipe(Recipe recipe) {
//        if (!recipes.contains(recipe))
//            return recipes.add(recipe);
//        else
//            return false;
//    }
//
//    public boolean addRecipes(List<Recipe> addRecipes) {
//        return recipes.addAll(addRecipes);
//    }
//
//    public List<Recipe> getRecipes() {
//        return recipes;
//    }
//
//    public List<Recipe> findRecipes(Consumable consumable, BiPredicate<Recipe, Consumable> rule) {
//        List<Recipe> possibleRecipes = new ArrayList<>();
//        for (Recipe recipe : recipes) {
//            if (rule.test(recipe, consumable))
//                possibleRecipes.add(recipe);
//        }
//        return possibleRecipes;
//    }
//
//    public void addDependencyRecipes() {
//        for (Consumable consumable : consumables) {
//            List<Recipe> recipes = findRecipes(consumable, Recipe::isResult);
//            Recipe best = findBestRecipe(recipes, bestRecipeRule(consumable));
//            consumable.setCraftingRecipe(best);
//            System.out.println("Found recipe for " + consumable.getName() + ": " + best);
//        }
//    }
//
//    /**
//     * Calculates a map with consumable double-value pairs how often this item is needed in 1 minute.
//     * @param startValues map which contains the start consumable double-value pairs for defined consumables.
//     *                    (for per Minute crafting)
//     * @return map with consumable double-value pairs
//     */
//    public Map<Consumable, Double> getValues(Map<Consumable, Double> startValues) {
//        if (startValues == null || startValues.isEmpty())
//            throw new IllegalArgumentException("Map startValues can not be null or empty!");
//
//        Map<Consumable, Double> startConsumables = Map.copyOf(startValues);
//        for (Map.Entry<Consumable, Double> set : startConsumables.entrySet()) {
//            Recipe recipe = set.getKey().getCraftingRecipe();
//
//            if (recipe != null) {
//                //calculateResources(startValues, recipe, set.getValue());
//            }
//        }
//
//        return startValues;
//    }
//
//    /*private void calculateResources(Map<Consumable, Double> startValues, Recipe recipe, double count, ) {
//
//    }*/
//
//    /*private Machine findMachine(Recipe recipe) {
//        return null;
//    }*/
}

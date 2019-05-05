package de.rilu.factorio.throughputCalculator.lib.calculator.saxHandlers;

import de.rilu.factorio.throughputCalculator.lib.calculator.Calculator;
import de.rilu.factorio.throughputCalculator.lib.consumable.ConsumableCount;
import de.rilu.factorio.throughputCalculator.lib.consumable.Item;
import de.rilu.factorio.throughputCalculator.lib.recipe.Recipe;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class RecipeHandler extends CalculatorHandler {
    private boolean bIngredients = false;
    private boolean bProduct = false;
    private boolean bCollection = false;
    private boolean bItem = false;
    private boolean bCount = false;
    private boolean bCraftingTime = false;
    private boolean bIntermediate = false;

    private Item item = null;
    private int count = ConsumableCount.MIN_COUNT - 1;
    private double craftingTime = Recipe.MIN_CRAFTING_TIME - 1;
    private boolean intermediate = false;

    public RecipeHandler(Calculator calculator) {
        super(calculator);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("recipe")) {
            System.out.println("Start read recipe");
        } else if (qName.equalsIgnoreCase("ingredients")) {
            System.out.println("Start read ingredients");
            bIngredients = true;
        } else if (qName.equalsIgnoreCase("collection")) {
            System.out.println("Start read collection");
            bCollection = true;
        } else if (qName.equalsIgnoreCase("item")) {
            bItem = true;
        } else if (qName.equalsIgnoreCase("count")) {
            bCount = true;
        } else if (qName.equalsIgnoreCase("product")) {
            bProduct = true;
        }
    }
}

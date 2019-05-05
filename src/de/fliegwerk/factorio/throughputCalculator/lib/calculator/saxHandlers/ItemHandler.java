package de.rilu.factorio.throughputCalculator.lib.calculator.saxHandlers;

import de.rilu.factorio.throughputCalculator.lib.calculator.Calculator;
import de.rilu.factorio.throughputCalculator.lib.consumable.Item;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.Arrays;

public class ItemHandler extends CalculatorHandler {
    private boolean bName = false;
    private boolean bStackSize = false;

    private String name = null;
    private int stackSize = Item.MIN_STACKSIZE - 1;

    public ItemHandler(Calculator calculator) {
        super(calculator);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("item")) {
            System.out.println(" >> Start item element");
        }
        if (qName.equalsIgnoreCase("name")) {
            bName = true;
        } else if (qName.equalsIgnoreCase("stacksize")) {
            bStackSize = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("item")) {
            System.out.println(" >> End item element, add to calculator");
            try {
                calculator.addConsumable(new Item(name, stackSize));

            } catch (IllegalArgumentException iae) {
                System.err.println("Something wrong happened:");
                System.err.println(iae.getMessage());
                System.err.println("Maybe check your file syntax?");
            }

            name = null;
            stackSize = Item.MIN_STACKSIZE - 1;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (bName) {
            name = Arrays.toString(ch);
            bName = false;
        } else if (bStackSize) {
            try {
                stackSize = Integer.parseInt(Arrays.toString(ch));
            } catch (NumberFormatException nfe) {
                System.err.println("Something wrong happened:");
                System.err.println("Can not parse stack size to integer (" + Arrays.toString(ch) + ')');
                System.err.println("Set to default stack size");
                stackSize = Item.DEFAULT_STACKSIZE;
            }
            bStackSize = false;
        }
    }
}

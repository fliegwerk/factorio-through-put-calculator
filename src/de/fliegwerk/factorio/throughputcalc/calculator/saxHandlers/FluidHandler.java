package de.fliegwerk.factorio.throughputCalculator.lib.calculator.saxHandlers;

import de.fliegwerk.factorio.throughputCalculator.lib.calculator.Calculator;
import de.fliegwerk.factorio.throughputCalculator.lib.consumable.Fluid;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.Arrays;

public class FluidHandler /* extends CalculatorHandler */ {
//    private boolean bName = false;
//    private boolean bCValue = false;
//
//    private String name = null;
//    private double cValue = Fluid.MIN_CVALUE - 1;
//
//    public FluidHandler(Calculator calculator) {
//        super(calculator);
//    }
//
//    @Override
//    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//        if (qName.equalsIgnoreCase("fluid")) {
//            System.out.println(" >> Start fluid element");
//        }
//        if (qName.equalsIgnoreCase("name")) {
//            bName = true;
//        } else if (qName.equalsIgnoreCase("cValue")) {
//            bCValue = true;
//        }
//    }
//
//    @Override
//    public void endElement(String uri, String localName, String qName) throws SAXException {
//        if (qName.equalsIgnoreCase("fluid")) {
//            System.out.println(" >> End fluid element, add to calculator");
//            try {
//                calculator.addConsumable(new Fluid(name, cValue));
//            } catch (IllegalArgumentException iae) {
//                System.err.println("Something wrong happened:");
//                System.err.println(iae.getMessage());
//                System.err.println("Maybe check your file syntax?");
//            }
//
//            name = null;
//            cValue = Fluid.MIN_CVALUE - 1;
//        }
//    }
//
//    @Override
//    public void characters(char[] ch, int start, int length) throws SAXException {
//        if (bName) {
//            name = Arrays.toString(ch);
//            bName = false;
//        } else if (bCValue) {
//            try {
//                cValue = Double.parseDouble(Arrays.toString(ch));
//            } catch (NumberFormatException nfe) {
//                System.err.println("Something wrong happened:");
//                System.err.println("Can not parse stack size to double (" + Arrays.toString(ch) + ')');
//                System.err.println("Set to default stack size");
//                cValue = Fluid.DEFAULT_CVALUE;
//            }
//            bCValue = false;
//        }
//    }
}

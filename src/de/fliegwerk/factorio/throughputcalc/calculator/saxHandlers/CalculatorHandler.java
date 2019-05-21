package de.fliegwerk.factorio.throughputCalculator.lib.calculator.saxHandlers;

import de.fliegwerk.factorio.throughputCalculator.lib.calculator.Calculator;
import org.xml.sax.helpers.DefaultHandler;

public class CalculatorHandler extends DefaultHandler {
    protected Calculator calculator;

    protected CalculatorHandler(Calculator calculator) {
        super();
        if (calculator == null)
            throw new IllegalArgumentException("Calculator can not be null!");

        this.calculator = calculator;
    }
}

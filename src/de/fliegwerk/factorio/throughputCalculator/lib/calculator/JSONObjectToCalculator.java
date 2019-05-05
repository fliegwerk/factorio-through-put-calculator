package de.fliegwerk.factorio.throughputCalculator.lib.calculator;

import org.json.JSONObject;

public interface JSONObjectToCalculator {
    void convertJSONObjectToCalculator(Calculator calculator, JSONObject obj);
}

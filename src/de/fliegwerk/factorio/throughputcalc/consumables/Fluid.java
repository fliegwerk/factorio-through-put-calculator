package de.fliegwerk.factorio.throughputcalc.consumables;

import de.fliegwerk.factorio.throughputcalc.CheckElements;
import org.jetbrains.annotations.Contract;

import java.util.Objects;

/**
 * <p><b>Work in progress!</b></p>
 * This class represents a fluid with a name and a c value (<i>WIP</i>).
 * It is a specialisation of a consumable and can also be used in recipes as ingredient and result specification.
 *
 * @see Consumable
 * @see de.fliegwerk.factorio.throughputcalc.recipes.Recipe
 * @see Item
 *
 * @author Ludwig Richter (richter@fliegwerk.com)
 * @version 1.1.2
 * @since 11.0.2
 */
public class Fluid extends Consumable {
    /**
     * Number on minimal c values for all fluids
     */
    public static final double MIN_C_VALUE = 0.1;

    private final double cValue;

    /**
     * Creates a new fluid with a given name and c value (<I>WIP</I>).
     * @param name name of this fluid
     * @param cValue <i>WIP</i>
     *
     * @throws IndexOutOfBoundsException if c value is less than {@value MIN_C_VALUE}
     */
    public Fluid(String name, double cValue) {
        super(name);
        CheckElements.checkMin(cValue, MIN_C_VALUE, "Double c value");

        this.cValue = cValue;
    }

    /**
     * Returns the c value of this fluid.
     * @return c value of fluid
     */
    public double getcValue() {
        return cValue;
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fluid)) return false;
        if (!super.equals(o)) return false;
        Fluid fluid = (Fluid) o;
        return Double.compare(fluid.cValue, cValue) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cValue);
    }

    @Override
    public String toString() {
        return "Fluid{" +
                "cValue=" + cValue +
                "} " + super.toString();
    }
}

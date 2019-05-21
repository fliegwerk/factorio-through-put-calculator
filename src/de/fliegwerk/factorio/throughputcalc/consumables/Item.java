package de.fliegwerk.factorio.throughputcalc.consumables;

import de.fliegwerk.factorio.throughputcalc.CheckElements;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * This class represents an item with a name and a stack size.
 * It is a specialisation of a consumable and can also be used in recipes as ingredient and result specification.
 *
 * @see Consumable
 * @see de.fliegwerk.factorio.throughputcalc.recipes.Recipe
 * @see Fluid
 *
 * @author Ludwig Richter (richter@fliegwerk.com)
 * @version 1.1.2
 * @since 11.0.2
 */
public class Item extends Consumable {
    /**
     * Number of minimal stack size for all items
     */
    public static final int MIN_STACK_SIZE = 1;

    private final int stackSize;

    /**
     * Creates a new item with a given name and stack size.
     * @param name name of this recipe
     * @param stackSize maximum number of this item for 1 stack
     *
     * @throws NullPointerException if string name is {@code null}
     * @throws IllegalArgumentException if string name trimmed is empty
     * @throws IndexOutOfBoundsException if stack size is less than {@value MIN_STACK_SIZE}
     */
    public Item(@NotNull String name, int stackSize) {
        super(name);
        CheckElements.checkMin(stackSize, MIN_STACK_SIZE, "Integer stack size");

        this.stackSize = stackSize;
    }

    /**
     * Returns the maximum number of this item for 1 stack.
     * @return stack size of item
     */
    public int getStackSize() {
        return stackSize;
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        if (!super.equals(o)) return false;
        Item item = (Item) o;
        return stackSize == item.stackSize;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), stackSize);
    }

    @Override
    public String toString() {
        return "Item{" +
                "stackSize=" + stackSize +
                "} " + super.toString();
    }
}

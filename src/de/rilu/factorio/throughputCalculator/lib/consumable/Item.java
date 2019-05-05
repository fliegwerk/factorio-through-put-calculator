package de.rilu.factorio.throughputCalculator.lib.consumable;

import java.util.Objects;

public class Item extends Consumable {
    public static final int MIN_STACKSIZE = 1;
    public static final int DEFAULT_STACKSIZE = 50;
    public static final int MAX_STACKSIZE = 1_000;

    private final int stackSize;

    public Item(String name, int stackSize) {
        super(name);
        if (stackSize < MIN_STACKSIZE || stackSize > MAX_STACKSIZE)
            throw new IllegalArgumentException("Stack size can not be less than " + MIN_STACKSIZE +
                    " or greater than " + MAX_STACKSIZE + '!');

        this.stackSize = stackSize;
    }

    public int getStackSize() {
        return stackSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
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
                super.toString() +
                "stackSize=" + stackSize +
                '}';
    }
}

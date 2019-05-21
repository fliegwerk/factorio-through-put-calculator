package de.fliegwerk.factorio.throughputcalc.consumables;

import java.util.Objects;

public class Item extends Consumable {
    private final int stackSize;

    public Item(String name, int stackSize) {
        super(name);
        this.stackSize = stackSize;
    }

    public int getStackSize() {
        return stackSize;
    }

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

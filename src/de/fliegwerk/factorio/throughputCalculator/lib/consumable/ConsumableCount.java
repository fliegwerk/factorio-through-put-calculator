package de.fliegwerk.factorio.throughputCalculator.lib.consumable;

import java.util.Objects;

public class ConsumableCount {
    public static final int MIN_COUNT = 1;
    public static final int MAX_COUNT = 1_000_000;

    private final Consumable consumable;
    private final int count;

    public ConsumableCount(Consumable consumable, int count) {
        if (consumable == null)
            throw new IllegalArgumentException("Item item can not be null!");
        if (count < MIN_COUNT || count > MAX_COUNT)
            throw new IllegalArgumentException("Integer count can not be less than " + MIN_COUNT + " or greater than " + MAX_COUNT + '!');

        this.consumable = consumable;
        this.count = count;
    }

    public ConsumableCount(Consumable consumable) {
        this(consumable, MIN_COUNT);
    }

    public Consumable getConsumable() {
        return consumable;
    }

    public int getCount() {
        return count;
    }

    public String getName() {
        return consumable.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsumableCount that = (ConsumableCount) o;
        return count == that.count &&
                consumable.equals(that.consumable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consumable, count);
    }

    @Override
    public String toString() {
        return "ConsumableCount{" +
                "consumable=" + consumable +
                ", count=" + count +
                '}';
    }
}

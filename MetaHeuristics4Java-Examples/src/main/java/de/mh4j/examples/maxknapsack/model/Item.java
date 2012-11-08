package de.mh4j.examples.maxknapsack.model;

public class Item {

    public final String name;
    public final int price;
    public final int volume;

    public Item(String name, int price, int volume) {
        this.name = name;
        this.price = price;
        this.volume = volume;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (otherObject instanceof Item == false) {
            return false;
        }
        Item otherItem = (Item) otherObject;
        return otherItem.name == this.name && otherItem.price == this.price && otherItem.volume == this.volume;
    }

    @Override
    public String toString() {
        return name;
    }
}

package de.mh4j.examples.maxknapsack.model;

import org.testng.annotations.Test;

public class ItemTest {

    @Test
    public void testCreate() {
        String name = "Foobar";
        int price = 123;
        int volume = 456;

        Item item = new Item(name, price, volume);

        assert item.name == name;
        assert item.price == price;
        assert item.volume == volume;
    }

}

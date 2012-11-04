package de.mh4j.examples.maxknapsack.model;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

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

    @Test
    public void testEquals() {
        Item item00 = new Item("Foo", 10, 20);

        Item item01 = new Item("Foo", 10, 20);
        Item item02 = new Item("Foo", 10, 99);
        Item item03 = new Item("Foo", 99, 20);
        Item item04 = new Item("Foo", 99, 99);
        Item item05 = new Item("Bar", 10, 20);
        Item item06 = new Item("Bar", 10, 99);
        Item item07 = new Item("Bar", 99, 20);
        Item item08 = new Item("Bar", 99, 99);

        assertTrue(item00.equals(item01));
        assertFalse(item00.equals(item02));
        assertFalse(item00.equals(item03));
        assertFalse(item00.equals(item04));
        assertFalse(item00.equals(item05));
        assertFalse(item00.equals(item06));
        assertFalse(item00.equals(item07));
        assertFalse(item00.equals(item08));
    }

}

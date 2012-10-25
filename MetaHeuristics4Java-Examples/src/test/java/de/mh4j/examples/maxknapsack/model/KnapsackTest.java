package de.mh4j.examples.maxknapsack.model;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;

public class KnapsackTest {

    @Test
    public void testCreate() {
        int capacity = 100;
        Knapsack knapsack = new Knapsack(capacity);
        assertEquals(capacity, knapsack.getCapacity());
        assertEquals("There shpuld be no items in a new knapsack", 0, knapsack.getNumberOfItems());
        assertEquals("Costs should be zero for a new knapsack", 0, knapsack.getCosts());
        assertEquals(capacity, knapsack.getRemainingCapacity());
    }

    @Test
    public void testAddItem() {
        int capacity = 100;
        Knapsack knapsack = new Knapsack(capacity);
        int price = 6;
        int volume = 10;
        knapsack.addItem(new Item("Foobar", price, volume));

        assertEquals(1, knapsack.getNumberOfItems());
        assertEquals(price, knapsack.getCosts());
        assertEquals(capacity - volume, knapsack.getRemainingCapacity());
    }

    @Test
    public void testExceedCapacityLimit() {
        Knapsack knapsack = new Knapsack(10);

        assertEquals(true, knapsack.addItem(new Item("Foobar0", 0, 3)));
        assertEquals(true, knapsack.addItem(new Item("Foobar1", 0, 3)));
        assertEquals(true, knapsack.addItem(new Item("Foobar2", 0, 4)));
        assertEquals(false, knapsack.addItem(new Item("Foobar3", 0, 3)));
        assertEquals(3, knapsack.getNumberOfItems());
        assertEquals(0, knapsack.getRemainingCapacity());
    }

    @Test
    public void testRemoveItem() {
        Knapsack knapsack = new Knapsack(10);
        knapsack.addItem(new Item("Foo1", 1, 0));
        knapsack.addItem(new Item("Foo2", 2, 0));
        knapsack.addItem(new Item("Foo3", 4, 0));

        Item removedItem = knapsack.removeItem(0);
        assertEquals("Foo1", removedItem.name);
        assertEquals(6, knapsack.getCosts());
        assertEquals(2, knapsack.getNumberOfItems());

        removedItem = knapsack.removeItem(1);
        assertEquals("Foo3", removedItem.name);
        assertEquals(2, knapsack.getCosts());
        assertEquals(1, knapsack.getNumberOfItems());

        removedItem = knapsack.removeItem(0);
        assertEquals("Foo2", removedItem.name);
        assertEquals(0, knapsack.getCosts());
        assertEquals(0, knapsack.getNumberOfItems());
    }
}

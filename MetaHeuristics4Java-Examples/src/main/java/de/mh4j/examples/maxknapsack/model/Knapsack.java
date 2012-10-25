package de.mh4j.examples.maxknapsack.model;

import java.util.ArrayList;
import java.util.List;

import de.mh4j.solver.Solution;

public class Knapsack implements Solution<Knapsack> {

    private final int totalCapacity;
    private final List<Item> items;

    private int remainingCapacity;
    private int costs;

    public Knapsack(int capacity) {
        totalCapacity = capacity;
        remainingCapacity = totalCapacity;
        items = new ArrayList<>();
        costs = 0;
    }

    @Override
    public int getCosts() {
        return costs;
    }

    public int getCapacity() {
        return totalCapacity;
    }

    @Override
    public boolean isBetterThan(Knapsack otherSolution) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean addItem(Item item) {
        int newRemainingCapacity = remainingCapacity - item.volume;

        if (newRemainingCapacity >= 0) {
            items.add(item);
            remainingCapacity = newRemainingCapacity;
            costs += item.price;
        }

        return newRemainingCapacity >= 0;
    }

    public int getNumberOfItems() {
        return items.size();
    }

    public int getRemainingCapacity() {
        return remainingCapacity;
    }

	public Item removeItem(int index) {		
		Item removedItem = items.remove(index);
		costs -= removedItem.price;
		return removedItem;
	}

}

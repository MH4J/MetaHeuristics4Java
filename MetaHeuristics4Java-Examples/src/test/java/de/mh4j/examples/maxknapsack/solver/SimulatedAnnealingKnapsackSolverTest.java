package de.mh4j.examples.maxknapsack.solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import de.mh4j.examples.maxknapsack.model.Item;

public class SimulatedAnnealingKnapsackSolverTest {

    @Test
    public void testSolveSimpleInstance() {
        int knapsackCapacity = 100;
        List<Item> items = new ArrayList<>(Arrays.asList(
                new Item("Foo", 100, 50),
                new Item("Bar", 80, 30),
                new Item("Muh", 5, 5),
                new Item("Awe", 30, 10)
                ));

        SimulatedAnnealingKnapsackSolver solver = new SimulatedAnnealingKnapsackSolver(knapsackCapacity, items);
        solver.run();
    }
}

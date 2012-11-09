package de.mh4j.examples.maxknapsack.solver;

import static org.testng.AssertJUnit.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import ch.qos.logback.classic.Level;
import de.mh4j.examples.maxknapsack.model.Item;
import de.mh4j.examples.maxknapsack.model.Knapsack;

public class SimulatedAnnealingKnapsackSolverTest {

	@Test
	public void testSolveSimpleInstance() {
		int knapsackCapacity = 59;
		List<Item> items = new ArrayList<>(Arrays.asList(new Item("Foo", 100,
				40), new Item("Bar", 80, 30), new Item("Muh", 5, 5), new Item(
				"Awe", 30, 10)));

		SimulatedAnnealingKnapsackSolver solver = new SimulatedAnnealingKnapsackSolver(
				knapsackCapacity, items);
		solver.run();
	}

	@Test
	public void testBuggyConfiguration() {
		int knapsackCapacity = 59;
		List<Item> items = new ArrayList<>(Arrays.asList(new Item("Foo", 100,
				40), new Item("Bar", 80, 30), new Item("Muh", 5, 5), new Item(
				"Awe", 30, 10)));

		SimulatedAnnealingKnapsackSolver solver = new SimulatedAnnealingKnapsackSolver(
				knapsackCapacity, items);
		Knapsack currentSolution = new Knapsack(knapsackCapacity);
		currentSolution.addItem(items.get(3));
		currentSolution.addItem(items.get(2));
		currentSolution.addItem(items.get(1));

		solver.setInitialSolution(currentSolution);
		solver.setLogLevel(Level.TRACE);

		boolean hasChanged = false;

		while (!solver.hasFinished()) {
			solver.step();
			if (solver.getCurrentSolution().equals(currentSolution) == false) {
				hasChanged = true;
				break;
			}
		}

		assertTrue("This bug should have been fixed", hasChanged);
    }
}

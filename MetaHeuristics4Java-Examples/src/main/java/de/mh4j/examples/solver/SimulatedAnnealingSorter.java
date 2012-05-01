package de.mh4j.examples.solver;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import de.mh4j.examples.ExampleSolverStateListener;
import de.mh4j.examples.Sorting;
import de.mh4j.solver.Solver;
import de.mh4j.solver.simulatedAnnealing.AbstractSimulatedAnnealingSolver;

public class SimulatedAnnealingSorter extends AbstractSimulatedAnnealingSolver<Sorting> {

    private final int amountOfNumbers;

    public SimulatedAnnealingSorter(int amountOfNumbers) {
        super(new ExampleCoolingScheme());
        this.amountOfNumbers = amountOfNumbers;
    }

    /**
     * Creates an initial random sorting.
     */
    @Override
    protected Sorting createInitialSolution() {
        return Sorting.createRandomSorting(amountOfNumbers);
    }

    /**
     * A random neighbor is created by choosing two random indices in the
     * sorting array and swapping the numbers at this position.<br>
     * Hopefully one of the swapped numbers will be nearer to its optimal
     * position in the sorting and decrease the cost of the whole new sorting.
     */
    @Override
    protected Sorting createRandomNeighbor() {
        Sorting neighbor = new Sorting(currentSolution);

        int randomIndex1 = randomizer.nextInt(currentSolution.getAmountOfNumbers());
        int randomIndex2 = randomizer.nextInt(currentSolution.getAmountOfNumbers());

        neighbor.swapIndices(randomIndex1, randomIndex2);
        return neighbor;
    }

    /**
     * Checks if we have found the optimal solution with costs zero or if the
     * number of steps the algorithm has been running has exceeded the maximum
     * number of steps.<br>
     * <br>
     * <b>Note:</b> This termination condition is some arbitrarily chosen
     * example. Real termination conditions will have to be founded in some
     * serious considerations.
     */
    @Override
    public boolean hasFinished() {
        if (currentSolution.getCosts() == 0) {
            return true;
        }
        else {
            return situationHasNotImproved >= 5000;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Logger log = (Logger) LoggerFactory.getLogger(SimulatedAnnealingSorter.class);
        log.setLevel(Level.WARN);

        Solver<Sorting> solver = new SimulatedAnnealingSorter(10);
        solver.addStateListener(new ExampleSolverStateListener<Sorting>());

        Thread solverThread = new Thread(solver);
        solverThread.start();
    }
}

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

    @Override
    protected Sorting createInitialSolution() {
        return Sorting.createRandomSorting(amountOfNumbers);
    }

    @Override
    protected Sorting createRandomNeighbor() {
        Sorting neighbor = new Sorting(currentSolution);

        int randomIndex1 = randomizer.nextInt(currentSolution.getAmountOfNumbers());
        int randomIndex2 = randomizer.nextInt(currentSolution.getAmountOfNumbers());

        neighbor.swapIndices(randomIndex1, randomIndex2);
        return neighbor;
    }

    @Override
    public boolean hasFinished() {
        if (currentSolution.getCosts() == 0) {
            // finish if we found the best sorting
            return true;
        }
        else {
            // finish if we have taken 100000 steps
            return getNumberOfSteps() >= 100000;
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

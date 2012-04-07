package de.mh4j.examples.solver;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import de.mh4j.examples.Sorting;
import de.mh4j.solver.Solver;
import de.mh4j.solver.simulatedAnnealing.AbstractLocalSearchSolver;

public class LocalSearchSorter extends AbstractLocalSearchSolver<Sorting> {

    private final int amountOfNumbers;

    public LocalSearchSorter(int amountOfNumbers) {
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
        Logger log = (Logger) LoggerFactory.getLogger(LocalSearchSorter.class);
        log.setLevel(Level.WARN);

        Solver<Sorting> solver = new LocalSearchSorter(10);
        solver.addStateListener(new SolverStateAdapter<Sorting>() {

            Sorting lastSolution = null;

            @Override
            public void solverHasStepped(Solver<Sorting> solver) {
                Sorting currentSolution = solver.getCurrentSolution();
                if (currentSolution.equals(lastSolution) == false) {
                    System.out.print("New Solution: " + currentSolution);
                    System.out.println(" (Costs " + currentSolution.getCosts() + ")");
                    lastSolution = currentSolution;
                }
            }

            @Override
            public void solverHasFinished(Solver<Sorting> solver) {
                System.out.println("Terminated after " + solver.getNumberOfSteps() + " steps");
                System.out.println(solver.getCurrentSolution());
            }

        });

        Thread solverThread = new Thread(solver);
        solverThread.start();
    }
}

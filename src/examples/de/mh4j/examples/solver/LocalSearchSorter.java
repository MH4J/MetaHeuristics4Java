package de.mh4j.examples.solver;

import de.mh4j.examples.Sorting;
import de.mh4j.solver.simulatedAnnealing.AbstractLocalSearchSolver;

public class LocalSearchSorter extends AbstractLocalSearchSolver<Sorting> {

    @Override
    protected Sorting createInitialSolution() {
        return Sorting.createRandomSorting(100);
    }

    @Override
    protected Sorting createRandomNeighbor() {
        Sorting neighbor = new Sorting(currentSolution);
        // choose a random index but not the last one
        int randomIndex = randomizer.nextInt(currentSolution.getAmountOfNumbers() - 1);

        neighbor.swapIndices(randomIndex, randomIndex + 1);
        return neighbor;
    }

    @Override
    public boolean hasFinished() {
        // TODO Auto-generated method stub
        return false;
    }

}

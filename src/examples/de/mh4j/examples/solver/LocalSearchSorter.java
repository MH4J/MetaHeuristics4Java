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
        return null;
    }

    @Override
    public boolean hasFinished() {
        // TODO Auto-generated method stub
        return false;
    }

}

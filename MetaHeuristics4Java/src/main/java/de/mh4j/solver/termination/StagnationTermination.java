package de.mh4j.solver.termination;

import de.mh4j.solver.Solution;
import de.mh4j.solver.Solver;

public class StagnationTermination implements TerminationCondition {

    private final Solver<? extends Solution<?>> solver;
    private final int maxNrOfStagnatingSteps;

    private int nrOfStagnatingSteps;
    private Solution<?> lastSolution;

    public StagnationTermination(Solver<? extends Solution<?>> solver, int maxNrOfStagnatingSteps) {
        this.solver = solver;
        this.maxNrOfStagnatingSteps = maxNrOfStagnatingSteps;
        this.nrOfStagnatingSteps = 0;
    }

    @Override
    public boolean shouldTerminate() {
        Solution<?> currentSolution = solver.getCurrentSolution();
        if (currentSolution == lastSolution) {
            nrOfStagnatingSteps++;
        }
        else {
            nrOfStagnatingSteps = 0;
            lastSolution = currentSolution;
        }

        return nrOfStagnatingSteps >= maxNrOfStagnatingSteps;
    }

    @Override
    public String toString() {
        return "stagnation termination condition (" + maxNrOfStagnatingSteps + " non-improving steps)";
    }
}

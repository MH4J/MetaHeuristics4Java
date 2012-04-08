package de.mh4j.solver.termination;

import de.mh4j.solver.AbstractSolver;

public class StepCountTermination<GenericSolutionType> implements TerminationCondition {

    private final AbstractSolver<GenericSolutionType> solver;
    private final int maxStepCount;

    public StepCountTermination(AbstractSolver<GenericSolutionType> solver, int maxStepCount) {
        this.solver = solver;
        this.maxStepCount = maxStepCount;
    }

    @Override
    public boolean shouldTerminate() {
        return solver.getNumberOfSteps() >= maxStepCount;
    }
}

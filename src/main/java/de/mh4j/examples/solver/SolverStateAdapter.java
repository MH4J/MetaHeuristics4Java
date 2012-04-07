package de.mh4j.examples.solver;

import de.mh4j.solver.Solver;
import de.mh4j.solver.SolverStateListener;

public class SolverStateAdapter<GenericSolutionType> implements SolverStateListener<GenericSolutionType> {

    @Override
    public void solverHasBeenRestarted(Solver<GenericSolutionType> solver) {
        // do nothing until this method is overridden

    }

    @Override
    public void solverHasStepped(Solver<GenericSolutionType> solver) {
        // do nothing until this method is overridden

    }

    @Override
    public void solverHasFinished(Solver<GenericSolutionType> solver) {
        // do nothing until this method is overridden
    }

}

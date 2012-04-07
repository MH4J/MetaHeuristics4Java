package de.mh4j.solver;

public interface SolverStateListener<GenericSolutionType> {

    void solverHasBeenRestarted(Solver<GenericSolutionType> solver);

    void solverHasStepped(Solver<GenericSolutionType> solver);

    void solverHasFinished(Solver<GenericSolutionType> solver);

}

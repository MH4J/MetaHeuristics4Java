package de.mh4j.solver;

public interface SolverStateListener {

    void solverHasBeenRestarted();

    void solverStateHasChanged();

    void solverHasFinished();

}

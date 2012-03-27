package de.mh4j.solver;

public interface SolverStateListener {

	public void solverHasBeenRestarted();
	public void solverStateHasChanged();
	public void solverHasFinished();
	
}

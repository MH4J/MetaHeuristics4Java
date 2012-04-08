package de.mh4j.concurrency;

import de.mh4j.solver.AbstractSolver;

public class SolverThread extends Thread {

    private final AbstractSolver<?> solver;

    public SolverThread(AbstractSolver<?> solver) {
        super(solver);
        this.solver = solver;
    }

    @Override
    public void interrupt() {
        super.interrupt();
        solver.interrupt();
    }
}

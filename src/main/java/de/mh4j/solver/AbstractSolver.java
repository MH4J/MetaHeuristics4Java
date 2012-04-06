package de.mh4j.solver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A Solver is the representation of an algorithm that can find or approximate
 * solutions to a given optimization problem.
 * 
 * TODO write more about the possible implementation and utilization of a solver
 * 
 * @param <GenericSolutionType>
 *            the actual Type of the solution class.
 */
public abstract class AbstractSolver<GenericSolutionType> implements Runnable {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected final Random randomizer;
    protected long seed;

    private final List<SolverStateListener> stateListeners = new ArrayList<SolverStateListener>(2);
    private int numberOfSteps = 0;
    private boolean isInitialized = false;

    /**
     * Creates a new solver with the current time as seed for the according
     * {@link Random}.
     */
    public AbstractSolver() {
        this(System.currentTimeMillis());
    }

    /**
     * Creates a new solver with the given seed for the according {@link Random}
     * .
     */
    public AbstractSolver(long seed) {
        this.randomizer = new Random(seed);
        this.seed = seed;
    }

    /**
     * Adds a {@linkplain SolverStateListener} to this solver. The listener will
     * be informed about important events that may occur on this solver.
     */
    public void addStateListener(SolverStateListener listener) {
        stateListeners.add(listener);
    }

    public void removeStateListener(SolverStateListener listener) {
        stateListeners.remove(listener);
    }

    /**
     * Calls {@link #step()} until {@link #hasFinished()} returns
     * <code>true</code>.
     */
    @Override
    public void run() {
        while (hasFinished() == false) {
            step();
        }
    }

    /**
     * Performs a single step in the algorithm of this solver. The following
     * actions will be taken:
     * <ol>
     * <li>The solver will be {@link #initialize() initialized} if it has not
     * yet been initialized by a previous call to this method</li>
     * <li>The actual algorithm step will be performed</li>
     * <li>The step counter will be advanced by 1</li>
     * <li>All {@link SolverStateListener SolverStateListeners} will be notified
     * by a call to {@link SolverStateListener#solverStateHasChanged()}</li>
     * 
     * @see AbstractSolver#doStep()
     */
    public void step() {
        if (isInitialized == false) {
            initialize();
        }
        doStep();
        numberOfSteps++;
        notifyStepped();
    }

    private void initialize() {
        isInitialized = true;
        numberOfSteps = 0;
        doInitialize();
    }

    private void notifyStepped() {
        for (SolverStateListener listener : stateListeners) {
            listener.solverHasStepped();
        }
    }

    /**
     * Resets this solver. The current solution will be discarded and the solver
     * will be initialized again. All {@link SolverStateListener
     * SolverStateListeners} will be notified.
     * 
     * @see #doInitialize()
     */
    public void reset() {
        initialize();
        notifySolverReset();
    }

    private void notifySolverReset() {
        for (SolverStateListener listener : stateListeners) {
            listener.solverHasBeenRestarted();
        }
    }

    /**
     * Returns how often the {@link #step()} method has been called since the
     * last call to {@link #initialize()}.
     */
    public int getNumberOfSteps() {
        return numberOfSteps;
    }

    /**
     * Returns the current solution that has been created after the last call to
     * {@link #step()}.<br>
     * <br>
     * <b>Note:</b><br>
     * This interim solution may not be the best solution ever found and can
     * even be <code>null</code> if {@link #step()} has never been called.
     */
    public abstract GenericSolutionType getCurrentSolution();

    /**
     * Initializes this solver. Every call to this method must reset the solver
     * in its initial state and discard the current solution.
     */
    protected abstract void doInitialize();

    /**
     * Performs a single step of the solvers concrete algorithm.
     */
    protected abstract void doStep();

    /**
     * Returns <code>true</code> as soon as the solver has finished optimizing
     * the problem. Otherwise <code>false</code> is returned.
     */
    public abstract boolean hasFinished();
}

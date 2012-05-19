package de.mh4j.solver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mh4j.solver.termination.TerminationCondition;
import de.mh4j.util.RNGGenerator;

/**
 * A Solver is the representation of an algorithm that can find or approximate
 * solutions to a given optimization problem.
 * 
 * TODO write more about the possible implementation and utilization of a solver
 * 
 * @param <GenericSolutionType>
 *            the actual Type of the solution class.
 */
public abstract class AbstractSolver<GenericSolutionType> implements Solver<GenericSolutionType> {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected final Random randomizer;

    private final List<SolverStateListener<GenericSolutionType>> stateListeners = new ArrayList<>(2);
    private final List<TerminationCondition> terminationConditions = new ArrayList<>(1);
    private int numberOfSteps = 0;
    private boolean isInitialized = false;

    /**
     * Creates a new solver. Note that the solver initialization will not yet be
     * started. Instead this is done after the first call to {@link #step()} or
     * {@link #run()}.
     */
    public AbstractSolver() {
        randomizer = RNGGenerator.createRandomNumberGenerator();
    }

    @Override
    public void addStateListener(SolverStateListener<GenericSolutionType> listener) {
        stateListeners.add(listener);
    }

    @Override
    public void removeStateListener(SolverStateListener<GenericSolutionType> listener) {
        stateListeners.remove(listener);
    }

    /**
     * Calls {@link #step()} until {@link #hasFinished()} returns
     * <code>true</code>.
     */
    @Override
    public void run() {
        do {
            step();
        } while (hasFinished() == false);
        notifyHasFinished();
    }

    @Override
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

        if (terminationConditions.isEmpty()) {
            log.warn("No termination condition has been defined. Solver will not terminate by itself!");
        }
    }

    private void notifyStepped() {
        for (SolverStateListener<GenericSolutionType> listener : stateListeners) {
            listener.solverHasStepped(this);
        }
    }

    private void notifyHasFinished() {
        for (SolverStateListener<GenericSolutionType> listener : stateListeners) {
            listener.solverHasFinished(this);
        }
    }

    @Override
    public void reset() {
        initialize();
        notifySolverReset();
    }

    private void notifySolverReset() {
        for (SolverStateListener<GenericSolutionType> listener : stateListeners) {
            listener.solverHasBeenRestarted(this);
        }
    }

    /**
     * Adds a new termination condition that will be checked each time that
     * {@link #hasFinished()} is called.
     */
    public void addTerminationCondition(TerminationCondition newTerminationCondition) {
        terminationConditions.add(newTerminationCondition);
    }

    /**
     * Checks if any {@link TerminationCondition} has been reached.
     * 
     * @return <code>true</code> If at least one termination condition has been
     *         reached.<br>
     *         <code>false</code> If no termination condition has been defined
     *         or no condition has been reached yet.
     */
    @Override
    public boolean hasFinished() {
        if (terminationConditions.isEmpty()) {
            return false;
        }

        for (TerminationCondition terminationCondition : terminationConditions) {
            if (terminationCondition.shouldTerminate()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns how often the {@link #step()} method has been called since the
     * last call to {@link #initialize()}.
     */
    @Override
    public int getNumberOfSteps() {
        return numberOfSteps;
    }

    @Override
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

}

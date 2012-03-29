package de.mh4j.solver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO write javadoc
 * 
 * @param <GenericSolutionType>
 */
public abstract class Solver<GenericSolutionType> implements Runnable {
	protected final Logger log = (Logger) LoggerFactory.getLogger(getClass());
	
	protected final Random randomizer;
	protected long seed;
	
	
	private List<SolverStateListener> stateListeners = new ArrayList<SolverStateListener>(2);
	protected GenericSolutionType currentSolution = null;
	private int numberOfSteps = 0;
	private boolean isInitialized = false;
	
	/**
	 * Creates a new solver with the current time as seed for the according {@link Random}.
	 */
	public Solver() {
		this(System.currentTimeMillis());
	}
	
	/**
	 * Creates a new solver with the given seed for the according {@link Random}.
	 */
	public Solver(long seed) {
		this.randomizer = new Random(seed);
		this.seed = seed;		
	}	
	
	/**
	 * Adds a {@linkplain SolverStateListener} to this solver.
	 * The listener will be informed about important events that may occur on this solver.
	 */
	public void addStateListener(SolverStateListener listener) {
		stateListeners.add(listener);
	}
	
	/**
	 * Calls {@link #step()} until {@link #hasFinished()} returns <code>true</code>. 
	 */
	@Override
	public void run() {				
		while (hasFinished() == false) {				
			step();
		}
	}	
	
	/**
	 * Performs a single step in the algorithm of this solver.
	 * The following actions will be taken:
	 * <ol>
	 * <li>The solver will be initialized if it has not yet
	 * been initialized by a previous call to this method</li>
	 * <li>The actual algorithm step will be performed</li>
	 * <li>The step counter will be advanced by 1</li>
	 * <li>All {@link SolverStateListener SolverStateListeners}
	 * will be notified by a call to {@link SolverStateListener#solverStateHasChanged()}</li>
	 * 
	 * @see #initialize()
	 * @see Solver#doStep()
	 */
	public void step() {
		if(isInitialized == false) {
			initialize();
		}		
		doStep();
		numberOfSteps++;
		notifyStateChanged();
	}
	
	private void initialize() {
		isInitialized  = true;
		numberOfSteps = 0;
		doInitialize();		
	}
	
	private void notifyStateChanged() {
		for (SolverStateListener listener : stateListeners) {
			listener.solverStateHasChanged();
		}
	}
	
	/**
	 * Resets this solver.
	 * The current solution will be discarded and the solver will be
	 * initialized again.
	 * All {@link SolverStateListener SolverStateListeners} will be notified.
	 */
	public void reset() {
		currentSolution = null;
		initialize();
		notifySolverReset();
	}
	
	private void notifySolverReset() {		
		for (SolverStateListener listener : stateListeners) {
			listener.solverHasBeenRestarted();			
		}
	}
	
	/**
	 * Returns how often the {@link #step()} method has been called since the last
	 * call to {@link #initialize()}.
	 */
	public int getNumberOfSteps() {
		return numberOfSteps;
	}
	
	/**
	 * TODO add javadoc
	 */
	protected abstract void doInitialize();
	
	/**
	 * TODO add javadoc
	 */
	protected abstract void doStep();
	
	/**
	 * TODO add javadoc
	 */
	public abstract boolean hasFinished();
	
	/**
	 * TODO add javadoc
	 */
	public abstract GenericSolutionType getCurrentSolution();	
}

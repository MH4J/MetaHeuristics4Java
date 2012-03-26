package de.mh4j.solver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractSolver<GenericSolutionType> implements Runnable {
	protected final Logger log = (Logger) LoggerFactory.getLogger(getClass());
	
	protected final Random randomizer;
	protected long seed;
	
	
	private List<SolverStateListener> stateListeners = new ArrayList<SolverStateListener>(2);
	protected GenericSolutionType currentSolution = null;
	private int numberOfSteps = 0;
	private boolean isInitialized = false;;
	
	/**
	 * TODO add javadoc
	 */
	public AbstractSolver() {
		this(System.currentTimeMillis());
	}
	
	/**
	 * TODO add javadoc
	 */
	public AbstractSolver(long seed) {
		this.randomizer = new Random(seed);
		this.seed = seed;
	}
	
	/**
	 * TODO add javadoc
	 */
	public void addStateListener(SolverStateListener listener) {
		stateListeners.add(listener);
	}
	
	/**
	 * TODO add javadoc
	 */
	public void run() {				
		while (hasFinished() == false) {				
			doStep();
		}
	}
	
	private void initialize() {
		isInitialized  = true;
		numberOfSteps = 0;
		doInitialize();
		notifySolverReset();
	}
	
	private void notifySolverReset() {		
		for (SolverStateListener listener : stateListeners) {
			listener.solverHasBeenRestarted();			
		}
	}
	
	/**
	 * TODO add javadoc
	 */
	public void step() {
		if(isInitialized == false) {
			initialize();
		}		
		doStep();
		numberOfSteps++;
		notifyStateChanged();
	}
	
	private void notifyStateChanged() {
		for (SolverStateListener listener : stateListeners) {
			listener.solverStateHasChanged();
		}
	}
	
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
	
	public GenericSolutionType getCurrentSolution() {
		return currentSolution;
	}	
}

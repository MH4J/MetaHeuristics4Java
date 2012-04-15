package de.mh4j.solver.simulatedAnnealing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mh4j.solver.Solution;

public abstract class AbstractCoolingScheme<GenericSolutionType extends Solution> {
    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Determines how long (in number of steps) the algorithm runs until the
     * current acceptance probability is decreased. Mathematically spoken this
     * is the length of the markov chain.
     */
    protected int epochLength;

    protected double currentTemperature;

    private int nrOfStepsInThisEpoch;

    /**
     * Creates and initializes a cooling scheme. The initial temperature and
     * epoch length will be set.
     * 
     * @see #getInitialTemperature()
     * @see #getInitialEpochLength()
     */
    public AbstractCoolingScheme() {
        initialize();
    }

    private void initialize() {
        currentTemperature = getInitialTemperature();
        epochLength = getInitialEpochLength();
    }

    /**
     * This method is called when this cooling scheme is initialized (usually in
     * the constructor).
     * 
     * @return The initial temperature from where the algorithms start.
     */
    protected abstract double getInitialTemperature();

    /**
     * This method is called when this cooling scheme is initialized (usually in
     * the constructor).
     * 
     * @return The length of the first epoch.
     * @see #epochLength
     */
    protected abstract int getInitialEpochLength();

    /**
     * TODO write javadoc
     */
    public void updateTemperature() {
        if (nrOfStepsInThisEpoch >= epochLength) {
            nrOfStepsInThisEpoch = 0;
            decreaseTemperature();
            log.trace("Temperature has been decreased to {}", currentTemperature);
        }
        nrOfStepsInThisEpoch++;
    }

    /**
     * TODO write javadoc
     */
    protected abstract void decreaseTemperature();

    /**
     * TODO write javadoc
     */
    public double getAcceptanceProbability(GenericSolutionType currentSolution, GenericSolutionType neighborSolution) {
        int currentCosts = currentSolution.getCosts();
        int neighborCosts = neighborSolution.getCosts();
        return Math.exp(-Math.log(neighborCosts - currentCosts) / currentTemperature);
    }

}

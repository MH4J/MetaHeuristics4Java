package de.mh4j.solver.simulatedAnnealing;

import de.mh4j.solver.NeighborFunction;
import de.mh4j.solver.Solution;

public class SimulatedAnnealingSolver<GenericSolutionType extends Solution> extends
        AbstractSimulatedAnnealingSolver<GenericSolutionType> {

    private NeighborFunction<GenericSolutionType> neighborFunction;
    private int numberOfConsecutiveSteadyStepsUntilTermination;

    public SimulatedAnnealingSolver(AbstractCoolingScheme<GenericSolutionType> coolingScheme,
            NeighborFunction<GenericSolutionType> neighborFunction, int numberOfConsecutiveSteadyStepsUntilTermination) {
        this(coolingScheme, neighborFunction, numberOfConsecutiveSteadyStepsUntilTermination, System
                .currentTimeMillis());
    }

    public SimulatedAnnealingSolver(AbstractCoolingScheme<GenericSolutionType> coolingScheme,
            NeighborFunction<GenericSolutionType> neighborFunction, int numberOfConsecutiveSteadyStepsUntilTermination,
            long seed) {
        super(coolingScheme, seed);
        this.neighborFunction = neighborFunction;
        this.numberOfConsecutiveSteadyStepsUntilTermination = numberOfConsecutiveSteadyStepsUntilTermination;
    }

    @Override
    protected GenericSolutionType createInitialSolution() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected GenericSolutionType createRandomNeighbor() {
        return neighborFunction.createRandomNeighbor(currentSolution);
    }

    @Override
    public boolean hasFinished() {
        return situationHasNotImproved >= numberOfConsecutiveSteadyStepsUntilTermination;
    }

}

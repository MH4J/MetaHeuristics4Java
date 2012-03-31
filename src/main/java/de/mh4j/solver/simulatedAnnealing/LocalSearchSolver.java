package de.mh4j.solver.simulatedAnnealing;

import de.mh4j.solver.Solution;
import de.mh4j.solver.AbstractSolver;

public abstract class LocalSearchSolver<GenericSolutionType extends Solution> extends AbstractSolver<GenericSolutionType> {
			
	protected int situationHasNotImproved = 0;		
	
	@Override
	public void doInitialize() {
		situationHasNotImproved = 0;
	}

	@Override
	public void doStep() {
		GenericSolutionType neighbor = createRandomNeighbor();		
		
		if (neighbor.isBetterThan(currentSolution)) {
			currentSolution = neighbor;
			situationHasNotImproved = 0;
			log.debug("Found a better neighbor. New costs are {}", neighbor.getCosts());
		} else {
			situationHasNotImproved++;
			log.debug("Neighbor is worse than the current configuration. Costs stay at {}", currentSolution.getCosts());
		}
	}

	protected abstract GenericSolutionType createRandomNeighbor();
	
}

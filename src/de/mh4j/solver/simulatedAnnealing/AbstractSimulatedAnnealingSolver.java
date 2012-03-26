package de.mh4j.solver.simulatedAnnealing;

import de.mh4j.solver.Solution;


public abstract class AbstractSimulatedAnnealingSolver<GenericSolutionType extends Solution> extends AbstractLocalSearchSolver<GenericSolutionType> {

	protected AbstractCoolingScheme<GenericSolutionType> coolingScheme;			
	protected GenericSolutionType bestSolutionEverFound;
	
	public AbstractSimulatedAnnealingSolver(AbstractCoolingScheme<GenericSolutionType> coolingScheme) {
		this(coolingScheme, System.currentTimeMillis());
	}
	
	public AbstractSimulatedAnnealingSolver(AbstractCoolingScheme<GenericSolutionType> coolingScheme, long seed) {
		super(seed);
		this.coolingScheme = coolingScheme;		
	}	
	
	@Override
	protected void doInitialize() {		
		super.doInitialize();
		bestSolutionEverFound = currentSolution;
	}
	
	@Override
	protected void doStep() {				
		GenericSolutionType neighborSolution = createRandomNeighbor();		

		if (neighborSolution.isBetterThan(currentSolution)) {			
			currentSolution = neighborSolution;
			
			if(neighborSolution.isBetterThan(bestSolutionEverFound)) {
				situationHasNotImproved = 0;
				bestSolutionEverFound = neighborSolution;
			}
			else {
				situationHasNotImproved++;
			}
			
			log.debug("Found a better neighbor. New costs are {}", neighborSolution.getCosts());			
		} else {
			double acceptanceProbability = coolingScheme.getAcceptanceProbability(neighborSolution, currentSolution);
			if (randomizer.nextDouble() < acceptanceProbability) {
				currentSolution = neighborSolution;
				log.debug("Took worse neighbor because of the cooling scheme acceptance probability. New costs are {}", neighborSolution.getCosts());
			} 
			else {
				log.debug("Neighbor is worse than the current configuration. Costs stay at {}", currentSolution.getCosts());				
			}
			situationHasNotImproved++;
		}		
		
		coolingScheme.updateTemperature();		
	}	
	
	public AbstractCoolingScheme<GenericSolutionType> getCoolingScheme() {
		return coolingScheme;
	}
}

package de.mh4j.solver;

public interface NeighborFunction<GenericSolutionType> {

	public GenericSolutionType createRandomNeighbor(GenericSolutionType currentSolution);
	
}

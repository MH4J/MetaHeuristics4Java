package de.mh4j.solver;

public interface NeighborFunction<GenericSolutionType> {

    GenericSolutionType createRandomNeighbor(GenericSolutionType currentSolution);

}

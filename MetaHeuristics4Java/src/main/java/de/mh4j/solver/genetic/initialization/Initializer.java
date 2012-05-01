package de.mh4j.solver.genetic.initialization;

import de.mh4j.solver.genetic.Genome;
import de.mh4j.solver.genetic.genepool.GenePool;

/**
 * 
 * TODO write class description
 * 
 */
public interface Initializer<GenericGenomeType extends Genome> {

    /**
     * TODO write javadoc
     */
    GenePool<GenericGenomeType> getInitialGenePool(int populationSize);

}

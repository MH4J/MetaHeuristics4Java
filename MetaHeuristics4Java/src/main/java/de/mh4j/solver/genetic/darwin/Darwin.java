package de.mh4j.solver.genetic.darwin;

import de.mh4j.solver.genetic.Genome;
import de.mh4j.solver.genetic.genepool.GenePool;

/**
 * 
 * TODO write class description
 * 
 */
public interface Darwin<GenericGenomeType extends Genome> {

    /**
     * TODO add javadoc
     */
    public void select(GenePool<GenericGenomeType> genePool, int numberOfSurvivors);

}

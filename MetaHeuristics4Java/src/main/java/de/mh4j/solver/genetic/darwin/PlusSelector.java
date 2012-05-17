package de.mh4j.solver.genetic.darwin;

import java.util.Iterator;

import de.mh4j.solver.genetic.Genome;
import de.mh4j.solver.genetic.genepool.GenePool;

/**
 * 
 * TODO write class description
 * 
 */
public class PlusSelector<GenericGenomeType extends Genome> implements Darwin<GenericGenomeType> {

    /**
     * TODO add javadoc
     */
    @Override
    public void select(GenePool<GenericGenomeType> genePool, int numberOfSurvivors) {
        Iterator<GenericGenomeType> genomeIt = genePool.iterator();
        while (genePool.getSize() > numberOfSurvivors && genomeIt.hasNext()) {
            genomeIt.next();
            genomeIt.remove();
        }
    }

}

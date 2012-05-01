package de.mh4j.solver.genetic.darwin;

import java.util.Iterator;

import de.mh4j.solver.genetic.Genome;
import de.mh4j.solver.genetic.genepool.GenePool;

/**
 * 
 * TODO write class description
 * 
 */
public class AgeSelector<GenericGenomeType extends Genome> extends PlusSelector<GenericGenomeType> {

    /**
     * TODO add javadoc
     */
    protected int maxAge;

    /**
     * TODO add javadoc
     */
    public AgeSelector(int maxAge) {
        this.maxAge = maxAge;
    }

    /**
     * TODO add javadoc
     */
    @Override
    public void select(GenePool<GenericGenomeType> genePool, int numberOfSurvivors) {

        // remove old individuals
        int currentGeneration = genePool.getCurrentGeneration();
        Iterator<GenericGenomeType> genePoolIt = genePool.iterator();
        while (genePoolIt.hasNext()) {
            GenericGenomeType genome = genePoolIt.next();
            if (currentGeneration - genome.getBirthGeneration() > maxAge) {
                genePoolIt.remove();
            }
        }

        super.select(genePool, numberOfSurvivors);
    }
}

package de.mh4j.solver.genetic.darwin;

import de.mh4j.solver.genetic.Genome;
import de.mh4j.solver.genetic.genepool.GenePool;

/**
 * 
 * TODO write class description
 * 
 */
public class MaxAgeVaryingSelector<GenericGenomeType extends Genome> extends AgeSelector<GenericGenomeType> {

    /**
     * TODO add javadoc
     */
    public MaxAgeVaryingSelector(int maxAge) {
        super(maxAge);
    }

    /**
     * TODO add javadoc
     */
    @Override
    public void select(GenePool<GenericGenomeType> genePool, int numberOfSurvivors) {
        if (genePool.getSuccessRate() < 0.2d) {
            if (maxAge > 0) {
                maxAge--;
            }
        }
        else {
            maxAge++;
        }
        super.select(genePool, numberOfSurvivors);
    }
}

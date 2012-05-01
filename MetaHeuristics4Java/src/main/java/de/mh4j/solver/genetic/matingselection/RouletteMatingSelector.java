package de.mh4j.solver.genetic.matingselection;

import de.mh4j.solver.genetic.Genome;
import de.mh4j.solver.genetic.genepool.GenePool;

/**
 * 
 * TODO write class description
 * 
 */
public class RouletteMatingSelector<GenericGenomeType extends Genome> extends AbstractMatingSelector<GenericGenomeType> {

    /**
     * TODO write javadoc
     */
    public RouletteMatingSelector() {
        super();
    }

    /**
     * TODO write javadoc
     */
    public RouletteMatingSelector(long seed) {
        super(seed);
    }

    /**
     * TODO write javadoc
     */
    @Override
    protected Genome selectMate(GenePool<GenericGenomeType> genePool) {
        double threshold = random.nextDouble();
        double temp = 0;

        for (Genome genome : genePool) {
            // individuals with higher fitness will be selected with higher
            // probability
            long fitnessSum = genePool.getFitnessSum();
            double selectionProbability = (fitnessSum == 0) ? 1 : genome.getFitness() / (double) fitnessSum;

            temp += selectionProbability;
            if (temp >= threshold) {
                return genome;
            }
        }
        throw new IllegalStateException("this should never happen");
    }
}

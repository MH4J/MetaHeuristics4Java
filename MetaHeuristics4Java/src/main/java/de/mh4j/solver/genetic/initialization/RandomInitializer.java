package de.mh4j.solver.genetic.initialization;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mh4j.solver.genetic.Genome;
import de.mh4j.solver.genetic.genepool.GenePool;
import de.mh4j.util.RNGGenerator;

/**
 * 
 * A RandomInitializer creates the initial gene pool by randomly creating
 * genomes.
 * 
 */
public abstract class RandomInitializer<GenericGenomeType extends Genome> implements Initializer<GenericGenomeType> {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected final Random random;

    /**
     * TODO write javadoc
     */
    protected RandomInitializer() {
        random = RNGGenerator.createRandomNumberGenerator();
    }

    @Override
    public GenePool<GenericGenomeType> getInitialGenePool(int populationSize) {
        GenePool<GenericGenomeType> genePool = new GenePool<>();
        for (int i = 0; i < populationSize; i++) {
            genePool.addGenome(getRandomGenome());
        }
        return genePool;
    }

    /**
     * TODO write javadoc
     */
    protected abstract GenericGenomeType getRandomGenome();

}

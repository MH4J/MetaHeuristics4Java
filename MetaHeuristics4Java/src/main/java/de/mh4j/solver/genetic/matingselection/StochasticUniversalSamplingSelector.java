package de.mh4j.solver.genetic.matingselection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mh4j.solver.genetic.Couple;
import de.mh4j.solver.genetic.Genome;
import de.mh4j.solver.genetic.genepool.GenePool;

/**
 * 
 * TODO write class description
 * 
 */
public class StochasticUniversalSamplingSelector<GenericGenomeType extends Genome> implements
        MatingSelector<GenericGenomeType> {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final Random random;

    /**
     * TODO write javadoc
     */
    public StochasticUniversalSamplingSelector() {
        this(System.currentTimeMillis());
    }

    /**
     * TODO write javadoc
     */
    public StochasticUniversalSamplingSelector(long seed) {
        log.info("Seed is {}", seed);
        random = new Random(seed);
    }

    /**
     * TODO write javadoc
     */
    @Override
    public Collection<Couple> select(int numberOfPairs, GenePool<GenericGenomeType> genePool) {
        List<Couple> couples = new ArrayList<Couple>(numberOfPairs);
        for (int i = 0; i < numberOfPairs; i++) {
            couples.add(selectRandomCouple(genePool));
        }

        return couples;
    }

    private Couple selectRandomCouple(GenePool<GenericGenomeType> genePool) {
        double pointer1 = random.nextDouble();
        double pointer2;

        if (pointer1 > 0.5) {
            pointer2 = pointer1;
            pointer1 = 1 - pointer1;
        }
        else {
            pointer2 = 1 - pointer1;
        }

        long fitnessSum = genePool.getFitnessSum();
        double probabilitySum = 0;
        Genome parent1 = null;
        for (Genome genome : genePool) {
            double selectionProbability = (fitnessSum == 0) ? 1 : genome.getFitness() / (double) fitnessSum;
            probabilitySum += selectionProbability;

            if (parent1 == null && probabilitySum >= pointer1) {
                parent1 = genome;
            }
            else if (probabilitySum >= pointer2) {
                return new Couple(parent1, genome);
            }
        }

        throw new IllegalStateException("this should never happen");
    }

}

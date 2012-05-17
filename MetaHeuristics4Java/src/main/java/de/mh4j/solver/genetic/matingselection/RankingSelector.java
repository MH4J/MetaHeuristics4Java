package de.mh4j.solver.genetic.matingselection;

import java.util.HashMap;
import java.util.Iterator;

import de.mh4j.solver.genetic.Genome;
import de.mh4j.solver.genetic.genepool.GenePool;

/**
 * 
 * TODO write class description
 * 
 */
public class RankingSelector<GenericGenomeType extends Genome> extends AbstractMatingSelector<GenericGenomeType> {

    private HashMap<Genome, Integer> ranks;
    private int sumOfAllRanks;

    /**
     * TODO write javadoc
     */
    public RankingSelector() {
        super();
    }

    /**
     * TODO write javadoc
     */
    public RankingSelector(long seed) {
        super(seed);
    }

    /**
     * TODO write javadoc
     */
    @Override
    protected GenericGenomeType selectMate(GenePool<GenericGenomeType> genePool) {
        double threshold = random.nextDouble();
        double temp = 0;

        sumOfAllRanks = genePool.getSize() * (genePool.getSize() + 1) / 2;
        setRanks(genePool);

        for (GenericGenomeType genome : genePool) {
            // individuals with higher fitness will be selected with higher
            // probability
            double selectionProbability = ranks.get(genome).doubleValue() / sumOfAllRanks;

            temp += selectionProbability;
            if (temp >= threshold) {
                return genome;
            }
        }
        throw new IllegalStateException("This should never happen");
    }

    private void setRanks(GenePool<GenericGenomeType> genePool) {
        Iterator<GenericGenomeType> iterator = genePool.descendingIterator();
        // highest rank for the fittest individual, 1 for the least fittest
        // individual
        int currentRank = genePool.getSize();
        ranks = new HashMap<Genome, Integer>(genePool.getSize());
        while (iterator.hasNext()) {
            GenericGenomeType individual = iterator.next();
            ranks.put(individual, currentRank);
            currentRank--;
        }
    }
}

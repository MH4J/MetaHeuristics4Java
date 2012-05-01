package de.mh4j.solver.genetic.matingselection;

import java.util.ArrayList;
import java.util.Collection;
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
public class TournamentMatingSelector<GenericGenomeType extends Genome> implements MatingSelector<GenericGenomeType> {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final Random random;

    /**
     * TODO write javadoc
     */
    public TournamentMatingSelector() {
        this(System.currentTimeMillis());
    }

    /**
     * TODO write javadoc
     */
    public TournamentMatingSelector(long seed) {
        log.debug("Seed is {}", seed);
        random = new Random(seed);
    }

    /**
     * TODO write javadoc
     */
    @Override
    public Collection<Couple> select(int numberOfPairs, GenePool<GenericGenomeType> genePool) {
        Collection<Couple> couples = new ArrayList<Couple>(numberOfPairs);

        Genome[] genomes = genePool.toArray();
        for (int i = 0; i < numberOfPairs; i++) {
            Genome parent1 = selectMate(genomes);
            Genome parent2 = selectMate(genomes);
            couples.add(new Couple(parent1, parent2));
        }
        return couples;
    }

    /**
     * TODO write javadoc
     */
    protected Genome selectMate(Genome[] genomes) {
        Genome bestIndividual = null;
        int numberOfIndividualsToDrawForTournament = 1 + random.nextInt(genomes.length - 1);
        for (int i = 0; i < numberOfIndividualsToDrawForTournament; i++) {
            int indexOfIndividual = random.nextInt(genomes.length);
            Genome tmpIndividual = genomes[indexOfIndividual];
            if (bestIndividual == null) {
                bestIndividual = tmpIndividual;
            }
            else {
                if (bestIndividual.getFitness() < tmpIndividual.getFitness()) {
                    bestIndividual = tmpIndividual;
                }
            }
        }
        return bestIndividual;
    }
}

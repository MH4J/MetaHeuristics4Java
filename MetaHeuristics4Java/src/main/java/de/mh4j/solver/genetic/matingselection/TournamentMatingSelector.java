package de.mh4j.solver.genetic.matingselection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import de.mh4j.solver.genetic.Couple;
import de.mh4j.solver.genetic.Genome;
import de.mh4j.solver.genetic.genepool.GenePool;
import de.mh4j.util.RNGGenerator;

/**
 * 
 * TODO write class description
 * 
 */
public class TournamentMatingSelector<GenericGenomeType extends Genome> implements MatingSelector<GenericGenomeType> {

    private final Random random;

    /**
     * TODO write javadoc
     */
    public TournamentMatingSelector() {
        random = RNGGenerator.createRandomNumberGenerator();
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

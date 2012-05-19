package de.mh4j.solver.genetic.matingselection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mh4j.solver.genetic.Couple;
import de.mh4j.solver.genetic.Genome;
import de.mh4j.solver.genetic.genepool.GenePool;
import de.mh4j.util.RNGGenerator;

/**
 * 
 * TODO write class description
 * 
 */
public abstract class AbstractMatingSelector<GenericGenomeType extends Genome> implements
        MatingSelector<GenericGenomeType> {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    protected final Random random;

    /**
     * TODO write javadoc
     */
    protected AbstractMatingSelector() {
        random = RNGGenerator.createRandomNumberGenerator();
    }

    @Override
    public Collection<Couple> select(int numberOfPairs, GenePool<GenericGenomeType> genePool) {
        Collection<Couple> parents = new ArrayList<Couple>(numberOfPairs);
        for (int i = 0; i < numberOfPairs; i++) {
            parents.add(getCouple(genePool));
        }

        return parents;
    }

    private Couple getCouple(GenePool<GenericGenomeType> genePool) {
        Genome parent1 = selectMate(genePool);
        Genome parent2 = selectMate(genePool);
        return new Couple(parent1, parent2);
    }

    /**
     * TODO write javadoc
     */
    abstract protected Genome selectMate(GenePool<GenericGenomeType> genePool);

}

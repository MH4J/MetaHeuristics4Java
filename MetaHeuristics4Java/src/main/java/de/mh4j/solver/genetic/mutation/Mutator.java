package de.mh4j.solver.genetic.mutation;

import java.util.Random;

import de.mh4j.solver.genetic.Genome;

/**
 * 
 * TODO write class description
 * 
 */
public interface Mutator<GenericGenomeType extends Genome> {

    /**
     * TODO add javadoc
     */
    public GenericGenomeType mutate(GenericGenomeType genome);

    /**
     * TODO add javadoc
     */
    public void setRandom(Random random);

}

package de.mh4j.solver.genetic.mutation;

import java.util.Random;

import de.mh4j.solver.genetic.Genome;

/**
 * 
 * TODO write class description
 * 
 */
public class AggregateMutator<GenericGenomeType extends Genome> implements Mutator<GenericGenomeType> {

    private final Mutator<GenericGenomeType>[] mutators;

    /**
     * TODO add javadoc
     */
    public AggregateMutator(Mutator<GenericGenomeType>... mutators) {
        this.mutators = mutators;
    }

    @Override
    public GenericGenomeType mutate(GenericGenomeType genome) {

        GenericGenomeType mutatedGenome = genome;
        for (Mutator<GenericGenomeType> mutator : mutators) {
            mutatedGenome = mutator.mutate(mutatedGenome);
        }

        return mutatedGenome;
    }

    @Override
    public void setRandom(Random random) {
        for (Mutator<?> mutator : mutators) {
            mutator.setRandom(random);
        }
    }
}

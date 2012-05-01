package de.mh4j.solver.genetic.recombination;

import de.mh4j.solver.genetic.Couple;
import de.mh4j.solver.genetic.Genome;

/**
 * 
 * TODO write class description
 * 
 */
public interface Recombinator<GenericGenomeType extends Genome> {

    /**
     * TODO add javadoc
     */
    public GenericGenomeType recombinate(Couple couple);
}

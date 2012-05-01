package de.mh4j.solver.genetic.matingselection;

import java.util.Collection;

import de.mh4j.solver.genetic.Couple;
import de.mh4j.solver.genetic.Genome;
import de.mh4j.solver.genetic.genepool.GenePool;

/**
 * 
 * A Mating selector is responsible for the selecting the genomes that will
 * later be combined into new genomes.
 * 
 */
public interface MatingSelector<GenericGenomeType extends Genome> {

    /**
     * TODO write javadoc<br>
     * FIXME couldn't we also allow more than two parents? Maybe we should just
     * return a Set of Genomes?
     */
    Collection<Couple> select(int numberOfPairs, GenePool<GenericGenomeType> genePool);

}

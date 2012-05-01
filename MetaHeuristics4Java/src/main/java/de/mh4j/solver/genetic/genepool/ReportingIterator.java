package de.mh4j.solver.genetic.genepool;

import java.util.Iterator;

import de.mh4j.solver.genetic.Genome;

/**
 * 
 * TODO write class description
 * 
 */
public class ReportingIterator<GenericGenomeType extends Genome> implements Iterator<GenericGenomeType> {

    private final Iterator<GenericGenomeType> decoree;
    private final GenePool<GenericGenomeType> genePool;

    private GenericGenomeType current;

    /**
     * TODO add javadoc
     */
    public ReportingIterator(Iterator<GenericGenomeType> decoree, GenePool<GenericGenomeType> genePool) {
        this.decoree = decoree;
        this.genePool = genePool;
    }

    @Override
    public boolean hasNext() {
        return decoree.hasNext();
    }

    @Override
    public GenericGenomeType next() {
        current = decoree.next();
        return current;
    }

    @Override
    public void remove() {
        decoree.remove();
        genePool.reportDeadGenome(current);
    }
}

package de.mh4j.solver.genetic.genepool;

import java.util.Iterator;

import de.mh4j.solver.genetic.Genome;

/**
 * This is a decorator class for the iterator of the gene pool. Its purpose is
 * to report any genome removals as dead genomes to the gene pool.
 * 
 * @see GenePool
 */
class ReportingIterator<GenericGenomeType extends Genome> implements Iterator<GenericGenomeType> {

    private final Iterator<GenericGenomeType> decoree;
    private final GenePool<GenericGenomeType> genePool;

    private GenericGenomeType current;

    /**
     * Creates a new {@link ReportingIterator}.
     * 
     * @param decoree
     *            the original {@link Iterator} to which all calls to the
     *            iterator interface are finally marshaled.
     * @param genePool
     *            the {@link GenePool} which is informed if a genome has been
     *            removed via the iterators {@link #remove()} method.
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

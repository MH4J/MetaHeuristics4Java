/*
 * Copyright 2012   Friedrich Große, Paul Seiferth,
 *                  Sebastian Starroske, Yannik Stein
 *
 * This file is part of MetaHeuristics4Java.
 *
 * MetaHeuristics4Java is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MetaHeuristics4Java is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MetaHeuristics4Java. If not, see <http://www.gnu.org/licenses/>.
 */

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

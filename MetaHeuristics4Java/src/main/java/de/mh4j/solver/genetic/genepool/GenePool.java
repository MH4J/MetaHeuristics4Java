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

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mh4j.solver.genetic.Genome;

/**
 * 
 * TODO write class description
 * 
 */
public class GenePool<GenericGenomeType extends Genome> implements Iterable<GenericGenomeType> {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final TreeSet<GenericGenomeType> genePool;

    private int currentGeneration;
    private long fitnessSum;
    private int numberOfChildren = 0;
    private int numberOfDeadChildren;
    private double successRate = 0;

    private GenericGenomeType fittestGenome;

    /**
     * Creates a new and empty {@link GenePool}.
     */
    public GenePool() {
        genePool = new TreeSet<GenericGenomeType>();
    }

    /**
     * Copies all elements of an existing {@link GenePool}.<br>
     * <b>Note:</b> the {@link Genome} instances in the original gene pool are
     * not copied as well. Instead the references to these Genomes are copied
     * into the new gene pool.<br>
     * Example: Suppose you have a gene pool <code>a</code>. First you add the
     * genomes <code>x, y</code> and <code>z</code> to <code>a</code>. Then you
     * create a new gene pool <code>b</code> by copying <code>a</code>. All
     * changes you invoke in <code>x, y</code> and <code>z</code> will now be
     * effective in <em>both</em> gene pools.
     */
    public GenePool(GenePool<GenericGenomeType> originalGenePool) {
        this.genePool = originalGenePool.copyGenePool();
        this.fitnessSum = originalGenePool.fitnessSum;
        this.numberOfDeadChildren = originalGenePool.numberOfDeadChildren;
        this.fittestGenome = originalGenePool.fittestGenome;
        this.successRate = originalGenePool.successRate;
    }

    private TreeSet<GenericGenomeType> copyGenePool() {
        TreeSet<GenericGenomeType> copiedGenePool = new TreeSet<GenericGenomeType>();
        for (GenericGenomeType genome : this.genePool) {
            copiedGenePool.add(genome);
        }
        return copiedGenePool;
    }

    void reportDeadGenome(GenericGenomeType genome) {
        fitnessSum -= genome.getFitness();

        if (genome.getBirthGeneration() == currentGeneration) {
            numberOfDeadChildren++;
        }
    }

    /**
     * @return the number of genomes that are currently present in this gene
     *         pool.
     */
    public int getSize() {
        return genePool.size();
    }

    /**
     * TODO write javadoc
     */
    public int getCurrentGeneration() {
        return currentGeneration;
    }

    /**
     * TODO write javadoc
     */
    public long getFitnessSum() {
        return fitnessSum;
    }

    /**
     * Adds all elements of the given List to this gene pool.
     */
    public void addAllGenomes(List<GenericGenomeType> genomes) {
        for (GenericGenomeType genome : genomes) {
            addGenome(genome);
        }
    }

    /**
     * TODO write javadoc
     */
    public void addGenome(GenericGenomeType newGenome) {
        newGenome.setBirthGeneration(currentGeneration);
        genePool.add(newGenome);
        fitnessSum += newGenome.getFitness();

        if (fittestGenome == null || newGenome.isBetterThan(fittestGenome)) {
            fittestGenome = newGenome;
        }
        numberOfChildren++;
    }

    @Override
    public Iterator<GenericGenomeType> iterator() {
        return new ReportingIterator<GenericGenomeType>(genePool.iterator(), this);
    }

    /**
     * TODO write javadoc
     */
    public Iterator<GenericGenomeType> descendingIterator() {
        return new ReportingIterator<GenericGenomeType>(genePool.descendingIterator(), this);
    }

    /**
     * TODO write javadoc
     */
    public int getNumberOfDeadChildren() {
        return numberOfDeadChildren;
    }

    /**
     * TODO write javadoc
     */
    public void startNewGeneration() {
        currentGeneration++;
        successRate = (double) (numberOfChildren - numberOfDeadChildren) / (double) numberOfChildren;
        numberOfDeadChildren = 0;
        numberOfChildren = 0;
        log.debug("Success: {}", successRate);

        if (log.isTraceEnabled()) {
            Set<Genome> genomes = new HashSet<Genome>();
            int counter = 0;
            int duplicates = 0;
            for (Genome genome : this) {
                counter++;
                if (genomes.add(genome) == false) {
                    duplicates++;
                }
            }

            log.trace("Iterated over {} of {} genomes", counter, getSize());
            log.trace("{} duplicates", duplicates);

            for (Genome genome : this) {
                log.trace("generation {} ------> {}", currentGeneration, genome);
            }
        }
    }

    /**
     * Returns all genomes of this gene pool in a new array. The elements in the
     * array will be ordered by their fitness such that the first element in the
     * array will have the lowest fitness in the gene pool.
     */
    public Genome[] toArray() {
        return genePool.toArray(new Genome[getSize()]);
    }

    /**
     * @return the fittest genome that has ever been added to this gene pool or
     *         <code>null</code> if no genome has been added yet.
     */
    public GenericGenomeType getFittestGenome() {
        return fittestGenome;
    }

    /**
     * TODO write javadoc
     */
    public double getSuccessRate() {
        return successRate;
    }

    /**
     * TODO write javadoc
     */
    public int getNumberOfGenerationsSinceFittestGenomeHasNotChanged() {
        // FIXME what if fittestGenome is NULL
        return getCurrentGeneration() - fittestGenome.getBirthGeneration();
    }

    /**
     * Returns <code>true</code> if this gene pool contains the specified
     * element. More formally, returns <code>true</code> if and only if this
     * gene pool contains a genome <code>g</code> such that
     * <code>(genome==null ? g==null : genome.equals(g))</code>.
     * 
     * @param genome
     *            object to be checked for containment in this gene pool
     * 
     * @return <code>true</code> if the given genome is part of this gene pool,
     *         <code>false</code> otherwise.
     */
    public boolean contains(GenericGenomeType genome) {
        return genePool.contains(genome);
    }
}

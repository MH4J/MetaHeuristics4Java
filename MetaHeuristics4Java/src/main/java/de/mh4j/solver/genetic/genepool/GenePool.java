package de.mh4j.solver.genetic.genepool;

import java.util.HashSet;
import java.util.Iterator;
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
    private double successRate = 0d;

    private GenericGenomeType fittestGenome;

    /**
     * TODO write javadoc
     */
    public GenePool() {
        genePool = new TreeSet<GenericGenomeType>();
    }

    /**
     * TODO write javadoc
     */
    public GenePool(GenePool<GenericGenomeType> genePoolToCopy) {
        this.genePool = genePoolToCopy.copyGenePool();
        this.fitnessSum = genePoolToCopy.fitnessSum;
        this.numberOfDeadChildren = genePoolToCopy.numberOfDeadChildren;
        this.fittestGenome = genePoolToCopy.fittestGenome;
        this.successRate = genePoolToCopy.successRate;
    }

    private TreeSet<GenericGenomeType> copyGenePool() {
        TreeSet<GenericGenomeType> copiedGenePool = new TreeSet<GenericGenomeType>();
        for (GenericGenomeType genome : this.genePool) {
            copiedGenePool.add(genome);
        }
        return copiedGenePool;
    }

    // FIXME should this really be package visible?
    void reportDeadGenome(GenericGenomeType genome) {
        fitnessSum -= genome.getFitness();

        if (genome.getBirthGeneration() == currentGeneration) {
            numberOfDeadChildren++;
        }
    }

    /**
     * TODO write javadoc
     */
    public int size() {
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
     * TODO write javadoc
     */
    public void addGenome(GenericGenomeType newGenome) {
        newGenome.setBirthGeneration(currentGeneration);
        genePool.add(newGenome);
        fitnessSum += newGenome.getFitness();

        if (fittestGenome == null || fittestGenome.getFitness() < newGenome.getFitness()) {
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

            log.trace("Iterated over {} of {} genomes", counter, size());
            log.trace("{} duplicates", duplicates);

            for (Genome genome : this) {
                log.trace("generation {} ------> {}", currentGeneration, genome);
            }
        }
    }

    /**
     * TODO write javadoc
     */
    public Genome[] toArray() {
        return genePool.toArray(new Genome[0]);
    }

    /**
     * TODO write javadoc
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
        return getCurrentGeneration() - fittestGenome.getBirthGeneration();
    }
}

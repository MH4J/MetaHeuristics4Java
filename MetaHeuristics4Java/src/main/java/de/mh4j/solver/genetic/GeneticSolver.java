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

package de.mh4j.solver.genetic;

import java.util.Collection;

import de.mh4j.solver.AbstractSolver;
import de.mh4j.solver.genetic.darwin.Darwin;
import de.mh4j.solver.genetic.genepool.GenePool;
import de.mh4j.solver.genetic.initialization.Initializer;
import de.mh4j.solver.genetic.matingselection.MatingSelector;
import de.mh4j.solver.genetic.mutation.Mutator;
import de.mh4j.solver.genetic.recombination.Recombinator;
import de.mh4j.util.GaussianDistribution;

/**
 * 
 * TODO write class description
 * 
 * @author Friedrich Große
 * 
 * @param <GenericSolutionType>
 */
public class GeneticSolver<GenericGenomeType extends Genome> extends AbstractSolver<GenericGenomeType> {
    protected GenePool<GenericGenomeType> genePool;

    private final Initializer<GenericGenomeType> initializer;
    private final MatingSelector<GenericGenomeType> matingSelector;
    private final Recombinator<GenericGenomeType> recombinator;
    private final Mutator<GenericGenomeType> mutator;
    private final Darwin<GenericGenomeType> darwin;

    private final GaussianDistribution random;
    private final double standardDeviationFactor = 0.9;

    private final int populationSize;
    private final int numberOfChildren;

    /**
     * TODO add javadoc
     */
    public GeneticSolver(int populationSize, int numberOfChildren, Initializer<GenericGenomeType> initializer,
            MatingSelector<GenericGenomeType> matingSelector, Recombinator<GenericGenomeType> recombinator,
            Mutator<GenericGenomeType> mutator, Darwin<GenericGenomeType> darwin) {
        this.populationSize = populationSize;
        this.numberOfChildren = numberOfChildren;

        this.initializer = initializer;
        this.matingSelector = matingSelector;
        this.recombinator = recombinator;
        this.mutator = mutator;
        this.darwin = darwin;

        random = new GaussianDistribution();
        mutator.setRandom(random);
    }

    @Override
    public void doInitialize() {
        genePool = initializer.getInitialGenePool(populationSize);
    }

    // FIXME remove all this commented code >.<
    @Override
    protected void doStep() {
        Collection<Couple> selectedParents = matingSelector.select(numberOfChildren, genePool);

        for (Couple couple : selectedParents) {
            // log.info("Recombinate (costs {} and {}) {}", new Object[] {
            // couple.getParent1().getCosts(), couple.getParent2().getCosts(),
            // couple });
            GenericGenomeType child = recombinator.recombinate(couple);
            child = mutator.mutate(child);
            // log.info("Child (costs {}) {}", child.getCosts(), child);
            genePool.addGenome(child);
        }

        darwin.select(genePool, populationSize);

        genePool.startNewGeneration();
        updateMutationRate();
        // log.info("New Standard Deviation is {}",
        // random.getStandardDeviation());
        // log.info("{} of all children survived", genePool.getSuccessRate());
        // log.info("Best individual (costs {}) is {}",
        // genePool.getFittestGenome().getCosts(), genePool.getFittestGenome());
        // log.info("Best individual contains all wanted items: {}",
        // genePool.getFittestGenome().containsAllWantedItems());
        // log.info("");
    }

    /**
     * 1/5 - Success Rule:<br>
     * FIXME write javadoc
     */
    private void updateMutationRate() {
        if (genePool.getSuccessRate() < 0.2) {
            random.multStandardDeviationWith(standardDeviationFactor);
        }
        else if (random.getStandardDeviation() <= 10) {
            random.multStandardDeviationWith(1 / standardDeviationFactor);
        }
    }

    @Override
    public GenericGenomeType getCurrentSolution() {
        return genePool.getFittestGenome();
    }

    /**
     * TODO write javadoc
     */
    public GenePool<GenericGenomeType> getPopulation() {
        return genePool;
    }

    /**
     * TODO write javadoc
     */
    public void setPopulation(GenePool<GenericGenomeType> genePool) {
        this.genePool = genePool;
    }

}

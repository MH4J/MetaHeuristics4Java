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

package de.mh4j.solver.genetic.initialization;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mh4j.solver.genetic.Genome;
import de.mh4j.solver.genetic.genepool.GenePool;
import de.mh4j.util.RNGGenerator;

/**
 * 
 * A RandomInitializer creates the initial gene pool by randomly creating
 * genomes.
 * 
 */
public abstract class RandomInitializer<GenericGenomeType extends Genome> implements Initializer<GenericGenomeType> {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected final Random random;

    /**
     * TODO write javadoc
     */
    protected RandomInitializer() {
        random = RNGGenerator.createRandomNumberGenerator();
    }

    @Override
    public GenePool<GenericGenomeType> getInitialGenePool(int populationSize) {
        GenePool<GenericGenomeType> genePool = new GenePool<>();
        for (int i = 0; i < populationSize; i++) {
            genePool.addGenome(getRandomGenome());
        }
        return genePool;
    }

    /**
     * TODO write javadoc
     */
    protected abstract GenericGenomeType getRandomGenome();

}

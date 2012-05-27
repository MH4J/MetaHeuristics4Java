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

package de.mh4j.solver.genetic.darwin;

import java.util.Iterator;

import de.mh4j.solver.genetic.Genome;
import de.mh4j.solver.genetic.genepool.GenePool;

/**
 * 
 * TODO write class description
 * 
 */
public class AgeSelector<GenericGenomeType extends Genome> extends PlusSelector<GenericGenomeType> {

    /**
     * TODO add javadoc
     */
    protected int maxAge;

    /**
     * TODO add javadoc
     */
    public AgeSelector(int maxAge) {
        this.maxAge = maxAge;
    }

    /**
     * TODO add javadoc
     */
    @Override
    public void select(GenePool<GenericGenomeType> genePool, int numberOfSurvivors) {

        // remove old individuals
        int currentGeneration = genePool.getCurrentGeneration();
        Iterator<GenericGenomeType> genePoolIt = genePool.iterator();
        while (genePoolIt.hasNext()) {
            GenericGenomeType genome = genePoolIt.next();
            if (currentGeneration - genome.getBirthGeneration() > maxAge) {
                genePoolIt.remove();
            }
        }

        super.select(genePool, numberOfSurvivors);
    }
}

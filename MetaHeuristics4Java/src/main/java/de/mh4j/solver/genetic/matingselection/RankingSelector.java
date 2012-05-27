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

package de.mh4j.solver.genetic.matingselection;

import java.util.HashMap;
import java.util.Iterator;

import de.mh4j.solver.genetic.Genome;
import de.mh4j.solver.genetic.genepool.GenePool;

/**
 * 
 * TODO write class description
 * 
 */
public class RankingSelector<GenericGenomeType extends Genome> extends AbstractMatingSelector<GenericGenomeType> {

    private HashMap<Genome, Integer> ranks;
    private int sumOfAllRanks;

    /**
     * TODO write javadoc
     */
    @Override
    protected GenericGenomeType selectMate(GenePool<GenericGenomeType> genePool) {
        double threshold = random.nextDouble();
        double temp = 0;

        sumOfAllRanks = genePool.getSize() * (genePool.getSize() + 1) / 2;
        setRanks(genePool);

        for (GenericGenomeType genome : genePool) {
            // individuals with higher fitness will be selected with higher
            // probability
            double selectionProbability = ranks.get(genome).doubleValue() / sumOfAllRanks;

            temp += selectionProbability;
            if (temp >= threshold) {
                return genome;
            }
        }
        throw new IllegalStateException("This should never happen");
    }

    private void setRanks(GenePool<GenericGenomeType> genePool) {
        Iterator<GenericGenomeType> iterator = genePool.descendingIterator();
        // highest rank for the fittest individual, 1 for the least fittest
        // individual
        int currentRank = genePool.getSize();
        ranks = new HashMap<Genome, Integer>(genePool.getSize());
        while (iterator.hasNext()) {
            GenericGenomeType individual = iterator.next();
            ranks.put(individual, currentRank);
            currentRank--;
        }
    }
}

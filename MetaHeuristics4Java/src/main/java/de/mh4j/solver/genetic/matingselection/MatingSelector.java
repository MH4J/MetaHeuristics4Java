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

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

import java.util.Random;

import de.mh4j.solver.Solution;
import de.mh4j.util.Comparable;
import de.mh4j.util.RNGGenerator;

/**
 * 
 * TODO write class description
 * 
 */
public abstract class Genome implements Solution<Genome>, Comparable<Genome> {

    private final Random random;

    public final static int NO_BIRTH_GENERATION_ASSOCIATED = -1;

    private int birthGeneration;

    /**
     * TODO write javadoc<br>
     */
    public Genome() {
        random = RNGGenerator.createRandomNumberGenerator();
        birthGeneration = NO_BIRTH_GENERATION_ASSOCIATED;
    }

    /**
     * Returns the general Fitness of this genome. The better a genome is the
     * higher should this value be.
     */
    public abstract int getFitness();

    /**
     * The costs of a genome are the opposite of its fitness. The better a
     * genome is, the higher is its fitness and the lower will the costs be.
     * 
     * @return the negated return value of <code>getFitness()</code>
     * @see #getFitness()
     */
    @Override
    public int getCosts() {
        return getFitness();
    }

    /**
     * TODO write javadoc
     */
    public int getBirthGeneration() {
        return birthGeneration;
    }

    /**
     * TODO write javadoc
     */
    public void setBirthGeneration(int birthGeneration) {
        this.birthGeneration = birthGeneration;
    }

    /**
     * Compares this genome to another genome depending on the return values of
     * {@link #isBetterThan(Genome)} and {@link #equals(Object)}.
     * 
     * @param otherGenome
     *            the other genome which is compared to this genome
     * @return <code>Comparable.GREATER</code> if this genome is better<br>
     *         <code>Comparable.LOWER</code> if this genome is worse<br>
     *         <code>Comparable.EQUAL</code> if both genomes are equal<br>
     *         or some value randomly chosen between
     *         <code>Comparable.LOWER</code> and <code>Comparable.GREATER</code>
     *         if <code>this.isBetterThan(otherGenome)</code> and
     *         <code>otherGenome.isBetterThan(this)</code> and
     *         <code>this.equals(otherGenome)</code> all return
     *         <code>false</code>
     * @see Comparable
     */
    @Override
    public int compareTo(Genome otherGenome) {

        if (this.isBetterThan(otherGenome)) {
            return Comparable.GREATER;
        }
        else if (otherGenome.isBetterThan(this)) {
            return Comparable.LOWER;
        }
        else if (this.equals(otherGenome)) {
            return Comparable.EQUAL;
        }
        else {
            if (random.nextBoolean()) {
                return Comparable.LOWER;
            }
            else {
                return Comparable.GREATER;
            }
        }
    }
}

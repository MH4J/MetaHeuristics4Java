package de.mh4j.solver.genetic;

import java.util.Random;

import de.mh4j.solver.Solution;
import de.mh4j.util.Comparable;

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
     * TODO enable seed parameter for random
     */
    public Genome() {
        random = new Random();
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

package de.mh4j.solver.genetic;

import java.util.Random;

import de.mh4j.solver.Solution;
import de.mh4j.util.Comparable;

/**
 * 
 * TODO write class description
 * 
 */
public abstract class Genome implements Solution, Comparable<Genome> {
    private final Random random;

    private int birthGeneration;

    /**
     * TODO write javadoc
     */
    public Genome() {
        random = new Random();
        birthGeneration = -1;
    }

    /**
     * TODO write javadoc
     */
    public long getFitness() {
        return -getCosts();
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
     * TODO write javadoc
     */
    @Override
    public int compareTo(Genome otherGenome) {
        double thisFitness = this.getFitness();
        double otherFitness = otherGenome.getFitness();

        if (thisFitness < otherFitness) {
            return Comparable.LOWER;
        }
        else if (thisFitness > otherFitness) {
            return Comparable.GREATER;
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

package de.mh4j.solver.genetic;

import org.testng.annotations.Test;

import de.mh4j.util.Comparable;

public class GenomeTest {

    @Test
    public void testGetBirthGeneration_WhenInitialized_BirthGenerationIsUnset() throws Exception {
        Genome genome = new GenomeMock();
        assert genome.getBirthGeneration() == Genome.NO_BIRTH_GENERATION_ASSOCIATED;
    }

    @Test
    public void testSetBirthGeneration() throws Exception {
        final int birthGeneration = 42;

        Genome genome = new GenomeMock();
        genome.setBirthGeneration(birthGeneration);

        assert genome.getBirthGeneration() == birthGeneration;
    }

    @Test
    public void testCompareGenomes() {
        Genome badGenome = new GenomeMock(20);
        Genome goodGenome = new GenomeMock(40);

        assert badGenome.compareTo(goodGenome) == Comparable.LOWER : "Bad genome should be worse than better genome";
        assert goodGenome.compareTo(badGenome) == Comparable.GREATER : "Good genome should be better than worse genome";
        assert goodGenome.compareTo(goodGenome) == Comparable.EQUAL : "Good genome compared to itself should return Comparable.EQUAL";
    }
}

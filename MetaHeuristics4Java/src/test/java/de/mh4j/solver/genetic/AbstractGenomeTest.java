package de.mh4j.solver.genetic;

import static org.mockito.Mockito.when;

public abstract class AbstractGenomeTest {

    protected void makeGenomeMockComparable(Genome genome, Genome otherGenome) {
        if (genome.getFitness() == otherGenome.getFitness()) {
            when(genome.compareTo(otherGenome)).thenReturn(Genome.EQUAL);
            when(genome.isBetterThan(otherGenome)).thenReturn(false);
        }
        if (genome.getFitness() > otherGenome.getFitness()) {
            when(genome.compareTo(otherGenome)).thenReturn(Genome.GREATER);
            when(genome.isBetterThan(otherGenome)).thenReturn(true);
        }
        if (genome.getFitness() < otherGenome.getFitness()) {
            when(genome.compareTo(otherGenome)).thenReturn(Genome.LOWER);
            when(genome.isBetterThan(otherGenome)).thenReturn(false);
        }
    }

}

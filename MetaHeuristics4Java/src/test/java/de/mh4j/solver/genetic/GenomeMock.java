package de.mh4j.solver.genetic;

public class GenomeMock extends Genome {

    private final int fitness;

    public GenomeMock() {
        this(0);
    }

    public GenomeMock(int fitness) {
        this.fitness = fitness;
    }

    @Override
    public int getFitness() {
        return fitness;
    }

    @Override
    public boolean isBetterThan(Genome otherSolution) {
        return this.fitness > otherSolution.getFitness();
    }

}

package de.mh4j.solver.genetic.darwin;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import de.mh4j.solver.genetic.AbstractGenomeTest;
import de.mh4j.solver.genetic.Genome;
import de.mh4j.solver.genetic.genepool.GenePool;

public class AgeSelectorTest extends AbstractGenomeTest {

    private List<Genome> oldGenomes;
    private List<Genome> newGenomes;

    @Test
    public void testSelect() {
        int maxAge = 3;
        int currentGeneration = 6;

        GenePool<Genome> population = new GenePool<>();
        List<Genome> allGenomes = createPopulation(maxAge, currentGeneration);

        population.addAllGenomes(allGenomes);

        // iterate to reach preferred generation
        for (int i = 0; i < currentGeneration; i++) {
            population.startNewGeneration();
        }

        Darwin<Genome> selector = new AgeSelector<>(maxAge);
        selector.select(population, 5);

        assert population.getSize() == 5 : "New population should have size: 5";

        for (Genome genome : oldGenomes) {
            assert population.contains(genome) == false;
        }

        for (Genome genome : newGenomes) {
            assert population.contains(genome) == true;
        }
    }

    private List<Genome> createPopulation(int maxAge, int currentGeneration) {
        int totalNumberOfGenomes = 10;
        int tooOld = currentGeneration - maxAge - 1;
        oldGenomes = new ArrayList<>(totalNumberOfGenomes / 2);
        newGenomes = new ArrayList<>(totalNumberOfGenomes / 2);

        Genome genome1 = mock(Genome.class);
        when(genome1.getFitness()).thenReturn(1000);
        when(genome1.getBirthGeneration()).thenReturn(tooOld);
        oldGenomes.add(genome1);

        Genome genome2 = mock(Genome.class);
        when(genome2.getFitness()).thenReturn(2000);
        when(genome2.getBirthGeneration()).thenReturn(tooOld);
        oldGenomes.add(genome2);

        Genome genome3 = mock(Genome.class);
        when(genome3.getFitness()).thenReturn(3000);
        when(genome3.getBirthGeneration()).thenReturn(tooOld);
        oldGenomes.add(genome3);

        Genome genome4 = mock(Genome.class);
        when(genome4.getFitness()).thenReturn(4000);
        when(genome4.getBirthGeneration()).thenReturn(tooOld);
        oldGenomes.add(genome4);

        Genome genome5 = mock(Genome.class);
        when(genome5.getFitness()).thenReturn(5000);
        when(genome5.getBirthGeneration()).thenReturn(tooOld);
        oldGenomes.add(genome5);

        Genome genome6 = mock(Genome.class);
        when(genome6.getFitness()).thenReturn(6000);
        when(genome6.getBirthGeneration()).thenReturn(currentGeneration);
        newGenomes.add(genome6);

        Genome genome7 = mock(Genome.class);
        when(genome7.getFitness()).thenReturn(7000);
        when(genome7.getBirthGeneration()).thenReturn(currentGeneration);
        newGenomes.add(genome7);

        Genome genome8 = mock(Genome.class);
        when(genome8.getFitness()).thenReturn(8000);
        when(genome8.getBirthGeneration()).thenReturn(currentGeneration);
        newGenomes.add(genome8);

        Genome genome9 = mock(Genome.class);
        when(genome9.getFitness()).thenReturn(9000);
        when(genome9.getBirthGeneration()).thenReturn(currentGeneration);
        newGenomes.add(genome9);

        Genome genome10 = mock(Genome.class);
        when(genome10.getFitness()).thenReturn(10000);
        when(genome10.getBirthGeneration()).thenReturn(currentGeneration);
        newGenomes.add(genome10);

        List<Genome> allGenomes = new ArrayList<>(totalNumberOfGenomes);
        allGenomes.addAll(oldGenomes);
        allGenomes.addAll(newGenomes);

        for (int i = 0; i < totalNumberOfGenomes; i++) {
            for (int j = 0; j < totalNumberOfGenomes; j++) {
                makeGenomeMockComparable(allGenomes.get(i), allGenomes.get(j));
            }
        }

        return allGenomes;
    }
}

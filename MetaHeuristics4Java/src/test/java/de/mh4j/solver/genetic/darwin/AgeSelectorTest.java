package de.mh4j.solver.genetic.darwin;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.testng.annotations.Test;

import de.mh4j.solver.genetic.Genome;
import de.mh4j.solver.genetic.genepool.GenePool;

public class AgeSelectorTest {

    @Test
    public void testSelect() {
        int maxAge = 3;
        int currentGeneration = 6;
        int tooOld = currentGeneration - maxAge - 1;

        // prepare genome mocks
        Genome genome1 = mock(Genome.class);
        when(genome1.getCosts()).thenReturn(1000);
        when(genome1.getBirthGeneration()).thenReturn(tooOld);
        Genome genome2 = mock(Genome.class);
        when(genome2.getCosts()).thenReturn(2000);
        when(genome2.getBirthGeneration()).thenReturn(currentGeneration);
        Genome genome3 = mock(Genome.class);
        when(genome3.getCosts()).thenReturn(3000);
        when(genome3.getBirthGeneration()).thenReturn(currentGeneration);
        Genome genome4 = mock(Genome.class);
        when(genome4.getCosts()).thenReturn(4000);
        when(genome4.getBirthGeneration()).thenReturn(tooOld);
        Genome genome5 = mock(Genome.class);
        when(genome5.getCosts()).thenReturn(5000);
        when(genome5.getBirthGeneration()).thenReturn(currentGeneration);
        Genome genome6 = mock(Genome.class);
        when(genome6.getCosts()).thenReturn(6000);
        when(genome6.getBirthGeneration()).thenReturn(currentGeneration);
        Genome genome7 = mock(Genome.class);
        when(genome7.getCosts()).thenReturn(7000);
        when(genome7.getBirthGeneration()).thenReturn(currentGeneration);
        Genome genome8 = mock(Genome.class);
        when(genome8.getCosts()).thenReturn(8000);
        when(genome8.getBirthGeneration()).thenReturn(currentGeneration);
        Genome genome9 = mock(Genome.class);
        when(genome9.getCosts()).thenReturn(9000);
        when(genome9.getBirthGeneration()).thenReturn(currentGeneration);
        Genome genome10 = mock(Genome.class);
        when(genome10.getCosts()).thenReturn(10000);
        when(genome10.getBirthGeneration()).thenReturn(currentGeneration);

        // add all mocks to the population
        GenePool<Genome> population = new GenePool<>();
        population.addGenome(genome1);
        population.addGenome(genome2);
        population.addGenome(genome3);
        population.addGenome(genome4);
        population.addGenome(genome5);
        population.addGenome(genome6);
        population.addGenome(genome7);
        population.addGenome(genome8);
        population.addGenome(genome9);
        population.addGenome(genome10);

        // iterate to reach preferred generation
        for (int i = 0; i < currentGeneration; i++) {
            population.startNewGeneration();
        }

        Darwin<Genome> selector = new AgeSelector<>(maxAge);
        selector.select(population, 5);

        assert population.size() == 5 : "New population should have size: 5";
        assert population.contains(genome1) == false : "New population should not contain genome 1";
        assert population.contains(genome2) == true : "New population should contain genome 2";
        assert population.contains(genome3) == true : "New population should contain genome 3";
        assert population.contains(genome4) == false : "New population should not contain genome 4";
        assert population.contains(genome5) == true : "New population should contain genome 5";
        assert population.contains(genome6) == true : "New population should contain genome 6";
        assert population.contains(genome7) == true : "New population should contain genome 7";
        assert population.contains(genome8) == false : "New population should not contain genome 8";
        assert population.contains(genome9) == false : "New population should not contain genome 9";
        assert population.contains(genome10) == false : "New population should not contain genome 10";
    }
}

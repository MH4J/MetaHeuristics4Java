package de.mh4j.solver.genetic.darwin;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import de.mh4j.solver.genetic.AbstractGenomeTest;
import de.mh4j.solver.genetic.Genome;
import de.mh4j.solver.genetic.genepool.GenePool;

public class PlusSelectorTest extends AbstractGenomeTest {

    @Test
    public void testSelect() {
        Darwin<Genome> selector = new PlusSelector<>();

        List<Genome> allGenomes = createPopulation();

        GenePool<Genome> population = new GenePool<>();
        population.addAllGenomes(allGenomes);

        selector.select(population, 5);

        assert population.size() == 5;

        for (int i = 1; i <= 5; i++) {
            Genome genome = allGenomes.get(i - 1);
            assert population.contains(genome) == false : "Genomes 1-5 should not have survived";
        }

        for (int i = 6; i <= 10; i++) {
            Genome genome = allGenomes.get(i - 1);
            assert population.contains(genome) == true : "Genomes 6-10 should have survived";
        }
    }

    private List<Genome> createPopulation() {
        int totalNumberOfGenomes = 10;
        List<Genome> allGenomes = new ArrayList<>(totalNumberOfGenomes);

        Genome genome1 = mock(Genome.class);
        when(genome1.getFitness()).thenReturn(1000);
        allGenomes.add(genome1);

        Genome genome2 = mock(Genome.class);
        when(genome2.getFitness()).thenReturn(2000);
        allGenomes.add(genome2);

        Genome genome3 = mock(Genome.class);
        when(genome3.getFitness()).thenReturn(3000);
        allGenomes.add(genome3);

        Genome genome4 = mock(Genome.class);
        when(genome4.getFitness()).thenReturn(4000);
        allGenomes.add(genome4);

        Genome genome5 = mock(Genome.class);
        when(genome5.getFitness()).thenReturn(5000);
        allGenomes.add(genome5);

        Genome genome6 = mock(Genome.class);
        when(genome6.getFitness()).thenReturn(6000);
        allGenomes.add(genome6);

        Genome genome7 = mock(Genome.class);
        when(genome7.getFitness()).thenReturn(7000);
        allGenomes.add(genome7);

        Genome genome8 = mock(Genome.class);
        when(genome8.getFitness()).thenReturn(8000);
        allGenomes.add(genome8);

        Genome genome9 = mock(Genome.class);
        when(genome9.getFitness()).thenReturn(9000);
        allGenomes.add(genome9);

        Genome genome10 = mock(Genome.class);
        when(genome10.getFitness()).thenReturn(10000);
        allGenomes.add(genome10);

        for (int i = 0; i < totalNumberOfGenomes; i++) {
            for (int j = 0; j < totalNumberOfGenomes; j++) {
                makeGenomeMockComparable(allGenomes.get(i), allGenomes.get(j));
            }
        }
        return allGenomes;
    }

}

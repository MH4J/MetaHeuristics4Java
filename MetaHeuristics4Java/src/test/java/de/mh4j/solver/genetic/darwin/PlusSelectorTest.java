package de.mh4j.solver.genetic.darwin;

import static de.mh4j.testAPI.Assert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.testng.annotations.Test;

import de.mh4j.solver.genetic.Genome;
import de.mh4j.solver.genetic.genepool.GenePool;

public class PlusSelectorTest {

    @Test
    public void testSelect() {
        Darwin<Genome> selector = new PlusSelector<>();
        Genome genome1 = mock(Genome.class);
        when(genome1.getCosts()).thenReturn(1000);
        Genome genome2 = mock(Genome.class);
        when(genome2.getCosts()).thenReturn(2000);
        Genome genome3 = mock(Genome.class);
        when(genome3.getCosts()).thenReturn(3000);
        Genome genome4 = mock(Genome.class);
        when(genome4.getCosts()).thenReturn(4000);
        Genome genome5 = mock(Genome.class);
        when(genome5.getCosts()).thenReturn(5000);
        Genome genome6 = mock(Genome.class);
        when(genome6.getCosts()).thenReturn(6000);
        Genome genome7 = mock(Genome.class);
        when(genome7.getCosts()).thenReturn(7000);
        Genome genome8 = mock(Genome.class);
        when(genome8.getCosts()).thenReturn(8000);
        Genome genome9 = mock(Genome.class);
        when(genome9.getCosts()).thenReturn(9000);
        Genome genome10 = mock(Genome.class);
        when(genome10.getCosts()).thenReturn(10000);

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

        selector.select(population, 5);

        assert population.size() == 5 : "New population should have whened size: 5";
        assertThat("Genomes 6-10 should not survived", population,
                not(hasItems(genome6, genome7, genome8, genome9, genome10)));
    }

}

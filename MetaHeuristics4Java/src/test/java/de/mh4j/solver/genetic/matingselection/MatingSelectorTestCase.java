package de.mh4j.solver.genetic.matingselection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collection;

import de.mh4j.solver.genetic.Couple;
import de.mh4j.solver.genetic.Genome;
import de.mh4j.solver.genetic.genepool.GenePool;

abstract class MatingSelectorTestCase {

    protected GenePool<Genome> genePool;
    protected Genome[] genomes;

    public void setUp() {
        Genome genome1 = mock(Genome.class);
        when(genome1.getFitness()).thenReturn(1000);
        Genome genome2 = mock(Genome.class);
        when(genome2.getFitness()).thenReturn(2000);
        Genome genome3 = mock(Genome.class);
        when(genome3.getFitness()).thenReturn(3000);
        Genome genome4 = mock(Genome.class);
        when(genome4.getFitness()).thenReturn(4000);
        Genome genome5 = mock(Genome.class);
        when(genome5.getFitness()).thenReturn(5000);
        Genome genome6 = mock(Genome.class);
        when(genome6.getFitness()).thenReturn(6000);
        Genome genome7 = mock(Genome.class);
        when(genome7.getFitness()).thenReturn(7000);
        Genome genome8 = mock(Genome.class);
        when(genome8.getFitness()).thenReturn(8000);
        Genome genome9 = mock(Genome.class);
        when(genome9.getFitness()).thenReturn(9000);
        Genome genome10 = mock(Genome.class);
        when(genome10.getFitness()).thenReturn(10000);

        genomes = new Genome[] { genome1, genome2, genome3, genome4, genome5, //
                genome6, genome7, genome8, genome9, genome10 };

        genePool = new GenePool<>();
        addAllGenesToGenePool(genePool);
    }

    protected void addAllGenesToGenePool(GenePool<Genome> genePool) {
        for (Genome genome : genomes) {
            genePool.addGenome(genome);
        }
    }

    protected void assertIsValidSelection(Collection<Couple> parents) {
        for (Couple couple : parents) {
            assert couple != null;
            assert couple.getParent1() != null;
            assert couple.getParent2() != null;

            /*
             * no incest unless it is the absolutely optimum (i.e. costs are
             * zero)
             */
            if (couple.getParent1().getCosts() != 0) {
                assert couple.getParent1().equals(couple.getParent2()) == false;
                assert couple.getParent1() != couple.getParent2();
            }
        }
    }
}

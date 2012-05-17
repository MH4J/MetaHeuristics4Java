package de.mh4j.solver.genetic.genepool;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertEquals;

import java.util.Arrays;
import java.util.Iterator;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.mh4j.solver.genetic.AbstractGenomeTest;
import de.mh4j.solver.genetic.Genome;

public class GenePoolTest extends AbstractGenomeTest {
    private GenePool<Genome> genePool;

    private Genome genome1;
    private Genome genome2;
    private Genome genome3;

    private final int genome1Fitness = 5;
    private final int genome2Fitness = 14;
    private final int genome3Fitness = 30;

    @BeforeMethod
    public void setUp() {
        genome1 = mock(Genome.class);
        genome2 = mock(Genome.class);
        genome3 = mock(Genome.class);

        when(genome1.getFitness()).thenReturn(genome1Fitness);
        when(genome2.getFitness()).thenReturn(genome2Fitness);
        when(genome3.getFitness()).thenReturn(genome3Fitness);

        // create comparable behavior
        for (Genome genome : Arrays.asList(genome1, genome2, genome3)) {
            for (Genome otherGenome : Arrays.asList(genome1, genome2, genome3)) {
                makeGenomeMockComparable(genome, otherGenome);
            }
        }

        genePool = new GenePool<>();
        genePool.addGenome(genome1);
        genePool.addGenome(genome2);
        genePool.addGenome(genome3);
    }

    @Test
    public void testSize_WhenInitialized_ThenSizeAndCurrentGenerationAndFitnessSumIsZero() throws Exception {
        GenePool<Genome> genePool = new GenePool<>();

        assert genePool.getSize() == 0 : "Fresh genepool should be empty";
        assert genePool.getCurrentGeneration() == 0 : "Fresh genepool should start at generation 0";
        assert genePool.getFitnessSum() == 0 : "Fresh genepool is empty and should therefore return a fitness sum of 0";
    }

    @Test
    public void testAddGenome_WithNewGenome() throws Exception {
        int currentSize = genePool.getSize();
        int currentGeneration = genePool.getCurrentGeneration();
        long oldFitnessSum = genePool.getFitnessSum();

        Genome newGenome = mock(Genome.class);
        int newGenomeFitness = 42;
        when(newGenome.compareTo(newGenome)).thenReturn(Genome.EQUAL);
        when(newGenome.getFitness()).thenReturn(newGenomeFitness);

        genePool.addGenome(newGenome);

        assert genePool.contains(newGenome) : "Genepool should contain the added genome";
        assert genePool.getSize() == currentSize + 1 : "A newly added genome should increase the size of the genepool";
        assert genePool.getFitnessSum() == oldFitnessSum + newGenomeFitness : "Adding a new genome to the genepool should increase the fitness sum";

        verify(newGenome).setBirthGeneration(currentGeneration);
    }

    @Test
    public void testAddGenome_WhenNewGenomeHasSameFitnessAsExistingOne_ThenAddIt() throws Exception {
        Genome sameFitnessGenome = mock(Genome.class);
        when(sameFitnessGenome.getFitness()).thenReturn(genome1Fitness);
        makeGenomeMockComparable(sameFitnessGenome, genome1);
        makeGenomeMockComparable(sameFitnessGenome, genome2);
        makeGenomeMockComparable(sameFitnessGenome, genome3);

        genePool.addGenome(sameFitnessGenome);
        assert genePool.contains(sameFitnessGenome);
    }

    @Test
    public void testReportDeadGenome_WhenReportedAndGenomeIsNotAChild_UpdateFitnessSum() throws Exception {
        long oldFitnessSum = genePool.getFitnessSum();

        // kill an older genome from a past generation
        Genome olderGenome = mock(Genome.class);
        int fitnessOfOlderGenome = 10;
        when(olderGenome.getFitness()).thenReturn(fitnessOfOlderGenome);
        when(olderGenome.getBirthGeneration()).thenReturn(genePool.getCurrentGeneration() - 1);

        genePool.reportDeadGenome(olderGenome);

        assert genePool.getFitnessSum() == oldFitnessSum - fitnessOfOlderGenome : "If a genome has been reported dead the overall fitness sum should decrease by its fitness";
        assert genePool.getNumberOfDeadChildren() == 0;

        // now kill the genome from current generation (child)
        oldFitnessSum = genePool.getFitnessSum();

        Genome genomeFromCurrentGeneration = mock(Genome.class);
        int fitnessOfGenomeFromCurrentGeneration = 8;
        when(genomeFromCurrentGeneration.getFitness()).thenReturn(fitnessOfGenomeFromCurrentGeneration);
        when(genomeFromCurrentGeneration.getBirthGeneration()).thenReturn(genePool.getCurrentGeneration());

        genePool.reportDeadGenome(genomeFromCurrentGeneration);

        assert genePool.getFitnessSum() == oldFitnessSum - fitnessOfGenomeFromCurrentGeneration : "If a genome has been reported dead the overall fitness sum should decrease by its fitness";
        assert genePool.getNumberOfDeadChildren() == 1;
    }

    @Test
    public void testIterator_WhenIterator_ThenSortedAscending() {
        long lastFitness = Long.MIN_VALUE;
        for (Genome genome : genePool) {
            assert genome.getFitness() >= lastFitness;
            lastFitness = genome.getFitness();
        }
    }

    @Test
    public void testDescendingIterator_WhenDescendingIterator_ThenSortedDescending() throws Exception {
        long lastFitness = Long.MAX_VALUE;
        Iterator<Genome> iterator = genePool.descendingIterator();
        while (iterator.hasNext()) {
            Genome genome = iterator.next();
            assert genome.getFitness() <= lastFitness;
            lastFitness = genome.getFitness();
        }
    }

    @Test
    public void testStartNewGeneration_WhenNewGeneration_ThenIncreaseCurrentGeneationAndResetNumberOfDeadChildren()
            throws Exception {
        int currentGeneration = genePool.getCurrentGeneration();

        when(genome1.getBirthGeneration()).thenReturn(currentGeneration);

        assert genePool.getNumberOfDeadChildren() == 0;
        genePool.reportDeadGenome(genome1);
        assert genePool.getNumberOfDeadChildren() == 1;

        genePool.startNewGeneration();

        assert genePool.getCurrentGeneration() == currentGeneration + 1;
        assert genePool.getNumberOfDeadChildren() == 0;
    }

    @Test
    public void testToArray() throws Exception {
        Genome[] genomes = genePool.toArray();
        assert genomes.length == 3;
        assert Arrays.equals(genomes, new Genome[] { genome1, genome2, genome3 });
    }

    @Test
    public void testGetFittestGenome() throws Exception {
        assert genePool.getFittestGenome() == genome3;
    }

    @Test
    public void testGetFittestGenome_WhenBetterGenomeIsAdded_UpdateBestGenome() throws Exception {
        Genome bestGenome = mock(Genome.class);
        when(bestGenome.getFitness()).thenReturn(Integer.MAX_VALUE);
        makeGenomeMockComparable(bestGenome, genome1);
        makeGenomeMockComparable(bestGenome, genome2);
        makeGenomeMockComparable(bestGenome, genome3);

        genePool.addGenome(bestGenome);
        assert genePool.getFittestGenome() == bestGenome;
    }

    @Test
    public void testGetFittestGenome_WhenBestGenomeIsDead_GetBestGenomeStillReturnsIt() throws Exception {
        genePool.reportDeadGenome(genome3);
        assert genePool.getFittestGenome() == genome3;
    }

    @Test
    public void testCopyConstructor() {
        GenePool<Genome> copiedGenepool = new GenePool<>(genePool);

        assert copiedGenepool != genePool : "Copy of a gene pool should be a new GenePool instance";
        assert copiedGenepool.getSize() == genePool.getSize() : "Copy of gene pool should have the same size as the original gene pool";
        assert copiedGenepool.getFitnessSum() == genePool.getFitnessSum();
        assert copiedGenepool.getNumberOfDeadChildren() == genePool.getNumberOfDeadChildren();
        assert copiedGenepool.getFittestGenome() == genePool.getFittestGenome();
        assert copiedGenepool.getSuccessRate() == genePool.getSuccessRate();

        for (Genome genome : genePool) {
            assert copiedGenepool.contains(genome) : "Copy of gene pool should contain all genomes";
        }
    }

    @Test
    public void testGetSuccessRate() {
        genePool.reportDeadGenome(genome1);
        // two out of three have survived
        double successRate = 2.0 / 3.0;
        genePool.startNewGeneration();

        assertEquals(successRate, genePool.getSuccessRate(), 0.0001);
    }

}

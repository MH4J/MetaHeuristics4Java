package de.mh4j.solver.genetic.matingselection;

import java.util.Collection;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.mh4j.solver.genetic.Couple;
import de.mh4j.solver.genetic.Genome;
import de.mh4j.solver.genetic.genepool.GenePool;

public class RouletteMatingSelectorTest extends MatingSelectorTestCase {

    private final int numberOfPairs = 4;

    @Override
    @BeforeMethod
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testSelect() throws Exception {
        MatingSelector<Genome> selector = new RouletteMatingSelector<>();
        Collection<Couple> parents = selector.select(numberOfPairs, genePool);

        assert parents.size() == numberOfPairs;
        assertIsValidSelection(parents);
    }

    @Test
    public void testSelect_WhenFitnessSumIsZero_ThenThrowNothing() {
        MatingSelector<Genome> selector = new RouletteMatingSelector<>();
        GenePool<Genome> zeroSumGenePool = new GenePool<>();
        addAllGenesToGenePool(zeroSumGenePool);

        Collection<Couple> parents = selector.select(numberOfPairs, zeroSumGenePool);

        assert parents.size() == numberOfPairs;
        assertIsValidSelection(parents);
    }
}

package de.mh4j.solver.genetic.matingselection;

import java.util.Collection;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.mh4j.solver.genetic.Couple;
import de.mh4j.solver.genetic.Genome;

public class RankingSelectorTest extends MatingSelectorTestCase {

    @Override
    @BeforeMethod
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testSelect() throws Exception {
        RankingSelector<Genome> selector = new RankingSelector<>();
        int numberOfPairs = 4;
        Collection<Couple> parents = selector.select(numberOfPairs, genePool);

        assert parents.size() == numberOfPairs;
        assertIsValidSelection(parents);
    }

}

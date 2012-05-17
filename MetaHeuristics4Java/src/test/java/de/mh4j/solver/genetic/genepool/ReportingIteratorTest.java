package de.mh4j.solver.genetic.genepool;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Iterator;

import org.mockito.InOrder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.mh4j.solver.genetic.Genome;

public class ReportingIteratorTest {
    private ReportingIterator<Genome> cut;

    private Iterator<Genome> decoree;
    private GenePool<Genome> genePool;

    @BeforeMethod
    @SuppressWarnings("unchecked")
    public void setUp() {
        decoree = mock(Iterator.class);
        genePool = mock(GenePool.class);

        cut = new ReportingIterator<>(decoree, genePool);
    }

    @Test
    public void testHasNext_WhenDecoreeHasNext_ThenReturnTrue() {
        when(decoree.hasNext()).thenReturn(true);
        assert cut.hasNext() == true;
    }

    @Test
    public void testHasNext_WhenDecoreeHasNoNext_ThenReturnFalse() {
        when(decoree.hasNext()).thenReturn(false);
        assert cut.hasNext() == false;
    }

    @Test
    public void testNext() {
        Genome next = mock(Genome.class);
        when(decoree.next()).thenReturn(next);
        assert cut.next() == next;
    }

    @Test
    public void testRemove() {
        Genome dead = mock(Genome.class);
        when(decoree.next()).thenReturn(dead);
        InOrder inOrder = inOrder(decoree, genePool);

        cut.next();
        cut.remove();

        inOrder.verify(decoree).remove();
        inOrder.verify(genePool).reportDeadGenome(dead);
    }

}

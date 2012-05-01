package de.mh4j.solver;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.testng.annotations.Test;

import de.mh4j.solver.termination.TerminationCondition;

public class AbstractSolverTest {

    @Test
    public void testCreateWithSeed() {
        long seed = 12345;
        AbstractSolver<?> solver1 = new AbstractSolverMock(seed);
        double d1 = solver1.randomizer.nextDouble();
        int i1 = solver1.randomizer.nextInt();
        float f1 = solver1.randomizer.nextFloat();
        boolean b1 = solver1.randomizer.nextBoolean();

        AbstractSolver<?> solver2 = new AbstractSolverMock(seed);
        double d2 = solver2.randomizer.nextDouble();
        int i2 = solver2.randomizer.nextInt();
        float f2 = solver2.randomizer.nextFloat();
        boolean b2 = solver2.randomizer.nextBoolean();

        assert solver1.seed == seed : "Solver seed should be the same as specified in the constructor";
        assert solver2.seed == seed : "Solver seed should be the same as specified in the constructor";

        assert d1 == d2 : "Solver with the same seed should create the same random values";
        assert i1 == i2 : "Solver with the same seed should create the same random values";
        assert f1 == f2 : "Solver with the same seed should create the same random values";
        assert b1 == b2 : "Solver with the same seed should create the same random values";
    }

    @Test
    public void testCreateWithoutSeed() {
        long before = System.currentTimeMillis();
        AbstractSolver<?> solver = new AbstractSolverMock();
        long after = System.currentTimeMillis();

        assert before <= solver.seed : "Random solver seed must be greater or equal to older system time";
        assert after >= solver.seed : "Random solver seed must be lower or equal to newer system time";
    }

    @Test
    public void testSolverRun() {
        AbstractSolver<Object> solver = spy(new AbstractSolverMock());

        TerminationCondition terminationCondition = mock(TerminationCondition.class);
        when(terminationCondition.shouldTerminate()).thenReturn(true);
        solver.addTerminationCondition(terminationCondition);

        @SuppressWarnings("unchecked")
        SolverStateListener<Object> stateListener = mock(SolverStateListener.class);
        solver.addStateListener(stateListener);

        solver.run();

        verify(solver).step();
        verify(solver).hasFinished();
        verify(stateListener).solverHasFinished(solver);
    }

    @Test
    public void testStateListenerisNotifiedAfterReset() {
        AbstractSolver<Object> solver = new AbstractSolverMock();

        @SuppressWarnings("unchecked")
        SolverStateListener<Object> stateListener = mock(SolverStateListener.class);

        solver.addStateListener(stateListener);
        solver.reset();

        verify(stateListener).solverHasBeenRestarted(solver);
    }

    private class AbstractSolverMock extends AbstractSolver<Object> {

        public AbstractSolverMock() {
            super();
        }

        public AbstractSolverMock(long seed) {
            super(seed);
        }

        @Override
        public AbstractSolverTest getCurrentSolution() {
            return null;
        }

        @Override
        protected void doInitialize() {
        }

        @Override
        protected void doStep() {
        }

    }
}

package de.mh4j.solver;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.testng.annotations.Test;

import de.mh4j.solver.termination.TerminationCondition;

public class AbstractSolverTest {

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

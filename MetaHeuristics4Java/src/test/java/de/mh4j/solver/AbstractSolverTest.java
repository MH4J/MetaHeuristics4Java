package de.mh4j.solver;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.testng.annotations.Test;

import ch.qos.logback.classic.Level;
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

    @Test
    public void testSetLogLevel() {
        AbstractSolver<Object> solver = new AbstractSolverMock();

        solver.setLogLevel(Level.DEBUG);
        assert solver.log.getLevel() == Level.DEBUG;

        solver.setLogLevel(Level.INFO);
        assert solver.log.getLevel() == Level.INFO;

        solver.setLogLevel(Level.WARN);
        assert solver.log.getLevel() == Level.WARN;

        solver.setLogLevel(Level.ERROR);
        assert solver.log.getLevel() == Level.ERROR;
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

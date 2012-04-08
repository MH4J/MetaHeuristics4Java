package de.mh4j.concurrency;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.testng.annotations.Test;

import de.mh4j.solver.AbstractSolver;

public class SolverThreadTest {

    @Test
    public void testInterrupt() {
        AbstractSolver<?> solverMock = mock(AbstractSolver.class);
        Thread solverThread = new SolverThread(solverMock);

        solverThread.interrupt();
        verify(solverMock).interrupt();
    }
}

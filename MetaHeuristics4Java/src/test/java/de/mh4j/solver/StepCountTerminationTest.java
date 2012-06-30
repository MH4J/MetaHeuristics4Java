package de.mh4j.solver;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.testng.annotations.Test;

import de.mh4j.solver.termination.StepCountTermination;
import de.mh4j.solver.termination.TerminationCondition;

public class StepCountTerminationTest {

    @Test
    @SuppressWarnings("unchecked")
    public void testShouldTerminate() {
        int maxStepCount = 100;
        Solver<Object> solver = mock(Solver.class);
        TerminationCondition terminator = new StepCountTermination(solver, maxStepCount);

        when(solver.getNumberOfSteps()).thenReturn(0);
        assert terminator.shouldTerminate() == false;

        when(solver.getNumberOfSteps()).thenReturn(99);
        assert terminator.shouldTerminate() == false;

        when(solver.getNumberOfSteps()).thenReturn(100);
        assert terminator.shouldTerminate() == true;

        when(solver.getNumberOfSteps()).thenReturn(1000);
        assert terminator.shouldTerminate() == true;
    }

}

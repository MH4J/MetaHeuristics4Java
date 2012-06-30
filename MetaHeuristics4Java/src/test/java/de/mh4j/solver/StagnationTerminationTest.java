package de.mh4j.solver;

import static org.mockito.Mockito.mock;

import org.testng.annotations.Test;

import de.mh4j.solver.termination.StagnationTermination;
import de.mh4j.solver.termination.TerminationCondition;

public class StagnationTerminationTest {

    @Test
    @SuppressWarnings("unchecked")
    public void testShouldTerminate() {
        int maxNrOfStagnatingSteps = 50;
        Solver<Solution<?>> solver = mock(Solver.class);
        TerminationCondition terminator = new StagnationTermination(solver, maxNrOfStagnatingSteps);

        assert terminator.shouldTerminate() == false;

        for (int i = 1; i < maxNrOfStagnatingSteps - 1; i++) {
            assert terminator.shouldTerminate() == false;
        }

        assert terminator.shouldTerminate() == true : "Terminator should terminate now because we asked it "
                + maxNrOfStagnatingSteps + " if we should terminate without any improvement in the associated solver";
    }
}

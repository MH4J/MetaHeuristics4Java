package de.mh4j.examples;

import de.mh4j.solver.Solution;
import de.mh4j.solver.Solver;
import de.mh4j.solver.SolverStateAdapter;

public class ExampleSolverStateListener<GenericSolutionType extends Solution<GenericSolutionType>> extends
        SolverStateAdapter<GenericSolutionType> {

    GenericSolutionType lastSolution = null;

    @Override
    public void solverHasStepped(Solver<GenericSolutionType> solver) {
        GenericSolutionType currentSolution = solver.getCurrentSolution();
        if (currentSolution.equals(lastSolution) == false) {
            System.out.print("New Solution: " + currentSolution);
            System.out.println(" (Costs " + currentSolution.getCosts() + ")");
            lastSolution = currentSolution;
        }
    }

    @Override
    public void solverHasFinished(Solver<GenericSolutionType> solver) {
        System.out.println("Terminated after " + solver.getNumberOfSteps() + " steps");
        System.out.println(solver.getCurrentSolution());
    }

}

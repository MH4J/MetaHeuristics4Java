/*
 * Copyright 2012   Friedrich Große, Paul Seiferth,
 *                  Sebastian Starroske, Yannik Stein
 *
 * This file is part of MetaHeuristics4Java.
 *
 * MetaHeuristics4Java is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MetaHeuristics4Java is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MetaHeuristics4Java. If not, see <http://www.gnu.org/licenses/>.
 */

package de.mh4j.solver.simulatedAnnealing;

import de.mh4j.solver.AbstractSolver;
import de.mh4j.solver.Solution;

/**
 * Local search algorithms move from solution to solution in the search space by
 * applying local changes, until the termination condition is reached. <br>
 * <br>
 * <b>Description:</b><br>
 * Every local search algorithm starts from an initial candidate solution and
 * then iteratively moves to a neighbor of that solution.<br>
 * If the neighbor is better than the current solution it becomes the new
 * candidate solution. If the neighbor was worse the current solution stays as
 * candidate. These steps are repeated until the termination condition has been
 * reached.<br>
 * <br>
 * The problem with pure local search algorithm is that they can easily run into
 * locally optimal solutions and may never return from there. This local-optima
 * problem may be cured by using restarts (repeated local search with different
 * initial conditions), or more advanced local search algorithms like
 * {@link AbstractSimulatedAnnealingSolver Simulated Annealing}.
 * 
 * @author Friedrich Große <friedrich.grosse@gmail.com>
 * 
 * @param <GenericSolutionType>
 *            The Type of the class that describes a candidate solution for the
 *            given optimization problem.
 * 
 * @see AbstractSimulatedAnnealingSolver
 */
public abstract class AbstractLocalSearchSolver<GenericSolutionType extends Solution<GenericSolutionType>> extends
        AbstractSolver<GenericSolutionType> {

    /**
     * The number of steps the algorithm could not find a better solution than
     * the best solution that has ever been found.
     */
    protected int situationHasNotImproved = 0;

    /**
     * The {@link Solution} that has been created by the last call to
     * {@link #doStep()}.
     */
    protected GenericSolutionType currentSolution;

    @Override
    public GenericSolutionType getCurrentSolution() {
        return currentSolution;
    }

    @Override
    protected void doInitialize() {
        situationHasNotImproved = 0;
        currentSolution = createInitialSolution();

        assert currentSolution != null : "The initially created solution can not be null.";

        log.debug("Initial solution with costs {} created: {}", currentSolution.getCosts(), currentSolution);
    }

    @Override
    protected void doStep() {
        GenericSolutionType neighbor = createRandomNeighbor();

        if (neighbor.isBetterThan(currentSolution)) {
            currentSolution = neighbor;
            situationHasNotImproved = 0;
            log.trace("Found a better neighbor. New costs are {}", neighbor.getCosts());
        }
        else {
            situationHasNotImproved++;
            log.trace("Neighbor is worse than the current configuration. Costs stay at {}", currentSolution.getCosts());
        }
    }

    /**
     * @return The initial tour from where the local search optimization
     *         algorithm starts.
     */
    protected abstract GenericSolutionType createInitialSolution();

    /**
     * @return A randomly created neighbor of the current solution.
     */
    protected abstract GenericSolutionType createRandomNeighbor();

}

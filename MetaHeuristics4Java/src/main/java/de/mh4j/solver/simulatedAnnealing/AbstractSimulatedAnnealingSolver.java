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

import de.mh4j.solver.Solution;

/**
 * 
 * Simulated annealing (SA) is a local search algorithm with the extension to
 * sometimes accept worse solutions. To find the global optima, they move from
 * solution to solution in the search space by applying local changes.<br>
 * <br>
 * Each SA algorithm keeps track of a temperature <i>T</i> which is gradually
 * decreased over time (usually in a {@link AbstractCoolingScheme cooling
 * scheme}). Worse solutions may be accepted in dependence of the absolute
 * difference to the current solution and this temperature.<br>
 * <br>
 * The dependency is such that the choice between the previous and current
 * solution is almost random when <i>T</i> is large, but increasingly selects
 * the better or "downhill" solution (for a minimization problem) as <i>T</i>
 * goes to zero. The allowance for "uphill" moves potentially saves the method
 * from becoming stuck at local optima.
 * 
 * @author Friedrich Große <friedrich.grosse@gmail.com>
 * 
 * @param <GenericSolutionType>
 *            The Type of the class that describes a candidate solution for the
 *            given optimization problem.
 * 
 * @see AbstractCoolingScheme
 * @see AbstractLocalSearchSolver
 */
public abstract class AbstractSimulatedAnnealingSolver<GenericSolutionType extends Solution<GenericSolutionType>>
        extends AbstractLocalSearchSolver<GenericSolutionType> {

    /**
     * The cooling scheme on which this simulated annealing instance is
     * operating on.
     */
    protected AbstractCoolingScheme<GenericSolutionType> coolingScheme;

    /**
     * The best solution that has been found since the first call to
     * {@link #doStep()}.<br>
     * <br>
     * <b>Note:</b> In contrast to
     * {@link AbstractLocalSearchSolver#currentSolution currentSolution} this
     * may be an older solution that has been found at some time in the past but
     * has been discarded because a worse solution has been accepted.
     */
    protected GenericSolutionType bestSolutionEverFound;

    /**
     * Creates a new simulated annealing solver with the given cooling scheme.
     * 
     * @param coolingScheme
     *            The scheme that is used to control the temperature and
     *            acceptance probability of worse solution candidates.
     */
    public AbstractSimulatedAnnealingSolver(AbstractCoolingScheme<GenericSolutionType> coolingScheme) {
        this.coolingScheme = coolingScheme;
    }

    @Override
    protected void doInitialize() {
        super.doInitialize();
        bestSolutionEverFound = currentSolution;
    }

    @Override
    protected void doStep() {
        GenericSolutionType neighborSolution = createRandomNeighbor();

        if (neighborSolution.isBetterThan(currentSolution)) {
            handleBetterNeighbor(neighborSolution);
        }
        else {
            handleWorseNeighbor(neighborSolution);
        }

        coolingScheme.updateTemperature();
    }

    private void handleBetterNeighbor(GenericSolutionType neighborSolution) {
        currentSolution = neighborSolution;

        if (neighborSolution.isBetterThan(bestSolutionEverFound)) {
            situationHasNotImproved = 0;
            bestSolutionEverFound = neighborSolution;
        }
        else {
            situationHasNotImproved++;
        }

        log.trace("Found a better solution. New costs are {}", neighborSolution.getCosts());
    }

    private void handleWorseNeighbor(GenericSolutionType neighborSolution) {
        double acceptanceProbability = coolingScheme.getAcceptanceProbability(neighborSolution, currentSolution);
        if (randomizer.nextDouble() < acceptanceProbability) {
            currentSolution = neighborSolution;
            log.trace("Took worse solution because of the cooling scheme acceptance probability. New costs are {}",
                    neighborSolution.getCosts());
        }
        else {
            log.trace("Neighbor is worse than the current solution. Costs stay at {}", currentSolution.getCosts());
        }
        situationHasNotImproved++;
    }

    /**
     * @return the cooling scheme on which this simulated annealing instance is
     *         operating on.
     */
    public AbstractCoolingScheme<GenericSolutionType> getCoolingScheme() {
        return coolingScheme;
    }
}

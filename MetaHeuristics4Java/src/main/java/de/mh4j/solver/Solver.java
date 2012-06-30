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

package de.mh4j.solver;

/**
 * A Solver is the representation of an algorithm that can find or approximate
 * solutions to a given optimization problem.
 * 
 * TODO write more about the possible implementation and utilization of a solver
 * 
 * @param <GenericSolutionType>
 *            the actual Type of the solution class.
 * 
 * @author Friedrich Große <friedrich.grosse@gmail.com>
 */
public interface Solver<GenericSolutionType> extends Runnable {

    /**
     * Performs a single step in the algorithm of this solver. The following
     * actions will be taken:
     * <ol>
     * <li>The solver will be {@link #initialize() initialized} if it has not
     * yet been initialized by a previous call to this method</li>
     * <li>The actual algorithm step will be performed</li>
     * <li>The step counter will be advanced by 1</li>
     * <li>All {@link SolverStateListener SolverStateListeners} will be notified
     * by a call to {@link SolverStateListener#solverStateHasChanged()}</li>
     */
    void step();

    /**
     * Runs the whole algorithm until the termination condition is reached.
     */
    @Override
    void run();

    /**
     * Resets this solver. The current solution will be discarded and the solver
     * will be initialized again. All {@link SolverStateListener
     * SolverStateListeners} will be notified.
     */
    void reset();

    /**
     * Returns how often a single step in the solvers algorithm has been
     * executed.
     */
    int getNumberOfSteps();

    /**
     * Returns the current solution that has been created after the last call to
     * {@link #step()}.<br>
     * <br>
     * <b>Note:</b><br>
     * This interim solution may not be the best solution ever found and can
     * even be <code>null</code> if {@link #step()} has never been called.
     */
    GenericSolutionType getCurrentSolution();

    /**
     * Returns <code>true</code> as soon as the solver has finished optimizing
     * the problem. Otherwise <code>false</code> is returned.
     */
    boolean hasFinished();

    /**
     * Adds a {@linkplain SolverStateListener} to this solver. The listener will
     * be informed about important events that may occur on this solver.
     */
    void addStateListener(SolverStateListener<GenericSolutionType> listener);

    /**
     * Removes a {@linkplain SolverStateListener} from this solver. The listener
     * will no longer be informed about important events that may occur on this
     * solver.
     */
    void removeStateListener(SolverStateListener<GenericSolutionType> listener);

}

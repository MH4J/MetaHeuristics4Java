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
 * A Solution represents a valid result of the given optimization problem. Every
 * Solution must be comparable to other Solutions and should be able to return a
 * numeric indicator of its costs or quality (fineness).
 * 
 * @author Friedrich Große <friedrich.grosse@gmail.com>
 * 
 * @param <SolutionImplementationType>
 *            The Actual type (class) of this solution. It is used to make this
 *            solution comparable to other solutions of the
 *            <code>SolutionImplementationType</code> in the
 *            {@linkplain #isBetterThan(Solution)} method.
 */
public interface Solution<SolutionImplementationType extends Solution<?>> {

    /**
     * Returns a numeric value that represents the costs of this solution. If
     * the optimization problem is, to maximize the costs of a solution the
     * return value indicates the absolute quality of the solution (the
     * fitness).
     **/
    int getCosts();

    /**
     * Compares this solution to another solution.
     * 
     * @return <code>true</code> if this solution instance is better than the
     *         other solution<br>
     *         <code>false</code> if this solution is of worse or equal quality.
     **/
    boolean isBetterThan(SolutionImplementationType otherSolution);

}

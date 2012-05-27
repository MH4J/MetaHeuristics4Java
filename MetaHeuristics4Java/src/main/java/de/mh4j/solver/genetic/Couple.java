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

package de.mh4j.solver.genetic;


public class Couple {
	private final Genome parent1;
	private final Genome parent2;

	public Couple(Genome parent1, Genome parent2) {
		this.parent1 = parent1;
		this.parent2 = parent2;
	}

	public Genome getParent1() {
		return parent1;
	}

	public Genome getParent2() {
		return parent2;
	}

	@Override
	public String toString() {
		return "<" + parent1.toString() + ", " + parent2.toString() + ">";
	}
}

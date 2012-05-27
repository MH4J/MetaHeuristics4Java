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

package de.mh4j.util;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GaussianDistribution extends Random {
	private static final long serialVersionUID = 4881546294540742726L;

	private final Logger log = LoggerFactory.getLogger(getClass());

	private final Random random;

	private double sigma = 1;

	public GaussianDistribution() {
		this(System.currentTimeMillis());
	}

	public GaussianDistribution(long seed) {
		log.debug("Seed is {}", seed);
		random = new Random(seed);
	}

	@Override
	public double nextGaussian() {
		return sigma * random.nextGaussian();
	}

	public void multStandardDeviationWith(double factor) {
		sigma *= factor;
		log.debug("Standard Deviation is {}", sigma);
	}

	public double getStandardDeviation() {
		return sigma;
	}
}

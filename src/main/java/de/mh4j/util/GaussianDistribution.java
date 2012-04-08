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

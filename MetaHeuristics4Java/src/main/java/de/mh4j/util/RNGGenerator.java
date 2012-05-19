package de.mh4j.util;

import java.util.Random;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

/**
 * This abstract class manages the creation of Pseudo Random Number Generators.
 * It will create the seeds for all {@link Random} instances out of one master
 * seed.
 * 
 * @author Friedrich Große
 */
public abstract class RNGGenerator {
    private final static Logger log = (Logger) LoggerFactory.getLogger(RNGGenerator.class);

    private static Random seedGenerator;

    public static void setMasterSeed(long masterSeed) {
        seedGenerator = new Random(masterSeed);
        log.info("Initialized random number generator with master seed {}", masterSeed);
    }

    public static Random createRandomNumberGenerator() {
        if (seedGenerator == null) {
            setMasterSeed(System.currentTimeMillis());
        }
        long seed = seedGenerator.nextLong();
        return new Random(seed);
    }

}

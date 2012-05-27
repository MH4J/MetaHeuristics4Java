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

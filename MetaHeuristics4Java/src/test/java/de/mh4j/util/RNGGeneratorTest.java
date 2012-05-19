package de.mh4j.util;

import java.util.Random;

import org.testng.annotations.Test;

public class RNGGeneratorTest {

    @Test
    public void testCreateRandomNumberGenerator() {
        Random randomizer1 = RNGGenerator.createRandomNumberGenerator();
        Random randomizer2 = RNGGenerator.createRandomNumberGenerator();

        assert randomizer1 != null;
        assert randomizer2 != null;
        assert randomizer1 != randomizer2;
    }

    @Test
    public void testSetMasterSeed() {
        RNGGenerator.setMasterSeed(1234567890);

        Random randomizer1 = RNGGenerator.createRandomNumberGenerator();
        Random randomizer2 = RNGGenerator.createRandomNumberGenerator();

        assert randomizer1.nextLong() == -5858895568764324068l : "RNGs created with this seed should allways return this value";
        assert randomizer2.nextInt() == 325488510 : "RNGs created with this seed should allways return this value";
    }
}

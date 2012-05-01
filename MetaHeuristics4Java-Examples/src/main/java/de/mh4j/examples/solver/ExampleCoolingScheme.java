package de.mh4j.examples.solver;

import de.mh4j.examples.Sorting;
import de.mh4j.solver.simulatedAnnealing.AbstractCoolingScheme;

public class ExampleCoolingScheme extends AbstractCoolingScheme<Sorting> {

    @Override
    protected double getInitialTemperature() {
        return 1000;
    }

    @Override
    protected int getInitialEpochLength() {
        return 100;
    }

    @Override
    public void decreaseTemperature() {
        currentTemperature = currentTemperature * 0.8;
    }
}

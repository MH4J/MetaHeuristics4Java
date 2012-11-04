package de.mh4j.examples.maxknapsack.solver;

import de.mh4j.examples.maxknapsack.model.Knapsack;
import de.mh4j.solver.simulatedAnnealing.AbstractCoolingScheme;

public class KnapsackCoolingScheme extends AbstractCoolingScheme<Knapsack> {

    @Override
    protected double getInitialTemperature() {
        return 100;
    }

    @Override
    protected int getInitialEpochLength() {
        return 50;
    }

    @Override
    protected double decreaseTemperature(double currentTemperature) {
        return currentTemperature * 0.8;
    }

}

package de.mh4j.examples.qap.solver;

import de.mh4j.examples.qap.model.Qap;
import de.mh4j.solver.simulatedAnnealing.AbstractCoolingScheme;

public class QapCoolingScheme extends AbstractCoolingScheme<Qap> {

    @Override
    protected double getInitialTemperature() {
        return 90;
    }

    @Override
    protected int getInitialEpochLength() {
        return 50;
    }

    @Override
    protected double decreaseTemperature(double currentTemperature) {
        return currentTemperature * 0.5;
    }

}

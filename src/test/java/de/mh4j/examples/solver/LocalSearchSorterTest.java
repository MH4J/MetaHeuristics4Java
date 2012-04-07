package de.mh4j.examples.solver;

import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import de.mh4j.examples.Sorting;

public class LocalSearchSorterTest {

    @BeforeClass
    public static void setup() {
        Logger log = (Logger) LoggerFactory.getLogger(LocalSearchSorter.class);
        log.setLevel(Level.WARN);
    }

    @Test
    public void testCreateInitialSolution() {
        LocalSearchSorter sorter = new LocalSearchSorter(10);
        sorter.createInitialSolution();

        /*
         * if the solver could create any solution without throwing an exception
         * this test succeeded. (Sorting class handles its validity by itself)
         */
    }

    @Test
    public void testCreateRandomNeighbor() {
        LocalSearchSorter sorter = new LocalSearchSorter(10);
        // invoke step to initialize the solver
        sorter.step();

        Sorting initialSorting = sorter.getCurrentSolution();
        Sorting neighbor = sorter.createRandomNeighbor();

        assert initialSorting.equals(neighbor) == false;
    }
}

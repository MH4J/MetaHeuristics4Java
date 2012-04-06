package de.mh4j.examples.solver;

import org.testng.annotations.Test;

public class LocalSearchSorterTest {

    @Test
    public void testCreateInitialSolution() {
        LocalSearchSorter sorter = new LocalSearchSorter();
        sorter.createInitialSolution();

        /*
         * if the solver could create any solution without throwing an exception
         * this test succeeded. (Sorting handles its validity by itself)
         */
    }

}

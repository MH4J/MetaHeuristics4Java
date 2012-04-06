package de.mh4j.solver;

public interface Solution {

    /** TODO add javadoc **/
    int getCosts();

    /**
     * Compares this solution to another solution.
     * 
     * @return <code>true</code> if this solution instance is better than the
     *         other solution<br>
     *         <code>false</code> if this solution is worse than the other or
     *         both solutions are of the same quality.
     **/
    boolean isBetterThan(Solution otherSolution);

}

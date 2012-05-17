package de.mh4j.solver;

public interface Solution<SolutionImplementationType extends Solution<?>> {

    /** TODO add javadoc **/
    int getCosts();

    /**
     * Compares this solution to another solution.
     * 
     * @return <code>true</code> if this solution instance is better than the
     *         other solution<br>
     *         <code>false</code> if this solution is of worse or equal quality.
     **/
    boolean isBetterThan(SolutionImplementationType otherSolution);

}

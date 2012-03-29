package de.mh4j.solver;

public interface Solution {

	/** TODO add javadoc **/
	public int getCosts();
	
	/** TODO add javadoc **/
	public boolean isBetterThan(Solution otherSolution);
	
}

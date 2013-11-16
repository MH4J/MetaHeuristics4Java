package de.mh4j.examples.qap.model;

import java.util.ArrayList;
import java.util.List;

import de.mh4j.solver.Solution;

/**
 * 
 * The quadratic assignment problem (QAP) is one of fundamental combinatorial
 * optimization problems in the branch of optimization or operations research in
 * mathematics, from the category of the facilities-location problems.
 **/
public class Qap implements Solution<Qap> {

	public List<String> solution = new ArrayList<>();;
	public int costs = 0;

	public Qap(List<String> solutions) {
        for (String solution : solutions) {
            this.solution.add(solution);
        }

		calculateCosts(this.solution);
	}

	public Qap(Qap original) {
		this.solution = new ArrayList<>(original.solution);
		this.costs = original.costs;
	}

	/**
	 *  			__n__    __n__
	 *             \        \
	 *              \        \      Fij*Dp(i)p(j)  for minimizing or maximizing the costs
	 *              /        /                      where F are the facilities' flows and D the 
	 *             /____    /____                   the distances
	 *              i=1      j=1
	 * 
	 * 
	 * for example in case of 3 facilities(1,2,3) and 3 locations(A,B,C) this
	 * method would calculate: f12*dAB+f13*dAC+f21*dBA+f23*dBC+ f31*dCA+f32*dCB
	 * */

	public void calculateCosts(List<String> solution) {
		costs = 0;

		for (int i = 0; i < solution.size(); i++) {
			for (int j = 0; j < Facility.facilities.size(); j++) {
				if (solution.get(i).equals(Facility.facilities.get(j).name)) {
					int m = 0;
					for (int l = 0; l < solution.size(); l++) {
						for (int o = 0; o < Facility.facilities.get(j).facilitiesNames.size(); o++) {
							if (solution.get(l).equals(Facility.facilities.get(j).facilitiesNames.get(o))) {
								costs = costs
										+ Facility.facilities.get(j).facilitiesCosts.get(o)
										* Location.locations.get(i).distances.get(m);
								m++;
							}
						}
					}
				}
			}
		}
	}

	public int getCosts() {
		return costs;
	}

	@Override
	public boolean isBetterThan(Qap otherSolution) {
		return otherSolution.getCosts() < costs;
	}

}
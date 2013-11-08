package de.mh4j.examples.qap.model;

import java.util.List;
import java.util.ArrayList;

public class Facility {

	public final String name;

	List<Integer> facilitiesCosts = new ArrayList<Integer>();

	List<String> facilitiesNames = new ArrayList<String>();

	static List<Facility> facilities = new ArrayList<Facility>();

	public Facility(String name, List<Integer> costs,
			List<String> facilities_names) {

		this.name = name;
		for (int i = 0; i < costs.size(); i++) {

			this.facilitiesCosts.add(costs.get(i));

			this.facilitiesNames.add(facilities_names.get(i));

		}

		facilities.add(this);

	}

}

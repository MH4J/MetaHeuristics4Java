package de.mh4j.examples.qap.model;

import java.util.ArrayList;
import java.util.List;

public class Location {

	public final String locationName;

	List<Integer> distances = new ArrayList<Integer>();
	List<String> distancesNames = new ArrayList<String>();

	static List<Location> locations = new ArrayList<Location>();

	public Location(String locationName, List<Integer> distances,
			List<String> distancesNames) {

		this.locationName = locationName;

		for (int i = 0; i < distances.size(); i++) {

			this.distances.add(distances.get(i));

			this.distancesNames.add(distancesNames.get(i));

		}

		locations.add(this);
	}

}

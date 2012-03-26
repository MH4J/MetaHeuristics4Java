package de.mh4j.solver.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class Util {

	public static <T> List<T> reverse(List<T> originalList) {
		List<T> reversedList = new ArrayList<T>(originalList);
		Collections.reverse(reversedList);
		return reversedList;		
	}
	
	public static <T> T getRandomEntryFrom(List<T> list, Random randomizer) {
		int randomIndex = randomizer.nextInt(list.size());
		return list.get(randomIndex);
	}
}

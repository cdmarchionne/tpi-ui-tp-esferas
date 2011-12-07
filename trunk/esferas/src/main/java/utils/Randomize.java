package utils;

import java.util.Random;

public class Randomize {

	public static Integer randomInteger(Integer aStart, Integer aEnd) {
		Random aRandom = new Random();
		if (aStart > aEnd) {
			throw new IllegalArgumentException("Start cannot exceed End.");
		}
		// get the range, casting to long to avoid overflow problems
		Integer range = aEnd - aStart + 1;
		return (Integer) (aRandom.nextInt(range) + aStart);
	}

}

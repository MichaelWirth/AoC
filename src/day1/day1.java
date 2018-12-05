package day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class day1 {

	private static Integer frequency = 0;
	private static File file = new File("./src/day1/input.txt");

	private static ArrayList<Integer> pastFrequencies = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		pastFrequencies.add(0);

		Integer firstRepeat = null;

		while (firstRepeat == null) {

			BufferedReader reader = new BufferedReader(new FileReader(file));

			String line;

			Integer frequencyAfterOneLoop = null;
			while ((line = reader.readLine()) != null) {
				frequency += Integer.parseInt(line);

				if (firstRepeat == null && hasRepeated()) {
					firstRepeat = frequency;
				}
			}

			if (frequencyAfterOneLoop == null) {
				frequencyAfterOneLoop = frequency;
			}

			reader.close();
		}

		System.out.println(frequency + " - " + firstRepeat);
	}

	private static boolean hasRepeated() {

		if (pastFrequencies.contains(frequency)) {
			return true;
		}

		pastFrequencies.add(frequency);

		return false;
	}
}

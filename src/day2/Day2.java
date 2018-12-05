package day2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day2 {

	private static File file = new File("./src/day2/input.txt");

	private static ArrayList<String> idsWithTwoRepeats = new ArrayList<>();
	private static ArrayList<String> idsWithThreeRepeats = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;

		// read line by line
		while ((line = reader.readLine()) != null) {

			ArrayList<String> containedOnce = new ArrayList<>();
			ArrayList<String> containedTwice = new ArrayList<>();
			ArrayList<String> containedThrice = new ArrayList<>();
			ArrayList<String> containedTooOften = new ArrayList<>();

			for (int i = 0; i < line.length(); i++) {

				String c = line.substring(i, i + 1);

				// skip if already found too often
				if (containedTooOften.contains(c)) {
					continue;
				}

				// push to tooOften if found thrice
				if (containedThrice.contains(c)) {
					containedThrice.remove(c);
					containedTooOften.add(c);
					continue;
				}

				// push to thrice if found twice
				if (containedTwice.contains(c)) {
					containedTwice.remove(c);
					containedThrice.add(c);
					continue;
				}

				// push to twice if found once
				if (containedOnce.contains(c)) {
					containedOnce.remove(c);
					containedTwice.add(c);
					continue;
				}

				// push to once if nothing else matched
				containedOnce.add(c);
			}

			if (!containedTwice.isEmpty()) {
				idsWithTwoRepeats.add(line);
			}

			if (!containedThrice.isEmpty()) {
				idsWithThreeRepeats.add(line);
			}
		}

		reader.close();

		System.out.println(idsWithTwoRepeats.size() + " * " + idsWithThreeRepeats.size() + " = "
				+ idsWithTwoRepeats.size() * idsWithThreeRepeats.size());
	}
}

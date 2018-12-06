package day2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day2 {

	private static File file = new File("./src/day2/input.txt");
	private static ArrayList<String> idList = new ArrayList<>();

	private static ArrayList<String> idsWithTwoRepeats = new ArrayList<>();
	private static ArrayList<String> idsWithThreeRepeats = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		// put everything in the array
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		while ((line = reader.readLine()) != null) {
			idList.add(line);
		}
		reader.close();

		// test the array for part 1
		for (String id : idList) {

			ArrayList<String> containedOnce = new ArrayList<>();
			ArrayList<String> containedTwice = new ArrayList<>();
			ArrayList<String> containedThrice = new ArrayList<>();
			ArrayList<String> containedTooOften = new ArrayList<>();

			for (int i = 0; i < id.length(); i++) {

				String c = id.substring(i, i + 1);

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
				idsWithTwoRepeats.add(id);
			}

			if (!containedThrice.isEmpty()) {
				idsWithThreeRepeats.add(id);
			}
		}

		System.out.println("Part 1: " + idsWithTwoRepeats.size() + " * " + idsWithThreeRepeats.size() + " = "
				+ idsWithTwoRepeats.size() * idsWithThreeRepeats.size());

		partTwo();
	}

	private static void partTwo() {

		// loop the id to test
		for (int i = 0; i < idList.size(); i++) {

			String testId = idList.get(i);

			// loop the second id to test
			nextAgainstId: for (int j = i + 1; j < idList.size(); j++) {

				String againstId = idList.get(j);

				// test every char in them
				ArrayList<Integer> differences = new ArrayList<>();
				int pos;
				for (pos = 0; pos < testId.length(); pos++) {

					// if not equal increase differences
					if (!testId.substring(pos, pos + 1).equals(againstId.substring(pos, pos + 1))) {
						differences.add(pos);

						if (differences.size() > 1)
							continue nextAgainstId;
					}
				}

				// if differences is exactly 1 we found our ids
				if (differences.size() == 1) {
					String result = testId.substring(0, differences.get(0)) + testId.substring(differences.get(0) + 1);
					System.out.println("Part 2:\n" + testId + "\n" + againstId + "\n=>\n" + result);
				}
			}
		}
	}
}

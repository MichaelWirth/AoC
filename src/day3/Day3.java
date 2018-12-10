package day3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {

	private static File file = new File("./src/day3/input.txt");
	private static ArrayList<List<List<Claim>>> cloth = new ArrayList<>();
	private static Set<Claim> allClaims = new HashSet<>();

	public static void main(String[] args) throws IOException {

		// generate cloth array
		for (int i = 0; i < 1000; i++) {

			cloth.add(new ArrayList<>());

			for (int j = 0; j < 1000; j++) {
				cloth.get(i).add(new ArrayList<>());
			}
		}

		// put everything in the grid
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		while ((line = reader.readLine()) != null) {

			Claim claim = parseLine(line);

			for (int x = claim.startX; x < claim.startX + claim.sizeX; x++) {

				for (int y = claim.startY; y < claim.startY + claim.sizeY; y++) {

					allClaims.add(claim);
					cloth.get(x).get(y).add(claim);
				}
			}
		}
		reader.close();

		// check for squares with more than one claim
		int invalidSquares = squaresWithMoreThanOneClaim();

		System.out.println("Invalid Squares: " + invalidSquares);

		// Part two:
		System.out.println("Non colliding claims: " + findNonCollidingClaims());
	}

	private static Claim parseLine(String line) {

		// #1 @ 493,113: 12x14
		Pattern pattern = Pattern.compile("#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)");
		Matcher matcher = pattern.matcher(line);

		matcher.find();

		return new Claim(
				Integer.parseInt(matcher.group(1)),
				Integer.parseInt(matcher.group(2)),
				Integer.parseInt(matcher.group(3)),
				Integer.parseInt(matcher.group(4)),
				Integer.parseInt(matcher.group(5)));
	}

	private static int squaresWithMoreThanOneClaim() {

		int result = 0;

		for (int x = 0; x < cloth.size(); x++) {

			List<List<Claim>> yList = cloth.get(x);
			for (int y = 0; y < yList.size(); y++) {

				List<Claim> claimList = yList.get(y);
				if (claimList.size() > 1) {

					setCollidesTrue(claimList);
					result++;
				}
			}
		}

		return result;
	}

	private static void setCollidesTrue(List<Claim> claimList) {

		for (Claim claim : claimList) {

			claim.collidesWithOthers = true;
		}
	}

	private static List<Claim> findNonCollidingClaims() {

		List<Claim> nonCollidingList = new ArrayList<>();
		for (Claim claim : allClaims) {

			if (!claim.collidesWithOthers) {

				nonCollidingList.add(claim);
			}
		}

		return nonCollidingList;
	}
}

class Claim {
	Integer id;
	Integer startX;
	Integer startY;
	Integer sizeX;
	Integer sizeY;

	boolean collidesWithOthers = false;

	Claim(Integer id, Integer startX, Integer startY, Integer sizeX, Integer sizeY) {
		this.id = id;
		this.startX = startX;
		this.startY = startY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}

	@Override
	public String toString() {
		return id.toString();
	}
}

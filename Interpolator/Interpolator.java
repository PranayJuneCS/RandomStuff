import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;

public class Interpolator {

	static Scanner scanner = new Scanner(System.in);
	private static int mod = 0;
	private static int numPoints = 0;
	private static ArrayList<Point> points = new ArrayList<Point>();

	public static void main(String[] args) {
		hello();
		getMod();
		getPoints();
		interpolate();

	}

	public static void getMod() {
		System.out.println("Please enter a prime number as a mod to work under: ");
		while (mod == 0) {
			int input = scanner.nextInt();
			if (isPrime(input) && input > 1) {
				mod = input;
			}
			else {
				System.out.println("Make sure the mod is > 1 and prime. ");
			}
		}
		System.out.println("Your mod is: " + mod);
	}

	public static void hello() {
		System.out.println("Hello! This is a polynomial interpolator. ");
	}

	public static void interpolate() {
		System.out.println("Commence interpolation...");
	}

	public static void getPoints() {
		System.out.println("Enter number of points for your polynomial.");
		numPoints = scanner.nextInt();
		System.out.println("Make sure your x values are less than your mod. ");
		HashSet<Integer> usedPoints = new HashSet<Integer>();
		for (int i = 0; i < numPoints; i++) {
			int x;
			int y;
			System.out.println("Enter your x value: ");
			x = adjustInt(scanner.nextInt());
			if (usedPoints.contains(x)) {
				System.out.println("Can't repeat an x value.");
				i -= 1;
			} else {
				usedPoints.add(x);
				System.out.println("Enter your y value: ");
				y = adjustInt(scanner.nextInt());
				points.add(new Point(x, y));
				System.out.println("Point " + (i + 1) + " is: " + points.get(i));
			}
		}
		System.out.println("Here are your points: " + points);
	}

	public static int adjustInt(int a) {
		if (a < 0) {
			a += mod;
			return adjustInt(a);
		} else if (a >= mod) {
			a -= mod;
			return adjustInt(a);
		}
		return a;
	}

	public static boolean isPrime(int x) {
		if (x == 2) return true;
		if (x % 2 == 0) {
			return false;
		}
		for (int i = 3; i <= Math.sqrt(x); i+=2) {
			if (x % i == 0) return false;
		}
		return true;
	}

}
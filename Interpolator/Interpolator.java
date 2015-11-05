import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

public class Interpolator {

	static Scanner scanner = new Scanner(System.in);
	private static boolean workingMod = false;
	private static int mod = 0;
	private static int numPoints = 0;
	private static ArrayList<Point> points = new ArrayList<Point>();

	public static void main(String[] args) {
		hello();
		getMod(workingMod);
		getPoints(workingMod);
		interpolate(workingMod);

	}

	public static void interpolate(boolean workingMod) {
		System.out.println("Commence interpolation.");
		System.out.print("Calculating");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (Exception e) {}
		System.out.print(".");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (Exception e) {}
		System.out.print(". ");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (Exception e) {}
		
		Polynomial[] polys = new Polynomial[numPoints];
		for (int i = 0; i < numPoints; i++) {
			Point currPoint = points.get(i);
			ArrayList<Polynomial> subPolys = new ArrayList<Polynomial>();
			double denom = 1.0;
			for (int e = 0; e < numPoints; e++) { //this works
				if (e != i) {
					Point otherPoint = points.get(e);
					denom = denom * (currPoint.x - otherPoint.x);
					Polynomial init = new Polynomial(1.0, 1);
					init = init.sub(new Polynomial(otherPoint.x, 0));
					subPolys.add(init);
				}
			}
			Polynomial finalPoly;
			if (workingMod) {
				denom = adjustInt(denom);
				finalPoly = new Polynomial(multInv(denom), 0);
			} else {
				finalPoly = new Polynomial((1/denom), 0);
			}
			for (Polynomial p: subPolys) {
				finalPoly = finalPoly.multiply(p);
			}
			polys[i] = finalPoly;
		} 

		Polynomial realPoly = new Polynomial(0, 0);
		for (int y = 0; y < numPoints; y++) {
			Point point = points.get(y);
			Polynomial poly = polys[y];
			realPoly = realPoly.add(poly.multiply(new Polynomial(point.y, 0)));
		}
		if (workingMod) {
			for (int z = 0; z < realPoly.coeff.length; z++) {
				double co = realPoly.coeff[z];
				realPoly.coeff[z] = adjustInt(co);
			}
		}

		System.out.println("Here is your polynomial: " + realPoly);
		
		
	}


	//Make more efficient later using Euclid's GCD algorithm
	public static double multInv(double x) {
		int a = 0;
		for (int i = 1; i < mod; i++) {
			if (adjustInt(x*i) == 1) {
				a = i;
			}
		}
		return a;
	}

	public static void getMod(boolean workingMod) {
		if (workingMod) {
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
	}

	public static void hello() {
		System.out.println("Hello! This is a polynomial interpolator. ");
		System.out.println("Do you want to work under a prime modulus? (yes) or (no)");
		if (scanner.next().equals("yes")) {
			workingMod = true;
		}
	}

	public static void getPoints(boolean workingMod) {
		System.out.println("Enter number of points for your polynomial.");
		numPoints = scanner.nextInt();
		HashSet<Double> usedPoints = new HashSet<Double>();
		for (int i = 0; i < numPoints; i++) {
			double x;
			double y;
			System.out.println("Enter your x value: ");
			x = scanner.nextDouble();
			if (workingMod) x = adjustInt(x);
			if (usedPoints.contains(x)) {
				System.out.println("Can't repeat an x value.");
				i -= 1;
			} else {
				usedPoints.add(x);
				System.out.println("Enter your y value: ");
				y = scanner.nextInt();
				if (workingMod) y = adjustInt(y);
				points.add(new Point(x, y));
				System.out.println("Point " + (i + 1) + " is: " + points.get(i));
			}
		}
		System.out.println("Here are your points: " + points);
	}

	public static double adjustInt(double a) {
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
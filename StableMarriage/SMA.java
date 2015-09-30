import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;


public class SMA {

	private static int numPeople;
	static Scanner scanner = new Scanner(System.in);
	private static int stringsFilled = 0;
	static ArrayList<Man> men = new ArrayList<Man>();
	static ArrayList<Woman> women = new ArrayList<Woman>();

	public static void main(String[] args) {
		System.out.println("Hello! This is a stable marriage algorithm solver.");
		System.out.println("Number of stable pairs to make: ");
		numPeople = Integer.parseInt(scanner.next());
		System.out.println("Now, you will enter the men's names.");
		for (int i = 0; i < numPeople; i++) {
			enterMen();
		}
		System.out.println();
		System.out.println("Now, you will enter the women's names.");
		for (int i = 0; i < numPeople; i++) {
			enterWomen();
		}
		System.out.println();
		System.out.println("Now, you will enter the men's preferences.");
		enterMenPrefs();
		System.out.println();
		System.out.println("Now, you will enter the women's preferences.");
		enterWomenPrefs();
		System.out.println();
		System.out.println("Algorithm will now run.");
		System.out.println();
		runAlgorithm();
		
	}

	public static void enterMen() {
		System.out.print("Enter a man's name: ");
		String manName = scanner.next();
		Man man = new Man(manName);
		men.add(man);
		
	}

	public static void enterWomen() {
		System.out.print("Enter a woman's name: ");
		String womanName = scanner.next();
		Woman woman = new Woman(womanName);
		women.add(woman);
		
	}

	public static void enterMenPrefs() {
		for (Man man: men) {
			String name = man.name;
			man.prefs = new LinkedList<String>();
			for (int i = 0; i < numPeople; i++) {
				System.out.print("Enter " + name + "'s Preference " + (i+1) + ":");
				String woman = scanner.next();
				man.prefs.add(woman);
			}
			System.out.println();
		}
	}

	public static void enterWomenPrefs() {
		for (Woman woman: women) {
			String name = woman.name;
			woman.prefs = new LinkedList<String>();
			for (int i = 0; i < numPeople; i++) {
				System.out.print("Enter " + name + "'s Preference " + (i+1) + ":");
				String man = scanner.next();
				woman.prefs.add(man);
			}
			System.out.println();
		}
	}


	public static void runAlgorithm() {
		//Propose
		for (Man man: men) {
			Woman highWoman = getWoman(man.prefs.peek());
			if (!highWoman.proposedTo) {
				stringsFilled += 1;
			}
			highWoman.string.add(man); 
			highWoman.proposedTo = true;
			System.out.println(highWoman.name + highWoman.string);
		}

		//Terminate if strings are full
		if (stringsFilled == numPeople) {
			finishAlgorithm();
			return;
		}

		//Reject
		for (Woman woman: women) {
			ArrayList<Man> rejectedMen = null;
			if (woman.string.size() > 1) {
				rejectedMen = new ArrayList<Man>();
				Man highMan = null;
				int best = 0;
				for (Man man: woman.string) {
					int currManIndex = woman.prefs.indexOf(man.name);
					if (highMan == null) {
						highMan = man;
						best = currManIndex;
					} else {
						if (currManIndex < best) {//this man is better
							rejectedMen.add(highMan);
							highMan.prefs.poll();
							highMan = man;
							best = currManIndex;
						} else {//man on top of string is better
							rejectedMen.add(man);
							man.prefs.poll();
						}
					}
					
				}
				for (Man man: rejectedMen) {
					woman.string.remove(man);
				}
			}
		}

		runAlgorithm();

	}

	public static void finishAlgorithm() {
		System.out.println("Here are the results.");
		System.out.println();
		for (Woman woman: women) {
			System.out.println(woman.name + " is paired with " + woman.string.iterator().next() + ".");
		}
		System.out.println("WOO");
	}


	public static Man getMan(String theName) {
		for (Man man: men) {
			if (man.name.equals(theName)) {
				return man;
			}
		}
		return null;
	}

	public static Woman getWoman(String theName) {
		for (Woman woman: women) {
			if (woman.name.equals(theName)) {
				return woman;
			}
		}
		return null;
	}






}
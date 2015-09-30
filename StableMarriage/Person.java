import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedList;

public class Person {

	String name;
	LinkedList<String> prefs;

	public Person(String name) {
		this.name = name;
		prefs = new LinkedList<String>();
	}

	public String toString() {
		return name;
	}

	


}
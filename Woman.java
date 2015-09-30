import java.util.HashSet;

public class Woman extends Person {

	HashSet<Man> string;
	boolean proposedTo;

	public Woman(String name) {
		super(name);
		string = new HashSet<Man>();
		proposedTo = false;
	}

	


}
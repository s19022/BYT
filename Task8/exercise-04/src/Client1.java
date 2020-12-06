
import java.io.*;

public class Client1 {

	public static void printPerson(Writer out, Person person) throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append(person.getFirstName());
		sb.append(" ");
		if (person.getMiddleName() != null) {
			sb.append(person.getMiddleName());
			sb.append(" ");
		}
		sb.append(person.getLastName());
		out.write(sb.toString());
	}

}

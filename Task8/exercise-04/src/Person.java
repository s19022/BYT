public class Person {
	private String lastName;

	private String firstName;

	private String middleName;


	public Person(String lastName, String firstName, String middleName) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb	.append(getLastName())
				.append(", ")
				.append(getFirstName());

		if (getMiddleName() != null)
			sb.append(" ").append(getMiddleName());
		return sb.toString();
	}
}
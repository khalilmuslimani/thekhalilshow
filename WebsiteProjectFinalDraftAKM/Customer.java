package khalil.business;

public class Customer {

	private String firstName;
	private String middleInitial;
	private String lastName;
	private String email;
	
	public Customer() {
		this("", "", "", "");
	}
	
	public Customer(String firstName, String middleInitial, String lastName, String email) {
		this.firstName = firstName;
		this.middleInitial = middleInitial;
		this.lastName = lastName;
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return firstName + " " + middleInitial + ". " + lastName;
	}
}

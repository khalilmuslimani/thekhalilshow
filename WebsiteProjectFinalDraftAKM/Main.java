package khalil.ui;

import java.util.List;
import khalil.business.Customer;
import khalil.io.CustomerTextFile;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Welcome to the Customer Information Application");
		
		displayMenu();
		
		String action;
		while(true) {
			action = Console.getString("Enter a command: ");
			System.out.println();
			
			if(action.equalsIgnoreCase("list")) {
				displayAllCustomers();
			} else if (action.equalsIgnoreCase("add")) {
				addCustomer();
			} else if (action.equalsIgnoreCase("del") || action.equalsIgnoreCase("delete")) {
				deleteCustomer();
			} else if (action.equalsIgnoreCase("help") || action.equalsIgnoreCase("menu")) {
				displayMenu();
			} else if (action.equalsIgnoreCase("exit") || action.equalsIgnoreCase("quit")) {
				quit();
			} else {
				System.out.println("Error! Not a valid command.\n");
			}
		}
	}
	
	public static void displayMenu() {
		System.out.println("COMMAND MENU");
		System.out.println("list     - List all customers");
		System.out.println("add      - Add a customer");
		System.out.println("del      - Delete a customer");
		System.out.println("help     - Show this menu");
		System.out.println("exit     - Exit thie application\n");
	}

	public static void displayAllCustomers() {
		System.out.println("CUSTOMER LIST");
		List<Customer> customers = CustomerTextFile.getCustomers();
		Customer c;
		final int NAME_SIZE = 25;
		StringBuilder sb = new StringBuilder();
		for (Customer customer : customers) {
			c = customer;
			sb.append(StringUtils.padWithSpaces(
					c.getName(), NAME_SIZE + 2));
			sb.append(c.getEmail());
			sb.append("\n");
			
		}
		System.out.println(sb.toString());
	}
	
	public static void addCustomer() {
	
		String firstName = Console.getString("Enter first name: ");
		String middleInitial = Console.getString("Enter middle initial: ");
		String lastName = Console.getString("Enter last name: ");
		String email = Console.getString("Enter customer email: ");
	
		Customer customer = new Customer();
		customer.setFirstName(firstName);
		customer.setMiddleInitial(middleInitial);
		customer.setLastName(lastName);
		customer.setEmail(email);
		CustomerTextFile.addCustomer(customer);
	
		System.out.println();
		System.out.println(firstName + " " + middleInitial + " " + lastName + " has been added.\n");
	}
	
	public static void deleteCustomer() {
		String email = Console.getString("Enter email to delete: ");
		Customer c = CustomerTextFile.getCustomer(email);
		System.out.println();
		if(c != null) {
			CustomerTextFile.deleteCustomer(c);
			System.out.println(c.getName() + " has been deleted.\n");
		} else {
			System.out.println("No customer matches that email.\n");
		}
	}
	
	public static void quit() {
		System.out.println("Bye.\n");
		System.exit(0);
	}
}

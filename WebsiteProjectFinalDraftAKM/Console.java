package khalil.ui;

import java.util.Scanner;

public class Console {

	private static Scanner sc = new Scanner(System.in);
	
	public static void println() {
		System.out.println();
	}
	
	public static void println(String string) {
		System.out.println(string);
	}
	
	public static String getString(String prompt) {
		System.out.print(prompt);
		String s = sc.nextLine();
		return s;
	}
	
}

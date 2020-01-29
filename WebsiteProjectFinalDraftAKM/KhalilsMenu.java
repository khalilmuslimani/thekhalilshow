// Author: Abraham Khalil Muslimani
// Date: 6/18/2019
// File: KhalilsMenu.java
// Purpose: To create a simple menu for CMSC 412 Homework 5. Wish me luck...

package khalil.hw5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class KhalilsMenu {
	
	// turns program on or off essentially. dictates menus while loop, if true, the menu
	// and program exit.
	boolean exit = false;
	public String userDirectoryName = null;
	
	private File file = null;
	// scanner to take input from the user
	Scanner userInput;
	
	// driver code
	public static void main(String[] args) throws FileNotFoundException, IOException {
		KhalilsMenu km = new KhalilsMenu();
		// print the menu header
		km.printMenuHeader();
		// run the menu method that prints the menu options, gets the users choice, and 
		// performs actions based on those choices.
		km.runMenu();

	}
	
	// method to run the menu
	public void runMenu() throws FileNotFoundException, IOException {
		
		// run the menu unless exit becomes true, when option 0 is selected.
		while(!exit) {
			
			printMenuOptions();
			// get the users choice
			int choice = getUserInput();
			
			// perform the given action or method and statement based on the users choice.
			performAction(choice);
		}
	}
	
	// method to print the menus header
	public void printMenuHeader() {
		System.out.println("\n************************************************************");
		System.out.println("*                  Welcome to Khalil's                     *");
		System.out.println("*                File/Directory Processor                  *");
		System.out.println("************************************************************");
	}
	
	// method to print the menus options
	public void printMenuOptions() {
		System.out.println("\n************************************************************");
		System.out.println("*             Please make a selection (0-7)                *");
		System.out.println("************************************************************");
		System.out.println("*  0 - Exit                                                *");
		System.out.println("*  1 - Select Directory                                    *");
		System.out.println("*  2 - List Directory Contents (First Level)               *");
		System.out.println("*  3 - List Directory Contents (All Levels)                *");
		System.out.println("*  4 - Delete File                                         *");
		System.out.println("*  5 - Display File (Hexadecimal View)                     *");
		System.out.println("*  6 - Encrypt File (XOR With Password)                    *");
		System.out.println("*  7 - Decrypt File (XOR With Password)                    *");
		System.out.println("************************************************************");		
		System.out.println("*            Version 1 Date: 6/18/2019 - AKM               *");
		System.out.println("************************************************************");
		System.out.print("\nPlease make a selection (0-7).");
	}
	
	// method to print goodbye and end program
	public void endProgram() {
		exit = true;
		System.out.println("\n************************************************************");
		System.out.println("*                  Goodbye!!! Thanks For                   *");
		System.out.println("*          Using Khalil's Fiile/Directory Processor        *");
		System.out.println("************************************************************");
	}
	
	// method for getting users input
	private int getUserInput() {
		
		// get user input
		userInput = new Scanner(System.in);
		String input = userInput.nextLine();
		// set choice to -1 so that the menu option selected are clear.
		int choice = -1;
		// catch all invalid selections
		while(choice < 0 || choice > 7) {
			try {
				// ask for input

				// parse the next line from the user to an integer value
				choice = Integer.parseInt(input);
				// tell the user to try again if they picked an invalid selection
				if (choice < 0 || choice > 7) {
					System.out.println("\nInvalid selection. Please try again.");
				}
			}
			// ask for valid selection
			catch(NumberFormatException e) {
				System.out.println("\nInvalid selection. Please try again.");
			}
		}
		// send back the selection
		return choice;
	}
	
	// method to process and execute the selection based on the users choice
	private void performAction(int choice) throws FileNotFoundException, IOException {
		
		// switch statement for each possible selection
		switch(choice) {
		case 0:
			endProgram();
			break;
		case 1:
			getDirectory();
			break;
		case 2:		
			listDirectoryContentsFirstLevel(file);
			break;
		case 3:
			listDirectoryContentsAllLevels();
			break;
		case 4:
			deleteFile(); 
			break;
		case 5:
			displayFileHexadecimalView();
			break;
		case 6:
			encryptFileXORWithPassword();
			break;
		case 7:
			dencryptFileXORWithPassword();
			break;
		default:
			// the user should never reach this scenario, give error message.
			System.out.println("\nAn unkown error has occured.");
		}
		
	}
	
	public void getDirectory() {
		System.out.println("\nThanks for choosing Option 1 - 'Select Directory'.");
		System.out.println("\nPlease enter the directory[absolute] name: ");
		userDirectoryName = null;
		@SuppressWarnings("resource")
		Scanner userInput = new Scanner(System.in);
		userDirectoryName = userInput.nextLine();
		file = new File(userDirectoryName);
		
		if(file.exists()) {
			System.out.println("Success!");
		}
		else {
			file = null;
			System.out.println("\nInvalid selection. Please try again.");
		}
	}
	
	public void listDirectoryContentsFirstLevel(File f) {
		
		if(userDirectoryName != null) {
			
			System.out.println("\nThanks for choosing Option 2 - 'List Directory Contents (First Level)'.");
			
			File dir = new File(userDirectoryName);
			
			for (File files : dir.listFiles()) {
				System.out.print("\n"+files.getName());
			}
			
		}
		
		else {
			System.out.println("\nError! Please select a directory before making this selection!");
		}
		
	}
	
	private void printAllDirectoryContent(File[] arr, int level) {
		
		for(File f : arr) {
			for(int i = 0; i < level; i++)
				System.out.print("\t");
			
			if(f.isFile())
				System.out.println(f.getName());
			
			else if(f.isDirectory()) {
				System.out.println("[" + f.getName() + "]");
				
				printAllDirectoryContent(f.listFiles(), level + 1);
			}
		}
	}
	
	private void listDirectoryContentsAllLevels() {
		
		if(userDirectoryName != null) {
			System.out.println("\nThanks for choosing Option 3 - 'List Directory Contents (All Levels)'.");
			System.out.println("");
			
			File dir = new File(userDirectoryName);
			if(dir.exists() && dir.isDirectory()) {
				File  arr[] = dir.listFiles();
				printAllDirectoryContent(arr, 0);
			}

		}
		else {
			System.out.println("\nError! Please select a directory before making this selection!");

		}
	}
	
	public void deleteFile() {


		
		if(userDirectoryName != null) {
			System.out.println("\nThanks for choosing Option 4 - 'Delete File'");	
			System.out.println("\nPlease enter the filename you woud like to have deleted: ");

			String filename = userInput.next();
			
			File deleteFile = new File(file.getAbsolutePath() + "\\" + filename);
			
			if(deleteFile.delete()) {
				System.out.println(filename + " was deleted successfully");
			}
			else {
				System.out.println(filename + " was not found in the Directory; deletion failed. Please Try again.");
			}
		}
		else {
			System.out.println("\nError! Please select a directory before making this selection!");

		}	
	}
	
	public void displayFileHexadecimalView() throws FileNotFoundException, IOException {

		
		if(userDirectoryName != null) {
			System.out.println("Thanks for choosing Option 5 - 'Display File (Hexadecimal View)'");
			System.out.println("\nPlease enter the filename you would like to have displayed in hexadecimal view: ");
			
			String filename = userInput.next();
			
			try(FileInputStream fis = new FileInputStream(file.getAbsolutePath() + "\\" + filename)) {
				int i = 0;
				int count = 0;
				while((i = fis.read()) != -1) {
					System.out.printf("%02X ", i);
					count++;
					
					if(count == 16) {
						System.out.println("");
						count = 0;
					}
				}
			} catch (FileNotFoundException ex) {
				System.out.println("Error! The file you selected is invalid please try again");
			} catch (IOException ex) {
				System.out.println("Error! The file you selected is invalid please try again");
			}
		}
		else {
			System.out.println("\nError! Please select a directory before making this selection!");
		}
	}
	
	public void encryptFileXORWithPassword() throws IOException {


		

		
		if(userDirectoryName != null) {
			System.out.println("Thanks for choosing Option 6 - 'Encrypt File (XOR With Password)'");
			System.out.println("Please enter the name of the file you would like to have encrypted: ");
			String filename = userInput.next();
			File fileToEncrypt = new File(this.userDirectoryName + "/" + filename);
			
			if(!fileToEncrypt.exists()) {
				System.out.println("\nError! This file does not exist.");
				return;
			}
			System.out.println("\nPlease enter password (max 256 bytes long, may contain letters, digits, other characters): ");
			
			String password = userInput.next();
			
			BufferedReader bf = null;
			BufferedWriter bw = null;
			
			File encryptedFile = new File(this.userDirectoryName + "/" + filename + "_encrypted.txt");
			if (fileToEncrypt.isDirectory()) {
				System.out.println("The file you selected is a directory.");
				return;
			}
			
			try {
				bf = new BufferedReader(new FileReader(fileToEncrypt));
				bw = new BufferedWriter(new FileWriter(encryptedFile));
				
				int passwordIndex = 0;
				char charCode;
				char encryptChar;
				
				while(bf.ready()) {
					if(passwordIndex == password.length()) {
						passwordIndex = 0;
					}
					
					charCode = (char)bf.read();
					encryptChar = (char)(password.charAt(passwordIndex) ^ (char)charCode);
					bw.append(encryptChar);
					
					passwordIndex++;
				}
				bf.close();
			} catch(FileNotFoundException ex) {
				System.err.println("Error: " + ex.getMessage());
			} catch (IOException ex) {
				System.err.println("Error: " + ex.getMessage());
			} finally {
				bw.flush();
				bf.close();
				bw.close();
				fileToEncrypt.delete();
			}
			
		}
		else {
			System.out.println("\nError! Please select a directory before making this selection!");
		}
	}
	
	public void dencryptFileXORWithPassword() throws IOException {


		
		if(userDirectoryName != null) {
			System.out.println("Thanks for choosing Option 7 - 'Decrypt File (XOR With Password)'");

			String pathOfFile;
			BufferedReader bf = null;
			BufferedWriter bw = null;
			System.out.println("Please enter the name of the file you would like to have dencrypted: ");
			String filename = userInput.nextLine();
			pathOfFile = file.getAbsolutePath() + "\\" + filename;
			File fileToDecrypt = new File(pathOfFile);
			
			if(!fileToDecrypt.exists()) {
				System.out.println("\nError! This file does not exist.");
				return;
			}
			System.out.println("\nPlease enter password (max 256 bytes long, may contain letters, digits, other characters): ");
			String password = userInput.next();
			
			pathOfFile = pathOfFile.replaceAll("(_encrypted.txt)", "");
			File encryptedFile = new File(pathOfFile);
			
			try {
				bf = new BufferedReader(new FileReader(fileToDecrypt));
				bw = new BufferedWriter(new FileWriter(encryptedFile));
				
				int passwordIndex = 0;
				char charCode;
				char encryptChar;
				
				while(bf.ready()) {
					if(passwordIndex == password.length()) {
						passwordIndex = 0;
					}
					
					charCode = (char)bf.read();
					encryptChar = (char)(password.charAt(passwordIndex) ^ (char)charCode);
					bw.append(encryptChar);
					passwordIndex++;
				}
				bf.close();
			} catch(FileNotFoundException ex) {
				System.err.println("\nError: " + ex.getMessage());
			} catch (IOException ex) {
				System.err.println("\nError: " + ex.getMessage());
			} finally {
				bw.flush();
				bf.close();
				bw.close();
				fileToDecrypt.delete();
			}
			
		}
		else {
			System.out.println("\nError! Please select a directory before making this selection!");
		}
	}
}
	



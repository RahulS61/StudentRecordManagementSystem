package com.admin;

import java.io.*;
import java.io.IOException;
import java.util.*;

public class AdminFunctions {
	public static int function() throws ClassNotFoundException, IOException {

		int maxLogin = 5;
		boolean isUser = false;
		while (maxLogin > 0) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter UserId and Password");
			String id = sc.next();
			String password = sc.next();
			isUser = Admin.validateAdmin(id, password);
			if (!isUser) {
				if (maxLogin == 1) {
					System.err.println("      You have attempted more times. " + "\n        Please try again later!!");
					return -1;
				} else
					System.err.println("    You have " + (maxLogin - 1) + " more attempts to Login! ");
				maxLogin--;

			}
			while (isUser) {
				System.out.println("+-------Enter Your choice-------+");
				System.out.println("|                               |");
				System.out.println("|       1.Add Student           |");
				System.out.println("|       2.Remove Student        |");
				System.out.println("|       3.Search Student        |");
				System.out.println("|       4.Display Student       |");
				System.out.println("|       5.Add Faculty           |");
				System.out.println("|       6.Display Faculty       |");
				System.out.println("|       7.Search  Faculty       |");
				System.out.println("|       8.Log out               |");
				System.out.println("|                               |");
				System.out.println("+-------------------------------+");
				int choice = sc.nextInt();
				switch (choice) {
				case 1:
					Admin.addStudent();
					break;
				case 2:
					Admin.removeStudent();
					break;
				case 3:
					File f = new File("studentDetails.txt");
					TreeMap<String, LinkedHashMap<String, String>> li = null;
					FileInputStream fis = new FileInputStream(f);
					try {
						ObjectInputStream ois = new ObjectInputStream(fis);
						li = (TreeMap<String, LinkedHashMap<String, String>>) ois.readObject();
					} catch (IOException e) {
					}
					Admin.search(li);
					break;
				case 4:
					File f1 = new File("studentDetails.txt");
					FileInputStream fis1 = new FileInputStream(f1);
					TreeMap<String, LinkedHashMap<String, String>> lih = null;
					try {
						ObjectInputStream ois1 = new ObjectInputStream(fis1);
						lih = (TreeMap<String, LinkedHashMap<String, String>>) ois1.readObject();
					} catch (IOException e1) {
					}
					Admin.display(lih);
					break;
				case 5:
					Admin.addFaculty();
					break;
				case 6:
					File f2 = new File("FacultyDetails.txt");
					TreeMap<String, LinkedHashMap<String, String>> li11 = null;
					FileInputStream fis2 = new FileInputStream(f2);
					try {
						ObjectInputStream ois2 = new ObjectInputStream(fis2);
						li11 = (TreeMap<String, LinkedHashMap<String, String>>) ois2.readObject();
					} catch (IOException e) {
					}
					Admin.display(li11);
					break;
				case 7:
					File f21 = new File("FacultyDetails.txt");
					TreeMap<String, LinkedHashMap<String, String>> li21 = null;
					FileInputStream fis21 = new FileInputStream(f21);
					try {
						ObjectInputStream ois21 = new ObjectInputStream(fis21);
						li21 = (TreeMap<String, LinkedHashMap<String, String>>) ois21.readObject();
					} 
					catch (IOException e) {
					}
					Admin.search(li21);
					break;
				case 8:
					System.out.println(id + " you have been logged out!");
					return -1;
				default:
					System.err.println("Enter a valid option!");
				} 

			}
		}
		return 0;
	}
}

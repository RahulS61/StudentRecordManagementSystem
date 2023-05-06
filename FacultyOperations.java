package com.faculty;

import java.io.*;
import java.util.*;

import com.admin.Admin;
import com.student.Student;

public class FacultyOperations {
	public static int facultyFunctions() throws IOException, ClassNotFoundException {
		int login = 5;
		while (login > 0) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter Your Faculty Id and password");
			String id = sc.next();
			String password = sc.next();
			boolean isUser = false;
			isUser = Admin.validateUser(id, password, "faculty");
			if (!isUser) {
				if (login == 1) {
					System.err.println("      You have attempted more times. " + "\n        Please try again later!!");
					return -1;
				} else
					System.err.println("    You have " + (login - 1) + " more attempts to Login! ");
				login--;

			}
			if (isUser) {
				@SuppressWarnings("unchecked")
				File f = new File("FacultyDetails.txt");
				FileInputStream fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis);
				TreeMap<String, LinkedHashMap<String, String>> hm = (TreeMap<String, LinkedHashMap<String, String>>) ois
						.readObject();
				System.out.println("Welcome " + hm.get(id).get("Name") + "!");
				while (true) {

					System.out.println("+------Enter your choice------+");
					System.out.println("|                             |");
					System.out.println("|      1.Display Details      |");
					System.out.println("|      2.Edit Details         |");
					System.out.println("|      3.Update Details       |");
					System.out.println("|      4.Log out              |");
					System.out.println("|                             |");
					System.out.println("+-----------------------------+");
					int choice = sc.nextInt();
					switch (choice) {
					case 1:
						Student.displayDetails(id, hm.get(id));
						break;
					case 2:
						hm.put(id, Student.editStudentInfo(id, hm.get(id)));
						FileOutputStream fout1 = new FileOutputStream("FacultyDetails.txt");
						ObjectOutputStream out1 = new ObjectOutputStream(fout1);
						out1.writeObject(hm);
						out1.close();
						fout1.close();
						break;
					case 3:
						hm.put(id, Student.updateStudentInfo(id, hm.get(id)));
						FileOutputStream fout11 = new FileOutputStream("FacultyDetails.txt");
						ObjectOutputStream out11 = new ObjectOutputStream(fout11);
						out11.writeObject(hm);
						out11.close();
						fout11.close();
						break;
					case 4:
						System.out.println(id + " you have been logged out!");
						return -1;
					default:
						System.out.println("Enter a valid choice!");
					}

				}

			}

		}
		return -1;
	}
}

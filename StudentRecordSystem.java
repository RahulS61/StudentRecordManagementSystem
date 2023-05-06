package StudenRecordProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;
import java.util.TreeMap;

import com.admin.AdminFunctions;
import com.faculty.FacultyOperations;
import com.student.StudentFunsctions;

public class StudentRecordSystem {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		int option;
		int loginstatus = -1;
		Scanner sc = new Scanner(System.in);
		while (loginstatus == -1) {
			System.out.println("**** STUDENT RECORD SYSTEM****");
			System.out.println("   +-------LOGIN-------+");
			System.out.println("   |                   |");
			System.out.println("   |     1.Admin       |");
			System.out.println("   |     2.Student     |");
			System.out.println("   |     3.Faculty     |");
			System.out.println("   |     4.Exit        |");
			System.out.println("   +-------------------+");

			option = sc.nextInt();
			switch (option) {
			case 1:
				loginstatus = AdminFunctions.function();
				break;
			case 2:
				loginstatus = StudentFunsctions.studentOperation();
				break;
			case 3:
				loginstatus = FacultyOperations.facultyFunctions();
				break;
			case 4:
				loginstatus = 1;
				System.err.println("*** Exiting ***");
				break;
			default:
				System.out.println("Enter a valid User Type");
				break;

			}

		}
	}

}

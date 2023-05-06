package com.admin;

import java.io.*;
import java.util.*;


public class Admin implements Serializable {

	// method to add admin to the system
	public static void addAdmin() throws IOException, ClassNotFoundException {
		TreeMap<String, LinkedHashMap<String, String>> hm = null;
		FileInputStream fis = new FileInputStream("AdminDetails.txt");
		try {
			ObjectInputStream ois = new ObjectInputStream(fis);
			hm = (TreeMap<String, LinkedHashMap<String, String>>) ois.readObject();
			ois.close();
		} catch (IOException e) {
		}

		if (hm == null)
			hm = new TreeMap<String, LinkedHashMap<String, String>>();
		int c = 1;
		while (c == 1) {
			System.out.println("Enter Admimn details");
			System.out.println("(Name,Dob,Address,YearOfJoining)");
			LinkedHashMap<String, String> stu = new LinkedHashMap<String, String>();
			Scanner sc = new Scanner(System.in);
			String name = sc.nextLine();
			String dob = sc.nextLine();
			String address = sc.nextLine();
			String year = sc.nextLine();
			stu.put("Name", name);
			stu.put("Dob", dob);
			stu.put("Address", address);
			stu.put("YearOfJoin", year);
			hm.put("admin" + hm.entrySet().size() + 1, stu);
			System.out.println("Are you want to add Admin ? \n 1.Yes\n 2.No");
			c = sc.nextInt();

		}
		FileOutputStream fout12 = new FileOutputStream("AdminDetails.txt");
		ObjectOutputStream out12 = new ObjectOutputStream(fout12);
		out12.writeObject(hm);
		out12.close();
		fout12.close();
	}

	// validate admin login credentials
	public static boolean validateAdmin(String id, String pass) throws IOException, ClassNotFoundException {
		// file
		TreeMap<String, LinkedHashMap<String, String>> hm = null;
		FileInputStream fis = new FileInputStream("AdminDetails.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		hm = (TreeMap<String, LinkedHashMap<String, String>>) ois.readObject();
		if (hm.containsKey(id)) {
			String password = hm.get(id).get("Name").toLowerCase() + "@" + hm.get(id).get("Dob").substring(0, 2);
			if (!pass.equals(password)) {
				System.out.println("*****Enter a Valid Password*****");
				return false;
			}
		} else {
			System.out.println("*****Enter a valid UserName*****");
			return false;
		}
		ois.close();
		fis.close();

		return true;
	}

	// validating user login details
	public static boolean validateUser(String name, String pass, String user)
			throws IOException, ClassNotFoundException {
		TreeMap<String, String> tm = null;
		FileInputStream fin = null;
		if (user.equals("student"))
			fin = new FileInputStream("studentPasswords.txt");
		else
			fin = new FileInputStream("FacultyPasswords.txt");

		try {
			ObjectInputStream ois = new ObjectInputStream(fin);
			tm = (TreeMap<String, String>) ois.readObject();
			ois.close();
		} catch (Exception e) {
		}
		if (tm == null) {
			System.out.println("Insufficient Details!");
			return false;
		}
		if (tm.containsKey(name)) {
			if (!tm.get(name).equals(pass)) {
				System.out.println("***** Enter a valid password *****");
				return false;
			}
		} else {
			System.out.println("***** Enter a Valid UserName *****");
			return false;
		}

		fin.close();
		return true;
	}

	public static String id_generate(TreeMap<String, LinkedHashMap<String, String>> tm) {
		Set<String> st = tm.keySet();
		return st.size() + 1 + "";
	}

	public static void addStudent() throws ClassNotFoundException, IOException {
		// update password
		FileInputStream fi = new FileInputStream("StudentPasswords.txt");
		TreeMap<String, String> tm = null;
		try {
			ObjectInputStream oi = new ObjectInputStream(fi);
			tm = (TreeMap<String, String>) oi.readObject();
		} catch (IOException e) {
		}
		// file
		if (tm == null)
			tm = new TreeMap<String, String>();

		TreeMap<String, LinkedHashMap<String, String>> hm = null;
		FileInputStream fis = new FileInputStream("studentDetails.txt");
		try {
			ObjectInputStream ois = new ObjectInputStream(fis);
			hm = (TreeMap<String, LinkedHashMap<String, String>>) ois.readObject();
			ois.close();
		} catch (IOException e) {
		}

		if (hm == null)
			hm = new TreeMap<String, LinkedHashMap<String, String>>();
		int c = 1;
		while (c == 1) {
			System.out.println("Enter student details");
			System.out.println("(Name,Department,Year,Dob,Address,Mobile,Email,Blood Group)");
			LinkedHashMap<String, String> stu = new LinkedHashMap<String, String>();
			Scanner sc = new Scanner(System.in);
			String name = sc.nextLine();
			String dept = sc.nextLine();
			String year = sc.nextLine();
			String dob = sc.nextLine();
			String address = sc.nextLine();
			String mob_num = sc.nextLine();
			String email = sc.nextLine();
			String bloodGrp = sc.nextLine();
			stu.put("Name", name);
			stu.put("Dept", dept);
			stu.put("Year", year);
			stu.put("Dob", dob);
			stu.put("Address", address);
			stu.put("Mobile number", mob_num);
			stu.put("Email", email);
			stu.put("Blood group", bloodGrp);
			String id = Admin.id_generate(hm);
			tm.put(id, name.toLowerCase() + "@" + id);
			hm.put(id, stu);

			System.out.println("Are you want to add Student Details ? \n 1.Yes \n 2.No");
			c = sc.nextInt();

		}

		FileOutputStream fout1 = new FileOutputStream("studentDetails.txt");
		ObjectOutputStream out1 = new ObjectOutputStream(fout1);
		out1.writeObject(hm);
		out1.close();
		fout1.close();
		// update passwords
		FileOutputStream fout = new FileOutputStream("StudentPasswords.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(tm);
		oos.close();
		fout.close();
	}

	public static void removeStudent() throws IOException, ClassNotFoundException {
		File f = new File("studentDetails.txt");
		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream ois = new ObjectInputStream(fis);
		TreeMap<String, LinkedHashMap<String, String>> tm = (TreeMap<String, LinkedHashMap<String, String>>) ois
				.readObject();
		System.out.println("Enter the Student Id to Remove:");
		Scanner sc = new Scanner(System.in);
		String id = sc.next();
		tm.remove(id);
		ois.close();
		fis.close();
		FileOutputStream fout1 = new FileOutputStream("studentDetails.txt");
		ObjectOutputStream out1 = new ObjectOutputStream(fout1);
		out1.writeObject(tm);
		out1.close();
		fout1.close();
	}

	public static void display(TreeMap<String, LinkedHashMap<String, String>> li)
			throws IOException, ClassNotFoundException {
		if (li == null) {
			System.out.println("Details are not sufficient!");
		} else {
			String id = "Id";
			for (Map.Entry e : li.entrySet()) {
				System.out.println("+------------------------------------------------------------------+");
				System.out.println(String.format("|%-23s | %-40s|", id, e.getKey()));
				for (Map.Entry e1 : ((LinkedHashMap<String, String>) e.getValue()).entrySet()) {
					System.out.println(String.format("|%-23s | %-40s|", e1.getKey(), e1.getValue()));
				}
				System.out.println("+------------------------------------------------------------------+");
				System.out.println();
			}
		}

	}

	public static void search(TreeMap<String, LinkedHashMap<String, String>> li)
			throws IOException, ClassNotFoundException {
		if (li == null) {
			System.out.println("Details are not sufficient!");

		} else {
			System.out.println("Enter the Student Id:");
			Scanner sc = new Scanner(System.in);
			String id = sc.next();
			if (li.containsKey(id)) {
				System.out.println("Details of " + id + " :");
				for (Map.Entry e : li.get(id).entrySet()) {
					System.out.println(e.getKey() + ": " + e.getValue());
				}
			} else {
				System.out.println("Enter a Valid Student Id!");
			}
		}

	}

	// Faculty operations
	public static String id_create(TreeMap<String, LinkedHashMap<String, String>> tm) {
		Set<String> st = tm.keySet();
		return "c" + st.size() + 1 + "";
	}

	public static void addFaculty() throws ClassNotFoundException, IOException {
		// update password
		FileInputStream fi = new FileInputStream("FacultyPasswords.txt");
		TreeMap<String, String> tm = null;
		try {
			ObjectInputStream oi = new ObjectInputStream(fi);
			tm = (TreeMap<String, String>) oi.readObject();
		} catch (IOException e) {
		}
		// file
		if (tm == null)
			tm = new TreeMap<String, String>();
		// file
		TreeMap<String, LinkedHashMap<String, String>> hm = null;
		FileInputStream fis = new FileInputStream("FacultyDetails.txt");
		try {
			ObjectInputStream ois = new ObjectInputStream(fis);
			hm = (TreeMap<String, LinkedHashMap<String, String>>) ois.readObject();
			ois.close();
		} catch (IOException e) {
		}

		if (hm == null)
			hm = new TreeMap<String, LinkedHashMap<String, String>>();
		int c = 1;
		while (c == 1) {
			System.out.println("Enter Faculty details");
			System.out.println("(Name,Dob,Address,Bloodgroup,CourseHandling,YearOfJoining,Degree)");
			LinkedHashMap<String, String> stu = new LinkedHashMap<String, String>();
			Scanner sc = new Scanner(System.in);
			String name = sc.nextLine();
			String Dob = sc.nextLine();
			String address = sc.nextLine();
			String Blood_group = sc.nextLine();
			String course = sc.nextLine();
			String yearOfJoining = sc.nextLine();
			String Degree = sc.nextLine();
			stu.put("Name", name);
			stu.put("Dob", Dob);
			stu.put("Degree", Degree);
			stu.put("Address", address);
			stu.put("BloodGroup", Blood_group);
			stu.put("Course", course);
			stu.put("YearOfJoining", yearOfJoining);
			String id = Admin.id_create(hm);
			tm.put(id, name.toLowerCase() + "@" + id);
			hm.put(id, stu);
			System.out.println("Are you want to add Faculty ? \n 1.Yes \n 2.No");
			c = sc.nextInt();

		}

		FileOutputStream fout1 = new FileOutputStream("FacultyDetails.txt");
		ObjectOutputStream out1 = new ObjectOutputStream(fout1);
		out1.writeObject(hm);
		out1.close();
		fout1.close();
		// update passwords
		FileOutputStream fout = new FileOutputStream("FacultyPasswords.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(tm);
		oos.close();
		fout.close();
	}

//	public static void displayPassword() throws IOException, ClassNotFoundException
//	{
//		FileInputStream fin = new FileInputStream("Passwords.txt");
//		ObjectInputStream ois = new ObjectInputStream(fin);
//		TreeMap<String,String> tm = (TreeMap<String, String>) ois.readObject();
//		for(Map.Entry e:tm.entrySet())
//		{
//			System.out.println("Username: "+e.getKey()+"  password : "+e.getValue());
//		}
//	}

	// update the password of admin
//	public static void updatePass() throws IOException
//	{
//		TreeMap<String,String> tm =null;
//		FileInputStream fin = new FileInputStream("Passwords.txt");
//		ObjectInputStream ois = new ObjectInputStream(fin);
//		 try {
//			tm = (TreeMap<String, String>) ois.readObject();
//		} catch ( IOException | ClassNotFoundException e) {
//		}
//		 if(tm==null)
//		   tm = new TreeMap<String,String>();
//		 tm.put("admin","admin@101");
//		 FileOutputStream fout = new FileOutputStream("Passwords.txt");
//		 ObjectOutputStream oos = new ObjectOutputStream(fout);
//		 oos.writeObject(tm);
//		 oos.close();
//		 fout.close();
//		 
//	}
//	

//	public static void main(String[] args) throws IOException, ClassNotFoundException {
//        //Admin.updatePass();
//		//Admin.addAdmin();
//		TreeMap<String,LinkedHashMap<String,String>> hm = null;
//		FileInputStream fis = new FileInputStream("AdminDetails.txt");
//		try {
//			ObjectInputStream ois = new ObjectInputStream(fis);
//			hm = (TreeMap<String,LinkedHashMap<String,String>>) ois.readObject();
//			ois.close();
//		} 
//		catch (IOException e) {}
//		
//		if(hm==null)
//			hm = new TreeMap<String,LinkedHashMap<String,String>>();
//		System.out.println(hm);
// }	
//}
}

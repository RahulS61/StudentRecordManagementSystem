package com.student;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.TreeMap;

import com.admin.Admin;

import StudenRecordProject.*;

public class StudentFunsctions {
  public static int studentOperation() throws ClassNotFoundException, IOException
  {
	  boolean isUser = false;
	  int maxLogin = 5;
	  while(maxLogin >0) 
	  {
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Enter Your Student Id and Password:");
	    String id = sc.next();
	    String password = sc.next();
	    isUser = Admin.validateUser(id, password,"student");
	    if (!isUser) {
			if (maxLogin == 1) {
				System.err.println("      You have attempted more times. " + "\n        Please try again later!!");
				return -1;
			} else
				System.err.println("    You have " + (maxLogin - 1) + " more attempts to Login! ");
			maxLogin--;

		}
	    while(isUser)
	    {
	    	@SuppressWarnings("unchecked")
	    	File f = new File("studentDetails.txt");
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
		 TreeMap<String,LinkedHashMap<String,String>> hm =(TreeMap<String,LinkedHashMap<String,String>>) ois.readObject();
	    	System.out.println("      Welcome "+hm.get(id).get("Name")+"!");
	    	while(true)
	    	{
	    		
	    		System.out.println("+------Enter your choice------+");
	    		System.out.println("|                             |");
	    		System.out.println("|      1.Display Details      |");
	    		System.out.println("|      2.Edit Details         |");
	    		System.out.println("|      3.Update Details       |");
	    		System.out.println("|      4.Log out              |");
	    		System.out.println("|                             |");
	    		System.out.println("+-----------------------------+");
	    		int choice = sc.nextInt();
	    		 switch(choice)
	    		 {
	    		 case 1:
	    			 Student.displayDetails(id, hm.get(id));
	    			 break;
	    		 case 2:
	    			hm.put(id,Student.editStudentInfo(id, hm.get(id)));
	    			FileOutputStream fout1 = new FileOutputStream("studentDetails.txt");
	    			ObjectOutputStream out1 = new ObjectOutputStream(fout1);
	    			out1.writeObject(hm);
	    			out1.close();
	    			fout1.close();
	    			 break;
	    		 case 3:
	    			 hm.put(id, Student.updateStudentInfo(id, hm.get(id)));
	    			 FileOutputStream fout11 = new FileOutputStream("studentDetails.txt");
	    				ObjectOutputStream out11 = new ObjectOutputStream(fout11);
	    				out11.writeObject(hm);
	    				out11.close();
	    				fout11.close();
	    			 break;
	    		 case 4:
	    			 System.out.println(id+" you have been logged out!");
	    			 return -1;
	    		 default:
	    			 System.err.println("Enter a valid choice!");
	    		 }
	    		 
	    	}
	    	
	    }
	    
	  }
	  return 0;
  }
}

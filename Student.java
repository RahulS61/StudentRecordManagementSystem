package com.student;
import java.io.*;
import java.util.*;

public class Student
{
	 
	public static void displayDetails(String id,LinkedHashMap<String,String> lh)
	{
		System.out.println("Hello "+lh.get("Name")+"!, Your Details:");
		  //System.out.println();
		  System.out.println("+------------------------------------------------------------------+");
		  System.out.println(String.format("|%-23s | %-40s|","Id",id));
		  for(Map.Entry e:lh.entrySet())
		  {
		      System.out.println(String.format("|%-23s | %-40s|",e.getKey(),e.getValue()));
		  }
		  System.out.println("+------------------------------------------------------------------+");
		  System.out.println();
		  
	}
	public static LinkedHashMap<String,String> editStudentInfo(String id,LinkedHashMap<String,String> lm) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		Student.displayDetails(id, lm);
		System.out.println("How many fields do  you want to edit ?");
		int count = Integer.parseInt(sc.nextLine());
		while(count-- >0)
		{
			System.out.println("Enter the field you want to edit:");
			String key = sc.nextLine();
			if(lm.containsKey(key)) 
			{
				System.out.println("Enter the value for "+key+":");
				String value = sc.nextLine();
		    lm.put(key, value);
			}
			else
			{
				System.out.println("Enter a Valid Field");
				count++;
			}
		}
		System.out.println("Updated Details");
		Student.displayDetails(id, lm);
		return lm;

	}
	public static LinkedHashMap<String,String> updateStudentInfo(String id,LinkedHashMap<String,String> lh)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Update Info(Add educational qualification,personal details)");
	  int flag = 1;
	  while(flag==1)
	  {
		  System.out.println("Enter the field to add details:");
		  String field = sc.nextLine();
		  System.out.println("Enter the value for the field:");
		  String value = sc.nextLine();
		  lh.put(field,value);
		  System.out.println("Do you want to add more details? \n 1.Yes \n 2.No");
		   flag = Integer.parseInt(sc.nextLine());
		   
	  }
		return lh;
	}
	
	
}

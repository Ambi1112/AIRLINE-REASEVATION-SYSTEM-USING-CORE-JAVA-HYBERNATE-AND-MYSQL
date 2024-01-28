package org.jsp.dao;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.jsp.dto.UserReg;

public class UserDao {
 
	EntityManager manager=Persistence.createEntityManagerFactory("dev").createEntityManager();
	Scanner sc=new Scanner(System.in);
	
	public void register() {
		System.out.println("Enter Your Name");
		String fName = sc.nextLine();
		System.out.println("Enter Your PhoneNumber");
		long phone = sc.nextLong();
		System.out.println("Enter Your Password");
		String pass = sc.next();
		System.out.println("Enter Your Age");
		int age = sc.nextInt();
		System.out.println("Enter Your Gender");
		String gender = sc.next();
		UserReg u = new UserReg(fName, phone, pass, age, gender);
		String user_name = removespace(fName) + (100 + (int) (Math.random() * 900));
		u.setUser_Name(user_name);
		EntityTransaction t = manager.getTransaction();
		manager.persist(u);
		t.begin();
		t.commit();
		System.out.println("------Registration Successfull------");
		System.out.println("Your User name is:- " + u.getUser_Name());
		System.out.println();

	}

	String removespace(String s) {
		s=s.toLowerCase();
		String ns = "";
		for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == ' ') {
				continue;
			} else {
				ns = ns + s.charAt(i);
			}
		}
		return ns;
	}
}

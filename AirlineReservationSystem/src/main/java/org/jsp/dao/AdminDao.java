package org.jsp.dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.dto.Admin;
import org.jsp.dto.Flights;
import org.jsp.dto.Passenger;
import org.jsp.dto.UserReg;

public class AdminDao {
    EntityManager manager=Persistence.createEntityManagerFactory("dev").createEntityManager();
    Scanner sc=new Scanner(System.in);
    
    public void getAllPassengers() {
    	for(int i=1;i<=5;i++) {
    		Flights f=null;
    		try {
    		 f=manager.find(Flights.class,i);
    		 List<Passenger> pl=f.getPassengers();
    		 for(Passenger p:pl) {
    			 System.out.println("Passenger name:- "+p.getName());
    			 System.out.println("Passenger phone:- "+p.getPhoneNum());
    			 System.out.println("Passenger email:- "+p.getGender());
    			 System.out.println("Passenger seat number:- "+p.getSeatno());
    			 System.out.println("Passenger seat class:- "+p.getSeatClass());
    			 System.out.println("Flight id:- "+f.getId());
    			 System.out.println("--------------------------------------------");
    		 }
    		}catch (NoResultException e) {
    			
			}
    	}
    }
    
    public void getPassengersById() {
    	System.out.println("Enter flight id to get passengers details");
    	int fid =sc.nextInt();
		try {
			Flights f=manager.find(Flights.class,fid);
		 List<Passenger> pl=f.getPassengers();
		if(pl.size()>0) {
			 for(Passenger p:pl) {
				 System.out.println("Passenger name:- "+p.getName());
				 System.out.println("Passenger phone:- "+p.getPhoneNum());
				 System.out.println("Passenger email:- "+p.getGender());
				 System.out.println("Passenger seat number:- "+p.getSeatno());
				 System.out.println("Passenger seat class:- "+p.getSeatClass());
				 System.out.println("Flight id:- "+f.getId());
				 System.out.println("--------------------------------------------");
			 }
		}
		else {
			System.err.println("There is no booked flight with flight id:- "+f.getId());
			System.out.println();
		}
		}catch (NoResultException e) {
			
		}
    }
    
    public void addFlight() {
        System.out.println("Enter flight name,origin,destination,startingTime,endingTime,timeDuration,famount");
        String name=sc.next();
        String origin=sc.next();
        String destination=sc.next();
        String stratingTime=sc.next();
        String endingTime=sc.next();
        String timeDuration=sc.next();
        int fAmount=sc.nextInt();
        Flights f=new Flights(name, origin, destination, stratingTime, endingTime, timeDuration, fAmount);
        manager.persist(f);
        EntityTransaction t=manager.getTransaction();
        t.begin();t.commit();
        System.out.println("Flight successfully added with flight id:- "+f.getId());
        System.out.println();
        
    }
    
    public void removeFlight() {
    	System.out.println("Enter flight id to delete flight details\n");
    	int fid =sc.nextInt();
    	try {
    		Flights f= manager.find(Flights.class,fid);
    		
    		manager.remove(f); 
    		 EntityTransaction t=manager.getTransaction();
    	        t.begin();t.commit();
    	    System.out.println("Flight successfully deleted\n");
    	}
    	catch (NoResultException e) {
    		System.err.println("Flight can not deleted as you entered wrong flight id\n");
		}
    }
    
    String pass;
	int user_id;
	public boolean login() {
		int fl=0;
		while(fl==0) {
		String jpql1 = "select a from Admin a where a.email=?1 and a.password=?2";
		String jpql2 = "select a from Admin a where a.phone=?1 and a.password=?2";
		int t = 0, ans = 0,exit=0;
		String email = "";
		long phn = 0;
		while (t == 0) {
			ans = 0;
			System.out.println("=== Login Account As Admin ===");
			System.out.println(
					"1.LogIn With EmailId\n          OR\n2.LogIn With PhoneNumber\n3.Forget UserName Or Password\n4.Exit");
			int op = sc.nextInt();
			switch (op) {
			case 1:t++;
				ans = 1;
				System.out.println("Enter Your  Email");
				sc.nextLine();
				String fname = sc.nextLine();
				email = fname;
				break;
			case 2:t++;
				ans = 2;
				System.out.println("Enter Your  PhoneNumber");
				long phone = sc.nextLong();
				phn = phone;
				break;
			case 3:
				boolean flage = true;
				while (flage) {
					System.out.println("Enter your PhoneNumber");
					long p = sc.nextLong();
					Admin a=getAdmin(p);
					if (a!=null) {
						int otp = 1000 + (int) (Math.random() * 9000);
						System.out.println("Your OTP is:- "+otp);
						System.out.println("Enter your OTP to verify Phone Number");
						int uotp = sc.nextInt();
						flage = false;
						if(otp == uotp) {
							System.out.println("Your User Name is:- " + a.getEmail());
							System.out.println("Your Password is:- " + a.getPassword());
						}
						else {
							System.err.println("Please Enter Correct OTP TO Verify");
						}
						
					} else {
						System.err.println("Wrong PhoneNumber Please Enter Right PhoneNumber");
					}
				}
				break;
			case 4: exit++;t++;fl++;
			break;
			default:
				System.err.println("Invalid input");
				break;
			}
		}
		if(exit==0) {
		System.out.println("Enter Your Password");
		 pass = sc.next();
		System.out.println();

		if (ans == 1) {
			Query q1 = manager.createQuery(jpql1);
			q1.setParameter(1, email);
			q1.setParameter(2, pass);
			try {
			Admin adminWithUemail = (Admin) q1.getSingleResult();
			System.out.println("---LogIn Successfull---");
			fl++;
			System.out.println();
			return true;
			}
			catch (NoResultException e) {
				System.err.println("++++Wrong eamil or phone number or password++++");
				System.out.println();
				System.err.println("Please try again");
			}
		}
		if (ans == 2) {
			Query q2 = manager.createQuery(jpql2);
			q2.setParameter(1, phn);
			q2.setParameter(2, pass);
			try {
				Admin uveriWithPhn = (Admin) q2.getSingleResult();
			System.out.println("---LogIn Successfull---\n");
			fl++;
			return true;
			}
			catch (NoResultException e) {
				System.err.println("++++Wrong username or phone number or password++++");
				System.out.println();
				System.err.println("Please try again");
			}
		}
	}
	}
		return false;
	}
	
	Admin getAdmin(long phone) {
		String jpql = "select a from Admin a where a.phone=?1";
		Query q = manager.createQuery(jpql);
		q.setParameter(1, phone);
		try {
			return  (Admin) q.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
		

	}
}

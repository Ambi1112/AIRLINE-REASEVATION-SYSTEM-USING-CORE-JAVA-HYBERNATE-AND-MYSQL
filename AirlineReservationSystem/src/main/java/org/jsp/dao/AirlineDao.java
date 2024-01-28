package org.jsp.dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.dto.BookedFlights;
import org.jsp.dto.Flights;
import org.jsp.dto.Passenger;
import org.jsp.dto.PassengerAddress;
import org.jsp.dto.SeatFId1;
import org.jsp.dto.UserReg;

public class AirlineDao {
	static Scanner sc = new Scanner(System.in);
	EntityManager manager = Persistence.createEntityManagerFactory("dev").createEntityManager();
	String pass;
	int user_id;
	public boolean login() {
		int fl=0;
		while(fl==0) {
		String jpql1 = "select u from UserReg u where u.user_Name=?1 and u.password=?2";
		String jpql2 = "select u from UserReg u where u.phoneNum=?1 and u.password=?2";
		int t = 0, ans = 0,exit=0;
		String uname = "";
		long phn = 0;
		while (t == 0) {
			ans = 0;
			System.out.println("=== Login Account As User ===");
			System.out.println(
					"1.LogIn With UserName\n          OR\n2.LogIn With PhoneNumber\n3.Forget UserName Or Password\n4.Exit");
			int op = sc.nextInt();
			switch (op) {
			case 1:t++;
				ans = 1;
				System.out.println("Enter Your  UserName");
				sc.nextLine();
				String fname = sc.nextLine();
				uname = fname;
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
					UserReg u = getUserName(p);
					if (u!=null) {
						int otp = 1000 + (int) (Math.random() * 9000);
						System.out.println("Your OTP is:- "+otp);
						System.out.println("Enter your OTP to verify Phone Number");
						int uotp = sc.nextInt();
						flage = false;
						if(otp == uotp) {
							System.out.println("Your User Name is:- " + u.getUser_Name());
							System.out.println("Your Password is:- " + u.getPassword());
						}
						else {
							System.err.println("Please Enter Correct OTP TO Verify");
						}
						
					} else {
						System.err.println("Wrong PhoneNumber Please Enter Right PhoneNumber");
					}
				}
				break;
			case 4:exit++;t++;fl++;
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
			q1.setParameter(1, uname);
			q1.setParameter(2, pass);
			try {
			UserReg uveriWithUName = (UserReg) q1.getSingleResult();
			 user_id=uveriWithUName.getId();
			System.out.println("---LogIn Successfull---");
			fl++;
			System.out.println();
			return true;
			}
			catch (NoResultException e) {
				System.err.println("++++Wrong username or phone number or password++++");
				System.out.println();
				System.err.println("Please try again");
			}
		}
		if (ans == 2) {
			Query q2 = manager.createQuery(jpql2);
			q2.setParameter(1, phn);
			q2.setParameter(2, pass);
			try {
			UserReg uveriWithPhn = (UserReg) q2.getSingleResult();
			 user_id=uveriWithPhn.getId();
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

	UserReg getUserName(long phone) {
		String jpql = "select u from UserReg u where u.phoneNum=?1";
		Query q = manager.createQuery(jpql);
		q.setParameter(1, phone);
		try {
			return (UserReg) q.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
		

	}

	public Passenger createPassenger() {
		System.out.println("Enter Your Name");
		sc.nextLine();
		String name = sc.nextLine();
		System.out.println("Enter Your PhoneNumber");
		long phone = sc.nextLong();
		System.out.println("Enter Your Age");
		int age = sc.nextInt();
		System.out.println("Enter Your Gender");
		String gender = sc.next();
		System.out.println("Enter Your Pan");
		String pan = sc.next();
		System.out.println("------Enter your Address Detail-----");
		System.out.println();
		System.out.println("Enter your Street Name");
		String street = sc.next();
		System.out.println("Enter your District Name");
		String dist = sc.next();
		System.out.println("Enter your Country Name");
		String country = sc.next();
		System.out.println("Enter your Pincode Number");
		long pincode = sc.nextLong();
         EntityTransaction t=manager.getTransaction();
		PassengerAddress pad=new PassengerAddress(street,dist,country,pincode);
		Passenger np= new Passenger( name,phone,pan,age,gender,pad);
		   np.setAdd(pad);
		   manager.persist(pad);
		   t.begin();
		   t.commit();
		   return np;
	}

	int fid;
	SeatFId1 sf;
	public void bookTicket() {
		int e = 0;
		System.out.println("For Booking Flight");
		System.out.println();
		int d = 0;
		while (d == 0) {
			System.out.println("Enter Flight id");
			 fid = sc.nextInt();
       Query q=manager.createQuery("select f.lseat from Flights f where f.id=?1");
          q.setParameter(1,fid);
          List<SeatFId1> ls=q.getResultList();
          System.out.println("\nAvailable Tickets In Flight\n");
          System.out.println("++++++++++Available Tickets++++++++\n");
          if(ls.size()>0) {
        	  d++; int ec=0,pc=0,bc=0;
			for (SeatFId1 s : ls) {
				if (s.getSclass().getClass_name().equals("EconomyClass")&&s.getSeat_availability().equals("available")) {
					ec++;
					if(ec==1) {
						System.out.println("Economy Class-");
					}
					System.out.print(s.getSeat_num()+" ");
				}
				if (s.getSclass().getClass_name().equals("PremiumClass")&&s.getSeat_availability().equals("available")) {
					pc++;
					if(pc==1) {
						System.out.println();
						System.out.println("Premium Class-");
					}
					System.out.print(s.getSeat_num()+" ");
				}
				if (s.getSclass().getClass_name().equals("BusinessClass")&&s.getSeat_availability().equals("available")) {
					bc++;
					if(bc==1) {
						System.out.println();
						System.out.println("Business Class-");
					}
					System.out.print(s.getSeat_num()+" ");
				}
					
			}
                    System.out.println();
					System.out.println("\nIn Which Class You Want To Book Your Ticket-");

					System.out.println("\nClass Specification-");
					System.out.println();
					seatClassInfo();
					System.out.println();
					System.out.println("Select Class-");
					System.out.println(
							"1.Economy Calss\n2.Premium Economy Class\n3.Business Class");
					int op = sc.nextInt();
					String sClass=null;
						switch (op) {
						case 1:sClass="EconomyClass";
							break;
						case 2:sClass="PremiumClass";
							break;
						case 3:sClass="BusinessClass";
							break;
						default:System.out.println("Invalid Option");
							break;
						}
						System.out.println("Enter Seat no-");
						String seatn = sc.next();
						
						for(SeatFId1 sd:ls) {
							if(sd.getSeat_num().equals(seatn)) {
								sf=sd;
							}
						}
						Query qf=manager.createQuery("select f from Flights f where f.id=?1");
						qf.setParameter(1,fid);
						int famt=0;
						UserReg ug=null;
						try {
						Flights fs=(Flights) qf.getSingleResult();
						System.out.println("user_id is:- "+user_id);
						 ug=manager.find(UserReg.class,user_id);
						int amtClass=0;
						 if(sClass.equals("EconomyClass")) {
							 amtClass=1;
								famt=fs.getFamount();
						  }else if(sClass.equals("PremiumClass")) {
							  amtClass=2;
							  famt=fs.getFamount()+3000;
						  }
						  else {
							  amtClass=3;
							  famt=fs.getFamount()+10000;
						  }
						 
					    if(sf.getSeat_availability().equals("available")) {
					    	Passenger np=createPassenger();
					    	np.setSeatClass(sClass);
					    	np.setSeatno(seatn);
					        np.setAmt(famt);
					        fs.getPassengers().add(np);
					        System.out.println(ug);
					        System.out.println(ug.getPassengers());
					        ug.getPassengers().add(np);
					        np.setUser(ug);
					        np.setFlight(fs);
					        EntityTransaction t=manager.getTransaction();
					        manager.persist(np);
					        t.begin();
					        t.commit();
					        BookedFlights bf=new BookedFlights(famt,fid,np.getId());
					        ug.getBookedFlights().add(bf);
					        bf.setUser(ug);
					        EntityTransaction t1=manager.getTransaction();
					        manager.persist(bf);
					        sf.setSeat_availability("unavailable");
					        manager.merge(sf);
					        t1.begin();
					        t1.commit();
					        while (e == 0) {
								if(amtClass==1) {
									System.out.println("Pay Amount " +famt+"₹");
								}
								else if(amtClass==2) {
									System.out.println("Pay Amount(general amount + 3000(Premium)):- " +famt+"₹");
								}
								else {
									System.out.println("Pay Amount(general amount + 10000(Business)):- " +famt+"₹");
								}
								int amt = sc.nextInt();
								if (famt== amt) {
									e++;
									System.out.println("Payment Successfully Done!!!!!");
									System.out.println();
									System.out.println("😊😊😊Your Flight is Successfully Booked Enjoy Your journey😊😊😊");
									System.out.println();
									System.out.println("Your flight details");
									System.out.println(fs.getId()+"   "+fs.getFname()+"   "+fs.getForigin()+"("+fs.getStartingtime()+")"+"   "+fs.getFdestination()+"("+fs.getEndingTime()+")"+"   "+fs.getFduration()+"   "+fs.getFamount()+"₹");
									System.out.println();
								} else {
									System.err.println("Please enter correct amount-");
								}
							}
					    }
						}
						catch (NoResultException es) {
							System.err.println("Please enter correct seat number");
						}
				}else {
					System.err.println("Please Enter write Flight Id");
				}
			}
	}

	
	public void getAllFlightDetails() {
		Query q=manager.createQuery("select fs from Flights fs");
		List<Flights> flights=q.getResultList();
		System.out.println(
				"ID\tName\tOrigin(Time)\tDestination(Time)\tTimeDuration\tGeneral_Amount\n----------------------------------------------------------------------------------------\n----------------------------------------------------------------------------------------");
		for (Flights fs : flights) {
			System.out.println(fs.getId()+"\t"+fs.getFname()+"\t"+fs.getForigin()+"("+fs.getStartingtime()+")"+"\t"+fs.getFdestination()+"("+fs.getEndingTime()+")"+"\t"+fs.getFduration()+"\t"+fs.getFamount()+"₹");
			System.out.println();
		}

	}

	
	public void checkBookedFlightDetail() {
		Query qb=manager.createQuery("select ug.bookedFlights from UserReg ug where ug.id=?1");
		qb.setParameter(1,user_id);
		int c=0;
		List<BookedFlights> bookfl=qb.getResultList();
		if (bookfl.size()>0) {
			Flights fl=null;
			Passenger p=null;
			for (BookedFlights bfl:bookfl) {
				c++;
				try {
					fl=manager.find(Flights.class,bfl.getPerticularF_id());
					p=manager.find(Passenger.class,bfl.getPassenger_id());
				} catch (NoResultException e) {
				}
				System.out.println("Passanger Name-"+p.getName()+"(Id-"+p.getId()+")  "+ fl.getId()+"\t"+fl.getFname()+"\t"+fl.getForigin()+"("+fl.getStartingtime()+")"+"\t"+fl.getFdestination()+"("+fl.getEndingTime()+")"+"\t"+fl.getFduration()+"\t"+bfl.getFamount()+"₹");
				System.out.println();
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
			}
			int ct = 0;
			while (ct == 0) {
				System.out.println("1.Cancel Ticket\n2.Back to previous page");
				int opst = sc.nextInt();
				switch (opst) {
				case 1:if(c>0) {
					cancleTicket();
				    }else {
				    	System.out.println("There is no booked flight to cancle");
				    }
					break;
				case 2:
					ct++;
					break;
				default:
					System.out.println("Inavlid option");
					break;
				}
			}

		} else {
			System.err.println("There is no Booked Filght(first book)");
		}
	}

	
	public void UserProfile() {
		Query q=manager.createQuery("select u from UserReg u where u.password=?1");
		q.setParameter(1,pass);
		try {
			UserReg ug=(UserReg) q.getSingleResult();
			System.out.println(
					"UserId:- " + ug.getId() +"\nName:- " + ug.getName() +"\nUserName:- " + ug.getUser_Name() + "\nPhone Number:-" + ug.getPhoneNum()
					+ "\nAge:-" + ug.getAge() + "\nGender:-" + ug.getGender());
			System.out.println();
		}
		catch (NoResultException e) {
			
		}
		

	}


	public void seatClassInfo() {
		System.out.println("==========Economy Class==========");
		System.out.println("Food Options:Yes");
		System.out.println("TV Screen:No");
		System.out.println("WiFi:NO");
		System.out.println("Entertainment: Nothing");
		System.out.println("Seat:Normal Seat");
		System.out.println();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		System.out.println("==========Premium Economy Class==========");
		System.out.println();
		System.out.println("Food Options:Yes(more option)");
		System.out.println("TV Screen:Yes");
		System.out.println("WiFi:Yes");
		System.out.println("Entertainment: Nothing");
		System.out.println("Seat:Normal Seat(with Extra leg space)");
		System.out.println();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		System.out.println("=============Business Class=========");
		System.out.println();
		System.out.println("Food Options:Yes(more option+Delicious+wine)");
		System.out.println("TV Screen:Yes(Big Screen");
		System.out.println("WiFi:Yes");
		System.out.println("Entertainment:Available");
		System.out.println("Security:Greater Privacy");
		System.out.println("Seat:Ecliner seat(very comfertable)");
	}

	public void cancleTicket() {
		System.out.println("Enter passenger id to cancle ticket");
		int pid=sc.nextInt();
		String bf="select bf from BookedFlights bf where bf.passenger_id=?1";
		String sd="select s from SeatFId1 s where s.seat_num=?1 and s.flight.id=?2";

		try {
		Passenger p=manager.find(Passenger.class,pid);
		PassengerAddress padd=manager.find(PassengerAddress.class, p.getpAdd().getId());
		Query q=manager.createQuery(bf);
		q.setParameter(1,p.getId());
		BookedFlights bfd=(BookedFlights) q.getSingleResult();
		Query qs=manager.createQuery(sd);
		qs.setParameter(1,p.getSeatno());
		qs.setParameter(2,p.getFlight().getId());
		SeatFId1 s=(SeatFId1) qs.getSingleResult();
		s.setSeat_availability("available");
		 manager.remove(padd);manager.remove(bfd); manager.remove(p);manager.merge(s);
		 EntityTransaction t=manager.getTransaction();
		 t.begin();t.commit();
		 System.out.println("✔✔Ticket Successfully Canceled✔✔");
		}
		catch(NoResultException e) {
			System.err.println("Enter write passenger id");
		}
	}

	
	public void getAllPassangerDetail() {
		Query qp=manager.createQuery("select p from Passenger p where p.flight.id=?1 ");
          qp.setParameter(1,user_id);
		List<Passenger> ps=qp.getResultList();
		if(ps.size()>0) {
		for(Passenger p:ps) {
			System.out.println("Passenger Name:- "+p.getName());
			System.out.println("Phonenumber:- "+p.getPhoneNum());
			System.out.println("gender:- "+p.getGender());
			System.out.println("Seat number :-"+p.getSeatno());
			System.out.println("Seat Class"+p.getSeatClass());
			System.out.println("Filght id"+p.getFlight().getId());
			System.out.println("---------------------------");
		}
		}else {
			System.err.println("No passengers found with user id:- "+user_id);
		}
		
	}
}

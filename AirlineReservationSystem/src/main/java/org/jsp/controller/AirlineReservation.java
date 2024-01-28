package org.jsp.controller;

import java.util.Scanner;

import org.jsp.dao.AdminDao;
import org.jsp.dao.AirlineDao;
import org.jsp.dao.UserDao;

public class AirlineReservation {
	static AirlineDao adao = new AirlineDao();
	static UserDao udao = new UserDao();
	static AdminDao aAdao = new AdminDao();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		boolean flag1 = true;
		while (flag1) {
			System.out.println("=======Welcome To ✈✈✈ FlightBooking ✈✈✈ App======");
			System.out.println("1.Admin\n2.User\n3.Exit");
			switch (sc.nextInt()) {
			case 1:int la=0;
			     while(la==0) {
				if(aAdao.login()) { 
				int ad = 0;
				while (ad == 0) {
					System.out.println(
							"1.Get All Passengers Details\n2.Get Passengers Details By Id\n3.Add Flight\n4.Remove Flight\n5.Back to previous page");
					switch (sc.nextInt()) {
					case 1:
						aAdao.getAllPassengers();
						break;
					case 2:
						aAdao.getPassengersById();
						break;
					case 3:
						aAdao.addFlight();
						break;
					case 4:
						aAdao.removeFlight();
						break;
					case 5:
						ad++;
						break;
					default:
						System.out.println("Invalid Input");
						break;
					}
				}
			}
				else {
					la++;
				}
			}
				break;
			case 2:
				boolean flage = true;
				while (flage) {

					System.out
							.println("1.Do Registration\n2.Login(If you already registered) \n3.Back to previous page");

					switch (sc.nextInt()) {
					case 1:
						udao.register();
						break;
					case 2:int lg=0;
						while(lg==0) {
						if (adao.login()) {
							lg++;
							boolean flage2 = true;
							while (flage2) {
								System.out.println("========Fly High With Dream Sky========");
								System.out.println();
								System.out.println(
										"1.User Profile\n2.Check All The Flight\n3.Your Booked Flight Details\n4.Get All Passenger Detail\n5.Back To Previous Page");
								switch (sc.nextInt()) {
								case 1:
									adao.UserProfile();
									break;
								case 2:
									int x = 0;
									while (x == 0) {
										System.out.println();
										adao.getAllFlightDetails();
										System.out.println("1.Book Ticket\n2.Back to previous page");
										int op1 = sc.nextInt();
										switch (op1) {
										case 1:
											adao.bookTicket();
											x++;
											break;
										case 2:
											x++;
											break;
										default:
											System.out.println("Invalid input");
										}
									}
									break;
								case 3:
									adao.checkBookedFlightDetail();
									break;
								case 4:
									adao.getAllPassangerDetail();
									break;
								case 5:
									flage2 = false;
									break;
								default:
									System.out.println("Invalid input");
									break;
								}
							}
						}
						else {
							lg++;
						}
					}
						break;
					case 3:
						flage = false;
						break;
					default:
						System.out.println("Invalid Input");
						break;
					}
				}
				break;
			case 3:
				System.exit(0);
				break;
			default:
				System.err.println("Invalid Input");
				break;
			}
		}
	}
}

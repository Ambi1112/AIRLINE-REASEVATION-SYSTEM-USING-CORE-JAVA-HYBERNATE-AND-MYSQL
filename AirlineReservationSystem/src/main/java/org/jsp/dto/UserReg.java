package org.jsp.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user_reg")
public class UserReg {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, unique = true)
	private long phoneNum;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private int age;
	@Column(nullable = false)
	private String gender;
	@Column(nullable = false, unique = true)
	private String user_Name;
	
	@OneToMany(mappedBy = "user")
	private List<BookedFlights> bookedFlights;
	@OneToMany(mappedBy = "user")
	private List<Passenger> passengers;


	public UserReg() {

	}

	public UserReg(String name, long phoneNum, String password, int age, String gender) {
		this.name = name;
		this.phoneNum = phoneNum;
		this.password = password;
		this.age = age;
		this.gender = gender;
	}

	public String getUser_Name() {
		return user_Name;
	}

	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(long phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<BookedFlights> getBookedFlights() {
		return bookedFlights;
	}

	public void setBookedFlights(List<BookedFlights> bookedFlights) {
		this.bookedFlights = bookedFlights;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}
	
	

}

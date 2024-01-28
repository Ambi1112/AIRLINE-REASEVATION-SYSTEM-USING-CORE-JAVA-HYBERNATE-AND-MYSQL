package org.jsp.dto;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="passenger")
public class Passenger {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false,unique = true)
	private long phoneNum;
	@Column(nullable = false,unique = true)
	private String pan;
	@Column(nullable = false)
	private int age;
	@Column(nullable = false)
	private String gender;
	@OneToOne
	private PassengerAddress pAdd;
	private int amt;
	private String seatno;
	private String seatClass;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserReg user;
	@ManyToOne
	@JoinColumn(name="flight_id")
	private Flights flight;

	public Passenger() {

	}

	public Passenger(String name, long phoneNum, String pan, int age, String gender, PassengerAddress pAdd) {
		this.name = name;
		this.phoneNum = phoneNum;
		this.pan = pan;
		this.age = age;
		this.gender = gender;
		this.pAdd = pAdd;
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

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
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

	public PassengerAddress getAdd() {
		return pAdd;
	}

	public void setAdd(PassengerAddress add) {
		this.pAdd = add;
	}

	public int getAmt() {
		return amt;
	}

	public void setAmt(int amt) {
		this.amt = amt;
	}

	public String getSeatno() {
		return seatno;
	}

	public void setSeatno(String seatno) {
		this.seatno = seatno;
	}

	public String getSeatClass() {
		return seatClass;
	}

	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}

	public UserReg getUser() {
		return user;
	}

	public void setUser(UserReg user) {
		this.user = user;
	}

	public PassengerAddress getpAdd() {
		return pAdd;
	}

	public void setpAdd(PassengerAddress pAdd) {
		this.pAdd = pAdd;
	}

	public Flights getFlight() {
		return flight;
	}

	public void setFlight(Flights flight) {
		this.flight = flight;
	}

	
	
	
	
	
	
	

}

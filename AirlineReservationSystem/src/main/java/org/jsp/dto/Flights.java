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
@Table(name = "flights")
public class Flights {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false,name="fname")
	private String fname;
	@Column(nullable = false,name="forigin")
	private String forigin;
	@Column(nullable = false,name="fdestination")
	private String fdestination;
	@Column(nullable = false,name="startingtime")
	private String startingtime;
	@Column(nullable = false,name="endingTime")
	private String endingTime;
	@Column(nullable = false,name="fduration")
	private String fduration;
	@Column(nullable = false,name="")
	private int famount;

	@OneToMany(mappedBy = "flight")
	private List<SeatFId1> lseat;
	@OneToMany(mappedBy = "flight")
	private List<Passenger>  passengers;
	

	public Flights() {

	}
	
	



	public Flights(String fname, String forigin, String fdestination, String startingtime, String endingTime,
			String fduration, int famount) {
		this.fname = fname;
		this.forigin = forigin;
		this.fdestination = fdestination;
		this.startingtime = startingtime;
		this.endingTime = endingTime;
		this.fduration = fduration;
		this.famount = famount;
	}





	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getForigin() {
		return forigin;
	}

	public void setForigin(String forigin) {
		this.forigin = forigin;
	}

	public String getFdestination() {
		return fdestination;
	}

	public void setFdestination(String fdestination) {
		this.fdestination = fdestination;
	}

	public String getStartingtime() {
		return startingtime;
	}

	public void setStartingtime(String startingtime) {
		this.startingtime = startingtime;
	}

	public String getEndingTime() {
		return endingTime;
	}

	public void setEndingTime(String endingTime) {
		this.endingTime = endingTime;
	}

	public String getFduration() {
		return fduration;
	}

	public void setFduration(String fduration) {
		this.fduration = fduration;
	}

	public int getFamount() {
		return famount;
	}

	public void setFamount(int famount) {
		this.famount = famount;
	}

	public List<SeatFId1> getLseat() {
		return lseat;
	}

	public void setLseat(List<SeatFId1> lseat) {
		this.lseat = lseat;
	}





	public List<Passenger> getPassengers() {
		return passengers;
	}





	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}
	
	
	

}

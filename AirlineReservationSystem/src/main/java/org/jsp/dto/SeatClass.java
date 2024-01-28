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
@Table(name = "seatclass")
public class SeatClass {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int class_id;
	@Column(nullable = false)
	private String class_name;

	@OneToMany(mappedBy = "sclass")
	private List<SeatFId1> seats;

	public SeatClass() {

	}

	public SeatClass(int class_id, String class_name) {
		this.class_id = class_id;
		this.class_name = class_name;
		
	}

	public int getClass_id() {
		return class_id;
	}

	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}

	public String getClass_name() {
		return class_name;
	}

	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

	public List<SeatFId1> getSeats() {
		return seats;
	}

	public void setSeats(List<SeatFId1> seats) {
		this.seats = seats;
	}

}

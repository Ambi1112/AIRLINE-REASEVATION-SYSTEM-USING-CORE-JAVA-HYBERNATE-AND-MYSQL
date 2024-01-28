package org.jsp.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "seatfid1")
public class SeatFId1 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seat_id;
	@Column(nullable = false)
	private String seat_num;
	@Column(nullable = false)
	private String seat_availability;
	

	@ManyToOne
	@JoinColumn(name = "flight_id")
	private Flights flight;
	@ManyToOne
	@JoinColumn(name = "sclass_id")
	private SeatClass sclass;

	public SeatFId1() {

	}

	public SeatFId1(String seat_num, String seat_availability) {
		this.seat_num = seat_num;
		this.seat_availability = seat_availability;
	}

	public int getSeat_id() {
		return seat_id;
	}

	public void setSeat_id(int seat_id) {
		this.seat_id = seat_id;
	}

	public String getSeat_num() {
		return seat_num;
	}

	public void setSeat_num(String seat_num) {
		this.seat_num = seat_num;
	}

	public String getSeat_availability() {
		return seat_availability;
	}

	public void setSeat_availability(String seat_availability) {
		this.seat_availability = seat_availability;
	}

	public Flights getFlight() {
		return flight;
	}

	public void setFlight(Flights flight) {
		this.flight = flight;
	}

	public SeatClass getSclass() {
		return sclass;
	}

	public void setSclass(SeatClass sclass) {
		this.sclass = sclass;
	}

	

}

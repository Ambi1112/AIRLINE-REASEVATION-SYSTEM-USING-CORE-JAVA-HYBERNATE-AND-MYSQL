package org.jsp.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PassengerAddress")
public class PassengerAddress {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String street;
	@Column(nullable = false)
	private String dist;
	@Column(nullable = false)
	private String country;
	@Column(nullable = false)
	private long piccod;

	public PassengerAddress() {

	}

	public PassengerAddress(String street, String dist, String country, long piccod) {

		this.street = street;
		this.dist = dist;
		this.country = country;
		this.piccod = piccod;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getDist() {
		return dist;
	}

	public void setDist(String dist) {
		this.dist = dist;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public long getPiccod() {
		return piccod;
	}

	public void setPiccod(long piccod) {
		this.piccod = piccod;
	}

}

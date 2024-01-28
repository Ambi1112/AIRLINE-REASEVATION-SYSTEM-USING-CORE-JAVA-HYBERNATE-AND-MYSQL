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
@Table(name="bookedflights")
public class BookedFlights {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private int famount;
	@Column(nullable = false)
	private int perticularF_id;
	@Column(nullable = false)
	private int passenger_id;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserReg user;
	 
	

	public BookedFlights() {
          
	}

	public BookedFlights(int famount,int perticularF_id,int passenger_id) {
		this.famount = famount;
		this.perticularF_id=perticularF_id;
		this.passenger_id=passenger_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFamount() {
		return famount;
	}

	public void setFamount(int famount) {
		this.famount = famount;
	}

	public UserReg getUser() {
		return user;
	}

	public void setUser(UserReg user) {
		this.user = user;
	}


	public int getPerticularF_id() {
		return perticularF_id;
	}

	public void setPerticularF_id(int perticularF_id) {
		this.perticularF_id = perticularF_id;
	}

	public int getPassenger_id() {
		return passenger_id;
	}

	public void setPassenger_id(int passenger_id) {
		this.passenger_id = passenger_id;
	}

	
	
	
	
	
	

}

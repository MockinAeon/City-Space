package com.wxr.spring.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="enterprisetable")
public class Enterprise {
	@Id
	@GeneratedValue
	@Column(name="enterpriseid", unique=true, nullable=false)
    private long id;
	
	@Column(name="enterprisename")
	private String enterprisename;
	
	@ManyToOne
	@JoinColumn(name="cityname")
	private City cityname;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="enterprisename")
	private Set<Reservation> reservations = new HashSet<Reservation>();
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="enterprisename")
	private Set<Building> buildings = new HashSet<Building>();
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="enterprisename")
	private Set<ReserveAdmin> reserveAdmins = new HashSet<ReserveAdmin>();
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="enterprisename")
	private Set<CreditAdmin> creditAdmins = new HashSet<CreditAdmin>();
	
	public Enterprise() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getEnterprisename() {
		return enterprisename;
	}

	public void setEnterprisename(String enterprisename) {
		this.enterprisename = enterprisename;
	}



	public City getCityname() {
		return cityname;
	}

	public void setCityname(City cityname) {
		this.cityname = cityname;
	}

	public Set<Building> getBuildings() {
		return buildings;
	}

	public void setBuildings(Set<Building> buildings) {
		this.buildings = buildings;
	}

	public Set<ReserveAdmin> getReserveAdmins() {
		return reserveAdmins;
	}

	public void setReserveAdmins(Set<ReserveAdmin> reserveAdmins) {
		this.reserveAdmins = reserveAdmins;
	}

	public Set<CreditAdmin> getCreditAdmins() {
		return creditAdmins;
	}

	public void setCreditAdmins(Set<CreditAdmin> creditAdmins) {
		this.creditAdmins = creditAdmins;
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	@Override
	public String toString() {
		return enterprisename;
	}
}

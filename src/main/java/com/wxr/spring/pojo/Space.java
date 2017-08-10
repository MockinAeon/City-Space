package com.wxr.spring.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "spacetable")
public class Space {

	@Id
	@GeneratedValue
	@Column(name = "spaceid", unique = true, nullable = false)
	private long id;

	@Column(name = "spacename")
	private String spacename;

	@ManyToOne
	@JoinColumn(name = "floorname")
	private Floor floorname;

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "usingSpace", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn(name = "guestName")
	private Guest guestname;

	@Column(name = "spaceStatus")
	private String spaceStatus;

	@Column(name = "canReserve")
	private boolean canReserve;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "spacename")
	private Set<Subscription> subscriptions = new HashSet<Subscription>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "spacename")
	private Set<Reservation> reservations = new HashSet<Reservation>();

	public Space() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSpacename() {
		return spacename;
	}

	public void setSpacename(String spacename) {
		this.spacename = spacename;
	}

	public Floor getFloorname() {
		return floorname;
	}

	public void setFloorname(Floor floorname) {
		this.floorname = floorname;
	}

	public Set<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(Set<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Guest getGuestname() {
		return guestname;
	}

	public void setGuestname(Guest guestname) {
		this.guestname = guestname;
	}

	public String getSpaceStatus() {
		return spaceStatus;
	}

	public void setSpaceStatus(String spaceStatus) {
		this.spaceStatus = spaceStatus;
	}

	public boolean isCanReserve() {
		return canReserve;
	}

	public void setCanReserve(boolean canReserve) {
		this.canReserve = canReserve;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return spacename;
	}

}

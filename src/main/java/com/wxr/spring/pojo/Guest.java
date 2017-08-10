package com.wxr.spring.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="guesttable")
@PrimaryKeyJoinColumn(name="accountid")
public class Guest extends Account{
	
	@Column(name="guestname")
	private String guestname;
   
    @Column(name="credit")
    private int credit=10;
    
    @Column(name="cancelTimes")
    private int cancelTimes=0;
    
    @Column(name="reservationTimes")
    private int reservationTimes=0;
    
    
	@OneToOne
	@JoinColumn(name="spaceId")
    private Space usingSpace;
    
    @OneToMany(orphanRemoval=true,fetch=FetchType.LAZY, mappedBy="accountid")
    private Set<Subscription> subscriptions=new HashSet<Subscription>();
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="accountid")
    private Set<Reservation> reservations = new HashSet<Reservation>();
    
    public Guest() {
		// TODO Auto-generated constructor stub
	}
    



	public String getGuestname() {
		return guestname;
	}




	public void setGuestname(String guestname) {
		this.guestname = guestname;
	}




	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public int getCancelTimes() {
		return cancelTimes;
	}
	public void setCancelTimes(int cancelTimes) {
		this.cancelTimes = cancelTimes;
	}
	public int getReservationTimes() {
		return reservationTimes;
	}
	public void setReservationTimes(int reservationTimes) {
		this.reservationTimes = reservationTimes;
	}
	public Space getUsingSpace() {
		return usingSpace;
	}
	public void setUsingSpace(Space usingSpace) {
		this.usingSpace = usingSpace;
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

    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return guestname;
    }
}

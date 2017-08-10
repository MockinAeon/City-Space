package com.wxr.spring.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table
public class Subscription {
	@Id
	@GeneratedValue
	@Column(name="subscriptionid", unique=true, nullable=false)
	private int id;
	
	@ManyToOne
	@PrimaryKeyJoinColumn(name="accountid")
	private Guest accountid;
	
	@ManyToOne
	@PrimaryKeyJoinColumn(name="spacename")
	private Space spacename;

	public Subscription() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Guest getAccountid() {
		return accountid;
	}
	public void setAccountid(Guest accountid) {
		this.accountid = accountid;
	}
	public Space getSpacename() {
		return spacename;
	}
	public void setSpacename(Space spacename) {
		this.spacename = spacename;
	}

	
	
    
}

package com.wxr.spring.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "creditadmintable")
@PrimaryKeyJoinColumn(name = "accountid")
public class CreditAdmin extends Account {

	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "enterprisename")
	private Enterprise enterprisename;

	public CreditAdmin() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Enterprise getEnterprisename() {
		return enterprisename;
	}

	public void setEnterprisename(Enterprise enterprisename) {
		this.enterprisename = enterprisename;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
}

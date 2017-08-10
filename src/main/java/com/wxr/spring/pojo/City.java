package com.wxr.spring.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="citytable")
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cityid", unique=true, nullable=false)
    private long id;
	
	@Column(name="cityname")
	private String cityname;

	@OneToMany(fetch=FetchType.EAGER, mappedBy="cityname")
	private Set<Enterprise> enterprises = new HashSet<Enterprise>();

	public City() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public Set<Enterprise> getEnterprises() {
		return enterprises;
	}

	public void setEnterprises(Set<Enterprise> enterprises) {
		this.enterprises = enterprises;
	}
	
	@Override
	public String toString() {
		return cityname;
	}
}

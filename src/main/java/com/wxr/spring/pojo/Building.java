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
@Table(name = "buildingtable")
public class Building {

	@Id
	@GeneratedValue
	@Column(name = "buildingid", unique = true, nullable = false)
	private long id;

	@Column(name = "buildingname")
	private String buildingname;

	@ManyToOne
	@JoinColumn(name = "enterprisename")
	private Enterprise enterprisename;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "buildingname")
	private Set<Floor> floors = new HashSet<Floor>();

	public Building() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBuildingname() {
		return buildingname;
	}

	public void setBuildingname(String buildingname) {
		this.buildingname = buildingname;
	}

	public Enterprise getEnterprisename() {
		return enterprisename;
	}

	public void setEnterprisename(Enterprise enterprisename) {
		this.enterprisename = enterprisename;
	}

	public Set<Floor> getFloors() {
		return floors;
	}

	public void setFloors(Set<Floor> floors) {
		this.floors = floors;
	}

	@Override
	public String toString() {
		return buildingname;
	}
}

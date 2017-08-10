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
@Table(name="floortable")
public class Floor {
	
	@Id
	@GeneratedValue
	@Column(name="floorid", unique=true, nullable=false)
    private long id;
	
	@Column(name="floorname")
	private String floorname;
	
	@ManyToOne
	@JoinColumn(name="buildingname")
	private Building buildingname;
	
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="floorname")
	private Set<Space> spaces= new HashSet<Space>();
	
	public Floor() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}



	public String getFloorname() {
		return floorname;
	}

	public void setFloorname(String floorname) {
		this.floorname = floorname;
	}

	public Building getBuildingname() {
		return buildingname;
	}

	public void setBuildingname(Building buildingname) {
		this.buildingname = buildingname;
	}

	public Set<Space> getSpaces() {
		return spaces;
	}

	public void setSpaces(Set<Space> spaces) {
		this.spaces = spaces;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return floorname;
	}
	
}

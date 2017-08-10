package com.wxr.spring.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Reservation {
	@Id
	@GeneratedValue
	@Column(name = "reservationid", unique = true, nullable = false)
	private int id;

	@ManyToOne
	@JoinColumn(name = "accountid")
	private Guest accountid;

	@ManyToOne
	@JoinColumn(name = "spacename")
	private Space spacename;

	@ManyToOne
	@JoinColumn(name = "enterprisename")
	private Enterprise enterprisename;

	@Column(name = "reserveDay")
	private String reserveDay;

	@Column(name = "startTime")
	private String startTime;

	@Column(name = "endTime")
	private String endTime;

	@Column(name = "processPerson")
	private String processPerson;//

	@Column(name = "reservationStatus")
	private String reservationStatus;

	@Column(name = "rejectedReason")
	private String rejectedReason;

	@Column(name = "cancelReason")
	private String cancelReason;

	@Column(name = "advice")
	private String advice;

	public Reservation() {
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

	public Enterprise getEnterprisename() {
		return enterprisename;
	}

	public void setEnterprisename(Enterprise enterprisename) {
		this.enterprisename = enterprisename;
	}



	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getProcessPerson() {
		return processPerson;
	}

	public void setProcessPerson(String processPerson) {
		this.processPerson = processPerson;
	}

	public String getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(String reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	public String getRejectedReason() {
		return rejectedReason;
	}

	public void setRejectedReason(String rejectedReason) {
		this.rejectedReason = rejectedReason;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public String getReserveDay() {
		return reserveDay;
	}

	public void setReserveDay(String reserveDay) {
		this.reserveDay = reserveDay;
	}

}

package com.amdocs.entity;

import java.util.Date;

public class MechanicAppointment {
	
	private int id;
	private String mechanicName;
	private int mobileNumber;
	private String specialization;
	private int customerId;
	private Date date;
	
	public MechanicAppointment() {
		
	}


	public MechanicAppointment(String mechanicName, int mobileNumber, String specialization, int customerId,
			Date date) {
		this.mechanicName = mechanicName;
		this.mobileNumber = mobileNumber;
		this.specialization = specialization;
		this.customerId = customerId;
		this.date = date;
	}


	public MechanicAppointment(int id, String mechanicName, int mobileNumber, String specialization,
			int customerId, Date date) {
		this.id = id;
		this.mechanicName = mechanicName;
		this.mobileNumber = mobileNumber;
		this.specialization = specialization;
		this.customerId = customerId;
		this.date = date;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getMechanicName() {
		return mechanicName;
	}


	public void setMechanicName(String mechanicName) {
		this.mechanicName = mechanicName;
	}


	public int getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public String getSpecialization() {
		return specialization;
	}


	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return "MechanicAppointment [id=" + id + ", mechanicName=" + mechanicName + ", mobileNumber=" + mobileNumber
				+ ", specialization=" + specialization + ", customerId=" + customerId + ", date=" + date + "]";
	}
	
}

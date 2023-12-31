package com.amdocs.entity;

public class Customer {
	
	private int id;
	private String name;
	private int mobileNumber;
	private String address;
	
	public Customer() {
		
	}

	public Customer(int id, String name, int mobileNumber, String address) {
		this.id = id;
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.address = address;
	}

	public Customer(String name, int mobileNumber, String address) {
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", mobileNumber=" + mobileNumber + ", address=" + address
				+ "]";
	}
	
	
	

}

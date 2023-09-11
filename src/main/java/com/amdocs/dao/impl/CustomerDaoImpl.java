package com.amdocs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.amdocs.dao.CustomerDao;
import com.amdocs.entity.Customer;
import com.amdocs.exceptions.CustomerNotFoundException;
import com.amdocs.utilites.DatabaseConnectionUtility;

public class CustomerDaoImpl implements CustomerDao {

	private final static String SELECT_ALL = "SELECT * FROM CUSTOMER";
	private final static String SELECT_BY_ID = "SELECT * FROM CUSTOMER WHERE id=?";
	private final static String INSERT = "INSERT INTO CUSTOMER(name,mobile_number,address) values(?,?,?)";
	private final static String UPDATE = "UPDATE CUSTOMER SET name = ?, mobile_number = ?, address = ? WHERE id = ?";
	private final static String DELETE = "DELETE FROM CUSTOMER WHERE id = ?";
	
	private Connection connection = DatabaseConnectionUtility.getConnection();

	public boolean insert(Customer customer) throws SQLException {
		boolean result=false;
		PreparedStatement stmt = connection.prepareStatement(INSERT);
		stmt.setString(1,customer.getName() );
		stmt.setInt(2, customer.getMobileNumber());
		stmt.setString(3,customer.getAddress() );
		if(stmt.executeUpdate()>0) {
			result = true;
		}
		stmt.close();
		return result;
	}

	public boolean update(Customer customer) throws SQLException, CustomerNotFoundException {
		
		PreparedStatement stmt = connection.prepareStatement(UPDATE);
		stmt.setString(1, customer.getName());
		stmt.setInt(2, customer.getMobileNumber());
		stmt.setString(3, customer.getAddress());
		stmt.setInt(4, customer.getId());
		
		int rowsAffected = stmt.executeUpdate();
		System.out.println("Rows Affected  :" + rowsAffected);
		
		if(rowsAffected >= 1) {
			Customer updatedCustomer = findById(customer.getId());
			System.out.println("Updated Customer is: "+ updatedCustomer.toString());
		}else {
			throw new SQLException();
		}
		return true;
	}

	public boolean delete(int customerId) throws SQLException, CustomerNotFoundException {
		
		PreparedStatement stmt = connection.prepareStatement(DELETE);
		stmt.setInt(1, customerId);
		
		int rowsAffected = stmt.executeUpdate();
		System.out.println("Rows Affected: " + rowsAffected);
		
		if(rowsAffected >= 1) {
			
		}else {
			throw new CustomerNotFoundException("Customer Not Found With Id: " + customerId);
		}
		return true;
	}

	public Customer findById(int customerId) throws SQLException, CustomerNotFoundException {
		Customer customer = null;
		PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID);
		stmt.setInt(1, customerId);
		ResultSet rs = stmt.executeQuery();
		System.out.println("Rs  : "  + rs);
		if(rs.next()) {
			customer = new Customer();
			customer.setId(rs.getInt("id"));
			customer.setName(rs.getString("name"));
			customer.setMobileNumber(rs.getInt("mobile_number"));
			customer.setAddress(rs.getString("address"));			
		}else {
			throw new CustomerNotFoundException("Customer Not Found With Id: " + customerId);
		}
		rs.close();
		stmt.close();
		return customer;
	}

	public List<Customer> findAll() throws SQLException {
		List<Customer> customers = new ArrayList<>();
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(SELECT_ALL);
		while (rs.next()) {
			Customer customer = new Customer();
			customer.setId(rs.getInt("id"));
			customer.setName(rs.getString("name"));
			customer.setMobileNumber(rs.getInt("mobile_number"));
			customer.setAddress(rs.getString("address"));
			// Adding to the list
			customers.add(customer);
		}
		rs.close();
		stmt.close();
		return customers;
	}
	
	
	
	
}

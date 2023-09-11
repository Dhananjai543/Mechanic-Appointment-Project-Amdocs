package com.amdocs.dao;

import java.sql.SQLException;
import java.util.List;

import com.amdocs.entity.Customer;
import com.amdocs.exceptions.CustomerNotFoundException;

public interface CustomerDao {

	boolean insert(Customer customer) throws SQLException;
	
	boolean update(Customer customer) throws SQLException, CustomerNotFoundException;
	
	boolean delete(int customerId) throws SQLException, CustomerNotFoundException;
	
	Customer findById(int customerId) throws SQLException, CustomerNotFoundException;
	
	List<Customer> findAll() throws SQLException;
	
}

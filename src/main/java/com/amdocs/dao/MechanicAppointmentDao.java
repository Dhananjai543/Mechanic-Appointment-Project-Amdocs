package com.amdocs.dao;

import java.sql.SQLException;
import java.util.List;

import com.amdocs.entity.MechanicAppointment;
import com.amdocs.exceptions.CustomerNotFoundException;
import com.amdocs.exceptions.MechanicAppointmentNotFoundException;

public interface MechanicAppointmentDao {
	
	boolean insert(MechanicAppointment mechanicAppointment) throws SQLException;
	
	boolean update(MechanicAppointment mechanicAppointment) throws SQLException, MechanicAppointmentNotFoundException;
	
	boolean delete(int id) throws SQLException, MechanicAppointmentNotFoundException;
	
	MechanicAppointment findById(int id) throws SQLException, MechanicAppointmentNotFoundException;
	
	List<MechanicAppointment> findAll() throws SQLException;
	
	List<MechanicAppointment> findAllByCustomerId(int customerId) throws SQLException, CustomerNotFoundException;

}

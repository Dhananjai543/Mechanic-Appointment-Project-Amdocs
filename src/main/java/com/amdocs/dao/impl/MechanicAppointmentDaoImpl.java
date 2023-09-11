package com.amdocs.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.amdocs.dao.MechanicAppointmentDao;
import com.amdocs.entity.Customer;
import com.amdocs.entity.MechanicAppointment;
import com.amdocs.exceptions.CustomerNotFoundException;
import com.amdocs.exceptions.MechanicAppointmentNotFoundException;
import com.amdocs.utilites.DatabaseConnectionUtility;

public class MechanicAppointmentDaoImpl implements MechanicAppointmentDao{

	private final static String SELECT_ALL = "SELECT * FROM APPOINTMENT";
	private final static String INSERT = "INSERT INTO APPOINTMENT("
			+ "mechanic_name, mobile_number, specialization, customer_id, date) values(?,?,?,?,?)";
	private final static String SELECT_BY_ID = "SELECT * FROM APPOINTMENT WHERE id=?";
	private final static String UPDATE = "UPDATE APPOINTMENT SET mechanic_name = ?, mobile_number = ?, specialization = ?, customer_id = ?, date = ? WHERE id = ?";
	private final static String SELECT_ALL_BY_CUSTOMER_ID = "SELECT * FROM APPOINTMENT WHERE customer_id = ?";
	private final static String DELETE = "DELETE FROM APPOINTMENT WHERE id = ?";
	
	
	private Connection connection = DatabaseConnectionUtility.getConnection();

	@Override
	public boolean insert(MechanicAppointment appointment) throws SQLException {
		
		boolean result=false;
		PreparedStatement stmt = connection.prepareStatement(INSERT);
		stmt.setString(1,appointment.getMechanicName());
		stmt.setInt(2, appointment.getMobileNumber());
		stmt.setString(3,appointment.getSpecialization() );
		stmt.setInt(4, appointment.getCustomerId());
		stmt.setDate(5, new java.sql.Date(appointment.getDate().getTime()));
		
		if(stmt.executeUpdate()>0) {
			result = true;
		}
		stmt.close();
		return result;
	}

	@Override
	public boolean update(MechanicAppointment appointment) throws SQLException, MechanicAppointmentNotFoundException {
		
		PreparedStatement stmt = connection.prepareStatement(UPDATE);
		stmt.setString(1, appointment.getMechanicName());
		stmt.setInt(2, appointment.getMobileNumber());
		stmt.setString(3, appointment.getSpecialization());
		stmt.setInt(4, appointment.getCustomerId());
		stmt.setDate(5, new java.sql.Date(appointment.getDate().getTime()));
		stmt.setInt(6, appointment.getId());
		
		int rowsAffected = stmt.executeUpdate();
		System.out.println("Rows Affected  :" + rowsAffected);
		
		if(rowsAffected >= 1) {
			MechanicAppointment updatedAppointment = findById(appointment.getId());
			System.out.println("Updated Mechanic Appointment is: "+ updatedAppointment.toString());
		}else {
			throw new SQLException();
		}
		return true;
	}

	@Override
	public boolean delete(int id) throws SQLException, MechanicAppointmentNotFoundException {
		PreparedStatement stmt = connection.prepareStatement(DELETE);
		stmt.setInt(1, id);
		
		int rowsAffected = stmt.executeUpdate();
		System.out.println("Rows Affected: " + rowsAffected);
		
		if(rowsAffected >= 1) {
			
		}else {
			throw new MechanicAppointmentNotFoundException("Appointment Not Found With Id: " + id);
		}
		return true;
	}

	@Override
	public MechanicAppointment findById(int appointmentId) throws SQLException, MechanicAppointmentNotFoundException {
		
		MechanicAppointment appointment = null;
		PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID);
		stmt.setInt(1, appointmentId);
		ResultSet rs = stmt.executeQuery();
		System.out.println("Rs  : "  + rs);
		
		if(rs.next()) {
			appointment = new MechanicAppointment();
			appointment.setId(rs.getInt("id"));
			appointment.setMechanicName(rs.getString("mechanic_name"));
			appointment.setMobileNumber(rs.getInt("mobile_number"));
			appointment.setSpecialization(rs.getString("specialization"));
			appointment.setCustomerId(rs.getInt("customer_id"));
			appointment.setDate(new java.util.Date(rs.getDate("date").getTime()));
		}else {
			throw new MechanicAppointmentNotFoundException("Mechanic Appointment Not Found With Id: " + appointmentId);
		}
		rs.close();
		stmt.close();
		return appointment;
		
	}

	@Override
	public List<MechanicAppointment> findAll() throws SQLException {
		List<MechanicAppointment> appointments = new ArrayList<>();
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(SELECT_ALL);
		while (rs.next()) {
			MechanicAppointment appointment = new MechanicAppointment();
			appointment.setId(rs.getInt("id"));
			appointment.setMechanicName(rs.getString("mechanic_name"));
			appointment.setMobileNumber(rs.getInt("mobile_number"));
			appointment.setSpecialization(rs.getString("specialization"));
			appointment.setCustomerId(rs.getInt("customer_id"));
			appointment.setDate(rs.getDate("date"));
			
			appointments.add(appointment);
		}
		rs.close();
		stmt.close();
		return appointments;
	}

	@Override
	public List<MechanicAppointment> findAllByCustomerId(int customerId) throws SQLException, CustomerNotFoundException {
		List<MechanicAppointment> appointments = new ArrayList<>();
		PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_BY_CUSTOMER_ID);
		stmt.setInt(1, customerId);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			MechanicAppointment appointment = new MechanicAppointment();
			appointment.setId(rs.getInt("id"));
			appointment.setMechanicName(rs.getString("mechanic_name"));
			appointment.setMobileNumber(rs.getInt("mobile_number"));
			appointment.setSpecialization(rs.getString("specialization"));
			appointment.setCustomerId(rs.getInt("customer_id"));
			appointment.setDate(rs.getDate("date"));
			
			appointments.add(appointment);
		}
		rs.close();
		stmt.close();
		return appointments;
	}

}

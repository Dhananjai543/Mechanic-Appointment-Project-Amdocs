package com.amdocs;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.amdocs.dao.CustomerDao;
import com.amdocs.dao.MechanicAppointmentDao;
import com.amdocs.dao.impl.CustomerDaoImpl;
import com.amdocs.dao.impl.MechanicAppointmentDaoImpl;
import com.amdocs.entity.Customer;
import com.amdocs.entity.MechanicAppointment;
import com.amdocs.exceptions.CustomerNotFoundException;
import com.amdocs.exceptions.MechanicAppointmentNotFoundException;

public class MechanicAppointmentApp {
	
	private static CustomerDao dao = new CustomerDaoImpl();
	
	private static MechanicAppointmentDao mDao = new MechanicAppointmentDaoImpl();
	
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String arhs[]) {
		
		System.out.println("MECHANIC APPOINTMENT PROJECT");

		while (true) {
			
			System.out.println("\n************Please enter Your choice************");
			System.out.println("1. Customer");
			System.out.println("2. Mechanic");
			System.out.println("0. Stop");
			
			String str = sc.nextLine();
			if(str.trim().isEmpty()) continue;
			
			int ch = Integer.parseInt(str);
			
			
			switch (ch) {
			case 1:
				
				boolean customer_present = true;
				
				while(customer_present) {
					System.out.println("\n************Please enter Your choice************");
					System.out.println("1. Register Customer");
					System.out.println("2. Modify Customer Details");
					System.out.println("3. Delete Customer Record");
					System.out.println("4. View Single Record");
					System.out.println("5. View All Records");
					System.out.println("0. Exit");
					
					int ch2 = Integer.parseInt(sc.nextLine());
					
					switch (ch2) {
					
					case 1:
						try {
							addCustomer();
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
						
					case 2:
						try {
							updateCustomer();
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
						
					case 3: 
						try {
							deleteCustomer();
						} catch (Exception e) { //InputMismatchException
							e.printStackTrace();
						}
						break;
						
					case 4:
						try {
							findCustomerById();
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
						
					case 5:
						findAllCustomers();
						break;
						
					default:
						System.out.println("Exiting");
						customer_present = false;
						break;		
						
					}
				}
				
				break;
				
			case 2:
				
				boolean mechanic_present = true;
				
				while(mechanic_present) {
					
					System.out.println("\n************Please enter Your choice************");
					System.out.println("1. Book an appointment");
					System.out.println("2. Modify appointment details");
					System.out.println("3. Delete an appointment");
					System.out.println("4. View Single Record");
					System.out.println("5. View All Records");
					System.out.println("6. View Customer's Appointment History");
					System.out.println("0. Exit");
					
					
					int ch3 = Integer.parseInt(sc.nextLine());
					
					switch (ch3) {
					
					case 1:
						try {
							bookAppointment();
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
						
					case 2:
						try {
							modifyAppointment();
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
						
					case 3: 
						try {
							deleteAppointment();
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
						
					case 4:
						try {
							findAppointmentById();
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
						
					case 5:
						findAllAppointments();
						break;
						
					case 6:
						try {
							viewAppointmentHistory();
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
						
					default:

						System.out.println("Exiting");
						mechanic_present = false;
						break;		
						
					}
				}

				break;
			
			default:
				System.out.println("PROGRAM TERMINATED");
				System.exit(0);
			}

		}
		
	}
	
	
	private static void deleteAppointment() {
		System.out.println("\nEnter Appointment Id");
		int id = sc.nextInt();
		sc.nextLine();
		
		try {
			mDao.delete(id);
			System.out.println("Appointment record is successfully deleted!!");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (MechanicAppointmentNotFoundException e) {
			e.printStackTrace();
		}
		
	}


	private static void viewAppointmentHistory() {
		
		System.out.println("Please enter customer ID");
		int custID = sc.nextInt();
		sc.nextLine();
		
		try {
			List<MechanicAppointment> appointments = mDao.findAllByCustomerId(custID);
			
			if(appointments.size()==0) {
				System.out.println("No Record Found");
			}else {
				System.out.println("Complete appointment history for given customer is as follows:");
				for(MechanicAppointment a : appointments) {
					System.out.println(a.toString());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (CustomerNotFoundException e1) {
			e1.printStackTrace();
		}
	}


	private static void findAllAppointments() {
		try {
			List<MechanicAppointment> appointments = mDao.findAll();
			
			if(appointments.size()==0) {
				System.out.println("No Record Found");
			}else {
				System.out.println("Complete appointment record is as follows:");
				for(MechanicAppointment a : appointments) {
					System.out.println(a.toString());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	private static void findAppointmentById() {
		
		System.out.println("\nEnter Appointment Id");
		int id = sc.nextInt();
		sc.nextLine();
		
		try {
			MechanicAppointment appointment = mDao.findById(id);
			System.out.println("Requested appointment details are:");
			System.out.println(appointment.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (MechanicAppointmentNotFoundException e) {
			e.printStackTrace();
		}
	}


	private static void modifyAppointment() {
		
		System.out.println("\nEnter Appointment ID");
		int id = sc.nextInt();
		sc.nextLine();
		
		try {
			MechanicAppointment appointment = mDao.findById(id);
			
			System.out.println("\nEnter New Mechanic Name:");
			appointment.setMechanicName(sc.nextLine());
			
			System.out.println("\nEnter New Mobile Number:");
			appointment.setMobileNumber(sc.nextInt());
			sc.nextLine();
			
			System.out.println("\nEnter New Specialization:");
			appointment.setSpecialization(sc.nextLine());
			
			System.out.println("\nEnter New Customer Id");
			int custId = sc.nextInt();
			appointment.setCustomerId(custId);
			sc.nextLine();
			
			try {
				dao.findById(custId);
			} catch (SQLException e2) {
				e2.printStackTrace();
			} catch (CustomerNotFoundException e2) {
				System.out.println("\nCannot Modify Appointment. Reason: Customer Not Found");
				e2.printStackTrace();
				return;
			}
			
			
			System.out.println("\nEnter Date (YYYY/MM/DD)");
			String dateStr = sc.nextLine();  
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Date date;
			try {
				date = sdf.parse(dateStr);
			} catch (ParseException e1) {
				e1.printStackTrace();
				return;
			} catch (InputMismatchException e) {
				e.printStackTrace();
				return;
			}
			
			appointment.setDate(date);
			
			mDao.update(appointment);
			System.out.println("Appointment details are modified successfully!!");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (MechanicAppointmentNotFoundException e) {
			System.out.println("Mechanic Appointment Not Found with given ID");
			e.printStackTrace();
		}
		
	}


	private static void bookAppointment() {
		
		System.out.println("\nEnter Mechanic Name:");
		String name = sc.nextLine();
		
		System.out.println("\nEnter Mobile Number:");
		int mobileNumber = sc.nextInt();
		sc.nextLine();
		
		System.out.println("\nEnter Specialization:");
		String specialization = sc.nextLine();
		
		System.out.println("\nEnter Customer Id");
		int custId = sc.nextInt();
		sc.nextLine();
		
		try {
			dao.findById(custId);
		} catch (SQLException e2) {
			e2.printStackTrace();
		} catch (CustomerNotFoundException e2) {
			System.out.println("\nCannot Make Appointment. Reason: Customer Not Found");
			e2.printStackTrace();
			return;
		}
		
		
		System.out.println("\nEnter Date (YYYY/MM/DD)");
		String dateStr = sc.nextLine();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date date;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e1) {
			e1.printStackTrace();
			return;
		}
		
		MechanicAppointment appointment = new MechanicAppointment(name, mobileNumber, specialization, custId, date);
		
		try {
			if(mDao.insert(appointment)) {
				System.out.println("Your appointment with the mechanic is successfully booked!!");
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
		
	}


	private static void findAllCustomers() {
		try {
			List<Customer> customers = dao.findAll();
			
			if(customers.size()==0) {
				System.out.println("No Record Found");
			}else {
				System.out.println("Complete customer record is as follows:");
				for(Customer c : customers) {
					System.out.println(c.toString());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	private static void deleteCustomer() {
		
		System.out.println("\nEnter Customer Id");
		int id = sc.nextInt();
		sc.nextLine();
		
		try {
			dao.delete(id);
			System.out.println("Customer record is successfully deleted!!");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (CustomerNotFoundException e) {
			e.printStackTrace();
		}
		
	}


	private static void findCustomerById() {
		
		System.out.println("\nEnter Customer Id");
		int id = sc.nextInt();
		sc.nextLine();
		
		try {
			Customer customer = dao.findById(id);
			System.out.println("Requested customer details are:");
			System.out.println(customer.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (CustomerNotFoundException e) {
			e.printStackTrace();
		}
			
	}


	private static void updateCustomer() {
		
		System.out.println("\nEnter Customer ID");
		int id = sc.nextInt();
		sc.nextLine();
		
		try {
			Customer customer = dao.findById(id);
			System.out.println("\nEnter new name:");
			customer.setName(sc.nextLine());
			System.out.println("\nEnter new mobile number:");
			customer.setMobileNumber(Integer.parseInt(sc.nextLine()));
			System.out.println("\nEnter new address:");
			customer.setAddress(sc.nextLine());
			
			dao.update(customer);
			System.out.println("Customer details are modified successfully!!");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (CustomerNotFoundException e) {
			System.out.println("Customer Not Found with given ID");
			e.printStackTrace();
		}
		
	}


	private static void addCustomer() {

		System.out.println("\nEnter Name:");
		String name = sc.nextLine();
		
		System.out.println("\nEnter Mobile Number:");
		int mobileNumber = Integer.parseInt(sc.nextLine());
		
		System.out.println("\nEnter Address:");
		String address = sc.nextLine();
		
		Customer customer = new Customer(name, mobileNumber, address);
		
		try {
			if(dao.insert(customer)) {
				System.out.println("Customer is successfully registered!!");
			}
		} catch (SQLException e) {
			System.err.println(e);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	

}

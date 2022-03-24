package com.mycompany.service;

import java.util.ArrayList;

import com.mycompany.dao.CustomerDao;
import com.mycompany.dao.CustomerDaoImpl;
import com.mycompany.dao.JPACustomerDaoImpl;
import com.mycompany.dto.Customer;

public class CustomerServiceImpl implements CustomerService{

	private CustomerDao customerDao;
	
	public CustomerServiceImpl() {
		customerDao = new JPACustomerDaoImpl();
	}
	
	@Override
	public boolean save(Customer customer){
		if(customer.getCustomerId() <= 9999 && customer.getName().length() <= 100) {
			return customerDao.save(customer);
		}else {
			throw new IllegalArgumentException("Invalid customer data, re try with proper customer data");
		}
	}

	@Override
	public ArrayList<Customer> getAll() {
		ArrayList<Customer> customers = customerDao.getAll();
		for (Customer customer : customers) {
			if(!(customer.getCustomerId() <= 9999 && customer.getName().length() <= 100)) {
				throw new IllegalArgumentException("Invalid customer data, re try with proper customer data");
			}
		}
		return customers;
	}

	@Override
	public boolean update(Customer customer) {
		if(customer.getCustomerId() <= 9999 && customer.getName().length() <= 100) {
			return customerDao.update(customer);
		}else {
			throw new IllegalArgumentException("Invalid customer data, re try with proper customer data");
		}
	}

    @Override
    public boolean delete(int customerId) {
        
        return customerDao.delete(customerId);
    }
    
	@Override
	public Customer get(int customerId) {
		
		return customerDao.get(customerId);
	}
	
}

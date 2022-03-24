package com.mycompany.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.mycompany.dto.Customer;

public class JPACustomerDaoImpl implements CustomerDao{

	@Override
	public boolean save(Customer customer) {
		boolean result = false;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Training");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();//start a transaction
			
			entityManager.persist(customer);
			
			entityManager.getTransaction().commit();//update qiery is executed here
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
		entityManager.close();
		entityManagerFactory.close();
		return result;
	}

	@Override
	public ArrayList<Customer> getAll() {
		ArrayList<Customer> customers = null;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Training");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			entityManager.getTransaction().begin();//start a transaction
			
			Query query = entityManager.createQuery("from Customer");//select * from t_customers
			customers = (ArrayList<Customer>)query.getResultList();
			
			entityManager.getTransaction().commit();//update qiery is executed here
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
		entityManager.close();
		entityManagerFactory.close();
		return customers;
	}

	@Override
	public boolean update(Customer customer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int customerId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Customer get(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.mycompany.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mycompany.dto.Customer;

public class CustomerDaoImpl implements CustomerDao{

	@Override
	public boolean save(Customer customer) {//name = */\\delete \\from \\t_customers

		try {
			String sql = "insert into t_customers values(?,?,?,?,?)";
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customer.getCustomerId());
			preparedStatement.setString(2, customer.getName());
			preparedStatement.setString(3, customer.getPhone());
			preparedStatement.setString(4, customer.getEmail());
			preparedStatement.setInt(5, customer.getAge());
			
			int result = preparedStatement.executeUpdate();
			
			preparedStatement.close();
			connection.close();
			
			return result == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<Customer> getAll() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		try {
			Connection connection = getConnection();
			Statement statement = connection.createStatement();//
			String sql = "select * from t_customers";
			ResultSet resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				Customer customer = new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5));
				customers.add(customer);
			}

			resultSet.close();
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;
	}

	@Override
	public boolean update(Customer customer) {

		try {
			String sql = "update t_customers set cname = ?, phone = ?, email = ?, age = ? where customerId = ?";
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(5, customer.getCustomerId());
			preparedStatement.setString(1, customer.getName());
			preparedStatement.setString(2, customer.getPhone());
			preparedStatement.setString(3, customer.getEmail());
			preparedStatement.setInt(4, customer.getAge());
			
			int result = preparedStatement.executeUpdate();
			
			preparedStatement.close();
			connection.close();
			
			return result == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(int customerId) {

		try {
			String sql = "delete from t_customers where customerId = ?";
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customerId);
			int result = preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
			
			return result == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Customer get(int customerId) {
		Connection connection = null;
		try {
			String sql = "select * from t_customers where customerId = ?";
			connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customerId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			Customer customer = new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5));
			
			preparedStatement.close();
			return customer;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	
	private Connection getConnection() throws SQLException{
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6478363", "sql6478363", "5N4lT5jZ5d");
		return connection;
	}
	
}

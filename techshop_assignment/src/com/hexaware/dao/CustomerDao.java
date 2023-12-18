package com.hexaware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hexaware.entity.*;
import com.hexaware.exception.InvalidDataException;
import com.hexaware.util.MyDBConnection;

public class CustomerDao {
    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement; 
    ResultSet resultSet; 

    public void insertCustomer(Customer cus) throws InvalidDataException {
        try {
            connection = MyDBConnection.getMyDbConnection();
            preparedStatement = connection.prepareStatement("insert into customers values(?,?,?,?,?,?)");
            preparedStatement.setInt(1, cus.getCustomerID());
            preparedStatement.setString(2, cus.getFirstName());
            preparedStatement.setString(3, cus.getLastName());
            preparedStatement.setString(4, cus.getEmail());
            preparedStatement.setString(5, cus.getPhone());
            preparedStatement.setString(6, cus.getAddress());

            int noofrows = preparedStatement.executeUpdate();
            if (noofrows > 0) {
                System.out.println(noofrows + " inserted successfully !!!");
            } else {
                throw new InvalidDataException();
            }

        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public void showCustomer() {
        try {
            connection = MyDBConnection.getMyDbConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM customers"); 
            while (resultSet.next()) {
                System.out.println("CustomerID: " + resultSet.getInt(1));
                System.out.println("FirstName: " + resultSet.getString(2));
                System.out.println("LastName: " + resultSet.getString(3));
                System.out.println("Email: " + resultSet.getString(4));
                System.out.println("Phone: " + resultSet.getString(5));
                System.out.println("Address: " + resultSet.getString(6));
                System.out.println("-------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public void updatecustomerdetails(int cusid, String cusemail, String cusphone) throws InvalidDataException {
        try {
            connection = MyDBConnection.getMyDbConnection();
            preparedStatement = connection.prepareStatement("update customers set email=?,phone=? where customerid=?");
            preparedStatement.setString(1, cusemail);
            preparedStatement.setString(2, cusphone);
            preparedStatement.setInt(3, cusid);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated == 1) {
                System.out.println("customer details updated successfully!");
            } else {
                throw new InvalidDataException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

    }

    public void calculateTotalOrders(Customer cus) {
        try {
            connection = MyDBConnection.getMyDbConnection();
            preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM orders WHERE CustomerID = ?");
            preparedStatement.setInt(1, cus.getCustomerID());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int totalOrders = resultSet.getInt(1);
                System.out.println("Total orders placed by customer: " + totalOrders);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    private void closeResources() {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

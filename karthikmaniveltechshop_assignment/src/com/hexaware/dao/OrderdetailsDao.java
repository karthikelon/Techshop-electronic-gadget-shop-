package com.hexaware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hexaware.util.MyDBConnection;

public class OrderdetailsDao {
    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement; 
    ResultSet resultSet; 

    public void Subtotal(int orderDetailID) {
        double subtotal = 0.0;

        try {
            connection = MyDBConnection.getMyDbConnection();
            preparedStatement = connection.prepareStatement("SELECT od.Quantity * p.Price " +
                                                           "FROM orderdetails od " +
                                                           "JOIN products p ON od.ProductID = p.ProductID " +
                                                           "WHERE od.OrderDetailID = ?");
            preparedStatement.setInt(1, orderDetailID);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                subtotal = resultSet.getDouble(1);
                System.out.println("Subtotal for OrderDetailID " + orderDetailID + ": $" + subtotal);
            } else {
                System.out.println("OrderDetailID " + orderDetailID + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public void getOrderDetailInfo(int orderDetailID) {
        try {
            connection = MyDBConnection.getMyDbConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM orderdetails WHERE OrderDetailID = ?");
            preparedStatement.setInt(1, orderDetailID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Assuming columns in your orderdetails table
                int orderID = resultSet.getInt("OrderID");
                int productID = resultSet.getInt("ProductID");
                int quantity = resultSet.getInt("Quantity");

                System.out.println("OrderDetailID: " + orderDetailID);
                System.out.println("OrderID: " + orderID);
                System.out.println("ProductID: " + productID);
                System.out.println("Quantity: " + quantity);

                // Add more columns as needed
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public void updateQuantity(int orderDetailID, int newQuantity) {
        try {
            connection = MyDBConnection.getMyDbConnection();
            preparedStatement = connection.prepareStatement("UPDATE orderdetails SET Quantity = ? WHERE OrderDetailID = ?");
            preparedStatement.setInt(1, newQuantity);
            preparedStatement.setInt(2, orderDetailID);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated successfully!");
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

	public void addDiscount(int orderID, double discountPercentage) {
		 try {
	            connection = MyDBConnection.getMyDbConnection();
	            
	            // Fetch order details including product price for the specified order ID
	            preparedStatement = connection.prepareStatement(
	                "SELECT orderdetails.Quantity, products.Price " +
	                "FROM orderdetails " +
	                "INNER JOIN products ON orderdetails.ProductID = products.ProductID " +
	                "WHERE orderdetails.OrderID = ?"
	            );
	            preparedStatement.setInt(1, orderID);
	            resultSet = preparedStatement.executeQuery();

	            double totalCostBeforeDiscount = 0.0;
	            double totalCostAfterDiscount = 0.0;

	            while (resultSet.next()) {
	                int quantity = resultSet.getInt("Quantity");
	                double price = resultSet.getDouble("Price");

	                totalCostBeforeDiscount += quantity * price;
	            }

	            // Calculate discount and total cost after discount
	            double discountAmount = totalCostBeforeDiscount * (discountPercentage / 100);
	            totalCostAfterDiscount = totalCostBeforeDiscount - discountAmount;

	            // Display results
	            System.out.println("Total Order Cost Before Discount: $" + totalCostBeforeDiscount);
	            System.out.println("Discount Amount: $" + discountAmount);
	            System.out.println("Total Order Cost After Discount: $" + totalCostAfterDiscount);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            closeResources();
	        }
		
	}
}

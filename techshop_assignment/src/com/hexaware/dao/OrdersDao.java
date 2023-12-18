package com.hexaware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.hexaware.exception.InvalidDataException;
import com.hexaware.util.MyDBConnection;

public class OrdersDao {
    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement; 
    ResultSet resultSet;

    public double calculateTotalAmount(int orderId) throws InvalidDataException {
        double totalAmount = 0;

        try {
            connection = MyDBConnection.getMyDbConnection();
            preparedStatement = connection.prepareStatement(
                    "SELECT SUM(orderdetails.quantity * products.price) AS total_amount FROM orderdetails inner join products on orderdetails.productid=products.productid WHERE orderid = ?");
            preparedStatement.setInt(1, orderId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                totalAmount = resultSet.getDouble(1);
            } else {
                throw new InvalidDataException("Order not found with ID: " + orderId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return totalAmount;
    }

    public void getOrderDetails(int orderId) throws InvalidDataException {
        try {
            connection = MyDBConnection.getMyDbConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM orderdetails WHERE orderid = ?");
            preparedStatement.setInt(1, orderId);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                throw new InvalidDataException("No order details found for Order ID: " + orderId);
            }

            while (resultSet.next()) {
                int productId = resultSet.getInt("productid");
                int quantity = resultSet.getInt("quantity");

                System.out.println("Product ID: " + productId + ", Quantity: " + quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public void checkstatus(int orderid) {
        try {
            connection = MyDBConnection.getMyDbConnection();
            preparedStatement = connection.prepareStatement("select status from orders where orderid=?");
            preparedStatement.setInt(1, orderid);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String status = resultSet.getString("status");
                System.out.println("Order Status: " + status);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

    }

    public void updateOrderStatus(int orderId, String newStatus) throws InvalidDataException {
        try {
            connection = MyDBConnection.getMyDbConnection();
            preparedStatement = connection.prepareStatement("UPDATE orders SET status = ? WHERE orderid = ?");
            preparedStatement.setString(1, newStatus);
            preparedStatement.setInt(2, orderId);

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Order status updated successfully!");
            } else {
                throw new InvalidDataException("No order found with ID: " + orderId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }
    public void getAllOrders() {
    	 try {
    	        connection = MyDBConnection.getMyDbConnection();
    	        statement = connection.createStatement();
    	        resultSet = statement.executeQuery("SELECT * FROM orders");

    	        // Get metadata to dynamically fetch column names
    	        ResultSetMetaData metaData = resultSet.getMetaData();
    	        int columnCount = metaData.getColumnCount();

    	        while (resultSet.next()) {
    	            // Print all columns dynamically
    	            for (int i = 1; i <= columnCount; i++) {
    	                String columnName = metaData.getColumnName(i);
    	                Object value = resultSet.getObject(i);

    	                System.out.println(columnName + ": " + value);
    	            }

    	            System.out.println("--------------");
    	        }
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    } finally {
    	        closeResources();
    	    }
		
	}
    private void closeResources() {
        try {
            if (resultSet != null)
                resultSet.close();
            if (statement != null)
                statement.close();
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	public void cancelOrder(int orderId)throws InvalidDataException {
		try {
            connection = MyDBConnection.getMyDbConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM orders WHERE orderid = ?");
            preparedStatement.setInt(1, orderId);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted <= 0) {
                throw new InvalidDataException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
	}
		
	

	
}

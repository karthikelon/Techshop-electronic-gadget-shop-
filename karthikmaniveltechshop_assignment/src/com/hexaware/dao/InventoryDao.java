package com.hexaware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hexaware.exception.InvalidDataException;
import com.hexaware.util.MyDBConnection;

public class InventoryDao {
    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement; 
    ResultSet resultSet; 

    public int getQuantityInStock(int productID) {
        int quantityInStock = 0;
        try {
            connection = MyDBConnection.getMyDbConnection();
            preparedStatement = connection.prepareStatement("SELECT QuantityInStock FROM inventory WHERE ProductID = ?");
            preparedStatement.setInt(1, productID);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                quantityInStock = resultSet.getInt("QuantityInStock");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return quantityInStock;
    }

    public void modifyinventory(int quantityinstock, int inventoryid) throws InvalidDataException {
        try {
            connection = MyDBConnection.getMyDbConnection();
            preparedStatement = connection.prepareStatement("update inventory set quantityinstock=? where inventoryid=?");
            preparedStatement.setInt(1, quantityinstock);
            preparedStatement.setInt(2, inventoryid);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("inventory stock updated successfully!");
            } else {
                throw new InvalidDataException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public boolean isProductAvailable(int productID, int quantityToCheck) {
        boolean available = false;
        try {
            connection = MyDBConnection.getMyDbConnection();
            preparedStatement = connection.prepareStatement("SELECT QuantityInStock FROM inventory WHERE ProductID = ?");
            preparedStatement.setInt(1, productID);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int quantityInStock = resultSet.getInt("QuantityInStock");
                available = quantityInStock >= quantityToCheck;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return available;
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

	public double getInventoryValue() {
		 double inventoryValue = 0.0;

	        try {
	            connection = MyDBConnection.getMyDbConnection();
	            preparedStatement = connection.prepareStatement("SELECT sum(inventory.QuantityInStock * products.Price) as TotalValue FROM inventory INNER JOIN products ON inventory.ProductID = products.ProductID");

	            resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	                

	            	inventoryValue = resultSet.getDouble("TotalValue");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            closeResources();
	        }

	        return inventoryValue;
	    
		}

	public void displayProductByInventoryID(int inventoryID) {
		try {
	        connection = MyDBConnection.getMyDbConnection();
	        preparedStatement = connection.prepareStatement(
	            "SELECT products.ProductID, products.ProductName, products.Price " +
	            "FROM inventory " +
	            "INNER JOIN products ON inventory.ProductID = products.ProductID " +
	            "WHERE inventory.InventoryID = ?"
	        );
	        preparedStatement.setInt(1, inventoryID);
	        resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            int productID = resultSet.getInt("ProductID");
	            String productName = resultSet.getString("ProductName");
	            double price = resultSet.getDouble("Price");

	            System.out.println("ProductID: " + productID);
	            System.out.println("ProductName: " + productName);
	            System.out.println("Price: " + price);
	        } else {
	            System.out.println("Product not found for InventoryID: " + inventoryID);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        closeResources();
	    }
		
	}

	public void listLowStockProducts(int threshold) {
		try {
            connection = MyDBConnection.getMyDbConnection();
            preparedStatement = connection.prepareStatement(
                "SELECT InventoryID, ProductID, QuantityInStock " +
                "FROM inventory " +
                "WHERE QuantityInStock < ?"
            );
            preparedStatement.setInt(1, threshold);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int inventoryID = resultSet.getInt("InventoryID");
                int productID = resultSet.getInt("ProductID");
                int quantityInStock = resultSet.getInt("QuantityInStock");

                System.out.println("InventoryID: " + inventoryID);
                System.out.println("ProductID: " + productID);
                System.out.println("QuantityInStock: " + quantityInStock);
                System.out.println("--------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
		
	}
}

package com.hexaware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.entity.Product;
import com.hexaware.exception.InvalidDataException;
import com.hexaware.util.MyDBConnection;

public class ProductDao {
    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement; 
    ResultSet resultSet; 

    private List<Product> productList = new ArrayList<>();
    public void insertProduct(Product product) throws InvalidDataException {
        try {
            connection = MyDBConnection.getMyDbConnection();
            preparedStatement = connection.prepareStatement("insert into products values(?,?,?,?,?,?)");
            preparedStatement.setInt(1, product.getProductID());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setInt(4, product.getPrice());
            preparedStatement.setString(5, product.getCategory());
            preparedStatement.setString(6, product.getAvailability());

            int noofrows = preparedStatement.executeUpdate();
            if (noofrows > 0) {
                System.out.println(noofrows + " product inserted successfully !!!");
                productList.add(product);
            } else {
                throw new InvalidDataException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public void showProduct() {
        try {
            connection = MyDBConnection.getMyDbConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from Products");
            
         // Clear existing products in the list
            productList.clear();
            
            while (resultSet.next()) {
            	Product product = new Product();
                product.setProductID(resultSet.getInt(1));
                product.setProductName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setPrice(resultSet.getInt(4));
                product.setCategory(resultSet.getString(5));
                product.setAvailability(resultSet.getString(6));

                // Add the product to the list
                productList.add(product);

                // Print details
                System.out.println("ProductID: " + resultSet.getInt(1));
                System.out.println("ProductName: " + resultSet.getString(2));
                System.out.println("Description: " + resultSet.getString(3));
                System.out.println("Price: " + resultSet.getInt(4));
                System.out.println("Category: " + resultSet.getString(5));
                System.out.println("Availability: " + resultSet.getString(6));
                System.out.println("--------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }
    public List<Product> getProductList() {
        return productList;
    }
    public void updateProductPrice(int productId, int newPrice) throws InvalidDataException {
        try {
            connection = MyDBConnection.getMyDbConnection();
            preparedStatement = connection.prepareStatement("update Products set Price=? where ProductID=?");
            preparedStatement.setInt(1, newPrice);
            preparedStatement.setInt(2, productId);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated == 1) {
                System.out.println("Product price updated successfully!");
            } else {
                System.out.println("Failed to update product price. Product not found.");
                throw new InvalidDataException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public void salesReport() {
        try {
            connection = MyDBConnection.getMyDbConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT products.productid,products.productname, orderdetails.quantity  FROM products inner join orderdetails on products.productid=orderdetails.productid");
            while (resultSet.next()) {
                int productid = resultSet.getInt("productid");
                String productname = resultSet.getString("productname");
                String quantity = resultSet.getString("quantity");
                System.out.println("productid: " + productid);
                System.out.println("productname: " + productname);
                System.out.println("quantity: " + quantity);
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

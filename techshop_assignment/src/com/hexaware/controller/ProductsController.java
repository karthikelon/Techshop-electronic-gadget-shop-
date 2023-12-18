package com.hexaware.controller;

import java.util.Scanner;

import com.hexaware.dao.ProductDao;
import com.hexaware.entity.Product;
import com.hexaware.exception.InvalidDataException;

public class ProductsController implements ProductsInterface {
    Product product;
    Scanner sc = new Scanner(System.in);
    ProductDao productDao = new ProductDao();

    public void addProduct() {
        product = new Product();

        System.out.println("Enter Product ID:");
        int productId = sc.nextInt();
        product.setProductID(productId);

        System.out.println("Enter Product Name:");
        String productName = sc.next();
        product.setProductName(productName);

        System.out.println("Enter Description:");
        String description = sc.next();
        product.setDescription(description);

        System.out.println("Enter Price:");
        int price = sc.nextInt();
        product.setPrice(price);

        System.out.println("Enter Category:");
        String category = sc.next();
        product.setCategory(category);

        System.out.println("Enter Availability (forsale/notforsale):");
        String availability = sc.next();
        product.setAvailability(availability);

        // You should call the method to insert the product into the database
        try {
            productDao.insertProduct(product);
        } catch (InvalidDataException e) {
            e.printStackTrace();
        }

        System.out.println("Product added successfully !!!");
    }

    public void viewProduct() {
        productDao.showProduct();
    }

    public void prdpriceupd() {
        System.out.println("Enter Product ID to update price:");
        int productId = sc.nextInt();

        System.out.println("Enter the new Price:");
        int newPrice = sc.nextInt();

        // You should call the method to update the price of the product in the database
        try {
            productDao.updateProductPrice(productId, newPrice);
        } catch (InvalidDataException e) {
            e.printStackTrace();
        }

        System.out.println("Product price updated successfully !!!");
    }

    public void salesReport() {
        productDao.salesReport();
    }
}

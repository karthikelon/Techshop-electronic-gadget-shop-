package com.hexaware.controller;

import java.util.Scanner;

import com.hexaware.dao.OrdersDao;
import com.hexaware.entity.Order;
import com.hexaware.exception.InvalidDataException;

public class OrdersController implements OrdersInterface {
    Order order; 
    Scanner sc = new Scanner(System.in);
    OrdersDao ordersDao = new OrdersDao(); 

    public void CalculateTotalAmount() {
        System.out.println("Enter Order ID:");
        int orderId = sc.nextInt();

        try {
            double totalAmount = ordersDao.calculateTotalAmount(orderId);
            System.out.println("Total amount for Order ID " + orderId + ": $" + totalAmount);
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        }
    }

    public void GetOrderDetails() {
        System.out.println("Enter Order ID:");
        int orderId = sc.nextInt();

        try {
            ordersDao.getOrderDetails(orderId);
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        }
    }

    public void orderstatus() {
        System.out.println("Enter orderID to check status :");
        int orderid = sc.nextInt();
        ordersDao.checkstatus(orderid);
    }

    public void updateOrderStatus() {
        System.out.println("Enter Order ID:");
        int orderId = sc.nextInt();

        System.out.println("Enter new status (e.g., processing, shipped):");
        sc.nextLine(); // Consume the newline character
        String newStatus = sc.nextLine();

        try {
            ordersDao.updateOrderStatus(orderId, newStatus);
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        }
    }

	
    public void getAllOrders() {
    	ordersDao.getAllOrders();
		
	}

	
	public void cancelOrder() {
		System.out.println("Enter Order ID:");
        int orderId = sc.nextInt();
		try {
			ordersDao.cancelOrder(orderId);
		} catch (InvalidDataException e) {
			
			e.printStackTrace();
		}
		
	}
}

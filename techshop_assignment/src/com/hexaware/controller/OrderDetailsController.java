package com.hexaware.controller;

import java.util.Scanner;

import com.hexaware.entity.OrderDetails;
import com.hexaware.dao.OrderdetailsDao;

public class OrderDetailsController implements OrderdetailsInterface {
    OrderDetails orderDetails; 
    OrderdetailsDao orderdetailsDao = new OrderdetailsDao(); 
    Scanner sc = new Scanner(System.in);

    public void CalculateSubtotal() {
        System.out.println("Enter the orderDetailID");
        int orderDetailID = sc.nextInt();
        orderdetailsDao.Subtotal(orderDetailID);
    }

    public void getOrderDetailInfo() {
        System.out.println("Enter OrderDetailID: ");
        int orderDetailID = sc.nextInt();

        orderdetailsDao.getOrderDetailInfo(orderDetailID);
    }

    public void updateQuantity() {
        System.out.println("Enter OrderDetailID: ");
        int orderDetailID = sc.nextInt();

        System.out.println("Enter new Quantity: ");
        int newQuantity = sc.nextInt();

        orderdetailsDao.updateQuantity(orderDetailID, newQuantity);
    }

	
	public void addDiscount() {
		System.out.println("Enter OrderID: ");
        int orderID = sc.nextInt();

        System.out.println("Enter Discount Percentage: ");
        double discountPercentage = sc.nextDouble();

        orderdetailsDao.addDiscount(orderID, discountPercentage);
		
	}
}

package com.hexaware.controller;

import java.util.Scanner;

import com.hexaware.dao.CustomerDao;
import com.hexaware.entity.Customer;
import com.hexaware.exception.InvalidDataException;

public class CustomerController implements CustomerInterface {
    Customer customer; 
    Scanner sc = new Scanner(System.in);

    CustomerDao customerDao = new CustomerDao(); 

    public void addCustomer() {
        customer = new Customer();
        System.out.println("Enter the customer id");
        int customerID = sc.nextInt();
        customer.setCustomerID(customerID);

        System.out.println("Enter customer first Name");
        String cfname = sc.next();
        customer.setFirstName(cfname);

        System.out.println("Enter customers Last Name:");
        String lastName = sc.next();
        customer.setLastName(lastName);

        System.out.println("Enter Email:");
        String email = sc.next();
        customer.setEmail(email);

        System.out.println("Enter Phone:");
        String phone = sc.next();
        customer.setPhone(phone);

        System.out.println("Enter Address:");
        String address = sc.next();
        customer.setAddress(address);

        // You should call the method to insert the customer into the database
        try {
            customerDao.insertCustomer(customer);
        } catch (InvalidDataException e) {
            e.printStackTrace();
        }

        System.out.println("Customer added successfully !!!");
    }

    public void viewCustomer() {
        customerDao.showCustomer();
    }

    public void custDetailupd() {
        System.out.println("Enter customerid ");
        int cusid = sc.nextInt();
        System.out.println("Enter the new email :");
        String cusemail = sc.next();
        System.out.println("Enter the new phonenumber :");
        String cusphone = sc.next();
        try {
            customerDao.updatecustomerdetails(cusid, cusemail, cusphone);
        } catch (InvalidDataException e) {
            e.printStackTrace();
        }
    }

    public void numbOrder() {
        customer = new Customer();
        System.out.println("Enter customer ID:");
        int customerID = sc.nextInt();
        customer.setCustomerID(customerID);
        customerDao.calculateTotalOrders(customer);
    }
}

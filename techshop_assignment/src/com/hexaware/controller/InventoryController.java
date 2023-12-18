package com.hexaware.controller;

import java.util.Scanner;

import com.hexaware.dao.InventoryDao;
import com.hexaware.entity.Inventory;
import com.hexaware.exception.InvalidDataException;

public class InventoryController implements InventoryInterface {
    Inventory inventory; 
    InventoryDao inventoryDao = new InventoryDao(); 
    Scanner sc = new Scanner(System.in);

    public void GetQuantityInStock() {
        System.out.println("Enter ProductID: ");
        int productID = sc.nextInt();
        System.out.println("Quantity in Stock: " + inventoryDao.getQuantityInStock(productID));
    }

    public void updateInventory() {
        System.out.println("Enter InventoryID to update inventory stock :");
        int inventoryid = sc.nextInt();
        System.out.println("Enter new inventory stock :");
        int quantityinstock = sc.nextInt();
        try {
            inventoryDao.modifyinventory(quantityinstock, inventoryid);
        } catch (InvalidDataException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void IsProductAvailable() {
        System.out.println("Enter ProductID: ");
        int productID = sc.nextInt();

        System.out.println("Enter Quantity to Check: ");
        int quantityToCheck = sc.nextInt();

        boolean available = inventoryDao.isProductAvailable(productID, quantityToCheck);

        displayAvailabilityResult(available);
    }

    private void displayAvailabilityResult(boolean available) {
        if (available) {
            System.out.println("Product is available in the specified quantity.");
        } else {
            System.out.println("Product is not available in the specified quantity.");
        }
    }

	public void getInventoryValue() {
		 double inventoryValue = inventoryDao.getInventoryValue();
	     System.out.println("Total Inventory Value: $" + inventoryValue);
		
	}

	
	public void getProduct() {
		Scanner sc = new Scanner(System.in);
        System.out.println("Enter Inventory ID: ");
        int inventoryID = sc.nextInt();
        inventoryDao.displayProductByInventoryID(inventoryID);
        
		
	}

	
	public void listLowStockProducts() {
		System.out.println("Enter the threshold value: ");
        int threshold = sc.nextInt();
        inventoryDao.listLowStockProducts(threshold);
		
	}
}

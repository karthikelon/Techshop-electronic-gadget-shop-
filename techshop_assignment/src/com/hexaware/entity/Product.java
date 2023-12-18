package com.hexaware.entity;

//note in database the table name is products
public class Product {
    private int productID;
    private String productName;
    private String description;
    private int price;
    private String category;
    private String availability;

    // Constructors, getters, and setters
    public Product() {
    	super();
    }
    public Product(int productID, String productName, String description, int price) {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.price = price;
    }
	
	
	public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}
}


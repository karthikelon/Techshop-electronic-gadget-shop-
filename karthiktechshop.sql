use techshop;

CREATE TABLE customers (
    CustomerID INT PRIMARY KEY,
    CustomerName VARCHAR(255),
    ContactNumber VARCHAR(15),
    Address VARCHAR(255)
);
CREATE TABLE products (
    ProductID INT PRIMARY KEY,
    ProductName VARCHAR(255),
    Description VARCHAR(255),
    Price DOUBLE,
    Category VARCHAR(50),
    Availability VARCHAR(20)
);
CREATE TABLE orders (
    OrderID INT PRIMARY KEY,
    CustomerID INT,
    OrderDate DATE,
    OrderStatus VARCHAR(20),
    FOREIGN KEY (CustomerID) REFERENCES customers(CustomerID)
);
CREATE TABLE orderdetails (
    OrderDetailID INT PRIMARY KEY,
    OrderID INT,
    ProductID INT,
    Quantity INT,
    Subtotal DOUBLE,
    FOREIGN KEY (OrderID) REFERENCES orders(OrderID),
    FOREIGN KEY (ProductID) REFERENCES products(ProductID)
);
CREATE TABLE inventory (
    InventoryID INT PRIMARY KEY,
    ProductID INT,
    QuantityInStock INT,
    FOREIGN KEY (ProductID) REFERENCES products(ProductID)
);
-- Insert sample data into customers table
INSERT INTO customers VALUES (1, 'John Doe', '1234567890', '123 Main St');
INSERT INTO customers VALUES (2, 'Jane Smith', '9876543210', '456 Oak St');

-- Insert sample data into products table
INSERT INTO products VALUES (1, 'Laptop', 'High-performance laptop', 1000.00, 'Electronics', 'In Stock');
INSERT INTO products VALUES (2, 'Smartphone', 'Latest smartphone model', 500.00, 'Electronics', 'In Stock');

-- Insert sample data into orders table
INSERT INTO orders VALUES (1, 1, '2023-01-01', 'Pending');
INSERT INTO orders VALUES (2, 2, '2023-01-02', 'Shipped');

-- Insert sample data into orderdetails table
INSERT INTO orderdetails VALUES (1, 1, 1, 2, 2000.00);
INSERT INTO orderdetails VALUES (2, 1, 2, 1, 500.00);
INSERT INTO orderdetails VALUES (3, 2, 1, 3, 3000.00);

-- Insert sample data into inventory table
INSERT INTO inventory VALUES (1, 1, 10);
INSERT INTO inventory VALUES (2, 2, 20);



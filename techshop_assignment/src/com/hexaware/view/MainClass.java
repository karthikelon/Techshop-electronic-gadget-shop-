package com.hexaware.view;

import com.hexaware.controller.*;
import java.util.Scanner;
import com.hexaware.exception.AuthorizationException;

public class MainClass {
    public static void main(String[] args) {
        System.out.println("Welcome to techshop");
        CustomerInterface customerInterface = new CustomerController();
        ProductsInterface productsInterface = new ProductsController();
        OrdersInterface ordersInterface = new OrdersController();
        OrderdetailsInterface orderdetailsInterface = new OrderDetailsController();
        InventoryInterface inventoryInterface = new InventoryController();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username: ");
        String unm = sc.next();

        System.out.println("Enter password: ");
        String pas = sc.next();
        String ch;
        try {
            if (unm.equals("karthik") && pas.equals("bankai")) {
                do {
                    System.out.println("Enter your choice");
                    System.out.println("1.customer module");
                    System.out.println("2.Product module");
                    System.out.println("3.orders module");
                    System.out.println("4.orderdetails module");
                    System.out.println("5.Inventory module");
                    System.out.println("6.exit");
                    int choice1 = sc.nextInt();
                    switch (choice1) {
                        case 1: {
                            System.out.println("Enter your choice");
                            System.out.println("1. Customer Registration");
                            System.out.println("2. Customer Details");
                            System.out.println("3. Update Customer information");
                            System.out.println("4. Number of order by customers");
                            System.out.println("5. Exit");
                            int choice2 = sc.nextInt();
                            switch (choice2) {
                                case 1: {
                                    customerInterface.addCustomer();
                                    break;
                                }
                                case 2: {
                                    customerInterface.viewCustomer();
                                    break;
                                }
                                case 3: {
                                    customerInterface.custDetailupd();
                                    break;
                                }
                                case 4: {
                                    customerInterface.numbOrder();
                                    break;
                                }
                                case 5: {
                                    break;
                                }
                                default: {
                                    System.out.println("Choose a proper choice");
                                    break;
                                }
                            }

                        }
                        break;
                        case 2: {
                            System.out.println("Enter your choice");
                            System.out.println("1. Product Registration");
                            System.out.println("2. Product Details");
                            System.out.println("3. Product price update");
                            System.out.println("4. product sales report");
                            System.out.println("5. Exit");
                            int choice3 = sc.nextInt();
                            switch (choice3) {
                                case 1: {
                                    productsInterface.addProduct();
                                    break;
                                }
                                case 2: {
                                    productsInterface.viewProduct();
                                    break;
                                }
                                case 3: {
                                    productsInterface.prdpriceupd();
                                    break;
                                }
                                case 4: {
                                    productsInterface.salesReport();
                                    break;
                                }

                                case 5: {
                                    break;
                                }
                                default: {
                                    System.out.println("Choose a proper choice");
                                    break;
                                }

                            }
                        }
                        break;
                        case 3: {
                            System.out.println("Enter your choice");
                            System.out.println("1. Calculate the total amount of the order. ");
                            System.out.println("2. Get Order Details");
                            System.out.println("3. Check Order Status ");
                            System.out.println("4. Update Order Status ");
                            System.out.println("5. Get all orders ");
                            System.out.println("6. Cancel Order");
                            System.out.println("7. Exit");
                            int choice4 = sc.nextInt();
                            switch (choice4) {
                                case 1: {
                                    ordersInterface.CalculateTotalAmount();
                                    break;
                                }
                                case 2: {
                                    ordersInterface.GetOrderDetails();
                                    break;
                                }
                                case 3: {

                                    ordersInterface.orderstatus();
                                    break;
                                }
                                case 4: {
                                    ordersInterface.updateOrderStatus();
                                    break;
                                }
                                case 5: {
                                	ordersInterface.getAllOrders();
                                    break;
                                }
                                case 6: {
                                     ordersInterface.cancelOrder();
                                    break;
                                }
                                case 7: {
                                    break;
                                }
                                default: {
                                    System.out.println("Choose a proper choice");
                                    break;
                                }

                            }
                        }
                        break;
                        case 4: {
                            System.out.println("Enter your choice");
                            System.out.println("1. Calculate the subtotal for this order detail");
                            System.out.println("2. Get Order Detail Info");
                            System.out.println("3. Update Quantity ");
                            System.out.println("4. Add Discount");
                            System.out.println("5. Exit");
                            int choice5 = sc.nextInt();
                            switch (choice5) {
                                case 1: {
                                    orderdetailsInterface.CalculateSubtotal();
                                    break;
                                }
                                case 2: {
                                    orderdetailsInterface.getOrderDetailInfo();
                                    break;
                                }
                                case 3: {
                                    orderdetailsInterface.updateQuantity();
                                    break;
                                }
                                case 4: {
                                     orderdetailsInterface.addDiscount();
                                    break;
                                }
                                case 5: {
                                    break;
                                }
                                default: {
                                    System.out.println("Choose a proper choice");
                                    break;
                                }

                            }
                        }
                        break;
                        case 5: {
                            System.out.println("Enter your choice");
                            System.out.println("1. Get Quantity In Stock ");
                            System.out.println("2. Update Inventory");
                            System.out.println("3. Specific quantity of a  Product is Available");
                            System.out.println("4. Get Inventory Value");
                            System.out.println("5. Get Product");
                            System.out.println("6. List Low Stock Products");                  
                            System.out.println("7. Exit");
                            int choice5 = sc.nextInt();
                            switch (choice5) {
                                case 1: {
                                    inventoryInterface.GetQuantityInStock();
                                    break;
                                }
                                case 2: {
                                    inventoryInterface.updateInventory();
                                    break;
                                }
                                case 3: {
                                    inventoryInterface.IsProductAvailable();
                                    break;
                                }
                                case 4: {

                                     inventoryInterface.getInventoryValue();
                                    break;
                                }
                                case 5: {
                                     inventoryInterface.getProduct();
                                    break;
                                }
                                case 6: {
                                     inventoryInterface.listLowStockProducts();
                                    break;
                                }
                                
                                case 7: {
                                    break;
                                }
                                default: {
                                    System.out.println("Choose a proper choice");
                                    break;
                                }

                            }
                        }
                        break;
                        case 6: {
                            break;
                        }
                        
                        default: {
                            System.out.println("Choose a proper choice");
                            break;
                        }

                    }
                    System.out.println("Do you want to continue? Y | y");
                    ch = sc.next();
                } while (ch.equals("Y") || ch.equals("y"));
            } else {
                throw new AuthorizationException();
            }
            System.out.println("Thank you for using tech shop");
        } catch (AuthorizationException e) {
            System.out.println(e);
        }
    }
}

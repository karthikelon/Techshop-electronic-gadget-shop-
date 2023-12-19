package com.hexaware.exception;

public class InsufficientStockException extends Exception {
	public InsufficientStockException() {
		System.out.println("stock not available ");
	}
	public String toString(){
		return "stock not available ";
		
	}
}

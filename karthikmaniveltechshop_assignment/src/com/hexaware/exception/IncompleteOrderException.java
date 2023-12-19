package com.hexaware.exception;

public class IncompleteOrderException extends Exception {
	public IncompleteOrderException() {
		System.out.println("order detail lacks a product reference.");
	}
	public String toString(){
		return "order detail lacks a product reference.";
		
	}
	
}

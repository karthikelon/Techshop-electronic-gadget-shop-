package com.hexaware.exception;

public class PaymentFailedException extends Exception {
	public PaymentFailedException() {
		System.out.println("payment is declined.");
	}
	public String toString(){
		return "payment is declined.";
		
	}
}

package com.hexaware.exception;

public class ConcurrencyException extends Exception  {
	public ConcurrencyException() {
		System.out.println("multiple users simultaneously attempt to update the same order");
	}
	public String toString(){
		return "Multiple users simultaneously attempt to update the same order";
		
	}

}

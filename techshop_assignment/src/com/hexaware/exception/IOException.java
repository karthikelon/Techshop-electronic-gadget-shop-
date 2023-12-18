package com.hexaware.exception;

public class IOException extends Exception  {
	public IOException() {
		System.out.println("an error occurs during data persistence");
	}
	public String toString(){
		return "an error occurs during data persistence";
		
	}
}

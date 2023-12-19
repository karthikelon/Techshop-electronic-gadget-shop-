package com.hexaware.exception;

public class InvalidDataException extends Exception {
	public InvalidDataException() {
		System.out.println("enter valid data");
	}
	public InvalidDataException(String string) {
		System.out.println("enter valid data");
	}
	public String toString(){
		return "given data not valid";
		
	}
}

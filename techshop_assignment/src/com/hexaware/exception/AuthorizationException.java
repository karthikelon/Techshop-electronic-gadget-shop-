package com.hexaware.exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class AuthorizationException  extends Exception{

	public void AuthorizationException(){
		System.out.println("User Not Found ");
	}

	public String toString(){
		return "incorrect credentials";
		
	}
}

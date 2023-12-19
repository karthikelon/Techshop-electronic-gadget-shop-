package com.hexaware.exception;

public class SqlException extends Exception  {
	public SqlException() {
		System.out.println("When executing a SQL query and the database is offline.");
	}
	public String toString(){
		return "When executing a SQL query and the database is offline.";
		
	}

}

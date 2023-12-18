package com.hexaware.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyDBConnection {

	static Connection con;
	public static Connection getMyDbConnection() {
		String fileName="db.properties";
		Properties props = new Properties();
		FileInputStream fis =null;
		
		try {
			fis= new FileInputStream(fileName);
			props.load(fis);
			
			String url = props.getProperty("db.url");
			String un =props.getProperty("db.username");
			String pwd =props.getProperty("db.password");
			//System.out.println(url+un+pwd);
		con=DriverManager.getConnection(url,un,pwd);
		} catch (SQLException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return con;
	}
	
	public static void main(String[] args) {
		//System.out.println(getMyDbConnection());

	}

}
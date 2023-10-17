package com.Arya.Moviesfinder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBconnection {
	private Connection connection=null;
	public Connection dbconnection()throws SQLException
	{
		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/movies","root","chikku@1910");
		return connection;
	}
	
	}




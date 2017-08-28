package com.orilore.daos;
import java.sql.*;
import java.util.*;
import java.io.*;
public class DBUtil{
	public Connection getConnection(){
		try{
			InputStream is = this.getClass().getResourceAsStream("Config.properties");
			Properties prop = new Properties();
			prop.load(is);
			Class.forName(prop.getProperty("driver"));
			Connection conn = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("username"),prop.getProperty("password"));
			is.close();
			return conn;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
}
package com.system.fileSystem.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


public class ConnectionUtil {
	
	Connection conn;
	private String driverClassName = "com.mysql.cj.jdbc.Driver";
	private String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/model?profileSQL=true";
	private String jdbcUserName = "user";
	private String jdbcPW = "P@ssw0rd";

	public ConnectionUtil() throws FileNotFoundException, IOException, SQLException, ClassNotFoundException {
	
		Class.forName(driverClassName);
//		Properties props = new Properties();
//		props.load(new FileInputStream(".\\config\\jdbc.properties"));
//		props.load(new FileInputStream("D:\\_JAVA\\eclipse_test_worlspace\\SourceFile2MSDB\\conf\\jdbc.properties"));
//		String url = props.getProperty("jdbc.url");
//		String user = props.getProperty("jdbc.user");
//		String password = props.getProperty("jdbc.password");
//		String url = jdbcUrl + dbName;
		
		conn = DriverManager.getConnection(jdbcUrl, jdbcUserName, jdbcPW);

	}

	public Connection getConnection() {
		return conn;
	}

}

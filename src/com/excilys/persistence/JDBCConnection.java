package com.excilys.persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;

public final class JDBCConnection implements AutoCloseable {

    private Connection connection;
    private static volatile JDBCConnection instance = null;
    private final Properties properties = new Properties();
    
	private JDBCConnection() {
	    setConnection(null);
	    try {
	    	InputStream inputStream = new FileInputStream("ressources/config.properties");
	    	properties.load(inputStream);
	    	String url = properties.getProperty("url");
	    	String user = properties.getProperty("user");
	    	String pwd = properties.getProperty("pwd");
			connection = (Connection) DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public final static JDBCConnection getInstance() {
        if (JDBCConnection.instance == null) {
           synchronized(JDBCConnection.class) {
             if (JDBCConnection.instance == null) {
            	 JDBCConnection.instance = new JDBCConnection();
             }
           }
        }
        return JDBCConnection.instance;
    }

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connexion) {
		this.connection = connexion;
	}
	
	@Override
	public void close() throws Exception {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

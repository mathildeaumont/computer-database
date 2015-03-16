package persistence;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public final class JDBCConnection implements AutoCloseable {

	private static String url = "jdbc:mysql://localhost/computer-database-db?zeroDateTimeBehavior=convertToNull";
    private static String user = "root";
    private static String pwd ="root";
    private Connection connection;
    private static volatile JDBCConnection instance = null;
    
	private JDBCConnection() {
	    setConnection(null);
	    try {
			connection = (Connection) DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
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

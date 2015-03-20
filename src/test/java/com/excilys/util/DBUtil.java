package com.excilys.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.dbunit.operation.DatabaseOperation;

public class DBUtil {

	public static IDatabaseTester databaseTester;
	public static String jdbcDriver;
	public static String jdbcUrl;
	public static String user;
	public static String password;
	static {
		final Properties properties = new Properties();
		try (final InputStream is = new FileInputStream("ressources/config-test.properties")) {
			properties.load(is);
			jdbcDriver = properties.getProperty("driver");
			jdbcUrl = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public static void cleanlyInsert(IDataSet dataSet) throws Exception {
		databaseTester = new JdbcDatabaseTester(
				jdbcDriver, jdbcUrl, user, password);
		databaseTester.getConnection().getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
                new H2DataTypeFactory());
		/*databaseTester.getConnection().getConfig().setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER,
                new H2MetadataHandler());*/
		databaseTester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
		databaseTester.setDataSet(dataSet);
		databaseTester.onSetup();
	}

	/**
	 * Execute the sql file.
	 * @param file File to execute
	 * @param connection Connection
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void executeSqlFile(String file, Connection connection) throws IOException, SQLException {
		final InputStream is =  new FileInputStream(file);
		final BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		final StringBuilder sb = new StringBuilder();
		String str = null;
		while ((str = br.readLine()) != null) {
			sb.append(str + "\n ");
		}
		try (final Statement stmt = connection.createStatement()) {
			stmt.execute(sb.toString());
		}
		br.close();
	}

	public static Connection getConnection() throws IOException, SQLException {
		final Properties properties = new Properties();
		try (final InputStream is = new FileInputStream("ressources/config-test.properties")) {
			properties.load(is);
			final String url = properties.getProperty("url");
			return DriverManager.getConnection(url, properties);
		}
	}
}
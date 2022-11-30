package JDBC_Util_Package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCUtil {
	
	private JDBCUtil() {}

	public static Connection getJdbcConnection() throws SQLException {
		
		Connection connection = null;

		String url = "jdbc:mysql://localhost:3306/javaconnectiondb";
		String username = "root";
		String password = "root";

		connection = DriverManager.getConnection(url, username, password);

		if (connection != null)
			return connection;

		return connection;
	}

	public static void closeConnection(ResultSet resultSet, Statement statement, Connection connection) throws SQLException {

		if (resultSet != null)
		{
			resultSet.close();
		}
		if (statement != null)
		{
			statement.close();
		}
		if (connection != null)
		{
			connection.close();
		}
	}

}

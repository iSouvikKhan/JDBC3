package Date;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import JDBC_Util_Package.JDBCUtil;

public class ReadAll_using_Statement {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	
	public void getReadAll() throws SQLException {
		
		String sqlSelectQuery = "select id, name,address,gender,dob,doj,dom from datedata";
		
		try
		{
			connection = JDBCUtil.getJdbcConnection();
			if (connection != null)
				statement = connection.createStatement();
			
			if(statement != null)
			{
				resultSet = statement.executeQuery(sqlSelectQuery);
				if(resultSet != null)
				{
					System.out.println("ID\tNAME\t\tADDRESS\t\tGENDER\t\tDOB\t\tDOJ\t\tDOM");
					while (resultSet.next())
					{
						String userID = resultSet.getString("id");
						String userName = resultSet.getString("name");
						String userAddress = resultSet.getString("address");
						String userGender = resultSet.getString("gender");
						// -----------
						java.sql.Date userDOB = resultSet.getDate("dob");
						java.sql.Date userDOJ = resultSet.getDate("doj");
						java.sql.Date userDOM = resultSet.getDate("dom");
						// -----------
						// formatting the output as the user choice (based on locale) , SQL date is known as locale
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
						String dateDOB = sdf.format(userDOB);
						String dateDOJ = sdf.format(userDOJ);
						String dateDOM = sdf.format(userDOM);
						// -----------
						System.out.println(userID+"\t"+userName+"\t\t"+userAddress+"\t\t"+userGender+"\t\t"+dateDOB+"\t"+dateDOJ+"\t"+dateDOM);
					}
				}
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.closeConnection(resultSet, statement, connection);
		}
	}
}


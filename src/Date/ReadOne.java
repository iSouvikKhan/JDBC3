package Date;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import JDBC_Util_Package.JDBCUtil;

public class ReadOne {
	
	private Connection connection = null;
	private PreparedStatement pstmt = null;
	private ResultSet resultSet = null;

	public void getReadOne() throws Exception {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter the name :");
		String name = scanner.next();

		String sqlSelectQuery = "select id,name,address,gender,dob,doj,dom from datedata where name = ?";

		try
		{
			connection = JDBCUtil.getJdbcConnection();
			if (connection != null)
				pstmt = connection.prepareStatement(sqlSelectQuery);
			
			if (pstmt != null)
			{
				pstmt.setString(1, name);
				resultSet = pstmt.executeQuery();
			}
			if (resultSet != null)
			{
				if (resultSet.next())
				{
					String userID = resultSet.getString(1);
					String userName = resultSet.getString(2);
					String userAddress = resultSet.getString(3);
					String userGender = resultSet.getString(4);
					// -----------
					java.sql.Date userDOB = resultSet.getDate(5);
					java.sql.Date userDOJ = resultSet.getDate(6);
					java.sql.Date userDOM = resultSet.getDate(7);
					// -----------
					System.out.println("SQLDate present in database is :: "+userDOB);
					System.out.println("SQLDate present in database is :: "+userDOJ);
					System.out.println("SQLDate present in database is :: "+userDOM);
					// -----------
					// formatting the output as the user choice (based on locale) , SQL date is known as locale
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					String dateDOB = sdf.format(userDOB);
					String dateDOJ = sdf.format(userDOJ);
					String dateDOM = sdf.format(userDOM);
					// -----------
					System.out.println("ID\tNAME\t\tADDRESS\t\tGENDER\t\tDOB\t\tDOJ\t\tDOM");
					System.out.println(userID+"\t"+userName+"\t\t"+userAddress+"\t\t"+userGender+"\t\t"+dateDOB+"\t"+dateDOJ+"\t"+dateDOM);
				}
				else
				{
					System.out.println("Record not available for the given name: " + name);
				}
			}
		}
		catch (SQLException se)
		{
			se.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			JDBCUtil.closeConnection(resultSet, pstmt, connection);
		}
	}
}

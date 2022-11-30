package Date;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import JDBC_Util_Package.JDBCUtil;

public class Insert {
	
	private Connection connection = null;
	private PreparedStatement pstmt = null;

	public void doInsert() throws Exception
	{
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter the NAME :");
		String name = scanner.nextLine();
		
		System.out.println("Enter the ADDRESS :");
		String address = scanner.nextLine();
		
		System.out.println("Enter the GENDER :");
		String gender = scanner.nextLine();
		
		// -----------

		System.out.println("Enter the DOB in (dd-mm-yyyy) format :");
		String dob = scanner.next();
		
		System.out.println("Enter the DOJ in (mm-dd-yyyy) format :");
		String doj = scanner.next();
		
		System.out.println("Enter the DOM in (yyyy-mm-dd) format :");
		String dom = scanner.next();
		
		// --------

		SimpleDateFormat sdfDOB = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date uDateDOB = sdfDOB.parse(dob);
		
		SimpleDateFormat sdfDOJ = new SimpleDateFormat("MM-dd-yyyy");
		java.util.Date uDateDOJ = sdfDOJ.parse(doj);
		
		// -----------

		long timeDOB = uDateDOB.getTime();
		java.sql.Date sqlDateDOB = new java.sql.Date(timeDOB);
		
		long timeDOJ = uDateDOJ.getTime();
		java.sql.Date sqlDateDOJ = new java.sql.Date(timeDOJ);
		
		java.sql.Date sqlDateDOM = java.sql.Date.valueOf(dom);
		
		// ------------

		System.out.println("String DOB is :: " + dob);
		System.out.println("String DOJ is :: " + doj);
		System.out.println("String DOM is :: " + dom);
		System.out.println("-----");
		System.out.println("Util DOB date is :: " + uDateDOB);
		System.out.println("Util DOJ date is :: " + uDateDOJ);
		System.out.println("-----");
		System.out.println("SQL DOB date is :: " + sqlDateDOB);
		System.out.println("SQL DOJ date is :: " + sqlDateDOJ);
		System.out.println("SQL DOM date is :: " + sqlDateDOM);
		
		// ------------

		String sqlInsertQuery = "insert into datedata(name,address,gender,dob,doj,dom) values (?,?,?,?,?,?)";

		try
		{
			connection = JDBCUtil.getJdbcConnection();

			if (connection != null)
				pstmt = connection.prepareStatement(sqlInsertQuery);

			if (pstmt != null)
			{
				pstmt.setString(1, name);
				pstmt.setString(2, address);
				pstmt.setString(3, gender);
				pstmt.setDate(4, sqlDateDOB);
				pstmt.setDate(5, sqlDateDOJ);
				pstmt.setDate(6, sqlDateDOM);

				int rowAffected = pstmt.executeUpdate();

				System.out.println("No of rows affected is:: " + rowAffected);
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
			JDBCUtil.closeConnection(null, pstmt, connection);
		}
	}
}

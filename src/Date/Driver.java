package Date;

import java.util.InputMismatchException;
import java.util.Scanner;



public class Driver {
	static Scanner scn = new Scanner(System.in);
	public static void main(String[] args) throws Exception {
		int val = 0;
		do
		{
			System.out.println("  ---  INSTRUCTIONS  ---  ");
			System.out.println("Which operation you wish to perform ??");
			System.out.println("to READ the entire table, type : 1");
			System.out.println("to READ one column through NAME, type : 2");
			System.out.println("to INSERT a column into table, type : 3");
//			System.out.println("to UPDATE column of the table, type : 4");
//			System.out.println("to DELETE column of the table, type : 5");
			System.out.println("Press enter...\n");
			val = scn.nextInt();
			switch(val)
			{
				case 1:
					new ReadAll_using_Statement().getReadAll();
					break;
				case 2:
					new ReadAll_using_Statement().getReadAll();
					System.out.println();
					new ReadOne().getReadOne();
					break;
				case 3:
					new Insert().doInsert();
					System.out.println();
					new ReadAll_using_Statement().getReadAll();
					break;
//				case 4:
//					new ReadAll_using_Statement().getReadAll();
//					System.out.println();
//					new Update().doUpdate();
//					System.out.println();
//					new ReadAll_using_Statement().getReadAll();
//					break;
//				case 5:
//					new ReadAll_using_Statement().getReadAll();
//					System.out.println();
//					new Delete().doDelete();
//					System.out.println();
//					new ReadAll_using_Statement().getReadAll();
//					break;
				default:
					System.out.println("Invalid entry, read instructions properly !!");
					break;
			}
			System.out.println("\nTo perform more such operations type : 7");
			System.out.println("To exit the application type : 9");
			System.out.println("Press enter...\n");
			val = checkEntry();
		}
		while(val != 9);
		System.out.println("Thanks for using the application. Have a nice day !!");
	}
	
	public static int checkEntry() {
		boolean flag = true;
		int val = 0;
		do
		{
			try
			{
				val = scn.nextInt();
				flag = false;
			}
			catch(InputMismatchException ie)
			{
				flag = true;
				System.out.println("You are supposed to enter any value between 7 or 9");
				System.out.println("Other than this will not be entertained...");
				System.out.println("Enter again !!");
				scn.nextLine();
			}
			catch(Exception e)
			{
				System.out.println("Enter again...");
			}
		} while(flag);
		if(val==7 || val==9)
			return val;
		else
		{
			System.out.println("Invalid entry !!!");
			System.out.println("To perform more such operations type : 7");
			System.out.println("To exit the application type : 9");
			System.out.println("Press enter...\n");
			return checkEntry();
		}
	}
}

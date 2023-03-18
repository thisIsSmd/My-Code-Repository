package Assignment_2;

import java.sql.*;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Assignment_2 
{		
	public static void main(String[] args) 
	{			
		try
		{
			    Connection conn = getConnection();
			    int menuNo = 0;
			    
				do
				{
					 System.out.println("\n*****Menu****");
					 System.out.println("1. Want to view the data in the table");
					 System.out.println("2. Want to insert the data in the student table");
					 System.out.println("3. Want to change the advisor for the student");
					 System.out.println("4. Want to see the list of students and advisors");
					 System.out.println("5. Want to establish the relationship between students and advisors");
					 System.out.println("6. Want to delete the student data");
					 System.out.println("7. Exit from the Menu\n");
					 
					 System.out.println("Please enter your choice");
					 
					 Scanner scanner = new Scanner(System.in);
					 menuNo = scanner.nextInt();
						
					 switch(menuNo)
					 {
						  case 1: System.out.println("Enter the table name to get the contents");			  		 
						  		  GetContent getContent = new GetContent();
						  		  getContent.getContent(conn);
						  		  break;
						  		  
						  case 2: System.out.println("You have selected to insert the data in the student table");
						  		  InsertData insertData = new InsertData();
						  		  insertData.insertData(conn);
							  	  break;
							  	  
						  case 3: System.out.println("You want to change the advisor for the student\n");
							  	  ModifyData modifyData = new ModifyData();
							  	  modifyData.modifyData(conn);
						  		  break;
						  
						  case 4: System.out.println("Displaying the list of students and advisors");
							   	  DisplayStudentAndAdvisors display = new DisplayStudentAndAdvisors();
							   	  display.displayStudentAndAdvisors(conn);
						  		  break;
						  		  
						  case 5: System.out.println("You want to establish a new Student-Advisor relationship");
						  		  EstablishRelationship estRel = new EstablishRelationship();
						  		  estRel.establishRelationship(conn);
						  		  break;
							  
						  case 6: System.out.println("You want to delete the student from the table");
						  		  DeleteData deleteData = new DeleteData();
						  		  deleteData.deleteData(conn);
						  		  break;
						  		  
						  case 7: System.out.println("Exiting from the program.....");
						  		  conn.close();
							  	  System.exit(0);
							  	  
						  default: System.out.println("Invalid selection");
						  		   break;
					 }
				}while (menuNo != 7);
		}
	    catch(NoSuchElementException e)
	     {
	    	e.printStackTrace();
	     }
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection()
	{
		Connection conn = null;
		try 
		{
		  conn = DriverManager.getConnection(Configuration.url, Configuration.username, Configuration.password);		
		}
		catch(SQLException ex) 
		{
			ex.printStackTrace();
		}
		return conn;
	}
	
}

package Assignment_2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteData 
{
	
	public void deleteData(Connection conn)
	{
		try
		{
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Enter the studentID to be deleted from the system");
			String studentID = sc.nextLine();
			String studentName = null, s_ID = null, i_ID = null;
			
			Statement stmt = conn.createStatement();
			
			String sql = "SELECT ID, name as StudentName FROM university.student WHERE ID = '"+studentID+"' ";
			ResultSet rs = stmt.executeQuery(sql);
			
            while (rs.next()) {
                studentID = rs.getString("ID");
                studentName = rs.getString("StudentName");
            }
            
			if(studentName != null )
			{
	            System.out.println("The student Name of the ID entered is: "+studentName);
	            
	            System.out.println("Please enter yes if you want to delete and no if you do not want to delete");
	            String choice = sc.nextLine(); 
	            
	
	            if (choice.equalsIgnoreCase("yes"))
	            
	            {
	            	Statement stmtRelationship = conn.createStatement();
	            	
	            	String checkRelationShipQuery = "SELECT s_ID, i_ID FROM advisor WHERE s_ID = " + studentID;
	            	ResultSet rsCheck = stmtRelationship.executeQuery(checkRelationShipQuery);
	            	
	            	while(rsCheck.next()) 
	            	{
	            		s_ID = rsCheck.getString("s_ID");
	            		i_ID = rsCheck.getString("i_ID");
	            	}
	            	
	            	rsCheck.close();
	            	stmtRelationship.close();
	            	
	            	if(s_ID != null && i_ID !=null)
	            	{
	            		System.out.println("There is a relationship with the advisor for this student we need to delete it first");
	            		
	            		Statement deleteRelationship = conn.createStatement();
	            		
	            		String deleteRelQuery = "DELETE FROM advisor WHERE s_ID = " +studentID;
	            		int rowsAffected = deleteRelationship.executeUpdate(deleteRelQuery);
	            		
	                    if (rowsAffected > 0) 
	                        System.out.println("Relationship deleted successfully.");
	                    else 
	                        System.out.println("No rows were deleted.");
	                    
	                    
	                    System.out.println("Now, let us delete the student from the student table");
	                    
	                    Statement deleteStudent = conn.createStatement();
	                    
	                    String deleteStdQuery = "DELETE FROM student WHERE ID = " +studentID;
	                    rowsAffected = deleteStudent.executeUpdate(deleteStdQuery);
	                    
	                    if (rowsAffected > 0) 
	                        System.out.println("Student data deleted successfully.");
	                    else 
	                        System.out.println("No rows were deleted.");
	                    
	                    deleteRelationship.close();
	                    deleteStudent.close();
	            	}
	            	else
	            	{
	                    Statement deleteStudent = conn.createStatement();
	                    
	                    String deleteStdQuery = "DELETE FROM student WHERE ID	 = " +studentID;
	                    int rowsAffected = deleteStudent.executeUpdate(deleteStdQuery);
	                    
	                    if (rowsAffected > 0) 
	                        System.out.println("Student data deleted successfully.");
	                    else 
	                        System.out.println("No rows were deleted.");
	                    
	                    deleteStudent.close();
	            	}
	            	
	            	rsCheck.close();
	            	stmtRelationship.close();
	            }
	            else if (choice.equalsIgnoreCase("no"))
	            	System.out.println("We skipped deleting the student ;)");
			}
			else {
				System.out.println("Student does not exist in the Database");
			}
            
			stmt.close();
			rs.close();
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

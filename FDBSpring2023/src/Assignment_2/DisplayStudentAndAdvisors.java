package Assignment_2;

import java.sql.*;
import java.util.Scanner;


public class DisplayStudentAndAdvisors 
{
	
	public void displayStudentAndAdvisors(Connection conn)
	{
		try 
		{	
			//Connection conn = assign.getConnection();
			
			Statement stmtStudent = conn.createStatement();
			Statement stmtInstructor = conn.createStatement();
			
			ResultSet checkStudentTable = stmtStudent.executeQuery("SELECT COUNT(*) as count"
															+ " FROM INFORMATION_SCHEMA.TABLES "
															+ "WHERE TABLE_NAME='" + Configuration.student + "' AND TABLE_SCHEMA = '"+Configuration.schema+"';");
			
			
			ResultSet checkInstructorTable = stmtInstructor.executeQuery("SELECT COUNT(*) as count"
															+ " FROM INFORMATION_SCHEMA.TABLES "
															+ "WHERE TABLE_NAME='" + Configuration.instructor + "' AND TABLE_SCHEMA = '"+Configuration.schema+"';");
			
			checkStudentTable.next();
			checkInstructorTable.next();
			
			int countStudent = checkStudentTable.getInt("count");
			int countInstructor = checkInstructorTable.getInt("count");
			
			if(countStudent == 1 && countInstructor == 1)
			{
				
				PreparedStatement pstmtStudent = conn.prepareStatement("SELECT name as StudentName FROM university." +Configuration.student);
				PreparedStatement pstmtInstructor = conn.prepareStatement("SELECT name as InstructorName FROM university." +Configuration.instructor);
				
				ResultSet rsStudent = pstmtStudent.executeQuery();
				ResultSet rsInstructor = pstmtInstructor.executeQuery();
				
	            ResultSetMetaData rsmdStudent = rsStudent.getMetaData();
	            ResultSetMetaData rsmdInstructor =  rsInstructor.getMetaData();
	            
	            int studentColumnCount = rsmdStudent.getColumnCount();
	            int instructorColumnCount = rsmdInstructor.getColumnCount();
	            
	         // Print column names
	            
	            for (int i = 1; i <= studentColumnCount; i++) 
	            {
	                System.out.print("List of Student Names: " + "\t");
	            }
	            
	            System.out.println();
	            
	            // Iterate through the ResultSet object to retrieve the data
	            while (rsStudent.next()) 
	            {
	                // Print the data for each row
	                for (int i = 1; i <= studentColumnCount; i++) 
	                {
	                    System.out.print(rsStudent.getString(i) + "\t\t");
	                }
	                System.out.println();
	            }
	            
	            System.out.println();
	            
	            for (int i = 1; i <= instructorColumnCount; i++) 
	            {
	                System.out.print("List of Instructor Names" + "\t");
	            }
	            
	            System.out.println();
	            
	            // Iterate through the ResultSet object to retrieve the data
	            while (rsInstructor.next()) 
	            {
	                // Print the data for each row
	                for (int i = 1; i <= instructorColumnCount; i++) 
	                {
	                    System.out.print(rsInstructor.getString(i) + "\t\t");
	                }
	                System.out.println(); 	   
	            }
	            
	            rsInstructor.close();
	            rsStudent.close();
	            pstmtStudent.close();
	            pstmtInstructor.close();
	            stmtStudent.close();
	            stmtInstructor.close();
	            checkStudentTable.close();
	            checkInstructorTable.close();
			}
			else
			{
				System.out.println("Table does not exist in the Database");
			}
						
			//conn.close();	
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void establishRelationship(Connection conn)
	{
		
		try
		{
			String studentID = null, advisorID = null;
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Looking to assign a student to the advisor? ");
			System.out.println("Enter the name of the student");
			String studentName = sc.nextLine();
			
			System.out.println("Enter the name of the advisor");
			String advisorName = sc.nextLine();
			
			Statement stmtStudent = conn.createStatement();
			String sql = "SELECT name as StudentName, ID FROM university.student WHERE name = '"+ studentName+"' ";
			
			ResultSet rsStudent = stmtStudent.executeQuery(sql);
			
			if(rsStudent.next() == false)
				System.out.println("Student Name entered does not exist in the Student table");
			else
				studentID = rsStudent.getString("ID");
			
			Statement stmtInstructor = conn.createStatement();
			sql = "SELECT name as InstructorName, ID FROM university.instructor WHERE name = '"+ advisorName+"' ";
				
			ResultSet rsInstructor = stmtInstructor.executeQuery(sql);
			
			if(rsInstructor.next() == false)
					System.out.println("Instructor name does not exist in the Instructor table");
			else
				advisorID = rsInstructor.getString("ID");
			
			
			String insertQuery = "INSERT INTO advisor (s_ID, i_ID) VALUES (?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(insertQuery);
			
			pstmt.setString(1, studentID);
			pstmt.setString(2, advisorID);
			
			int msg = pstmt.executeUpdate();
			
			if (msg == 0)
				System.out.println("Relation established sucessfully!!");
			else
				System.out.println("Failed");
			
			
			sc.close();
			pstmt.close();
			rsStudent.close();
			stmtStudent.close();
			rsInstructor.close();
			stmtInstructor.close();
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}	
}

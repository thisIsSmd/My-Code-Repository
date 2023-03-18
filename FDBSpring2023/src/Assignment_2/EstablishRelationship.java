package Assignment_2;

import java.sql.*;
import java.util.Scanner;

public class EstablishRelationship 
{
	String studentID, advisorID;
	
	public void establishRelationship(Connection conn)
	{
		try
		{
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

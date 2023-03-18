package Assignment_2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertData 
{
	Scanner sc = new Scanner(System.in);
	String student = "student";
	
	Connection conn = null;
	
	void insertData(Connection conn)
	{
		try 
		{
			System.out.println("Enter the data to be inserted for the student table");
			
			System.out.println("Enter the ID: ");
			String id = sc.nextLine();
			
			System.out.println("Enter the name of the Student");
			String studentName = sc.nextLine();
			
			System.out.println("Enter the name of the department");
			String dept = sc.nextLine();
			
			System.out.println("Enter the total number of credits");
			float credits = sc.nextFloat();
			
			String sql = "INSERT INTO "+ student +"(ID, name, dept_name, tot_cred) VALUES (?, ?, ?, ?)";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, studentName);
			pstmt.setString(3, dept);
			pstmt.setFloat(4, credits);
			
            pstmt.executeUpdate();
            System.out.println("Student Data inserted successfully");
            
          //  conn.close();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}	
	}
	
}

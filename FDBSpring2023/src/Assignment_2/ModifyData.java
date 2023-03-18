package Assignment_2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ModifyData 
{	
	public void modifyData(Connection conn)
	{
		try
		{
			String currentAdvisor = null, studentName = null;
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Please enter the Student ID");
			String studentID = sc.nextLine();
				
			Statement statement = conn.createStatement();
			
			ResultSet rs = statement.executeQuery("SELECT S.name as StudentName, I.name as advisorName "
															+ "FROM student S "
															+ "LEFT JOIN advisor A "
															+ "	ON A.s_ID = S.ID "
															+ "LEFT JOIN Instructor I "
															+ "	ON A.i_ID = I.ID "
															+ "WHERE S.ID = '"+ studentID +"' ");
			while(rs.next())
			{
			 currentAdvisor = rs.getString("advisorName");
			 studentName = rs.getString("StudentName");
			}
			
			if (currentAdvisor == null || currentAdvisor.equals("") || currentAdvisor.trim().equals(""))
				System.out.println("No instructor is assigned to the student: " +studentName);
			else
			{
				System.out.println("The current advisor of the student: "+studentName+ " is "+currentAdvisor );
				
				System.out.println("Enter the name of the new advisor to be assigned for the student: "+studentName);
				String newAdvisor = sc.nextLine();
				
				String sql = "WITH "
						+ "  NewAdvisor AS (SELECT ID FROM university.instructor WHERE name = ? ), "
						+ "  stud AS (SELECT ID FROM university.student WHERE name = ?) "
						+ "UPDATE advisor A "
						+ "INNER JOIN stud s  "
						+ "	ON s.ID = A.s_ID "
						+ "INNER JOIN NewAdvisor NA "
						+ "	ON 1 = 1  "
						+ "SET A.i_ID = NA.ID";
						
				PreparedStatement pdstmt = conn.prepareStatement(sql);
				
				pdstmt.setString(1, newAdvisor);
				pdstmt.setString(2, studentName);
				
				// execute the update statement
				
				int rowsUpdated = pdstmt.executeUpdate();
				
				if(rowsUpdated == 1)
					System.out.println("Advisor is modified successfully");
				else 
					System.out.println("There is not relationship established for the student in the advisor table :(");
				
				statement.close();
				rs.close();
				pdstmt.close();
			}
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


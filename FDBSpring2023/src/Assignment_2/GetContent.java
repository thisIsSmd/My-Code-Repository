package Assignment_2;
import java.sql.*;
import java.util.Scanner;


public class GetContent
{

	public void getContent(Connection conn)
	{
		try 
		{
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Enter the Table Name: ");
			
			String tableName = sc.nextLine(); 
		    			
			Statement statement = conn.createStatement();
			
			ResultSet checkTable = statement.executeQuery("SELECT COUNT(*) as count"
															+ " FROM INFORMATION_SCHEMA.TABLES "
															+ "WHERE TABLE_NAME='" + tableName + "' AND TABLE_SCHEMA = '"+Configuration.schema+"';");
			
			
			checkTable.next();
			
			int count = checkTable.getInt("count");
			
			if(count == 1)
			{
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM university." +tableName);
				
				ResultSet rs = pstmt.executeQuery();
				
	            ResultSetMetaData rsmd = rs.getMetaData();
	            int columnCount = rsmd.getColumnCount();
	            
	         // Print column names
	            
	            for (int i = 1; i <= columnCount; i++) 
	            {
	                System.out.print(rsmd.getColumnName(i) + "\t");
	            }
	            
	            System.out.println();
	            
	            // Iterate through the ResultSet object to retrieve the data
	            while (rs.next()) 
	            {
	                // Print the data for each row
	                for (int i = 1; i <= columnCount; i++) {
	                    System.out.print(rs.getString(i) + "\t");
	                }
	                System.out.println();
	            }
				rs.close();
				pstmt.close();
			}
			else
			{
				System.out.println("Table does not exist in the Database");
			}
						
			//sc.close();
			statement.close();
			checkTable.close();
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}

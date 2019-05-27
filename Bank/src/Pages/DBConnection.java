package Pages;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	
	public static Connection getConnection()
	{
		Connection conn = null;
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/banksystem","root","apple4");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return conn;
	}
	


}

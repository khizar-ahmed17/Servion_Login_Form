package webpack;

import java.sql.Connection;
import java.sql.DriverManager;

public class Jdbc_Connection 
{
	
	public static final ThreadLocal<Connection> THREAD_LOCAL=new ThreadLocal<Connection>();
	static
	{
		try
		{
			
			Class.forName("com.mysql.jdbc.Driver");
			
		}
		catch(Exception e)
		{
			System.err.println("Class Not Error");
		}
	}
	public static Connection getConnection()
	{
		Connection connection=null;
		try
		{

			connection=THREAD_LOCAL.get();
			if(connection==null)
			{
				connection=DriverManager.getConnection("jdbc:mysql://localhost/mydatabase","root","root");
				THREAD_LOCAL.set(connection);	
			}		
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
		
		return connection;
	}
	

}

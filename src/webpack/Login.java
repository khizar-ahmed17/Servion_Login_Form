package Java;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login
{

	public Connection connection;
	public Statement statement;
	private void setConnection() throws SQLException
	{

		connection=Jdbc_Connection.getConnection();
		statement = connection.createStatement();
		
	}
	public  int dologin(String usr,String pass) throws SQLException
	{
		
		setConnection();
		System.out.println("in do login");
		
		ResultSet set=statement.executeQuery("select * from project where name='"+usr+"'");
		set.last();
		
		if (set.getRow()>0)
		{
			System.out.println("user in");
			Statement st=connection.createStatement();
			ResultSet result=st.executeQuery("select * from project where name='"+usr+"'");
			result.next();
			if( result.getString(2).trim().equals(pass) )
			{
				if(checkStatus(usr))
				{
					System.out.println("password match");
					resetStatus(usr);
					return 0;
				}
				else
				{
					return 2;
					
					//already logged in
				}
				//login successful
			}
			else
				return 1;
				//password error
		}
		
		return -1;
		//user not found
		
		
	}
	
	private void resetStatus(String usr) throws SQLException 
	{
		String updateTableSQL = "UPDATE project SET status = ? WHERE name = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(updateTableSQL);
		preparedStatement.setString(1, "1");
		preparedStatement.setString(2, usr);
		// execute insert SQL stetement
		preparedStatement .executeUpdate();
		System.out.println("done");
		
	}
	
	private boolean checkStatus(String usr) throws SQLException 
	{

		Statement st=connection.createStatement();
		ResultSet result=st.executeQuery("select * from project where name='"+usr+"'");
		result.next();
		if(result.getString(3).trim().equals("0"))
		{
			System.out.println("Status approved");
			return true;
		}
		return false;
	}

}

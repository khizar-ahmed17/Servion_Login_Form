package webpack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction extends Action 
{
	public Connection connection;
	public Statement statement;
	private void setConnection() throws SQLException
	{

		connection=Jdbc_Connection.getConnection();
		statement = connection.createStatement();
		
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)throws Exception
	{
		setConnection();
		String usr=request.getParameter("name");
		String pass=request.getParameter("password");
		
		
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
					return "login.success";
				}
				else
				{
					return "login.already";
					
					//already logged in
				}
				//login successful
			}
			else
				return "login.error";
				//password error
		}
		
		return "login.register";
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

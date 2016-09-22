package webpack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterAction extends Action
{
	public Connection connection;
	public Statement statement;
	BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
	private void setConnection() throws SQLException
	{

		connection=Jdbc_Connection.getConnection();
		statement = connection.createStatement();
		
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException 
	{
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		setConnection();
		
		if(checkUserExists(name))
		{
			setPassword(name,password);
			return "login.dologin";
		}
		return "login.register";
	}
	
	private void setPassword(String uname, String pass) throws SQLException
	{
			String insert="insert into project values(?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insert);
			preparedStatement.setString(1,uname);
			preparedStatement.setString(2, pass);
			preparedStatement.setString(3, "0");
			
			// execute insert SQL stetement
			preparedStatement .executeUpdate();
			System.out.println("done");
		
	}

	private boolean checkUserExists(String tmpname) throws SQLException, IOException 
	{
		ResultSet set=statement.executeQuery("select * from project where name='"+tmpname+"'");
		set.last();
		if(set.getRow()>0)
		{
			return false;
		}

		return true;

		
	}

}

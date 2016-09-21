package Servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Java.DataUtility;
import Java.Login;
import Java.Register;

/**
 * Servlet implementation class JavaServlet
 */
public class JavaServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    public JavaServlet() 
    {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		System.out.println("In do get");
		
		/*
		 * Getting Parameter from Jsp
		 */
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		
		
		/*
		 * Setting Global Username and Password
		 */
		
		DataUtility.setData(request.getParameter("name"), request.getParameter("password"));
		
		
		Java.Login login=new Login();
		try 
		{
			int i=login.dologin(name,password);
			if(i==0)
			{			
				response.sendRedirect("welcome.jsp");
			}
			else if(i==2)
			{

				response.sendRedirect("alreadyLogin.jsp");
			}
			else if(i==1)
			{
				response.sendRedirect("passwordError.jsp");
			}
			else if(i==-1)
			{
				response.sendRedirect("register.jsp");
				
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("Exception");
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("In do get");
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		System.out.println(name);
		System.out.println(password);
		Java.Register register=new Register();
		try 
		{
			if(register.doRegister(name, password))
			{
				response.sendRedirect("login.jsp");
			}
			else

				response.sendRedirect("register.jsp");
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

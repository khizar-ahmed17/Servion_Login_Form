package webservlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webpack.Action;

/**
 * Servlet implementation class JavaServlet
 */
public class JavaServlet extends HttpServlet 
{
	Properties prop;
	@Override
	public void init(ServletConfig config) throws ServletException
	{
		System.out.println("Loading Properties File");
		prop=new Properties();
    	String path=config.getInitParameter("path");
    	String abs=config.getServletContext().getRealPath(path);
    	try
    	{
    		prop.load(new FileInputStream(abs));
        	
    	}
    	catch (Exception e)
    	{
			// TODO: handle exception
		}
    	
	}
	private static final long serialVersionUID = 1L;
    public JavaServlet() 
    {
    
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	
		doPost(request, response);
	}
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Action action=null;
		String actionString=request.getParameter("id");
		Class<Action>classObj;
		try
		{
			classObj = (Class<Action>) Class.forName(prop.getProperty(actionString));
			action = (Action) classObj.newInstance();
			String result=action.execute(request, response);

			request.setAttribute("Name", request.getParameter("name"));
			RequestDispatcher rd=request.getRequestDispatcher(prop.getProperty(result));  
			rd.include(request, response); 
	        
       }
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}

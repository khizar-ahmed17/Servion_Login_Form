<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
</body>
	<div align="center">
	<%
    	Object value = request.getAttribute("msg");
	if(value==null)
	{
		value="";
	}
	%>
	
	<h3 style="color:white">
	<i>
		<%=value %>
	</i>
	</h3>
	</div>
	
	<%@include  file="pages/register.html" %>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Logout Page</h1>
<%
	response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
	session.invalidate();
	response.sendRedirect("http://localhost:8080/EmployeeCRUDMaven/");
%>

</body>
</html>
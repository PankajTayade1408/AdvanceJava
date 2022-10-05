<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="employeecrudmaven.model.EmployeeModel.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<body>
	<h1>
		<a href="<%=request.getContextPath()%>/new">Add Employee Detail's</a>
	</h1>
	<br>
	<br>
	<h3>Employee Information</h3>
	<table border="1" width="80%" padding="10px">
		<tr>
			<th>Sr.No.</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Skills</th>
			<th>Age</th>
			<th>Salary</th>
			<th>Date of Joining</th>
			<th>Update</th>
			<th>Delete</th>
		</tr>
		<%
			int count = 0;
		%>
		<c:forEach items="${empList}" var="e">		
			<tr>
				<td>
				<%
					count += 1;
					out.print(count);
				%>
				</td>
				<td>${e.getFirstname()}</td>
				<td>${e.getLastname()}</td>
				<td>${e.getSkills()}</td>
				<td>${e.getAge()}</td>
				<td>${e.getSalary()}</td>
				<td>${e.getDoj()}</td>
				<td><a
					 href="<%=request.getContextPath()%>/edit?id=${e.getId()}">Edit</a></td>
				<td><a
					href="<%=request.getContextPath()%>/delete?id=${e.getId()}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>

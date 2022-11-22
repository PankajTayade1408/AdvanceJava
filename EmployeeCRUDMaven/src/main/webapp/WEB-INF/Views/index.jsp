<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="employeecrudmaven.model.EmployeeModel.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
	<style>
	#logoutsection
	{
	 	position:absolute;
		right:20;
		top:10;
		font-size:20px; 
	}
	#logout
	{
	position:absolute;
	top:29;
	left:9;
	}
	</style>
	<c:out value="${messegeLogin}">
	</c:out>
	<h1>
		<a href="<%=request.getContextPath()%>/new">Add Employee Detail's</a>
	</h1>
	<br>
	<div id="logoutsection">
	Hii <c:out value="${username}"></c:out>
 	<a href="<%=request.getContextPath()%>/logout" id="logout" >Logout</a>
	</div>
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
			<th>Country</th>
			<th>Date of Joining</th>
			<th>Update</th>
			<th>Delete</th>
		</tr>
		<%
			int count = 0;
		%>
		<c:forEach items="${empList}" var="e">		
			<tr>
				<td><center>
				<%
					count += 1;
					out.print(count);
				%></center>
				</td>
				<td><center>${e.getFirstname()}</center></td>
				<td><center>${e.getLastname()}</center></td>
				<td><center>${e.getSkills()}</center></td>
				<td><center>${e.getAge()}</center></td>
				<td><center><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${e.getSalary()}"/></center></td>
				<td><center>${e.getCountry()}</center></td>
				<td><center>${e.getDoj()}</center></td>
				<td><center><a
					 href="<%=request.getContextPath()%>/edit?id=${e.getId()}">Edit</a></center></td>
				<td><center><a
					href="<%=request.getContextPath()%>/delete?id=${e.getId()}">Delete</a></center></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
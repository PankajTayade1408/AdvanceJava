<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*,java.text.*,java.time.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
.Form {
	margin-left: 400px;
}

#submit {
	margin-left: 150px;
}

#reset {
	margin-left: 30px;
}
</style>
</head>
<body>
<c:out value="${employeeEmail}">
</c:out>
	<c:if test="${allEmail.contains(employeeEmail)}">
		<form action="<%=request.getContextPath() %>/edit" method="post">
		<h2>Existing Email</h2>
	</c:if>
	
	<c:if test="${employee==null}">
		<form action="<%=request.getContextPath() %>/insert" method="post">
	</c:if>
	<c:if test="${employee!=null }">
		<form action="<%=request.getContextPath() %>/update" method="post">
	</c:if>
	<div class="Form">
		<input type="hidden" name="empId" value="${employee.getId()}">
		Enter the Employee Name : <input type="text" name="empfname"
			placeholder="Enter the First Name" value="${employee.getFirstname()}"
			required /> <input type="text" name="emplname"
			placeholder="Enter the Last Name" value="${employee.getLastname()}"
			required /><br />
		<br /> Enter the Employee Skills : <input type="checkbox"
			name="empSkills" value="C"
			${ employee.getSkills().contains("C")  ?"checked":""}>C <input
			type="checkbox" name="empSkills" value="C++"
			${employee.getSkills().contains("C++")  ?"checked":""}>C++ <input
			type="checkbox" name="empSkills" value="JAVA"
			${employee.getSkills().contains("JAVA")?"checked":""}>JAVA <input
			type="checkbox" name="empSkills" value="PYTHON"
			${employee.getSkills().contains("PYTHON")?"checked":""}>PYTHON
		<input type="checkbox" name="empSkills" value="AWS"
			${employee.getSkills().contains("AWS")?"checked":""}>AWS <br />
		<br /> Enter the Employee Age : <input type="number" name="empage"
			value="${employee.getAge()}" pattern="[A-Za-z]" min="20" max="60"
			required /><br />
		<br /> Enter the Employee Salary : <input type="number"
			name="empsalary" value="${employee.getSalary()}" min="20000"
			max="90000" required /><br />
		<br /> Enter the Employee's Date of Joining : <input type="date"
			name="empdoj" value="${employee.getDoj()}"
			max=<%=java.time.LocalDate.now()%> required /><br />
		<br/>  Enter the E-mail : <input type="email" name="empemail" 
			value="${employee.getEmail()}" max="254" required/>	
		<br /> <br/><input type="submit" name="submit" value="Submit" id="submit"
			required /> <input type="reset" name="reset" value="Reset" id="reset"
			required /> &nbsp;&nbsp;&nbsp;&nbsp; <a
			href="<%=request.getContextPath()%>/"><button type="button">List</button></a>
	</div>
</body>
</html>
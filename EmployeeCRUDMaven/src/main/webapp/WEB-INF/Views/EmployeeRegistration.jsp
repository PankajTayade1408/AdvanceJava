<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*,java.text.*,java.time.*"%>
<!DOCTYPE html>
<html>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
.Form {
	margin-left: 400px;
}

.required:After{
  content:"*";
  color:red;
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
	<c:if test="${employee==null}">
		<form action="<%=request.getContextPath() %>/insert" method="post">
	</c:if>
	<c:if test="${employee!=null }">
		<form action="<%=request.getContextPath() %>/update" method="post">
	</c:if>
	<div class="Form">
	
		<input type="hidden" name="id" value="${employee.getId()}">
		 Employee Name <span style="color:red ; font-weight:bold">*</span> : <input type="text" name="empfname"
			placeholder="Enter Your First Name" value="${employee.getFirstname()}"
			required /> <input type="text" name="emplname"
			placeholder="Enter Your Last Name" value="${employee.getLastname()}"
			required /><br />
		<br /> Employee Skills : <input type="checkbox"
			name="empSkills" value="C"
			${employee.getSkills().contains("C")  ?"checked":""}>C <input
			type="checkbox" name="empSkills" value="C++"
			${employee.getSkills().contains("C++")  ?"checked":""}>C++ <input
			type="checkbox" name="empSkills" value="JAVA"
			${employee.getSkills().contains("JAVA")?"checked":""}>JAVA <input
			type="checkbox" name="empSkills" value="PYTHON"
			${employee.getSkills().contains("PYTHON")?"checked":""}>PYTHON
		<input type="checkbox" name="empSkills" value="AWS"
			${employee.getSkills().contains("AWS")?"checked":""}>AWS <br />
		<br /> Employee Age <span style="color:red ; font-weight:bold">*</span> : <input type="number" name="empage"
			value="${employee.getAge()}" pattern="[A-Za-z]" min="20" max="60" class="required"
			required /><br />
		<br /> Employee Salary <span style="color:red ; font-weight:bold">*</span> : <input type="number"
			name="empsalary" value="${employee.getSalary()} step="1" min="1"  required /><br />
		<br /> Employee's Date of Joining <span style="color:red ; font-weight:bold">*</span> : <input type="date"
			name="empdoj" value="${employee.getDoj()}"
			max=<%=java.time.LocalDate.now()%> required /><br />
			<input type="hidden" name="emploginId" value="${employee.getLoginId()}">
		<br /> <input type="submit" name="submit" value="Submit" id="submit"
			required /> <input type="reset" name="reset" value="Reset" id="reset"
			required /> &nbsp;&nbsp;&nbsp;&nbsp; <a
			href="<%=request.getContextPath()%>/list"><button type="button">List</button></a>
	</div>
</body>
</html>
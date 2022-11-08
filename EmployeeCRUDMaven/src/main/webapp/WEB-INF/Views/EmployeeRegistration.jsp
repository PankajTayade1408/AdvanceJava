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

.nameMessege span
{
display:inline-block;
color:red;
margin-left:131px;
font-size:15px;
}

/*  #lastname
{
margin-left:182px;
font-size:14px;
color:red;
}  */
#age
{
margin-left:120px;
color:red;	
}
.required:After{
  content:"*";
  color:red;
}
#salary
{
margin-left:135px;
font-size:14px;
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
		 Employee Name <span style="color:red ; font-weight:bold">*</span> : <input type="text" name="empfname"placeholder="Enter Your First Name" value="${employee.getFirstname()}" />
		  <input type="text" name="emplname" placeholder="Enter Your Last Name" value="${employee.getLastname()}"/><br />
		<div class="nameMessege">
		<span>
		<c:out value="${firstName}" >
		</c:out>
		</span>
		<span>
		<c:out value="${lastName}">
		</c:out>
		</span>
		</div>
		</br>Employee Skills : <input type="checkbox"
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
			value="${employee.getAge()}"/><br />
			<div id="age">
			<c:out value="${age}">
			</c:out>
			</div>
		<br /> Employee Salary <span style="color:red ; font-weight:bold">*</span> : <input type="text"
			name="empsalary" value="${employee.getSalary()}" /><br />
			<div id="salary">
			<c:out value="${salary}">
			</c:out>
			</div>
		<br /> Employee's Date of Joining <span style="color:red ; font-weight:bold">*</span> : <input type="date"
			name="empdoj" value="${employee.getDoj()}"
			max=<%=java.time.LocalDate.now()%> /><br />
			<input type="hidden" name="emploginId" value="${employee.getLoginId()}"/>
		<br /> <input type="submit" name="submit" value="Submit" id="submit"
			required /> <input type="reset" name="reset" value="Reset" id="reset"
			required /> &nbsp;&nbsp;&nbsp;&nbsp; <a
			href="<%=request.getContextPath()%>/list"><button type="button">List</button></a>
	</div>
</body>
</html>
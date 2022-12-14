<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page
	import="java.util.*,java.text.*,java.time.*,employeecrudmaven.model.*"%>
<!DOCTYPE html>

<%@ page import="java.util.*,java.text.*,java.time.*"%>


<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">

<title>Insert title here</title>

<style>
.Form {
	margin-left: 400px;
	margin-top:50px;
}
<<<<<<< HEAD
=======

.nameMessege span {
	display: inline;
	color: red;
	margin-left: 132px;
	font-size: 15px;
}

#lastname {
	margin-left: 315px;
	margin-top: -18px;
	color: red;
}

#age {
	margin-left: 120px;
	color: red;
}

.required:After {
	content: "*";
	color: red;
}

#salary {
	margin-left: 135px;
	font-size: 14px;
	color: red;
}

#doj {
	color: red;
	margin-left: 200px;
	font-size: 15px;
}

>>>>>>> 4946e0a6446a48c07ec16a121c46492e195926ff
#submit {
	margin-left: 150px;
}
#reset {
	margin-left: 30px;
}

#country {
	color: red;
	margin-left: 140px;
}

select {
	margin-left: 60px;
}

#picture {
	color: red;
	font-size: 15px;
	margin-left: 170px;
}
</style>
</head>

<body>
<<<<<<< HEAD
<c:out value="${messege}">
</c:out>
	<c:if test="${employee.getId()==0 || employee==null}">
		<form action="<%=request.getContextPath() %>/insert" method="post">
	</c:if>
  	<c:if test="${employee.getId()!=0  &&  employee!=null}"> 
		<form action="<%=request.getContextPath() %>/update" method="post">
 	</c:if> 
	<div class="Form">
		<input type="hidden" name="empId" value="${employee.getId()}">
		 
		 Enter the Employee Name : <input type="text" name="empfname"placeholder="Enter the First Name" value="${employee.getFirstname()}" required /> 
		
		<input type="text" name="emplname" placeholder="Enter the Last Name" value="${employee.getLastname()}" required /><br /><br />
		
		 Enter the Employee Skills : <input type="checkbox"name="empSkills" value="C"	${ employee.getSkills().contains("C")  ?"checked":""}>C 
		
		<input	type="checkbox" name="empSkills" value="C++" ${employee.getSkills().contains("C++")  ?"checked":""}>C++
		<input type="checkbox" name="empSkills" value="JAVA" ${employee.getSkills().contains("JAVA")?"checked":""}>JAVA 
		<input type="checkbox" name="empSkills" value="PYTHON" ${employee.getSkills().contains("PYTHON")?"checked":""}>PYTHON
		<input type="checkbox" name="empSkills" value="AWS" ${employee.getSkills().contains("AWS")?"checked":""}>AWS <br /><br /> 
		
		Enter the Employee Age : <input type="number" name="empage" value="${employee.getAge()}" pattern="[A-Za-z]" min="20" max="60" required /><br /><br /> 
		
		Enter the Employee Salary : <input type="number" name="empsalary" value="${employee.getSalary()}" min="20000" max="90000" required /><br /><br />  
		
		Enter the Employee's Date of Joining : <input type="date" name="empdoj" value="${employee.getDoj()}" max=<%=java.time.LocalDate.now()%> required /><br /><br />
		
		 <c:if test="${employee.getId()==0 || employee==null}">
		Enter the E-mail : <input type="email" name="empemail" 	value="${employee.getEmail()}" max="254" required/>	<br /><br />
		<form action="<%=request.getContextPath() %>/insert" method="post">
		</c:if>
		 
		 <c:if test="${employee.getId()!=0 && employee!=null}">
		Enter the E-mail : <input type="email" name="empemail" 	value="${employee.getEmail()}" max="254" required readonly/>	<br /><br />
		 <form action="<%=request.getContextPath() %>/update" method="post">
 		</c:if> 
		
		<input type="submit" name="submit" value="Submit" id="submit" required />
		
		<input type="reset" name="reset" value="Reset" id="reset" required />  &nbsp;&nbsp;&nbsp;
		
		<a	href="<%=request.getContextPath()%>/"><button type="button">List</button></a>
=======
	<c:if test="${employee==null || idForEdit==null}">
		<form action="<%=request.getContextPath()%>/insert" method="post"
			enctype="multipart/form-data">
	</c:if>
	<c:if test="${employee!=null && idForEdit>0}">
		<form action="<%=request.getContextPath()%>/update" method="post"
			enctype="multipart/form-data">
	</c:if>
	<div class="Form">
		<input type="hidden" name="id" value="${employee.getId()}">
		Employee Name <span style="color: red; font-weight: bold">*</span> : <input
			type="text" name="empfname" placeholder="Enter Your First Name"
			value="${employee.getFirstname()}" /> <input type="text"
			name="emplname" placeholder="Enter Your Last Name"
			value="${employee.getLastname()}" /><br />
		<div class="nameMessege">
			<span> <c:out value="${firstName}">
				</c:out>
			</span>
		</div>
		<span>
			<div id="lastname">
				<c:out value="${lastName}">
				</c:out>
		</span>
	</div>
	</br>Employee Skills :
	<input type="checkbox" name="empSkills" value="C"
		${employee.getSkills().contains("C")  ?"checked":""}>C
	<input type="checkbox" name="empSkills" value="C++"
		${employee.getSkills().contains("C++")  ?"checked":""}>C++
	<input type="checkbox" name="empSkills" value="JAVA"
		${employee.getSkills().contains("JAVA")?"checked":""}>JAVA
	<input type="checkbox" name="empSkills" value="PYTHON"
		${employee.getSkills().contains("PYTHON")?"checked":""}>PYTHON
	<input type="checkbox" name="empSkills" value="AWS"
		${employee.getSkills().contains("AWS")?"checked":""}>AWS
	<br />
	<br /> Employee Age
	<span style="color: red; font-weight: bold">*</span> :
	<input type="text" name="empage" value="${employee.getAge()}" />
	<br />
	<div id="age">
		<c:out value="${age}">
		</c:out>
	</div>
	<br /> Employee Salary
	<span style="color: red; font-weight: bold">*</span> :
	<input type="text" name="empsalary"
		value="<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${employee.getSalary()}"/>" />
	<br />
	<div id="salary">
		<c:out value="${salary}">
		</c:out>
	</div>
	<br />
	<form>
		<label>Country <span style="color: red; font-weight: bold">*</span>
			:
		</label> <select name="country">
			<option value="none" selected disabled hidden>Select Country</option>
			<option value="India"
				${employee.getCountry().contains("India")?"selected":""}>India</option>
			<option value="USA"
				${employee.getCountry().contains("USA")  ?"selected":""}>USA</option>
			<option value="UK"
				${employee.getCountry().contains("UK")  ?"selected":""}>UK</option>
			<option value="Africa"
				${employee.getCountry().contains("Africa")  ?"selected":""}>Africa</option>
			<option value="New Zealand"
				${employee.getCountry().contains("New Zealand") ?"selected":""}>New
				Zealand</option>
			<option value="Australia"
				${employee.getCountry().contains("Australia") ?"selected":""}>Australia</option>
		</select>
		<div id="country">
			<c:out value="${country}">
			</c:out>
		</div>
	</form>
	<br /> Employee's Date of Joining
	<span style="color: red; font-weight: bold">*</span> :
	<input type="date" name="empdoj" value="${employee.getDoj()}"
		max=<%=java.time.LocalDate.now()%> />
	<br />
	<div id="doj">
		<c:out value="${dateOfJoining}">
		</c:out>
	</div>
	<%!String picture = "";
	byte[] image = null;%>
	<%
		if (image != null) {
			image = ((EmployeeModel) pageContext.findAttribute("employee")).getProfilePicture();
			System.out.println("Image is " + image);
			
		}
	%>
	<script>
      
    </script>
	<br /> Upload Profile Picture
	<span style="color: red; font-weight: bold">*</span> :
	<input type="file" name="file" value="${employee.getfileName()}" id="ProfilePicture"/>
	<c:if test="${picture!=null}">
		<img src="${picture}" width="100px" height="100px" />
	</c:if>
	<div id="picture">
		<c:out value="${Picture}"></c:out>
	</div>
	<br />
	<br />
	<input type="submit" name="submit" value="Submit" id="submit" required />
	<input type="reset" name="reset" value="Reset" id="reset" required />
	&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="<%=request.getContextPath()%>/list"><button type="button">List</button></a>
>>>>>>> 4946e0a6446a48c07ec16a121c46492e195926ff
	</div>
	</form>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<style>
      .loginbody {
        margin-left:500px;
        margin-top:30px;
      }
      #loginbutton
      {
      	  margin-top:10px;
          margin-left:70px;
      }
      #signin
      {
      margin-top:15px;
      }
      #messegeLogin
      {
      margin-top:40px;
      }
      #usernameAndPassword
      {
      margin-top:10px;
      }
    </style>
</head>
<body>
<c:out value="${LogOutMessege}">
</c:out>
<c:out value="${messege}">
</c:out>
<div id="messegeLogin">

  	   
</div>
		<form action="<%=request.getContextPath()%>/" method="post">
		<center><h1>Login Employee</h1></center>
  	    <div class="loginbody">
  	     <c:out value="${messegeLogin}">
		</c:out>
		<input type="hidden" name="Id" > 
		<div id="usernameAndPassword" >
		UserName <span style="color:red ; font-weight:bold">*</span>:-
        <input type="text" name="Username" value="${username}"  required /><br /><br />
        <br />
        Password <span style="color:red ; font-weight:bold">*</span>:-
        <input type="password" name="Password"  required /><br /><br />
        <input type="submit" name="LogIn" id="loginbutton" value="Login"/>
        
     	<div id="signin">Don't have an Account?<a href="<%=request.getContextPath()%>/signin"> Sign up</a></div>
		</div>
        </form>
     </div>
</body>
</html>
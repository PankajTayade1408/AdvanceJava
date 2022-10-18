<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<style>
      .loginbody {
        margin-left:500px;
        margin-top:20px;
      }
      #loginbutton
      {
      	  margin-top:10px;
          margin-left:70px;
      }
      #newuser
      {
      	margin-left:10px;
      	margin-top:800px;
      }
    </style>
</head>
<body>
<c:out value="${messege}">
</c:out>
<c:out value="${messegeLogin}">
</c:out>
		<form action="<%=request.getContextPath()%>/" method="post">
		<center><h1>Login Employee</h1></center>
  	    <div class="loginbody">
		<input type="hidden" name="Id" > 
		Enter UserName :-
        <input type="text" name="Username" value="${username}" required /><br /><br />
        <br />
        
        Enter Password :-
        <input type="password" name="Password"  required /><br /><br />
        <input type="submit" id="loginbutton"/>
        
        <a href="<%=request.getContextPath()%>/newuser" id="newuser"> New User</a>
        </form>
     </div>
</body>
</html>
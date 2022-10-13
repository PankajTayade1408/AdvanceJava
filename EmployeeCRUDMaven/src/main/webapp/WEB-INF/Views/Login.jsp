<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
      .loginbody {
        margin-left: 500px;
        margin-top:10px;
      }
      #loginbutton
      {
      	  margin-top:10px;
          margin-left:40px;
      }
      #newuser
      {
      	margin-left:120px;
      	margin-top:-30px;
      }
    </style>
</head>
<body>
<c:out value="${messegeLogin}">
</c:out>
    	 <c:if test="${loginModel!=null && username!=null}">
    	<form action="<%=request.getContextPath()%>/list" method="post">
    	</c:if> 
    	<c:if test="${loginModel==null && username==null}">
		<form action="<%=request.getContextPath()%>/" method="post">
    	</c:if>
		<center><h1>Login Employee</h1></center>
    <div class="loginbody">
		Enter UserName :-
        <input type="text" name="Username" value="${username}" required /><br /><br />
        <br />

        Enter Password :-
        <input type="password" name="Password"  required /><br /><br />
        <input type="submit" id="loginbutton"/><br /><br />
        <a href="<%=request.getContextPath()%>/newuser" id="newuser"> New User</a>
        </form>
     </div>
</body>
</html>
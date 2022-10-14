<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<style>
		 .loginbody {
        margin-left: 500px;
        margin-top:100px;
      }
      #submitForm
      {
      	  margin-top:50px;
          margin-left:80px;
      }
      #newuser
      {
      	margin-left:120px;
      }
      #loginbutton
      {
      	margin-left:200px;
      	margin-bottom:-10px;
      	
      }
    </style>
    <c:out value="${messege}">
    </c:out>
    <div class="loginbody">
		<form action="<%=request.getContextPath()%>/" method="post">
		Enter UserName :-
        <input type="text"  name="username"  value="${username}"required /><br /><br />
        <br />
        Enter Password :-
        <input type="password" name="password" required /><br /><br /><br />
        Confirm Password :-
        <input type="password" name="confirmpassword"  required /><br />
       <input type="submit" id="submitForm"/><br/><br/>
   	  <button type="button" id="loginbutton"><a href="<%=request.getContextPath()%>/">Login</a></button>
        </form>
     </div>

</body>
</html>
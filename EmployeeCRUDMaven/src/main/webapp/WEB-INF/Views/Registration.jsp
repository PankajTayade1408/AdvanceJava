<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
</head>
<body>
<style>
		 .registrationbody {
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
      #registrationbutton
      {
      	margin-left:20px;
      	margin-bottom:-10px;
      }
    </style>
    
    <c:out value="${messege}">
    </c:out>
    <div class="registrationbody">
		<form action="<%=request.getContextPath()%>/" method="post">
		<input type="hidden" name="Id" > 
		Enter UserName :-
        <input type="text"  name="username"  value="${username}"required /><br /><br />
        <br />
        
        Enter Password :-
        <input type="password" name="password" required /><br /><br /><br />
        
        Confirm Password :-
        <input type="password" name="confirmpassword"  required /><br />
       	<input type="submit" id="submitForm"/>
       	
   	  	<button type="button" id="registrationbutton"><a href="<%=request.getContextPath()%>/">Login</a></button>
        </form>
     </div>
</body>
</html>
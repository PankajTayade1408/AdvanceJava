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
      #login 
      {
      margin-top:20px;
      margin-left:20px;
      }
      #login p
      {
      margin-left:40px;
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
      h1
      {
     	position:absolute;
     	top:7;
     	margin-left:50px;
      }
      #messege
      {
        margin-left:90px;
        margin-top:-15px;
        font-size:14px;
        color:red;
      }
      #username 
      {
      	margin-top:-14px;
      	font-size=20px;
      	}
     #password p 
     {
     	margin-top:-40px;
     }
     #passwordMessege
     {
     	margin-left:140px;
     	margin-top:5px;
     	color:red;
     }
    </style>
    
    <div class="registrationbody">
    	<h1><center>Sign Up Employee</center></h1>
		<form action="<%=request.getContextPath()%>/" method="post">
		<input type="hidden" name="Id" > 
		UserName :-
        <input type="text"  name="username"   value="${username}"required /><br /><br />
    	<div id="messege">
    	<c:out value="${messege}" >
  		</c:out>
  		</div>	
        <br />
       <div id="Password"> Password :-
        <input type="password" name="password" required /><br /><br /><br /></div>
        Confirm Password :-
        <input type="password" name="confirmpassword"  required /><br />
       <div id="passwordMessege"><c:out value="${messegeForPassword}"></c:out></div>	
       
   	  	<div id="login">
       	<input type="submit" id="submitForm" value="Sign up"/>
   	  	<p>Already Registered?<a href="<%=request.getContextPath()%>/"> Log in</a></button></p>
        </div>
        </form>
     </div>
</body>
</html>
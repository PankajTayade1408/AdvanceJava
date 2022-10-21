package employeecrudmaven.controller;

import java.io.IOException;
import java.util.LinkedHashSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import employeecrudmaven.dao.LoginDAO;
import employeecrudmaven.dao.LoginDAOImpl;
import employeecrudmaven.model.LoginModel;
import employeecrudmaven.service.LoginService;
import employeecrudmaven.service.LoginServiceImpl;

@WebServlet("/")
public class LoginController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmpassword");
		LoginService loginService = new LoginServiceImpl();
		LoginModel registrationModel = new LoginModel(username, password);
		if (username != null && password != null && confirmPassword != null) {
			if (loginService.isUsernameExistsInDB(username)) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("//WEB-INF//Views//Registration.jsp");
				request.setAttribute("messege", "Username is already Exists...");
				request.setAttribute("username", username);
				dispatcher.forward(request, response);
				return;
			} else if (loginService.isPasswordNotEqualsConfirmPassword(password, confirmPassword)) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("//WEB-INF//Views//Registration.jsp");
				request.setAttribute("messege","Entered Password and Confirmed Password are Not Same..Enter the Correct Password...");
				request.setAttribute("username", username);
				dispatcher.forward(request, response);
				return;
			} else {
				loginService.insertLogin(registrationModel);
				RequestDispatcher dispatcher1 = request.getRequestDispatcher("//WEB-INF//Views//Login.jsp");
				request.setAttribute("messege", "Registration Successfull");
				dispatcher1.forward(request, response);
			}
			return;
		}
		
		String action = request.getServletPath();
		if (action.equals("/newuser")) {
			registration(request, response);
		}
		else if(action.contentEquals("/logout"))
		{
			logOut(request,response);
		}
		else {
			loginEmployee(request, response);
		}
	}
	
	private void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	RequestDispatcher dispatcher = request.getRequestDispatcher("/");
		HttpSession session=request.getSession();
		session.invalidate();
		request.getSession(false);
		dispatcher.forward(request, response);
	}

	private void loginEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("//WEB-INF//Views//Login.jsp");
		LoginService loginService = new LoginServiceImpl();
		String usernameLogin = request.getParameter("Username");
		String passwordLogin = request.getParameter("Password");
		if (usernameLogin != null && passwordLogin != null) {
			if (loginService.isUsernameNotExistsInDBForLogin(usernameLogin)) {
				request.setAttribute("messegeLogin", "Username is not exists...Enter the correct the Username");
				request.setAttribute("username", usernameLogin);
				dispatcher.forward(request, response);
				return;
			} else if (loginService.isPasswordNotExistsInDBForLogin(usernameLogin, passwordLogin)) {
				request.setAttribute("username", usernameLogin);
				request.setAttribute("messegeLogin", "Invalid Password...Enter the correct the Password");
				dispatcher.forward(request, response);
				return;
			} else {
				HttpSession session = request.getSession();
				LinkedHashSet<String> usernameSet=new LinkedHashSet<String>();
				int id=loginService.getId( usernameLogin, passwordLogin);
				session.setAttribute("id",id);
				RequestDispatcher dispatcher1= request.getRequestDispatcher("/list");
				request.setAttribute("messegeLogin", "Login Successfull");
				dispatcher1.forward(request, response);
			}
			return;
		}
		dispatcher.forward(request, response);
	}

	private void registration(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("//WEB-INF//Views//Registration.jsp");
		dispatcher.forward(request, response);
	}
}

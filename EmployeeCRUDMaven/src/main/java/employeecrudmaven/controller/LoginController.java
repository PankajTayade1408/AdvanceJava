package employeecrudmaven.controller;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.regex.*;

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
			if (loginService.regexValidationForUsername(username)
					&& loginService.regexValidationForPassword(password)) {
				System.out.println("Reaching if block");
				RequestDispatcher dispatcher = request.getRequestDispatcher("//WEB-INF//Views//Registration.jsp");
				request.setAttribute("username", username);
				request.setAttribute("messege", "Invalid Username");
				request.setAttribute("messegeForPassword", "Invalid Password");
				dispatcher.forward(request, response);
				return;
			} else if (loginService.regexValidationForUsername(username)
					&& loginService.isPasswordNotEqualsConfirmPassword(password, confirmPassword)) {
				System.out.println("Reaching else-if block 1");
				RequestDispatcher dispatcher = request.getRequestDispatcher("//WEB-INF//Views//Registration.jsp");
				request.setAttribute("username", username);
				request.setAttribute("messege", "Invalid Username");
				request.setAttribute("messegeForPassword",
						"Both Password are not same.Try Again");
				dispatcher.forward(request, response);
				return;
			}
			else if (loginService.regexValidationForUsername(username)) {
				System.out.println("Reaching else-if block 2");
				RequestDispatcher dispatcher = request.getRequestDispatcher("//WEB-INF//Views//Registration.jsp");
				request.setAttribute("username", username);
				request.setAttribute("messege", "Invalid Username");
				dispatcher.forward(request, response);
				return;
			} 
			else if (loginService.isUsernameExistsInDB(username)
					&& loginService.regexValidationForPassword(password)) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("//WEB-INF//Views//Registration.jsp");
				System.out.println("Username is already exits...");
				request.setAttribute("messege", "Username is already Exists...");
				request.setAttribute("messegeForPassword", "Invalid Password");
				request.setAttribute("username", username);
				dispatcher.forward(request, response);
				return;
			} 
			
			else if (loginService.isUsernameExistsInDB(username)
					&& loginService.isPasswordNotEqualsConfirmPassword(password, confirmPassword)) {
				System.out.println("Reaching else-if block 5");
				RequestDispatcher dispatcher = request.getRequestDispatcher("//WEB-INF//Views//Registration.jsp");
				request.setAttribute("messege", "Username is already Exists...");
				request.setAttribute("messegeForPassword",
						"Both Password are not same.Try Again");
				request.setAttribute("username", username);
				dispatcher.forward(request, response);
				return;
			}
			else if (loginService.regexValidationForPassword(password)) {
				System.out.println("Reaching else-if block 3");
				RequestDispatcher dispatcher = request.getRequestDispatcher("//WEB-INF//Views//Registration.jsp");
				request.setAttribute("username", username);
				request.setAttribute("messegeForPassword", "Invalid Password");
				dispatcher.forward(request, response);
				return;
			} else if (loginService.isUsernameExistsInDB(username)) {
				System.out.println("Reaching else-if block 6");
				RequestDispatcher dispatcher = request.getRequestDispatcher("//WEB-INF//Views//Registration.jsp");
				request.setAttribute("messege", "Username is already Exists...");
				request.setAttribute("username", username);
				dispatcher.forward(request, response);
				return;
			} else if (loginService.isPasswordNotEqualsConfirmPassword(password, confirmPassword)) {
				System.out.println("Reaching else-if block 7");
				RequestDispatcher dispatcher = request.getRequestDispatcher("//WEB-INF//Views//Registration.jsp");
				request.setAttribute("messegeForPassword",
						"Both Password are not same.Try Again");
				request.setAttribute("username", username);
				dispatcher.forward(request, response);
				return;
			} else {
				System.out.println("Reaching else block ");
				loginService.insertLogin(registrationModel);
				RequestDispatcher dispatcher1 = request.getRequestDispatcher("//WEB-INF//Views//Login.jsp");
				request.setAttribute("messege", "Registration Successfull");
				dispatcher1.forward(request, response);
			}
			return;
		}
		String action = request.getServletPath();
		if (action.equals("/signin")) {
			registration(request, response);
		} else if (action.equals("/logout")) {
			logOut(request, response);
		} else {
			loginEmployee(request, response);
		}
	}

	private void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		session.invalidate();
		RequestDispatcher dispatcher = request.getRequestDispatcher("//WEB-INF//Views//Login.jsp");
		request.setAttribute("LogOutMessege", "You have Successfully LogOut...");
		dispatcher.forward(request, response);
	}

	private void loginEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		response.setDateHeader("Expire", 0);
		RequestDispatcher dispatcher = request.getRequestDispatcher("//WEB-INF//Views//Login.jsp");
		LoginService loginService = new LoginServiceImpl();
		String usernameLogin = request.getParameter("Username");
		String passwordLogin = request.getParameter("Password");
		HttpSession session = request.getSession();
		if ((usernameLogin != null && passwordLogin != null)) {
			if (loginService.isUsernameNotExistsInDBForLogin(usernameLogin)) {
				request.setAttribute("messegeLogin", "Invalid Credentials...");
				request.setAttribute("username", usernameLogin);
				dispatcher.forward(request, response);
				return;
			} else if (loginService.isPasswordNotExistsInDBForLogin(usernameLogin, passwordLogin)) {
				request.setAttribute("username", usernameLogin);
				request.setAttribute("messegeLogin", "Invalid Credentials...");
				dispatcher.forward(request, response);
				return;
			} else {
				int id = loginService.getId(usernameLogin, passwordLogin);
				session.setAttribute("id", id);
				session.setAttribute("usernameLogin", usernameLogin);
				response.sendRedirect("http://localhost:8080/list");
			}
			return;
		}
		Integer loginId = (Integer) session.getAttribute("id");
		if (session != null && loginId != null) {
			session.setAttribute("id", loginId);
			response.sendRedirect("http://localhost:8080/list");
			return;
		}
		dispatcher.forward(request, response);
	}

	private void registration(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("//WEB-INF//Views//Registration.jsp");
		HttpSession session = request.getSession();
		Integer loginId = (Integer) session.getAttribute("id");
		if (session != null && loginId != null) {
			session.setAttribute("id", loginId);
			response.sendRedirect("http://localhost:8080/list");
			return;
		}
		dispatcher.forward(request, response);
	}
}

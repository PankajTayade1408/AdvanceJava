package employeecrudmaven.controller;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import employeecrudmaven.dao.LoginDAO;
import employeecrudmaven.dao.LoginDAOImpl;
import employeecrudmaven.model.EmployeeModel;
import employeecrudmaven.model.LoginModel;
import employeecrudmaven.service.EmployeeService;
import employeecrudmaven.service.EmployeeServiceImpl;
import employeecrudmaven.service.LoginService;
import employeecrudmaven.service.LoginServiceImpl;

@WebServlet("/")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmpassword");
		LoginService employeeService = new LoginServiceImpl();
		LoginModel registrationModel = new LoginModel(username, password);

		if (username != null && password != null && confirmPassword != null) {
			System.out.println(employeeService.isUsernameExistsInDB(username, password, confirmPassword));
			if (employeeService.isUsernameExistsInDB(username, password, confirmPassword)) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("//WEB-INF//Views//Registration.jsp");
				request.setAttribute("messege", "Username is already Exists...");
				request.setAttribute("username", username);
				dispatcher.forward(request, response);
			} else if (employeeService.isPasswordEqualsConfirmPassword(username, password, confirmPassword)) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("//WEB-INF//Views//Registration.jsp");
				request.setAttribute("messege",
						"Entered Password and Confirmed Password are Not Same..Enter the Correct Password...");
				request.setAttribute("username", username);
				dispatcher.forward(request, response);
			} else {
				employeeService.insertLogin(registrationModel);
				RequestDispatcher dispatcher = request.getRequestDispatcher("//WEB-INF//Views//Login.jsp");
				request.setAttribute("messege", "Registration Successfull");
				dispatcher.forward(request, response);
			}
		}

		String usernameLogin = request.getParameter("Username");
		String passwordLogin = request.getParameter("Password");
		LinkedHashSet<String> passwordSet=new LinkedHashSet<String>();
		LoginDAO loginDAO=new LoginDAOImpl();
		passwordSet=loginDAO.getPassword();
		if (usernameLogin != null && passwordLogin != null) {
			if (employeeService.isUsernameExistsInDBForLogin(usernameLogin)) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("//WEB-INF//Views//Login.jsp");
				request.setAttribute("messegeLogin", "Username is not exists...Enter the correct the Username");
				request.setAttribute("username", usernameLogin);
				dispatcher.forward(request, response);
			} else if (employeeService.isPasswordExistsInDBForLogin(passwordLogin)) 
				{
				RequestDispatcher dispatcher = request.getRequestDispatcher("//WEB-INF//Views//Login.jsp");
				request.setAttribute("username", usernameLogin);
				request.setAttribute("messegeLogin", "Password is not exists...Enter the correct the Password");
				dispatcher.forward(request, response);
			} else {
				LoginModel loginModel = new LoginModel(usernameLogin,passwordLogin);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/list");
				request.setAttribute("password", passwordLogin);
				request.setAttribute("passwordSet", passwordSet);
				request.setAttribute("loginModel", loginModel);
				request.setAttribute("messegeLogin", "Login Successfull");
				dispatcher.forward(request, response);
			}
		}
		
		String action = request.getServletPath();
		System.out.println("Action is (Login)"+action);
		if (action.equals("/newuser") ) {
			registration(request, response);
		}	else   {
			loginEmployee(request, response);
		}
	}

	private void loginEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("//WEB-INF//Views//Login.jsp");
		dispatcher.forward(request, response);
	}

	private void registration(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("//WEB-INF//Views//Registration.jsp");
		dispatcher.forward(request, response);

	}
}

package employeecrudmaven.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import employeecrudmaven.dao.LoginDAOImpl;
import employeecrudmaven.model.EmployeeModel;
import employeecrudmaven.model.LoginModel;
import employeecrudmaven.service.EmployeeService;
import employeecrudmaven.service.EmployeeServiceImpl;
import employeecrudmaven.service.LoginService;
import employeecrudmaven.service.LoginServiceImpl;

@WebServlet("/list")
public class EmployeeController extends HttpServlet {
	EmployeeService employeeService = new EmployeeServiceImpl();
	int count = 0;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		switch (action) {
		case "/new":
			showNewForm(request, response);
			break;

		case "/insert":
			try {
				insertNewEmployee(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case "/delete":
			try {
				deleteEmployee(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;

		case "/edit":
			showEditForm(request, response);
			break;

		case "/update":
			try {
				updateEmployee(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;

		case "/list":
			listEmployee(request, response);
			break;
		default:
			break;
		}
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		response.setDateHeader("Expire", 0);
		HttpSession session = request.getSession();
		Integer loginId = (Integer) session.getAttribute("id");
		EmployeeModel employee = employeeService.getEmployeeById(loginId);
		if (session != null && loginId != null) {
			response.setDateHeader("Expire", 0);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			dispatcher.forward(request, response);
			return;
		} else {
			response.sendRedirect("http://localhost:8080/");
		}
	}

	private void insertNewEmployee(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ClassNotFoundException, ServletException {
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		response.setDateHeader("Expire", 0);
		LoginService loginService = new LoginServiceImpl();
		HttpSession session = request.getSession(true);
		Integer loginId = (Integer) session.getAttribute("id");
		if (session != null) {
			String firstName = request.getParameter("empfname");
			String lastName = request.getParameter("emplname");
			String employeeSkillsArray[] = new String[0];
			String checkedEmployeeSkills = "";
			LinkedHashSet<String> skills = new LinkedHashSet<String>();
			if (request.getParameterValues("empSkills") == null) {
				skills.add("");
			} else {
				employeeSkillsArray = request.getParameterValues("empSkills");
				for (int i = 0; i < employeeSkillsArray.length; i++) {
					checkedEmployeeSkills = employeeSkillsArray[i];
					skills.add(checkedEmployeeSkills);
				}
			}
			String age = request.getParameter("empage");
			String salary = request.getParameter("empsalary");
			String dateOfJoining = request.getParameter("empdoj");
			EmployeeModel employee = new EmployeeModel(firstName, lastName, age, salary, dateOfJoining, loginId);
			if (firstName != null && lastName != null) {
				System.out.println("FirstName is empty" + employeeService.regexValidationForFirstName(firstName));
				if ((employeeService.regexValidationForFirstName(firstName)
						&& employeeService.regexValidationForLastName(lastName)
						&& employeeService.regexValidationForAge(age)
						&& employeeService.regexValidationForSalary(salary)) == true) {
					System.out.println("if block");
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("firstName", "Enter the valid first name");
					request.setAttribute("lastName", "Enter the valid Last name");
					request.setAttribute("age", "Enter the valid Age");
					request.setAttribute("salary", "Enter the valid Salary");
					dispatcher.forward(request, response);
				} else if (employeeService.regexValidationForFirstName(firstName)
						&& employeeService.regexValidationForAge(age)
						&& employeeService.regexValidationForSalary(salary) == true) {
					System.out.println("if block");
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("firstName", "Enter the valid first name");
					request.setAttribute("age", "Enter the valid Age");
					request.setAttribute("salary", "Enter the valid Salary");
					dispatcher.forward(request, response);
				} else if (employeeService.regexValidationForFirstName(firstName)
						&& employeeService.regexValidationForAge(age) == true) {
					System.out.println("First else if block");
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("firstName", "Enter the valid first name");
					request.setAttribute("age", "Enter the valid Age");
					dispatcher.forward(request, response);
				} else if (employeeService.regexValidationForFirstName(firstName)
						&& employeeService.regexValidationForSalary(salary) == true) {
					System.out.println("Second else if block");
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("firstName", "Enter the valid first name");
					request.setAttribute("salary", "Enter the valid Salary");
					dispatcher.forward(request, response);
				} else if (employeeService.regexValidationForLastName(lastName)
						&& employeeService.regexValidationForAge(age)
						&& employeeService.regexValidationForSalary(salary) == true) {
					System.out.println("Third else if block");
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("lastName", "Enter the valid last name");
					request.setAttribute("age", "Enter the valid Age");
					request.setAttribute("salary", "Enter the valid Salary");
					dispatcher.forward(request, response);
				} else if (employeeService.regexValidationForLastName(lastName)
						&& employeeService.regexValidationForAge(age) == true) {
					System.out.println("Fourth else if block");
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("lastName", "Enter the valid last name");
					request.setAttribute("age", "Enter the valid Age");
					dispatcher.forward(request, response);
				} else if (employeeService.regexValidationForLastName(lastName)
						&& employeeService.regexValidationForSalary(salary) == true) {
					System.out.println("Fifth else if block");
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("lastName", "Enter the valid last name");
					request.setAttribute("salary", "Enter the valid Salary");
					dispatcher.forward(request, response);
				} else if (employeeService.regexValidationForAge(age)
						&& employeeService.regexValidationForSalary(salary) == true) {
					System.out.println("Sixth else if block");
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("age", "Enter the valid Age");
					request.setAttribute("salary", "Enter the valid Salary");
					dispatcher.forward(request, response);
				} else if (employeeService.regexValidationForFirstName(firstName)
						&& employeeService.regexValidationForLastName(lastName) == true) {
					System.out.println("Seventh else if block");
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("firstName", "Enter the valid first name");
					request.setAttribute("lastName", "Enter the valid Last name");
					dispatcher.forward(request, response);
				} else if (employeeService.regexValidationForFirstName(firstName)) {
					System.out.println("Eighth else if block");
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("firstName", "Enter the valid first name");
					dispatcher.forward(request, response);
				} else if (employeeService.regexValidationForLastName(lastName)) {
					System.out.println("Ninth else if block");
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("lastName", "Enter the valid last name");
					dispatcher.forward(request, response);
				} else if (employeeService.regexValidationForAge(age)) {
					System.out.println("Tenth else if block");
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("age", "Enter the valid Age");
					dispatcher.forward(request, response);
				} else if (employeeService.regexValidationForSalary(salary)) {
					System.out.println("Eleventh else if block");
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("salary", "Enter the valid Salary");
					dispatcher.forward(request, response);
				} else {
					employeeService.insertEmployee(employee);
					employeeService.insertEmployeeSkillsById(employee.getId(), skills);
					response.sendRedirect(request.getContextPath() + "/list");
				}
			}
			response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
			response.setDateHeader("Expire", 0);
		}
	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			employeeService.deleteEmployee(id);
			response.sendRedirect(request.getContextPath() + "/list");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		response.setDateHeader("Expire", 0);
		EmployeeModel selectedEmployee;
		try {
			HttpSession session = request.getSession();
			Integer loginId = (Integer) session.getAttribute("id");
			if (session != null && loginId != null) {
				selectedEmployee = employeeService.getEmployeeById(id);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("//WEB-INF//Views//EmployeeRegistration.jsp");
				request.setAttribute("employee", selectedEmployee);
				dispatcher.forward(request, response);
			} else {
				response.sendRedirect("http://localhost:8080/");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		String firstName = request.getParameter("empfname");
		String lastName = request.getParameter("emplname");
		String employeeSkillsArray[] = new String[0];
		String checkedEmployeeSkills = "";
		LinkedHashSet<String> skills = new LinkedHashSet<String>();
		if (request.getParameterValues("empSkills") == null) {
			skills.add("");
		} else {
			employeeSkillsArray = request.getParameterValues("empSkills");
			for (int i = 0; i < employeeSkillsArray.length; i++) {
				checkedEmployeeSkills = employeeSkillsArray[i];
				skills.add(checkedEmployeeSkills);
			}
		}
		String age = request.getParameter("empage");
		String salary = request.getParameter("empsalary");
		String dateOfJoining = request.getParameter("empdoj");
		if ((employeeService.regexValidationForFirstName(firstName)
				&& employeeService.regexValidationForLastName(lastName) && employeeService.regexValidationForAge(age)
				&& employeeService.regexValidationForSalary(salary)) == true) {
			System.out.println("if block");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("firstName", "Enter the valid first name");
			request.setAttribute("lastName", "Enter the valid Last name");
			request.setAttribute("age", "Enter the valid Age");
			request.setAttribute("salary", "Enter the valid Salary");
			dispatcher.forward(request, response);
		} else if (employeeService.regexValidationForFirstName(firstName) && employeeService.regexValidationForAge(age)
				&& employeeService.regexValidationForSalary(salary) == true) {
			System.out.println("First if block");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("firstName", "Enter the valid first name");
			request.setAttribute("age", "Enter the valid Age");
			request.setAttribute("salary", "Enter the valid Salary");
			dispatcher.forward(request, response);
		} else if (employeeService.regexValidationForFirstName(firstName)
				&& employeeService.regexValidationForAge(age) == true) {
			System.out.println("First if block");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("firstName", "Enter the valid first name");
			request.setAttribute("age", "Enter the valid Age");
			dispatcher.forward(request, response);
		} else if (employeeService.regexValidationForFirstName(firstName)
				&& employeeService.regexValidationForSalary(salary) == true) {
			System.out.println("First if block");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("firstName", "Enter the valid first name");
			request.setAttribute("salary", "Enter the valid Salary");
			dispatcher.forward(request, response);
		} else if (employeeService.regexValidationForLastName(lastName) && employeeService.regexValidationForAge(age)
				&& employeeService.regexValidationForSalary(salary) == true) {
			System.out.println("Second if block");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("lastName", "Enter the valid last name");
			request.setAttribute("age", "Enter the valid Age");
			request.setAttribute("salary", "Enter the valid Salary");
			dispatcher.forward(request, response);
		} else if (employeeService.regexValidationForLastName(lastName)
				&& employeeService.regexValidationForAge(age) == true) {
			System.out.println("Second if block");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("lastName", "Enter the valid last name");
			request.setAttribute("age", "Enter the valid Age");
			dispatcher.forward(request, response);
		} else if (employeeService.regexValidationForLastName(lastName)
				&& employeeService.regexValidationForSalary(salary) == true) {
			System.out.println("Second if block");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("lastName", "Enter the valid last name");
			request.setAttribute("salary", "Enter the valid Salary");
			dispatcher.forward(request, response);
		} else if (employeeService.regexValidationForAge(age)
				&& employeeService.regexValidationForSalary(salary) == true) {
			System.out.println("Third if block");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("age", "Enter the valid Age");
			request.setAttribute("salary", "Enter the valid Salary");
			dispatcher.forward(request, response);
		} else if (employeeService.regexValidationForFirstName(firstName)
				&& employeeService.regexValidationForLastName(lastName) == true) {
			System.out.println("Fourth if block");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("firstName", "Enter the valid first name");
			request.setAttribute("lastName", "Enter the valid Last name");
			dispatcher.forward(request, response);
		} else if (employeeService.regexValidationForFirstName(firstName)) {
			System.out.println("Fifth if block");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("firstName", "Enter the valid first name");
			dispatcher.forward(request, response);
		} else if (employeeService.regexValidationForLastName(lastName)) {
			System.out.println("Sixth if block");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("lastName", "Enter the valid last name");
			dispatcher.forward(request, response);
		} else if (employeeService.regexValidationForAge(age)) {
			System.out.println("Seventh if block");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("age", "Enter the valid Age");
			dispatcher.forward(request, response);
		} else if (employeeService.regexValidationForSalary(salary)) {
			System.out.println("Eighth if block");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("salary", "Enter the valid Salary");
			dispatcher.forward(request, response);
		} else {
			EmployeeModel employee = new EmployeeModel(id, firstName, lastName, skills, age, salary, dateOfJoining);
			employeeService.updateEmployee(employee);
			response.sendRedirect(request.getContextPath() + "/list");
		}
		EmployeeModel empskills = new EmployeeModel(id, skills);
		employeeService.updateEmployeeSkills(empskills);
		response.sendRedirect(request.getContextPath() + "/list");
	}

	private void listEmployee(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
			response.setDateHeader("Expire", 0);
			LoginService loginService = new LoginServiceImpl();
			HttpSession session = request.getSession(true);
			Integer loginId = (Integer) session.getAttribute("id");
			if (session != null && loginId != null) {
				List<EmployeeModel> employeeList = employeeService.getAllEmployee(loginId);
				String username = (String) session.getAttribute("usernameLogin");
				request.setAttribute("empList", employeeList);
				request.setAttribute("username", username);
				RequestDispatcher dispatcher = request.getRequestDispatcher("//WEB-INF//Views//index.jsp");
				dispatcher.forward(request, response);
			} else {
				response.sendRedirect("http://localhost:8080/");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

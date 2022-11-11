package employeecrudmaven.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import employeecrudmaven.model.EmployeeModel;
import employeecrudmaven.service.EmployeeService;
import employeecrudmaven.service.EmployeeServiceImpl;

@WebServlet("/list")
public class EmployeeController extends HttpServlet {
	EmployeeService employeeService = new EmployeeServiceImpl();

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
		if (session != null && loginId != null) {
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
			String employeeSalary = request.getParameter("empsalary");
			String salary = "";
			String country = request.getParameter("country");
			if (employeeSalary.isEmpty()) {
				salary = "";
			} else {
				float floatSalary = Float.parseFloat(employeeSalary);
				DecimalFormat decimalFormat = new DecimalFormat("0.00");
				salary = decimalFormat.format(floatSalary);
			}
			String dateOfJoining = request.getParameter("empdoj");
			String invalidFirstName = "";
			String invalidLastName = "";
			String invalidAge = "";
			String invalidSalary = "";
			EmployeeModel employee = new EmployeeModel(firstName, lastName, skills, age, salary, dateOfJoining, country,
					loginId);
			if (firstName != null && lastName != null) {
				if ((employeeService.regexValidationForFirstName(firstName)
						&& employeeService.regexValidationForLastName(lastName)
						&& employeeService.regexValidationForAge(age)
						&& employeeService.regexValidationForSalary(salary) && dateOfJoining.isEmpty()
						&& country == null) == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("firstName", "Enter the valid first name");
					request.setAttribute("lastName", "Enter the valid Last name");
					request.setAttribute("age", "Enter the valid Age");
					request.setAttribute("salary", "Enter the valid Salary");
					request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
					request.setAttribute("country", "Please.Select the Country");
					EmployeeModel invalidData = new EmployeeModel(invalidFirstName, invalidLastName, skills, invalidAge,
							invalidSalary, dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if ((employeeService.regexValidationForFirstName(firstName)
						&& employeeService.regexValidationForLastName(lastName)
						&& employeeService.regexValidationForAge(age)
						&& employeeService.regexValidationForSalary(salary) && country == null) == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("firstName", "Enter the valid first name");
					request.setAttribute("lastName", "Enter the valid Last name");
					request.setAttribute("age", "Enter the valid Age");
					request.setAttribute("salary", "Enter the valid Salary");
					request.setAttribute("country", "Please.Select the Country");
					EmployeeModel invalidData = new EmployeeModel(invalidFirstName, invalidLastName, skills, invalidAge,
							invalidSalary, dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if ((employeeService.regexValidationForFirstName(firstName)
						&& employeeService.regexValidationForLastName(lastName)
						&& employeeService.regexValidationForAge(age)
						&& employeeService.regexValidationForSalary(salary) && dateOfJoining.isEmpty()) == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("firstName", "Enter the valid first name");
					request.setAttribute("lastName", "Enter the valid Last name");
					request.setAttribute("age", "Enter the valid Age");
					request.setAttribute("salary", "Enter the valid salary");
					request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
					EmployeeModel invalidData = new EmployeeModel(invalidFirstName, invalidLastName, skills, invalidAge,
							invalidSalary, dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if ((employeeService.regexValidationForFirstName(firstName)
						&& employeeService.regexValidationForLastName(lastName)
						&& employeeService.regexValidationForAge(age)
						&& employeeService.regexValidationForSalary(salary)) == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("firstName", "Enter the valid first name");
					request.setAttribute("lastName", "Enter the valid Last name");
					request.setAttribute("age", "Enter the valid Age");
					request.setAttribute("salary", "Enter the valid Salary");
					EmployeeModel invalidData = new EmployeeModel(invalidFirstName, invalidLastName, skills, invalidAge,
							invalidSalary, dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if ((employeeService.regexValidationForFirstName(firstName)
						&& employeeService.regexValidationForLastName(lastName)
						&& employeeService.regexValidationForAge(age) && country == null
						&& dateOfJoining.isEmpty()) == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("firstName", "Enter the valid first name");
					request.setAttribute("lastName", "Enter the valid Last name");
					request.setAttribute("age", "Enter the valid Age");
					request.setAttribute("country", "Please.Select the Country");
					request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
					EmployeeModel invalidData = new EmployeeModel(invalidFirstName, invalidLastName, skills, invalidAge,
							salary, dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if ((employeeService.regexValidationForFirstName(firstName)
						&& employeeService.regexValidationForLastName(lastName)
						&& employeeService.regexValidationForAge(age)) == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("firstName", "Enter the valid first name");
					request.setAttribute("lastName", "Enter the valid Last name");
					request.setAttribute("age", "Enter the valid Age");
					EmployeeModel invalidData = new EmployeeModel(invalidFirstName, invalidLastName, skills, invalidAge,
							salary, dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if ((employeeService.regexValidationForFirstName(firstName)
						&& employeeService.regexValidationForLastName(lastName)
						&& employeeService.regexValidationForAge(age) && country == null) == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("firstName", "Enter the valid first name");
					request.setAttribute("lastName", "Enter the valid Last name");
					request.setAttribute("age", "Enter the valid Age");
					request.setAttribute("country", "Please.Select the Country");
					EmployeeModel invalidData = new EmployeeModel(invalidFirstName, invalidLastName, skills, invalidAge,
							salary, dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if ((employeeService.regexValidationForFirstName(firstName)
						&& employeeService.regexValidationForLastName(lastName)
						&& employeeService.regexValidationForSalary(salary) && country == null
						&& employee.getDoj().isEmpty()) == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("firstName", "Enter the valid first name");
					request.setAttribute("lastName", "Enter the valid Last name");
					request.setAttribute("salary", "Enter the valid Salary");
					request.setAttribute("country", "Please.Select the Country");
					request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
					EmployeeModel invalidData = new EmployeeModel(invalidFirstName, invalidLastName, skills, age,
							invalidSalary, dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if ((employeeService.regexValidationForFirstName(firstName)
						&& employeeService.regexValidationForLastName(lastName)
						&& employeeService.regexValidationForSalary(salary) && country == null) == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("firstName", "Enter the valid first name");
					request.setAttribute("lastName", "Enter the valid Last name");
					request.setAttribute("salary", "Enter the valid Salary");
					request.setAttribute("country", "Please.Select the country");
					EmployeeModel invalidData = new EmployeeModel(invalidFirstName, invalidLastName, skills, age,
							invalidSalary, dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if ((employeeService.regexValidationForFirstName(firstName)
						&& employeeService.regexValidationForLastName(lastName)
						&& employeeService.regexValidationForSalary(salary)) == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("firstName", "Enter the valid first name");
					request.setAttribute("lastName", "Enter the valid Last name");
					request.setAttribute("salary", "Enter the valid Salary");
					EmployeeModel invalidData = new EmployeeModel(invalidFirstName, invalidLastName, skills, age,
							invalidSalary, dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if ((employeeService.regexValidationForFirstName(firstName)
						&& employeeService.regexValidationForLastName(lastName) && country == null
						&& employee.getDoj().isEmpty()) == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("firstName", "Enter the valid first name");
					request.setAttribute("lastName", "Enter the valid Last name");
					request.setAttribute("country", "Please.Select the country");
					request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
					EmployeeModel invalidData = new EmployeeModel(invalidFirstName, invalidLastName, skills, age,
							invalidSalary, dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if ((employeeService.regexValidationForFirstName(firstName)
						&& employeeService.regexValidationForLastName(lastName) && country == null) == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("firstName", "Enter the valid first name");
					request.setAttribute("lastName", "Enter the valid Last name");
					request.setAttribute("country", "Please.Select the country");
					EmployeeModel invalidData = new EmployeeModel(invalidFirstName, invalidLastName, skills, age,
							invalidSalary, dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if ((employeeService.regexValidationForFirstName(firstName) && country == null
						&& employee.getDoj().isEmpty()) == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("firstName", "Enter the valid first name");
					request.setAttribute("country", "Please.Select the country");
					request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
					EmployeeModel invalidData = new EmployeeModel(invalidFirstName, lastName, skills, age, salary,
							dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if ((employeeService.regexValidationForSalary(salary) && country == null
						&& employee.getDoj().isEmpty()) == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("salary", "Enter the valid salary");
					request.setAttribute("country", "Please.Select the country");
					request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
					EmployeeModel invalidData = new EmployeeModel(firstName, lastName, skills, age, invalidSalary,
							dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if ((employeeService.regexValidationForFirstName(firstName)
						&& employeeService.regexValidationForLastName(lastName) && dateOfJoining.isEmpty()) == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("firstName", "Enter the valid first name");
					request.setAttribute("lastName", "Enter the valid Last name");
					request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
					EmployeeModel invalidData = new EmployeeModel(invalidFirstName, invalidLastName, skills, age,
							salary, dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (employeeService.regexValidationForFirstName(firstName)
						&& employeeService.regexValidationForAge(age)
						&& employeeService.regexValidationForSalary(salary) && country == null
						&& dateOfJoining.isEmpty() == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("firstName", "Enter the valid first name");
					request.setAttribute("age", "Enter the valid Age");
					request.setAttribute("salary", "Enter the valid Salary");
					request.setAttribute("country", "Please.Select the Country");
					request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
					EmployeeModel invalidData = new EmployeeModel(invalidFirstName, lastName, skills, invalidAge,
							invalidSalary, dateOfJoining, country, loginId);
					request.setAttribute("invalidData", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (employeeService.regexValidationForFirstName(firstName)
						&& employeeService.regexValidationForAge(age)
						&& employeeService.regexValidationForSalary(salary) && country == null == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("firstName", "Enter the valid first name");
					request.setAttribute("age", "Enter the valid Age");
					request.setAttribute("salary", "Enter the valid Salary");
					request.setAttribute("country", "Please.Select the Country");
					EmployeeModel invalidData = new EmployeeModel(invalidFirstName, lastName, skills, invalidAge,
							invalidSalary, dateOfJoining, country, loginId);
					request.setAttribute("invalidData", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (employeeService.regexValidationForFirstName(firstName)
						&& employeeService.regexValidationForAge(age)
						&& employeeService.regexValidationForSalary(salary) == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("firstName", "Enter the valid first name");
					request.setAttribute("age", "Enter the valid Age");
					request.setAttribute("salary", "Enter the valid Salary");
					EmployeeModel invalidData = new EmployeeModel(invalidFirstName, lastName, skills, invalidAge,
							invalidSalary, dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (employeeService.regexValidationForFirstName(firstName)
						&& employeeService.regexValidationForAge(age) && dateOfJoining.isEmpty() == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("firstName", "Enter the valid first name");
					request.setAttribute("age", "Enter the valid Age");
					request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
					EmployeeModel invalidData = new EmployeeModel(invalidFirstName, lastName, skills, invalidAge,
							salary, dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (employeeService.regexValidationForFirstName(firstName)
						&& employeeService.regexValidationForSalary(salary) && country == null
						&& dateOfJoining.isEmpty() == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("firstName", "Enter the valid first name");
					request.setAttribute("salary", "Enter the valid Salary");
					request.setAttribute("country", "Please.Select the Country");
					request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
					EmployeeModel invalidData = new EmployeeModel(invalidFirstName, lastName, skills, age,
							invalidSalary, dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (employeeService.regexValidationForFirstName(firstName)
						&& employeeService.regexValidationForSalary(salary) && country == null == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("firstName", "Enter the valid first name");
					request.setAttribute("salary", "Enter the valid Salary");
					request.setAttribute("country", "Please.Select the Country");
					EmployeeModel invalidData = new EmployeeModel(invalidFirstName, lastName, skills, age,
							invalidSalary, dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (employeeService.regexValidationForFirstName(firstName) && country == null
						&& dateOfJoining.isEmpty() == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("firstName", "Enter the valid first name");
					request.setAttribute("country", "Please.Select the Country");
					request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
					EmployeeModel invalidData = new EmployeeModel(invalidFirstName, lastName, skills, age, salary,
							dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (employeeService.regexValidationForFirstName(firstName) && country == null == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("firstName", "Enter the valid first name");
					request.setAttribute("country", "Please.Select the Country");
					EmployeeModel invalidData = new EmployeeModel(invalidFirstName, lastName, skills, age, salary,
							dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (employeeService.regexValidationForFirstName(firstName) && dateOfJoining.isEmpty() == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("firstName", "Enter the valid first name");
					request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
					EmployeeModel invalidData = new EmployeeModel(invalidFirstName, lastName, skills, age, salary,
							dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (employeeService.regexValidationForFirstName(firstName)
						&& employeeService.regexValidationForAge(age) == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("firstName", "Enter the valid first name");
					request.setAttribute("age", "Enter the valid Age");
					EmployeeModel invalidData = new EmployeeModel(invalidFirstName, lastName, skills, invalidAge,
							salary, dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (employeeService.regexValidationForFirstName(firstName)
						&& employeeService.regexValidationForSalary(salary) == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("firstName", "Enter the valid first name");
					request.setAttribute("salary", "Enter the valid Salary");
					EmployeeModel invalidData = new EmployeeModel(invalidFirstName, lastName, skills, age,
							invalidSalary, dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (employeeService.regexValidationForLastName(lastName)
						&& employeeService.regexValidationForAge(age)
						&& employeeService.regexValidationForSalary(salary) && country == null
						&& dateOfJoining.isEmpty() == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("lastName", "Enter the valid last name");
					request.setAttribute("age", "Enter the valid Age");
					request.setAttribute("salary", "Enter the valid Salary");
					request.setAttribute("country", "Please.Select the Country");
					request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
					EmployeeModel invalidData = new EmployeeModel(firstName, invalidLastName, skills, invalidAge,
							invalidSalary, dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (employeeService.regexValidationForLastName(lastName)
						&& employeeService.regexValidationForAge(age)
						&& employeeService.regexValidationForSalary(salary) && country == null == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("lastName", "Enter the valid last name");
					request.setAttribute("age", "Enter the valid Age");
					request.setAttribute("salary", "Enter the valid Salary");
					request.setAttribute("country", "Please.Select the Country");
					EmployeeModel invalidData = new EmployeeModel(firstName, invalidLastName, skills, invalidAge,
							invalidSalary, dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (employeeService.regexValidationForLastName(lastName)
						&& employeeService.regexValidationForAge(age)
						&& employeeService.regexValidationForSalary(salary) == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("lastName", "Enter the valid last name");
					request.setAttribute("age", "Enter the valid Age");
					request.setAttribute("salary", "Enter the valid Salary");
					EmployeeModel invalidData = new EmployeeModel(firstName, invalidLastName, skills, invalidAge,
							invalidSalary, dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if ((employeeService.regexValidationForLastName(lastName)
						&& employeeService.regexValidationForAge(age)
						&& employeeService.regexValidationForSalary(salary) && dateOfJoining.isEmpty()) == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("lastName", "Enter the valid last name");
					request.setAttribute("age", "Enter the valid Age");
					request.setAttribute("salary", "Enter the Valid Salary");
					request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
					EmployeeModel invalidData = new EmployeeModel(firstName, invalidLastName, skills, invalidAge,
							invalidSalary, dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if ((employeeService.regexValidationForLastName(lastName)
						&& employeeService.regexValidationForAge(age) && dateOfJoining.isEmpty()) == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("lastName", "Enter the valid last name");
					request.setAttribute("age", "Enter the valid Age");
					request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
					EmployeeModel invalidData = new EmployeeModel(firstName, invalidLastName, skills, invalidAge,
							salary, dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (employeeService.regexValidationForLastName(lastName)
						&& employeeService.regexValidationForAge(age) == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("lastName", "Enter the valid last name");
					request.setAttribute("age", "Enter the valid Age");
					EmployeeModel invalidData = new EmployeeModel(firstName, invalidLastName, skills, invalidAge,
							salary, dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (employeeService.regexValidationForLastName(lastName)
						&& employeeService.regexValidationForSalary(salary) && country == null
						&& dateOfJoining.isEmpty() == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("lastName", "Enter the valid last name");
					request.setAttribute("salary", "Enter the valid Salary");
					request.setAttribute("country", "Please.Select the Country");
					request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
					EmployeeModel invalidData = new EmployeeModel(firstName, invalidLastName, skills, age,
							invalidSalary, dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (employeeService.regexValidationForLastName(lastName)
						&& employeeService.regexValidationForSalary(salary) && country == null == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("lastName", "Enter the valid last name");
					request.setAttribute("salary", "Enter the valid Salary");
					request.setAttribute("country", "Please.Select the Country");
					EmployeeModel invalidData = new EmployeeModel(firstName, invalidLastName, skills, age,
							invalidSalary, dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (employeeService.regexValidationForLastName(lastName) && country == null
						&& dateOfJoining.isEmpty() == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("lastName", "Enter the valid last name");
					request.setAttribute("country", "Please.Select the Country");
					request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
					EmployeeModel invalidData = new EmployeeModel(firstName, invalidLastName, skills, age, salary,
							dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (employeeService.regexValidationForLastName(lastName)
						&& employeeService.regexValidationForSalary(salary) == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("lastName", "Enter the valid last name");
					request.setAttribute("salary", "Enter the valid Salary");
					EmployeeModel invalidData = new EmployeeModel(firstName, invalidLastName, skills, age,
							invalidSalary, dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (employeeService.regexValidationForLastName(lastName) && dateOfJoining.isEmpty() == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("lastName", "Enter the valid last name");
					request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
					EmployeeModel invalidData = new EmployeeModel(firstName, invalidLastName, skills, age, salary,
							dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (employeeService.regexValidationForLastName(lastName) && country == null == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("lastName", "Enter the valid last name");
					request.setAttribute("country", "Please.Select the Country");
					EmployeeModel invalidData = new EmployeeModel(firstName, invalidLastName, skills, age, salary,
							dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (employeeService.regexValidationForAge(age)
						&& employeeService.regexValidationForSalary(salary) && country == null
						&& dateOfJoining.isEmpty() == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("age", "Enter the valid Age");
					request.setAttribute("salary", "Enter the valid Salary");
					request.setAttribute("country", "Please.Select the Country");
					request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
					EmployeeModel invalidData = new EmployeeModel(firstName, lastName, skills, invalidAge,
							invalidSalary, dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if ((employeeService.regexValidationForAge(age)
						&& employeeService.regexValidationForSalary(salary) && country == null) == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("age", "Enter the valid Age");
					request.setAttribute("salary", "Enter the valid Salary");
					request.setAttribute("country", "Please.Select the Country");
					EmployeeModel invalidData = new EmployeeModel(firstName, lastName, skills, invalidAge,
							invalidSalary, dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (employeeService.regexValidationForSalary(salary) && dateOfJoining.isEmpty() == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("salary", "Enter the valid Salary");
					request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
					EmployeeModel invalidData = new EmployeeModel(firstName, lastName, skills, age, salary,
							dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (employeeService.regexValidationForAge(age) && country == null == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("age", "Enter the valid Age");
					request.setAttribute("country", "Please.Select the Country");
					EmployeeModel invalidData = new EmployeeModel(firstName, lastName, skills, invalidAge, salary,
							dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (employeeService.regexValidationForAge(age)
						&& employeeService.regexValidationForSalary(salary) == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("age", "Enter the valid Age");
					request.setAttribute("salary", "Enter the valid Salary");
					EmployeeModel invalidData = new EmployeeModel(firstName, lastName, skills, invalidAge,
							invalidSalary, dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (employeeService.regexValidationForAge(age) && employee.getDoj().isEmpty() == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("age", "Enter the valid Age");
					request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
					EmployeeModel invalidData = new EmployeeModel(firstName, lastName, skills, invalidAge, salary,
							dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if ((employeeService.regexValidationForSalary(salary) && country == null) == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("salary", "Enter the valid Salary");
					request.setAttribute("country", "Please.Select the Country");
					EmployeeModel invalidData = new EmployeeModel(firstName, lastName, skills, invalidAge,
							invalidSalary, dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (employeeService.regexValidationForFirstName(firstName)
						&& employeeService.regexValidationForLastName(lastName) == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("firstName", "Enter the valid first name");
					request.setAttribute("lastName", "Enter the valid Last name");
					EmployeeModel invalidData = new EmployeeModel(invalidFirstName, invalidLastName, skills, age,
							salary, dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (country == null && dateOfJoining.isEmpty() == true) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("country", "Please.Select the Country");
					request.setAttribute("dateOfJoining", "Please.Select the Date of Joining");
					EmployeeModel invalidData = new EmployeeModel(firstName, lastName, skills, age, salary,
							dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (employeeService.regexValidationForFirstName(firstName)) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("firstName", "Enter the valid first name");
					EmployeeModel invalidData = new EmployeeModel(invalidFirstName, lastName, skills, age, salary,
							dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (employeeService.regexValidationForLastName(lastName)) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("lastName", "Enter the valid last name");
					EmployeeModel invalidData = new EmployeeModel(firstName, invalidLastName, skills, age, salary,
							dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (employeeService.regexValidationForAge(age)) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("age", "Enter the valid Age");
					EmployeeModel invalidData = new EmployeeModel(firstName, lastName, skills, invalidAge, salary,
							dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (employeeService.regexValidationForSalary(salary)) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("salary", "Enter the valid Salary");
					EmployeeModel invalidData = new EmployeeModel(firstName, lastName, skills, age, invalidSalary,
							dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (country == null) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("country", "Please.Select the Country");
					EmployeeModel invalidData = new EmployeeModel(firstName, lastName, skills, age, salary,
							dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else if (dateOfJoining.isEmpty()) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
					request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
					EmployeeModel invalidData = new EmployeeModel(firstName, lastName, skills, age, salary,
							dateOfJoining, country, loginId);
					request.setAttribute("employee", invalidData);
					dispatcher.forward(request, response);
					return;
				} else {
					employeeService.insertEmployee(employee);
					employeeService.insertEmployeeSkillsById(employee.getId(), skills);
					response.sendRedirect(request.getContextPath() + "/list");
					return;
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
		String employeeSalary = request.getParameter("empsalary");
		String salary = "";
		if (employeeSalary.isEmpty()) {
			salary = "";
		} else {
			float floatSalary = Float.parseFloat(employeeSalary);
			DecimalFormat decimalFormat = new DecimalFormat("0.00");
			salary = decimalFormat.format(floatSalary);
		}
		String dateOfJoining = request.getParameter("empdoj");
		String country = request.getParameter("country");
		String invalidFirstName = "";
		String invalidLastName = "";
		String invalidAge = "";
		String invalidSalary = "";
		EmployeeModel employee = new EmployeeModel(id, firstName, lastName, skills, age, salary, dateOfJoining,
				country);
		if ((employeeService.regexValidationForFirstName(firstName)
				&& employeeService.regexValidationForLastName(lastName) && employeeService.regexValidationForAge(age)
				&& employeeService.regexValidationForSalary(salary) && dateOfJoining.isEmpty()
				&& country == null) == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("firstName", "Enter the valid first name");
			request.setAttribute("lastName", "Enter the valid Last name");
			request.setAttribute("age", "Enter the valid Age");
			request.setAttribute("salary", "Enter the valid Salary");
			request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
			request.setAttribute("country", "Please.Select the Country");
			EmployeeModel invalidData = new EmployeeModel(invalidFirstName, invalidLastName, skills, invalidAge,
					invalidSalary, dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if ((employeeService.regexValidationForFirstName(firstName)
				&& employeeService.regexValidationForLastName(lastName) && employeeService.regexValidationForAge(age)
				&& employeeService.regexValidationForSalary(salary) && country == null) == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("firstName", "Enter the valid first name");
			request.setAttribute("lastName", "Enter the valid Last name");
			request.setAttribute("age", "Enter the valid Age");
			request.setAttribute("salary", "Enter the valid Salary");
			request.setAttribute("country", "Please.Select the Country");
			EmployeeModel invalidData = new EmployeeModel(invalidFirstName, invalidLastName, skills, invalidAge,
					invalidSalary, dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if ((employeeService.regexValidationForFirstName(firstName)
				&& employeeService.regexValidationForLastName(lastName) && employeeService.regexValidationForAge(age)
				&& employeeService.regexValidationForSalary(salary)) == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("firstName", "Enter the valid first name");
			request.setAttribute("lastName", "Enter the valid Last name");
			request.setAttribute("age", "Enter the valid Age");
			request.setAttribute("salary", "Enter the valid Salary");
			EmployeeModel invalidData = new EmployeeModel(invalidFirstName, invalidLastName, skills, invalidAge,
					invalidSalary, dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if ((employeeService.regexValidationForFirstName(firstName)
				&& employeeService.regexValidationForLastName(lastName)
				&& employeeService.regexValidationForAge(age)) == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("firstName", "Enter the valid first name");
			request.setAttribute("lastName", "Enter the valid Last name");
			request.setAttribute("age", "Enter the valid Age");
			EmployeeModel invalidData = new EmployeeModel(invalidFirstName, invalidLastName, skills, invalidAge, salary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if ((employeeService.regexValidationForFirstName(firstName)
				&& employeeService.regexValidationForLastName(lastName)
				&& employeeService.regexValidationForSalary(salary) && country == null
				&& employee.getDoj().isEmpty()) == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("firstName", "Enter the valid first name");
			request.setAttribute("lastName", "Enter the valid Last name");
			request.setAttribute("salary", "Enter the valid Salary");
			request.setAttribute("country", "Please.Select the Country");
			request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
			EmployeeModel invalidData = new EmployeeModel(invalidFirstName, invalidLastName, skills, invalidAge,
					invalidSalary, dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if ((employeeService.regexValidationForFirstName(firstName)
				&& employeeService.regexValidationForLastName(lastName)
				&& employeeService.regexValidationForSalary(salary) && country == null) == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("firstName", "Enter the valid first name");
			request.setAttribute("lastName", "Enter the valid Last name");
			request.setAttribute("salary", "Enter the valid Salary");
			request.setAttribute("country", "Please.Select the country");
			EmployeeModel invalidData = new EmployeeModel(invalidFirstName, invalidLastName, skills, age, invalidSalary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if ((employeeService.regexValidationForFirstName(firstName)
				&& employeeService.regexValidationForLastName(lastName)
				&& employeeService.regexValidationForSalary(salary)) == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("firstName", "Enter the valid first name");
			request.setAttribute("lastName", "Enter the valid Last name");
			request.setAttribute("salary", "Enter the valid Salary");
			EmployeeModel invalidData = new EmployeeModel(invalidFirstName, invalidLastName, skills, age, invalidSalary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if ((employeeService.regexValidationForFirstName(firstName)
				&& employeeService.regexValidationForLastName(lastName) && country == null
				&& employee.getDoj().isEmpty()) == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("firstName", "Enter the valid first name");
			request.setAttribute("lastName", "Enter the valid Last name");
			request.setAttribute("country", "Please.Select the country");
			request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
			EmployeeModel invalidData = new EmployeeModel(invalidFirstName, invalidLastName, skills, age, invalidSalary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if ((employeeService.regexValidationForFirstName(firstName)
				&& employeeService.regexValidationForLastName(lastName) && country == null) == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("firstName", "Enter the valid first name");
			request.setAttribute("lastName", "Enter the valid Last name");
			request.setAttribute("country", "Please.Select the country");
			EmployeeModel invalidData = new EmployeeModel(invalidFirstName, invalidLastName, skills, age, invalidSalary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if ((employeeService.regexValidationForFirstName(firstName) && country == null
				&& employee.getDoj().isEmpty()) == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("firstName", "Enter the valid first name");
			request.setAttribute("country", "Please.Select the country");
			request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
			EmployeeModel invalidData = new EmployeeModel(invalidFirstName, lastName, skills, age, salary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if ((employeeService.regexValidationForSalary(salary) && country == null
				&& employee.getDoj().isEmpty()) == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("salary", "Enter the valid salary");
			request.setAttribute("country", "Please.Select the country");
			request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
			EmployeeModel invalidData = new EmployeeModel(firstName, lastName, skills, age, invalidSalary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if ((employeeService.regexValidationForFirstName(firstName)
				&& employeeService.regexValidationForLastName(lastName) && dateOfJoining.isEmpty()) == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("firstName", "Enter the valid first name");
			request.setAttribute("lastName", "Enter the valid Last name");
			request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
			EmployeeModel invalidData = new EmployeeModel(invalidFirstName, invalidLastName, skills, age, salary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (employeeService.regexValidationForFirstName(firstName) && employeeService.regexValidationForAge(age)
				&& employeeService.regexValidationForSalary(salary) && country == null
				&& dateOfJoining.isEmpty() == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("firstName", "Enter the valid first name");
			request.setAttribute("age", "Enter the valid Age");
			request.setAttribute("salary", "Enter the valid Salary");
			request.setAttribute("country", "Please.Select the Country");
			request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
			EmployeeModel invalidData = new EmployeeModel(invalidFirstName, lastName, skills, invalidAge, invalidSalary,
					dateOfJoining, country, id);
			request.setAttribute("invalidData", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (employeeService.regexValidationForFirstName(firstName) && employeeService.regexValidationForAge(age)
				&& employeeService.regexValidationForSalary(salary) && country == null == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("firstName", "Enter the valid first name");
			request.setAttribute("age", "Enter the valid Age");
			request.setAttribute("salary", "Enter the valid Salary");
			request.setAttribute("country", "Please.Select the Country");
			EmployeeModel invalidData = new EmployeeModel(invalidFirstName, lastName, skills, invalidAge, invalidSalary,
					dateOfJoining, country, id);
			request.setAttribute("invalidData", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (employeeService.regexValidationForFirstName(firstName) && employeeService.regexValidationForAge(age)
				&& employeeService.regexValidationForSalary(salary) == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("firstName", "Enter the valid first name");
			request.setAttribute("age", "Enter the valid Age");
			request.setAttribute("salary", "Enter the valid Salary");
			EmployeeModel invalidData = new EmployeeModel(invalidFirstName, lastName, skills, invalidAge, invalidSalary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (employeeService.regexValidationForFirstName(firstName) && employeeService.regexValidationForAge(age)
				&& dateOfJoining.isEmpty() == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("firstName", "Enter the valid first name");
			request.setAttribute("age", "Enter the valid Age");
			request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
			EmployeeModel invalidData = new EmployeeModel(invalidFirstName, lastName, skills, invalidAge, salary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (employeeService.regexValidationForFirstName(firstName)
				&& employeeService.regexValidationForSalary(salary) && country == null
				&& dateOfJoining.isEmpty() == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("firstName", "Enter the valid first name");
			request.setAttribute("salary", "Enter the valid Salary");
			request.setAttribute("country", "Please.Select the Country");
			request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
			EmployeeModel invalidData = new EmployeeModel(invalidFirstName, lastName, skills, age, invalidSalary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (employeeService.regexValidationForFirstName(firstName)
				&& employeeService.regexValidationForSalary(salary) && country == null == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("firstName", "Enter the valid first name");
			request.setAttribute("salary", "Enter the valid Salary");
			request.setAttribute("country", "Please.Select the Country");
			EmployeeModel invalidData = new EmployeeModel(invalidFirstName, lastName, skills, age, invalidSalary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (employeeService.regexValidationForFirstName(firstName) && country == null
				&& dateOfJoining.isEmpty() == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("firstName", "Enter the valid first name");
			request.setAttribute("country", "Please.Select the Country");
			request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
			EmployeeModel invalidData = new EmployeeModel(invalidFirstName, lastName, skills, age, salary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (employeeService.regexValidationForFirstName(firstName) && country == null == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("firstName", "Enter the valid first name");
			request.setAttribute("country", "Please.Select the Country");
			EmployeeModel invalidData = new EmployeeModel(invalidFirstName, lastName, skills, age, salary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (employeeService.regexValidationForFirstName(firstName) && dateOfJoining.isEmpty() == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("firstName", "Enter the valid first name");
			request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
			EmployeeModel invalidData = new EmployeeModel(invalidFirstName, lastName, skills, age, salary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (employeeService.regexValidationForFirstName(firstName)
				&& employeeService.regexValidationForAge(age) == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("firstName", "Enter the valid first name");
			request.setAttribute("age", "Enter the valid Age");
			EmployeeModel invalidData = new EmployeeModel(invalidFirstName, lastName, skills, invalidAge, salary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (employeeService.regexValidationForFirstName(firstName)
				&& employeeService.regexValidationForSalary(salary) == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("firstName", "Enter the valid first name");
			request.setAttribute("salary", "Enter the valid Salary");
			EmployeeModel invalidData = new EmployeeModel(invalidFirstName, lastName, skills, age, invalidSalary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (employeeService.regexValidationForLastName(lastName) && employeeService.regexValidationForAge(age)
				&& employeeService.regexValidationForSalary(salary) && country == null
				&& dateOfJoining.isEmpty() == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("lastName", "Enter the valid last name");
			request.setAttribute("age", "Enter the valid Age");
			request.setAttribute("salary", "Enter the valid Salary");
			request.setAttribute("country", "Please.Select the Country");
			request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
			EmployeeModel invalidData = new EmployeeModel(firstName, invalidLastName, skills, invalidAge, invalidSalary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (employeeService.regexValidationForLastName(lastName) && employeeService.regexValidationForAge(age)
				&& employeeService.regexValidationForSalary(salary) && country == null == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("lastName", "Enter the valid last name");
			request.setAttribute("age", "Enter the valid Age");
			request.setAttribute("salary", "Enter the valid Salary");
			request.setAttribute("country", "Please.Select the Country");
			EmployeeModel invalidData = new EmployeeModel(firstName, invalidLastName, skills, invalidAge, invalidSalary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (employeeService.regexValidationForLastName(lastName) && employeeService.regexValidationForAge(age)
				&& employeeService.regexValidationForSalary(salary) == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("lastName", "Enter the valid last name");
			request.setAttribute("age", "Enter the valid Age");
			request.setAttribute("salary", "Enter the valid Salary");
			EmployeeModel invalidData = new EmployeeModel(firstName, invalidLastName, skills, invalidAge, invalidSalary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if ((employeeService.regexValidationForLastName(lastName) && employeeService.regexValidationForAge(age)
				&& employeeService.regexValidationForSalary(salary) && dateOfJoining.isEmpty()) == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("lastName", "Enter the valid last name");
			request.setAttribute("age", "Enter the valid Age");
			request.setAttribute("salary", "Enter the Valid Salary");
			request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
			EmployeeModel invalidData = new EmployeeModel(firstName, invalidLastName, skills, invalidAge, invalidSalary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if ((employeeService.regexValidationForLastName(lastName) && employeeService.regexValidationForAge(age)
				&& dateOfJoining.isEmpty()) == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("lastName", "Enter the valid last name");
			request.setAttribute("age", "Enter the valid Age");
			request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
			EmployeeModel invalidData = new EmployeeModel(firstName, invalidLastName, skills, invalidAge, salary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (employeeService.regexValidationForLastName(lastName)
				&& employeeService.regexValidationForAge(age) == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("lastName", "Enter the valid last name");
			request.setAttribute("age", "Enter the valid Age");
			EmployeeModel invalidData = new EmployeeModel(firstName, invalidLastName, skills, invalidAge, salary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (employeeService.regexValidationForLastName(lastName)
				&& employeeService.regexValidationForSalary(salary) && country == null
				&& dateOfJoining.isEmpty() == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("lastName", "Enter the valid last name");
			request.setAttribute("salary", "Enter the valid Salary");
			request.setAttribute("country", "Please.Select the Country");
			request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
			EmployeeModel invalidData = new EmployeeModel(firstName, invalidLastName, skills, age, invalidSalary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (employeeService.regexValidationForLastName(lastName)
				&& employeeService.regexValidationForSalary(salary) && country == null == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("lastName", "Enter the valid last name");
			request.setAttribute("salary", "Enter the valid Salary");
			request.setAttribute("country", "Please.Select the Country");
			EmployeeModel invalidData = new EmployeeModel(firstName, invalidLastName, skills, age, invalidSalary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (employeeService.regexValidationForLastName(lastName) && country == null
				&& dateOfJoining.isEmpty() == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("lastName", "Enter the valid last name");
			request.setAttribute("country", "Please.Select the Country");
			request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
			EmployeeModel invalidData = new EmployeeModel(firstName, invalidLastName, skills, age, salary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (employeeService.regexValidationForLastName(lastName)
				&& employeeService.regexValidationForSalary(salary) == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("lastName", "Enter the valid last name");
			request.setAttribute("salary", "Enter the valid Salary");
			EmployeeModel invalidData = new EmployeeModel(firstName, invalidLastName, skills, age, invalidSalary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (employeeService.regexValidationForLastName(lastName) && dateOfJoining.isEmpty() == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("lastName", "Enter the valid last name");
			request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
			EmployeeModel invalidData = new EmployeeModel(firstName, invalidLastName, skills, age, salary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (employeeService.regexValidationForLastName(lastName) && country == null == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("lastName", "Enter the valid last name");
			request.setAttribute("country", "Please.Select the Country");
			EmployeeModel invalidData = new EmployeeModel(firstName, invalidLastName, skills, age, salary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (employeeService.regexValidationForAge(age) && employeeService.regexValidationForSalary(salary)
				&& country == null && dateOfJoining.isEmpty() == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("age", "Enter the valid Age");
			request.setAttribute("salary", "Enter the valid Salary");
			request.setAttribute("country", "Please.Select the Country");
			request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
			EmployeeModel invalidData = new EmployeeModel(firstName, lastName, skills, invalidAge, invalidSalary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if ((employeeService.regexValidationForAge(age) && employeeService.regexValidationForSalary(salary)
				&& country == null) == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("age", "Enter the valid Age");
			request.setAttribute("salary", "Enter the valid Salary");
			request.setAttribute("country", "Please.Select the Country");
			EmployeeModel invalidData = new EmployeeModel(firstName, lastName, skills, invalidAge, invalidSalary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (employeeService.regexValidationForSalary(salary) && dateOfJoining.isEmpty() == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("salary", "Enter the valid Salary");
			request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
			EmployeeModel invalidData = new EmployeeModel(firstName, lastName, skills, age, salary, dateOfJoining,
					country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (employeeService.regexValidationForAge(age) && country == null == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("age", "Enter the valid Age");
			request.setAttribute("country", "Please.Select the Country");
			EmployeeModel invalidData = new EmployeeModel(firstName, lastName, skills, invalidAge, salary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (employeeService.regexValidationForAge(age)
				&& employeeService.regexValidationForSalary(salary) == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("age", "Enter the valid Age");
			request.setAttribute("salary", "Enter the valid Salary");
			EmployeeModel invalidData = new EmployeeModel(firstName, lastName, skills, invalidAge, invalidSalary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (employeeService.regexValidationForAge(age) && employee.getDoj().isEmpty() == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("age", "Enter the valid Age");
			request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
			EmployeeModel invalidData = new EmployeeModel(firstName, lastName, skills, invalidAge, salary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if ((employeeService.regexValidationForSalary(salary) && country == null) == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("salary", "Enter the valid Salary");
			request.setAttribute("country", "Please.Select the Country");
			EmployeeModel invalidData = new EmployeeModel(firstName, lastName, skills, invalidAge, invalidSalary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (employeeService.regexValidationForFirstName(firstName)
				&& employeeService.regexValidationForLastName(lastName) == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("firstName", "Enter the valid first name");
			request.setAttribute("lastName", "Enter the valid Last name");
			EmployeeModel invalidData = new EmployeeModel(invalidFirstName, invalidLastName, skills, age, salary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (country == null && dateOfJoining.isEmpty() == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("country", "Please.Select the Country");
			request.setAttribute("dateOfJoining", "Please.Select the Date of Joining");
			EmployeeModel invalidData = new EmployeeModel(firstName, lastName, skills, age, salary, dateOfJoining,
					country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (employeeService.regexValidationForFirstName(firstName)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("firstName", "Enter the valid first name");
			EmployeeModel invalidData = new EmployeeModel(invalidFirstName, lastName, skills, age, salary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (employeeService.regexValidationForLastName(lastName)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("lastName", "Enter the valid last name");
			EmployeeModel invalidData = new EmployeeModel(firstName, invalidLastName, skills, age, salary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (employeeService.regexValidationForAge(age)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("age", "Enter the valid Age");
			EmployeeModel invalidData = new EmployeeModel(firstName, lastName, skills, invalidAge, salary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (employeeService.regexValidationForSalary(salary)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("salary", "Enter the valid Salary");
			EmployeeModel invalidData = new EmployeeModel(firstName, lastName, skills, age, invalidSalary,
					dateOfJoining, country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (country == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("country", "Please.Select the Country");
			EmployeeModel invalidData = new EmployeeModel(firstName, lastName, skills, age, salary, dateOfJoining,
					country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else if (dateOfJoining.isEmpty()) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			request.setAttribute("dateOfJoining", "Please.Enter the Date of Joining");
			EmployeeModel invalidData = new EmployeeModel(firstName, lastName, skills, age, salary, dateOfJoining,
					country, id);
			request.setAttribute("employee", invalidData);
			dispatcher.forward(request, response);
			return;
		} else {
			employeeService.updateEmployee(employee);
			EmployeeModel empskills = new EmployeeModel(id, skills);
			employeeService.updateEmployeeSkills(empskills);
			response.sendRedirect(request.getContextPath() + "/list");
			return;
		}
	}

	private void listEmployee(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
			response.setDateHeader("Expire", 0);
			HttpSession session = request.getSession();
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

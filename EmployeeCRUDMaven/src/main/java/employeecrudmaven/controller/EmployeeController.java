package employeecrudmaven.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import employeecrudmaven.dao.EmployeeDAOImpl;
import employeecrudmaven.model.EmployeeModel;
import employeecrudmaven.service.EmployeeService;
import employeecrudmaven.service.EmployeeServiceImpl;

@WebServlet("/")
public class EmployeeController extends HttpServlet {
	EmployeeService employeeService = new EmployeeServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
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

		default:
			listEmployee(request, response);
			break;
		}
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
		dispatcher.forward(request, response);
	}

	private void insertNewEmployee(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ClassNotFoundException, ServletException {
		String firstName = request.getParameter("empfname");
		String lastName = request.getParameter("emplname");
		String employeeSkillsArray[] = new String[0];
		String checkedEmployeeSkills = "";
		LinkedHashSet<String> skills = new LinkedHashSet<String>();
		employeeSkillsArray = request.getParameterValues("empSkills");
		for (int i = 0; i < employeeSkillsArray.length; i++) {
			checkedEmployeeSkills = employeeSkillsArray[i];
			skills.add(checkedEmployeeSkills);
		}
		String age = request.getParameter("empage");
		String salary = request.getParameter("empsalary");
		String dateOfJoining = request.getParameter("empdoj");
		String email = request.getParameter("empemail");
		ArrayList<String> emailList = employeeService.getEmployeeEmail();
		if (employeeService.validateEmail(email)) {
			EmployeeModel employee = new EmployeeModel(firstName, lastName, skills, age, salary, dateOfJoining, email);
			RequestDispatcher dispatcher = request.getRequestDispatcher("//WEB-INF//Views//EmployeeRegistration.jsp");
			String messege = "Email is already Present ... Please enter unique email...";
			request.setAttribute("messege", messege);
			request.setAttribute("employee", employee);
			dispatcher.forward(request, response);
		} else {
			EmployeeModel employee = new EmployeeModel(firstName, lastName, age, salary, dateOfJoining, email);
			employeeService.insertEmployee(employee);
			employeeService.insertEmployeeSkillsById(employee.getId(), skills);
			response.sendRedirect("empList");
		}
	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			employeeService.deleteEmployee(id);
			response.sendRedirect("empList");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		EmployeeModel selectedEmployee;
		try {
			selectedEmployee = employeeService.getEmployeeById(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("//WEB-INF//Views//EmployeeRegistration.jsp");
			request.setAttribute("employee", selectedEmployee);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("empId"));
		String firstName = request.getParameter("empfname");
		String lastName = request.getParameter("emplname");
		String employeeSkills[] = request.getParameterValues("empSkills");
		String checkedEmployeeSkills = "";
		LinkedHashSet<String> skills = new LinkedHashSet<String>();
		for (int i = 0; i < employeeSkills.length; i++) {
			checkedEmployeeSkills = employeeSkills[i];
			skills.add(checkedEmployeeSkills);
		}
		String age = request.getParameter("empage");
		String salary = request.getParameter("empsalary");
		String dateOfJoining = request.getParameter("empdoj");
		String email = request.getParameter("empemail");
		EmployeeModel employee = new EmployeeModel(id, firstName, lastName, skills, age, salary, dateOfJoining, email);
		employeeService.updateEmployee(employee);
		EmployeeModel empskills = new EmployeeModel(id, skills);
		employeeService.updateEmployeeSkills(empskills);
		response.sendRedirect("empList");
	}

	private void listEmployee(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<EmployeeModel> employeeList = employeeService.getAllEmployee();
			request.setAttribute("empList", employeeList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("//WEB-INF//Views//index.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

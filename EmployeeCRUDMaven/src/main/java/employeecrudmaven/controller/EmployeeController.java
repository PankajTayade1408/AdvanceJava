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
	int count=0;
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
		System.out.println("New");
		HttpSession session = request.getSession();
		Integer loginId = (Integer) session.getAttribute("id");
		System.out.println(loginId);
		EmployeeModel employee=employeeService.getEmployeeById(loginId);
		if (session != null && loginId!=null ) {
			response.setDateHeader("Expire", 0);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\Views\\EmployeeRegistration.jsp");
			dispatcher.forward(request, response);
			return;
		}else {
			response.sendRedirect("http://localhost:8080/");
		}
	}

	private void insertNewEmployee(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException, ClassNotFoundException {
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		response.setDateHeader("Expire", 0);	
		LoginService loginService = new LoginServiceImpl();
		HttpSession session = request.getSession(true);
		Integer loginId = (Integer) session.getAttribute("id");
		if (session != null ) {
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
			employeeService.insertEmployee(employee);
			employeeService.insertEmployeeSkillsById(employee.getId(), skills);
			response.sendRedirect(request.getContextPath()+"/list");
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("//WEB-INF//Views//EmployeeRegistration.jsp");
			request.setAttribute("employee", selectedEmployee);
			dispatcher.forward(request, response);
			}else {
				response.sendRedirect("http://localhost:8080/");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String firstName = request.getParameter("empfname");
		String lastName = request.getParameter("emplname");
		String employeeSkillsArray[] = new String[0];
		String checkedEmployeeSkills = "";
		LinkedHashSet<String> skills = new LinkedHashSet<String>();
		if (request.getParameterValues("empSkills") == null ) {
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
		EmployeeModel employee = new EmployeeModel(id, firstName, lastName, skills, age, salary, dateOfJoining);
		employeeService.updateEmployee(employee);
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
				String username=(String) session.getAttribute("usernameLogin");
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

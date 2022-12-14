package employeecrudmaven.service;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.*;
import employeecrudmaven.service.EmployeeService;
import employeecrudmaven.dao.EmployeeDAO;
import employeecrudmaven.dao.EmployeeDAOImpl;
import employeecrudmaven.dao.LoginDAOImpl;
import employeecrudmaven.model.EmployeeModel;

public class EmployeeServiceImpl implements EmployeeService {
	EmployeeDAO employeeDAO = new EmployeeDAOImpl();
<<<<<<< HEAD

	public void insertEmployee(EmployeeModel employee) {
			employeeDAO.insertEmployee(employee);
=======
	String regexForName = "([A-Z][a-z]*)";
	String regexForSize = ".{3,}";
	String regexForAge = "1[89]|[2-9][0-9]|100";
	String regexForSalary = "([+]?\\d\\.?\\d*.?\\S*)(?=.*[^a-zA-Z])[^!@#%^&*?>']";

	public void insertEmployee(EmployeeModel employee, FileInputStream fileInputStream) {
		employeeDAO.insertEmployee(employee, fileInputStream);
>>>>>>> 4946e0a6446a48c07ec16a121c46492e195926ff
	}
	
	public boolean validateEmail(String email) {
		ArrayList<String> emailList = getEmployeeEmail();
		if (emailList.contains(email)) {
			return true;
		} else {
			return false;
		}
	}
	
	public EmployeeModel getEmployeeById(int id) {
		return employeeDAO.getEmployeeById(id);
	}

	public List<EmployeeModel> getAllEmployee(int loginId) {
		return employeeDAO.getAllEmployee(loginId);
	}

<<<<<<< HEAD
	public boolean updateEmployee(EmployeeModel employee) {
			return employeeDAO.updateEmployee(employee);
=======
	public boolean updateEmployee(EmployeeModel employee, FileInputStream fileInputStream) {
		return employeeDAO.updateEmployee(employee, fileInputStream);
>>>>>>> 4946e0a6446a48c07ec16a121c46492e195926ff
	}

	public boolean deleteEmployee(int id) throws Exception {
		return employeeDAO.deleteEmployee(id);
	}

	public boolean updateEmployeeSkills(EmployeeModel employee) {
<<<<<<< HEAD
		boolean updatedEmployeeSkills;
		LinkedHashSet<String> skillsFromUser = new LinkedHashSet<String>();
		skillsFromUser = employee.getSkills();
=======
		boolean updateSkills = false;
		LinkedHashSet<String> userSkills = new LinkedHashSet<String>();
		userSkills = employee.getSkills();
>>>>>>> 4946e0a6446a48c07ec16a121c46492e195926ff
		int employeeId = employee.getId();
		EmployeeDAO getSkills = new EmployeeDAOImpl();
		LinkedHashSet<String> skillsFromDB = new LinkedHashSet<String>();
		skillsFromDB = getSkills.getEmployeeSkillsById(employeeId);
		LinkedHashSet<String> retainUserSkills = (LinkedHashSet<String>) userSkills.clone();
		LinkedHashSet<String> removeUserSkills = (LinkedHashSet<String>) userSkills.clone();
		LinkedHashSet<String> retainDBSkills = (LinkedHashSet<String>) skillsFromDB.clone();
		LinkedHashSet<String> removeDBSkills = (LinkedHashSet<String>) skillsFromDB.clone();
<<<<<<< HEAD
		if (skillsFromUser.equals(skillsFromDB)) {
			updatedEmployeeSkills = true;
=======
		if (userSkills.equals(skillsFromDB)) {
			updateSkills = true;
>>>>>>> 4946e0a6446a48c07ec16a121c46492e195926ff
		} else {
			retainUserSkills.retainAll(skillsFromDB);
			removeDBSkills.removeAll(retainUserSkills);
			EmployeeDAO employeeSkillsDelete = new EmployeeDAOImpl();
			employeeSkillsDelete.deleteEmployeeSkillsById(employeeId, removeDBSkills);
			retainDBSkills.retainAll(userSkills);
			removeUserSkills.removeAll(retainDBSkills);
<<<<<<< HEAD
			EmployeeDAO employeeSkillsInsert = new EmployeeDAOImpl();
			employeeSkillsInsert.insertEmployeeSkillsById(employeeId, removeUserSkills);
			updatedEmployeeSkills = true;
=======
			EmployeeDAO skillsToBeInserted = new EmployeeDAOImpl();
			skillsToBeInserted.insertEmployeeSkillsById(employeeId, removeUserSkills);
			updateSkills = true;
>>>>>>> 4946e0a6446a48c07ec16a121c46492e195926ff
		}
		return updateSkills;
	}

	public LinkedHashSet<String> getEmployeeSkillsById(int id) {
		return employeeDAO.getEmployeeSkillsById(id);
	}

	public void deleteEmployeeSkillsById(int employeeId, LinkedHashSet<String> skills) {
		employeeDAO.deleteEmployeeSkillsById(employeeId, skills);
	}

	public int insertEmployeeSkillsById(int id, LinkedHashSet<String> skills) {
		int latestId = 0;
		EmployeeModel employee = new EmployeeModel();
<<<<<<< HEAD
		latestId = employeeDAO.selectLatestIdFromEmployee();
		if (employee.getId() == 0 || employee.getId() == latestId) {
			id = latestId;
			employeeDAO.insertEmployeeSkillsById(id, skills);
		} else if (employee.getId() < id) {
			id = employee.getId();
			employeeDAO.insertEmployeeSkillsById(id, skills);
=======
		latestId = EmployeeDAOImpl.selectLatestIdFromEmployee(id);
		if (employee.getId() == 0) {
			id = latestId;
			employeeDAO.insertEmployeeSkillsById(id, skills);
>>>>>>> 4946e0a6446a48c07ec16a121c46492e195926ff
		}
		return id;
	}

<<<<<<< HEAD
	public ArrayList<String> getEmployeeEmail() {
		return employeeDAO.getEmployeeEmail();
	}

	public String getEmployeeEmailById(int id) {

		return employeeDAO.getEmployeeEmailById(id);
	}

	public int selectLatestIdFromEmployee() {
		return employeeDAO.selectLatestIdFromEmployee();
=======
	public boolean regexValidationForFirstName(String firstName) {
		if (!(Pattern.matches(regexForName, firstName) && Pattern.matches(regexForSize, firstName))) {
			return false;
		}
		return true;
	}

	public boolean regexValidationForLastName(String lastName) {
		if (!(Pattern.matches(regexForName, lastName) && Pattern.matches(regexForSize, lastName))) {
			return false;
		}
		return true;
	}

	public boolean regexValidationForAge(String age) {
		if (!Pattern.matches(regexForAge, age)) {
			return false;
		}
		return true;
	}

	public boolean regexValidationSalary(String salary) {
		if (salary == null || salary.isEmpty()) {
			return false;
		} else {
			if (Pattern.matches(regexForSalary, salary)) {
				return true;
			}
		}
		return false;
>>>>>>> 4946e0a6446a48c07ec16a121c46492e195926ff
	}

}

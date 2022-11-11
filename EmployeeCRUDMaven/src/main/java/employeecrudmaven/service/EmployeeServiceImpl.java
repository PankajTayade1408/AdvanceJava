package employeecrudmaven.service;

import java.util.*;

import java.util.regex.*;
import employeecrudmaven.service.EmployeeService;
import employeecrudmaven.dao.EmployeeDAO;
import employeecrudmaven.dao.EmployeeDAOImpl;
import employeecrudmaven.dao.LoginDAOImpl;
import employeecrudmaven.model.EmployeeModel;
import employeecrudmaven.model.LoginModel;

public class EmployeeServiceImpl implements EmployeeService {
	EmployeeDAO employeeDAO = new EmployeeDAOImpl();
	String regexForName = "([A-Z][a-z]*)";
	String regexForSize = ".{3,}";
	String regexForAge = "1[89]|[2-9][0-9]|100";
	String regexForSalary = "([0-9]*[.][0-9]{2})";

	public void insertEmployee(EmployeeModel employee) {
		employeeDAO.insertEmployee(employee);
	}

	public EmployeeModel getEmployeeById(int id) {
		return employeeDAO.getEmployeeById(id);
	}

	public List<EmployeeModel> getAllEmployee(int loginId) {
		List<Integer> loginIdList = employeeDAO.getLoginId();
		if (loginIdList.contains(loginId)) {
			return employeeDAO.getAllEmployee(loginId);
		} else {
			int lastestId = LoginDAOImpl.getLatestId();
			return employeeDAO.getAllEmployee(lastestId);
		}
	}

	public boolean updateEmployee(EmployeeModel employee) {
		return employeeDAO.updateEmployee(employee);
	}

	public boolean deleteEmployee(int id) throws Exception {
		return employeeDAO.deleteEmployee(id);
	}

	public boolean updateEmployeeSkills(EmployeeModel employee) {
		boolean updatedEmployeeSkills = false;
		LinkedHashSet<String> skillsFromUser = new LinkedHashSet<String>();
		skillsFromUser = employee.getSkills();
		int employeeId = employee.getId();
		EmployeeDAO getEmployeeSkill = new EmployeeDAOImpl();
		LinkedHashSet<String> skillsFromDB = new LinkedHashSet<String>();
		skillsFromDB = getEmployeeSkill.getEmployeeSkillsById(employeeId);
		LinkedHashSet<String> retainUserSkills = (LinkedHashSet<String>) skillsFromUser.clone();
		LinkedHashSet<String> removeUserSkills = (LinkedHashSet<String>) skillsFromUser.clone();
		LinkedHashSet<String> retainDBSkills = (LinkedHashSet<String>) skillsFromDB.clone();
		LinkedHashSet<String> removeDBSkills = (LinkedHashSet<String>) skillsFromDB.clone();
		if (skillsFromUser.equals(skillsFromDB)) {
			System.out.println("No Need To Update ");
			updatedEmployeeSkills = true;
		} else {
			retainUserSkills.retainAll(skillsFromDB);
			removeDBSkills.removeAll(retainUserSkills);
			EmployeeDAO employeeSkillsDelete = new EmployeeDAOImpl();
			employeeSkillsDelete.deleteEmployeeSkillsById(employeeId, removeDBSkills);
			retainDBSkills.retainAll(skillsFromUser);
			removeUserSkills.removeAll(retainDBSkills);
			EmployeeDAO employeeSkillsInsert = new EmployeeDAOImpl();
			employeeSkillsInsert.insertEmployeeSkillsById(employeeId, removeUserSkills);
			updatedEmployeeSkills = true;
		}
		return updatedEmployeeSkills;
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
		latestId = EmployeeDAOImpl.selectLatestIdFromEmployee(id);
		if (employee.getId() == 0 || employee.getId() == latestId) {
			id = latestId;
			employeeDAO.insertEmployeeSkillsById(id, skills);
		} else if (employee.getId() < id) {
			id = employee.getId();
			employeeDAO.insertEmployeeSkillsById(id, skills);
		}
		return id;
	}

	public boolean regexValidationForFirstName(String firstName) {
		if ((Pattern.matches(regexForName, firstName) && Pattern.matches(regexForSize, firstName)) == false) {
			return true;
		}
		return false;
	}

	public boolean regexValidationForLastName(String lastName) {
		if ((Pattern.matches(regexForName, lastName) && Pattern.matches(regexForSize, lastName)) == false) {
			return true;
		}
		return false;
	}

	@Override
	public boolean regexValidationForAge(String age) {
		if (Pattern.matches(regexForAge, age) == false) {
			return true;
		}
		return false;
	}

	@Override
	public boolean regexValidationForSalary(String salary) {
		if (Pattern.matches(regexForSalary, salary) == false) {
			return true;
		}
		return false;
	}
}

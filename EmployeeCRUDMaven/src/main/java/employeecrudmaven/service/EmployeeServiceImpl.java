package employeecrudmaven.service;

import java.util.*;

import employeecrudmaven.service.EmployeeService;
import employeecrudmaven.dao.EmployeeDAO;
import employeecrudmaven.dao.EmployeeDAOImpl;
import employeecrudmaven.model.EmployeeModel;

public class EmployeeServiceImpl implements EmployeeService {
	EmployeeDAO employeeDAO = new EmployeeDAOImpl();
	
	public void insertEmployee(EmployeeModel employee) {
		employeeDAO.insertEmployee(employee);
	}

	public EmployeeModel getEmployeeById(int id) {
		return employeeDAO.getEmployeeById(id);
	}

	public List<EmployeeModel> getAllEmployee() {
		return employeeDAO.getAllEmployee();
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
		if (skillsFromUser.equals(skillsFromDB)) 
		{
			System.out.println("No Need To Update ");
			updatedEmployeeSkills=true;
		}
		else 
		{
			retainUserSkills.retainAll(skillsFromDB);
			removeDBSkills.removeAll(retainUserSkills);
			EmployeeDAO employeeSkillsDelete = new EmployeeDAOImpl();
			employeeSkillsDelete.deleteEmployeeSkillsById(employeeId, removeDBSkills);
			retainDBSkills.retainAll(skillsFromUser);
			removeUserSkills.removeAll(retainDBSkills);
			EmployeeDAO employeeSkillsInsert = new EmployeeDAOImpl();
			employeeSkillsInsert.insertEmployeeSkillsById(employeeId,removeUserSkills);
			updatedEmployeeSkills=true;
		}
		return updatedEmployeeSkills;
	}
	
	public LinkedHashSet<String> getEmployeeSkillsById(int id) {
		return employeeDAO.getEmployeeSkillsById(id);
	}
	
	public void deleteEmployeeSkillsById(int employeeId, LinkedHashSet<String> skills) {
		employeeDAO.deleteEmployeeSkillsById(employeeId, skills);
	}
	
	public int insertEmployeeSkillsById(int id,LinkedHashSet<String> skills) {
		int latestId=0;
		EmployeeModel employee=new EmployeeModel();
		latestId = EmployeeDAOImpl.selectLatestIdFromEmployee(id);
		if(employee.getId()==0 || employee.getId()==latestId)
		{
			id=latestId;
			employeeDAO.insertEmployeeSkillsById(id,skills);  
		}
		else if(employee.getId()<id)
		{ 
			id=employee.getId();
			employeeDAO.insertEmployeeSkillsById(id,skills);
		}
		return id;
	}
}
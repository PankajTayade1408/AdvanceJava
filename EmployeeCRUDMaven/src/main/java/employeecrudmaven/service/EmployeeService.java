package employeecrudmaven.service;

import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.List;

import employeecrudmaven.model.EmployeeModel;

public interface EmployeeService {
	
	//Methods For Employee1 Table 
    public void insertEmployee(EmployeeModel employee);
	
	public EmployeeModel getEmployeeById(int id);
    
	public List<EmployeeModel> getAllEmployee();
	
	public  boolean updateEmployee(EmployeeModel employee) throws SQLException;

	public  boolean deleteEmployee(int id) throws Exception;
	
	//Methods For Employee_Skills Table=
	public  LinkedHashSet<String> getEmployeeSkillsById(int id);
	
	public  void deleteEmployeeSkillsById(int employeeId,LinkedHashSet<String> skills);
	
	public  int insertEmployeeSkillsById(int id,LinkedHashSet<String> skills);
	
	public boolean updateEmployeeSkills(EmployeeModel employee);

}

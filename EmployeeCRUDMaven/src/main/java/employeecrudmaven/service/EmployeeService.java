package employeecrudmaven.service;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import employeecrudmaven.model.EmployeeModel;

public interface EmployeeService {

	// Methods For Employee1 Table
	public void insertEmployee(EmployeeModel employee,FileInputStream fileInputStream);

	public EmployeeModel getEmployeeById(int id);
<<<<<<< HEAD
    
	public List<EmployeeModel> getAllEmployee();
	
	public  boolean updateEmployee(EmployeeModel employee) throws SQLException;
	
	public ArrayList<String> getEmployeeEmail();

	public  String  getEmployeeEmailById(int id);
	
	public  boolean deleteEmployee(int id) throws Exception;
	
	public int selectLatestIdFromEmployee();
	
	public boolean validateEmail(String email);
	
	//Methods For Employee_Skills Table=
	public  LinkedHashSet<String> getEmployeeSkillsById(int id);
	
	public  void deleteEmployeeSkillsById(int employeeId,LinkedHashSet<String> skills);
	
	public  int insertEmployeeSkillsById(int id,LinkedHashSet<String> skills);
	
=======

	public List<EmployeeModel> getAllEmployee(int loginId);

	public boolean updateEmployee(EmployeeModel employee,FileInputStream fileInputStream) throws SQLException;

	public boolean deleteEmployee(int id) throws Exception;

	public boolean regexValidationForFirstName(String firstName);

	public boolean regexValidationForLastName(String lastName);

	public boolean regexValidationForAge(String age);

	public boolean regexValidationSalary(String salary);

	// Methods For Employee_Skills Table=
	public LinkedHashSet<String> getEmployeeSkillsById(int id);

	public void deleteEmployeeSkillsById(int employeeId, LinkedHashSet<String> skills);

	public int insertEmployeeSkillsById(int id, LinkedHashSet<String> skills);

>>>>>>> 4946e0a6446a48c07ec16a121c46492e195926ff
	public boolean updateEmployeeSkills(EmployeeModel employee);
	
}
